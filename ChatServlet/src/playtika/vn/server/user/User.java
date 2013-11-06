package playtika.vn.server.user;

import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
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
	LOGGER.debug("User : sendMessage() : fromUser = {}, mesg = {}", fromUser, mesg);
	messages.put(fromUser, mesg);
    }

    public String getMessage() {
	String allMsg = "";
	for (Entry<String, String> message : messages.entrySet()) {

	    allMsg = allMsg.concat(message.getKey() + ": " + message.getValue() + "\n");
	}
	messages.clear();
	LOGGER.debug("User : getMessage() : allMsg = {}", allMsg);
	return allMsg;
    }
}
