package playtika.vn.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import playtika.vn.core.database.UsersDB;
import playtika.vn.core.user.User;

// убрать циклы for each

public class UserService {

    private volatile static UserService instance = null;
    private ArrayList<User> userList;
    private UsersDB usersDB;
    private final Logger logger = Logger.getLogger(this.getClass());

    private UserService() {
	usersDB = new UsersDB();
	userList = new ArrayList<>();
	createTestUser();
    }

    private void createTestUser() {
	logger.debug(String.format("UserService : createTestUser()"));
	User testUser = new User("testUser");
	userList.add(testUser);
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
	logger.debug(String.format("UserService : loginUser()"));
	usersDB.addRow(login, pass);
	if (!login.equals("testUser")) {
	    for (User user : userList) {
		if (user.getName().equals(login)) {
		    return;
		}
	    }
	    userList.add(new User(login));
	}
    }

    public void sendMessage(String fromUser, String message, String toUser) {
	logger.debug(String.format("UserService : sendMessage()"));
	for (User user : userList) {
	    if (user.getName().equals(toUser)) {
		user.sendMessage(fromUser, message);
	    }
	}
    }

    public String getMessages(String toUser) {
	logger.debug(String.format("UserService : getMessages()"));
	String msg = "";
	for (User user : userList) {
	    if (user.getName().equals(toUser)) {
		msg = user.getMessage();
	    }
	}
	return msg;
    }

    public List<String> getUserList() {
	List<String> list = new ArrayList<String>();
	for (User user : userList) {
	    list.add(user.getName());
	}
	return list;
    }

}
