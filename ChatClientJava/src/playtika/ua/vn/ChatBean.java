package playtika.ua.vn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@SessionScoped
public class ChatBean implements Serializable {

    private static final long serialVersionUID = -4182842073251002567L;
    private List<SelectItem> users;
    private String allMessages;

    public ChatBean() {
	users = new ArrayList<SelectItem>();
	users.add(new SelectItem(new User("1"), "Tom"));
	users.add(new SelectItem(new User("1"), "Jerry"));
	users.add(new SelectItem(new User("1"), "Cat"));
	users.add(new SelectItem(new User("1"), "Dog"));
	setAllMessages("all messages \nme \nme1 \nme2 \nme3 \nme4 \nme5 \nme6 \nme7");
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
}
