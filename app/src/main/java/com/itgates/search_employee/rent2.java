package com.itgates.search_employee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class rent2 extends Fragment {
    Button addrent;
    EditText city,neighborhood,nameperson,phoneperson,nameproduct,description,price;
    ImageView imageproduct;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ProgressDialog progressDialog;
    MaterialSpinner spinnerrent;
    String  governate = "الوادي الجديد";
    Uri imgUri;
    private StorageReference mStorageRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View view= inflater.inflate(R.layout.fragment_rent2, container, false);
        addrent=view.findViewById(R.id.addrent);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("RentalData");
        progressDialog = new ProgressDialog(getActivity());
        mStorageRef = FirebaseStorage.getInstance().getReference();
        city=view.findViewById(R.id.city1);
        imageproduct=view.findViewById(R.id.imgProductId);
        neighborhood=view.findViewById(R.id.region1);
        nameperson=view.findViewById(R.id.person1);
        phoneperson=view.findViewById(R.id.phone1);
        nameproduct=view.findViewById(R.id.nameproduct1);
        description=view.findViewById(R.id.description1);
        price=view.findViewById(R.id.price1);
        imageproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        spinnerrent = view. findViewById(R.id.spinnerr1);
        spinnerrent.setItems("الوادي الجديد","المنيا","المنوفيه","مطروح","كفر الشيخ","قنا","الفيوم","شمال سيناء","الشرقيه","السويس","دمياط","الجيزه","جنوب سيناء","بور سعيد","بنى سويف","البحيره","البحر الاحمر","سوهاج","الاقصر","القاهره", "القليوبيه", "الغربية", "الإسكندرية", "الاسماعيليه","اسوان","الدقهلية");
//        spinnerrent.setItems("القاهره", "القليوبيه", "بني سويف", "الدقهليه", "بورسعيد");
//
        spinnerrent.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                governate = item;
            }
        });
        addrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                final StorageReference ref = mStorageRef.child("Photos/" + System.currentTimeMillis());
                ref.putFile(imgUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Map<String, String> map = new HashMap<>();
                                map.put("governate",governate);
                                map.put("city", city.getText().toString());
                                map.put("neighborhood", neighborhood.getText().toString());
                                map.put("personeName", nameperson.getText().toString());
                                map.put("personePhone", phoneperson.getText().toString());
                                map.put("productName", nameproduct.getText().toString());
                                map.put("description", description.getText().toString());
                                map.put("price", price.getText().toString());
                                map.put("imgUrl",uri.toString());
                                myRef.child(governate)
                                        .child(city.getText().toString())
                                        .child(neighborhood.getText().toString()).push().setValue(map);
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "insert succses", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


            }
        });
      return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK )
        {
            this.imgUri = data.getData();
            imageproduct.setImageURI(imgUri);
        }
    }
}