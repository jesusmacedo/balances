package com.jesusmacedo.balances.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Create and manage the Database.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "balances.db";
    private static final Integer VER = 2;
    private static final Integer DATABASE_VERSION = VER;

    private Context context;

    /**
     * Create the Database.
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VER);
    }

    /**
     * Called in two circumstances, for creating the Database and for upgrading it.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Card (" +
                "    cardId integer NOT NULL CONSTRAINT Card_pk PRIMARY KEY AUTOINCREMENT," +
                "    name text NOT NULL," +
                "    current_balance real NOT NULL," +
                "    currency text NOT NULL," +
                "    type text NOT NULL," +
                "    credit_limit real," +
                "    closing_date integer NOT NULL," +
                "    due_date integer NOT NULL," +
                "    reminder_days integer NOT NULL" +
                ");");

        db.execSQL("CREATE TABLE Period (" +
                "    periodId integer NOT NULL CONSTRAINT Period_pk PRIMARY KEY AUTOINCREMENT," +
                "    month integer NOT NULL," +
                "    year integer NOT NULL," +
                "    balance real NOT NULL," +
                "    card_cardId integer NOT NULL," +
                "    CONSTRAINT Period_Card FOREIGN KEY (card_cardId)" +
                "    REFERENCES Card (cardId)" +
                ");");

        db.execSQL("CREATE TABLE Record (" +
                "    recordId integer NOT NULL CONSTRAINT Record_pk PRIMARY KEY," +
                "    type integer NOT NULL," +
                "    amount real NOT NULL," +
                "    category text NOT NULL," +
                "    desc text," +
                "    date integer NOT NULL," +
                "    msi integer," +
                "    currency text NOT NULL," +
                "    planed integer NOT NULL," +
                "    card_cardId integer NOT NULL," +
                "    period_periodId integer NOT NULL," +
                "    CONSTRAINT Record_Card FOREIGN KEY (card_cardId)" +
                "    REFERENCES Card (cardId)," +
                "    CONSTRAINT Record_Period FOREIGN KEY (period_periodId)" +
                "    REFERENCES Period (periodId)" +
                ");");
    }

    /**
     * Automatically called when there is a new version of the Database.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion  != DATABASE_VERSION) {
            db.execSQL("DROP TABLE IF EXISTS Card");
            db.execSQL("DROP TABLE IF EXISTS Period");
            db.execSQL("DROP TABLE IF EXISTS Record");
            onCreate(db);// re-create the database
        }
    }
}
