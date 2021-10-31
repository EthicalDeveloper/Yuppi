package com.example.yuppi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



public class VerificationMessageActivity extends AppCompatActivity {

    // declare all the view variables
    TextView userEmailTextView;
    TextView contactUsTextView;
    Button resendEmailButton;
    Button continueButton;
    String email;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_message);

        Bundle bundle = getIntent().getExtras();


        // attach the corresponding view with variables
        userEmailTextView = findViewById(R.id.userEmailTextView);
        contactUsTextView =  findViewById(R.id.contactUsTextView);
        resendEmailButton =  findViewById(R.id.resendEmailButton);
        continueButton    =  findViewById(R.id.continueButton);
        // get the email obtained in SignupActivity
        email             =  bundle.getString("email");
        // this should assign the TextView to a string from previous intent
        userEmailTextView.setText(email);

        // user transfers to login page after he is confident that the verification is complete
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerificationMessageActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        // if the app fails to send verification email then take the user back to the SignUp activity
        resendEmailButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(VerificationMessageActivity.this, "Please try again.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(VerificationMessageActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });


        // contact us page
        contactUsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerificationMessageActivity.this);
                builder.setMessage("yuppichatapp@gmail.com");
                builder.setTitle("Contact Yuppi Team");
                builder.show();
            }
        });

    }
}