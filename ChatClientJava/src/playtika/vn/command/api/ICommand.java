package playtika.vn.command.api;

import playtika.vn.client.Response;


public interface ICommand {
    Response execute(String command, Object params);
}
