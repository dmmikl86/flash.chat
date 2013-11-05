package vn.playtika.client.command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import vn.playtika.Response;

import com.google.gson.Gson;

public class ServerCallCommand extends Command {

    @Override
    public Response execute(String command, Object params) {
	try {
	    String url = "http://localhost:8080/ChatServlet/chat";
	    CloseableHttpClient httpclient = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(url);

	    List<NameValuePair> data = (List<NameValuePair>) params;

	    httpPost.setEntity(new UrlEncodedFormEntity(data));
	    CloseableHttpResponse httpResponse = httpclient.execute(httpPost);

	    BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

	    StringBuffer result = new StringBuffer();
	    String line = "";
	    while ((line = rd.readLine()) != null) {
		result.append(line);
	    }
	    response = convertJsonToResponce(result.toString());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return response;
    }

    private Response convertJsonToResponce(String result) {
	Gson gson = new Gson();
	Response res = new Response();
	res = gson.fromJson(result, Response.class);
	return res;
    }
}
