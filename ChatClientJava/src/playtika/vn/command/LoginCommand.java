package playtika.vn.command;

import org.apache.http.message.BasicNameValuePair;

import playtika.vn.client.Response;
import playtika.vn.config.GeneralCommand;

public class LoginCommand extends Command {
    @Override
    public Response execute(String command, Object params) {
	super.execute(command, params);
	
	data.add(new BasicNameValuePair("login", variables.get("login")));
	data.add(new BasicNameValuePair("password", variables.get("password")));
	
	response = chatService.executeCommand(GeneralCommand.SERVER_CALL, data);
	return response;
    }
}
