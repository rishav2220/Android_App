package com.example.bit_bus.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bit_bus.R;

import java.util.ArrayList;

public class BUSActivity extends AppCompatActivity {
     private Spinner day;
     private Spinner Start , End;
     private Button Search;


     ArrayList<String>arrayList_day;
     ArrayAdapter<String>arrayAdapter_day;

    ArrayList<String>arrayList_start;
    ArrayAdapter<String>arrayAdapter_start;

    ArrayList<String>arrayList_end;
    ArrayAdapter<String>arrayAdapter_end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);


        day = findViewById(R.id.spinner2);
        Start = findViewById(R.id.spinner3);
        End = findViewById(R.id.spinner4);
        Search = findViewById(R.id.button2);

        arrayList_day = new ArrayList<>();
        arrayList_day.add("Select Day");
        arrayList_day.add("Sunday");
        arrayList_day.add("Monday");
        arrayList_day.add("Tuesday");
        arrayList_day.add("Wednesday");
        arrayList_day.add("Thursday");
        arrayList_day.add("Friday");
        arrayList_day.add("Saturday");

        arrayAdapter_day = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_day);
        day.setAdapter(arrayAdapter_day);


        arrayList_start = new ArrayList<>();
        arrayList_start.add("Select Starting Point");
        arrayList_start.add("BIT-PATNA");
        arrayList_start.add("GANDHI MAIDAN");

        arrayAdapter_start = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_start);
        Start.setAdapter(arrayAdapter_start);


        arrayList_end = new ArrayList<>();
        arrayList_end.add("Select End Point");
        arrayList_end.add("BIT-PATNA");
        arrayList_end.add("GANDHI MAIDAN");

        arrayAdapter_end = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_end);
        End.setAdapter(arrayAdapter_end);


         Search.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String spinner_day = day.getSelectedItem().toString();
                 String spinner_start = Start.getSelectedItem().toString();
                 String spinner_end = End.getSelectedItem().toString();

                 if((spinner_day.equals("Monday")||spinner_day.equals("Tuesday")||spinner_day.equals("Wednesday")||spinner_day.equals("Thursday")||spinner_day.equals("Friday"))&&(spinner_start.equals("BIT-PATNA")&&spinner_end.equals("GANDHI MAIDAN"))){
                    Intent intent = new Intent(BUSActivity.this , ONEActivity.class);
                    startActivity(intent);

                 }
                 else if ((spinner_day.equals("Saturday")||spinner_day.equals("Sunday")) && (spinner_start.equals("BIT-PATNA")&&spinner_end.equals("GANDHI MAIDAN"))){
                     ////new Activity 2
                     Intent intent = new Intent(BUSActivity.this , SecondActivity.class);
                     startActivity(intent);
                 }

                 else if((spinner_day.equals("Monday")||spinner_day.equals("Tuesday")||spinner_day.equals("Wednesday")||spinner_day.equals("Thursday")||spinner_day.equals("Friday"))&&(spinner_start.equals("GANDHI MAIDAN")&&spinner_end.equals("BIT-PATNA"))){
                     ////new activity 1
                     Intent intent = new Intent(BUSActivity.this , ThirdActivity.class);
                     startActivity(intent);

                 }
                 else if ((spinner_day.equals("Saturday")||spinner_day.equals("Sunday"))&&(spinner_start.equals("GANDHI MAIDAN")&&spinner_end.equals("BIT-PATNA"))){
                     ////new Activity 2
                     Intent intent = new Intent(BUSActivity.this , FourthActivity.class);
                     startActivity(intent);
                 }
                 else
                 {
                     Toast.makeText(BUSActivity.this, "INVALID SELECTION", Toast.LENGTH_SHORT).show();
                 }
             }
         });



    }
}
