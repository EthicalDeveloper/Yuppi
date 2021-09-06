package com.example.yuppi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ContactsActivity extends AppCompatActivity {

    // declaring the variables
    TextView addNewContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        addNewContacts = (TextView) findViewById(R.id.addNewContacts);

        addNewContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsActivity.this,AddContactsActivity.class);
                startActivity(intent);
            }
        });

    }
}