package playtika.ua.vn.model.VO {

	public class UserVO {
		private var _name:String;

		public function UserVO() {
		}

		public function get name():String {

			return _name;
		}

		public function set name(value:String):void {

			_name = value;
		}

	}
}
