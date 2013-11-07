package vn.common.client.command;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import vn.common.Response;
import vn.common.api.ICommand;
import vn.common.client.ClientService;

public class Command<T> implements ICommand<T> {
    protected Response response = new Response();
    protected T variables;
    protected List<NameValuePair> data;
    protected ClientService chatService;

    @Override
    public Response execute(String command, T params) {
	chatService = new ClientService();
	variables = params;
	data = new ArrayList<NameValuePair>();
	data.add(new BasicNameValuePair("command", command));
	return response;
    }
}
