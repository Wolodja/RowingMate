package com.example.vvaskovy.rowingmate;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

public class GreedActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greed);

        TextView myText = (TextView) findViewById(R.id.touch );

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(800); //You can manage the time of the blink with this parameter
        anim.setStartOffset(200);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        myText.startAnimation(anim);

        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.greedL);
        cl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(GreedActivity.this, MenuBar.class));
            }
        });
    }



}
