package playtika.vn.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import playtika.vn.client.ChatService;
import playtika.vn.client.Response;
import playtika.vn.config.GeneralCommand;
import playtika.vn.model.Proxy;

public class ChatTimerTask extends TimerTask {
    private ChatService chatService;
    private String user;

    public ChatTimerTask(String user) {
	this.user = user;
	chatService = new ChatService();
    }

    @Override
    public void run() {
	Map<String, String> params = new HashMap<String, String>();
	params.put("toUser", user);
	Response response = chatService.executeCommand(GeneralCommand.GET_MESSAGE, params);
	Proxy.getInstance().setData(response);
    }
}
