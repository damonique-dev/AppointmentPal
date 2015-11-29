package com.paul_nikki.cse5236.appointmentpal.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.paul_nikki.cse5236.appointmentpal.R;

import java.io.IOException;
import java.util.List;

public class MapActivity extends AppCompatActivity {
    String TAG = "MapsActivity";
    GoogleMap map;
    List<Address> geocodeMatches;
    String address;
    String officeName;
    Double lat;
    Double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        Intent intent = getIntent();
        address = intent.getStringExtra("Location");
        officeName = intent.getStringExtra("OfficeName");
        GetLatLong();
        setUpMapIfNeeded();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setUpMapIfNeeded(){
        if(map == null){
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.google_map);
            map = mapFragment.getMap();
            if(map!=null){
                setUpMap();
            }
        }
    }

    public void setUpMap(){
        map.addMarker(new MarkerOptions().position(new LatLng(lat,lng)).title(officeName).snippet(address));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng), 15));
        map.animateCamera(CameraUpdateFactory.zoomIn());
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }

    private void GetLatLong(){
        try{
            geocodeMatches = new Geocoder(getApplicationContext()).getFromLocationName(address, 1);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        if(!geocodeMatches.isEmpty()){
            lat = geocodeMatches.get(0).getLatitude();
            lng = geocodeMatches.get(0).getLongitude();
        }
    }


}
