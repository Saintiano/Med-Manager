package com.example.saint.medmanager.create_doctor_hospital_profiles;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.saint.medmanager.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.picasso.Picasso;

public class Create_Doctor_Activity extends AppCompatActivity {



    private static final int PICK_IMAGE_REQUEST_CODE = 2;

    private static final int ACTIVITY_NUM = 1;

    private Uri imageUri;

    private Context mContext = Create_Doctor_Activity.this;

    private EditText doctor_userName;
    private EditText doctor_Name;
    private EditText doctorate_position;
    private EditText doctor_specialization;
    private EditText doctor_hospitalDepartment;
    private EditText doctor_briefDescription;
    private EditText doctor_Email;
    private EditText doctor_phoneNumber;

    private ProgressBar mProgressBar;

    private ImageView setDoctorPhoto;

    private Button createDoctorBtn;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__doctor_);

        storageReference = FirebaseStorage.getInstance().getReference("doctors_profile");
        databaseReference = FirebaseDatabase.getInstance().getReference("doctors_profile");

        setUpBottomNavigationView();


        doctor_userName = findViewById(R.id.edit_doctor_username);
        doctor_Name = findViewById(R.id.edit_doctor_full_name);
        doctorate_position = findViewById(R.id.edit_doctor_position_group);
        doctor_specialization = findViewById(R.id.edit_doctor_specialization);
        doctor_hospitalDepartment = findViewById(R.id.edit_doctor_work_department);
        doctor_briefDescription = findViewById(R.id.edit_doctor_work_description);
        doctor_Email = findViewById(R.id.edit_email);
        doctor_phoneNumber = findViewById(R.id.edit_doctor_PhoneNumber);

        setDoctorPhoto = (ImageView) findViewById(R.id.set_doctor_profile_photo);

        createDoctorBtn = findViewById(R.id.create_doctor_profile_button);

        mProgressBar = findViewById(R.id.progressBar_create_doctor_profile);

        mProgressBar.setVisibility(View.GONE);


        createDoctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createDoctorProfile();

            }
        });

        setDoctorPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFilePicker();

            }
        });

    }

    private String getFileExtension(Uri uri){

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }

    private void createDoctorProfile() {

        if(imageUri != null){

            StorageReference imageStorageReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));

            imageStorageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {




                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {




                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                //create a progree bar that load according to bites of data transferred
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getBytesTransferred());


                }
            });

        } else {

            Toast.makeText(this, "Please Pick an Image", Toast.LENGTH_SHORT).show();

        }

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

            Picasso.with(this).load(imageUri).into(setDoctorPhoto);

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
