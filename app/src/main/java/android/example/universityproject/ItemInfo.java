package android.example.universityproject;

public class ItemInfo {
        String item_name, item_category, contact_no, description;
        Double longitude, latitude;


        public ItemInfo(){

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
    }
