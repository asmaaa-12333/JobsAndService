package com.itgates.search_employee;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;


public class search extends Fragment {
    MaterialSpinner spinner4,spinner5,spinner6,spinner7;
Button button4;

String categorei = "مطاعم" , mohafza ="الوادي الجديد", child1 = "مطاعم" , child2 = "الوادي الجديد" , child3 , child4;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ProgressDialog progressDialog;
    ArrayList<String> arrayList1 , arrayList2;
//    SharedHelp sharedHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_search, container, false);
       button4=view.findViewById(R.id.button4);
        spinner4 = view. findViewById(R.id.spinner4);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("data");
        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
//        sharedHelper = new SharedHelp();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetch data please wait ...");
        spinner4.setItems("مطاعم", "مولات", "صيدلبات", "محلات ملابي", "عطاره");
        spinner4.setOnItemSelectedListener(
                new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                categorei = item;
                child1 = item;
            }
        });
        spinner5 = view. findViewById(R.id.spinner5);
        spinner5.setItems("الوادي الجديد","المنيا","المنوفيه","مطروح","كفر الشيخ","قنا","الفيوم","شمال سيناء","الشرقيه","السويس","دمياط","الجيزه","جنوب سيناء","بور سعيد","بنى سويف","البحيره","البحر الاحمر","سوهاج","الاقصر","القاهره", "القليوبيه", "الغربية", "الإسكندرية", "الاسماعيليه","اسوان","الدقهلية");
//        spinner5.setItems("القاهره", "القليوبيه", "بني سويف", "الدقهليه", "بورسعيد");

        spinner5.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                mohafza = item;
                child2 = item;
                progressDialog.show();
                myRef.child(categorei).child(mohafza).addValueEventListener(new ValueEventListener() {
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
        spinner6 = view. findViewById(R.id.spinner6);
        spinner6.setItems(arrayList1);
        spinner6.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                countary = item;
                child3 = item;
                progressDialog.show();
                myRef.child(categorei).child(mohafza).child(item).addValueEventListener(new ValueEventListener() {
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

        spinner7 = view. findViewById(R.id.spinner7);
        spinner7.setItems(arrayList2);
        spinner7.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                child4 = item;
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sharedHelper.putKey(getActivity(),"Child1Categore",child1);
//                sharedHelper.putKey(getActivity(),"Child2Categore",child2);
//                sharedHelper.putKey(getActivity(),"Child3Categore",child3);
//                sharedHelper.putKey(getActivity(),"Child4Categore",child4);

                Bundle bundle = new Bundle();
                bundle.putString("Child1Categore",child1);
                bundle.putString("Child2Categore",child2);
                bundle.putString("Child3Categore",child3);
                bundle.putString("Child4Categore",child4);
           Navigation.findNavController(view).navigate(R.id.action_search3_to_search2 , bundle);
            }
        });
       return view;
    }
}