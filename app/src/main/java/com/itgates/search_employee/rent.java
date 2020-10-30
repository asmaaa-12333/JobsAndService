package com.itgates.search_employee;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class rent extends Fragment {
    Button rent1,rent2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_rent, container, false);
        rent1=view.findViewById(R.id.rent1);
        rent2=view.findViewById(R.id.rent2);
        rent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_rent_to_rent22);

            }
        });
        rent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_rent_to_rent3);

            }
        });
        return view;

    }
}