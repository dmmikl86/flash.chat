package playtika.vn.command;

import playtika.vn.Response;


public class ShowMessageCommand extends Command {

    @Override
    public Response execute(String command, Object params) {
	super.execute(command, params);
	return response;	
    }

}
