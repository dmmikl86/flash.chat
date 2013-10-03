package {

	import flash.display.Sprite;
	import flash.events.Event;

	import playtika.ua.vn.ChatFacade;

	[SWF(width = '700', height = '400', frameRate = '25', backgroundColor = '#ffffff')]
	public class Chat extends Sprite {
		public function Chat() {

			this.addEventListener(Event.ADDED_TO_STAGE, onAddedToStage);
		}

		private function onAddedToStage(event:Event):void {

			this.removeEventListener(Event.ADDED_TO_STAGE, onAddedToStage);
			var sprite:Sprite = event.target as Sprite;
			ChatFacade.getInstance().startup(sprite);
		}
	}
}
