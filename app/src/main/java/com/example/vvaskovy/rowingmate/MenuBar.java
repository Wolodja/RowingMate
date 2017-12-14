package com.example.vvaskovy.rowingmate;

import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vvaskovy.rowingmate.fragments.AddFragment;
import com.example.vvaskovy.rowingmate.fragments.DataFragment;
import com.example.vvaskovy.rowingmate.fragments.MotivationFragment;
import com.example.vvaskovy.rowingmate.fragments.PlanningFragment;
import com.example.vvaskovy.rowingmate.fragments.TrainingFragment;

import io.realm.Realm;
import io.realm.RealmResults;

public class MenuBar extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MotivationFragment motivationFragment;
    private PlanningFragment planningFragment;
    private AddFragment addFragment;
    private TrainingFragment trainingFragment;
    private DataFragment dataFragment;
    private TextView glowne_imie ,glowne_poziom;
    private DatabaseHelper db;
    private Button bt1, bt2, bt3,bt4;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        motivationFragment = new MotivationFragment();
        planningFragment = new PlanningFragment();
        addFragment = new AddFragment();
        trainingFragment = new TrainingFragment();
        dataFragment = new DataFragment();

        bt1 = (Button) findViewById(R.id.menu_bt1);
        bt2 = (Button) findViewById(R.id.menu_bt2);
        bt3 = (Button) findViewById(R.id.menu_bt3);
        bt4 = (Button) findViewById(R.id.menu_bt4);

        fragmentTransaction = getFragmentManager().beginTransaction();


        bt1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                RelativeLayout fr = (RelativeLayout) findViewById(R.id.container);
                fr.setBackgroundColor(Color.parseColor("#1faf95"));
                bt1.setVisibility(View.INVISIBLE);
                bt2.setVisibility(View.INVISIBLE);
                bt3.setVisibility(View.INVISIBLE);
                bt4.setVisibility(View.INVISIBLE);
                fragmentTransaction.replace(R.id.container,planningFragment);
                fragmentTransaction.commit();

            }
        });

        bt2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                RelativeLayout fr = (RelativeLayout) findViewById(R.id.container);
                fr.setBackgroundColor(Color.parseColor("#1faf95"));
                bt1.setVisibility(View.INVISIBLE);
                bt2.setVisibility(View.INVISIBLE);
                bt3.setVisibility(View.INVISIBLE);
                bt4.setVisibility(View.INVISIBLE);
                fragmentTransaction.replace(R.id.container,addFragment);
                fragmentTransaction.commit();
            }
        });

        bt3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                RelativeLayout fr = (RelativeLayout) findViewById(R.id.container);
                fr.setBackgroundColor(Color.parseColor("#1faf95"));
                bt1.setVisibility(View.INVISIBLE);
                bt2.setVisibility(View.INVISIBLE);
                bt3.setVisibility(View.INVISIBLE);
                bt4.setVisibility(View.INVISIBLE);
                fragmentTransaction.replace(R.id.container,trainingFragment);
                fragmentTransaction.commit();

            }
        });

        bt4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                RelativeLayout fr = (RelativeLayout) findViewById(R.id.container);
                fr.setBackgroundColor(Color.parseColor("#1faf95"));
                bt1.setVisibility(View.INVISIBLE);
                bt2.setVisibility(View.INVISIBLE);
                bt3.setVisibility(View.INVISIBLE);
                bt4.setVisibility(View.INVISIBLE);
                fragmentTransaction.replace(R.id.container,dataFragment);
                fragmentTransaction.commit();

            }
        });


    }
    public void showAdvice() {
       // int showAdviceOrNot = (int) Math.random() * 10;
       // if (showAdviceOrNot <= 2) {
            Realm realm = Realm.getDefaultInstance();
            final RealmResults<Advice> advices = realm.where(Advice.class).findAll();
            int sizeAdvices = advices.size();
            int randomNumber = (int) Math.random() * sizeAdvices;
            String adviceText = advices.get(randomNumber).getText();
            Toast.makeText(this, "dfdsfdf"+adviceText, Toast.LENGTH_LONG).show();
        //}
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        glowne_imie = (TextView) findViewById(R.id.glowne_imie);
        glowne_poziom = (TextView) findViewById(R.id.glowne_poziom);
        DatabaseHelper db = new DatabaseHelper(this);
        final SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        String imieDoUstwaienia="";
        int poziomDoUstawienia=-1;
        Cursor cursor = sqLiteDatabase.query("Uzytkownik",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                imieDoUstwaienia = cursor.getString(cursor.getColumnIndex("imie"));
                poziomDoUstawienia = cursor.getInt(cursor.getColumnIndex("poziom"));
               // poziomDoUstawienia = temp+"";
                //Log.d("Log", "Imie "+cursor.getString(cursor.getColumnIndex("imie"))+" poziom "+cursor.getInt(cursor.getColumnIndex("poziom")) );
            }while(cursor.moveToNext());
        }else{
            Log.d("log","Pusta tablica");
        }
        glowne_imie.setText(imieDoUstwaienia);
        if(poziomDoUstawienia==1){
            glowne_poziom.setText("Początkujący");
        }else{
            if(poziomDoUstawienia == 2){
                glowne_poziom.setText("Amator");
            }else{
                if(poziomDoUstawienia==3){
                    glowne_poziom.setText("Zaawansowany");
                }else{
                    if(poziomDoUstawienia==4){
                        glowne_poziom.setText("Mistrz");
                    }
                }
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        RelativeLayout fr = (RelativeLayout) findViewById(R.id.container);
        fr.setBackgroundColor(Color.parseColor("#1faf95"));
        bt1.setVisibility(View.INVISIBLE);
        bt2.setVisibility(View.INVISIBLE);
        bt3.setVisibility(View.INVISIBLE);
        bt4.setVisibility(View.INVISIBLE);
        if (id == R.id.motywacja) {
            fragmentTransaction.replace(R.id.container, motivationFragment);
        } else if (id == R.id.zaplanuj) {
            fragmentTransaction.replace(R.id.container,planningFragment);
        } else if (id == R.id.dodaj) {
            fragmentTransaction.replace(R.id.container,addFragment);
        } else if (id == R.id.zrealizowane) {
            fragmentTransaction.replace(R.id.container,trainingFragment);
        } else if (id == R.id.dane) {
            fragmentTransaction.replace(R.id.container,dataFragment);
        } else if (id == R.id.autor) {

        }
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
