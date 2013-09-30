package playtika.ua.vn.controller {

	import org.puremvc.as3.interfaces.INotification;
	import org.puremvc.as3.patterns.command.SimpleCommand;

	import playtika.ua.vn.view.LoginMediator;

	public class LoginCommand extends SimpleCommand {
		override public function execute(notification:INotification):void {

			facade.registerMediator(new LoginMediator());
		}
	}
}
