package playtika.ua.vn.model.VO {

	import config.GeneralPath;

	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.net.URLRequestMethod;
	import flash.net.URLVariables;

	import utils.EventTrans;

	public class ServerVO extends EventDispatcher {
		public var server:URLLoader;
		private var request:URLRequest;
		private var path:String;

		public function ServerVO() {

			loadXML();
			server = new URLLoader();

		}

		private function loadXML():void {

			var loader:URLLoader;
			loader = new URLLoader();
			loader.addEventListener(Event.COMPLETE, xmlLoaded);
			var request:URLRequest = new URLRequest("ServerPath.txt");
			loader.load(request);
		}

		protected function xmlLoaded(event:Event):void {

			path = event.currentTarget.data;
			request = new URLRequest(path);
		}

		public function sendGet():void {

			request.method = URLRequestMethod.GET;
			server.addEventListener(Event.COMPLETE, onComplete, false, 0, false);
			server.load(request);
		}

		public function sendPost(variables:URLVariables):void {

			request.data = variables;
			request.method = URLRequestMethod.POST;
			server.addEventListener(Event.COMPLETE, onComplete);
			server.load(request);
		}

		protected function onComplete(event:Event):void {

			trace("from server: <----- " + event.target.data);
			dispatchEvent(new EventTrans(Event.COMPLETE, {data:event.target.data, requestMethod:this.request.method}));
		}
	}
}
