package com.itgates.search_employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.productRecyclerAdapter> {

    Context context;
    List<ProductModel> list;

    public ProductRecyclerAdapter(Context context, List<ProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductRecyclerAdapter.productRecyclerAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.row2,parent,false);
        return new ProductRecyclerAdapter.productRecyclerAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerAdapter.productRecyclerAdapter holder, int position) {
        ProductModel mymodel =list.get(position);
        holder.title.setText(mymodel.getTitle());
        holder.price.setText(mymodel.getPrice());
        Picasso.get().load(mymodel.getImgUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class productRecyclerAdapter extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title , price;
        public productRecyclerAdapter(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageproduct);
            title = itemView.findViewById(R.id.NameOfProduct);
            price = itemView.findViewById(R.id.PriceOfProduct);
        }
    }
}
