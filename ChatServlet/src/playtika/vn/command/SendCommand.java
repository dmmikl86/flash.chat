package playtika.vn.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import playtika.vn.core.server.Response;

public class SendCommand extends Command {
    private String message;
    private String fromUser;
    private String toUser;
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public Response execute(String command, HttpServletRequest req) {
	logger.debug(String.format("execute SendCommand"));
	super.execute(command, req);

	message = req.getParameter("message");
	fromUser = req.getParameter("fromUser");
	toUser = req.getParameter("toUser");

	userService.sendMessage(fromUser, message, toUser);
	result.command = command;
	return result;
    }
}
