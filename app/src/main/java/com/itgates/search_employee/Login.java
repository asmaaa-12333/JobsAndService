package com.itgates.search_employee;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
EditText name,phone,email,password;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("please wait");
        name=findViewById(R.id.filledTextField);
        phone=findViewById(R.id.filledTextField2);
        email=findViewById(R.id.filledTextField3);
        password =findViewById(R.id.filled2TextField);
        sharedpreferences = getSharedPreferences("JS", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Animatoo.animateSlideLeft(this); //fire the slide left animation
    }

    public void signup(View view) {
        if (name.getText().toString().isEmpty()){
            name.setError("please enter name");
        }
        if (phone.getText().toString().isEmpty()){
           phone.setError("please enter phone");
        }
        if (email.getText().toString().isEmpty()){
            email.setError("please enter email");
        }
        if (password.getText().toString().isEmpty()){
            password.setError("please enter password");
        }
        else {
        progressDialog.show();
    mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                mAuth.getCurrentUser().sendEmailVerification();
                progressDialog.dismiss();
                Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
            } else{
                progressDialog.dismiss();
                Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }


        }
    });

    }
}}
