package com.sossolution.serviceonway.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Interface.Car_item;
import com.sossolution.serviceonway.R;

import java.util.List;

public  class Car_adapter extends RecyclerView.Adapter<Car_adapter.My_viewholder2>
{
    private List<Vehicle_car_name>  vehicle_car_nameList;
    private Context context;
    private Car_item car_item;


    public Car_adapter(Context context1, List<Vehicle_car_name> vehicle_car_nameList, Car_item car_item)
    {
        this.context=context1;
        this.vehicle_car_nameList=vehicle_car_nameList;
        this.car_item=car_item;

    }

    @NonNull
    @Override
    public My_viewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.bike_design,parent,false);
        return new My_viewholder2(view);

    }

    @Override
    public void onBindViewHolder(@NonNull My_viewholder2 holder, int position)
    {

        Vehicle_car_name vehicle_name= vehicle_car_nameList.get(position);
        holder.textView.setText(vehicle_name.getName());
        holder.imageView.setImageResource(vehicle_name.getImageView());

        holder.bind(vehicle_name,car_item);

    }

    @Override
    public int getItemCount() {
        return vehicle_car_nameList.size();
    }

    public class My_viewholder2 extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView;
        public My_viewholder2(@NonNull View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageview_v);
            textView=itemView.findViewById(R.id.text_v);

        }

        public void bind(Vehicle_car_name vehicle_name, Car_item car_item)
        {
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    car_item.my_car_item(vehicle_name);
                }
            });




        }
    }
}
