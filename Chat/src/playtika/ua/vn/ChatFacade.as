package playtika.ua.vn {

	import config.GeneralCommands;

	import flash.display.Sprite;
	import flash.net.Socket;

	import org.puremvc.as3.patterns.facade.Facade;

	import playtika.ua.vn.controller.*;

	public class ChatFacade extends Facade {
		private static var _instance:ChatFacade;

		public function ChatFacade() {

			super();
		}

		public static function getInstance():ChatFacade {

			if(!_instance) {
				_instance = new ChatFacade();
			}
			return _instance;
		}

		public function startup(mainView:Sprite):void {

			registerCommand(GeneralCommands.STARTUP_COMMAND, StartupCommand);
			sendNotification(GeneralCommands.STARTUP_COMMAND, mainView);
		}
	}
}
