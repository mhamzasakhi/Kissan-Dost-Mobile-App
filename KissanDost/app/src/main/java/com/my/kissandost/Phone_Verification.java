package com.my.kissandost;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Phone_Verification extends AppCompatActivity {

    private String CodeByFirebase;
    private EditText PhoneNumber,Code;
    private Button sendButton, verifyButton;

    // Firebase Connectivity...
    static PhoneAuthCredential phoneDetails;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks stateChange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone__verification);


        // get the XML file id's
        PhoneNumber = findViewById(R.id.phoneNumber);
        Code = findViewById(R.id.code);
        sendButton = findViewById(R.id.sendButton);
        verifyButton = findViewById(R.id.verifyButton);


        Code.setEnabled(false);
        verifyButton.setEnabled(false);


            // Send msg to the given Number for verification...
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("Number", PhoneNumber.getText().toString());

                assignStateChange();

                PhoneAuthProvider.getInstance().verifyPhoneNumber(PhoneNumber.getText().toString(),
                                                                    60,
                                                                    TimeUnit.SECONDS,
                                                            Phone_Verification.this,
                                                                    stateChange);
                Log.i("Hamza", "onClick: "+"Data is store successfully");
                Toast.makeText(getApplicationContext(),"Successful_1 Code",Toast.LENGTH_SHORT).show();
            }
        });



        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthCredential phoneAuthProvider = PhoneAuthProvider.getCredential(CodeByFirebase,Code.getText().toString());
            if(phoneAuthProvider.getSmsCode().equals(Code.getText().toString())) {

                    Intent intent = new Intent(getApplicationContext(),sign_up.class);
                    intent.putExtra("Number",PhoneNumber.getText().toString());
                    Toast.makeText(getApplicationContext(),"Successful Code",Toast.LENGTH_SHORT).show();
                    phoneDetails = phoneAuthProvider;
                    startActivity(intent);

            }
            else {
                    Toast.makeText(getApplicationContext(),"Wrong OTP",Toast.LENGTH_SHORT).show();
              }
            }
        });

    }


    private void assignStateChange() {
        stateChange = new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Code.setEnabled(true);
                verifyButton.setEnabled(true);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }


            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Log.i("OTP Sent is", s);
                CodeByFirebase = s;
                sendButton.setEnabled(false);
                Code.setEnabled(true);
                verifyButton.setEnabled(true);
                // phoneText.setEnabled(false);
            }
        };
    }
}
