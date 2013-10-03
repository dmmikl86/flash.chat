package playtika.vn.command;

import java.util.HashMap;

import org.apache.log4j.Logger;

import playtika.vn.server.Response;

public class LoginCommand extends Command {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public Response execute(String command, HashMap<String, Object> req) {
	logger.debug(String.format("execute LoginCommand"));
	super.execute(command, req);

	String [] login = (String[]) req.get("login");
	String [] pass = (String[]) req.get("password");

	userService.loginUser(login[0], pass[0]);
	result.command = command;
	return result;
    }
}
