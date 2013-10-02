package playtika.vn.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import playtika.vn.command.GetCommand;
import playtika.vn.command.LoginCommand;
import playtika.vn.command.SendCommand;
import playtika.vn.command.api.ICommand;
import playtika.vn.config.GeneralCommand;
import playtika.vn.core.server.Response;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class ChatServlet extends HttpServlet {

    // private String login;
    // private String pass;
    // private String message;
    // private String fromUser;
    // private String toUser;
    private final Logger logger = Logger.getLogger(this.getClass());
    private String json;
    private HashMap<String, ICommand> commands = new HashMap<String, ICommand>();
    private static volatile boolean isCommandsRegisted = false;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	PrintWriter out = resp.getWriter();
	logger.debug(String.format("doGet"));
	out.println("Hello, World! TEST_MESSAGE!");
	out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	registerCommands();
	String command = req.getParameter(GeneralCommand.COMMAND);
	logger.debug(String.format("doPost() command = %s", command));
	Response result = new Response();
	Gson gson = new Gson();
	
	logger.debug(String.format("starting execute command : %s", command));
	result = executeCommand(command, req);
	json = gson.toJson(result);
	logger.debug(String.format("write response : %s", json));
	
	PrintWriter out = resp.getWriter();
	out.println(json);
	out.close();
    }

    private Response executeCommand(String command, HttpServletRequest req) {
	Response response = null;
	logger.debug(String.format("start executeCommand() : %s", command));
	ICommand commandInstance = commands.get(command);
	response = commandInstance.execute(command, req);
	logger.debug(String.format("finish executeCommand() : %s", response.toString()));
	return response;
    }

    private void registerCommands() {
//	 logger.warn(String.format("Something went wrong, param1 = %s, param2 = %s", param1, param2));

	if (isCommandsRegisted == false) {
	    logger.debug(String.format("Commands registeration"));
	    commands.put(GeneralCommand.LOGIN, new LoginCommand());
	    commands.put(GeneralCommand.SEND, new SendCommand());
	    commands.put(GeneralCommand.GET, new GetCommand());
	    isCommandsRegisted = true;
	    logger.debug(String.format("Commands was registered"));
	}
    }
}