package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Class.Home_service_details;

import java.util.List;

public class My_home_details_adapter  extends RecyclerView.Adapter<My_home_details_adapter.My_viewholder>
{
    private Context context;
    private List<Home_service_details> home_service_list_item;


    public My_home_details_adapter(Context context,List<Home_service_details> home_service_list_item)
    {
        this.context=context;
        this.home_service_list_item=home_service_list_item;



    }

    @NonNull
    @Override
    public My_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull My_viewholder holder, int position)
    {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class My_viewholder extends RecyclerView.ViewHolder
    {
        public My_viewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
