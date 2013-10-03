package utils {

	import flash.events.Event;

	public class EventTrans extends Event {

		protected var event_data:Object;
		public static const SEND:String = "send";

		public function EventTrans(type:String, data:Object = null, bubbles:Boolean = false, cancelable:Boolean = false) {

			super(type, bubbles, cancelable);

			event_data = data;
		}

		public function get data():Object {

			return event_data;
		}

		public override function clone():Event {

			return new EventTrans(type, data, bubbles, cancelable);
		}
	}
}
