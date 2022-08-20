package android.example.universityproject;

public class ItemDetails {
    String item_name, item_category, contact_no, description, img_url;
    Double longitude, latitude;

    public ItemDetails(String item_name, String item_category, Double longitude, Double latitude, String contact_no, String description, String img_url){
        this.item_name = item_name;
        this.item_category = item_category;
        this.longitude = longitude;
        this.latitude = latitude;
        this.contact_no = contact_no;
        this.description = description;
        this.img_url = img_url;

    }

    public ItemDetails(){

    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItem_category(String item_category) {
        this.item_category = item_category;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_category() {
        return item_category;
    }

    public String getContact_no() {
        return contact_no;
    }

    public String getDescription() {
        return description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
