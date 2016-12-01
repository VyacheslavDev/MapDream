package company.chi.mapdream.data.model;


import java.util.Date;

public class Visit {
    private double lat;
    private double lng;
    private String namePlace;
    private String photoUrl;
    private Date dateTime;
    private Date create;

    public Visit(double lat, double lng, String namePlace, String photoUrl, Date dateTime, Date create) {
        this.lat = lat;
        this.lng = lng;
        this.namePlace = namePlace;
        this.photoUrl = photoUrl;
        this.dateTime = dateTime;
        this.create = create;
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

    public String getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }
}
