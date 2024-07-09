package com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.bongoacademy.addData;
import com.bongoacademy.digitalmoneybag.MainActivity;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper( @Nullable Context context) {super(context, "Digital_Moneybag", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table expense (id INTEGER primary key autoincrement, amount DOUBLE, reason TEXT, time DOUBLE)");
        db.execSQL("create table income (id INTEGER primary key autoincrement, amount DOUBLE, reason TEXT, time DOUBLE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("drop table if exists expense");
       db.execSQL("drop table if exists income");

    }

    //addExpense
    public void addExpense(double amount, String reason){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount",amount);
        contentValues.put("reason",reason);
        contentValues.put("time",System.currentTimeMillis());
        sqLiteDatabase.insert("expense",null,contentValues);

   }

    //addIncome
    public void addIncome(double amount, String reason){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount",amount);
        contentValues.put("reason",reason);
        contentValues.put("time",System.currentTimeMillis());
        sqLiteDatabase.insert("income",null,contentValues);

    }





    public double  calculateTotalExpense(){
       double TotalExpenseAmount = 0;

       SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from expense",null);

        if (cursor!=null && cursor.getCount()>0){

            while (cursor.moveToNext()){
                double ExpenseAmount = cursor.getDouble(1);
                //String ExpenseAmount = String.valueOf(cursor.getDouble(1));
                TotalExpenseAmount = TotalExpenseAmount+ExpenseAmount;
            }
        }


        return TotalExpenseAmount;



   }


    public double  calculateTotalIncome(){
        double TotalIncomeAmount = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from income",null);

        if (cursor!=null && cursor.getCount()>0){

            while (cursor.moveToNext()){
                double IncomeAmount = cursor.getDouble(1);
                //String ExpenseAmount = String.valueOf(cursor.getDouble(1));
                TotalIncomeAmount = TotalIncomeAmount+IncomeAmount;
            }
        }


        return TotalIncomeAmount;



    }

    public void getExpenseIdDelete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from expense where id like "+id);
    }

    public void getIncomeIdDelete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from income  where id like "+id);
    }





    public Cursor getAllData (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from expense",null);
        return cursor;

    }

    public Cursor getAllIncomeData (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from income",null);
        return cursor;

    }


    public Cursor SearchDataExpense(String key){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from expense where word like '%"+key+"%' ",null);
        return cursor;


    }









}
