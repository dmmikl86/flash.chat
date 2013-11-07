package vn.playtika.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import vn.common.Response;
import vn.common.client.ClientService;
import vn.common.config.GeneralCommand;
import vn.playtika.user.User;

@ManagedBean
@SessionScoped
public class ChatBean implements Serializable {

    private static final long serialVersionUID = -4182842073251002567L;
    private List<SelectItem> users;
    private HtmlOutputText currentUser;
    private String allMessages;
    private String message;
    private String toUser;
    private ClientService chatService;

    public ChatBean() {
	chatService = new ClientService();

	setAllMessages("Welcome to JAVA_CHAT");
	UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
	currentUser = (HtmlOutputText) viewRoot.findComponent("currentUser");

	users = new ArrayList<SelectItem>();
	users.add(new SelectItem(new User(getCurrentUser()), getCurrentUser()));
    }

    public void sendMessage() {
	Map<String, String> params = new HashMap<String, String>();
	params.put("fromUser", getCurrentUser());
	params.put("toUser", getToUser());
	params.put("message", getMessage());

	chatService.executeCommand(GeneralCommand.SEND_MESSAGE, params);

	setAllMessages(">>>" + toUser + " : " + message + "\n" + allMessages);
	setMessage("");
    }

    public void handleEvent() {
	Map<String, String> params = new HashMap<String, String>();
	params.put("toUser", getCurrentUser());
	Response response = chatService.executeCommand(GeneralCommand.GET_MESSAGE, params);
	update(response);
    }

    public void update(Response data) {
	List<SelectItem> allUsers = new ArrayList<SelectItem>();
	for (String user : data.list) {
	    allUsers.add(new SelectItem(new User(user), user));
	}
	setUsers(allUsers);
	setAllMessages(data.messages + allMessages);
    }

    public String getCurrentUser() {
	return (String) currentUser.getValue();
    }

    public List<SelectItem> getUsers() {
	return users;
    }

    public void setUsers(List<SelectItem> users) {
	this.users = users;
    }

    public String getAllMessages() {
	return allMessages;
    }

    public void setAllMessages(String allMessages) {
	this.allMessages = allMessages;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public String getToUser() {
	return toUser;
    }

    public void setToUser(String toUser) {
	this.toUser = toUser;
    }
}
