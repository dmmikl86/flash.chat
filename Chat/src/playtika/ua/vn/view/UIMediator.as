package playtika.ua.vn.view {

	import config.GeneralNotification;

	import org.puremvc.as3.patterns.mediator.Mediator;

	import playtika.ua.vn.view.components.ViewLogic;

	public class UIMediator extends Mediator {
		private static const NAME:String = "UIMediator";

		public function UIMediator(name:String, viewElement:ViewLogic) {

			super(name, viewElement);
		}

		override public function onRegister():void {

			super.onRegister();
			sendNotification(GeneralNotification.ADD_CHILD_TO_ROOT, vLogic.content);
		}

		override public function onRemove():void {

			sendNotification(GeneralNotification.REMOVE_CHILD_FROM_ROOT, viewComponent);
		}

		public function get vLogic():ViewLogic {

			return viewComponent as ViewLogic;
		}
	}
}
