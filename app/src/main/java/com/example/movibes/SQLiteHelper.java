package com.example.movibes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME = "com.example.movibes";

    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;

    //TABLE NAME
    public static final String TABLE_EVENTS = "COMPLAINTS";

    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "id";

    //COLUMN email
    public static final String KEY_DETAILS = "details";

    //COLUMN password
    public static final String KEY_IMAGE = "image";



    public SQLiteHelper(Context context,String Details, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Details, factory, version);
    }

    public void insertData(String Details, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO COMPLAINTS VALUES (NULL, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, Details);
        statement.bindBlob(2, image);

        statement.executeInsert();
    }

    public void queryData(String sql, byte[]image){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void updateData(String Details,byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE COMPLAINTS SET Details = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, Details);
        statement.bindBlob(2, image);
        statement.bindDouble(3, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM COMPLAINTS WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_EVENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_EVENTS);
    }







}
