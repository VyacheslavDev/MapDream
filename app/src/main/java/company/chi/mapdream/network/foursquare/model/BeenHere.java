package company.chi.mapdream.network.foursquare.model;


public class BeenHere {
    private int unconfirmedCount;
    private boolean marked;
    private int lastCheckinExpiredAt;

    public BeenHere(int unconfirmedCount, boolean marked, int lastCheckinExpiredAt) {
        this.unconfirmedCount = unconfirmedCount;
        this.marked = marked;
        this.lastCheckinExpiredAt = lastCheckinExpiredAt;
    }

    public int getUnconfirmedCount() {
        return unconfirmedCount;
    }

    public void setUnconfirmedCount(int unconfirmedCount) {
        this.unconfirmedCount = unconfirmedCount;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public int getLastCheckinExpiredAt() {
        return lastCheckinExpiredAt;
    }

    public void setLastCheckinExpiredAt(int lastCheckinExpiredAt) {
        this.lastCheckinExpiredAt = lastCheckinExpiredAt;
    }
}
