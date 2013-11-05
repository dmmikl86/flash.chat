package vn.playtika.command;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.playtika.server.Response;

public class LoginCommand extends Command {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public Response execute(String command, Map<String, Object> req) {
	LOGGER.debug("execute LoginCommand");
	super.execute(command, req);

	String [] login = (String[]) req.get("login");
	String [] pass = (String[]) req.get("password");

	userService.loginUser(login[0], pass[0]);
	result.command = command;
	return result;
    }
}
