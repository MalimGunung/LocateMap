package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.io.IOException;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap myMap;
    private SearchView mapSearchView;
    protected Button openActivity;

    private ImageButton openActivity2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openActivity = (Button) findViewById(R.id.button);
        openActivity2 = (ImageButton) findViewById(R.id.imageButton);

        mapSearchView=findViewById(R.id.mapSearch);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference locationsRef = database.getReference("Search_locations");

        mapSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = mapSearchView.getQuery().toString();
                List<Address> addressList = null;

                if (location != null) {
                    Geocoder geocoder = new Geocoder(MainActivity.this);

                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (addressList != null && !addressList.isEmpty()) {
                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        myMap.addMarker(new MarkerOptions().position(latLng).title(location));
                        myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                        // Store the location marker in Firebase Realtime Database
                        LocationHelper helper = new LocationHelper(String.valueOf(address.getLongitude()), String.valueOf(address.getLatitude()));
                        locationsRef.push().setValue(helper);
                    }
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mapFragment.getMapAsync(MainActivity.this);
    }

    public void openActivity(View view) {
        Intent intent = new Intent(this, history.class);
        startActivity(intent);
    }

    public void openActivity1(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        myMap = googleMap;

    }

}