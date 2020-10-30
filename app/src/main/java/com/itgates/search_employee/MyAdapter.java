package com.itgates.search_employee;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    List<MyModel> list;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    public MyAdapter(Context c, List<MyModel> mylist){
        this.context=c;
        this.list=mylist;
        sharedpreferences = c.getSharedPreferences("JS", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

    }
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v= LayoutInflater.from(context).inflate(R.layout.room_two,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        final MyModel mymodel =list.get(position);
        holder.place.setText(mymodel.getPlace());
        holder.descri.setText(mymodel.getDescription());
        holder.category.setText(mymodel.getCategory());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                editor.putString("UidForSearch",mymodel.Uid);
                Bundle bundle = new Bundle();
                bundle.putString("UidForSearch",mymodel.getUid());
                Navigation.findNavController(v).navigate(R.id.action_search2_to_productsFragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView place,descri,category;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            place=itemView.findViewById(R.id.textview4) ;
            descri =itemView.findViewById(R.id.textview5) ;
           category =itemView.findViewById(R.id.cate) ;
            constraintLayout =itemView.findViewById(R.id.mainConstraintId) ;
        }
    }
}


