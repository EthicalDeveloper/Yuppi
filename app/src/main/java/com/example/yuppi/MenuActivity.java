package com.example.yuppi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends AppCompatActivity {

    // declaring the variables
    FloatingActionButton contactsFloatingActionButton;
    FloatingActionButton callsFloatingActionButton;
    FloatingActionButton chatsFloatingActionButton;
    FloatingActionButton settingsFloatingActionButton;
    FirebaseUser         user;

    public MenuActivity(FirebaseUser user){
        this.user = user;
    }

    public MenuActivity(){};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // hooking up the variables with the xml buttons
        contactsFloatingActionButton = (FloatingActionButton)findViewById(R.id.contactsFloatingActionButton);
        callsFloatingActionButton = (FloatingActionButton)findViewById(R.id.callsFloatingActionButton);
        chatsFloatingActionButton = (FloatingActionButton) findViewById(R.id.chatsFloatingActionButton);
        settingsFloatingActionButton = (FloatingActionButton)findViewById(R.id.settingsFloatingActionButton);

        contactsFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });
    }
}