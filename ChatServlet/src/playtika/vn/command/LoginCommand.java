package playtika.vn.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import playtika.vn.command.core.Command;
import playtika.vn.core.server.Response;

public class LoginCommand extends Command {
    private String login;
    private String pass;
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public Response execute(String command, HttpServletRequest req) {
	logger.debug(String.format("execute LoginCommand"));
	super.execute(command, req);

	login = req.getParameter("login");
	pass = req.getParameter("password");

	userService.loginUser(login, pass);
	result.command = command;
	return result;
    }
}
