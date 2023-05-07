package com.example.bit_bus.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bit_bus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText userName,password;
    private  TextView reg;
    private FloatingActionButton Login;
    private  FirebaseAuth mAuth;
    private final String TAG = "MainActivity";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);



         userName = findViewById(R.id.user_edittext);
         password = findViewById(R.id.password_edittext);
         Login = findViewById(R.id.button);
         reg = findViewById(R.id.register_textview);

         Login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 if(!validatePassword()||!validateName())
                 {
                     return;

                 }else
                 {
                     isUser();
                 }

             }
         });

         reg.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intewnt = new Intent(MainActivity.this , RegisterActivity.class);
                 startActivity(intewnt);
             }
         });

    }




    private Boolean validateName()
    {
        String val = userName.getText().toString();
        if(val.isEmpty()){
            userName.setError("field cant be empty");
            return  false;
        }
        else
        {
            userName.setError(null);
            return  true;
        }
    }
    private Boolean validatePassword()
    {
        String val = password.getText().toString();
        if(val.isEmpty()){
            password.setError("field cant be empty");
            return  false;
        }
        else
        {
            password.setError(null);
            return  true;
        }
    }




    public void isUser()
    {
        final String userEnterUsername = userName.getText().toString();
        final String userEnteredPassword = password.getText().toString();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
        Query checkUser = reference.orderByChild("fullName").equalTo(userEnterUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  if(dataSnapshot.exists())

                  {

                      userName.setError(null);

                      String passwordFromDB = dataSnapshot.child(userEnterUsername).child("password").getValue(String.class);
                      if(passwordFromDB.equals(userEnteredPassword))
                      {

                          userName.setError(null);
                          String nameFromDB = dataSnapshot.child(userEnterUsername).child("fullName").getValue(String.class);
                          String emailFromDB = dataSnapshot.child(userEnterUsername).child("email").getValue(String.class);
                          String rollFromDB = dataSnapshot.child(userEnterUsername).child("roll").getValue(String.class);
                          String branchFromDB = dataSnapshot.child(userEnterUsername).child("branch").getValue(String.class);


                          mPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                          mPreferences = getSharedPreferences("MY DataBase", MODE_PRIVATE);
                          mEditor = mPreferences.edit();
                          mEditor.putString("fullName",nameFromDB);
                          mEditor.putString("roll",rollFromDB);
                          mEditor.putString("branch",branchFromDB);
                          mEditor.putString("password",passwordFromDB);
                          mEditor.putString("email",emailFromDB);
                          mEditor.apply();

                          Intent intent = new Intent(getApplicationContext(),PofiActivity.class);
                          startActivity(intent);


                                 /* intent.putExtra("fullName",nameFromDB);
                                  intent.putExtra("roll",rollFromDB);
                                  intent.putExtra("branch",branchFromDB);
                                  intent.putExtra("password",passwordFromDB);
                                  intent.putExtra("email",emailFromDB);*/





                      }else
                      {
                          password.setError("wrong");
                      }

                  }
                  else
                  {
                      userName.setError("no user");
                  }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }





}









