package com.example.stepcounter.chart;

import static com.example.stepcounter.chart.ChartContract.ChartInfo.CHART_TABLE;
import static com.example.stepcounter.chart.ChartContract.ChartInfo.CHART_BMI;
import static com.example.stepcounter.chart.ChartContract.ChartInfo.CHART_DATE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class ChartDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "chartdatabase.db";
    private static final int DATABASE_VERSION = 2;
    String tag = "Database Operation";
    private static final String CREATE_QUERY = "CREATE TABLE "+ CHART_TABLE +" ("+CHART_DATE+" varchar, "+CHART_BMI+" varchar)";


    public ChartDbHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);

        Log.e(tag, "Database Created/ Opened ...");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e(tag,"Table Created ...");
    }

    //Adding a record to a databse
    public void addData(String chartdate,String chartbmi, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();

        contentValues.put(CHART_DATE,chartdate);
        contentValues.put(CHART_BMI,chartbmi);
        db.insert(CHART_TABLE,null,contentValues);
        Log.e(tag,"One row Inserted successfully....");
    }

    //getting all the contents of the table
    public Cursor getInformation(SQLiteDatabase db){

        Cursor result;
        String [] Projections = {CHART_DATE,CHART_BMI};
        result = db.query(CHART_TABLE,Projections,null,null,null,null,null);

        return result;
    }

    //Get password for a particular user
    public Cursor getPassword(String userName,SQLiteDatabase db){

        String Projections[] = {CHART_DATE,CHART_BMI};
        String selection = CHART_DATE + " LIKE ?";
        String selection_args []= {userName};
        Cursor result =db.query(CHART_TABLE,Projections,selection,selection_args,null,null,null);

        return result;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
