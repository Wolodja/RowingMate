package com.example.vvaskovy.rowingmate;

import android.content.ContentValues;
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

        db.execSQL("create table Trening(idTreningu integer primary key autoincrement, sposobTreningu text not null, dataTreningu text not null );");

        db.execSQL("create table Interwal(idTreningu integer, nrInterwalu integer not null, czasInterwalu text not null," +
                " mocInterwalu integer,tempoInterwalu integer, dystansInterwalu integer," +
                " FOREIGN KEY (idTreningu) REFERENCES Trening(idTrening), primary key (idTreningu, nrInterwalu));");

        db.execSQL("create table Plan(idPlanu integer primary key autoincrement, " +
                "czasPlanu integer, typPlanu text, poziomUzytkownika integer, opisPlanu text);");

        //Wypełniam tabele Plan
        ContentValues contentValues = new ContentValues();

        contentValues.put("czasPlanu", 30);
        contentValues.put("typPlanu", "moc");
        contentValues.put("poziomUzytkownika", 1);
        contentValues.put("opisPlanu", "3 interwaly po 4 minut, 5 minuty przerwy po każdym interwale, moc 150 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 30);
        contentValues.put("typPlanu", "moc");
        contentValues.put("poziomUzytkownika", 2);
        contentValues.put("opisPlanu", "3 interwaly po 4 minut, 5 minuty przerwy po każdym interwale, moc 280 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 30);
        contentValues.put("typPlanu", "moc");
        contentValues.put("poziomUzytkownika", 3);
        contentValues.put("opisPlanu", "3 interwaly po 4 minut, 5 minuty przerwy po każdym interwale, moc 350 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 30);
        contentValues.put("typPlanu", "moc");
        contentValues.put("poziomUzytkownika", 4);
        contentValues.put("opisPlanu", "3 interwaly po 4 minut, 5 minuty przerwy po każdym interwale, moc 450 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 60);
        contentValues.put("typPlanu", "moc");
        contentValues.put("poziomUzytkownika", 1);
        contentValues.put("opisPlanu", "5 interwalów po 5 minut, 6 minuty przerwy po każdym interwale, moc 150 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 60);
        contentValues.put("typPlanu", "moc");
        contentValues.put("poziomUzytkownika", 2);
        contentValues.put("opisPlanu", "5 interwalów po 5 minut, 6 minuty przerwy po każdym interwale, moc 250 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 60);
        contentValues.put("typPlanu", "moc");
        contentValues.put("poziomUzytkownika", 3);
        contentValues.put("opisPlanu", "5 interwalów po 5 minut, 6 minuty przerwy po każdym interwale, moc 350 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 60);
        contentValues.put("typPlanu", "moc");
        contentValues.put("poziomUzytkownika", 4);
        contentValues.put("opisPlanu", "5 interwalów po 5 minut, 6 minuty przerwy po każdym interwale, moc 450 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 61);
        contentValues.put("typPlanu", "moc");
        contentValues.put("poziomUzytkownika", 1);
        contentValues.put("opisPlanu", "8 interwalów po 5 minut, 6 minuty przerwy po każdym interwale, moc 150 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 61);
        contentValues.put("typPlanu", "moc");
        contentValues.put("poziomUzytkownika", 2);
        contentValues.put("opisPlanu", "8 interwalów po 5 minut, 6 minuty przerwy po każdym interwale, moc 250 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 61);
        contentValues.put("typPlanu", "moc");
        contentValues.put("poziomUzytkownika", 3);
        contentValues.put("opisPlanu", "8 interwalów po 5 minut, 6 minuty przerwy po każdym interwale, moc 350 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 61);
        contentValues.put("typPlanu", "moc");
        contentValues.put("poziomUzytkownika", 4);
        contentValues.put("opisPlanu", "8 interwalów po 5 minut, 6 minuty przerwy po każdym interwale, moc 450 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 30);
        contentValues.put("typPlanu", "wytrwalosc");
        contentValues.put("poziomUzytkownika", 1);
        contentValues.put("opisPlanu", "2 interwaly po 12 minut, 5 minuty przerwy po każdym interwale, moc 120 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 30);
        contentValues.put("typPlanu", "wytrwalosc");
        contentValues.put("poziomUzytkownika", 2);
        contentValues.put("opisPlanu", "2 interwaly po 12 minut, 5 minuty przerwy po każdym interwale, moc 220 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 30);
        contentValues.put("typPlanu", "wytrwalosc");
        contentValues.put("poziomUzytkownika", 3);
        contentValues.put("opisPlanu", "2 interwaly po 12 minut, 5 minuty przerwy po każdym interwale, moc 320 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 30);
        contentValues.put("typPlanu", "wytrwalosc");
        contentValues.put("poziomUzytkownika", 4);
        contentValues.put("opisPlanu", "2 interwaly po 12 minut, 5 minuty przerwy po każdym interwale, moc 420 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 60);
        contentValues.put("typPlanu", "wytrwalosc");
        contentValues.put("poziomUzytkownika", 1);
        contentValues.put("opisPlanu", "3 interwaly po 15 minut, 5 minuty przerwy po każdym interwale, moc 120 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 60);
        contentValues.put("typPlanu", "wytrwalosc");
        contentValues.put("poziomUzytkownika", 2);
        contentValues.put("opisPlanu", "3 interwaly po 15 minut, 5 minuty przerwy po każdym interwale, moc 220 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 60);
        contentValues.put("typPlanu", "wytrwalosc");
        contentValues.put("poziomUzytkownika", 3);
        contentValues.put("opisPlanu", "3 interwaly po 15 minut, 5 minuty przerwy po każdym interwale, moc 320 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 60);
        contentValues.put("typPlanu", "wytrwalosc");
        contentValues.put("poziomUzytkownika", 4);
        contentValues.put("opisPlanu", "3 interwaly po 15 minut, 5 minuty przerwy po każdym interwale, moc 420 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 61);
        contentValues.put("typPlanu", "wytrwalosc");
        contentValues.put("poziomUzytkownika", 1);
        contentValues.put("opisPlanu", "4 interwaly po 15 minut, 5 minuty przerwy po każdym interwale, moc 120 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 61);
        contentValues.put("typPlanu", "wytrwalosc");
        contentValues.put("poziomUzytkownika", 2);
        contentValues.put("opisPlanu", "4 interwaly po 15 minut, 5 minuty przerwy po każdym interwale, moc 220 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 61);
        contentValues.put("typPlanu", "wytrwalosc");
        contentValues.put("poziomUzytkownika", 3);
        contentValues.put("opisPlanu", "4 interwaly po 15 minut, 5 minuty przerwy po każdym interwale, moc 310 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();

        contentValues.put("czasPlanu", 61);
        contentValues.put("typPlanu", "wytrwalosc");
        contentValues.put("poziomUzytkownika", 4);
        contentValues.put("opisPlanu", "4 interwaly po 15 minut, 5 minuty przerwy po każdym interwale, moc 400 wat");
        db.insert("Plan", null, contentValues);
        contentValues.clear();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
