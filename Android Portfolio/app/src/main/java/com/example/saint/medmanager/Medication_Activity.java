package com.example.saint.medmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saint.medmanager.create_doctor_hospital_profiles.Create_Doctor_Activity;
import com.example.saint.medmanager.create_doctor_hospital_profiles.Create_Hospital_Activity;
import com.example.saint.medmanager.details_activities.Doctors_Profile_Details;
import com.example.saint.medmanager.details_activities.Fake_Drugs_Activity;
import com.example.saint.medmanager.details_activities.Scanner_Activity;
import com.example.saint.medmanager.firebase_model.AddMedication;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class Medication_Activity extends AppCompatActivity {

    EditText editTextDrug;
    EditText editTextDescription;
    EditText editTextFrequency;

    Button setReminderBtn;
    Button doctors_Profile;

    Spinner spinnerDay;
    Spinner spinnerMonth;

    TextView pleaseWait;
    ProgressBar progressBar;

    DatabaseReference databaseMedication;

    private CardView create_doctor_profile;
    private CardView create_hospital_profile;

    private ImageView scanImage;
    private ImageView fakeDrugs;



    private static final int ACTIVITY_NUM = 1;

    private Context mContext = Medication_Activity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_);

        create_doctor_profile = (CardView) findViewById(R.id.create_doctor_card);
        create_hospital_profile = (CardView) findViewById(R.id.create_hospital_card);

        scanImage = (ImageView) findViewById(R.id.scan_image_card);
        fakeDrugs = (ImageView) findViewById(R.id.fake_drugs_report_image_card);


        databaseMedication = FirebaseDatabase.getInstance().getReference("medications");

        pleaseWait = (TextView) findViewById(R.id.please_wait_drugs);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_drugs);

        editTextDrug = (EditText) findViewById(R.id.drug_name);
        editTextDescription =(EditText) findViewById(R.id.drug_description);
        editTextFrequency =(EditText) findViewById(R.id.drug_interval);

        setReminderBtn = (Button) findViewById(R.id.set_reminder);

        spinnerDay = (Spinner) findViewById(R.id.spinner_day);
        spinnerMonth = (Spinner) findViewById(R.id.spinner_month);




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



        fakeDrugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Medication_Activity.this, Fake_Drugs_Activity.class);
                startActivity(intent);

            }
        });

        scanImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Medication_Activity.this, Scanner_Activity.class);
                startActivity(intent);

            }
        });

        create_doctor_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Medication_Activity.this, Create_Doctor_Activity.class);
                startActivity(intent);

            }
        });


        create_hospital_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Medication_Activity.this, Create_Hospital_Activity.class);
                startActivity(intent);

            }
        });



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

            editTextDrug.setText("");
            editTextDescription.setText("");
            editTextFrequency.setText("");

            Toast.makeText(Medication_Activity.this, "Medication Added", Toast.LENGTH_LONG).show();;

        }else {

            Toast.makeText(Medication_Activity.this, "Please Fill The Fields", Toast.LENGTH_LONG).show();

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
