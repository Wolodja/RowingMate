package com.example.vvaskovy.rowingmate.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.widget.CheckBox;
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
    Training2Fragment training2Fragment;
    CheckBox sposobTreninguCheckBox, dataTreninguCheckBox, dystansTreninguCheckBox, czasTreninguCheckBox, mocTreninguCheckBox,tempoTreninguCheckBox;

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

        dataTreninguCheckBox = (CheckBox) getView().findViewById(R.id.checkBoxData);
        sposobTreninguCheckBox = (CheckBox) getView().findViewById(R.id.checkBoxSposob);
        czasTreninguCheckBox = (CheckBox) getView().findViewById(R.id.checkBoxCzas);
        mocTreninguCheckBox = (CheckBox) getView().findViewById(R.id.checkBoxMoc);
        tempoTreninguCheckBox = (CheckBox) getView().findViewById(R.id.checkBoxTempo);
        dystansTreninguCheckBox = (CheckBox) getView().findViewById(R.id.checkBoxDystans);

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



                boolean prawidłoweDaty=true;
                DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
                try {
                    if(czasTreningupobrany!= null && !czasTreningupobrany.isEmpty() && czasTreningupobrany!="") {
                        Date date = formatter.parse(czasTreningupobrany);
                    }
                } catch (ParseException e) {
                    prawidłoweDaty=false;
                    Toast.makeText(getActivity(),"Czas musi być podany w formacie hh:mm:ss", Toast.LENGTH_LONG).show();
                }

                DateFormat foramtter2 = new SimpleDateFormat("dd.mm.yyyy");

                try {
                    if(dataTreninguPobrana!= null && !dataTreninguPobrana.isEmpty() && dataTreninguPobrana!="") {
                        Date dataTreninguFormatowana = foramtter2.parse(dataTreninguPobrana);
                    }
                } catch (ParseException e) {
                    prawidłoweDaty=false;
                    Toast.makeText(getActivity(),"Data treningu musi być w formacie dd.mm.yyyy", Toast.LENGTH_LONG).show();
                }

                if(prawidłoweDaty) {
                   // Toast.makeText(getActivity(), czasTreningupobrany + " " + mocTreninguPobrana + " " + tempoTreninguPobrane +
                     //       " " + dystansTreninguPobrany + " " + dataTreninguPobrana + " " + sposobTreninguPobrany, Toast.LENGTH_LONG).show();

                    int licznik = 0;
                    String textSQL = "Select * FROM Trening T Join Interwal I on T.idTreningu = I.idTreningu ";
                    if(!dataTreninguPobrana.isEmpty()) {
                        if (licznik == 0)
                            textSQL += "WHERE ";
                        textSQL += "T.dataTreningu = '" + dataTreninguPobrana + "' ";
                        licznik++;
                    }
                    if(!sposobTreninguPobrany.isEmpty()) {
                        if(licznik == 0){
                            textSQL+="WHERE ";
                        }
                        else{
                            textSQL+="AND ";
                        }
                        textSQL+="T.sposobTreningu = '"+sposobTreninguPobrany+"' ";
                        licznik++;
                    }
                    if(!mocTreninguPobrana.isEmpty()) {
                        if(licznik == 0){
                            textSQL+="WHERE ";
                        }
                        else{
                            textSQL+="AND ";
                        }
                        textSQL+="I.mocInterwalu = '"+mocTreninguPobrana+"' ";
                        licznik++;
                    }
                    if(!czasTreningupobrany.isEmpty()) {
                        if(licznik == 0){
                            textSQL+="WHERE ";
                        }
                        else{
                            textSQL+="AND ";
                        }
                        textSQL+="I.czasInterwalu = '"+czasTreningupobrany+"' ";
                        licznik++;
                    }
                    if(!tempoTreninguPobrane.isEmpty()) {
                        if(licznik == 0){
                            textSQL+="WHERE ";
                        }
                        else{
                            textSQL+="AND ";
                        }
                        textSQL+="I.tempoInterwalu = '"+tempoTreninguPobrane+"' ";
                        licznik++;
                    }
                    if(!dystansTreninguPobrany.isEmpty()) {
                        if(licznik == 0){
                            textSQL+="WHERE ";
                        }
                        else{
                            textSQL+="AND ";
                        }
                        textSQL+="I.dystansInterwalu = '"+dystansTreninguPobrany+"' ";
                        licznik++;
                    }

                    int licznik2 = 0;
                    if(sposobTreninguCheckBox.isChecked()){
                        if(licznik2==0){
                            textSQL+=" ORDER BY ";
                        }else{
                            textSQL+=",";
                        }
                        textSQL+=" sposobTreningu";
                        licznik2++;
                    }
                    if(dataTreninguCheckBox.isChecked()){
                        if(licznik2==0){
                            textSQL+=" ORDER BY ";
                        }else{
                            textSQL+=",";
                        }
                        textSQL+=" dataTreningu desc";
                        licznik2++;
                    }
                    if(dystansTreninguCheckBox.isChecked()){
                        if(licznik2==0){
                            textSQL+=" ORDER BY ";
                        }else{
                            textSQL+=",";
                        }
                        textSQL+=" dystansInterwalu desc";
                        licznik2++;
                    }
                    if(czasTreninguCheckBox.isChecked()){
                        if(licznik2==0){
                            textSQL+=" ORDER BY ";
                        }else{
                            textSQL+=",";
                        }
                        textSQL+=" czasInterwalu asc";
                        licznik2++;
                    }
                    if(mocTreninguCheckBox.isChecked()){
                        if(licznik2==0){
                            textSQL+=" ORDER BY ";
                        }else{
                            textSQL+=",";
                        }
                        textSQL+=" mocInterwalu desc";
                        licznik2++;
                    }
                    if(tempoTreninguCheckBox.isChecked()){
                        if(licznik2==0){
                            textSQL+=" ORDER BY ";
                        }else{
                            textSQL+=",";
                        }
                        textSQL+=" tempoInterwalu asc";
                        licznik2++;
                    }

                    textSQL+=";";
                    Toast.makeText(getActivity(), textSQL, Toast.LENGTH_LONG).show();
                    training2Fragment = new Training2Fragment();
                    training2Fragment.setTextSQL(textSQL);
                    FragmentManager fragmentManager = getActivity().getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, training2Fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }

            }
        });



    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
