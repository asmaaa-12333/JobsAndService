package com.itgates.search_employee;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class employee_result extends Fragment {
    RecyclerView recyclerView;
    adapter  recycleAdapter;

    ProgressDialog progressDialog;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<model> arrayList;
    String  childg , childc , childn , childj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


       View view= inflater.inflate(R.layout.fragment_employee_result, container, false);
        recyclerView=view.findViewById(R.id.res);
        arrayList=new ArrayList<>();
        progressDialog = new ProgressDialog(getActivity());
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Jop");
//        sharedHelper = new SharedHelp();
        childg = getArguments().getString("Child1Job");
        childc =  getArguments().getString("Child2Job");
        childn = getArguments().getString("Child3Job");
        childj =  getArguments().getString("Child4Job");
        progressDialog.show();
        myRef.child(childg).child(childc).child(childn).child(childj).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.child("name").getValue().toString();
                    String phone = ds.child("phone").getValue().toString();
                    String salary = ds.child("salary").getValue().toString();
                    model mym =new model("Name : "+name,"Phone : "+phone,"Salary : "+salary);
                    arrayList.add(mym);
                }
                recycleAdapter=  new adapter(getActivity(),arrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(recycleAdapter);
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       return view;
    }


}
