package com.itgates.search_employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class ActivityAuth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }

    public void login(View view) {
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
        Animatoo.animateZoom(this);  //fire the zoom animation
    }

    public void Log(View view) {
        Intent intent2=new Intent(this, log.class);
        startActivity(intent2);
        Animatoo.animateZoom(this);
    }
}
