package playtika.vn.command.api;

import java.util.Map;

import playtika.vn.server.Response;

public interface ICommand {
    public Response execute(String command, Map<String, Object> req);
}
