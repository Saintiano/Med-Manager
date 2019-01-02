package com.example.saint.medmanager.details_activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.saint.medmanager.Medication_Activity;
import com.example.saint.medmanager.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class Doctors_Profile_Details extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 1;

    private Context mContext = Doctors_Profile_Details.this;

    private CardView cardView_PatientReview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors__profile__details);

        setUpBottomNavigationView();






        cardView_PatientReview = (CardView) findViewById(R.id.doctors_patients_reviews);



        cardView_PatientReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Doctors_Profile_Details.this, Doctors_Patients_Review_Activity.class));

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
