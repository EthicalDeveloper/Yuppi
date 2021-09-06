package com.example.yuppi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddContactsActivity extends AppCompatActivity {

    TextView cancelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        cancelTextView = (TextView)findViewById(R.id.cancelTextView);

        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddContactsActivity.this,ContactsActivity.class);
                startActivity(intent);
            }
        });
    }
}