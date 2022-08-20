package android.example.universityproject;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ItemListFragment extends Fragment {


    RecyclerView recyclerView;
    ArrayList<ItemModel> arrItems;
    DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_item_list, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.item_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        arrItems = new ArrayList<ItemModel>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Missing Items");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                arrItems.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

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

                RecyclerItemAdapter adapter = new RecyclerItemAdapter(getActivity(), arrItems);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getActivity(), "Internal Error!!" + error.getMessage(), Toast.LENGTH_SHORT).show();
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



        return rootView;
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
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}