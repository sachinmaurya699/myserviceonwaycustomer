package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Activity.Subscription_history_details_Activity;
import com.sossolution.serviceonway.Class.Subscription_history_item;
import com.sossolution.serviceonway.R;

import java.util.List;

public  class Subscription_history_adapter extends RecyclerView.Adapter<Subscription_history_adapter.My_viewholder>
{
    Context context;
    List<Subscription_history_item> list_item;

    public Subscription_history_adapter(Context context, List<Subscription_history_item> list_item)
    {
        this.context = context;
        this.list_item = list_item;
    }

    @NonNull
    @Override
    public My_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.subscription_layout,parent,false);
        return new My_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_viewholder holder, int position)
    {

         Subscription_history_item item= list_item.get(position);
         String text_id=item.getId();
         String text_date=item.getPurches_date();
         String text_price=item.getPrice();

         holder.textView_id.setText("SUBSCRIPTION_ID"+"-"+text_id);
         holder.textView_date.setText("PURCHES-DATE"+"-"+text_date);
         holder.textView_price.setText("PRICE"+"-"+"\u20B9"+text_price);
         holder.button.setOnClickListener(new View.OnClickListener()
         {
             @Override
             public void onClick(View view) {

                 Intent intent= new Intent(context, Subscription_history_details_Activity.class);
                 context.startActivity(intent);


             }
         });

    }


    @Override
    public int getItemCount()
    {
        return list_item.size();
    }

    public class My_viewholder extends RecyclerView.ViewHolder
    {
        TextView textView_id,textView_date,textView_price;
        Button button;

        public My_viewholder(@NonNull View itemView)
        {
            super(itemView);
            textView_id=itemView.findViewById(R.id.subscription_id);
            textView_date=itemView.findViewById(R.id.purches_date);
            textView_price=itemView.findViewById(R.id.price_new);
            button=itemView.findViewById(R.id.button_view);

        }
    }
}
