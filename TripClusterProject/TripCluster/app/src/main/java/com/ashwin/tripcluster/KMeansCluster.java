package com.ashwin.tripcluster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ashwi on 11/28/2015.
 */
public class KMeansCluster {
    public static void cluster(ArrayList<Locations> L){
        double farthest;
        double middle;
        List<Double> list;
        for(Locations l : L){
            l.createDist(L);
        }
        list = L.get(0).getDistMatrix();
        Collections.sort(list);
        farthest = list.get(5);
        middle = list.get(3);
    }
}
