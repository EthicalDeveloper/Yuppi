package com.example.yuppi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    // declaring the local variables
    EditText usernameEditText;
    EditText passwordEditText;
    Button   signupButton;
    Button   loginButton;

    // declaring the database variables
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //hooking up the xml buttons to the actual variables
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        signupButton     = (Button) findViewById(R.id.signupButton);
        loginButton      = (Button) findViewById(R.id.loginButton);

        ref = FirebaseDatabase.getInstance().getReference().child("users");


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String username = usernameEditText.getText().toString();
                final String password = passwordEditText.getText().toString();
                try{
                ref.child(username).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Users users = dataSnapshot.getValue(Users.class);
                        if (password.equals(users.getPassword())){
                            Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this,"Incorrect Password...",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                }catch (Exception e){
                    Toast.makeText(LoginActivity.this,"Incorrect Username...",Toast.LENGTH_LONG).show();
                }


            }
        });



        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SignupActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }


}