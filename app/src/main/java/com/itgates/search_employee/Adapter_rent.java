package com.itgates.search_employee;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.List;


    public class Adapter_rent extends RecyclerView.Adapter<com.itgates.search_employee.Adapter_rent.ViewHolder> {
        Context context;
        List<Model_rent> list;
        AlertDialog.Builder aBuilder;
        public Adapter_rent(Context c, List<Model_rent> mylist){
            this.context=c;
            this.list=mylist;
            aBuilder = new AlertDialog.Builder(c);
        }
        @NonNull
        @Override
        public com.itgates.search_employee.Adapter_rent.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            View v= LayoutInflater.from(context).inflate(R.layout.row,parent,false);
            return new ViewHolder(v);

        }

        @Override
        public void onBindViewHolder(@NonNull Adapter_rent.ViewHolder holder, int position) {
             final Model_rent model_rent =list.get(position);
           // holder.imageproduct.setText(model.getName());
            holder.nameprod.setText(model_rent.getProductName());
            holder.descriprod.setText(model_rent.getDescription());
            holder.priceprod.setText(model_rent.getPrice());
            Picasso.get().load(model_rent.getImage()).into(holder.imageView);
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    aBuilder.setTitle("Person Data ");
                    aBuilder.setMessage("Person Name : "+model_rent.getPersonName()+"\n"+"Person Phone : "+model_rent.getPersonPhone());
                    aBuilder.create().show();
                }
            });
        }


        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView nameprod,descriprod,priceprod;
            ImageView imageView;
            ConstraintLayout constraintLayout;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                nameprod=itemView.findViewById(R.id.NameOfProduct) ;
                descriprod =itemView.findViewById(R.id.Description) ;
                priceprod=itemView.findViewById(R.id.Price) ;
                imageView = itemView.findViewById(R.id.imageproduct);
                constraintLayout = itemView.findViewById(R.id.mainConstraintId);
            }
        }
}