package com.example.finalapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shop.db";
    private static final int DATABASE_VERSION = 3;

    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_QUANTITY = "quantity";

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    private static final String TABLE_WAITING_LIST = "waiting_list";
    private static final String COLUMN_DATE_TIME = "date_time";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_PRICE + " REAL," +
                COLUMN_IMAGE + " TEXT," +
                COLUMN_QUANTITY + " INTEGER DEFAULT 0)";
        db.execSQL(CREATE_PRODUCTS_TABLE);

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_WAITING_LIST_TABLE = "CREATE TABLE " + TABLE_WAITING_LIST + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_QUANTITY + " INTEGER, " +
                COLUMN_DATE_TIME + " TEXT)";
        db.execSQL(CREATE_WAITING_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_PRODUCTS + " ADD COLUMN " + COLUMN_QUANTITY + " INTEGER DEFAULT 0");
        }
        if (oldVersion < 3) {
            db.execSQL("CREATE TABLE " + TABLE_WAITING_LIST + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_QUANTITY + " INTEGER, " +
                    COLUMN_DATE_TIME + " TEXT)");
        }
    }

    public void addToWaitingList(String title, int quantity, String dateTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_DATE_TIME, dateTime);
        db.insert(TABLE_WAITING_LIST, null, values);
        db.close();
    }

    // Get Waiting List
    public List<WaitingListItem> getAllWaitingListItems() {
        List<WaitingListItem> waitingList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_WAITING_LIST, null);

        if (cursor.moveToFirst()) {
            do {
                WaitingListItem item = new WaitingListItem(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE_TIME))
                );
                waitingList.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return waitingList;
    }

    //  Clear Waiting List (Delete History)
    public void clearWaitingList() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WAITING_LIST, null, null);
        db.close();
    }




    //  **Fixed: Insert or Update Product**
    public void addOrUpdateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, product.getId());
        values.put(COLUMN_TITLE, product.getTitle());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_IMAGE, product.getImageUrl());
        values.put(COLUMN_QUANTITY, product.getQuantity());

        db.insertWithOnConflict(TABLE_PRODUCTS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    // **Fixed: Get All Products**
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                product.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
                product.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
                product.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
                product.setImageUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)));
                product.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY))); // ✅ Fixed quantity fetching

                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList;
    }

    // **Fixed: Update Product Quantity**
    public void updateProductQuantity(int productId, int newQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUANTITY, newQuantity);

        db.update(TABLE_PRODUCTS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(productId)});
        db.close();
    }

    // **Get Single Product by ID**
    public Product getProductById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCTS,
                new String[]{COLUMN_ID, COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_PRICE, COLUMN_IMAGE, COLUMN_QUANTITY},
                COLUMN_ID + " = ?", new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Product product = new Product();
            product.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            product.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
            product.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
            product.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
            product.setImageUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)));
            product.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));

            cursor.close();
            db.close();
            return product;
        }

        if (cursor != null) cursor.close();
        db.close();
        return null;
    }

    // **Delete Product**
    public boolean deleteProduct(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?", new String[]{String.valueOf(productId)});
        db.close();
        return rowsDeleted > 0;
    }

    // **Clear Cart (Reset Quantities)**
    public void clearCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUANTITY, 0);

        db.update(TABLE_PRODUCTS, values, null, null);
        db.close();
    }

    // **User Methods**
    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());

        long userId = db.insert(TABLE_USERS, null, values);
        db.close();
        return userId;
    }

    public User getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COLUMN_ID, COLUMN_USERNAME, COLUMN_PASSWORD},
                COLUMN_USERNAME + " = ?", new String[]{username},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            User user = new User(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
            );

            cursor.close();
            db.close();
            return user;
        }

        if (cursor != null) cursor.close();
        db.close();
        return null;
    }
}
