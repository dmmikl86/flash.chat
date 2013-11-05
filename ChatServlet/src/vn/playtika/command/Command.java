package vn.playtika.command;

import java.util.HashMap;
import java.util.Map;

import vn.playtika.command.api.ICommand;
import vn.playtika.server.Response;
import vn.playtika.server.UserService;

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
