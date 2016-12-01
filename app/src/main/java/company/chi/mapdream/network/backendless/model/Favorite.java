package company.chi.mapdream.network.backendless.model;


import java.util.Date;

public class Favorite {
    private double lat;
    private double lng;
    private String name;
    private String phone;
    private String address;
    private Date created;

    public Favorite(double lat, double lng, String name, String phone, String address, Date created) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.created = created;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
