package playtika.ua.vn.view.components {

	import flash.display.DisplayObjectContainer;
	import flash.events.EventDispatcher;

	public class ViewLogic extends EventDispatcher {
		protected var _graphics:DisplayObjectContainer;

		public function ViewLogic(graphics:DisplayObjectContainer) {

			_graphics = graphics;
		}

		public function get content():DisplayObjectContainer {

			return _graphics as DisplayObjectContainer;
		}
	}
}
