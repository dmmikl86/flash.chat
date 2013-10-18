package playtika.vn.command;

import org.apache.http.message.BasicNameValuePair;

import playtika.vn.ChatService;
import playtika.vn.Response;
import playtika.vn.config.GeneralCommand;

public class SendMessageCommand extends Command {

    @Override
    public Response execute(String command, Object params) {
	super.execute(command, params);
	
	data.add(new BasicNameValuePair("message", variables.get("message")));
	data.add(new BasicNameValuePair("toUser", variables.get("toUser")));
	data.add(new BasicNameValuePair("fromUser", variables.get("fromUser")));
	
	response = ChatService.getInstance().executeCommand(GeneralCommand.SERVER_CALL, data);
	return response;
    }
}
