package playtika.ua.vn.view.components {

	import flash.display.DisplayObjectContainer;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.text.TextField;

	import utils.EventTrans;

	public class LoginViewLogic extends ViewLogic {
		public function LoginViewLogic(graphics:DisplayObjectContainer) {

			super(graphics);
			initComponents();
		}

		private function initComponents():void {

			content['btn_ok'].addEventListener(MouseEvent.CLICK, onMouseClick, false, 0, false);
			content.x = 175;
			content.y = 80;
		}

		protected function onMouseClick(event:MouseEvent):void {

			if((content['userName'] as TextField).text == "" || (content['password'] as TextField).text == "") {
				return;
			}

			dispatchEvent(new EventTrans(MouseEvent.CLICK, {userName:(content['userName'] as TextField).text, password:(content['password'] as TextField).text}));
		}

		public function keyActions(keyCode:Array):void {

			if(keyCode[13] == true) {
				content['btn_ok'].dispatchEvent(new MouseEvent(MouseEvent.CLICK));
			}
		}
	}
}
