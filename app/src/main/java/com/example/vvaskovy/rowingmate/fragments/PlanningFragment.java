package com.example.vvaskovy.rowingmate.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vvaskovy.rowingmate.R;


public class PlanningFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    ArrayAdapter<CharSequence> adapter1, adapter2, adapter3;
    Button planuj;
    Spinner spinner1, spinner2, spinner3;

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
        return inflater.inflate(R.layout.fragment_planning, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        planuj.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String text1 = spinner1.getSelectedItem().toString();
                String text2 = spinner2.getSelectedItem().toString();
                String text3 = spinner3.getSelectedItem().toString();
                Toast.makeText(getActivity(), text1+ " "+text2+" "+text3,Toast.LENGTH_LONG).show();
               // Log.d("Log", "Imie "+text1);
            }
        });
        //String text = mySpinner.getSelectedItem().toString();
    }
    /*    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
