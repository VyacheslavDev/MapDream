package company.chi.mapdream.network.foursquare.model;


import java.util.List;

public class Specials {
    private int count ;
    private List<Object> items;

    public Specials(int count, List<Object> items) {
        this.count = count;
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }
}
