package com.example.vvaskovy.rowingmate.fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vvaskovy.rowingmate.R;


public class MotivationFragment extends Fragment {

    private String [] texts;
    private TextView mt;
    private Button mb;
    private ImageView mi;
    private int tempa=-1, tempb=-1;



    private OnFragmentInteractionListener mListener;

    public MotivationFragment() {
        // Required empty public constructor
    }



    public static MotivationFragment newInstance() {
        MotivationFragment fragment = new MotivationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        texts = new String[10];
        texts[0]="Lepiej jest zapalić świecę, niż przeklinać ciemność";
        texts[1]="Czyń tylko to co podpowiada Ci serce";
        texts[2]="Rób to, co uważasz za stosowne. I tak zawsze znajdzie się ktoś, kto uważa inaczej…";
        texts[3]="Najtrudniejsze jest zdecydowanie się na działanie. Reszta to już tylko kwestia wytrwałości";
        texts[4]="Kiedyś – nie ma takiego dnia tygodnia";
        texts[5]="Wznoś się po każdym upadku, padniesz trupem albo urosną Ci skrzydła";
        texts[6]="Dawać bez wahania, tracić bez żalu i zdobywać bez chciwości…";
        texts[7]="Droga postępu nie jest ani szybka, ani łatwa";
        texts[8]="Zagłuszanie bólu na jakiś czas sprawia, że powraca ze zdwojoną siłą";
        texts[9]="Mieć trudne życie – to wielki przywilej";

    }
    public void setRandomTextAndImage(){
        int a = tempa;
        int b = tempb;
        while(a==tempa) //random text must be different from previous one
            a = (int)(Math.random()*10);
        while (b==tempb) //random image must be different from previous one
            b = (int)(Math.random()*12)+1;
        tempa=a;
        tempb=b;
        mt.setText(texts[a]);
        int resID = getResources().getIdentifier("mf"+b , "drawable",getActivity().getPackageName());
        mi.setImageResource(resID);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mt = (TextView) getView().findViewById(R.id.motywacjaText);
        mb = (Button) getView().findViewById(R.id.motywacjaBtn);
        mi = (ImageView) getActivity().findViewById(R.id.motywacjaImg);
        setRandomTextAndImage();
        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRandomTextAndImage();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_motivation, container, false);
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



    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
