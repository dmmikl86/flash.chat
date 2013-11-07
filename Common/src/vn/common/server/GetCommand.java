package vn.common.server;

import java.util.Map;

import vn.common.Response;

public class GetCommand extends Command {

    @Override
    public Response execute(String command, Map<String, Object> req) {
	super.execute(command, req);

	String[] toUser = (String[]) req.get("toUser");

	result.command = command;
	result.messages = userService.getMessages(toUser[0]);
	result.list = userService.getUserList();
	return result;
    }

}
