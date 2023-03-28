package com.example.labtest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private EditText emailEdt, passEdt;
    private Button sendDatabtn, retireveDatabtn;

    private TextView retrieveTV;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    data userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // initializing our edittext and button
        emailEdt = findViewById(R.id.emailEditText);
        passEdt = findViewById(R.id.passwordEditText);
        sendDatabtn = findViewById(R.id.sendbtn);

        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("EmployeeInfo");

        // initializing our object
        // class variable.
        userData = new data();

        // adding on click listener for our button.
        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEdt.getText().toString();
                String password = passEdt.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(MainActivity2.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    checkLogin(email, password);

                }
            }
        });

    }

    private void checkLogin(String email, String password) {

        // calling add value event listener method
        // for getting the values from database.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();

                    Toast.makeText(MainActivity2.this, "Login successful.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity2.this, "data added", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(), MainActivity3.class);
                    startActivity(intent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainActivity2.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getdata() {

        // calling add value event listener method
        // for getting the values from database.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();

                retrieveTV.setText(map.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainActivity2.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}