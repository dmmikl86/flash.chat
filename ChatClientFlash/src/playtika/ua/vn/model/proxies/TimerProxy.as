package playtika.ua.vn.model.proxies {

	import config.GeneralCommands;

	import flash.events.Event;
	import flash.events.TimerEvent;

	import org.puremvc.as3.patterns.proxy.Proxy;

	import playtika.ua.vn.model.VO.TimerVO;

	public class TimerProxy extends Proxy {
		public static const NAME:String = "TimerProxy";

		public function TimerProxy() {

			super(NAME, new TimerVO());
			intit();
		}

		private function intit():void {

			timer.addEventListener(TimerEvent.TIMER, onTimer);
		}

		protected function onTimer(event:Event):void {

			sendNotification(GeneralCommands.TIMER);
		}

		public function get timer():TimerVO {

			return data as TimerVO;
		}
	}
}
