package com.jesusmacedo.balances.models;

import android.content.ContentValues;
import android.content.Context;

import com.jesusmacedo.balances.database.DatabaseAdapter;

import java.util.Date;

/**
 * Records.
 */

public class Record {

    private static final String TABLE_NAME = "Record";
    private static final String RECORD_ID = "recordId";
    private static final String TYPE = "type";
    private static final String AMOUNT = "amount";
    private static final String CATEGORY = "category";
    private static final String DESC = "desc";
    private static final String DATE = "date";
    private static final String MSI = "msi";
    private static final String CURRENCY = "currency";
    private static final String IS_PLANED = "isPlaned";
    private static final String CARD_ID = "card_cardId";
    private static final String PERIOD_ID = "period_periodId";

    private long recordId;
    private int type;
    private double amount;
    private long category;
    private String desc;
    private Date date;
    private int msi;
    private long currency;
    private int isPlaned;
    private long cardId;
    private long periodId;

    public Record(int type, double amount, long category, String desc, Date date, int msi, long currency, int isPlaned, long cardId, long periodId) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.desc = desc;
        this.date = date;
        this.msi = msi;
        this.currency = currency;
        this.isPlaned = isPlaned;
        this.cardId = cardId;
        this.periodId = periodId;
    }

    public static long addNewRecord(Context context, Record record) {
        ContentValues values = new ContentValues();
        values.put("type", record.getType());
        values.put("amount", record.getAmount());
        values.put("category", record.getCategory());
        values.put("desc", record.getDesc());
        values.put("date", record.getDate().toString());
        values.put("msi", record.getMsi());
        values.put("currency", record.getCurrency());
        values.put("isPlaned", record.getIsPlaned());
        values.put("period_periodId", record.getCardId());
        values.put("card_cardId", record.getCardId());

        return DatabaseAdapter.getDB(context).insert(TABLE_NAME, null, values);
    }

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMsi() {
        return msi;
    }

    public void setMsi(int msi) {
        this.msi = msi;
    }

    public long getCurrency() {
        return currency;
    }

    public void setCurrency(long currency) {
        this.currency = currency;
    }

    public int getIsPlaned() {
        return isPlaned;
    }

    public void setIsPlaned(int isPlaned) {
        this.isPlaned = isPlaned;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(long periodId) {
        this.periodId = periodId;
    }
}
