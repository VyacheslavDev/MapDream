package company.chi.mapdream.network.foursquare.model;


public class Meta {
    public int code;
    public String requestId;

    public Meta(String requestId, int code) {
        this.requestId = requestId;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}

