package com.example.saint.medmanager.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saint.medmanager.R;
import com.example.saint.medmanager.firebase_model.AddMedication;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Drug_Fragments extends Fragment {


    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       //Declaring the views
        View view = inflater.inflate(R.layout.fragment_drug, container, false);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("medications");
        databaseReference.keepSynced(true);

        recyclerView = view.findViewById(R.id.show_user_review_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Toast.makeText(getActivity(), "Fetching Medications",Toast.LENGTH_SHORT).show();






        return view;



    }


    @Override
    public void onStart() {
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








}
