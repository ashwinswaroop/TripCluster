package com.ashwin.tripcluster;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by ashwi on 11/25/2015.
 */
public class LocationsCursorAdapter extends CursorAdapter {
    public LocationsCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView Name = (TextView) view.findViewById(R.id.Name);
        TextView Lat = (TextView) view.findViewById(R.id.Latitude);
        TextView Lng = (TextView) view.findViewById(R.id.Longitude);
        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        String lat = cursor.getString(cursor.getColumnIndexOrThrow("latitude"));
        String lng = cursor.getString(cursor.getColumnIndexOrThrow("longitude"));
        Name.setTextColor(Color.BLACK);
        Name.setText(name);
        Lat.setTextColor(Color.BLACK);
        Lat.setText(lat);
        Lng.setTextColor(Color.BLACK);
        Lng.setText(lng);

    }
}
