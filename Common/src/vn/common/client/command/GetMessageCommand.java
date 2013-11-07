package vn.common.client.command;

import java.util.HashMap;

import org.apache.http.message.BasicNameValuePair;

import vn.common.Response;
import vn.common.config.GeneralCommand;

public class GetMessageCommand extends Command<HashMap<String, String>> {
    @Override
    public Response execute(String command, HashMap<String, String> params) {
	super.execute(command, params);
	data.add(new BasicNameValuePair("toUser", variables.get("toUser")));
	response = chatService.executeCommand(GeneralCommand.SERVER_CALL, data);
	return response;
    }
}
