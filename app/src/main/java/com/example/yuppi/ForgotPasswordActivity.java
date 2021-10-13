package com.example.yuppi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class ForgotPasswordActivity extends AppCompatActivity {

    Button   resetPasswordButton;
    TextView backTextView;
    EditText resetEmailTextBox;

    // declaring the database variables
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // assign all the widgets to the actual variables
        resetPasswordButton = findViewById(R.id.resetPasswordButton);
        backTextView        = findViewById(R.id.backTextView);
        resetEmailTextBox   = findViewById(R.id.resetEmailTextBox);

        // declare the database with it users root
        ref = FirebaseDatabase.getInstance().getReference().child("users");

        // back button to go to previous intent
        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Reset the password. Sends an email if there is a match in the database for the further reset instructions
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = resetEmailTextBox.getText().toString();

                if(email.equals("")){
                    Toast.makeText(ForgotPasswordActivity.this, "Missing field!", Toast.LENGTH_LONG).show();
                }else{
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot data: dataSnapshot.getChildren()){ // Loop through each child without specifying
                                if (email.equals(data.child("email").getValue(String.class))){
                                    Toast.makeText(ForgotPasswordActivity.this, "Match Found!", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(ForgotPasswordActivity.this, "Not Found!", Toast.LENGTH_LONG).show();
                                }
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });


    }
}