package com.ashwin.tripcluster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ashwi on 11/28/2015.
 */
public class Locations {
    private double latitutde;
    private double longitude;
    private String name;
    public double[] dist;
    List<Double> distMatrix;

    public Locations(String a, Double b, Double c){
        setName(a);
        setLatitutde(b);
        setLongitude(c);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitutde() {
        return latitutde;
    }

    public void setLatitutde(double latitutde) {
        this.latitutde = latitutde;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public void createDist(ArrayList<Locations> loc){
            for(int j=0; j<5; j++) {
                dist[j] = DistanceCalculator.distance(loc.get(0).getLatitutde(), loc.get(0).getLongitude(), loc.get(j).getLatitutde(), loc.get(j).getLongitude(), "K");
                distMatrix.add(dist[j]);
            }
    }

    public List<Double> getDistMatrix() {
        return distMatrix;
    }
}
