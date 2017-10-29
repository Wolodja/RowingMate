package com.example.vvaskovy.rowingmate.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vvaskovy.rowingmate.DatabaseHelper;
import com.example.vvaskovy.rowingmate.Interwal;
import com.example.vvaskovy.rowingmate.R;
import com.example.vvaskovy.rowingmate.Trening;

import java.util.ArrayList;


public class Training2Fragment extends Fragment {



    private String textSQL;
    private DatabaseHelper db;
    ArrayList<Trening> treningi;



    private OnFragmentInteractionListener mListener;

    public Training2Fragment() {
        // Required empty public constructor
    }

    public void setTextSQL(String textSQL) {
        this.textSQL = textSQL;
    }

    public static Training2Fragment newInstance(String param1, String param2) {
        Training2Fragment fragment = new Training2Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training2, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Toast.makeText(getActivity(), textSQL, Toast.LENGTH_LONG).show();
        treningi = new ArrayList<Trening>();
        db = new DatabaseHelper(getActivity());
        final SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();


        Cursor cursor = sqLiteDatabase.rawQuery(textSQL, null);

        if(cursor.moveToFirst()){
            do{
                Log.d("Log", "idTreningu "+cursor.getInt(cursor.getColumnIndex("idTreningu"))+" sposob "+
                        cursor.getString(cursor.getColumnIndex("sposobTreningu"))+
                        " czas "+ cursor.getString(cursor.getColumnIndex("czasInterwalu"))+
                        "dystans "+ cursor.getInt(cursor.getColumnIndex("dystansInterwalu")));

                Interwal i = new Interwal(cursor.getInt(cursor.getColumnIndex("nrInterwalu")), cursor.getString(cursor.getColumnIndex("czasInterwalu")),
                        cursor.getString(cursor.getColumnIndex("mocInterwalu")), cursor.getString(cursor.getColumnIndex("tempoInterwalu")),
                        cursor.getString(cursor.getColumnIndex("dystansInterwalu")));

                Trening t = new Trening(cursor.getInt(cursor.getColumnIndex("idTreningu")),
                        cursor.getString(cursor.getColumnIndex("dataTreningu")), cursor.getString(cursor.getColumnIndex("sposobTreningu")));

                t.addInterwal(i);
                treningi.add(t);

            }while(cursor.moveToNext());
        }else{
            Log.d("log","Pusta tablica");
        }


    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
