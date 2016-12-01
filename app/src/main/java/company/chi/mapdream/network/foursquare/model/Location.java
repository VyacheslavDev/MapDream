package company.chi.mapdream.network.foursquare.model;


import java.util.List;

public class Location {
    private String address;
    private double lat;
    private double lng;
    private List<LabeledLatLng> labeledLatLngs;
    private int distance ;
    private String cc;
    private String city;
    private String country;
    private List<String> formattedAddress;
    private String postalCode;
    private String state;
    private String crossStreet;

    public Location(String address, double lat, double lng, List<LabeledLatLng> labeledLatLngs, int distance, String cc, String city, String country, List<String> formattedAddress, String postalCode, String state, String crossStreet) {
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.labeledLatLngs = labeledLatLngs;
        this.distance = distance;
        this.cc = cc;
        this.city = city;
        this.country = country;
        this.formattedAddress = formattedAddress;
        this.postalCode = postalCode;
        this.state = state;
        this.crossStreet = crossStreet;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public List<LabeledLatLng> getLabeledLatLngs() {
        return labeledLatLngs;
    }

    public void setLabeledLatLngs(List<LabeledLatLng> labeledLatLngs) {
        this.labeledLatLngs = labeledLatLngs;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(List<String> formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }
}
