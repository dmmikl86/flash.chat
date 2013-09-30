package playtika.ua.vn.controller {

	import config.GeneralCommands;
	import config.GeneralNotification;

	import flash.net.URLVariables;

	import org.puremvc.as3.interfaces.INotification;
	import org.puremvc.as3.patterns.command.SimpleCommand;

	import playtika.ua.vn.model.proxies.UserProxy;

	public class SendMessageCommand extends SimpleCommand {
		override public function execute(notification:INotification):void {

			var variables:URLVariables = new URLVariables();
			variables.command = GeneralNotification.SEND.toString();
			variables.message = notification.getBody()["message"].toString();
			variables.fromUser = userProxy.user.name;
			variables.toUser = notification.getBody()["sendTo"].toString();;

			sendNotification(GeneralCommands.SERVER_CALL, variables);
		}

		protected function get userProxy():UserProxy {

			return facade.retrieveProxy(UserProxy.NAME) as UserProxy;
		}
	}
}
