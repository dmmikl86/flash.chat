package playtika.ua.vn.view.components {

	import flash.display.DisplayObjectContainer;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.text.TextField;

	import utils.EventTrans;

	public class PanelViewLogic extends ViewLogic {

		public function PanelViewLogic(graphics:DisplayObjectContainer) {

			super(graphics);
			initComponents();
		}

		private function initComponents():void {

			content['btn_send'].addEventListener(MouseEvent.CLICK, onMouseClick, false, 0, false);
			content['usesrList'].addEventListener(MouseEvent.CLICK, onListClick, false, 0, false);
			content["SendTo"].text = "testUser";
		}

		protected function onMouseClick(event:MouseEvent):void {

			var message:String;

			if(content['input_text'].text.length != 0) {
				message = content['input_text'].text;
				addMessageToHistory(message);
			} else {
				return;
			}
			dispatchEvent(new EventTrans(EventTrans.SEND, {message:message, sendTo:content["SendTo"].text}));
		}

		protected function onListClick(event:MouseEvent):void {

			trace(event.target.name);

			if((event.target as TextField).text == "" || (event.target as TextField).text == content["SendTo"].text) {
				return;
			}
			content["SendTo"].text = (event.target as TextField).text;
		}

		private function addMessageToHistory(message:String):void {

			var currentField:String;
			var previousField:String;

			for(var i:int = 9; i > 1; i--) {
				currentField = 'history_text_' + i;
				previousField = 'history_text_' + ((i - 1) as int);
				content[currentField].text = content[previousField].text;
			}
			content['history_text_1'].text = message;
			content['input_text'].text = "";
		}

		public function keyActions(keyCode:Array):void {

			if(keyCode[13] == true) {
				content['btn_send'].dispatchEvent(new MouseEvent(MouseEvent.CLICK));
			}
		}

		public function receiveMessage(message:String):void {

			addMessageToHistory(message);
		}

		public function addUserToUserList(users:Array):void {

			for(var i:int = 0; i < users.length; i++) {
				if(i == 10) {
					break;
				}
				var item:String = "userName_" + (i + 1);
				content['usesrList'][item].text = users[i];
			}
		}
	}
}
