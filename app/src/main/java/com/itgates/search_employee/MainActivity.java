package com.itgates.search_employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
NavController navController;
    SharedPreferences sharedpreferences;
//    SharedPreferences.Editor editor;
    String Uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottom);
        navController= Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
        sharedpreferences = getSharedPreferences("JS", Context.MODE_PRIVATE);
//        editor = sharedpreferences.edit();
        Uid = sharedpreferences.getString("Uid","");
        Log.i("UidHome",Uid);
    }

}
