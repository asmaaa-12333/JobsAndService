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

public class rent_result extends Fragment {
    RecyclerView recyclerViewrent;
    Adapter_rent  recycleAdapterrent;

    ProgressDialog progressDialog;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<Model_rent> arrayList;
    String  childgov , childcc , childnn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view= inflater.inflate(R.layout.fragment_rent_result, container, false);
        recyclerViewrent=view.findViewById(R.id.rentrecycle);
        arrayList=new ArrayList<>();
        progressDialog = new ProgressDialog(getActivity());
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("RentalData");
//        sharedHelper = new SharedHelp();
        assert getArguments() != null;
        childgov = getArguments().getString("Child1Rent");
        childcc =  getArguments().getString("Child2Rent");
        childnn = getArguments().getString("Child3Rent");
//        childpp=  getArguments().getString("Child4Rent");
        progressDialog.show();
        myRef.child(childgov).child(childcc).child(childnn).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String productname = ds.child("productName").getValue().toString();
                    String description = ds.child("description").getValue().toString();
                    String price = ds.child("price").getValue().toString();
                    String imgUrl = ds.child("imgUrl").getValue().toString();
                    String personeName = ds.child("personeName").getValue().toString();
                    String personePhone = ds.child("personePhone").getValue().toString();
//                    Model_rent model_rent=new Model_rent("Product_name : "+productname,"Description : "+description,"price: "+price , imgUrl);
                    Model_rent model_rent=new Model_rent(imgUrl,"Product_name : "+productname,"Description : "+description,"price: "+price , personeName,personePhone);
                    arrayList.add(model_rent);
                }
                recycleAdapterrent=  new Adapter_rent(getActivity(),arrayList);
                recyclerViewrent.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerViewrent.setAdapter(recycleAdapterrent);
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
      return view;
    }
}