package vn.common.client.command;

import java.util.Map;

import org.apache.http.message.BasicNameValuePair;

import vn.common.Response;
import vn.common.config.GeneralCommand;

public class SendMessageCommand extends Command<Map<String, String>> {

    @Override
    public Response execute(String command, Map<String, String> params) {
	super.execute(command, params);

	data.add(new BasicNameValuePair("message", variables.get("message")));
	data.add(new BasicNameValuePair("toUser", variables.get("toUser")));
	data.add(new BasicNameValuePair("fromUser", variables.get("fromUser")));

	response = chatService.executeCommand(GeneralCommand.SERVER_CALL, data);
	return response;
    }
}
