package playtika.vn.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import playtika.vn.server.datebase.UsersDB;
import playtika.vn.server.user.User;

public class UserService {

    private volatile static UserService instance = null;
    private HashMap<String, User> userMap;
    private UsersDB usersDB;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private UserService() {
	usersDB = new UsersDB();
	userMap = new HashMap<>();
	createTestUser();
    }

    private void createTestUser() {
	LOGGER.debug("UserService : createTestUser()");
	User testUser = new User("testUser");
	userMap.put("testUser", testUser);
    }

    public static UserService getInstance() {
	if (instance == null) {
	    synchronized (UserService.class) {
		if (instance == null)
		    instance = new UserService();
	    }
	}
	return instance;
    }

    public void loginUser(String login, String pass) {
	LOGGER.debug("UserService : loginUser()");
	usersDB.addRow(login, pass);
	if (!login.equals("testUser")) {
	    if (userMap.get(login) != null) {
		return;
	    }
	    userMap.put(login, new User(login));
	}
    }

    public void sendMessage(String fromUser, String toUser, String message) {
	LOGGER.debug("UserService : sendMessage()");
	if (userMap.get(toUser) != null) {
	    userMap.get(toUser).addMessage(fromUser, message);
	}
    }

    public String getMessages(String toUser) {
	LOGGER.debug("UserService : getMessages()");
	String msg = "";
	if (userMap.get(toUser) != null) {
	    msg = userMap.get(toUser).getMessage();
	}
	return msg;
    }

    public List<String> getUserList() {
	List<String> list = new ArrayList<String>();
	for (String key : userMap.keySet())
	    list.add(key);
	return list;
    }

}
