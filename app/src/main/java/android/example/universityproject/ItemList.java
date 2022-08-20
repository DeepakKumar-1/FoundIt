package android.example.universityproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ItemList extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ItemModel> arrItems;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);


        recyclerView = findViewById(R.id.item_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrItems = new ArrayList<ItemModel>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Missing Items");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                arrItems.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    String itemName = dataSnapshot.child("item_name").getValue(String.class);
                    String itemCategory = dataSnapshot.child("item_category").getValue(String.class);
                    String longitude = dataSnapshot.child("longitude").getValue().toString();
                    String latitude = dataSnapshot.child("latitude").getValue().toString();
//                    assert latitude != null;
//                    assert longitude != null;
                    String area = getArea(Double.parseDouble(latitude), Double.parseDouble(longitude));
                    ItemModel itemModel = new ItemModel(itemName, itemCategory, area);
//////                    Log.e("Name", itemName);
//////                    Log.e("Name", itemCategory);
//////                    Log.e("Name", ContactNo);
                    arrItems.add(itemModel);
//                    ItemInfo itemInfo = dataSnapshot.getValue(ItemInfo.class);
//
//                    if(itemInfo != null) {
//                        ItemModel itemModel = new ItemModel(itemInfo.getItem_name(), itemInfo.getItem_category(), itemInfo.getContact_no());
//                        arrItems.add(itemModel);
//                    }
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));

//                    Toast.makeText(ItemList.this, "" +  longitude+ " " + latitude, Toast.LENGTH_LONG).show();
//
//                    arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
                }

                RecyclerItemAdapter adapter = new RecyclerItemAdapter(ItemList.this, arrItems);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ItemList.this, "Internal Error!!" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//


//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//        arrItems.add(new ItemModel("a", "hgds", "bhsd"));
//




    }


//
//    // Method to get Area of Current User
//    private String getArea(double latitude, double longitude) {
//
//        Geocoder geocoder = new Geocoder(ItemList.this, Locale.getDefault());
//        String add = "";
//        try {
//            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
//            Address obj = addresses.get(0);
//            add = obj.getAddressLine(0);
//            // Toast.makeText(this, "Address=>" + add,
//            // Toast.LENGTH_SHORT).show();
//
//            // TennisAppActivity.showDialog(add);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//        return add;
//    }

    private String getArea(double latitude, double longitude) {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                result.append(address.getAddressLine(0));
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        return result.toString();
    }
}