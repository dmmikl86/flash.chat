package vn.playtika.client.command.api;

import vn.playtika.Response;

public interface ICommand {
    Response execute(String command, Object params);
}
