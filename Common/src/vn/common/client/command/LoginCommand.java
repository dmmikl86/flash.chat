package vn.common.client.command;

import org.apache.http.message.BasicNameValuePair;

import vn.common.Response;
import vn.common.client.config.GeneralCommand;

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
