package company.chi.mapdream.network.foursquare.model;


public class Contact {
    private String phone;
    private String formattedPhone;

    public Contact(String formattedPhone, String phone) {
        this.formattedPhone = formattedPhone;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFormattedPhone() {
        return formattedPhone;
    }

    public void setFormattedPhone(String formattedPhone) {
        this.formattedPhone = formattedPhone;
    }
}

