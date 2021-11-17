package com.example.movibes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class SQLiteUser extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME = "user.com";

    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;

    //TABLE NAME
    public static final String TABLE_USERS = "users";

    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "id";
    public static final String KEY_PROFILEPIC = "profilepic";
    public static final String KEY_NAME = "name";

    public static final String KEY_SURNAME = "surname";

    public static final String KEY_GENDER = "gender";

    public static final String KEY_CITYTOWN = "citytown";

    public static final String KEY_INTERESTS = "interests";


    //SQL for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + " TEXT, "
            + KEY_PROFILEPIC+ " TEXT, "
            + KEY_NAME + " TEXT,"
            + KEY_SURNAME + " TEXT,"
            + KEY_GENDER + " TEXT,"
            + KEY_CITYTOWN + " TEXT,"
            + KEY_INTERESTS + " TEXT"

            + " ) ";


    public SQLiteUser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteUser(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    //using this method we can add users to user table
    public void addUser(User user) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();


        //Put email in  @values
        values.put(KEY_PROFILEPIC, user.profileimage);

        //Put password in  @values
        values.put(KEY_NAME, user.name);
        values.put(KEY_SURNAME, user.surname);
        values.put(KEY_GENDER, user.gender);
        values.put(KEY_CITYTOWN, user.citytown);
        values.put(KEY_INTERESTS, user.interests);


        // insert row
        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_SURNAME, KEY_NAME},//Selecting columns want to query
                KEY_SURNAME + "=?",
                new String[]{user.surname},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));

            //Match both passwords check they are same or not
            if (user.surname.equalsIgnoreCase(user1.surname)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isUSERExists(String surname) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_NAME, KEY_SURNAME},//Selecting columns want to query
                KEY_SURNAME + "=?",
                new String[]{surname},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }
}
