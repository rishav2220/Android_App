package com.example.bit_bus.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bit_bus.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class ProfileActivity extends AppCompatActivity {




    private TextView nameTextView, rollTextView, branchTextView, emailTextView, passwordTextView;
    private final String TAG = this.getClass().getName().toUpperCase();
    private DatabaseReference profileUserRef;
    private FirebaseAuth mAuth;
    private  FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameTextView = findViewById(R.id.name_textview);
        rollTextView = findViewById(R.id.roll_textview);
        branchTextView = findViewById(R.id.branch_textview);
        emailTextView = findViewById(R.id.email_textview);
        passwordTextView = findViewById(R.id.password_textview);


        showUserData();



    }

    private void showUserData() {

        SharedPreferences mPreferences = getSharedPreferences("MY DataBase",MODE_PRIVATE);
        String user_username = mPreferences.getString("fullName","default");
        String user_email = mPreferences.getString("email","default");
        String user_roll = mPreferences.getString("roll","default");
        String user_password = mPreferences.getString("password","default");
        String user_branch = mPreferences.getString("branch","default");
        nameTextView.setText(user_username);
        rollTextView.setText( user_roll);
        branchTextView.setText( user_branch);
        emailTextView.setText( user_email);
        passwordTextView.setText( user_password);

    }
}
