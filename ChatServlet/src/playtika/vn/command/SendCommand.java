package playtika.vn.command;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import playtika.vn.server.Response;

public class SendCommand extends Command {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public Response execute(String command, Map<String, Object> req) {
	LOGGER.debug("execute SendCommand");
	super.execute(command, req);

	String [] message = (String[]) req.get("message");
	String []  fromUser = (String[]) req.get("fromUser");
	String [] toUser = (String[]) req.get("toUser");

	userService.sendMessage(fromUser[0], toUser[0], message[0]);
	result.command = command;
	return result;
    }
}
