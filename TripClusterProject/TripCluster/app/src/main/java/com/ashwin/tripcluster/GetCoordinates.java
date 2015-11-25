package com.ashwin.tripcluster;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by ashwi on 11/24/2015.
 */
public class GetCoordinates extends AsyncTask<URL, Integer, Long> {
    @Override
    protected Long doInBackground(URL... params) {
        //ArrayList<Double> latlong = new ArrayList<Double>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(params[0].openStream()));
            String strTemp = "";
            Double c;
            while (null != (strTemp = br.readLine())) {
                if (strTemp.contains("\"lat\"")&&MapsActivity.latitude==0){
                    c = Double.valueOf(strTemp.replace("\"lat\" :","").replace(",","").trim());
                    MapsActivity.latitude = c;
                    //latlong.add(c);
                    Log.v("Lat",c.toString());
                }
                if (strTemp.contains("\"lng\"")&&MapsActivity.longitude==0){
                    c = Double.valueOf(strTemp.replace("\"lng\" :","").replace(",","").trim());
                    MapsActivity.longitude = c;
                    //latlong.add(c);
                    Log.v("Long",c.toString());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
