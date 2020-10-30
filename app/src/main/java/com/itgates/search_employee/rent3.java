package com.itgates.search_employee;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;


public class rent3 extends Fragment {
    MaterialSpinner spinner11,spinner22,spinner33,spinner44;
    Button search;
    String  mohafza ="الوادي الجديد", childgov ="الوادي الجديد", childcc , childnn , childpp;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ProgressDialog progressDialog;
    ArrayList<String> arrayList1 , arrayList2,arrayList3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_rent3, container, false);
        search=view.findViewById(R.id.search);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("RentalData");
        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3= new ArrayList<>();
//        sharedHelper = new SharedHelp();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetch data please wait ...");
        spinner11 = view. findViewById(R.id.gover);
        spinner11.setItems("الوادي الجديد","المنيا","المنوفيه","مطروح","كفر الشيخ","قنا","الفيوم","شمال سيناء","الشرقيه","السويس","دمياط","الجيزه","جنوب سيناء","بور سعيد","بنى سويف","البحيره","البحر الاحمر","سوهاج","الاقصر","القاهره", "القليوبيه", "الغربية", "الإسكندرية", "الاسماعيليه","اسوان","الدقهلية");
//        spinner11.setItems("القاهره", "القليوبيه", "بني سويف", "الدقهليه", "بورسعيد");

        spinner11.setOnItemSelectedListener(
                new MaterialSpinner.OnItemSelectedListener<String>() {

                    @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                        mohafza = item;
                        childgov = item;
                        progressDialog.show();
                        myRef.child(mohafza).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot ds : dataSnapshot.getChildren()){
                                    arrayList1.add(ds.getKey());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        progressDialog.dismiss();

                    }
                });
        spinner22 = view. findViewById(R.id.city1);
        spinner22.setItems(arrayList1);
        spinner22.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                childcc = item;
                progressDialog.show();
                myRef.child(mohafza).child(item).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                            arrayList2.add(ds.getKey());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                progressDialog.dismiss();
            }



        });
        spinner33 = view. findViewById(R.id.region);
        spinner33.setItems(arrayList2);
        spinner33.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                childnn = item;
                progressDialog.show();
                myRef.child(mohafza).child(item).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                            arrayList1.add(ds.getKey());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                progressDialog.dismiss();

            }
        });
//        spinner44 = view. findViewById(R.id.product);
//        spinner44.setItems(arrayList3);
//        spinner44.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//
//            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                childpp=item;
//            }
//        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Child1Rent",childgov);
                bundle.putString("Child2Rent",childcc);
                bundle.putString("Child3Rent",childnn);
//                bundle.putString("Child4Rent",childpp);
                Navigation.findNavController(view).navigate(R.id.action_rent3_to_rent_result,bundle);
            }
        });


        return view;
    }
}