package com.example.vvaskovy.rowingmate.fragments;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vvaskovy.rowingmate.Interwal;
import com.example.vvaskovy.rowingmate.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TrainingFragment extends Fragment {


    EditText dataTreningu, dystansTreningu, czasTreningu, mocTreningu, tempoTreningu;
    Spinner spinnerSposobTreningu;
    Button wyszukajTrening;
    ArrayAdapter<CharSequence> arrayAdapter;
    String czasTreningupobrany, mocTreninguPobrana, tempoTreninguPobrane, dystansTreninguPobrany, dataTreninguPobrana, sposobTreninguPobrany;



    private OnFragmentInteractionListener mListener;

    public TrainingFragment() {
        // Required empty public constructor
    }

    public static TrainingFragment newInstance() {
        TrainingFragment fragment = new TrainingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_training, container, false);
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

        wyszukajTrening = (Button) getView().findViewById(R.id.wyszukajTrening);

        wyszukajTrening.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


                czasTreningupobrany = czasTreningu.getText().toString();
                mocTreninguPobrana = mocTreningu.getText().toString();
                tempoTreninguPobrane = tempoTreningu.getText().toString();
                dystansTreninguPobrany = dystansTreningu.getText().toString();
                dataTreninguPobrana = dataTreningu.getText().toString();
                sposobTreninguPobrany= spinnerSposobTreningu.getSelectedItem().toString();

                DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
                try {
                    Date date = formatter.parse(czasTreningupobrany);
                } catch (ParseException e) {
                    Toast.makeText(getActivity(),"Czas musi być podany w formacie hh:mm:ss", Toast.LENGTH_LONG).show();
                }

                DateFormat foramtter2 = new SimpleDateFormat("dd.mm.yyyy");

                try {
                    Date dataTreninguFormatowana = foramtter2.parse(dataTreninguPobrana);

                } catch (ParseException e) {
                    Toast.makeText(getActivity(),"Data treningu musi być w formacie dd.mm.yyyy", Toast.LENGTH_LONG).show();
                }

                Toast.makeText(getActivity(),czasTreningupobrany+" "+mocTreninguPobrana+" "+ tempoTreninguPobrane+
                        " "+dystansTreninguPobrany+" "+dataTreninguPobrana+" "+sposobTreninguPobrany, Toast.LENGTH_LONG).show();


            }
        });



    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
