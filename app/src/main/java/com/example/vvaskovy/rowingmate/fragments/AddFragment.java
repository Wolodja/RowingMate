package com.example.vvaskovy.rowingmate.fragments;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vvaskovy.rowingmate.DatabaseHelper;
import com.example.vvaskovy.rowingmate.Interwal;
import com.example.vvaskovy.rowingmate.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.DataFormatException;


public class AddFragment extends Fragment {


    EditText dataTreningu, czasTreningu, mocTreningu, tempoTreningu, dystansTreningu;
    Spinner spinnerSposobTreningu;
    ArrayAdapter<CharSequence> arrayAdapter;
    Button dodajInterwal, zapiszTrening, oczyscTrening, podzielsieTreningiem;
    //Date dataTreninguPobrana;
    String czasTreningupobrany, mocTreninguPobrana, tempoTreninguPobrane, dystansTreninguPobrany, dataTreninguPobrana, sposobTreninguPobrany;
    ListView listaInterwalow;
    ArrayList<String> arrayInterwalow;
    int licznik;
    ArrayList<Interwal> interwalySQL;
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper db;


    private OnFragmentInteractionListener mListener;

    public AddFragment() {
        // Required empty public constructor
    }


    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();
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
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataTreningu = (EditText) getView().findViewById(R.id.dataTreningu);
        czasTreningu = (EditText) getView().findViewById(R.id.czasTreningu);
        mocTreningu = (EditText) getView().findViewById(R.id.mocTreningu);
        tempoTreningu = (EditText) getView().findViewById(R.id.tempoTreningu);
        dystansTreningu = (EditText) getView().findViewById(R.id.dystansTreningu);

        spinnerSposobTreningu = (Spinner) getView().findViewById(R.id.spinnerSposob);
        arrayAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.two_types, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSposobTreningu.setAdapter(arrayAdapter);

        dodajInterwal = (Button) getView().findViewById(R.id.dodajInterwal);
        zapiszTrening = (Button) getView().findViewById(R.id.zapiszTrening);
        oczyscTrening = (Button) getView().findViewById(R.id.oczyśćTrening);
        podzielsieTreningiem = (Button) getView().findViewById(R.id.podzielsieTreningiem);
        podzielsieTreningiem.setVisibility(View.GONE);

        listaInterwalow = (ListView) getView().findViewById(R.id.listaInterwalow);
        arrayInterwalow = new ArrayList<String>();
        licznik = 1;
        interwalySQL = new ArrayList<Interwal>();
        final ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayInterwalow);

        db = new DatabaseHelper(getActivity());
        sqLiteDatabase = db.getWritableDatabase();


        dodajInterwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                czasTreningupobrany = czasTreningu.getText().toString();
                mocTreninguPobrana = mocTreningu.getText().toString();
                tempoTreninguPobrane = tempoTreningu.getText().toString();
                dystansTreninguPobrany = dystansTreningu.getText().toString();
                podzielsieTreningiem.setVisibility(View.GONE);


                DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
                try {
                    Date date = formatter.parse(czasTreningupobrany);
                    if(czasTreningupobrany.equals(" ") || mocTreninguPobrana.equals("") || tempoTreninguPobrane.equals("") || dystansTreninguPobrany.equals(""))
                        Toast.makeText(getActivity(),"Wszystkie pola muszą być wypełnione!", Toast.LENGTH_SHORT).show();
                    else{
                        Interwal i = new Interwal(licznik, czasTreningupobrany, mocTreninguPobrana,tempoTreninguPobrane,dystansTreninguPobrany);
                        interwalySQL.add(i);
                        arrayInterwalow.add(licznik+".  "+czasTreningupobrany+ "   "+mocTreninguPobrana+"           "+ tempoTreninguPobrane+"              "+ dystansTreninguPobrany );
                        listaInterwalow.setAdapter(adapter);
                        licznik++;
                        //Toast.makeText(getActivity(),"Jestem " +czasTreningupobrany+ " "+ mocTreninguPobrana+ " "+ tempoTreninguPobrane+ " "+ dystansTreninguPobrany, Toast.LENGTH_SHORT).show();
                    }

                } catch (ParseException e) {
                    Toast.makeText(getActivity(),"Czas musi być podany w formacie hh:mm:ss", Toast.LENGTH_LONG).show();
                }



            }
        });

        zapiszTrening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataTreninguPobrana = dataTreningu.getText().toString();
                sposobTreninguPobrany= spinnerSposobTreningu.getSelectedItem().toString();
                DateFormat foramtter2 = new SimpleDateFormat("dd.mm.yyyy");

                try {
                    Date dataTreninguFormatowana = foramtter2.parse(dataTreninguPobrana);
                    if(arrayInterwalow.size()==0){
                        Toast.makeText(getActivity(),"Nie dodałeś żadnego interwalu", Toast.LENGTH_LONG).show();
                    }
                    else {

                        podzielsieTreningiem.setVisibility(View.VISIBLE);

                        ContentValues contentValues = new ContentValues();
                        contentValues.put("sposobTreningu", sposobTreninguPobrany);
                        contentValues.put("dataTreningu", dataTreninguPobrana);
                        sqLiteDatabase.insert("Trening", null, contentValues);
                        Log.d("Log","Data inserted");
                        Cursor cursor = sqLiteDatabase.rawQuery("Select * FROM Trening Where sposobTreningu = '"+sposobTreninguPobrany+
                                "' AND dataTreningu = '"+dataTreninguPobrana+"'", null);
                        int idTreningu=-1;
                        if(cursor.moveToFirst()){
                            do{
                                idTreningu = cursor.getInt(cursor.getColumnIndex("idTreningu"));
                                Log.d("Log", "id "+cursor.getInt(cursor.getColumnIndex("idTreningu"))+" sposob "+
                                        cursor.getString(cursor.getColumnIndex("sposobTreningu"))+
                                        " data "+ cursor.getString(cursor.getColumnIndex("dataTreningu")));
                            }while(cursor.moveToNext());
                        }else{
                            Log.d("log","Pusta tablica");
                        }

                        int nrInterwaluSQL=1;
                        for( Interwal interwal: interwalySQL){

                            contentValues.clear();
                            contentValues = new ContentValues();
                            contentValues.put("idTreningu", idTreningu);
                            contentValues.put("nrInterwalu", nrInterwaluSQL);
                            contentValues.put("czasInterwalu", interwal.getCzasInterwalu());
                            contentValues.put("mocInterwalu", interwal.getMocInterwalu());
                            contentValues.put("dystansInterwalu", interwal.getDystansInterwalu());
                            contentValues.put("tempoInterwalu", interwal.getTempoInterwalu());
                            sqLiteDatabase.insert("Interwal", null, contentValues);
                            Log.d("Log","Data inserted");
                            nrInterwaluSQL++;
                        }
                        Cursor cursor2 = sqLiteDatabase.rawQuery("Select * FROM Interwal Where idTreningu = '"+idTreningu+
                                "' AND nrInterwalu = '1'", null);
                        if(cursor2.moveToFirst()){
                            do{
                                Log.d("Log", "id "+cursor2.getInt(cursor2.getColumnIndex("idTreningu"))+" czasInterwalu "+
                                        cursor2.getString(cursor2.getColumnIndex("czasInterwalu"))+
                                        " dystansInterwalu "+ cursor2.getString(cursor2.getColumnIndex("dystansInterwalu")));
                            }while(cursor.moveToNext());
                        }else{
                            Log.d("log","Pusta tablica");
                        }



                        Toast.makeText(getActivity(), "Dane zostałe zapisane", Toast.LENGTH_LONG).show();
                    }
                } catch (ParseException e) {
                    Toast.makeText(getActivity(),"Data treningu musi być w formacie dd.mm.yyyy", Toast.LENGTH_LONG).show();
                }

            }
        });

        oczyscTrening.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                dataTreningu.setText("");
                czasTreningu.setText("");
                mocTreningu.setText("");
                tempoTreningu.setText("");
                dystansTreningu.setText("");
                interwalySQL.clear();
                arrayInterwalow.clear();
                listaInterwalow.setAdapter(adapter);
                licznik=1;
                podzielsieTreningiem.setVisibility(View.GONE);
            }
        });

        podzielsieTreningiem.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody="Oto wynik mojego treningu: ";
                int nrInterwalu = 1;
                for (Interwal i :interwalySQL){
                    shareBody+= "Interwal nr "+nrInterwalu+" ";
                    nrInterwalu++;
                    shareBody+= i.getCzasInterwalu()+" min, "+i.getDystansInterwalu()+
                            " m, "+i.getMocInterwalu()+" watt, "+i.getTempoInterwalu()+" pociągnięć na minutę";

                }
                String shareSub = "Oto mój dzisiejszy trening!";
                intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "Podziel się przez:"));
            }
        });



    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
