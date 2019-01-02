package com.example.saint.medmanager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saint.medmanager.create_doctor_hospital_profiles.Create_Doctor_Activity;
import com.example.saint.medmanager.create_doctor_hospital_profiles.Create_Hospital_Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

public class Edit_Profile_Activity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 3;
    private static final int SELECT_FILE = 2;
    //DECLARE THE FIELDS



    //FIREBASE AUTHENTICATION FIELDS
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    //FIREBASE DATABASE FIELDS
    DatabaseReference mUserDatabse;
    StorageReference mStorageRef;

    //IMAGE HOLD URI
    Uri imageHoldUri = null;

    //PROGRESS DIALOG
    ProgressDialog mProgress;


    private static final int CHOOSE_IMAGE = 1;

    private EditText mUsername;
    private EditText mDisplayName;
    private EditText mWork;
    private EditText mDescription;
    private EditText mEmail;
    private EditText mPhoneNumber;
    private EditText mBlood_group;
    private EditText mGenotype;


    private ProgressBar progressBar;
    private TextView pleaseWait;

    private Button saveUser;


    private ImageView uploadPhoto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile_);


        mUsername = (EditText) findViewById(R.id.edit_username);
        mDisplayName = (EditText) findViewById(R.id.edit_display_name);
        mWork = (EditText) findViewById(R.id.edit_work);
        mDescription = (EditText) findViewById(R.id.edit_description);
        mEmail = (EditText) findViewById(R.id.edit_email);
        mPhoneNumber = (EditText) findViewById(R.id.edit_PhoneNumber);
        mBlood_group = findViewById(R.id.edit_blood_group);
        mGenotype = findViewById(R.id.edit_genotype);

        pleaseWait = (TextView) findViewById(R.id.please_wait_profile);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_profile);

        saveUser = (Button) findViewById(R.id.update_profile_button);
        uploadPhoto = (ImageView) findViewById(R.id.profile_photo);


        //hiding progress bar and please wait text field at start
        progressBar.setVisibility(View.GONE);
        pleaseWait.setVisibility(View.GONE);


        //ASSIGN INSTANCE TO FIREBASE AUTH
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //LOGIC CHECK USER
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {

                    finish();
                    Intent moveToHome = new Intent(Edit_Profile_Activity.this, Profile_Activity.class);
                    moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(moveToHome);

                }

            }
        };

        //PROGRESS DIALOG
        mProgress = new ProgressDialog(this);

        //FIREBASE DATABASE INSTANCE
        mUserDatabse = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
        mStorageRef = FirebaseStorage.getInstance().getReference();

        //ONCLICK SAVE PROFILE BTN
        saveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //LOGIC FOR SAVING USER PROFILE
                saveUserProfile();

            }
        });

        //USER IMAGEVIEW ONCLICK LISTENER
        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //LOGIC FOR PROFILE PICTURE
                profilePicSelection();

            }
        });


    }

    private void saveUserProfile() {

        final String username, description, fullname, work, email, phoneNumber, blood_group, genotype;

        username = mUsername.getText().toString().trim();
        description = mDescription.getText().toString().trim();
        fullname = mDisplayName.getText().toString().trim();
        work = mWork.getText().toString().trim();
        email = mEmail.getText().toString().trim();
        phoneNumber = mPhoneNumber.getText().toString().trim();
        blood_group = mBlood_group.getText().toString().trim();
        genotype = mGenotype.getText().toString().trim();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(fullname)) {

            if (imageHoldUri != null) {

                mProgress.setTitle("Saveing Profile");
                mProgress.setMessage("Please wait....");
                mProgress.show();

                StorageReference mChildStorage = mStorageRef.child("User_Profile").child(imageHoldUri.getLastPathSegment());
                String profilePicUrl = imageHoldUri.getLastPathSegment();

                mChildStorage.putFile(imageHoldUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        final Uri imageUrl = taskSnapshot.getDownloadUrl();

                        mUserDatabse.child("username").setValue(username);
                        mUserDatabse.child("description").setValue(description);
                        mUserDatabse.child("fullname").setValue(fullname);
                        mUserDatabse.child("work").setValue(work);
                        mUserDatabse.child("email").setValue(email);
                        mUserDatabse.child("phonenumber").setValue(phoneNumber);
                        mUserDatabse.child("blood_group").setValue(blood_group);
                        mUserDatabse.child("genotype").setValue(genotype);


                        mUserDatabse.child("userid").setValue(mAuth.getCurrentUser().getUid());
                        mUserDatabse.child("imageurl").setValue(imageUrl.toString());

                        mProgress.dismiss();

                        finish();
                        Intent moveToHome = new Intent(Edit_Profile_Activity.this, Profile_Activity.class);
                        moveToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(moveToHome);


                    }
                });
            } else {

                Toast.makeText(Edit_Profile_Activity.this, "Please select the profile pic", Toast.LENGTH_LONG).show();

            }

        } else {

            Toast.makeText(Edit_Profile_Activity.this, "Please enter username and status", Toast.LENGTH_LONG).show();

        }

    }

    private void profilePicSelection() {


        //DISPLAY DIALOG TO CHOOSE CAMERA OR GALLERY

        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Edit_Profile_Activity.this);
        builder.setTitle("Add Photo!");

        //SET ITEMS AND THERE LISTENERS
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Take Photo")) {
                    cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    private void cameraIntent() {

        //CHOOSE CAMERA
        Log.d("gola", "entered here");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {

        //CHOOSE IMAGE FROM GALLERY
        Log.d("gola", "entered here");
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //SAVE URI FROM GALLERY
        if (requestCode == SELECT_FILE && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);

        } else if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            //SAVE URI FROM CAMERA

            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);

        }


        //image crop library code
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageHoldUri = result.getUri();

                uploadPhoto.setImageURI(imageHoldUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }


}