package playtika.ua.vn.controller {

	import config.GeneralNotification;

	import org.puremvc.as3.interfaces.INotification;
	import org.puremvc.as3.patterns.command.SimpleCommand;

	public class ShowMessageCommand extends SimpleCommand {
		override public function execute(notification:INotification):void {

			sendNotification(GeneralNotification.RECEIVE_MESSAGE, notification.getBody());
		}
	}
}
