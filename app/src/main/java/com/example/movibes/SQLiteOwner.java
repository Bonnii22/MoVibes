package com.example.movibes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import java.sql.Time;
import java.util.Date;


public class SQLiteOwner extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME = "Owner.com";

    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;

    //TABLE NAME
    public static final String TABLE_OWNER = "owners";

    //TABLE OWNER COLUMNS
    public static final String KEY_ID = "id";

    private static Integer profileimage;
    public static final Integer KEY_PROFILEIMAGE = profileimage;

    //COLUMN establishmentname
    public static final String KEY_ESTABLISHMENTNAME = "establishmentname";

    public static final String KEY_ADDRESS = "address";

    public static final String KEY_NAME = "name";

    public static final String KEY_SURNAME = "surmane";

    private static Integer contactnumber;
    public static final Integer KEY_CONTACTNUMBER = contactnumber;





    //SQL for creating users table
    public static final String SQL_TABLE_OWNER = " CREATE TABLE " + TABLE_OWNER
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + " TEXT, "
            + KEY_PROFILEIMAGE + " TEXT, "
            + KEY_ESTABLISHMENTNAME + " TEXT,"
            + KEY_ADDRESS + " TEXT,"
            + KEY_NAME + " TEXT,"
            + KEY_SURNAME + " TEXT,"
            + KEY_CONTACTNUMBER + " TEXT"

            + " ) ";


    public SQLiteOwner(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteOwner(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_OWNER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_OWNER);
    }

    //using this method we can add users to user table
    public void addUser(Owner owner) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();


        //Put email in  @values
        values.put(KEY_PROFILEIMAGE.toString(), owner.profileimage);
        //Put password in  @values
        values.put(KEY_ESTABLISHMENTNAME, owner.establishmentname);
        values.put(KEY_ADDRESS, owner.address);
        values.put(KEY_NAME, owner.name);
        values.put(KEY_SURNAME, owner.surname);
        values.put(KEY_CONTACTNUMBER.toString(), owner.contactnumber);



        // insert row
        long todo_id = db.insert(TABLE_OWNER, null, values);
    }

    public Owner Authenticate(Owner owner) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_OWNER,// Selecting Table
                new String[]{KEY_ID, KEY_PROFILEIMAGE.toString(), KEY_ESTABLISHMENTNAME, KEY_ADDRESS, KEY_NAME, KEY_SURNAME, KEY_CONTACTNUMBER.toString()},//Selecting columns want to query
                KEY_PROFILEIMAGE + "=?",
                new String[]{owner.establishmentname},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            //if cursor has value then in user database there is user associated with this given email
            Owner owner1 = new Owner(cursor.getString(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5), cursor.getInt(6));

            //Match both passwords check they are same or not
            if (owner.establishmentname.equalsIgnoreCase(owner1.establishmentname)) {
                return owner1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEstablishmentnameExists(String establishmentname) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_OWNER,// Selecting Table
                new String[]{KEY_ID, KEY_ESTABLISHMENTNAME},//Selecting columns want to query
                KEY_ESTABLISHMENTNAME + "=?",
                new String[]{establishmentname},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }
    public void insertData(byte[] image, String description, int venue, Spinner spinner, String lineup, String fee, String date, String time){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO COMPLAINTS VALUES (NULL, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1, image);
        statement.bindString(2, description);
        statement.bindString(3, String.valueOf(venue));
        statement.bindString(4, spinner.toString());
        statement.bindString(5, lineup);
        statement.bindString(6, fee);
        statement.bindString(7, date);
        statement.bindString(8, time);

        statement.executeInsert();
    }
    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
}
