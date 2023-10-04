package com.example.katiasproject.DataTables;
import android.provider.BaseColumns;

public class TablesString {

    public TablesString() {
    }
    //region Product Table
    public static class ProductTable implements BaseColumns {
        public static final String TABLE_PRODUCT = "Product";
        public static final String COLUMN_PRODUCT_NAME = "ProductName";
        public static final String COLUMN_PRODUCT_DESCRIPTION = "Description";
        public static final String COLUMN_PRODUCT_IMAGE = "ProductImage";
        public static final String COLUMN_PRODUCT_STOCK = "Stock";
        public static final String COLUMN_PRODUCT_PRICE = "Price";
        public static final String COLUMN_PRODUCT_RATING = "Rating";
        public static final String COLUMN_PRODUCT_COLOR = "Color";

    }
    //endregion

    //region Cart Table
    public static class Cart implements BaseColumns {
        public static final String TABLE_CART = "Cart";
        public static final String COLUMN_PRODUCT_ID = "PID";
        public static final String COLUMN_USER_ID = "UID";

    }
    //endregion

    //region STATION Table
    public static class Station implements BaseColumns {
        public static final String TABLE_STATION = "station";

        public static final String COLUMN_STATION_USER_ID = "UID";
        public static final String COLUMN_STATION_EVENT_NAME = "EVENTNAME";
        public static final String COLUMN_STATION_DATE = "DATE";
        public static final String COLUMN_STATION_LOCATION = "LOCATION";
        public static final String COLUMN_STATION_STARTTIME = "STARTTIME";
        public static final String COLUMN_STATION_ENDTIME = "ENDTIME";

    }
    //endregion
}