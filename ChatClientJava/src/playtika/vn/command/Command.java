package playtika.vn.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import playtika.vn.Response;
import playtika.vn.command.api.ICommand;

public class Command implements ICommand {
    protected Response response = new Response();
    protected HashMap<String, String> variables;
    protected List<NameValuePair> data;

    @Override
    public Response execute(String command, Object params) {
	variables = (HashMap<String, String>) params;
	data = new ArrayList<NameValuePair>();
	data.add(new BasicNameValuePair("command", command));
	return response;
    }

}
