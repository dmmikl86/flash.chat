package playtika.ua.vn.controller {

	import config.GeneralCommands;

	import flash.display.Sprite;
	import flash.ui.Keyboard;

	import org.puremvc.as3.interfaces.INotification;
	import org.puremvc.as3.patterns.command.SimpleCommand;

	import playtika.ua.vn.model.proxies.KeyboardProxy;
	import playtika.ua.vn.model.proxies.ServerProxy;
	import playtika.ua.vn.model.proxies.UserProxy;
	import playtika.ua.vn.view.RootMediator;

	public class StartupCommand extends SimpleCommand {
		override public function execute(notification:INotification):void {

			var mainView:Sprite = notification.getBody() as Sprite;

			facade.registerCommand(GeneralCommands.LOGIN_COMMAND, LoginCommand);
			facade.registerCommand(GeneralCommands.LOGIN_COMPLETE, LoginCompleteCommand);
			facade.registerCommand(GeneralCommands.SERVER_CALL, ServerCallCommand);
			facade.registerCommand(GeneralCommands.STRAT_CHAT, StartChatCommand);
			facade.registerCommand(GeneralCommands.KEYBOARD_CONTROLER, KeyboardControler);
			facade.registerCommand(GeneralCommands.SEND_MESSAGE, SendMessageCommand);

			facade.registerProxy(new ServerProxy());
			facade.registerProxy(new KeyboardProxy(mainView));
			facade.registerProxy(new UserProxy());

			facade.registerMediator(new RootMediator(mainView));

			sendNotification(GeneralCommands.LOGIN_COMMAND);
		}
	}
}
