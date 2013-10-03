package playtika.ua.vn.controller {

	import com.adobe.serialization.json.JSON;

	import config.GeneralCommands;
	import config.GeneralNotification;

	import flash.events.Event;
	import flash.net.URLVariables;

	import org.puremvc.as3.interfaces.INotification;
	import org.puremvc.as3.patterns.command.SimpleCommand;

	import playtika.ua.vn.model.VO.ServerVO;
	import playtika.ua.vn.model.proxies.ServerProxy;
	import playtika.ua.vn.model.proxies.UserProxy;
	import playtika.ua.vn.view.LoginMediator;

	import utils.EventTrans;

	public class ServerCallCommand extends SimpleCommand {
		override public function execute(notification:INotification):void {

			serverProxy.server.addEventListener(Event.COMPLETE, onComplete, false, 0, false);
			serverProxy.server.sendPost(notification.getBody() as URLVariables);
		}

		protected function onComplete(event:EventTrans):void {

			var data:* = JSON.decode(event.data.data);
			(event.currentTarget as ServerVO).removeEventListener(Event.COMPLETE, onComplete);

			switch(data.command) {
				case GeneralNotification.LOGIN:
					facade.removeMediator(LoginMediator.NAME);
					sendNotification(GeneralCommands.STRAT_CHAT);
					break;
				case GeneralNotification.GET:
					var message:String = data.messages;
					var list:Array = data.list;
					if(message.length != 0) {
						sendNotification(GeneralCommands.SHOW_MESSAGE, message);
					}
					if(list.length > 0) {
						var index:int;

						for(var i:int = 0; i < list.length; i++) {
							if(list[i] == userProxy.user.name) {
								index = i;
								break;
							}
						}
						list.splice(index, 1);
						sendNotification(GeneralNotification.ADD_USER_TO_LIST, list);
					}
					break;
			}
		}

		protected function get serverProxy():ServerProxy {

			return facade.retrieveProxy(ServerProxy.NAME) as ServerProxy;
		}

		protected function get userProxy():UserProxy {

			return facade.retrieveProxy(UserProxy.NAME) as UserProxy;
		}
	}
}
