package playtika.vn.model;

import playtika.vn.client.Response;

public interface IObserver {
    void update(Response data);
}
