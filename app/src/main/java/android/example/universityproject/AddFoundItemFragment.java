package android.example.universityproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class AddFoundItemFragment extends Fragment {

    private static final int CAMERA_REQUEST = 1;
    ImageView add_image;
    ImageView get_location;
    TextView tv_longitude, tv_latitude, tv_area;
    Button btn_submit;
    EditText itemName, itemCategory, contactNo, et_description;
    // initializing
    // FusedLocationProviderClient
    // object
    FirebaseStorage firebaseStorage;
    StorageReference firebaseStorageReference;

    FusedLocationProviderClient mFusedLocationClient;
    int PERMISSION_ID = 44;

    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;
    private Uri mImageUri = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_found_item, container, false);


        add_image = rootView.findViewById(R.id.image);
        get_location = rootView.findViewById(R.id.get_location);
        tv_latitude = rootView.findViewById(R.id.tv_latitude);
        tv_longitude = rootView.findViewById(R.id.tv_longitude);
        tv_area = rootView.findViewById(R.id.tv_area);
        btn_submit = rootView.findViewById(R.id.btn_submit);
        itemName = rootView.findViewById(R.id.et_itemName);
        itemCategory = rootView.findViewById(R.id.et_itemCategory);
        contactNo = rootView.findViewById(R.id.et_ContactNo);
        et_description = rootView.findViewById(R.id.et_description);

        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Missing Items");
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseStorageReference = firebaseStorage.getReference().child("Item_Photos");


        get_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // method to get the location
                getLastLocation();
            }

        });


        // Add Found Item Detail to Database
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                databaseReference.child("Hey").setValue("Hello");
                addItemToDatabase();
                saveImageToFirebase();

            }
        });


        return rootView;
    }

    private void addItemToDatabase() {
        // get All data
        String item_name, item_category, contact_no, description, img_url;
        double longitude, latitude;
        ImageView image;
        item_name = itemName.getText().toString();
        item_category = itemCategory.getText().toString();
        contact_no = contactNo.getText().toString();
        longitude = Double.parseDouble(tv_longitude.getText().toString());
        latitude = Double.parseDouble(tv_latitude.getText().toString());
        description = et_description.getText().toString();
        img_url = "";


//        ItemDetails itemDetails = new ItemDetails(item_name, item_category, longitude, latitude, contact_no, description);
        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setItem_name(item_name);
        itemDetails.setItem_category(item_category);
        itemDetails.setLongitude(longitude);
        itemDetails.setLatitude(latitude);
        itemDetails.setContact_no(contact_no);
        itemDetails.setDescription(description);
        itemDetails.setImg_url(img_url);


//        databaseReference.setValue("Hello !!");
        databaseReference.push().setValue(itemDetails);
        Toast.makeText(getActivity(), "Data Inserted Successfully !!", Toast.LENGTH_SHORT).show();
    }


    void imageChooser() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

         ActivityResultLauncher.launch(intent);
    }

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> ActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Intent intent = result.getData();
                        Bitmap bitmap  = (Bitmap)intent.getExtras().get("data");
//                        Bundle bundle = result.getData().getExtras();
//                        Bitmap bitmap = (Bitmap) bundle.get("data");
//                        mImageUri = (Uri) bundle.get("data");
//                        add_image.setImageURI(mImageUri);

                        Bitmap resized = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
                        add_image.setImageBitmap(resized);
//                        saveImageToFirebase();
                    }else{
                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });


    private void saveImageToFirebase() {

        Uri mImageUri = Uri.parse(add_image.getDrawable().toString());

        StorageReference photoRef = firebaseStorageReference.child(mImageUri.toString());
//        final StorageReference ref = storageRef.child("images/mountains.jpg");
        UploadTask uploadTask = photoRef.putFile(mImageUri);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return photoRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    databaseReference.child("img_url").setValue(downloadUri.toString());
                    Toast.makeText(getActivity(), "SuccessFull" + downloadUri, Toast.LENGTH_SHORT).show();
                } else {
                    // Handle failures
                    // ...
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            tv_latitude.setText(location.getLatitude() + "");
                            tv_longitude.setText(location.getLongitude() + "");
                            getArea(location.getLatitude(), location.getLongitude() );
                        }
                    }
                });
            } else {
                Toast.makeText(getActivity(), "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions();
        }
    }

    // Method to get Area of Current User
    private void getArea(double latitude, double longitude) {

        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();

            tv_area.setText(add);
            // TennisAppActivity.showDialog(add);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
//        LocationRequest mLocationRequest = new LocationRequest();
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        mLocationRequest.setInterval(5);
//        mLocationRequest.setFastestInterval(0);
//        mLocationRequest.setNumUpdates(1);

        LocationRequest locationRequest = LocationRequest.create()
                .setInterval(1000)
                .setFastestInterval(0)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mFusedLocationClient.requestLocationUpdates(locationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            tv_latitude.setText(mLastLocation.getLatitude() + "");
            tv_longitude.setText(mLastLocation.getLongitude() + "");
        }
    };

    // method to check for permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    // method to check
    // if location is enabled
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // If everything is alright then
    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        if (checkPermissions()) {
//            getLastLocation();
//        }
//    }

}