package com.itgates.search_employee;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.HashMap;
import java.util.Map;

public class Add_Jop extends Fragment {

    EditText name,title,description,phone,salary,country,governorate,neighporhood;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ProgressDialog progressDialog;
    Button button;
    MaterialSpinner spinner;
    String selectedItemSpinner = "الوادي الجديد";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add__jop, container, false);
        spinner = view.findViewById(R.id.spinner1);
//        spinner.setItems("القاهره", "القليوبيه", "بني سويف", "الدقهليه", "بورسعيد");

        spinner.setItems("الوادي الجديد","المنيا","المنوفيه","مطروح","كفر الشيخ","قنا","الفيوم","شمال سيناء","الشرقيه","السويس","دمياط","الجيزه","جنوب سيناء","بور سعيد","بنى سويف","البحيره","البحر الاحمر","سوهاج","الاقصر","القاهره", "القليوبيه", "الغربية", "الإسكندرية", "الاسماعيليه","اسوان","الدقهلية");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                selectedItemSpinner = item;
            }
        });

        name=view.findViewById(R.id.textInputLayout61);
        country=view.findViewById(R.id.textInputLayout111);
        neighporhood=view.findViewById(R.id.textInputLayout121);
        governorate=view.findViewById(R.id.textInputLayout101);
        phone=view.findViewById(R.id.textInputLayout71);
        title=view.findViewById(R.id.textInputLayout81);
        salary=view.findViewById(R.id.textInputLayout131);
        description=view.findViewById(R.id.textInputLayout91);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Jop");
        button=view.findViewById(R.id.button3);
        progressDialog = new ProgressDialog(getActivity());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();

                Map<String, String> map = new HashMap<>();
                map.put("name", name.getText().toString());
                map.put("title", title.getText().toString());
                map.put("description", description.getText().toString());
                map.put("governate", governorate.getText().toString());
                map.put("country",country.getText().toString());
                map.put("phone", phone.getText().toString());
                map.put("salary", salary.getText().toString());
                map.put("neighporhood",neighporhood.getText().toString());

                myRef.child(selectedItemSpinner)
                        .child(country.getText().toString())
                        .child(neighporhood.getText().toString())
                        .child(title.getText().toString())
                        .push()
                        .setValue(map);
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "insert sucses", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}