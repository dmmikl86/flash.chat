package vn.common.client;

import java.util.HashMap;
import java.util.Map;

import vn.common.Response;
import vn.common.api.ICommand;
import vn.common.client.command.GetMessageCommand;
import vn.common.client.command.LoginCommand;
import vn.common.client.command.SendMessageCommand;
import vn.common.client.command.ServerCallCommand;
import vn.common.config.GeneralCommand;

public class ClientService {
    private Map<String, ICommand> commandsMap = new HashMap<String, ICommand>();

    public ClientService() {
	commandsMap.put(GeneralCommand.LOGIN, new LoginCommand());
	commandsMap.put(GeneralCommand.SEND_MESSAGE, new SendMessageCommand());
	commandsMap.put(GeneralCommand.SERVER_CALL, new ServerCallCommand());
	commandsMap.put(GeneralCommand.GET_MESSAGE, new GetMessageCommand());
    }

    public <T> Response executeCommand(String command, T params) {
	Response response = new Response();
	ICommand<T> commandInstance = commandsMap.get(command);
	response = commandInstance.execute(command, params);
	return response;
    }
}
