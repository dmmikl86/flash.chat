package playtika.vn.command.api;

import java.util.Map;

public interface ICommand {
    public void execute(String command, Map<String, String> params);
}
