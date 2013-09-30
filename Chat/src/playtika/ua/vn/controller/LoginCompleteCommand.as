package playtika.ua.vn.controller {

	import config.GeneralCommands;
	import config.GeneralNotification;

	import flash.net.URLVariables;

	import org.puremvc.as3.interfaces.INotification;
	import org.puremvc.as3.patterns.command.SimpleCommand;

	import playtika.ua.vn.model.proxies.UserProxy;
	import playtika.ua.vn.view.LoginMediator;
	import playtika.ua.vn.view.PanelMediator;

	public class LoginCompleteCommand extends SimpleCommand {
		override public function execute(notification:INotification):void {

			var variables:URLVariables = new URLVariables();
			variables.command = GeneralNotification.LOGIN.toString();
			variables.login = notification.getBody()['userName'].toString();
			variables.password = notification.getBody()['password'].toString();

			userProxy.user.name = notification.getBody()['userName'].toString();

			sendNotification(GeneralCommands.SERVER_CALL, variables);
		}

		protected function get userProxy():UserProxy {

			return facade.retrieveProxy(UserProxy.NAME) as UserProxy;
		}
	}
}
