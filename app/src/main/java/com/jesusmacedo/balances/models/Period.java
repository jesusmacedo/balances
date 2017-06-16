package com.jesusmacedo.balances.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.jesusmacedo.balances.database.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Credit card period.
 */

public class Period {

    private static final String TABLE_NAME = "Period";
    private static final String PERIOD_ID = "periodId";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
    private static final String BALANCE = "balance";
    private static final String CARD_ID = "card_cardId";

    private long periodId;
    private int month;
    private int year;
    private double balance;
    private long cardId;

    public Period(int month, int year, double balance, long cardId) {
        this.month = month;
        this.year = year;
        this.balance = balance;
        this.cardId = cardId;
    }

    /**
     * Add new period to card.
     * @param context
     * @param period
     * @return
     */
    public static long addNewPeriodToCard(Context context, Period period) {
        ContentValues values = new ContentValues();
        values.put("month", period.getMonth());
        values.put("year", period.getYear());
        values.put("balance", period.getBalance());
        values.put("card_cardId", period.getCardId());

        return DatabaseAdapter.getDB(context).insert(TABLE_NAME, null, values);
    }

    public static Period getPeriodByCardId(Context context, long cardId, int month, int year) {
        Period period =  null;
        String where = CARD_ID +"="+ cardId +" AND "+ MONTH +"="+ month +" AND "+ YEAR +"="+ year;

        Cursor cursor = DatabaseAdapter.getDB(context).query(TABLE_NAME, null, where, null, null, null, null);

        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                int periodId = cursor.getInt(cursor.getColumnIndexOrThrow(PERIOD_ID));
                int monthResponse = cursor.getInt(cursor.getColumnIndexOrThrow(MONTH));
                int yearResponse = cursor.getInt(cursor.getColumnIndexOrThrow(YEAR));
                double balance = cursor.getDouble(cursor.getColumnIndexOrThrow(BALANCE));

                period = new Period(monthResponse, yearResponse, balance, cardId);
                period.setPeriodId(periodId);
            }

            cursor.close();
        }
        return period;

    }
    //https://www.tutorialspoint.com/sqlite/sqlite_using_joins.htm
    //https://stackoverflow.com/questions/9902394/how-to-get-last-record-from-sqlite

    /**
     * Get all periods for a card.
     * @param context
     * @return
     */
    public static List<Period> getAllPeriods(Context context) {
        List<Period> periods = new ArrayList<>();

        Cursor cursor = DatabaseAdapter.getDB(context).query(TABLE_NAME, null, null, null, null, null, CARD_ID);

        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                int periodId = cursor.getInt(cursor.getColumnIndexOrThrow(PERIOD_ID));
                int month = cursor.getInt(cursor.getColumnIndexOrThrow(MONTH));
                int year = cursor.getInt(cursor.getColumnIndexOrThrow(YEAR));
                double balance = cursor.getDouble(cursor.getColumnIndexOrThrow(BALANCE));
                long cardId = cursor.getLong(cursor.getColumnIndexOrThrow(CARD_ID));

                Period period = new Period(month, year, balance, cardId);
                period.setPeriodId(periodId);
                periods.add(period);
                Log.e("DFA", "Period from " + month);
            }

            cursor.close();
        }
        return periods;
    }

    public long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(long periodId) {
        this.periodId = periodId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }
}
