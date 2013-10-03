package playtika.vn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import playtika.vn.command.GetCommand;
import playtika.vn.command.LoginCommand;
import playtika.vn.command.SendCommand;
import playtika.vn.command.api.ICommand;
import playtika.vn.config.GeneralCommand;
import playtika.vn.server.Response;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class ChatServlet extends HttpServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(ChatServlet.class);
    private HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    @Override
    public void init() {
	LOGGER.debug("Commands registration");
	
	commands.put(GeneralCommand.LOGIN, new LoginCommand());
	commands.put(GeneralCommand.SEND, new SendCommand());
	commands.put(GeneralCommand.GET, new GetCommand());
	
	LOGGER.debug("Commands ware registered");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	PrintWriter out = resp.getWriter();
	LOGGER.debug("doGet");
	out.println("Hello, World! TEST_MESSAGE!");
	out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String command = req.getParameter(GeneralCommand.COMMAND);
	LOGGER.debug("doPost() command {} ", command);
	
	Response result = executeCommand(command, req);
	String json = converteResponseToJson(result);
	sendResponseToClient(resp, json);
    }

    private void sendResponseToClient(HttpServletResponse resp, String json) throws IOException {
	PrintWriter out = resp.getWriter();
	out.println(json);
	out.close();
    }

    private String converteResponseToJson(Response result) {
	Gson gson = new Gson();
	String json = gson.toJson(result);
	LOGGER.debug("converteResponseToJson : {}", json);
	return json;
    }

    private Response executeCommand(String command, HttpServletRequest req) {
	LOGGER.debug("start executeCommand() : {}", command);
	ICommand commandInstance = commands.get(command);
	HashMap<String, Object> requestMap =extractedRequest(req);
	Response response = commandInstance.execute(command, requestMap);
	LOGGER.debug("finish executeCommand() : {}", response.toString());
	return response;
    }

    private HashMap<String, Object> extractedRequest(HttpServletRequest req) {
	return (HashMap<String, Object>) req.getParameterMap();
    }
}