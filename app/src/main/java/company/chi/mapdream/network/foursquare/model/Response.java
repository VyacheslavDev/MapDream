package company.chi.mapdream.network.foursquare.model;


import java.util.List;

public class Response {
    private List<Venue> venues;

    public Response(List<Venue> venues) {
        this.venues = venues;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }
}
