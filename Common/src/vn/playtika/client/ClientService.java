package vn.playtika.client;

import java.util.HashMap;
import java.util.Map;

import vn.playtika.Response;
import vn.playtika.client.command.GetMessageCommand;
import vn.playtika.client.command.LoginCommand;
import vn.playtika.client.command.SendMessageCommand;
import vn.playtika.client.command.ServerCallCommand;
import vn.playtika.client.command.api.ICommand;
import vn.playtika.client.config.GeneralCommand;

public class ClientService {
    private Map<String, ICommand> commandsMap = new HashMap<String, ICommand>();

    public ClientService() {
	commandsMap.put(GeneralCommand.LOGIN, new LoginCommand());
	commandsMap.put(GeneralCommand.SEND_MESSAGE, new SendMessageCommand());
	commandsMap.put(GeneralCommand.SERVER_CALL, new ServerCallCommand());
	commandsMap.put(GeneralCommand.GET_MESSAGE, new GetMessageCommand());
    }

    public Response executeCommand(String command, Object params) {
	Response response = new Response();
	ICommand commandInstance = commandsMap.get(command);
	response = commandInstance.execute(command, params);
	return response;
    }
}
