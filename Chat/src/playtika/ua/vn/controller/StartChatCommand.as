package playtika.ua.vn.controller {

	import config.GeneralCommands;

	import flash.utils.Timer;

	import org.puremvc.as3.interfaces.INotification;
	import org.puremvc.as3.patterns.command.SimpleCommand;

	import playtika.ua.vn.model.proxies.TimerProxy;
	import playtika.ua.vn.view.PanelMediator;

	public class StartChatCommand extends SimpleCommand {
		override public function execute(notification:INotification):void {

			facade.registerMediator(new PanelMediator());

			init();
		}

		private function init():void {

			facade.registerCommand(GeneralCommands.SHOW_MESSAGE, ShowMessageCommand);
			facade.registerCommand(GeneralCommands.TIMER, TimerCommand);
			facade.registerProxy(new TimerProxy());
		}
	}
}
