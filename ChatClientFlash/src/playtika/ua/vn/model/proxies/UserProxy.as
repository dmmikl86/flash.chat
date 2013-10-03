package playtika.ua.vn.model.proxies {

	import org.puremvc.as3.patterns.proxy.Proxy;

	import playtika.ua.vn.model.VO.UserVO;

	public class UserProxy extends Proxy {
		public static const NAME:String = "UserProxy";

		public function UserProxy() {

			super(NAME, new UserVO());
		}

		public function get user():UserVO {

			return data as UserVO;
		}
	}
}
