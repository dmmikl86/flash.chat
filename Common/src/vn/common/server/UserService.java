package vn.common.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserService {

    private volatile static UserService instance = null;
    private HashMap<String, User> userMap;
    private UsersDB usersDB;

    private UserService() {
	usersDB = new UsersDB();
	userMap = new HashMap<>();
	createTestUser();
    }

    private void createTestUser() {
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
	usersDB.addRow(login, pass);
	if (!login.equals("testUser")) {
	    if (userMap.get(login) != null) {
		return;
	    }
	    userMap.put(login, new User(login));
	}
    }

    public void sendMessage(String fromUser, String toUser, String message) {
	if (userMap.get(toUser) != null) {
	    userMap.get(toUser).addMessage(fromUser, message);
	}
    }

    public String getMessages(String toUser) {
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
