package com.itgates.search_employee;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static int splash_screen=5000;

Animation topanimation,bottomanimation;
ImageView imageView;
TextView welcome,J_s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        topanimation= AnimationUtils.loadAnimation( this,R.anim.top_animator);
        bottomanimation= AnimationUtils.loadAnimation( this,R.anim.bottom_animator);
        imageView=findViewById(R.id.imageView);
        welcome=findViewById(R.id.textView2);
        J_s=findViewById(R.id.textView3);
        imageView.setAnimation(topanimation);
        welcome.setAnimation(bottomanimation);
        J_s.setAnimation(bottomanimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),ActivityAuth.class);
                startActivity(intent);
                finish();

            }
        },splash_screen);
    }
}