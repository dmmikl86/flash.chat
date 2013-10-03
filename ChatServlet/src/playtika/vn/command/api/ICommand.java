package playtika.vn.command.api;

import java.util.HashMap;

import playtika.vn.server.Response;

public interface ICommand {
    public Response execute(String command, HashMap<String, Object> req);
}
