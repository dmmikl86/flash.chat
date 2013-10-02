package playtika.vn.command.api;

import javax.servlet.http.HttpServletRequest;

import playtika.vn.server.Response;

public interface ICommand {
    public Response execute(String command, HttpServletRequest req);
}
