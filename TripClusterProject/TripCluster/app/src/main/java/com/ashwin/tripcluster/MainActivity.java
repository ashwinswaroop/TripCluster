package com.ashwin.tripcluster;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static double latitude;
    public static double longitude;
    LocationDBHelper dbHelper;
    boolean reloadSavedLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new LocationDBHelper(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editLocation = (EditText)findViewById(R.id.editLocation);
        final Button addLocation  = (Button)findViewById(R.id.addLocation);
        final Button clearLocations  = (Button) findViewById(R.id.clearLocations);
        //final SqlHandler sqlHandler = new SqlHandler(getApplicationContext());
        editLocation.setText("Enter New Location");
        editLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editLocation.setText("");
            }
        });
        addLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editLocation.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter a location");
                    return;
                }
                GetCoordinates.latflag = false;
                GetCoordinates.longflag = false;
                URL url = null;
                try {
                    String el = editLocation.getText().toString().replace(',', '+');
                    Log.v("app", "https://maps.googleapis.com/maps/api/geocode/json?address=" + el + "&key=AIzaSyCSGwVHDPWVzs795rAX2sOngeYalf64Z0g");
                    url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + el + "&key=AIzaSyCSGwVHDPWVzs795rAX2sOngeYalf64Z0g");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    new GetCoordinates().execute(url).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                //showMessage("Success", "Location added to trip");
                dbHelper.addLocation(editLocation.getText().toString(), latitude, longitude);
                ListView locationList = (ListView) findViewById(R.id.locationList);
                Cursor cursor = dbHelper.getLocations();
                LocationsCursorAdapter adapter = new LocationsCursorAdapter(getApplicationContext(), cursor, 0);
                locationList.setAdapter(adapter);
                adapter.changeCursor(cursor);
                editLocation.setText("Enter New Location");
            }
        });
        clearLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.clearLocations();
            }
        });
        //showList();
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (reloadSavedLocations) {

            ListView locationList = (ListView) findViewById(R.id.locationList);
            Cursor cursor = dbHelper.getLocations();
            LocationsCursorAdapter adapter = new LocationsCursorAdapter(getApplicationContext(), cursor, 0);
            locationList.setAdapter(adapter);
            adapter.changeCursor(cursor);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        reloadSavedLocations = true;
    }

}
