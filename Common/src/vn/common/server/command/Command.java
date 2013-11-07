package vn.common.server.command;

import vn.common.Response;
import vn.common.api.ICommand;
import vn.common.server.user.UserService;

public class Command<T> implements ICommand<T> {
    protected Response result = new Response();
    protected UserService userService = UserService.getInstance();
    protected T req;

    @Override
    public Response execute(String command, T req) {
	this.req = req;
	return result;
    }
}
