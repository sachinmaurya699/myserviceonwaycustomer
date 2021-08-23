package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Class.Subscriptn_not_include;
import com.sossolution.serviceonway.R;

import java.util.List;

public class Subscription_not_include_adapter extends RecyclerView.Adapter<Subscription_not_include_adapter.my_viewholder1>
{
    private Context context;
    private List<Subscriptn_not_include> list_not_include_item;

    private Object Tag;

    public Subscription_not_include_adapter(Context context, List<Subscriptn_not_include> list_not_include_item)
    {
        this.context=context;
        this.list_not_include_item=list_not_include_item;

    }

    @NonNull
    @Override
    public my_viewholder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view= LayoutInflater.from(context).inflate(R.layout.my_subscription_design,parent,false);


        return  new my_viewholder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Subscription_not_include_adapter.my_viewholder1 holder, int position)
    {
        Subscriptn_not_include include= list_not_include_item.get(position);
        String text=include.getText();
        holder.textView.setText(text);

    }

    @Override
    public int getItemCount() {
        return list_not_include_item.size();
    }

    public class my_viewholder1  extends RecyclerView.ViewHolder
    {
        TextView textView;
        public my_viewholder1(@NonNull View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.my_text);
        }
    }
}
