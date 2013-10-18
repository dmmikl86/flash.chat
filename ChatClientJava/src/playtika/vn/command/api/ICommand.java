package playtika.vn.command.api;

import playtika.vn.Response;


public interface ICommand {
    Response execute(String command, Object params);
}
