package playtika.vn.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import playtika.vn.client.ChatService;
import playtika.vn.client.Response;
import playtika.vn.command.api.ICommand;

public class Command implements ICommand {
    protected Response response = new Response();
    protected HashMap<String, String> variables;
    protected List<NameValuePair> data;
    protected ChatService chatService;

    @Override
    public Response execute(String command, Object params) {
	chatService = new ChatService();
	variables = (HashMap<String, String>) params;
	data = new ArrayList<NameValuePair>();
	data.add(new BasicNameValuePair("command", command));
	return response;
    }

}
