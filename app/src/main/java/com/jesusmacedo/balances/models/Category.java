package com.jesusmacedo.balances.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.jesusmacedo.balances.database.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Category of records.
 */

public class Category {

    public static final String TABLE_NAME = "Category";
    public static final String CATEGORY_ID = "categoryId";
    public static final String NAME = "name";
    public static final String DESC = "desc";

    private long categoryId;
    private String name;
    private String desc;

    public Category(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    /**
     * Create new record category.
     *
     * @param context
     * @param category
     * @return
     */
    public static long addNewCategory(Context context, Category category) {
        ContentValues values = new ContentValues();
        values.put("name", category.getName());
        values.put("desc", category.getDesc());

        return DatabaseAdapter.getDB(context).insert(TABLE_NAME, null, values);
    }

    /**
     * Update the received category by its id.
     *
     * @param context
     * @param category
     * @return
     */
    public static long updateCategoryById(Context context, Category category) {
        ContentValues cv = new ContentValues();
        cv.put(NAME, category.getName());
        cv.put(DESC, category.getDesc());

        return DatabaseAdapter.getDB(context).update(TABLE_NAME, cv, CATEGORY_ID + "=" + category.getCategoryId(), null);
    }

    /**
     * Get all the categories.
     *
     * @param context
     * @return
     */
    public static List<Category> getCategories(Context context) {
        List<Category> categories = new ArrayList<>();

        Cursor cursor = DatabaseAdapter.getDB(context).query(TABLE_NAME, null, null, null, null, null, CATEGORY_ID);

        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                int categoryId = cursor.getInt(cursor.getColumnIndexOrThrow(CATEGORY_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow(DESC));

                Category category = new Category(name, desc);
                category.setCategoryId(categoryId);
                categories.add(category);
            }

            cursor.close();
        }
        return categories;
    }

    /**
     * To automatically use the category name as text in the spinners.
     *
     * @return
     */
    @Override
    public String toString() {
        return name;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
