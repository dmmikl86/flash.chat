package playtika.vn;

import java.util.ArrayList;
import java.util.List;

public class Response {

    public String command = "";
    public String messages = "";
    public List<String> list = new ArrayList<String>();

    @Override
    public String toString() {
	return "Response [command=" + command + ", messages=" + messages + ", list=" + list + "]";
    }

}
