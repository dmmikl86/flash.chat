package playtika.ua.vn.controller {

	import config.GeneralNotification;

	import org.puremvc.as3.interfaces.INotification;
	import org.puremvc.as3.patterns.command.SimpleCommand;

	public class KeyboardControler extends SimpleCommand {
		override public function execute(notification:INotification):void {

			sendNotification(GeneralNotification.KEY_PRESSED, notification.getBody());
		}
	}
}
