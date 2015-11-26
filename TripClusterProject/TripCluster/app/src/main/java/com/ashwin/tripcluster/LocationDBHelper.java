package com.ashwin.tripcluster;

/**
 * Created by ashwi on 11/25/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Swaroop on 11/7/2015.
 */
public class LocationDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TripCluster.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "locations";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LAT = "latitude";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LONG = "longitude";

    public LocationDBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_LAT + " REAL, " +
                        COLUMN_LONG + " REAL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addLocation (String locationName, Double latitude, Double longitude) {
        long retVal = 0;

        SQLiteDatabase db = getWritableDatabase();
        ContentValues rowData = new ContentValues();
        rowData.put (COLUMN_NAME, locationName);
        rowData.put (COLUMN_LAT, latitude);
        rowData.put (COLUMN_LONG, longitude);
        retVal = db.insert(TABLE_NAME, null, rowData);

        return retVal;
    }

    public Cursor getLocations () {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    public void clearLocations () {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }
}