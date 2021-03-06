package vn.common.server;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import vn.common.Response;
import vn.common.api.ICommand;
import vn.common.config.GeneralCommand;
import vn.common.server.command.GetCommand;
import vn.common.server.command.LoginCommand;
import vn.common.server.command.SendCommand;

public class ServerService {
    private Map<String, ICommand> commands = new HashMap<String, ICommand>();

    public ServerService() {
	commands.put(GeneralCommand.LOGIN, new LoginCommand());
	commands.put(GeneralCommand.SEND_MESSAGE, new SendCommand());
	commands.put(GeneralCommand.GET_MESSAGE, new GetCommand());
    }

    public Response executeCommand(String command, HttpServletRequest req) {
	Response response = new Response();
	ICommand<Map<String, Object>> commandInstance = commands.get(command);
	Map<String, Object> requestMap = extractedRequest(req);
	response = commandInstance.execute(command, requestMap);
	return response;
    }

    private Map<String, Object> extractedRequest(HttpServletRequest req) {
	return (HashMap<String, Object>) req.getParameterMap();
    }
}
