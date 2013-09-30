package playtika.ua.vn.view {

	import config.GeneralCommands;
	import config.GeneralNotification;

	import org.puremvc.as3.interfaces.INotification;

	import playtika.ua.vn.view.components.PanelViewLogic;

	import utils.EventTrans;

	public class PanelMediator extends UIMediator {
		private static const NAME:String = "PanelMediator";

		public function PanelMediator() {

			super(NAME, new PanelViewLogic(new PanelMC()));
			registerEventListeners();
		}

		private function registerEventListeners():void {

			content.addEventListener(EventTrans.SEND, onSend);
		}

		protected function onSend(event:EventTrans):void {

			sendNotification(GeneralCommands.SEND_MESSAGE, event.data);
		}

		public function get content():PanelViewLogic {

			return viewComponent as PanelViewLogic;
		}

		override public function listNotificationInterests():Array {

			return [GeneralNotification.KEY_PRESSED,
				GeneralNotification.RECEIVE_MESSAGE,
				GeneralNotification.ADD_USER_TO_LIST
				];
		}

		override public function handleNotification(notification:INotification):void {

			switch(notification.getName()) {
				case GeneralNotification.KEY_PRESSED:
					content.keyActions(notification.getBody() as Array);
					break;
				case GeneralNotification.RECEIVE_MESSAGE:
					content.receiveMessage(notification.getBody() as String);
					break;
				case GeneralNotification.ADD_USER_TO_LIST:
					content.addUserToUserList(notification.getBody() as Array);
			}
		}
	}
}
