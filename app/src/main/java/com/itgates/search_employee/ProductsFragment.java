package com.itgates.search_employee;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductsFragment extends Fragment {

    ProgressDialog progressDialog;
    FirebaseDatabase database;
    DatabaseReference myRef;
    SharedPreferences sharedpreferences;
    ArrayList<ProductModel> productModels;
    ProductRecyclerAdapter productRecyclerAdapter;
    RecyclerView recyclerView;
    String uid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_products, container, false);
        recyclerView = view.findViewById(R.id.rentrecycle);
        sharedpreferences = getActivity().getSharedPreferences("JS", Context.MODE_PRIVATE);
        productModels = new ArrayList<>();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Get data please wait ...");
        progressDialog.show();
        productRecyclerAdapter = new ProductRecyclerAdapter(getActivity(),productModels);
        recyclerView.setAdapter(productRecyclerAdapter);
        database = FirebaseDatabase.getInstance();
        uid = getArguments().getString("UidForSearch");
        Log.i("Uid",sharedpreferences.getString("UidForSearch", ""));
        try {
            myRef = database.getReference("Products").child(uid);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String img = ds.child("imgUrl").getValue().toString();
                        String title = ds.child("productName").getValue().toString();
                        String price = ds.child("productPrice").getValue().toString();
                        productModels.add(new ProductModel(img, title, price));
                    }
                    productRecyclerAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                    Log.i("DataSnap",dataSnapshot.toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (Exception e)
        {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return view;
    }
}