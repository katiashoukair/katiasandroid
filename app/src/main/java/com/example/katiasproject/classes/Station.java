package com.example.katiasproject.classes;

import static com.example.katiasproject.DataTables.TablesString.Station.*;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.sql.Date;
import java.sql.Time;


public class Station implements SqlInterface {
    private int stationid;
    private String uid, eventname, location;
    private Date date;
    private Time starttime, endtime;

    //endregion

    //region Constructors
    public Station(String eventname, String location, Date date, Time starttime, Time endtime, String uid) {
        this.uid = uid;
        this.eventname = eventname;
        this.location = location;
        this.date = date;
        this.starttime = starttime;
        this.endtime = endtime;

    }
    //endregion

    //region Add,Delete,Update,Select Sql
    @Override
    public long Add(SQLiteDatabase db) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_STATION_USER_ID, uid);
        values.put(COLUMN_STATION_EVENT_NAME, eventname);
        values.put(COLUMN_STATION_DATE, date.toString());
        values.put(COLUMN_STATION_LOCATION, location);
        values.put(COLUMN_STATION_STARTTIME, starttime.toString());
        values.put(COLUMN_STATION_ENDTIME, endtime.toString());

// Insert the new row, returning the primary key value of the new row
        return db.insert(TABLE_STATION, null, values);
    }

    @Override
    public int Delete(SQLiteDatabase db, int id) {
        String selection = BaseColumns._ID + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = {id + ""};
// Issue SQL statement.
        return db.delete(TABLE_STATION, selection, selectionArgs);
    }

    @Override
    public int Update(SQLiteDatabase db, int id) {
        // New value for one column
        ContentValues values = new ContentValues();

        values.put(COLUMN_STATION_USER_ID, uid);
        values.put(COLUMN_STATION_EVENT_NAME, eventname);
        values.put(COLUMN_STATION_DATE, date.toString());
        values.put(COLUMN_STATION_LOCATION, location);
        values.put(COLUMN_STATION_STARTTIME, starttime.toString());
        values.put(COLUMN_STATION_ENDTIME, endtime.toString());


// Which row to update, based on the title
        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = {id + ""};

        return db.update(
                TABLE_STATION,
                values,
                selection,
                selectionArgs);
    }

    @Override
    public Cursor Select(SQLiteDatabase db) {
        String[] projection = {
                BaseColumns._ID,

                COLUMN_STATION_USER_ID,
                COLUMN_STATION_EVENT_NAME,
                COLUMN_STATION_DATE,
                COLUMN_STATION_LOCATION,
                COLUMN_STATION_STARTTIME,
                COLUMN_STATION_ENDTIME,

        };
// How you want the results sorted in the resulting Cursor
        String sortOrder =
                BaseColumns._ID + " DESC";
        Cursor c = db.query(TABLE_STATION,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);
        return c;
    }


    public int getStationid() {
        return stationid;
    }

    public void setStationid(int stationid) {
        this.stationid = stationid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStarttime() {
        return starttime;
    }

    public void setStarttime(Time starttime) {
        this.starttime = starttime;
    }

    public Time getEndtime() {
        return endtime;
    }

    public void setEndtime(Time endtime) {
        this.endtime = endtime;
    }
}
