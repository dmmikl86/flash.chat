package playtika.ua.vn.model.proxies {

	import org.puremvc.as3.patterns.proxy.Proxy;

	import playtika.ua.vn.model.VO.ServerVO;

	public class ServerProxy extends Proxy {
		public static const NAME:String = "SocketProxy";

		public function ServerProxy() {

			super(NAME, new ServerVO());
		}

		public function get server():ServerVO {

			return data as ServerVO;
		}
	}
}
