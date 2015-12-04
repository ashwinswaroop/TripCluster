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


    public int getMaxDist(ArrayList<Locations> loc){
        double max=0;
        double temp=0;
        int maxloc=0;
        for(int j=0; j<loc.size(); j++) {
            temp = DistanceCalculator.distance(loc.get(0).getLatitutde(), loc.get(0).getLongitude(), loc.get(j).getLatitutde(), loc.get(j).getLongitude(), "K");
            if(temp>max){
                max=temp;
                maxloc=j;
            }
        }
        return maxloc;
    }

    public List<Double> getDistMatrix() {
        return distMatrix;
    }
}
