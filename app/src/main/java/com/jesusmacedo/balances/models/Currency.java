package com.jesusmacedo.balances.models;

import android.content.Context;
import android.database.Cursor;

import com.jesusmacedo.balances.database.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Currency for cards and records.
 */

public class Currency {

    public static final String TABLE_NAME = "Currency";
    public static final String CURRENCY_ID = "currencyId";
    public static final String ABBR = "abbr";
    public static final String SYMBOL = "symbol";

    private long currencyId;
    private String abbr;
    private char symbol;

    public Currency(String abbr, char symbol) {
        this.abbr = abbr;
        this.symbol = symbol;
    }

    /**
     * Get all currencies.
     *
     * @param context
     * @return
     */
    public static List<Currency> getCurrencies(Context context) {
        List<Currency> currencies = new ArrayList<>();

        Cursor cursor = DatabaseAdapter.getDB(context).query(TABLE_NAME, null, null, null, null, null, CURRENCY_ID);

        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                int currencyId = cursor.getInt(cursor.getColumnIndexOrThrow(CURRENCY_ID));
                String abbr = cursor.getString(cursor.getColumnIndexOrThrow(ABBR));
                char symbol = cursor.getString(cursor.getColumnIndexOrThrow(SYMBOL)).charAt(0);

                Currency currency = new Currency(abbr, symbol);
                currency.setCurrencyId(currencyId);
                currencies.add(currency);
            }

            cursor.close();
        }
        return currencies;
    }

    /**
     * To automatically use the category name as text in the spinners.
     *
     * @return
     */
    @Override
    public String toString() {
        return abbr + " / " + symbol;
    }

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
