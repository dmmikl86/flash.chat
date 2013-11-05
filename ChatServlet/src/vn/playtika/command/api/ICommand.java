package vn.playtika.command.api;

import java.util.Map;

import vn.playtika.server.Response;

public interface ICommand {
    public Response execute(String command, Map<String, Object> req);
}
