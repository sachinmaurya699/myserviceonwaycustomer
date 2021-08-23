package com.sossolution.serviceonway.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.R;

import java.util.List;

public class Subscription_adapter_grid extends RecyclerView.Adapter<Subscription_adapter_grid.My_viewholder>
{

    Context context;
    List<Subscription_grid_item> list_item;


    public Subscription_adapter_grid(Context context, List<Subscription_grid_item> list_item)
    {
        this.context=context;
        this.list_item=list_item;
    }

    @NonNull
    @Override
    public My_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view= LayoutInflater.from(context).inflate(R.layout.subscrption_grid_design,parent,false);


        return new My_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_viewholder holder, int position)
    {
        Subscription_grid_item item=list_item.get(position);
        String text=item.getText();
        String title=item.getTitle();
        int image=item.getImage();

        holder.textView.setText(text);
        holder.text_message.setText(title);
        holder.imageView.setImageResource(image);



    }

    @Override
    public int getItemCount()
    {
        return list_item.size();
    }

    public class My_viewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        TextView text_message;

        public My_viewholder(@NonNull View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.text_grid);
            imageView=itemView.findViewById(R.id.image_grid);
            text_message=itemView.findViewById(R.id.text_message);
        }
    }
}
