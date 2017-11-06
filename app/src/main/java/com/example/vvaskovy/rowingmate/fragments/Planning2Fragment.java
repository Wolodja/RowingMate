package com.example.vvaskovy.rowingmate.fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vvaskovy.rowingmate.R;




public class Planning2Fragment extends Fragment {


    TextView planTreningu;
    private String mParam1;

    private OnFragmentInteractionListener mListener;

    public Planning2Fragment() {
        // Required empty public constructor
    }


    public static Planning2Fragment newInstance() {
        Planning2Fragment fragment = new Planning2Fragment();

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
        return inflater.inflate(R.layout.fragment_planning2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        planTreningu = (TextView) getActivity().findViewById(R.id.planTreningu);
        planTreningu.setText(mParam1);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void setArguments(String text){

        mParam1=text;

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
