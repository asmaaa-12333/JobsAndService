package com.itgates.search_employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    Context context;
    List<model> list;
    public adapter(Context c, List<model> mylist){
        this.context=c;
        this.list=mylist;

    }
    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v= LayoutInflater.from(context).inflate(R.layout.room,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {
       model model =list.get(position);
        holder.name.setText(model.getName());
        holder.phone.setText(model.getPhone());
        holder.salary.setText(model.getSalary());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone,salary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
          name=itemView.findViewById(R.id.name) ;
            phone =itemView.findViewById(R.id.phone) ;
            salary=itemView.findViewById(R.id.salary) ;
        }
    }
}
