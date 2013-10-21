package playtika.vn;

import java.util.HashMap;
import java.util.Map;

import playtika.vn.command.LoginCommand;
import playtika.vn.command.SendMessageCommand;
import playtika.vn.command.ServerCallCommand;
import playtika.vn.command.api.ICommand;
import playtika.vn.config.GeneralCommand;

public class ChatService {
    private Map<String, ICommand> commandsMap = new HashMap<String, ICommand>();

    public ChatService() {
	commandsMap.put(GeneralCommand.LOGIN, new LoginCommand());
	commandsMap.put(GeneralCommand.SEND_MESSAGE, new SendMessageCommand());
	commandsMap.put(GeneralCommand.SERVER_CALL, new ServerCallCommand());
    }

    public Response executeCommand(String command, Object params) {
	Response response = new Response();
	ICommand commandInstance = commandsMap.get(command);
	response = commandInstance.execute(command, params);
	return response;
    }
}
