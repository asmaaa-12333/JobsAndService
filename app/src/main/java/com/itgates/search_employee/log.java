package com.itgates.search_employee;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

public class log extends AppCompatActivity {
    EditText email,password;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait");
        email = findViewById(R.id.filledTextFieldd);
        password = findViewById(R.id.filled2TextFieldd2);
        sharedpreferences = getSharedPreferences("JS", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Animatoo.animateSlideLeft(this); //fire the slide left animation
    }

    public void loggin(View view) {

        if (email.getText().toString().isEmpty()){
            email.setError("please enter email");
        }
        if (password.getText().toString().isEmpty()){
            password.setError("please enter password");
        }
        else {
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        if (mAuth.getCurrentUser().isEmailVerified()){
                            progressDialog.dismiss();
                            Toast.makeText(log.this, "Login Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            editor.putString("Uid",mAuth.getCurrentUser().getUid());
                            editor.commit();
                            Log.i("UidLogin",mAuth.getCurrentUser().getUid());

                        } else {
                        progressDialog.dismiss();
                        Toast.makeText(log.this, "check in box", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(log.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
                }} );


        }
    }}



