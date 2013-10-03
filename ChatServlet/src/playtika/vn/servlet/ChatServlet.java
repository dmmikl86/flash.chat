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
import playtika.vn.server.Response;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class ChatServlet extends HttpServlet {

    private final Logger LOG = Logger.getLogger(this.getClass());
    private HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    @Override
    public void init() {
	LOG.debug(String.format("Commands registration"));
	
	commands.put(GeneralCommand.LOGIN, new LoginCommand());
	commands.put(GeneralCommand.SEND, new SendCommand());
	commands.put(GeneralCommand.GET, new GetCommand());
	
	LOG.debug(String.format("Commands ware registered"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	PrintWriter out = resp.getWriter();
	LOG.debug(String.format("doGet"));
	out.println("Hello, World! TEST_MESSAGE!");
	out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String command = req.getParameter(GeneralCommand.COMMAND);
	LOG.debug(String.format("doPost() command = %s", command));
	
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
	LOG.debug(String.format("converteResponseToJson : %s", json));
	return json;
    }

    private Response executeCommand(String command, HttpServletRequest req) {
	LOG.debug(String.format("start executeCommand() : %s", command));
	ICommand commandInstance = commands.get(command);
	HashMap<String, Object> requestMap =extractedRequest(req);
	Response response = commandInstance.execute(command, requestMap);
	LOG.debug(String.format("finish executeCommand() : %s", response.toString()));
	return response;
    }

    private HashMap<String, Object> extractedRequest(HttpServletRequest req) {
	return (HashMap<String, Object>) req.getParameterMap();
    }
}