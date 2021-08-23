package com.sossolution.serviceonway.Class;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sossolution.serviceonway.Interface.Shotingdata;
import com.sossolution.serviceonway.R;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import static com.sossolution.serviceonway.Activity.MainActivity.TAG;
import static com.sossolution.serviceonway.Constant.name;
import static java.lang.Float.valueOf;

public class NewShopAdapter extends RecyclerView.Adapter<NewShopAdapter.Shopviewholder>
{

      private Context context;
      private ArrayList<Shop_item> shoplist;
      private Shotingdata shotingdata;
     String s1;
     String s5="";
     String service="";
     String price="";
     private Object JSONArray;
     private String reating;
     String img1;
     Shop_item shop_item;
     String img2;

     //new data add.............
     private final int VIEW_TYPE_ITEM = 0;
     private final int VIEW_TYPE_LOADING = 1;
     private boolean isLoadingAdded = false;



    public NewShopAdapter(Context context,ArrayList<Shop_item> shoplist,Shotingdata shotingdata)
    {
        this.context = context;
        this.shoplist= shoplist;
        this.shotingdata=shotingdata;

    }


    @Override
    public Shopviewholder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //Shopviewholder viewHolder = null;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.shodesign,parent,false);
        return new Shopviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Shopviewholder holder, int position)
    {
        Shop_item shop_item = shoplist.get(position);

                // Shopviewholder shopviewholder = holder;
                Log.d("class", shop_item.toString());
                String name = shop_item.getName();
                Log.d("name_value", name.toString());
                String provide = shop_item.getProvideid();
                String button1 = shop_item.getButton();
                String s1 = shop_item.getInclude_item();
                Log.d("new_position", position + "");
                //get service value..............
                try {

                    JSONArray arr = new JSONArray(s1);
                    JSONArray arr2 = arr.getJSONArray(1);
                    for (int i = 0; i < arr2.length(); i++)
                    {
                        JSONObject object = arr2.getJSONObject(i);
                        service = object.getString("service");
                        Log.d("service11", service.toString());
                        price = object.getString("price");
                        Log.d("price11", price.toString());

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Log.d("include",include_item.toString());
                String distence = shop_item.getDistance();
                String makerimage = shop_item.getMarker_image();
                reating = shop_item.getReating();
                Log.d("name", name);
                //String price1=shop_item.getPrice();
                holder.textname.setText(name);
                holder.ratingBar1.setNumStars((int) Float.parseFloat(reating));
                holder.textinclude.setText(service);
                holder.textdistance.setText(distence + "Km");
                holder.textprice.setText("Price:\u20B9" + price);
                holder.button.setText(button1);
                //  holder.setIsRecyclable(false);
                //more image show in app
                String image = shop_item.getImage();

                if (image.contains("none"))
                {
                    //Toast.makeText(context, "none", Toast.LENGTH_SHORT).show();
                    Picasso.get()
                            .load("https://serviceonway.com/UploadedFiles/ServiceProviderImages/logo3-plane1.png")
                            .fit()
                            .into(holder.imageView);
                } else {
                    // Toast.makeText(context, "hai", Toast.LENGTH_SHORT).show();

                    Log.d("image_item", image);

                    String[] img = image.split(",");

                    String img1 = "";
                    for (int i = 0; i < img.length; i++) {
                        img1 = img[i];

                    }
                    Log.d("image11", "" + img.length);

                    Picasso.get()
                            .load("https://serviceonway.com/UploadedFiles/ServiceProviderImages/" + img1)
                            .fit()
                            .into(holder.imageView);
                }
                holder.bind(shoplist.get(position), shotingdata);


            /*case LOADING:

                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
                break;*/


    }
    @Override
    public int getItemCount()
    {
        //return shoplist == null ? 0 : shoplist.size();
        return shoplist.size();

    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }

    public class Shopviewholder  extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textname;
        RatingBar ratingBar1;
        TextView textinclude;
        TextView wash;
        TextView textdistance;
        TextView textprice;
        Button button;
        ImageView imagemarker;

        public Shopviewholder(View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView13);
            textname=itemView.findViewById(R.id.textView10);
            ratingBar1=itemView.findViewById(R.id.ratingBar2);
            textinclude=itemView.findViewById(R.id.text_includevalue);
            textprice=itemView.findViewById(R.id.textView_price);
            //ratingBar=itemView.findViewById(R.id.ratingBar2);
            button=itemView.findViewById(R.id.booknow);
            imagemarker=itemView.findViewById(R.id.imagemarker);
            textdistance=itemView.findViewById(R.id.textdistence);
        }

        public void bind(Shop_item shop_item, Shotingdata shotingdata)
        {

            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    shotingdata.item(shop_item);
                   // final int position = getAdapterPosition();
                   // Log.d("butt", "click " + position);
                    //NewShopAdapter.this.notifyItemChanged(position, "payload " + position);
                    //New.this.notifyItemChanged(position, "payload " + position);
                  //  NewShopAdapter.this.notifyItemChanged(position);


                }
            });

        }

    }
    public class LoadingViewHolder extends RecyclerView.ViewHolder
    {

        private ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            //progressBar = (ProgressBar) itemView.findViewById(R.id.loadmore_progress);

        }
    }




   /* public void notifyItemInserted(List<Shop_item> shop_items)
    {
        final  int size=shoplist.size();
        shoplist.addAll(shop_items);
        notifyItemRangeInserted(size,getItemCount());
    }*/

}
