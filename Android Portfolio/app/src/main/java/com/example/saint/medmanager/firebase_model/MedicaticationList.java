package com.example.saint.medmanager.firebase_model;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.saint.medmanager.R;

import java.util.List;

public class MedicaticationList extends ArrayAdapter<AddMedication> {


    private Activity context;
    private List<AddMedication> medicationList;

    public MedicaticationList(Activity context, List<AddMedication> medicationList){

        super(context, R.layout.medication_list, medicationList);
        this.context = context;
        this.medicationList = medicationList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View medicationListView = inflater.inflate(R.layout.medication_list, null, true);

        TextView drugName = (TextView) medicationListView.findViewById(R.id.tv_drugName);
        TextView drugDescription = (TextView) medicationListView.findViewById(R.id.tv_drugDescription);
        TextView drugFrequency = (TextView) medicationListView.findViewById(R.id.tv_frequency);
        TextView day = (TextView) medicationListView.findViewById(R.id.tv_day);
        TextView month = (TextView) medicationListView.findViewById(R.id.tv_month);

        AddMedication medication = medicationList.get(position);

        drugName.setText(medication.getDrug_name());
        drugDescription.setText(medication.getDescription());
        drugFrequency.setText(medication.getFrequency());
        day.setText(medication.getDay());
        month.setText(medication.getMonth());

        return medicationListView;

    }
}
