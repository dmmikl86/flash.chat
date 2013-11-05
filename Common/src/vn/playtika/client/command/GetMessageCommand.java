package vn.playtika.client.command;

import org.apache.http.message.BasicNameValuePair;

import vn.playtika.Response;
import vn.playtika.client.config.GeneralCommand;

public class GetMessageCommand extends Command {
    @Override
    public Response execute(String command, Object params) {
	super.execute(command, params);

	data.add(new BasicNameValuePair("toUser", variables.get("toUser")));

	response = chatService.executeCommand(GeneralCommand.SERVER_CALL, data);
	return response;
    }
}
