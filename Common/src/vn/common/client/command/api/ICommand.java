package vn.common.client.command.api;

import vn.common.Response;

public interface ICommand {
    Response execute(String command, Object params);
}
