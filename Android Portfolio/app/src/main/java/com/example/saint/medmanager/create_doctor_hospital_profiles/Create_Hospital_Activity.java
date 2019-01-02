package com.example.saint.medmanager.create_doctor_hospital_profiles;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.saint.medmanager.R;
import com.example.saint.medmanager.details_activities.Hospital_Ambulance_Profile_Activity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.picasso.Picasso;

public class Create_Hospital_Activity extends AppCompatActivity {



    private static final int ACTIVITY_NUM = 1;

    private Context mContext = Create_Hospital_Activity.this;

    private static final int PICK_IMAGE_REQUEST_CODE = 2;

    private Uri imageUri;



    private ImageView setHospitalPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__hospital_);

        setUpBottomNavigationView();


        setHospitalPhoto = (ImageView) findViewById(R.id.set_hospital_profile_photo);


        setHospitalPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFilePicker();

            }
        });

    }


    private void openFilePicker(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){

            imageUri = data.getData();

            Picasso.with(this).load(imageUri).into(setHospitalPhoto);

        }

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
