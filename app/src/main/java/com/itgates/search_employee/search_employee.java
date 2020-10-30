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


public class search_employee extends Fragment {
    MaterialSpinner spinner4,spinner5,spinner6,spinner7;
    Button button4;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ProgressDialog progressDialog;
    ArrayList<String> arrayList1 , arrayList2,arrayList3;
    String gove = "الوادي الجديد",childg = "الوادي الجديد",childc,childn,childj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search_employee, container, false);
        button4=view.findViewById(R.id.button44);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Jop");
        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3=new ArrayList<>();
//        sharedHelper = new SharedHelp();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetch data please wait ...");
        spinner4 = view. findViewById(R.id.spinner41);
        spinner4.setItems("الوادي الجديد","المنيا","المنوفيه","مطروح","كفر الشيخ","قنا","الفيوم","شمال سيناء","الشرقيه","السويس","دمياط","الجيزه","جنوب سيناء","بور سعيد","بنى سويف","البحيره","البحر الاحمر","سوهاج","الاقصر","القاهره", "القليوبيه", "الغربية", "الإسكندرية", "الاسماعيليه","اسوان","الدقهلية");
//        spinner4.setItems("القاهره", "القليوبيه", "بني سويف", "الدقهليه", "بورسعيد");

        spinner4.setOnItemSelectedListener(
                new MaterialSpinner.OnItemSelectedListener<String>() {

                    @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                       gove = item;
                        childg = item;
                        progressDialog.show();
                        myRef.child(gove).addValueEventListener(new ValueEventListener() {
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


        spinner5 = view. findViewById(R.id.spinner51);
        spinner5.setItems(arrayList1);
        spinner5.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                childc = item;
                progressDialog.show();
                myRef.child(gove).child(item).addValueEventListener(new ValueEventListener() {
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
        spinner6 = view. findViewById(R.id.spinner61);
        spinner6.setItems(arrayList2);
        spinner6.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                childn = item;
                progressDialog.show();
                myRef.child(gove).child(childc).child(item).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                            arrayList3.add(ds.getKey());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                progressDialog.dismiss();

            }
        });



        spinner7 = view. findViewById(R.id.spinner71);
        spinner7.setItems(arrayList3);
        spinner7.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                childj=item;
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Child1Job",childg);
                bundle.putString("Child2Job",childc);
                bundle.putString("Child3Job",childn);
                bundle.putString("Child4Job",childj);
                Navigation.findNavController(view).navigate(R.id.action_search_employee_to_employee_result,bundle);
            }
        });
        return view;

    }


}
