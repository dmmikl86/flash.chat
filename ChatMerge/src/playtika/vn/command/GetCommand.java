package playtika.vn.command;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import playtika.vn.server.Response;

public class GetCommand extends Command {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public Response execute(String command, Map<String, Object> req) {
	LOGGER.debug("execute GetCommand");
	super.execute(command, req);

	String [] toUser = (String[]) req.get("toUser");
	
	result.command = command;
	result.messages = userService.getMessages(toUser[0]);
	result.list = userService.getUserList();
	return result;
    }

}
