package com.example.lawati97.deliveryapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText phoneNumber;
    private TextView logIn;
    private Button buttonRegister;
    private String rUsersName;
    private String rUserEmailName;
    private String rUserpassword;
    private String rUserPhoneNumber;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private Intent loginPage;
    private Intent homePage;
    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        //textFields
        name = (EditText) findViewById(R.id.edName);
        email = (EditText) findViewById(R.id.edUserName);
        password = (EditText) findViewById(R.id.edPassword);
        phoneNumber = (EditText) findViewById(R.id.edphoneNumber);
        logIn = (TextView) findViewById(R.id.toLogInPage);

        //Button
        buttonRegister = (Button) findViewById(R.id.registerButton);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterActivity.this, "Log In page", Toast.LENGTH_SHORT);
                loginPage = new Intent(RegisterActivity.this, LogInActivity.class);
                startActivity(loginPage);
                finish();
            }
        });


    }

    private void registerUser() {

        toast = Toast.makeText(RegisterActivity.this, "Please check your details", Toast.LENGTH_LONG);

        homePage = new Intent(RegisterActivity.this, Homepage.class);

        rUsersName = name.getText().toString().trim();
        rUserEmailName = email.getText().toString().trim().toLowerCase();
        rUserpassword = password.getText().toString().trim().toLowerCase();
        rUserPhoneNumber = phoneNumber.getText().toString().trim();

        if (rUsersName.isEmpty()) {
            toast.show();
            return; // helps to stop the function
        }
        if ((rUserEmailName.isEmpty())) {
            toast.show();
            return; // helps to stop the function
        }
        if (rUserpassword.isEmpty()) {
            toast.show();
            return; // helps to stop the function
        }
        if (rUserPhoneNumber.isEmpty()) {
            toast.show();
            return; // helps to stop the function
        }


        firebaseAuth.createUserWithEmailAndPassword(rUserEmailName, rUserpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //the task is completed return to the login page and print task is completed
                            progressDialog.setMessage("user Registration");
                            progressDialog.show();
                            toast = Toast.makeText(RegisterActivity.this, "Registration completed", Toast.LENGTH_SHORT);
                            toast.show();
                            startActivity(homePage);
                            finish();
                        }
                        else{
                            toast = Toast.makeText(RegisterActivity.this, "Failed to Register Please try again", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister) {
            registerUser();
        }
    }
}