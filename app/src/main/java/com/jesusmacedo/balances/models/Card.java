package com.jesusmacedo.balances.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.jesusmacedo.balances.database.DatabaseAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Card implements Serializable {

    public static final String TABLE_NAME = "Card";
    public static final String CARD_ID = "cardId";
    public static final String CARD_NAME ="name";
    public static final String CURRENT_BALANCE = "current_balance";
    public static final String CURRENCY = "currency";
    public static final String TYPE = "type";
    public static final String CREDIT_LIMIT = "credit_limit";
    public static final String CLOSING_DATE = "closing_date";
    public static final String DUE_DATE = "due_date";
    public static final String REMINDER_DAYS = "reminder_days";

    private long cardId;
    private String name;
    private double currentBalance;
    private String currency;
    private String type;
    private double creditLimit;
    private int closingDate;
    private int dueDate;
    private int reminderDays;

    public Card(String name, double currentBalance, String currency, String type, double creditLimit, int closingDate, int dueDate, int reminderDays) {
        this.name = name;
        this.currentBalance = currentBalance;
        this.currency = currency;
        this.type = type;
        this.creditLimit = creditLimit;
        this.closingDate = closingDate;
        this.dueDate = dueDate;
        this.reminderDays = reminderDays;
    }

    /**
     * Store new card.
     * @param context
     * @param card
     * @return
     */
    public static long addNewCard(Context context, Card card) {
        ContentValues values = new ContentValues();
        values.put("name", card.getName());
        values.put("current_balance", card.getCurrentBalance());
        values.put("currency", card.getCurrency());
        values.put("type", card.getType());
        values.put("credit_limit", card.getCreditLimit());
        values.put("closing_date", card.getClosingDate());
        values.put("due_date", card.getDueDate());
        values.put("reminder_days", card.getReminderDays());

        long cardId = DatabaseAdapter.getDB(context).insert(TABLE_NAME, null, values);
        Period period = new Period(Calendar.MONTH +1, Calendar.YEAR, card.getCurrentBalance(), cardId);
        Period.addNewPeriodToCard(context, period);

        return cardId;
    }

    /**
     * Get all cards stored.
     * @param context
     * @return
     */
    public static List<Card> getCards(Context context) {
        List<Card> cards = new ArrayList<>();

        Cursor cursor = DatabaseAdapter.getDB(context).query(TABLE_NAME, null, null, null, null, null, CARD_ID);

        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                int cardId = cursor.getInt(cursor.getColumnIndexOrThrow(CARD_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(CARD_NAME));
                double currentBalance = cursor.getDouble(cursor.getColumnIndexOrThrow(CURRENT_BALANCE));
                String currency = cursor.getString(cursor.getColumnIndexOrThrow(CURRENCY));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(TYPE));
                double creditLimit = cursor.getDouble(cursor.getColumnIndexOrThrow(CREDIT_LIMIT));
                int closingDate = cursor.getInt(cursor.getColumnIndexOrThrow(CLOSING_DATE));
                int dueDate = cursor.getInt(cursor.getColumnIndexOrThrow(DUE_DATE));
                int reminderDays = cursor.getInt(cursor.getColumnIndexOrThrow(REMINDER_DAYS));

                Card card = new Card(name, currentBalance, currency, type, creditLimit, closingDate, dueDate, reminderDays);
                card.setCardId(cardId);
                cards.add(card);
            }
            Log.e("DFA", "Size: " + cards.size());
            cursor.close();
        }
        return cards;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public int getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(int closingDate) {
        this.closingDate = closingDate;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public int getReminderDays() {
        return reminderDays;
    }

    public void setReminderDays(int reminderDays) {
        this.reminderDays = reminderDays;
    }
}
