package playtika.ua.vn.model.VO {

	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.TimerEvent;
	import flash.utils.Timer;

	public class TimerVO extends EventDispatcher {

		private var myTimer:Timer;
		private static const REPEAT_COUNT:int = 10;
		private static const DELAY:int = 1000; // 1s = 1000ms

		public function TimerVO() {

			myTimer = new Timer(DELAY, REPEAT_COUNT);
			init();
			myTimer.start();
		}

		private function init():void {

			myTimer.addEventListener(TimerEvent.TIMER, onTimer);
			myTimer.addEventListener(TimerEvent.TIMER_COMPLETE, onTimerComplete);
		}

		protected function onTimerComplete(event:TimerEvent):void {

			myTimer.reset();
			myTimer.start();
		}

		protected function onTimer(event:TimerEvent):void {

			dispatchEvent(new Event(TimerEvent.TIMER));
		}
	}
}
