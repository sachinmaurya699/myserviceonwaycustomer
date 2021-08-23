package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Class.My_Home_s;
import com.sossolution.serviceonway.Interface.My_home_service;
import com.sossolution.serviceonway.R;

import java.util.List;

public class MY_home_service_adapter extends RecyclerView.Adapter<MY_home_service_adapter.My_viewholder>
{

    private Context context;
    private MY_home_service_adapter my_home_service_adapter;
    private List<My_Home_s> home_services ;
    private My_home_service my_home_service;


    public MY_home_service_adapter(Context context, MY_home_service_adapter my_home_service_adapter, List<My_Home_s> my_home_new_service, My_home_service my_home_service)
    {
        this.context=context;
        this.home_services=my_home_new_service;
        this.my_home_service_adapter = my_home_service_adapter;
        this.my_home_service= my_home_service;
    }


    @NonNull
    @Override
    public My_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view= LayoutInflater.from(context).inflate(R.layout.home_service,parent,false);

        return new My_viewholder(view);
    }

    @Override
    public void onBindViewHolder(My_viewholder holder, int position)
    {

        My_Home_s vehicle_bike_name= home_services.get(position);

     /*   holder.first_text.setText(vehicle_bike_name.getName());
        holder.first_text.setText(vehicle_bike_name.getName());*/
      //  holder.second_text.setImageResource(vehicle_bike_name.getImageView());

        holder.bind(home_services.get(position), my_home_service);

    }

    @Override
    public int getItemCount()
    {
        return  home_services.size();
    }

    public class My_viewholder extends RecyclerView.ViewHolder
    {

        TextView first_text;
        TextView second_text;

        public My_viewholder(@NonNull View itemView)
        {
            super(itemView);

            first_text=itemView.findViewById(R.id.first_text);
            second_text=itemView.findViewById(R.id.secod_text_new);
        }

        public void bind(My_Home_s home_service, My_home_service my_home_service)
        {
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {

                    my_home_service.my_home_service((My_Home_s) my_home_service);

                }
            });

        }
    }
}
