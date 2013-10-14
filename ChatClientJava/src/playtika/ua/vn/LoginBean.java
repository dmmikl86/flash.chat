package playtika.ua.vn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String pass;
    private List<SelectItem> users;

    public LoginBean() {
	users = new ArrayList<SelectItem>();
	users.add(new SelectItem(new User("1"),"2"));
	users.add(new SelectItem(new User("1"),"3"));
	users.add(new SelectItem(new User("1"),"4"));
	users.add(new SelectItem(new User("1"),"5"));
    }

    public String getPass() {
	return pass;
    }

    public void setPass(String pass) {
	this.pass = pass;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<SelectItem> getUsers() {
	return users;
    }

    public void setUsers(List<SelectItem> users) {
	this.users = users;
    }

}