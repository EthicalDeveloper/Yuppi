package com.example.yuppi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddContactsActivity extends AppCompatActivity {

    // declaring the buttons for canceling and creating contacts
    Button cancelButton;
    Button createButton;
    Button addPhotoButton;

    // all the textview fields to retrieve the info from
    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText cellPhoneNumberEditText;
    EditText homePhoneNumberEditText;
    EditText emailEditText;
    EditText notesEditText;

    //declaring the firebase database instance
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        // attaching the buttons to the variables
        cancelButton = (Button)findViewById(R.id.cancelButton);
        createButton = (Button)findViewById(R.id.createButton);
        addPhotoButton = (Button)findViewById(R.id.addPhotoButton);

        // attaching the edittext to the variables
        firstNameEditText = (EditText)findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText)findViewById(R.id.lastNameEditText);
        cellPhoneNumberEditText = (EditText)findViewById(R.id.cellPhoneNumberEditText);
        homePhoneNumberEditText = (EditText)findViewById(R.id.homePhoneNumberEditText);
        emailEditText = (EditText)findViewById(R.id.emailEditText);
        notesEditText = (EditText)findViewById(R.id.notesEditText);


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddContactsActivity.this,ContactsActivity.class);
                startActivity(intent);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String cellPhoneNumber = cellPhoneNumberEditText.getText().toString();
                String homePhoneNumber = homePhoneNumberEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String notes = notesEditText.getText().toString();

                if(!firstName.isEmpty() && !lastName.isEmpty() && !cellPhoneNumber.isEmpty()){
                    Contacts contacts = new Contacts(firstName,lastName,cellPhoneNumber,homePhoneNumber,email,notes);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Contacts");
                    reference.child(firstName).setValue(contacts).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            firstNameEditText.setText("");
                            lastNameEditText.setText("");
                            cellPhoneNumberEditText.setText("");
                            homePhoneNumberEditText.setText("");
                            emailEditText.setText("");
                            notesEditText.setText("");
                            Toast.makeText(AddContactsActivity.this,"Contact is successfully created",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }
}