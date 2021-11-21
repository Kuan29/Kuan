package com.example.stepcounter.database;

import static com.example.stepcounter.database.UserContract.NewUserInfo.TABLE_NAME;
import static com.example.stepcounter.database.UserContract.NewUserInfo.USER_NAME;
import static com.example.stepcounter.database.UserContract.NewUserInfo.USER_PASSWORD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Admin on 09-04-2015.
 */
public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ruthvikdatabase.db";
    private static final int DATABASE_VERSION = 1;
    String tag = "Database Operation";
    private static final String CREATE_QUERY = "CREATE TABLE "+TABLE_NAME +" ("+USER_NAME+" varchar, "+USER_PASSWORD+" varchar)";


    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);

        Log.e(tag, "Database Created/ Opened ...");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e(tag,"Table Created ...");
    }

    //Adding a record to a databse
    public void addData(String username,String userpassword,SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_NAME,username);
        contentValues.put(USER_PASSWORD,userpassword);

        db.insert(TABLE_NAME,null,contentValues);
        Log.e(tag,"One row Inserted successfully....");
    }

    //getting all the contents of the table
    public Cursor getInformation(SQLiteDatabase db){

        Cursor result;
        String [] Projections = {USER_NAME,USER_PASSWORD};
        result = db.query(TABLE_NAME,Projections,null,null,null,null,null);

        return result;
    }

    //Get password for a particular user
    public Cursor getPassword(String userName,SQLiteDatabase db){

        String Projections[] = {USER_NAME,USER_PASSWORD};
        String selection = USER_NAME + " LIKE ?";
        String selection_args []= {userName};
        Cursor result =db.query(TABLE_NAME,Projections,selection,selection_args,null,null,null);

        return result;
    }

    //Delete a record of a particular user
    public int deleteUser(String userName,SQLiteDatabase db){
        String selection = USER_NAME + " LIKE ?";
        String[] selection_args ={userName};
        int result = db.delete(TABLE_NAME,selection,selection_args);

        return result;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
