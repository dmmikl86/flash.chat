package playtika.vn.core.user;

import java.util.Map.Entry;
import java.util.TreeMap;

public class User {

    private String name;

    public String getName() {
	return name;
    }

    private TreeMap<String, String> messages = new TreeMap<String, String>();

    public User(String name) {
	this.name = name;
    }

    public void sendMessage(String fromUser, String mesg) {

	if (messages.get(fromUser) != null) {
	    mesg = messages.get(fromUser).concat("\n" + mesg);
	}
	messages.put(fromUser, mesg);
    }

    public String getMessage() {
	String allMsg = "";
	for (Entry<String, String> message : messages.entrySet()) {

	    allMsg = allMsg.concat(message.getKey() + ": " + message.getValue() + "\n");
	}
	messages.clear();
	return allMsg;
    }
}
