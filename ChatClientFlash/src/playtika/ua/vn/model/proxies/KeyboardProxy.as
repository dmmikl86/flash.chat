package playtika.ua.vn.model.proxies {

	import config.GeneralCommands;
	import config.GeneralNotification;

	import flash.display.Sprite;
	import flash.events.KeyboardEvent;

	import org.puremvc.as3.patterns.proxy.Proxy;

	import playtika.ua.vn.model.VO.KeyboardVO;

	import utils.EventTrans;

	public class KeyboardProxy extends Proxy {
		public static const NAME:String = "KeyboardProxy";


		public function KeyboardProxy(data:Sprite) {

			super(NAME, new KeyboardVO(data.root.stage));
		}

		override public function onRegister():void {

			data.addEventListener(KeyboardEvent.KEY_DOWN, onKeyDown);
		}

		protected function onKeyDown(event:EventTrans):void {

			sendNotification(GeneralCommands.KEYBOARD_CONTROLER, event.data);
		}
	}
}
