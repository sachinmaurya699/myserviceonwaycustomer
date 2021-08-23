package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.R;

import java.util.ArrayList;
import java.util.List;

public class Condition_adapter extends RecyclerView.Adapter<Condition_adapter.My_viewholder>{

    Context context;
    List<String> list_condition;


    public Condition_adapter(Context context,List<String> list_condition)
    {
        this.context=context;
        this.list_condition=list_condition;

    }

    @NonNull
    @Override
    public My_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.my_subscription_design,parent,false);
        return new My_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_viewholder holder, int position)
    {
        String text=list_condition.get(position);
        holder.textView.setText(text);

    }

    @Override
    public int getItemCount()
    {
        return list_condition.size();
    }

    public class My_viewholder  extends RecyclerView.ViewHolder
    {
        TextView textView;
        public My_viewholder(@NonNull View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.my_text);
        }
    }
}
