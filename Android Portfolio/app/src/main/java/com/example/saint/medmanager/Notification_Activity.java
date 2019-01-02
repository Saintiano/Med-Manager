package com.example.saint.medmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saint.medmanager.firebase_model.AddMedication;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

public class Notification_Activity extends AppCompatActivity {


    public static final String DRUG_NAME = "drugName";
    public static final String DRUG_ID ="drugId";

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    private static final int ACTIVITY_NUM = 2;

    private Context mContext = Notification_Activity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_);




        setUpBottomNavigationView();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("medications");
        databaseReference.keepSynced(true);


        recyclerView = (RecyclerView) findViewById(R.id.show_medication_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Notification_Activity.this));
        Toast.makeText(Notification_Activity.this, "Fetching Medications",Toast.LENGTH_SHORT).show();




    }



    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<AddMedication, AddMedication_ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<AddMedication, AddMedication_ViewHolder>
                (AddMedication.class, R.layout.cardview_show_medication, AddMedication_ViewHolder.class, databaseReference) {

            @Override
            protected void populateViewHolder(AddMedication_ViewHolder viewHolder, AddMedication model, int position) {

                viewHolder.set_DrugName(model.getDrug_name());
                viewHolder.set_DrugDesc(model.getDescription());
                viewHolder.set_DrugDay(model.getFrequency());
                viewHolder.set_DrugDay(model.getDay());
                viewHolder.set_DrugMonth(model.getMonth());

            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }


    //create a viewholder class where xml fields
    public static class AddMedication_ViewHolder extends RecyclerView.ViewHolder{

        //create view field
        View mView;

        public AddMedication_ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void set_DrugName(String drugName){

            TextView tv_DrugName = (TextView) mView.findViewById(R.id.tv_card_drugName);
            tv_DrugName.setText(drugName);

        }

        public void set_DrugDesc(String drugDesc){

            TextView tv_DrugDesc = (TextView) mView.findViewById(R.id.tv_card_drugDescription);
            tv_DrugDesc.setText(drugDesc);

        }

        public void set_Frequency(String drugFrequency){

            TextView tv_DrugFrequency = (TextView) mView.findViewById(R.id.tv_card_frequency);
            tv_DrugFrequency.setText(drugFrequency);

        }

        public void set_DrugDay(String drugDay){

            TextView tv_DrugDay = (TextView) mView.findViewById(R.id.tv_card_day);
            tv_DrugDay.setText(drugDay);

        }

        public void set_DrugMonth(String drugMonth){

            TextView tv_DrugMonth = (TextView) mView.findViewById(R.id.tv_card_month);
            tv_DrugMonth.setText(drugMonth);

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
