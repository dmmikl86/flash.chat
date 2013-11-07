package playtika.vn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import playtika.vn.config.GeneralCommand;
import vn.common.Response;
import vn.common.server.ServerService;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class ChatServlet extends HttpServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(ChatServlet.class);
    private ServerService serverService = new ServerService();

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
	
	Response result = serverService.executeCommand(command, req);
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
}