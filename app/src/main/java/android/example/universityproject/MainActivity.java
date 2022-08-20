package android.example.universityproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.example.universityproject.R.id;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {


    NavigationBarView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(id.add);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.map:
                setTitle("Map");
                Toast.makeText(this, "Map Fragment", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(id.content, new MapsFragment()).commit();
                return true;

            case R.id.add:
                setTitle("Add Found Item");
                Toast.makeText(this, "Add Fragment", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(id.content, new AddFoundItemFragment()).commit();
                return true;

            case R.id.recent:
                setTitle("Recents");
                Toast.makeText(this, "Recent Fragment", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(id.content, new ItemListFragment()).commit();
                return true;

            case R.id.account:
                setTitle("Profile");
                Toast.makeText(this, "Account Fragment", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.content, new Profile()).commit();
                return true;

        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}