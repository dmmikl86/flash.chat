package playtika.ua.vn.model.VO {

	import flash.display.Stage;
	import flash.events.EventDispatcher;
	import flash.events.KeyboardEvent;

	import utils.EventTrans;

	public class KeyboardVO extends EventDispatcher {
		private var _data:Stage;
		private var keyCode:Array = new Array();

		public function KeyboardVO(data:Stage) {

			_data = data;
			init();
		}

		private function init():void {

			_data.addEventListener(KeyboardEvent.KEY_DOWN, onKeyDown);
			_data.addEventListener(KeyboardEvent.KEY_UP, onKeyUp);
		}

		protected function onKeyDown(event:KeyboardEvent):void {

			keyCode[event.keyCode] = true;
			dispatchEvent(new EventTrans(KeyboardEvent.KEY_DOWN, keyCode));
			keyCode[event.keyCode] = false;
		}

		protected function onKeyUp(event:KeyboardEvent):void {

			keyCode[event.keyCode] = false;
		}
	}
}
