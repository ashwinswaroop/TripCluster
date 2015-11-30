package com.ashwin.tripcluster;

import android.location.Location;

import java.util.ArrayList;

/**
 * Created by ashwi on 11/29/2015.
 */
public class Clusters {
    public ArrayList<Locations> cluster = new ArrayList<>();
    public double centroidLat=0;
    public double centroidLong=0;
    public Clusters(Locations l){
        cluster.add(l);
        this.centroidLat = l.getLatitutde();
        this.centroidLong = l.getLongitude();
    }
}
