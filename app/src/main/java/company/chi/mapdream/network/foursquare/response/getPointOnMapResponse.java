package company.chi.mapdream.network.foursquare.response;


import company.chi.mapdream.network.foursquare.model.Meta;
import company.chi.mapdream.network.foursquare.model.Response;

public class getPointOnMapResponse {
    public Meta meta;
    public Response response;

    public getPointOnMapResponse(Meta meta, Response response) {
        this.meta = meta;
        this.response = response;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
