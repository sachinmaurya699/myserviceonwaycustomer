package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Class.Home_service_details;
import com.sossolution.serviceonway.Interface.My_home_details_interface;
import com.sossolution.serviceonway.R;

import java.util.List;

public class Home_service_details_adapter extends RecyclerView.Adapter<Home_service_details_adapter.My_viewhoolder>
{


    private Context context;
    private List<Home_service_details> home_service_list_item;
    private My_home_details_interface my_home_details_interface;

    public Home_service_details_adapter(Context context, List<Home_service_details> home_service_list_item,My_home_details_interface my_home_details_interface)
    {
        this.context = context;
        this.home_service_list_item = home_service_list_item;
        this.my_home_details_interface=my_home_details_interface;
    }


    @NonNull
    @Override
    public My_viewhoolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_home_details,parent,false);
        return new My_viewhoolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_viewhoolder holder, int position)
    {
          Home_service_details home_service_details=home_service_list_item.get(position);
          String name=home_service_details.getName();
          String email=home_service_details.getEmial();
          String service_type=home_service_details.getService_type();
          String description=home_service_details.getDescription();

          holder.text_name.setText(name);
          holder.textView_service_type.setText(service_type);
          holder.textView_description.setText(description);
          holder.textView_email.setText(email);

          holder.bind(home_service_list_item.get(position),my_home_details_interface);


    }

    @Override
    public int getItemCount()
    {
        return home_service_list_item.size();
    }

    public class My_viewhoolder extends RecyclerView.ViewHolder
    {

        TextView text_name,textView_email,textView_service_type,textView_description,textView_number;

        public My_viewhoolder(@NonNull View itemView)
        {
            super(itemView);
            text_name=itemView.findViewById(R.id.dynmic_name);
            textView_email=itemView.findViewById(R.id.dynmic_email);
            textView_description=itemView.findViewById(R.id.dynemic_desc);
            textView_service_type=itemView.findViewById(R.id.dynmic_service);
            textView_number=itemView.findViewById(R.id.dynmic_number);


        }

        public void bind(Home_service_details home_service_details, My_home_details_interface my_home_details_interface)
        {

            my_home_details_interface.my_item(home_service_details);


        }
    }
}
