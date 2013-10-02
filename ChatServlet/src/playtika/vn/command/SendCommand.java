package playtika.vn.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import playtika.vn.server.Response;

public class SendCommand extends Command {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public Response execute(String command, HttpServletRequest req) {
	logger.debug(String.format("execute SendCommand"));
	super.execute(command, req);

	String message = req.getParameter("message");
	String fromUser = req.getParameter("fromUser");
	String toUser = req.getParameter("toUser");

	userService.sendMessage(fromUser, toUser, message);
	result.command = command;
	return result;
    }
}
