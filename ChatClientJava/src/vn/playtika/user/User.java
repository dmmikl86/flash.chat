package vn.playtika.user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class User {
    private String name;

    @Override
    public String toString() {
	return getName();
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public User(String name) {
	this.name = name;
    }
}
