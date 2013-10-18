package playtika.vn;

import java.util.HashMap;
import java.util.Map;

import playtika.vn.command.SendMessageCommand;
import playtika.vn.command.ServerCallCommand;
import playtika.vn.command.ShowMessageCommand;
import playtika.vn.command.api.ICommand;
import playtika.vn.config.GeneralCommand;

public class ChatService {
    private volatile static ChatService instance = null;
    private static Map<String, ICommand> commandsMap = new HashMap<String, ICommand>();

    private ChatService() {
	commandsMap.put(GeneralCommand.SHOW_MESSAGE, new ShowMessageCommand());
	commandsMap.put(GeneralCommand.SEND_MESSAGE, new SendMessageCommand());
	commandsMap.put(GeneralCommand.SERVER_CALL, new ServerCallCommand());
    }

    public static ChatService getInstance() {
	if (instance == null) {
	    synchronized (ChatService.class) {
		if (instance == null)
		    instance = new ChatService();
	    }
	}
	return instance;
    }

    public Response executeCommand(String command, Object params) {
	Response response = new Response();
	ICommand commandInstance = commandsMap.get(command);
	response = commandInstance.execute(command, params);
	return response;
    }
}
