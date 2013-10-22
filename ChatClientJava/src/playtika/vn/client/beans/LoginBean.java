package playtika.vn.client.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import playtika.vn.client.ChatService;
import playtika.vn.config.GeneralCommand;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String pass;
    private ChatService chatService;
    
    public LoginBean(){
	chatService = new ChatService();
    }
    
    public String doLogin(){
	Map<String, String> params = new HashMap<String, String>();
	params.put("login", getName());
	params.put("password", getPass());

	chatService.executeCommand(GeneralCommand.LOGIN, params);
	return "chat";
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
}