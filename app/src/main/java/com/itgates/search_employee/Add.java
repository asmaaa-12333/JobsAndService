package com.itgates.search_employee;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.HashMap;
import java.util.Map;

public class Add extends Fragment {

    Button button3;
    EditText city,neighborhood,place,description;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ProgressDialog progressDialog;
    MaterialSpinner spinner,spinner2,spinner3;
    String categoreSelected = "مطاعم", governate = "الوادي الجديد";
    SharedPreferences sharedpreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_add, container, false);
      button3=view.findViewById(R.id.button3);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("data");
        progressDialog = new ProgressDialog(getActivity());
        neighborhood=view.findViewById(R.id.textInputLayout31);
        description=view.findViewById(R.id.textInputLayout1);
        place=view.findViewById(R.id.textInputLayout21);
        city=view.findViewById(R.id.governateEdit1);

        sharedpreferences = getActivity().getSharedPreferences("JS", Context.MODE_PRIVATE);


        spinner = view. findViewById(R.id.spinner3);
        spinner.setItems("مطاعم", "مولات", "صيدلبات", "محلات ملابي", "عطاره");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                categoreSelected = item;
            }
        });


         spinner2 = view. findViewById(R.id.spinner2);
        spinner.setItems("الوادي الجديد","المنيا","المنوفيه","مطروح","كفر الشيخ","قنا","الفيوم","شمال سيناء","الشرقيه","السويس","دمياط","الجيزه","جنوب سيناء","بور سعيد","بنى سويف","البحيره","البحر الاحمر","سوهاج","الاقصر","القاهره", "القليوبيه", "الغربية", "الإسكندرية", "الاسماعيليه","اسوان","الدقهلية");
        spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                governate = item;
            }
        });
//        spinner3 = view. findViewById(R.id.spinner3);
//        spinner3.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow");
//        spinner3.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//
//            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
//            }
//        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();

                Map<String, String> map = new HashMap<>();
                map.put("neighborhood", neighborhood.getText().toString());
                map.put("place", place.getText().toString());
                map.put("description", description.getText().toString());
                map.put("city", city.getText().toString());
                map.put("governate",governate);
                map.put("category",categoreSelected);
                map.put("Uid",sharedpreferences.getString("Uid",""));
                myRef.child(categoreSelected).child(governate)
                        .child(city.getText().toString())
                        .child(neighborhood.getText().toString()).push().setValue(map);
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "insert succses", Toast.LENGTH_SHORT).show();




            }
        });

      return view;
    }
}