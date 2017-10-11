package com.example.vvaskovy.rowingmate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VVASKOVY on 13.07.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, "rm", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Uzytkownik(imie text primary key, poziom integer );");
        db.execSQL("create table Traning(id integer primary key autoincrement, czas time, moc integer, tempo integer, typ text, dystans integer, data date );");
        db.execSQL("create table Plan(id integer primary key autoincrement, czas integer, typ text, opis text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
