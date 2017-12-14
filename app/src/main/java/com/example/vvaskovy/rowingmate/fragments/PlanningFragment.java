package com.example.vvaskovy.rowingmate.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vvaskovy.rowingmate.Advice;
import com.example.vvaskovy.rowingmate.DatabaseHelper;
import com.example.vvaskovy.rowingmate.R;

import io.realm.Realm;
import io.realm.RealmResults;


public class PlanningFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private ArrayAdapter<CharSequence> adapter1, adapter2, adapter3;
    private Button planuj;
    private Spinner spinner1, spinner2, spinner3;
    private Planning2Fragment planning2Fragment;
    private DatabaseHelper db;
    private int poziomDoUstawienia=-1;



    public PlanningFragment() {
        // Required empty public constructor
    }


    public static PlanningFragment newInstance(String param1, String param2) {
        PlanningFragment fragment = new PlanningFragment();
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
        showAdvice();
        return inflater.inflate(R.layout.fragment_planning, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       // showAdvice();

        spinner1 = (Spinner) getView().findViewById(R.id.spinner1);
        adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.two_types, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner2 = (Spinner) getView().findViewById(R.id.spinner2);
        adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.time_types, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner3 = (Spinner) getView().findViewById(R.id.spinner3);
        adapter3 = ArrayAdapter.createFromResource(getActivity(),
                R.array.typ_types, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        planuj = (Button) getView().findViewById(R.id.planuj);

        db = new DatabaseHelper(getActivity());
        final SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("Uzytkownik",null,null,null,null,null,null);
        poziomDoUstawienia=-1;
        if(cursor.moveToFirst()){
            do{
                poziomDoUstawienia = cursor.getInt(cursor.getColumnIndex("poziom"));
                Log.d("Log", "poziom "+cursor.getInt(cursor.getColumnIndex("poziom")) );
            }while(cursor.moveToNext());
        }else{
            Log.d("log","Pusta tablica");
        }
        cursor.close();



        planuj.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String text1 = spinner1.getSelectedItem().toString();
                String text2 = spinner2.getSelectedItem().toString();
                String text3 = spinner3.getSelectedItem().toString();

                if(text3.equals("Wytrwałość"))
                    text3="wytrwalosc";
                else
                    text3="moc";

                if(text2.equals("10-30"))
                    text2="30";
                else
                    if(text2.equals("30-60"))
                        text2="60";
                    else
                        text2="61";


                Log.d("log", poziomDoUstawienia+ " "+text3);
                Cursor cursor = sqLiteDatabase.rawQuery("Select * FROM Plan Where typPlanu = '"+text3+
                        "' AND czasPlanu = '"+text2+"' AND poziomUzytkownika = "+poziomDoUstawienia, null);
                String planingText="";
                if(cursor.moveToFirst()){
                    do{
                        planingText = cursor.getString(cursor.getColumnIndex("opisPlanu"));
                        Log.d("Log", "czas "+cursor.getInt(cursor.getColumnIndex("czasPlanu"))+" typ "+
                                cursor.getString(cursor.getColumnIndex("typPlanu"))+
                                " poziom "+ cursor.getInt(cursor.getColumnIndex("poziomUzytkownika"))+
                                "opis "+ cursor.getString(cursor.getColumnIndex("opisPlanu")));
                    }while(cursor.moveToNext());
                }else{
                    Log.d("log","Pusta tablica");
                }


                planning2Fragment = new Planning2Fragment();
                planning2Fragment.setArguments(planingText);
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, planning2Fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void showAdvice(){
        int showAdviceOrNot = (int) (Math.random()*10);
        if(showAdviceOrNot<=3){
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Advice> advices = realm.where(Advice.class).findAll();
        int sizeAdvices = advices.size();
        int randomNumber = (int) (Math.random()*sizeAdvices);
        String adviceText = advices.get(randomNumber).getText();
            Toast.makeText(getContext(), adviceText, Toast.LENGTH_LONG).show();
        }
    }




    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
