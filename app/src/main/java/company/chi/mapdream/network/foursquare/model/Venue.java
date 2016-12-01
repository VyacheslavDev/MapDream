package company.chi.mapdream.network.foursquare.model;


import java.util.List;

public class Venue {
    private String id;
    private String name;
    private Contact contact;
    private Location location;
    private List<Category> categories ;
    private boolean verified;
    private Stats stats ;
    private boolean allowMenuUrlEdit ;
    private BeenHere beenHere;
    private Specials specials ;
    private HereNow hereNow;
    private String referralId ;
    private List<Object> venueChains;
    private boolean hasPerk ;
    private String url ;
    private VenuePage venuePage;
    private String storeId ;

    public Venue(String id, String name, Contact contact, Location location, List<Category> categories, boolean verified, Stats stats, boolean allowMenuUrlEdit, BeenHere beenHere, Specials specials, HereNow hereNow, String referralId, List<Object> venueChains, boolean hasPerk, String url, VenuePage venuePage, String storeId) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.location = location;
        this.categories = categories;
        this.verified = verified;
        this.stats = stats;
        this.allowMenuUrlEdit = allowMenuUrlEdit;
        this.beenHere = beenHere;
        this.specials = specials;
        this.hereNow = hereNow;
        this.referralId = referralId;
        this.venueChains = venueChains;
        this.hasPerk = hasPerk;
        this.url = url;
        this.venuePage = venuePage;
        this.storeId = storeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public boolean isAllowMenuUrlEdit() {
        return allowMenuUrlEdit;
    }

    public void setAllowMenuUrlEdit(boolean allowMenuUrlEdit) {
        this.allowMenuUrlEdit = allowMenuUrlEdit;
    }

    public BeenHere getBeenHere() {
        return beenHere;
    }

    public void setBeenHere(BeenHere beenHere) {
        this.beenHere = beenHere;
    }

    public Specials getSpecials() {
        return specials;
    }

    public void setSpecials(Specials specials) {
        this.specials = specials;
    }

    public HereNow getHereNow() {
        return hereNow;
    }

    public void setHereNow(HereNow hereNow) {
        this.hereNow = hereNow;
    }

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }

    public List<Object> getVenueChains() {
        return venueChains;
    }

    public void setVenueChains(List<Object> venueChains) {
        this.venueChains = venueChains;
    }

    public boolean isHasPerk() {
        return hasPerk;
    }

    public void setHasPerk(boolean hasPerk) {
        this.hasPerk = hasPerk;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public VenuePage getVenuePage() {
        return venuePage;
    }

    public void setVenuePage(VenuePage venuePage) {
        this.venuePage = venuePage;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
