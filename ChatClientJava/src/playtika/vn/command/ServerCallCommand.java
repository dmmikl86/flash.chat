package playtika.vn.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import playtika.vn.command.api.ICommand;

public class ServerCallCommand implements ICommand {

    @Override
    public void execute(String command, Map<String, String> params) {

	CloseableHttpClient httpclient = HttpClients.createDefault();
	try {
	    HttpPost httpPost = new HttpPost("http://localhost:8080/ChatServlet/chat");
	    List<NameValuePair> data = new ArrayList<NameValuePair>();
	    data.add(new BasicNameValuePair("username", "vip"));
	    data.add(new BasicNameValuePair("password", "secret"));
	    httpPost.setEntity(new UrlEncodedFormEntity(data));
	    CloseableHttpResponse response = httpclient.execute(httpPost);

	    try {
		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		// do something useful with the response body
		// and ensure it is fully consumed
		EntityUtils.consume(entity);
	    } finally {
		response.close();
	    }
	} catch (ClientProtocolException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    try {
		httpclient.close();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

}
