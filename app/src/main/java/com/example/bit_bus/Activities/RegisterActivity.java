package com.example.bit_bus.Activities;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bit_bus.R;
import com.example.bit_bus.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegisterActivity extends AppCompatActivity {

    private ImageView ImgUserPhoto;
    static int PReqCode = 1 ;
    static int REQUESCODE = 1 ;
    Uri pickedImgUri ;
    private EditText emailEditText, passwordEditText, nameEditText;
    private EditText branchEditText, rollEditText;
    private FloatingActionButton registerButton;
    private static final String USER = "user";
    private static final String TAG = "RegisterActivity";
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        nameEditText = findViewById(R.id.fullname_edittext);
        branchEditText = findViewById(R.id.Branch_edittext);
        rollEditText = findViewById(R.id.Roll_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        emailEditText = findViewById(R.id.email_editText);
        /*ImgUserPhoto = findViewById(R.id.pic_image);*/
        registerButton = findViewById(R.id.reg_btn);



      /*  ImgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 22) {

                    checkAndRequestForPermission();


                }
                else
                {
                    openGallery();
                }
            }
        });*/








        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validateName() || !validateBranch() || !validateEmail() || !validatePassword() || !validateRoll())
                {
                    return;
                }


                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("user");

                //get all
                String FullName = nameEditText.getText().toString();
                String Email = emailEditText.getText().toString();
                String Password = passwordEditText.getText().toString();
                String Branch = branchEditText.getText().toString();
                String Roll = rollEditText.getText().toString();


                User user = new User(FullName,Roll,Branch,Password,Email);
                reference.child(FullName).setValue(user);

                main();



            }
        });

    }



    /*private void openGallery() {


        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);

    }*/

   /* private void checkAndRequestForPermission() {

        if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(RegisterActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            }

            else
            {
                ActivityCompat.requestPermissions(RegisterActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }

        }
        else
            openGallery();








    }*/


 /*   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null ) {

            // the user has successfully picked an image
            // we need to save its reference to a Uri variable
            pickedImgUri = data.getData() ;
            ImgUserPhoto.setImageURI(pickedImgUri);
            StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
            final StorageReference filepath = mStorage.child(pickedImgUri.getLastPathSegment());
            filepath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(RegisterActivity.this,"upload successful",Toast.LENGTH_LONG).show();
                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                        }
                    });

                }
            });

        }


    }*/



    private void main() {

        Intent intent = new Intent(RegisterActivity.this , MainActivity.class);
        startActivity(intent);
    }

    private Boolean validateName()
    {
        String val = nameEditText.getText().toString();
        if(val.isEmpty()){
            nameEditText.setError("field cant be empty");
            return  false;
        }
        else
        {
            nameEditText.setError(null);
            return  true;
        }
    }
    private Boolean validatePassword()
    {
        String val = passwordEditText.getText().toString();
        if(val.isEmpty()){
            passwordEditText.setError("field cant be empty");
            return  false;
        }
        else
        {
            passwordEditText.setError(null);
            return  true;
        }
    }
    private Boolean validateBranch()
    {
        String val = branchEditText.getText().toString();
        if(val.isEmpty()){
            branchEditText.setError("field cant be empty");
            return  false;
        }
        else
        {
            branchEditText.setError(null);
            return  true;
        }
    }
    private Boolean validateRoll()
    {
        String val = rollEditText.getText().toString();
        if(val.isEmpty()){
            rollEditText.setError("field cant be empty");
            return  false;
        }
        else
        {
            rollEditText.setError(null);
            return  true;
        }
    }
    private Boolean validateEmail()
    {
        String val = emailEditText.getText().toString();
        if(val.isEmpty()){
            emailEditText.setError("field cant be empty");
            return  false;
        }
        else
        {
            emailEditText.setError(null);
            return  true;
        }
    }



}