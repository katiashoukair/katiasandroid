package com.example.katiasproject.DataTables;
import static com.example.katiasproject.DataTables.TablesString.*;

import android.media.metrics.Event;

public class QueryString {


    //region Create Tables
    public static final String SQL_CREATE_PRODUCT =
            "CREATE TABLE " + ProductTable.TABLE_PRODUCT + " (" +
                    ProductTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ProductTable.COLUMN_PRODUCT_NAME + " TEXT," +
                    ProductTable.COLUMN_PRODUCT_DESCRIPTION + " TEXT," +
                    ProductTable.COLUMN_PRODUCT_STOCK + " INTEGER," +
                    ProductTable.COLUMN_PRODUCT_PRICE + " DOUBLE,"+
                    ProductTable.COLUMN_PRODUCT_COLOR + " TEXT,"+
                    ProductTable.COLUMN_PRODUCT_RATING + " DOUBLE,"+
                    ProductTable.COLUMN_PRODUCT_IMAGE + " BLOB);";

    public static final String SQL_CREATE_CART =
            "CREATE TABLE " + TablesString.Cart.TABLE_CART + " (" +
                    TablesString.Cart._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TablesString.Cart.COLUMN_PRODUCT_ID + " INTEGER," +
                    TablesString.Cart.COLUMN_USER_ID + " TEXT);";

    public static final String SQL_CREATE_STATION =
            "CREATE TABLE " + Station.TABLE_STATION + " (" +
                    Station._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Station.COLUMN_STATION_USER_ID + " TEXT," +
                    Station.COLUMN_STATION_EVENT_NAME + " TEXT," +
                    Station.COLUMN_STATION_LOCATION+ " TEXT," +
                    Station.COLUMN_STATION_DATE + " DATE," +
                    Station.COLUMN_STATION_STARTTIME + " TIME,"+
                    Station.COLUMN_STATION_ENDTIME + " TIME);";


    //endregions

    //region Delete Tables

    public static final String SQL_DELETE_PRODUCT =
            "DROP TABLE IF EXISTS " + ProductTable.TABLE_PRODUCT;

    public static final String SQL_DELETE_CART =
            "DROP TABLE IF EXISTS " + Cart.TABLE_CART;

    public static final String SQL_DELETE_STATION =
            "DROP TABLE IF EXISTS " + Station.TABLE_STATION;

    //endregion
}