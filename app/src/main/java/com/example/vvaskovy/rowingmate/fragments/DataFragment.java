package com.example.vvaskovy.rowingmate.fragments;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vvaskovy.rowingmate.DatabaseHelper;
import com.example.vvaskovy.rowingmate.GreedActivity;
import com.example.vvaskovy.rowingmate.MenuBar;
import com.example.vvaskovy.rowingmate.R;


public class DataFragment extends Fragment {


    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4, rb;
    private EditText name;
    private Button zapisz;
    private String imie;
    private String poziom;
    private DatabaseHelper db;
    private TextView glowne_imie ,glowne_poziom;
    private OnFragmentInteractionListener mListener;

    public DataFragment() {
        // Required empty public constructor
    }


    public static DataFragment newInstance(String param1, String param2) {
        DataFragment fragment = new DataFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_data, container, false);
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        glowne_imie = (TextView) getActivity().findViewById(R.id.glowne_imie);
        glowne_poziom = (TextView) getActivity().findViewById(R.id.glowne_poziom);
        radioGroup = (RadioGroup) getView().findViewById(R.id.radioGroup);
        zapisz = (Button) getView().findViewById(R.id.zapiszDane);
        name = (EditText) getView().findViewById(R.id.imie);
        DatabaseHelper db = new DatabaseHelper(getActivity());
        final SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();

        //Odczytuje dane z bazy danych by ustawić początkowy wygląd
        String imieDoUstwaienia="";
        int poziomDoUstawienia=-1;
        rb1 = (RadioButton) getView().findViewById(R.id.radioButton1);
        rb2 = (RadioButton) getView().findViewById(R.id.radioButton2);
        rb3 = (RadioButton) getView().findViewById(R.id.radioButton3);
        rb4 = (RadioButton) getView().findViewById(R.id.radioButton4);
        Cursor cursor = sqLiteDatabase.query("Uzytkownik",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                imieDoUstwaienia = cursor.getString(cursor.getColumnIndex("imie"));
                poziomDoUstawienia = cursor.getInt(cursor.getColumnIndex("poziom"));
                //Log.d("Log", "Imie "+cursor.getString(cursor.getColumnIndex("imie"))+" poziom "+cursor.getInt(cursor.getColumnIndex("poziom")) );
            }while(cursor.moveToNext());
        }else{
            Log.d("log","Pusta tablica");
        }
        name.setText(imieDoUstwaienia);
        if(poziomDoUstawienia == 1){
            rb1.toggle();
        }else{
            if(poziomDoUstawienia == 2){
                rb2.toggle();
            }else{
                if(poziomDoUstawienia==3){
                    rb3.toggle();
                }else{
                    if(poziomDoUstawienia==4){
                        rb4.toggle();
                    }
                }
            }
        }
        //rb2 = (RadioButton) getView().findViewById(R.id.);
        zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imie = name.getText().toString();
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                rb = (RadioButton) getView().findViewById(radioButtonId);
                poziom = rb.getText().toString();
                int poziomDB=0;
                String s = "Początkujący";
                if(poziom.equals(s)){
                    poziomDB=1;
                }else{
                    if(poziom.equals("Amator")){
                        poziomDB=2;
                    }else {
                        if(poziom.equals("Zaawansowany")){
                            poziomDB=3;
                        }else{
                            if(poziom.equals("Mistrz")){
                                poziomDB=4;
                            }
                        }
                    }
                }
                glowne_imie.setText(imie);
                glowne_poziom.setText(poziom);
                sqLiteDatabase.execSQL("delete from Uzytkownik");
                ContentValues contentValues = new ContentValues();
                contentValues.put("imie", imie);
                contentValues.put("poziom", poziomDB);
                sqLiteDatabase.insert("Uzytkownik", null, contentValues);
                Log.d("Log","Data inserted");
                Toast.makeText(getActivity(),imie+", twoje dane zapisane", Toast.LENGTH_LONG).show();
                Cursor cursor = sqLiteDatabase.query("Uzytkownik",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        Log.d("Log", "Imie "+cursor.getString(cursor.getColumnIndex("imie"))+" poziom "+cursor.getInt(cursor.getColumnIndex("poziom")) );
                    }while(cursor.moveToNext());
                }else{
                    Log.d("log","Pusta tablica");
                }
                cursor.close();

                startActivity(new Intent(getActivity(), MenuBar.class));


            }
        });


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
