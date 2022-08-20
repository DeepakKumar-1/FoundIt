package android.example.universityproject;

public class ItemModel {
    String itemName, itemCategory, area;

    public ItemModel(String itemName, String itemCategory, String area){
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.area = area;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public String getArea() {
        return area;
    }
}
