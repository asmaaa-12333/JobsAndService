package com.itgates.search_employee;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class search2 extends Fragment {
    RecyclerView recyclerView;

    MyAdapter  recycleAdapter;

    ProgressDialog progressDialog;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<MyModel> arrayList;
    String  child1 , child2 , child3 , child4;

//    SharedHelp sharedHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_search2, container, false);
        recyclerView=view.findViewById(R.id.res);
        arrayList=new ArrayList<>();
        progressDialog = new ProgressDialog(getActivity());
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("data");
//        sharedHelper = new SharedHelp();
        child1 = getArguments().getString("Child1Categore");
        child2 =  getArguments().getString("Child2Categore");
        child3 = getArguments().getString("Child3Categore");
        child4 =  getArguments().getString("Child4Categore");
        progressDialog.show();
        myRef.child(child1).child(child2).child(child3).child(child4).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String place = ds.child("place").getValue().toString();
                    String description = ds.child("description").getValue().toString();
                    String Category = ds.child("category").getValue().toString();
                    String Uid = ds.child("Uid").getValue().toString();
                MyModel mym =new MyModel("Place : "+place,"Description : "+description,"Category : "+Category,Uid);
              arrayList.add(mym);
                }
                recycleAdapter=  new MyAdapter(getActivity(),arrayList);
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