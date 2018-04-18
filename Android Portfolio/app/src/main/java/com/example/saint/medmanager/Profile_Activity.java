package com.example.saint.medmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class Profile_Activity extends AppCompatActivity {


    private static final int ACTIVITY_NUM = 4;

    private ImageView editProfile;
    private ImageView home;

    private Context mContext = Profile_Activity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);


        home = (ImageView) findViewById(R.id.profile_home);
        editProfile = (ImageView) findViewById(R.id.profile_edit);




        setUpBottomNavigationView();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Profile_Activity.this, Home_Activity.class);
                startActivity(intent);

            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Profile_Activity.this, Edit_Profile_Activity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * bottom navigation view setup
     */

    private void setUpBottomNavigationView(){

        BottomNavigationViewEx buttomNavigationViewEx = ( BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        com.example.saint.medmanager.bottom_view_helper.BottomNavigationView.setUpBottomNavigationView(buttomNavigationViewEx);

        //Enable bottom activity in each activities / items
        com.example.saint.medmanager.bottom_view_helper.BottomNavigationView.enableNavigation(mContext, buttomNavigationViewEx);

        Menu menu = buttomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }

}
