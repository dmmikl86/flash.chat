package vn.common.server.command;

import java.util.HashMap;
import java.util.Map;

import vn.common.Response;
import vn.common.server.command.api.ICommand;
import vn.common.server.user.UserService;

public class Command implements ICommand {
    protected Response result = new Response();
    protected UserService userService = UserService.getInstance();
    protected HashMap<String, Object> req;

    @Override
    public Response execute(String command, Map<String, Object> req) {
	this.req = (HashMap<String, Object>) req;
	return result;
    }
}
