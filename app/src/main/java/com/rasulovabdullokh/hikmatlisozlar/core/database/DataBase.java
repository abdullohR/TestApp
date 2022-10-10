package com.rasulovabdullokh.hikmatlisozlar.core.database;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.Nullable;

import com.rasulovabdullokh.hikmatlisozlar.core.lib.DbHelper;
import com.rasulovabdullokh.hikmatlisozlar.core.models.HikmatliGaplar;

import java.util.ArrayList;

public class DataBase extends DbHelper {

    public static DataBase dataBase;

    public DataBase(@Nullable Context context) {
        super(context, "quotes.db");

    }

    public static void init(Context context) {
        if (dataBase == null) {
            dataBase = new DataBase(context);
        }
    }

    public static DataBase getDataBase() {
        return dataBase;
    }

    public ArrayList<HikmatliGaplar> getSozlar() {
        ArrayList<HikmatliGaplar> gaplar = new ArrayList<>();
        String query = "SELECT * FROM QUOTE_TABLE";
        Cursor cursor = mDataBase.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String author = cursor.getString(1);
            String sozlar = cursor.getString(2);
            HikmatliGaplar hikmatliGaplar = new HikmatliGaplar(author, sozlar);
            gaplar.add(hikmatliGaplar);
            cursor.moveToNext();
        }
        return gaplar;
    }
}
