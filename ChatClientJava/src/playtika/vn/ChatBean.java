package playtika.vn;

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

import playtika.vn.config.GeneralCommand;

@ManagedBean
@SessionScoped
public class ChatBean implements Serializable {

    private static final long serialVersionUID = -4182842073251002567L;
    private List<SelectItem> users;
    private HtmlOutputText currentUser;
    private String allMessages;
    private String message;
    private String toUser;

    public ChatBean() {
	users = new ArrayList<SelectItem>();
	users.add(new SelectItem(new User("1Tom"), "Tom"));
	users.add(new SelectItem(new User("2Jerry"), "Jerry"));
	users.add(new SelectItem(new User("3Cat"), "Cat"));
	users.add(new SelectItem(new User("4Dog"), "Dog"));
	setAllMessages("Welcome to JAVA_CHAT");
	UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
	currentUser = (HtmlOutputText) viewRoot.findComponent("currentUser");
    }

    public void sendMessage() {
	Map<String, String> params = new HashMap<String, String>();
	params.put("fromUser", getCurrentUser());
	params.put("toUser", getToUser());
	params.put("message", getMessage());

	ChatService.getInstance().executeCommand(GeneralCommand.SERVER_CALL, params);

	setAllMessages(">>> from User: ("+getCurrentUser()+") --- to User (" + toUser + ") : " + message + "\n" + allMessages);
	setMessage("");
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
