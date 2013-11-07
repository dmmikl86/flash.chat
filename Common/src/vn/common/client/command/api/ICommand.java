package vn.common.client.command.api;

import vn.common.Response;

public interface ICommand<T> {
    Response execute(String command, T params);
}
