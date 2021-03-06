package vn.common.server.command;

import java.util.Map;

import vn.common.Response;

public class LoginCommand extends Command<Map<String, Object>> {

    @Override
    public Response execute(String command, Map<String, Object> req) {
	super.execute(command, req);

	String [] login = (String[]) req.get("login");
	String [] pass = (String[]) req.get("password");

	userService.loginUser(login[0], pass[0]);
	result.command = command;
	return result;
    }
}
