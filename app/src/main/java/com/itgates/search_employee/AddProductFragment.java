package com.itgates.search_employee;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class AddProductFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference myRef;
    private StorageReference mStorageRef;
    ImageView imageView;
    Uri imgUri;
    EditText name , price;
    Button uploadBtn;
    SharedPreferences sharedpreferences;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        name = view.findViewById(R.id.productNameId);
        price = view.findViewById(R.id.productPriceId);
        uploadBtn = view.findViewById(R.id.uploadBtn);
        myRef = database.getReference("Products");
        imageView = view.findViewById(R.id.imgUpload);
        sharedpreferences = getActivity().getSharedPreferences("JS", Context.MODE_PRIVATE);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Uploading please wait");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgUri == null)
                {
                    Toast.makeText(getActivity(), "Please select image !", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        progressDialog.show();
                        final StorageReference ref = mStorageRef.child("Photos/" + System.currentTimeMillis());
                        ref.putFile(imgUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        Map<String, String> map = new HashMap<>();
                                        map.put("imgUrl", uri.toString());
                                        map.put("productName", name.getText().toString());
                                        map.put("productPrice", price.getText().toString());
                                        map.put("Uid", sharedpreferences.getString("Uid", ""));
                                        myRef.child(sharedpreferences.getString("Uid", "")).push().setValue(map);
                                        progressDialog.dismiss();
                                        Toast.makeText(getActivity(), "Uploaded Success", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Exception " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
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
            imageView.setImageURI(imgUri);
        }
    }
}