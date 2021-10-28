package com.example.yuppi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    // declaring the local variables
    EditText usernameEditText;
    EditText passwordEditText;
    TextView forgotTextView;
    Button   signupButton;
    Button   loginButton;
    boolean  matchFound; // this is gonna check if the match happened while looping through database and looking for a match


    // declaring the database variables
    private DatabaseReference ref;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //hooking up the xml buttons to the actual variables
        usernameEditText = (EditText) findViewById(R.id.usernameEditText); // usernameEditText variable is used to capture both the username and email
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        forgotTextView   = (TextView) findViewById(R.id.forgotTextView);
        signupButton     = (Button) findViewById(R.id.signupButton);
        loginButton      = (Button) findViewById(R.id.loginButton);


        // get the instance of the firebase database
        ref = FirebaseDatabase.getInstance().getReference().child("users");
        mAuth = FirebaseAuth.getInstance();


        //login button listener. Takes the user input from textview converts it to string and
        // goes through Firebase authentication system to approve for sign in.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email    = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Login Successful!",Toast.LENGTH_LONG).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                MenuActivity newUser = new MenuActivity(user);
                                Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                                startActivity(intent);
                            }else{
                                // If sign in fails, display a message to the user
                                Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }

                    }
                });
            }
        });



//        // login system. It parses through firebase database and if there is a match
//        // it would let you log in to main menu
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//                final String username = usernameEditText.getText().toString();
//                final String password = passwordEditText.getText().toString();
//
//                if (username.equals("") || password.equals("")){
//                    Toast.makeText(LoginActivity.this,"Missing field!",Toast.LENGTH_LONG).show();
//                }else{
//                    try{
//                        ref.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                for (DataSnapshot data: dataSnapshot.getChildren()){ // loop through database and check for password and email. If match found break and toast. Else give the problem definition
//                                    if (password.equals(data.child("password").getValue(String.class)) && username.equals(data.child("email").getValue(String.class)) || username.equals(data.child("name").getValue(String.class))){
//                                        Toast.makeText(LoginActivity.this,"Login Successful!",Toast.LENGTH_LONG).show();
//                                        Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
//                                        startActivity(intent);
//                                        matchFound = true;
//                                        break;
//                                    }
//                                }
//                                // if the match is not found print. This expression only executes if the loop breaks
//                                if (!matchFound){
//                                    Toast.makeText(LoginActivity.this,"Incorrect username or password.",Toast.LENGTH_LONG).show();
//                                }
//
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                            }
//                        });
//
//                    }catch (Exception e){
//                        Toast.makeText(LoginActivity.this,"Something went wrong...",Toast.LENGTH_LONG).show();
//                    }
//                }
//
//
//
//
//            }
//        });

        // takes you to SignupActivity to register to an app
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SignupActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        // forgotTextView takes to an intent for password retrieval
        forgotTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });


    }


}