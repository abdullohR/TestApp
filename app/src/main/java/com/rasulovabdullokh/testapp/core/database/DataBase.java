package com.rasulovabdullokh.testapp.core.database;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.rasulovabdullokh.testapp.core.lib.DbHelper;
import com.rasulovabdullokh.testapp.core.models.HikmatliGaplar;

import java.util.ArrayList;

public class DataBase  extends DbHelper {
    public static DataBase dataBase;
    public DataBase(@Nullable Context context) {
        super(context,"sozlar.db");

    }
    public static void init(Context context){
        if(dataBase==null){
            dataBase= new DataBase(context);
        }
    }

    public static DataBase getDataBase() {
        return dataBase;
    }

    public ArrayList<HikmatliGaplar> getSozlar(){
        ArrayList<HikmatliGaplar> gaplar = new ArrayList<>();
        String query = "SELECT * FROM azzamaxshariy";
        Cursor cursor = mDataBase.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String sozlar = cursor.getString(1);
            HikmatliGaplar hikmatliGaplar = new HikmatliGaplar(id,sozlar);
            gaplar.add(hikmatliGaplar);
            cursor.moveToNext();
        }
        return gaplar;
    }
}
