package com.example.yuppi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    //declaring all the variables
    EditText newNameEditText;
    EditText newPasswordEditText;
    EditText newEmailEditText;
    Button   registerButton;
    Button   cancelButton;

    // declaring the database variables
    private FirebaseAuth      mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // hooking all the xml widgets to the variables
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        newEmailEditText    = findViewById(R.id.newEmailEditText);
        registerButton      = findViewById(R.id.registerButton);
        cancelButton        = findViewById(R.id.cancelButton);
        mAuth               = FirebaseAuth.getInstance();



        //method to go back to the login page
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),LoginActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        // registration button listener function. Converts textViews to string and processes it
        // through Firebase system and adds it to the database for authentication
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email    = newEmailEditText.getText().toString();
                String password = newPasswordEditText.getText().toString();

                // create an account through Firebase database system
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    FirebaseUser user = mAuth.getCurrentUser();
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            MenuActivity newUser = new MenuActivity(user); // add the user to the menuActivity for verification
                            // function to verify the email through Firebase
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignupActivity.this, "Email sent...", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignupActivity.this,VerificationMessageActivity.class); // if verify email is successfully sent then take it to VerificationMessageActivity
                                        Bundle bundle = new Bundle(); // passing the current obtained email from the user to show in VerificationMessageActivity
                                        bundle.putString("email",email);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(SignupActivity.this, "Something Went Wrong...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });





    }

}