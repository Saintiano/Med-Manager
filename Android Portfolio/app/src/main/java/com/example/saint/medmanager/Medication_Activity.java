package com.example.saint.medmanager;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saint.medmanager.firebase_model.AddMedication;
import com.example.saint.medmanager.firebase_model.MedicaticationList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

public class Medication_Activity extends AppCompatActivity {

    EditText editTextDrug;
    EditText editTextDescription;
    EditText editTextFrequency;

    Button setReminderBtn;

    Spinner spinnerDay;
    Spinner spinnerMonth;

    TextView pleaseWait;
    ProgressBar progressBar;

    DatabaseReference databaseMedication;

    ListView listViewMedication;

    List<AddMedication> addMedicationList;

    private static final int ACTIVITY_NUM = 1;

    private Context mContext = Medication_Activity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_);


        databaseMedication = FirebaseDatabase.getInstance().getReference("medications");

        pleaseWait = (TextView) findViewById(R.id.please_wait_drugs);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_drugs);

        editTextDrug = (EditText) findViewById(R.id.drug_name);
        editTextDescription =(EditText) findViewById(R.id.drug_description);
        editTextFrequency =(EditText) findViewById(R.id.drug_interval);

        setReminderBtn = (Button) findViewById(R.id.set_reminder);

        spinnerDay = (Spinner) findViewById(R.id.spinner_day);
        spinnerMonth = (Spinner) findViewById(R.id.spinner_month);


        listViewMedication = (ListView) findViewById(R.id.medication_listView);

        addMedicationList = new ArrayList<>();


        //hiding progress bar and please wait text field at start
        progressBar.setVisibility(View.GONE);
        pleaseWait.setVisibility(View.GONE);




        setReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //hiding progress bar and please wait text field at start
                progressBar.setVisibility(View.VISIBLE);
                pleaseWait.setVisibility(View.VISIBLE);


                addReminder();

            }
        });




        setUpBottomNavigationView();



    }


    private void addReminder(){

        String drugName = editTextDrug.getText().toString().trim();
        String drugDescription = editTextDescription.getText().toString().trim();
        String drugFrequency = editTextFrequency.getText().toString().trim();

        String day = spinnerDay.getSelectedItem().toString();
        String month = spinnerMonth.getSelectedItem().toString();

        if (!TextUtils.isEmpty(drugName) && !TextUtils.isEmpty(drugDescription) && !TextUtils.isEmpty(drugFrequency)){

            //hiding progress bar and please wait text field at start
            progressBar.setVisibility(View.GONE);
            pleaseWait.setVisibility(View.GONE);


            String id = databaseMedication.push().getKey();

            AddMedication medication = new AddMedication(id, drugName, drugDescription, drugFrequency, day, month);

            databaseMedication.child(id).setValue(medication);

            Toast.makeText(Medication_Activity.this, "Medication Added", Toast.LENGTH_LONG).show();;

        }else {

            Toast.makeText(Medication_Activity.this, "Please Fill The Fields", Toast.LENGTH_LONG).show();

        }



    }


    @Override
    protected void onStart() {
        super.onStart();

        databaseMedication.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                addMedicationList.clear();

                for (DataSnapshot medicationDataSnapshot : dataSnapshot.getChildren()){

                    AddMedication addMedication = medicationDataSnapshot.getValue(AddMedication.class);

                    addMedicationList.add(addMedication);

                }

                MedicaticationList medicaticationListAdapter = new MedicaticationList(Medication_Activity.this, addMedicationList);

                listViewMedication.setAdapter(medicaticationListAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



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
