package com.example.saint.medmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_Activity extends AppCompatActivity {


    private static final int ACTIVITY_NUM = 4;

    private ImageView editProfile;
    private ImageView home;

    private Context mContext = Profile_Activity.this;

    private TextView tv_username;
    private TextView tv_name;
    private CircleImageView tv_image;
    private TextView tv_address;
    private TextView tv_blood_group;
    private TextView tv_genotype;
    private TextView tv_work;
    private TextView tv_description;
    private TextView tv_phone_number;

    private TextView tv_drugs_taken;
    private TextView tv_drugs_on;


    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        //getting the unique id of the current user
        String current_uid = mCurrentUser.getUid();

        home = (ImageView) findViewById(R.id.profile_home);
        editProfile = (ImageView) findViewById(R.id.profile_edit);

        tv_username = findViewById(R.id.profile_tv_username);
        tv_name = findViewById(R.id.profile_tv_name);
        tv_image = findViewById(R.id.profile_image);
        tv_address = findViewById(R.id.profile_tv_address);
        tv_blood_group = findViewById(R.id.profile_blood_group);
        tv_genotype = findViewById(R.id.profile_genotype);
        tv_work = findViewById(R.id.profile_work);
        tv_description = findViewById(R.id.profile_description);
        tv_phone_number = findViewById(R.id.profile_phone_number);







        setUpBottomNavigationView();

        //retrieving data fro the users node then the unique_id node into the mUserDatabase
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);

        //adding the addvalue event listener to the mUserDatabase
        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //on success, get values of the user unique id and store inside the relevant fields
                String mUsername = dataSnapshot.child("username").getValue().toString();
                String mFullname = dataSnapshot.child("fullname").getValue().toString();
                String mWork = dataSnapshot.child("work").getValue().toString();
                String mDescription = dataSnapshot.child("description").getValue().toString();
                String mEmail = dataSnapshot.child("email").getValue().toString();
                String mPhonenumber = dataSnapshot.child("phonenumber").getValue().toString();
                String mImage = dataSnapshot.child("imageurl").getValue().toString();
                String mBlood_group = dataSnapshot.child("blood_group").getValue().toString();
                String mGenotype = dataSnapshot.child("genetype").getValue().toString();



                //set values as strings to textview fields
                tv_username.setText(mUsername);
                tv_name.setText(mFullname);
                tv_work.setText(mWork);
                tv_description.setText(mDescription);
                tv_phone_number.setText(mPhonenumber);
                tv_blood_group.setText(mBlood_group);
                tv_genotype.setText(mGenotype);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                //on error

            }
        });




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
