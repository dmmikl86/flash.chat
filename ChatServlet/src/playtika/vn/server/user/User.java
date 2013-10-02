package playtika.vn.server.user;

import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.log4j.Logger;

public class User {
    private final Logger logger = Logger.getLogger(this.getClass());
    private String name;

    public String getName() {
	return name;
    }

    private TreeMap<String, String> messages = new TreeMap<String, String>();

    public User(String name) {
	this.name = name;
    }

    public void addMessage(String fromUser, String mesg) {
	if (messages.get(fromUser) != null) {
	    mesg = messages.get(fromUser).concat(" | " + mesg);
	}
	logger.debug(String.format("User : sendMessage() : fromUser = %s, mesg = %s", fromUser, mesg));
	messages.put(fromUser, mesg);
    }

    public String getMessage() {
	String allMsg = "";
	for (Entry<String, String> message : messages.entrySet()) {

	    allMsg = allMsg.concat(message.getKey() + ": " + message.getValue() + "\n");
	}
	messages.clear();
	logger.debug(String.format("User : getMessage() : allMsg = %s", allMsg));
	return allMsg;
    }
}
