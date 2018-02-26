package com.apkglobal.swimmingmaster;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubePlayerView;

import static com.apkglobal.swimmingmaster.R.id.default_activity_button;
import static com.apkglobal.swimmingmaster.R.id.frame;
import static com.apkglobal.swimmingmaster.R.id.home;
import static com.apkglobal.swimmingmaster.R.id.textView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final int PERMISSIONS_REQUEST_PHONE_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Gaurav Adhikari", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

       NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        onItemselected(R.id.nav_home);
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        onItemselected(item.getItemId());
        /*        int id = item.getItemId();



        if (id == R.id.nav_home) {


        } else {
            if (id == R.id.nav_prep) {

            } else if (id == R.id.nav_basic) {

            } else if (id == R.id.nav_ext) {

            } else if (id == R.id.nav_share) {

            } else if (id == R.id.nav_email) {

            } else if (id == R.id.nav_call) {

            }
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;
    }

    private void onItemselected(int itemId) {


        Fragment fragment = null;
        switch(itemId)
        {
            case R.id.nav_home:
                fragment = new HomeFragment();
                FragmentManager fm= getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,fragment).commit();
                break;


            case R.id.nav_prep:
                fragment = new PreparingFragment();
                FragmentManager fm1= getSupportFragmentManager();
                fm1.beginTransaction().replace(R.id.frame,fragment).commit();
                break;

            case R.id.nav_basic:
                fragment = new BasicFragment();
                FragmentManager fm2= getSupportFragmentManager();
                fm2.beginTransaction().replace(R.id.frame,fragment).commit();
                break;

            case R.id.nav_adv:
                fragment = new AdvancedFragment();
                FragmentManager fm3= getSupportFragmentManager();
                fm3.beginTransaction().replace(R.id.frame,fragment).commit();
                break;

            case R.id.nav_share:{

                Intent share = new Intent(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_TEXT,"please Download and promote my app \n" +

                        "https://play.google.com/store/apps/details?id=com.apkglobal.swimmingmaster&hl=en");

                share.setType("text/plain");
                startActivity(Intent.createChooser(share,"Share App via"));
                break;


            }

            case R.id.nav_email:{

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,new String[] {"gaurav.adhikari36@gmail.com"} );
                email.putExtra(Intent.EXTRA_SUBJECT,"download the app");
                email.setType("email/rfc822");


                startActivity(Intent.createChooser(email,"Email to send"));
                break;


            }

            case R.id.nav_call:
            {

                //use runtime permission for calling
                callme();

                /*Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("8360175038"));
                startActivity(call);
                break;*/

            }

        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    private void callme() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_PHONE_CALL);
        } else {
            //Open call function
            String number = "+919803617736";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);
        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_PHONE_CALL) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                callme();
            } else {
                Toast.makeText(this, "Sorry!!! Permission Denied", Toast.LENGTH_SHORT).show();
            }

        }

     }




}




