package com.example.max.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.example.max.phonebook.DBHelper.COLUMN_ID;
import static com.example.max.phonebook.DBHelper.TABLE_NAME;

/**
 * Created by Max on 18.03.2018.
 */

public class ContactsHelper {

    SQLiteDatabase db;

    ContactsHelper(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    long insert(String name, String phone, String birthday) {
        ContentValues cv = new ContentValues();

        cv.put(DBHelper.COLUMN_NAME, name);
        cv.put(DBHelper.COLUMN_PHONE, phone);
        cv.put(DBHelper.COLUMN_BIRTHDAY, birthday);

        return db.insert(TABLE_NAME, null, cv);
    }

    public int update(Contact md) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_NAME, md.getName());
        cv.put(DBHelper.COLUMN_PHONE, md.getPhone());
        cv.put(DBHelper.COLUMN_BIRTHDAY, md.getBirthday());
        return db.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{String.valueOf(md.getId())});
    }

    public void delete(long id) {
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }




    ArrayList<Contact> getAll() {

        Cursor mCursor = db.query(TABLE_NAME, null, null, null, null, null,
                null);
        ArrayList<Contact> arr = new ArrayList<>();

        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {


            do {
                long id = mCursor.getLong(DBHelper.NUM_COLUMN_ID);
                String name = mCursor.getString(DBHelper.NUM_COLUMN_NAME);
                String phone = mCursor.getString(DBHelper.NUM_COLUMN_PHONE);
                String birthday = mCursor.getString(DBHelper.NUM_COLUMN_BIRTHDAY);


                arr.add(new Contact(id, name, phone, birthday));

            } while (mCursor.moveToNext());
        }
        db.close();
        return arr;
    }

}