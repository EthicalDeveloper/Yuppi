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

import com.google.firebase.auth.FirebaseAuth;



public class ForgotPasswordActivity extends AppCompatActivity {

    // declare the variables for further use
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

        //Reset the password through Firebase. If the email exists then send an reset email to a user
        // with instructions for resetting the email

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth  = FirebaseAuth.getInstance();
                String       email = resetEmailTextBox.getText().toString();

                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ForgotPasswordActivity.this,"Email sent to " + email, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });




    }
}