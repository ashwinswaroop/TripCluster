package com.ashwin.tripcluster;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ashwi on 11/28/2015.
 */
public class KMeansCluster {
    public static void cluster(ArrayList<Locations> L, int clusterCount){
        int i,k,z,t,m,h;
        double j=0;
        int closest=0;
        double min=Double.MAX_VALUE;
        double dist=0;
        long[] chosenClusterIndexes = new long[clusterCount];
        double interval = L.size()/(clusterCount-1);
        for(i=0; i<clusterCount-1; i++, j+=interval){
            chosenClusterIndexes[i] = Math.round(j);
        }
        chosenClusterIndexes[clusterCount-1] = L.size()-1;
        ArrayList<Clusters> clusterList = new ArrayList<Clusters>();
        for(i=0; i<clusterCount; i++){
            clusterList.add(new Clusters(L.get((int)chosenClusterIndexes[i])));
        }
        for(i=0,h=0; i<clusterCount; i++,h++){
            L.remove((int)chosenClusterIndexes[i]-h); //Remember this WILL NOT always work.
        }
        for(i=0; i<L.size(); i++){
            min=Double.MAX_VALUE;
            closest=0;
            for(k=0; k<clusterCount; k++){
                dist = DistanceCalculator.distance(L.get(i).getLatitutde(),L.get(i).getLongitude(),clusterList.get(k).centroidLat,clusterList.get(k).centroidLong,"K");
                if(dist<min){
                    min=dist;
                    closest=k;
                }
            }
            //clusterList.get(closest).centroidLat = ((clusterList.get(closest).cluster.size()-1)*clusterList.get(closest).centroidLat + L.get(i).getLatitutde())/clusterList.get(closest).cluster.size();
            clusterList.get(closest).centroidLat = (clusterList.get(closest).centroidLat + L.get(i).getLatitutde())/2;
            //clusterList.get(closest).centroidLong = ((clusterList.get(closest).cluster.size()-1)*clusterList.get(closest).centroidLong + L.get(i).getLongitude())/clusterList.get(closest).cluster.size();
            clusterList.get(closest).centroidLong = (clusterList.get(closest).centroidLong + L.get(i).getLongitude())/2;
            clusterList.get(closest).cluster.add(L.get(i));

        }

        for(m=0; m<3; m++){
            for(i=0; i<clusterCount; i++){
                min=Double.MAX_VALUE;
                closest=0;
                t=0;
                for(k=0; k<clusterList.get(i).cluster.size();k++){ //SOMETHING WRONG with this loop
                    for(z=0; z<clusterCount; z++){
                        dist = DistanceCalculator.distance(clusterList.get(i).cluster.get(k).getLatitutde(),clusterList.get(i).cluster.get(k).getLongitude(),clusterList.get(z).centroidLat,clusterList.get(z).centroidLong,"K");
                        if(dist<min){
                            min=dist;
                            closest=z;
                            t=k;
                        }
                    }
                    //clusterList.get(closest).centroidLat = ((clusterList.get(closest).cluster.size()-1)*clusterList.get(closest).centroidLat + clusterList.get(i).cluster.get(t).getLatitutde())/clusterList.get(closest).cluster.size();
                    //clusterList.get(closest).centroidLong = ((clusterList.get(closest).cluster.size()-1)*clusterList.get(closest).centroidLong + clusterList.get(i).cluster.get(t).getLongitude())/clusterList.get(closest).cluster.size();
                    clusterList.get(closest).centroidLat = (clusterList.get(closest).centroidLat + clusterList.get(i).cluster.get(t).getLatitutde())/2;
                    clusterList.get(closest).centroidLong = (clusterList.get(closest).centroidLong + clusterList.get(i).cluster.get(t).getLongitude())/2;
                    clusterList.get(closest).cluster.add(clusterList.get(i).cluster.get(t));

                    //clusterList.get(i).centroidLat = ((clusterList.get(i).cluster.size()+1)*clusterList.get(i).centroidLat + clusterList.get(i).cluster.get(t).getLatitutde())/clusterList.get(closest).cluster.size();
                    //clusterList.get(i).centroidLong = ((clusterList.get(i).cluster.size()+1)*clusterList.get(i).centroidLong + clusterList.get(i).cluster.get(t).getLongitude())/clusterList.get(closest).cluster.size();
                    clusterList.get(i).centroidLat = (2*clusterList.get(i).centroidLat - clusterList.get(i).cluster.get(t).getLatitutde());
                    clusterList.get(i).centroidLong = (2*clusterList.get(i).centroidLong - clusterList.get(i).cluster.get(t).getLongitude());
                    clusterList.get(i).cluster.remove(clusterList.get(i).cluster.get(t));
                }
            }
        }
        for(i=0;i<clusterCount;i++){
            for(k=0;k<clusterList.get(i).cluster.size();k++){
                Log.v("Cluster", clusterList.get(i).cluster.get(k).getName());
            }
            Log.v("Cluster", "End");
        }
    }
}
