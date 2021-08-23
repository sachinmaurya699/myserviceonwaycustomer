package com.sossolution.serviceonway.Class;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sossolution.serviceonway.Activity.Listing_details_Activity;
import com.sossolution.serviceonway.Interface.My_listing_interface;
import com.sossolution.serviceonway.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class My_listing_adapter extends RecyclerView.Adapter<My_listing_adapter.My_viewholder> {

    private Context context;
    private ArrayList<Vehicle_listing> my_vehicle_item;
    private My_listing_interface myListingInterface;

    public My_listing_adapter(Context context1,ArrayList<Vehicle_listing> my_vehicle_item1,My_listing_interface myListingInterface1)
    {
        this.context=context1;
        this.my_vehicle_item=my_vehicle_item1;
        this.myListingInterface=myListingInterface1;



    }

    @NonNull
    @Override
    public My_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_listing_desing,parent,false);
        return new My_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_listing_adapter.My_viewholder holder, int position)
    {
           Vehicle_listing listing1=my_vehicle_item.get(position);
           String maker=listing1.getMaker();
           String price=listing1.getPrice();
           String year=listing1.getYear();
           String km=listing1.getKm();
           String image1 =listing1.getImage();
           String location=listing1.getLocation();
            String create_date=listing1.getCreate_date();
             String arr[]=create_date.split("-");
             int y1= Integer.parseInt(arr[0]);
             //Log.d("y1",y1);
             int month= Integer.parseInt(arr[1]);
            // Log.d("y2",month);


           //showing month name....
          Calendar cal1 = new GregorianCalendar(y1, month, 20);

         String date = DateUtils.formatDateRange(context, cal1.getTimeInMillis(), cal1.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE);
         Log.d("get_date",date);
        holder.textView_create.setText(date);





        // date == "December 20 - 24"
       // date = DateUtils.formatDateRange(context, cal1.getTimeInMillis(), cal3.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE);
        // date == "December 20, 2013 - January 4, 2014"



          // String[] para = location.split("\\s+");

          // Log.d("loc", String.valueOf(location.length()));
           if(location.length()>=10)
           {
              // Toast.makeText(context, "size 10", Toast.LENGTH_SHORT).show();
               holder.textview_location.setText("Dehradun");
           }
           //String[] loc=location.split(",");
          // Log.d("loc",loc[1]);
           //Log.d("item_value", String.valueOf(image1.length()));

         String [] my_image=image1.split(",");
           Log.d("my_image",my_image[0]);

        Picasso.get()
                .load("https://serviceonway.com/UploadedFiles/Listing_Images/Vehicle/"+my_image[0])
                .fit()
                .into(holder.imageView);

         /*  if(image1.contains(","))
           {
               //Toast.makeText(context, "get_img", Toast.LENGTH_SHORT).show();
               String [] img=image1.split(",");
               String img5=img[1];
               Log.d("img_position",img5);

               Picasso.get()
                   .load("https://serviceonway.com/UploadedFiles/Listing_Images/Vehicle/"+img5)
                   .fit()
                   .into(holder.imageView);

           }*/
            //Log.d("image_1",image1);



         //  Log.d("image1",image1);

/*
         Picasso.get()
                .load("https://serviceonway.com/UploadedFiles/Listing_Images/Vehicle/"+image1)
                .fit()
                .into(holder.imageView);*/



             holder.textView_price.setText(price);
             holder.textView_marker.setText(maker);
             holder.textView_year_km.setText(year+"-"+km+"km");

            holder.bind(my_vehicle_item.get(position),myListingInterface);



    }

    @Override
    public int getItemCount() {
        return my_vehicle_item.size();
    }

    public class My_viewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView_marker;
        TextView textView_price;
        TextView textView_year_km;
        TextView textview_location;
        TextView textView_create;
        Button button;
        CardView cardView;

        public My_viewholder(@NonNull View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.my_id_2);
            int id1=itemView.getId();
            Log.d("id_value", String.valueOf(id1));
            textView_price=itemView.findViewById(R.id.text_price_vehicle);
            textView_marker=itemView.findViewById(R.id.text_maker_1);
            textView_year_km=itemView.findViewById(R.id.text_year_km);
            textview_location=itemView.findViewById(R.id.text_location);
            cardView=itemView.findViewById(R.id.card_id2);
            textView_create=itemView.findViewById(R.id.create_date);




           // button=itemView.findViewById(R.id.btn_id);
          /*  button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context,Listing_details_Activity.class);
                    context.startActivity(intent);

                }
            });*/

        }

        public void bind(Vehicle_listing vehicle_listing, My_listing_interface myListingInterface2)
        {

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {

                    myListingInterface2.my_listing_itm(vehicle_listing);
                }
            });


        }
    }
}
