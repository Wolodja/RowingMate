package com.example.vvaskovy.rowingmate;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.vvaskovy.rowingmate.fragments.AddFragment;
import com.example.vvaskovy.rowingmate.fragments.DataFragment;
import com.example.vvaskovy.rowingmate.fragments.MotivationFragment;
import com.example.vvaskovy.rowingmate.fragments.PlanningFragment;
import com.example.vvaskovy.rowingmate.fragments.TrainingFragment;

public class MenuBar extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MotivationFragment motivationFragment;
    PlanningFragment planningFragment;
    AddFragment addFragment;
    TrainingFragment trainingFragment;
    DataFragment dataFragment;
    TextView glowne_imie ,glowne_poziom;
    DatabaseHelper db;

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

/*        glowne_imie = (TextView) findViewById(R.id.glowne_imie);
        glowne_poziom = (TextView) findViewById(R.id.glowne_poziom);*/
/*        DatabaseHelper db = new DatabaseHelper(this);
        final SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        String imieDoUstwaienia="";
        String poziomDoUstawienia="";
        Cursor cursor = sqLiteDatabase.query("Uzytkownik",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                imieDoUstwaienia = cursor.getString(cursor.getColumnIndex("imie"));
                int temp = cursor.getInt(cursor.getColumnIndex("poziom"));
                poziomDoUstawienia = temp+"";
                //Log.d("Log", "Imie "+cursor.getString(cursor.getColumnIndex("imie"))+" poziom "+cursor.getInt(cursor.getColumnIndex("poziom")) );
            }while(cursor.moveToNext());
        }else{
            Log.d("log","Pusta tablica");
        }
        glowne_imie.setText(imieDoUstwaienia);
       // glowne_poziom.setText(poziomDoUstawienia);*/
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

        FrameLayout fr = (FrameLayout) findViewById(R.id.container);
        fr.setBackgroundColor(0x00000000);
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
