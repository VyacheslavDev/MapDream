package company.chi.mapdream.network.foursquare.model;


import java.util.List;

public class HereNow {

    private int count;
    private String summary;
    private List<Object> groups;

    public HereNow(int count, String summary, List<Object> groups) {
        this.count = count;
        this.summary = summary;
        this.groups = groups;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Object> getGroups() {
        return groups;
    }

    public void setGroups(List<Object> groups) {
        this.groups = groups;
    }
}
