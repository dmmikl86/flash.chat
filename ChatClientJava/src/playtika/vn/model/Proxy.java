package playtika.vn.model;

import java.util.ArrayList;
import java.util.List;

import playtika.vn.client.Response;

public class Proxy {

    private List<IObserver> observersList = new ArrayList<IObserver>();
    private Response data;

    private Proxy() {
    }

    private static class ProxyHolder {
	public static final Proxy INSTANCE = new Proxy();
    }

    public static Proxy getInstance() {
	return ProxyHolder.INSTANCE;
    }

    public void registerObserver(IObserver instance) {
	observersList.add(instance);
    }

    private void notifyObservers() {
	for (IObserver observerInstance : observersList) {
	    observerInstance.update(data);
	}
    }
    
    public void setData(Response data){
	this.data = data;
	notifyObservers();
    }
}