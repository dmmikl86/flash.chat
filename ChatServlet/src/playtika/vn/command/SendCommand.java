package playtika.vn.command;

import java.util.HashMap;

import org.apache.log4j.Logger;

import playtika.vn.server.Response;

public class SendCommand extends Command {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public Response execute(String command, HashMap<String, Object> req) {
	logger.debug(String.format("execute SendCommand"));
	super.execute(command, req);

	String [] message = (String[]) req.get("message");
	String []  fromUser = (String[]) req.get("fromUser");
	String [] toUser = (String[]) req.get("toUser");

	userService.sendMessage(fromUser[0], toUser[0], message[0]);
	result.command = command;
	return result;
    }
}
