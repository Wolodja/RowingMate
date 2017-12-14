package com.example.vvaskovy.rowingmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class GreedActivity extends AppCompatActivity {


    @BindView(R.id.touch) TextView myText;
    @BindView(R.id.greedL) ConstraintLayout cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greed);
        ButterKnife.bind(this);

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(800); //You can manage the time of the blink with this parameter
        anim.setStartOffset(200);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        myText.startAnimation(anim);

        final RealmConfiguration config = new RealmConfiguration.Builder(this).build();

        Realm.setDefaultConfiguration(config);

        Realm realm = Realm.getDefaultInstance();
        String [] advices = {"Ważne jest sprawdzenie stanu zdrowia przed rozpoczęciem treningu. Jeśli nie masz dobrego samopoczucia, nigdy nie zaczynaj ćwiczeń.",
                "Zawsze się rozgrzej i ‘rozciągnij’ dokładnie przed i po każdej sesji treningowej",
                "Opanuj najpierw dobrą technikę przed zwiększeniem intensywności treningu.",
                "Kiedy rozpoczynasz program ćwiczeń, zachowaj umiar; zaczynaj powoli i rozwijaj go systematycznie.",
                "Pij dużo płynów podczas i po ćwiczeniu. Nie czekaj aż poczujesz pragnienie.",
                "Upewnij się, że trenujesz z odpowiednią intensywnością.",
                "Prowadź dziennik treningów pomocny w wyznaczaniu realistycznych celów i zadań i planowaniu przyszłych programów pracy",
                "Sprawdź czy rękojeść, siedzisko i szyna są czyste.",
                "Dopasuj ustawienie oporu do odpowiedniej intensywności treningu",
                "Umieść rękojeść na uchwycie zanim zamocujesz stopy.",
                "Dopasuj podnóżki do wielkości stóp i zaciągnij paski.",
                "Siadaj nieznacznie w kierunku tylnej części siedzenia.",
                "Odciągaj rękojeść prosto przy wykorzystaniu obydwu rąk. Nie wiosłuj jedną ręką.",
                "Nie przekręcaj łańcucha.",
                "Trzymaj ubranie, włosy i palce z dala od rolek siedzenia.",
                "Kiedy skończysz ćwiczyć, umieść rękojeść na uchwycie , następnie po zwolnieniu stóp, umieść ją z powrotem na prowadnicy łańcucha w celu zwolnienia napięcia mechanizmu powrotnego",
                "Zawsze upewniaj się czy urządzenie jest utrzymywane we właściwym stanie"
        };
        /*
        final RealmResults<Advice> results = realm.where(Advice.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.clear();
            }
        });

        for(int i=0; i<advices.length; i++){

            realm.beginTransaction();
            Advice advice = realm.createObject(Advice.class); // Create managed objects directly
            advice.setId(i+100);
            advice.setText(advices[i]);
            realm.commitTransaction();
        }
*/
        final RealmResults<Advice> puppies = realm.where(Advice.class).findAll();
        for(Advice a: puppies){
        Log.v("log", a.getText()+"   ");
        }
    }

    @OnClick(R.id.greedL)
    public void proceed(){
        startActivity(new Intent(GreedActivity.this, MenuBar.class));
    }





}
