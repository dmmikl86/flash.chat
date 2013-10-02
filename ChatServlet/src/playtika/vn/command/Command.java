package playtika.vn.command;

import javax.servlet.http.HttpServletRequest;

import playtika.vn.command.api.ICommand;
import playtika.vn.server.Response;
import playtika.vn.server.UserService;

public class Command implements ICommand {
    protected Response result = new Response();;
    protected UserService userService = UserService.getInstance();
    protected HttpServletRequest req;

    @Override
    public Response execute(String command, HttpServletRequest req) {
	this.req = req;
	return result;
    }

}
