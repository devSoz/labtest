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

public class MainActivity extends AppCompatActivity {

    private EditText emailEdt, passEdt, ageEdt, cityEdt, pnoEdt;
    private Button sendDatabtn;


    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    data userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our edittext and button
        emailEdt = findViewById(R.id.emailEditText);
        passEdt = findViewById(R.id.passwordEditText);
        cityEdt = findViewById(R.id.cityEditText);
        ageEdt = findViewById(R.id.ageEditText);
        pnoEdt = findViewById(R.id.pnoEditText);
        sendDatabtn = findViewById(R.id.idBtnSendData);
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

                // getting text from our edittext fields.
                String email = emailEdt.getText().toString();
                String password = passEdt.getText().toString();
                String city = cityEdt.getText().toString();
                String age = ageEdt.getText().toString();
                String pno = pnoEdt.getText().toString();

                // below line is for checking whether the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(MainActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(email, password, city, age, pno);
                }
            }
        });

    }

    private void addDatatoFirebase(String email, String password, String city, String age, String pno) {
        // below 3 lines of code is used to set
        // data in our object class.
        userData.setEmail(email);
        userData.setPassword(password);
        userData.setAge(age);
        userData.setCity(city);
        userData.setPno(pno);


        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(userData);

                // after adding this data we are showing toast message.
                Toast.makeText(MainActivity.this, "data added", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(MainActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}