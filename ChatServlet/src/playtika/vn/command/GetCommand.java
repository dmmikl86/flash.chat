package playtika.vn.command;

import java.util.HashMap;

import org.apache.log4j.Logger;

import playtika.vn.server.Response;

public class GetCommand extends Command {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public Response execute(String command, HashMap<String, Object> req) {
	logger.debug(String.format("execute GetCommand"));
	super.execute(command, req);

	String [] toUser = (String[]) req.get("toUser");
	
	result.command = command;
	result.messages = userService.getMessages(toUser[0]);
	result.list = userService.getUserList();
	return result;
    }

}
