package com.jesusmacedo.balances.models;

import android.content.ContentValues;
import android.content.Context;

import com.jesusmacedo.balances.database.DatabaseAdapter;

/**
 * Credit card period.
 */

public class Period {

    private static final String TABLE_NAME = "Period";
    private static final String PERDIOD_ID = "periodId";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
    private static final String BALANCE = "balance";
    private static final String CARD_ID = "card_cardId";

    private long periodId;
    private int month;
    private int year;
    private double balance;
    private double cardId;

    public Period(int month, int year, double balance, double cardId) {
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

    public void setCardId(double cardId) {
        this.cardId = cardId;
    }
}
