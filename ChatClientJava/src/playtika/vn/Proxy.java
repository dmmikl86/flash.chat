package playtika.vn;

import java.util.HashMap;
import java.util.Map;

public class Proxy {
    
    private Map<String, Class> observersMap = new HashMap<String, Class>();

    private Proxy() {
    }
    private static class ProxyHolder { 
            public static final Proxy INSTANCE = new Proxy();
    }

    public static Proxy getInstance() {
            return ProxyHolder.INSTANCE;
    }
    
    public void registerObserver(){
	
    }
    
    public void removeObserve(){
	
    }
}