package com.example.yuppi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    //declaring the variables
    Button addNewContacts;
    Button sortNewContacts;
    ListView contactsListView;
    //declaring the firebase database instance
    FirebaseDatabase db;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        // attach the add button to a variable
        addNewContacts = (Button) findViewById(R.id.addContactsButton);
        contactsListView = (ListView) findViewById(R.id.contactsListView);
        // change the activity when pressed
        addNewContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsActivity.this,AddContactsActivity.class);
                startActivity(intent);
            }
        });

        //get the database instance and reference to access the contacts
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Contacts");
        final ArrayList<String> contacts= new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,contacts);
        contactsListView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    Contacts contact = childSnapshot.getValue(Contacts.class);
                    contacts.add(contact.getFirstName());
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

    }
}