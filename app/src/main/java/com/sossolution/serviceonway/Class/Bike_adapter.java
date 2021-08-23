package com.sossolution.serviceonway.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Adapter.My_bike;
import com.sossolution.serviceonway.R;

import java.util.List;

public  class Bike_adapter extends RecyclerView.Adapter<Bike_adapter.My_viewholder>
{
   private List<Vehicle_bike_name>  vehicle_bike_nameList;
   private Context context;
   private  My_bike my_bike;


    public Bike_adapter(Context context1, List<Vehicle_bike_name> vehicle_bike_nameList, My_bike my_bike)
    {
        this.context=context1;
        this. vehicle_bike_nameList= vehicle_bike_nameList;
        this.my_bike=my_bike;


    }


    @NonNull
    @Override
    public My_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view= LayoutInflater.from(context).inflate(R.layout.bike_design,parent,false);

        return new My_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_viewholder holder, int position)
    {
        Vehicle_bike_name vehicle_bike_name=  vehicle_bike_nameList.get(position);

      holder.textView.setText(vehicle_bike_name.getName());
      holder.imageView.setImageResource(vehicle_bike_name.getImageView());

      holder.bind(vehicle_bike_nameList.get(position),my_bike);

    }

    @Override
    public int getItemCount()
    {
        return  vehicle_bike_nameList.size();
    }

    public class My_viewholder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView;
        ConstraintLayout layout;


        public My_viewholder(@NonNull View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageview_v);
            textView=itemView.findViewById(R.id.text_v);
            layout=itemView.findViewById(R.id.constraint);

        }

        public void bind(Vehicle_bike_name vehicle_bike_name1, My_bike my_bike)
        {
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    my_bike.mybike_item(vehicle_bike_name1);

                }
            });


        }
    }
}
