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
    private FirebaseDatabase  rootNode;
    private DatabaseReference reference;
    private FirebaseAuth      mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // hooking all the xml widgets to the variables
        newNameEditText     = findViewById(R.id.newNameEditText);
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

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignupActivity.this,"Registration Is Complete!",Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            MenuActivity newUser = new MenuActivity(user);
                            Intent intent = new Intent(SignupActivity.this,MenuActivity.class);
                            startActivity(intent);
                        }else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(SignupActivity.this, "Something Went Wrong...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


//        // method to register and add the data to the database
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference("users");
//                //get all the values from xml file and convert it toString()
//                String name     = newNameEditText.getText().toString();
//                String email    = newEmailEditText.getText().toString();
//                String password = newPasswordEditText.getText().toString();
//
//                if(name.equals("") || email.equals("") || password.equals("")){
//                    Toast.makeText(SignupActivity.this, "Missing field!", Toast.LENGTH_LONG).show();
//                }else{
//                    Users users = new Users(name,email,password);
//                    //reference.child(email).setValue(users);
//                    //DatabaseReference  ref = reference.child("users").push();
//
//                    reference.child(name).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful()){
//                                Toast.makeText(SignupActivity.this,"Registration Is Complete!",Toast.LENGTH_LONG).show();
//                            }else{
//                                Toast.makeText(SignupActivity.this,"Something Went Wrong...",Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
//                }
//
//
//
//
//
//
//
//
//            }
//        });


    }

}