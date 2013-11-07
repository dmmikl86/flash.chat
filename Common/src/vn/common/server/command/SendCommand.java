package vn.common.server.command;

import java.util.Map;

import vn.common.Response;

public class SendCommand extends Command {

    @Override
    public Response execute(String command, Map<String, Object> req) {
	super.execute(command, req);

	String [] message = (String[]) req.get("message");
	String []  fromUser = (String[]) req.get("fromUser");
	String [] toUser = (String[]) req.get("toUser");

	userService.sendMessage(fromUser[0], toUser[0], message[0]);
	result.command = command;
	return result;
    }
}
