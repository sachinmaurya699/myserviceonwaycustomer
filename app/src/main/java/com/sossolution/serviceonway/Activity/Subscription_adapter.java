package com.sossolution.serviceonway.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Class.Subscription_include_item;
import com.sossolution.serviceonway.R;

import java.util.List;

public class Subscription_adapter extends RecyclerView.Adapter<Subscription_adapter.My_viewholder>
{
    private Context context;
    private List<Subscription_include_item> list_include_item;

    public Subscription_adapter(Context context,List<Subscription_include_item> list_include_item)
    {
        this.context = context;
        this.list_include_item=list_include_item;
    }


    @NonNull
    @Override
    public My_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.my_design_include_item,parent,false);
        return new My_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_viewholder holder, int position)
    {
        Subscription_include_item item=list_include_item.get(position);
        String text=item.getText();
        String price=item.getPrice();

        holder.textView.setText(text);
        holder.text_price.setText(price);




    }

    @Override
    public int getItemCount()
    {
        return list_include_item.size();
    }

    public class My_viewholder extends RecyclerView.ViewHolder
    {
        TextView textView,text_price;

        public My_viewholder(@NonNull View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.textview_include_service);
            text_price=itemView.findViewById(R.id.textview_inlcude_price);
        }
    }
}
