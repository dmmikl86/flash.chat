package playtika.ua.vn.view {

	import config.GeneralNotification;

	import flash.display.DisplayObjectContainer;
	import flash.display.Sprite;

	import org.puremvc.as3.interfaces.INotification;
	import org.puremvc.as3.patterns.mediator.Mediator;

	public class RootMediator extends Mediator {
		public static const NAME:String = "RootMediator";

		public function RootMediator(mainView:Sprite) {

			super(NAME, mainView);
		}

		private function get content():DisplayObjectContainer {

			return viewComponent as DisplayObjectContainer;
		}

		override public function listNotificationInterests():Array {

			return [GeneralNotification.ADD_CHILD_TO_ROOT,
				GeneralNotification.REMOVE_CHILD_FROM_ROOT];
		}

		override public function handleNotification(notification:INotification):void {

			switch(notification.getName()) {
				case GeneralNotification.ADD_CHILD_TO_ROOT:
					content.addChild(notification.getBody() as DisplayObjectContainer);
					break;
				case GeneralNotification.REMOVE_CHILD_FROM_ROOT:
					content.removeChildAt(0);
					break;
			}
		}
	}
}
