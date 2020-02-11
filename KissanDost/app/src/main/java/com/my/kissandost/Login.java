package com.my.kissandost;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



public class Login extends AppCompatActivity {

    private Button loginFarmer, loginRetailer, signup;
    private EditText phoneField, passwordField;
    private ProgressDialog progressDialog;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    static String ID;
    static String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();

        phoneField = findViewById(R.id.phone);
        passwordField = findViewById(R.id.password);

        loginFarmer = findViewById(R.id.loginFarmer);
        loginRetailer = findViewById(R.id.loginRetailer);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        signup = findViewById(R.id.signUp);


        // When signup button pressed Call this action listener function

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Sign UP");
                builder.setMessage("Create a new account as a");
                builder.setPositiveButton("Farmer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), Phone_Verification.class);
                        intent.putExtra("value", "Farmer");
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Retailer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), Phone_Verification.class);
                        intent.putExtra("value", "Retailer");
                        startActivity(intent);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });



        // When LoginFarmer button pressed Call this action listener function
        // Customer login as a Farmer...
        loginFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = phoneField.getText().toString();
                String pass = passwordField.getText().toString();
                if (email.equals("") || pass.equals("")) {
                    Toast.makeText(Login.this, "Email Id or password is wrong!", Toast.LENGTH_SHORT).show();
                    phoneField.setText("");
                    passwordField.setText("");
                } else {
                    final ProgressDialog progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMessage("Logging You In...");
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(phoneField.getText().toString(), passwordField.getText().toString())
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        /*  else {*/
                                        Toast.makeText(Login.this, "Log In Success", Toast.LENGTH_SHORT).show();
                                        Log.i("Current User: ", String.valueOf(mAuth.getCurrentUser()));
                                        ID = mAuth.getCurrentUser().getEmail();
                                        type = "Farmer";
                                        FindKey obj = new FindKey();
                                        obj.getKey();
                                        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                                        startActivity(intent);
                                        phoneField.setText("");
                                        passwordField.setText("");
                                    } else {
                                        Toast.makeText(Login.this, "Email or Password is wrong", Toast.LENGTH_SHORT).show();
                                    }
                                    progressDialog.cancel();
                                }
                            });
                }
            }
        });





        // When LoginRetailer button pressed Call this action listener function
        // Customer login as a Retailer...

        loginRetailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = phoneField.getText().toString();
                String pass = passwordField.getText().toString();
                if (email.equals("") || pass.equals("")) {
                    Toast.makeText(Login.this, "Email Id or password is wrong!", Toast.LENGTH_SHORT).show();
                    phoneField.setText("");
                    passwordField.setText("");
                } else {
                    final ProgressDialog progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMessage("Logging You In As Retailer...");
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(phoneField.getText().toString(), passwordField.getText().toString())
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Login.this, "Log In Success", Toast.LENGTH_SHORT).show();
                                        Log.i("Current User: ", String.valueOf(mAuth.getCurrentUser()));
                                        ID = mAuth.getCurrentUser().getEmail();
                                        type = "Retailer";
                                        FindKey obj = new FindKey();
                                        obj.getKey();
                                        Intent intent = new Intent(getApplicationContext(), retailer.class);
                                        startActivity(intent);
                                        phoneField.setText("");
                                        passwordField.setText("");
                                    } else {
                                        Toast.makeText(Login.this, "Email or Password is wrong", Toast.LENGTH_SHORT).show();
                                    }
                                    progressDialog.cancel();
                                }
                            });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null) {
            FirebaseAuth.getInstance().signOut();
        }
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
