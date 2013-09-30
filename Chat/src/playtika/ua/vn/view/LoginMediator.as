package playtika.ua.vn.view {

	import config.GeneralCommands;
	import config.GeneralNotification;

	import flash.display.DisplayObjectContainer;
	import flash.events.Event;
	import flash.events.MouseEvent;

	import org.puremvc.as3.interfaces.INotification;

	import utils.EventTrans;

	import playtika.ua.vn.view.components.LoginViewLogic;

	public class LoginMediator extends UIMediator {
		public static const NAME:String = "LoginMediator";

		public function LoginMediator() {

			super(NAME, new LoginViewLogic(new LoginMC()));
			registerEventListeners();
		}

		private function registerEventListeners():void {

			vLogic.addEventListener(MouseEvent.CLICK, onMouseClick, false, 0, false);
		}

		protected function onMouseClick(event:EventTrans):void {

			sendNotification(GeneralCommands.LOGIN_COMPLETE, event.data);
		}

		public function get content():LoginViewLogic {

			return viewComponent as LoginViewLogic;
		}

		override public function listNotificationInterests():Array {

			return [GeneralNotification.KEY_PRESSED
				];
		}

		override public function handleNotification(notification:INotification):void {

			switch(notification.getName()) {
				case GeneralNotification.KEY_PRESSED:
					content.keyActions(notification.getBody() as Array);
					break;
			}
		}

		override public function onRemove():void {

			super.onRemove();
		}
	}
}
