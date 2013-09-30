
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import playtika.vn.config.GeneralCommand;
import playtika.vn.core.UserService;
import playtika.vn.core.server.Response;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class ChatServlet extends HttpServlet {

    private String login;
    private String pass;
    private String message;
    private String fromUser;
    private String toUser;
    private String json;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	PrintWriter out = resp.getWriter();
	out.println("Hello, World! TEST_MESSAGE!");
	out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	String command = req.getParameter(GeneralCommand.COMMAND);
	Response result = new Response();
	Gson gson = new Gson();
	switch (command) {
	case GeneralCommand.LOGIN:
	    login = req.getParameter("login");
	    pass = req.getParameter("password");
	    UserService.getInstance().loginUser(login, pass);
	    result.command = command;
	    break;
	case GeneralCommand.SEND:
	    message = req.getParameter("message");
	    fromUser = req.getParameter("fromUser");
	    toUser = req.getParameter("toUser");
	    UserService.getInstance().sendMessage(fromUser, message, toUser);
	    result.command = command;
	    break;
	case GeneralCommand.GET:
	    toUser = req.getParameter("toUser");
	    result.command = command;
	    result.messages = UserService.getInstance().getMessages(toUser);
	    result.list = UserService.getInstance().getUserList();
	    break;
	}
	json = gson.toJson(result);
	PrintWriter out = resp.getWriter();
	out.println(json);
	out.close();
    }
}