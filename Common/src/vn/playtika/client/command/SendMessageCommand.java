package vn.playtika.client.command;

import org.apache.http.message.BasicNameValuePair;

import vn.playtika.Response;
import vn.playtika.client.config.GeneralCommand;

public class SendMessageCommand extends Command {

    @Override
    public Response execute(String command, Object params) {
	super.execute(command, params);

	data.add(new BasicNameValuePair("message", variables.get("message")));
	data.add(new BasicNameValuePair("toUser", variables.get("toUser")));
	data.add(new BasicNameValuePair("fromUser", variables.get("fromUser")));

	response = chatService.executeCommand(GeneralCommand.SERVER_CALL, data);
	return response;
    }
}
