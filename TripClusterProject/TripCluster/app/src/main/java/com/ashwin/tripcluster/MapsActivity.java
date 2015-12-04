package com.ashwin.tripcluster;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    //public static double latitude;
    //public static double longitude;
    LocationDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Double latitude;
        Double longitude;
        LatLng location = null;
        /*
        dbHelper = new LocationDBHelper(getApplicationContext());
        Cursor cursor  = dbHelper.getLocations();
        while(cursor.moveToNext()){
            latitude = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.COLUMN_LAT)));
            longitude = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.COLUMN_LONG)));
            location = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(location).title("Marker").snippet("Cluster").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        }
        */
        for(int i=0;i<KMeansCluster.cCount;i++){
            for(int k=0;k<MainActivity.cList.get(i).cluster.size();k++){
                //Log.v("Cluster", MainActivity.cList.get(i).cluster.get(k).getName());
                latitude = MainActivity.cList.get(i).cluster.get(k).getLatitutde();
                longitude = MainActivity.cList.get(i).cluster.get(k).getLongitude();
                location = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(location).title("Marker").snippet("Cluster").icon(BitmapDescriptorFactory.defaultMarker((i+1)*60.0f)));

            }
            //Log.v("Cluster", "End");
        }
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 7.0f));
    }
}
