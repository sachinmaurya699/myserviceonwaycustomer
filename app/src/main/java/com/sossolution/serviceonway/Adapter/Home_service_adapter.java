package com.sossolution.serviceonway.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Class.MY_service_home;
import com.sossolution.serviceonway.Interface.My_home_interface;
import com.sossolution.serviceonway.Interface.My_home_service;
import com.sossolution.serviceonway.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Home_service_adapter extends RecyclerView.Adapter<Home_service_adapter.My_viewholder>
{
    private  Context context;
    private List<MY_service_home> home_service_list;
    private My_home_interface  my_home_interface;

    public Home_service_adapter(Context context, List<MY_service_home> home_service_list,My_home_interface  my_home_interface)
    {
        this.context = context;
        this.home_service_list = home_service_list;
        this.my_home_interface=my_home_interface;
    }


    @NonNull
    @Override
    public My_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_home_service,parent,false);
        return new My_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Home_service_adapter.My_viewholder holder, int position)
    {
        MY_service_home  my_home_service= home_service_list.get(position);
        String title=my_home_service.getName();
        String des=my_home_service.getDes_cription();
        String image1=my_home_service.getImage();
        String imge_new=image1.replace(","," ");
        Log.d("get_image",imge_new);

        holder.textView_title.setText(title);
      //  holder.textView_desc.setText(des);

        Log.d("image_item",image1);
        Picasso.get()
                .load("https://serviceonway.com/UploadedFiles/ServiceImage/"+imge_new)
                .fit()
                .into(holder.image_service);

        holder.bind(home_service_list.get(position),my_home_interface);


    }


    @Override
    public int getItemCount()
    {
        return home_service_list.size();
    }

    public class My_viewholder  extends RecyclerView.ViewHolder
    {

        TextView textView_title;
        TextView textView_desc;
        ImageView image_service;


        public My_viewholder(@NonNull View itemView)
        {
            super(itemView);
            textView_title=itemView.findViewById(R.id.service_title);
            textView_desc=itemView.findViewById(R.id.service_des);
            image_service=itemView.findViewById(R.id.home_img);
        }

        public void bind(MY_service_home service_home, My_home_interface my_home_interface)
        {
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    my_home_interface.item_service(service_home);
                }
            });




        }
    }
}
