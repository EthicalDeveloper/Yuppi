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

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Map;

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

        // Reset the password. Sends an email if there is a match in the database for the further reset instructions
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email   = resetEmailTextBox.getText().toString().trim(); // get the value from texview field and convert it to a string

                if(email.equals("")){
                    Toast.makeText(ForgotPasswordActivity.this, "Missing field!", Toast.LENGTH_LONG).show();
                }else{
                    try{
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot data: dataSnapshot.getChildren()){ // loop through database and check for password and email. If match found break and toast. Else give the problem definition
                                    if (email.equals(data.child("email").getValue(String.class))){
                                        Toast.makeText(ForgotPasswordActivity.this, "Email found!", Toast.LENGTH_LONG).show();

                                        try {
                                            // set all the properties for gmail com. Port, server and enable stl
                                            Properties properties = new Properties();
                                            properties.put("mail.transport.protocol", "smtp");
                                            properties.put("mail.smtp.starttls.enable","true");
                                            properties.put("mail.smtp.auth", "true");
                                            properties.put("mail.smtp.host", "smtp.gmail.com");
                                            properties.put("mail.smtp.port", "587");

                                            // start the session and authenticate the gmail with username and password
                                            Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
                                                @Override
                                                protected PasswordAuthentication getPasswordAuthentication() {
                                                    return new PasswordAuthentication("yuppichatapp@gmail.com","yuppinewapp");
                                                }
                                            });

                                            // set the message that you want to sent and pass the session as an argument
                                            Message message = new MimeMessage(session);
                                            message.setSubject("Email from yuppi!");
                                            message.setContent("<h1>Email from yuppi</h1>", "text/html");

                                            // set the address that you are sending the email to
                                            Address addressTo = new InternetAddress("yuppichatapp@gmail.com");
                                            message.setRecipient(Message.RecipientType.TO, addressTo);
                                            Transport.send(message);
                                        }
                                        catch (Exception e){
                                            e.printStackTrace();
                                        }

                                        break;
                                    }
                                }



                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }catch (Exception e){
                        Toast.makeText(ForgotPasswordActivity.this,"Something went wrong...",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }
}