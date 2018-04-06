package com.example.lawati97.deliveryapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
        private EditText userName;
        private EditText passwordet;
        private ProgressDialog progressDialog;
        private Button loginButton;
        private FirebaseAuth firebaseAuth;
        private Intent homepage;
        private boolean toHomePage;
        private boolean toLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        //textFields
         userName = (EditText) findViewById(R.id.userName);
         passwordet = (EditText) findViewById(R.id.password);
        //buttons
         loginButton = (Button) findViewById(R.id.loginButton);
        //Register text field
        final TextView register = (TextView) findViewById(R.id.registerTextView);

        homepage = new Intent(this, Homepage.class);

        loginButton.setOnClickListener(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent registerIntent = new Intent(LogInActivity.this, RegisterActivity.class);
                LogInActivity.this.startActivity(registerIntent);
            }
        });


    }
    private void LogIn(){
    String email = userName.getText().toString().trim().toLowerCase();
    String password = passwordet.getText().toString().trim();
    toHomePage = false;
    if(TextUtils.isEmpty(email)){
        Toast.makeText(LogInActivity.this,"Please enter your email", Toast.LENGTH_SHORT).show();
        return;
    }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(LogInActivity.this,"Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging in");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){

                    startActivity(new Intent(LogInActivity.this, Homepage.class));
                    finish();
                }
                else if (!task.isSuccessful()){
                        Toast toast = Toast.makeText(LogInActivity.this, "Your email or password are incorrect", Toast.LENGTH_SHORT);
                        toast.show();
                }
            }
        });

    }

    private void Admin(){
        String email = userName.getText().toString().trim().toLowerCase();
        String password = passwordet.getText().toString().trim();
        progressDialog.setMessage("Admin page is loading");
        if(email.equals("admin")){
            if(password.equals("123123")) {
                toLogin = false;
                progressDialog.show();
                startActivity(new Intent(LogInActivity.this, AdminPage.class));
                finish();
            }
        }
    }
    @Override
    public void onClick(View view) {
        if(view == loginButton){
            toLogin = true;
            Admin();
            if(toLogin) {
                LogIn();
            }
        }
    }
}
