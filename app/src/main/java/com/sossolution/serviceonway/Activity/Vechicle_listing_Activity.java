package com.sossolution.serviceonway.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.sossolution.serviceonway.Class.My_listing_adapter;
import com.sossolution.serviceonway.Class.Shop_item;
import com.sossolution.serviceonway.Class.Vehicle_listing;
import com.sossolution.serviceonway.Interface.My_listing_interface;
import com.sossolution.serviceonway.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Vechicle_listing_Activity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener
{

   private RecyclerView recyclerView_2;
   private ArrayList<Vehicle_listing> my_vehicle_item;
   private   My_listing_adapter myListingAdapter;
   private LinearLayoutManager manager;
   private int lenght;
   private ImageView back;
   private TextView header;
   private ProgressDialog progressDialog;
   private int number=0;

   private ImageView imageView_filter;
   private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vechicle_listing_);
        back=findViewById(R.id.back);
        progressBar=findViewById(R.id.progress_bar_list);
        progressBar.setVisibility(View.VISIBLE);
        imageView_filter=findViewById(R.id.filter);
        imageView_filter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PopupMenu popup = new PopupMenu(Vechicle_listing_Activity.this, v);
                popup.setOnMenuItemClickListener(Vechicle_listing_Activity.this);
                popup.inflate(R.menu.vehicle);
                popup.show();
            }
        });
        progressDialog= new ProgressDialog(this);
        header=findViewById(R.id.header);
        progressDialog.setMessage("Loading.....");
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        header.setText("Vehicle listing Details");
        recyclerView_2=findViewById(R.id.recyclerview_listing_item);
        recyclerView_2.setHasFixedSize(true);

        manager= new LinearLayoutManager(Vechicle_listing_Activity.this,LinearLayoutManager.VERTICAL,false);
     //   manager.setReverseLayout(true);
        recyclerView_2.setLayoutManager(manager);


        lenght=100;


        MyVehicle_lising_api(lenght);
        my_vehicle_item= new ArrayList<>();
       // Collections.reverse(  my_vehicle_item);


    }

    private void MyVehicle_lising_api(int Length)
    {

        String url="https://serviceonway.com/Get_vehicle_listing_details_length?len="+Length;

        JsonArrayRequest request= new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                progressBar.setVisibility(View.GONE);
              progressDialog.dismiss();
                Log.d("responce_item",response.toString());

                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        Vehicle_listing listing= new Vehicle_listing();
                        listing.setCreate_date(jsonObject.getString("created_date"));
                        listing.setContact(jsonObject.getString("user_contact"));
                        listing.setPrice(jsonObject.getString("price"));
                        listing.setId(jsonObject.getString("id"));
                        listing.setMaker(jsonObject.getString("maker"));
                        listing.setImage(jsonObject.getString("image"));
                        listing.setKm(jsonObject.getString("kilometer"));
                        listing.setYear(jsonObject.getString("year"));
                        listing.setLocation(jsonObject.getString("user_address"));
                        my_vehicle_item.add(listing);

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                myListingAdapter= new My_listing_adapter(Vechicle_listing_Activity.this, my_vehicle_item,new My_listing_interface()
                {
                    @Override
                    public void my_listing_itm(Vehicle_listing vehicle_listing)
                    {
                        //Toast.makeText(Vechicle_listing_Activity.this, "ha ji", Toast.LENGTH_SHORT).show();
                        SharedPreferences preferences= getSharedPreferences("my_id",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("my_vahicle_id",vehicle_listing.getId());
                        editor.putString("vahicle_brand_name",vehicle_listing.getMaker());
                        editor.apply();

                        SharedPreferences preferences1= getSharedPreferences("my_contact",MODE_PRIVATE);
                        SharedPreferences.Editor editor1=preferences1.edit();
                        editor1.putString("vahicle_contact",vehicle_listing.getContact());
                        editor1.apply();

                        Intent intent= new Intent(Vechicle_listing_Activity.this,Listing_details_Activity.class);
                        startActivity(intent);


                        //Toast.makeText(Vechicle_listing_Activity.this,id+"my_id", Toast.LENGTH_SHORT).show();

                        //Toast.makeText(Vechicle_listing_Activity.this, "list item", Toast.LENGTH_SHORT).show();
                    }
                });

                recyclerView_2.setAdapter(  myListingAdapter);
                //recyclerView_2.scrollToPosition(0);



            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("error",error.toString());

               // Toast.makeText(Vechicle_listing_Activity.this, "hii", Toast.LENGTH_SHORT).show();


            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                HashMap<String,String> hashMap=new HashMap<>();
                return hashMap;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setShouldCache(false);
        Volley.newRequestQueue(Vechicle_listing_Activity.this).add(request);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.vehicle, menu);

        return true;

    }

    @Override
    public boolean onMenuItemClick(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.my_price:
                Toast.makeText(this, "price click menu", Toast.LENGTH_SHORT).show();

                if (item.isChecked())
                {
                    item.setChecked(false);

                } else
                    {
                    item.setChecked(true);
                    Toast.makeText(this, "price menu", Toast.LENGTH_SHORT).show();
                    priceshort();
                    myListingAdapter.notifyDataSetChanged();
                   }

                return true;

            case R.id.name:
                // write your code here
                if (item.isChecked())
                {
                    item.setChecked(false);
                } else
                    {
                    item.setChecked(true);
                    date();
                    myListingAdapter.notifyDataSetChanged();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.my_price:

                Toast.makeText(this, "price click", Toast.LENGTH_SHORT).show();

                if (item.isChecked())
                {
                    item.setChecked(false);

                } else
                    {
                    item.setChecked(true);
                    Toast.makeText(this, "price", Toast.LENGTH_SHORT).show();
                    priceshort();
                    myListingAdapter.notifyDataSetChanged();
                    }

                return true;

            case R.id.name:

                // write your code here
                if (item.isChecked())
                {
                    item.setChecked(false);

                } else
                    {
                    item.setChecked(true);
                      date();
                        myListingAdapter.notifyDataSetChanged();
                   }
                return true;

            default:
                return super.onOptionsItemSelected(item);
             }

    }

    private void priceshort()
    {

        Collections.sort(my_vehicle_item, new Comparator<Vehicle_listing>()
        {
            @Override
            public int compare(Vehicle_listing a, Vehicle_listing b)
            {
                return a.getPrice().compareTo(b.getPrice());
            }
        });

    }

    private void date()
    {

        Collections.sort(my_vehicle_item, new Comparator<Vehicle_listing>()
        {
            @Override
            public int compare(Vehicle_listing a, Vehicle_listing b)
            {
                return a.getCreate_date().compareTo(b.getCreate_date());
            }
        });
        /*Collections.sort(my_vehicle_item, new Comparator<Vehicle_listing>()
        {
            @Override
            public int compare(Vehicle_listing a, vehicle_listing b)
            {
                return a.getDistance().compareTo(b.getDistance());
            }
        });*/




    }
}