package com.example.saint.medmanager;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.saint.medmanager.fragments.Doctor_Fragments;
import com.example.saint.medmanager.fragments.Home_Fragments;
import com.example.saint.medmanager.fragments.Review_Fragments;
import com.example.saint.medmanager.fragments.SectionPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class Home_Activity extends AppCompatActivity {


    private static final int ACTIVITY_NUM = 0;

    private Context mContext = Home_Activity.this;



    //firebase setup
    private FirebaseAuth mAuthentication;
    private FirebaseAuth.AuthStateListener mAuthenticationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);




        setUpFirebaseAuth();

        //setup for bottom navigation
        setUpBottomNavigationView();

        //setting the tab slider
        setUpViewpager();




    }


    //Setting up firebase methods and fields.......................................................

    private void checkCurrentUser(FirebaseUser user){

        if (user == null){

            Intent intent = new Intent(mContext, Login_Activity.class);
            startActivity(intent);

        }

    }

    private void setUpFirebaseAuth(){

        //app wide, can be accessed anywhere in fragments and activities
        mAuthentication = FirebaseAuth.getInstance();

        mAuthenticationListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                //calling method to check if user is logged in
                checkCurrentUser(user);

                if (user != null){

                    //user is signed in


                }else {

                    //user is signed out


                }


            }
        };

    }

    //create firebase on start and stop methods
    @Override
    public void onStart() {
        super.onStart();

        //add user (non Null)
        mAuthentication.addAuthStateListener(mAuthenticationListener);

        checkCurrentUser(mAuthentication.getCurrentUser());


    }

    @Override
    public void onStop() {
        super.onStop();

        //remove user
        if (mAuthenticationListener != null){

            mAuthentication.removeAuthStateListener(mAuthenticationListener);

        }

    }

    /**
     * setting up for viewpager for fragments setup
     */
    private void setUpViewpager(){

        //get the fragment manager
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());

        //adding fragments with the addfragment method we created in SectionPagerAdapter class
        adapter.addFragments(new Doctor_Fragments()); //index 0
        adapter.addFragments(new Home_Fragments());   //index 1
        adapter.addFragments(new Review_Fragments() );   //index 2

        //declaring the view pager object
        ViewPager viewpager = (ViewPager) findViewById(R.id.container);

        //setting the adapter
        viewpager.setAdapter(adapter);

        //setting up our tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewpager);

        //setting up icons for the tabs
        tabLayout.getTabAt(0).setIcon(R.drawable.doctor);
        tabLayout.getTabAt(1).setIcon(R.drawable.drugs_small);
        tabLayout.getTabAt(2).setIcon(R.drawable.ambulance);

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
