package playtika.vn.client.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import playtika.vn.client.ChatService;
import playtika.vn.client.Response;
import playtika.vn.client.user.User;
import playtika.vn.config.GeneralCommand;
import playtika.vn.model.IObserver;
import playtika.vn.model.Proxy;
import playtika.vn.util.ChatTimerTask;

@ManagedBean
@SessionScoped
public class ChatBean implements Serializable, IObserver {

    private static final long serialVersionUID = -4182842073251002567L;
    private List<SelectItem> users;
    private HtmlOutputText currentUser;
    private String allMessages;
    private String message;
    private String toUser;
    private ChatService chatService;

    public ChatBean() {
	chatService = new ChatService();

	setAllMessages("Welcome to JAVA_CHAT");
	UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
	currentUser = (HtmlOutputText) viewRoot.findComponent("currentUser");

	users = new ArrayList<SelectItem>();
	users.add(new SelectItem(new User(getCurrentUser()), getCurrentUser()));

	Timer timer = new Timer();
	ChatTimerTask timerTask = new ChatTimerTask(getCurrentUser());
	timer.schedule(timerTask, 1000, 2000);

	Proxy.getInstance().registerObserver(this);
    }

    public void sendMessage() {
	Map<String, String> params = new HashMap<String, String>();
	params.put("fromUser", getCurrentUser());
	params.put("toUser", getToUser());
	params.put("message", getMessage());

	chatService.executeCommand(GeneralCommand.SEND_MESSAGE, params);

	setAllMessages(">>> from User: (" + getCurrentUser() + ") --- to User (" + toUser + ") : " + message + "\n" + allMessages);
	setMessage("");
    }

    @Override
    public void update(Response data) {
	List<SelectItem> allUsers = new ArrayList<SelectItem>();
	for (String user : data.list) {
	    allUsers.add(new SelectItem(new User(user), user));
	}
	setUsers(allUsers);
	setAllMessages(data.messages + allMessages);
	refreshPage();
    }

    private void refreshPage() {
	FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        ViewHandler handler = context.getApplication().getViewHandler();
        UIViewRoot root = handler.createView(context, viewId);
        root.setViewId(viewId);
        context.setViewRoot(root);
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
