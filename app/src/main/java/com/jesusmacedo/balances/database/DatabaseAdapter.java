package com.jesusmacedo.balances.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Handle the database.
 */

public class DatabaseAdapter {

    private static DatabaseHelper mDbHelper;
    private static SQLiteDatabase mDb;

    /**
     * Open Database connection.
     * @param context
     * @return
     */
    public static boolean openDB(Context context) {
        // close previous connection if any
        if(mDbHelper != null)
            mDbHelper.close();

        mDbHelper = new DatabaseHelper(context);

        try {
            mDb = mDbHelper.getWritableDatabase();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Return Database connection.
     * @param context
     * @return
     */
    public static SQLiteDatabase getDB(Context context) {
        if(mDb == null)
            openDB(context);

        return mDb;
    }
}
