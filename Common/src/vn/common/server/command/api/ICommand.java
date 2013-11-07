package vn.common.server.command.api;

import java.util.Map;

import vn.common.Response;

public interface ICommand {
    Response execute(String command, Map<String, Object> req);
}
