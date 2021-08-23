package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLanguage;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sossolution.serviceonway.Adapter.My_subscription_adapter;
import com.sossolution.serviceonway.Interface.My_subscription_bike_interfac;
import com.sossolution.serviceonway.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bike_subscription_Activity extends AppCompatActivity
{
    private RecyclerView recyclerView1;
    private   LinearLayoutManager linearLayoutManager2;
    private ArrayList<Subscription_my_item> my_subscription_item;
    private My_subscription_adapter my_subscription_adapter;
    private Subscription_my_item subscription_my_item;
    Subscription_my_item item;
    String value_prefrence;
    String value;
    ImageView back;
    TextView header;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_subscription_);
         back=findViewById(R.id.back);
         progressBar=findViewById(R.id.progress_bar);
         progressBar.setVisibility(View.VISIBLE);
         back.setOnClickListener(new View.OnClickListener()
         {
             @Override
             public void onClick(View v)
             {
                 onBackPressed();
             }
         });
         header=findViewById(R.id.header);
         header.setText("VEHICLE");
      //  Toast.makeText(this, "page hai", Toast.LENGTH_SHORT).show();

        SharedPreferences mPrefs = getSharedPreferences("makeitem", MODE_PRIVATE);
         value_prefrence=mPrefs.getString("bike","");
        Log.d("value_prefrence",value_prefrence);
        recyclerView1=findViewById(R.id.recyclerview_subscription_5);
        recyclerView1.setHasFixedSize(true);
        my_subscription_item= new ArrayList<>();
        subscription_my_item= new Subscription_my_item();
        linearLayoutManager2 = new LinearLayoutManager(Bike_subscription_Activity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(  linearLayoutManager2);
        String maker=value_prefrence;
        //Toast.makeText(this,maker+"value", Toast.LENGTH_SHORT).show();
        my_api_hit(maker);

    }

    private void my_api_hit(String Maker)
    {

        String url="https://serviceonway.com/GetSubscriptionUsingCarName?car="+Maker;

        JsonArrayRequest request= new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                progressBar.setVisibility(View.GONE);

                try {
                    Log.d("responce", String.valueOf(response.length()));

                    if(response.length()<=1)
                    {
                        JSONObject jsonObject=response.getJSONObject(0);
                        if(jsonObject.has("data"))
                        {
                          /*  AlertDialog.Builder builder = new AlertDialog.Builder(Bike_subscription_Activity.this);
                            builder.setIcon(R.drawable.ic_close_black_24dp);
                            builder.setTitle("NO SUBSCRIPTION AVAILABLE ");*/
                            //builder.setCancelable(false);
                           // subscription
                           // builder.show();
                           // Toast.makeText(Bike_subscription_Activity.this, "1 se km", Toast.LENGTH_SHORT).show();


                            Intent intent= new Intent(Bike_subscription_Activity.this,Service_not_avilable_Activity.class);
                            startActivity(intent);
                            //finish();

                        }
                        else
                        {
                            JSONObject jsonObject1=response.getJSONObject(0);
                            item= new Subscription_my_item();
                            item.setPrice(jsonObject1.getString("price"));
                            item.setText(jsonObject1.getString("service_name"));
                            item.setYear(jsonObject1.getString("period"));
                            item.setSubscription_id(jsonObject1.getString("sid"));

                            my_subscription_item.add(item);
                        }


                        //Toast.makeText(Bike_subscription_Activity.this, "not value", Toast.LENGTH_SHORT).show();
                    }else
                    {
                       // Toast.makeText(Bike_subscription_Activity.this, "1 se jada", Toast.LENGTH_SHORT).show();
                        for(int i=0;i<=response.length();i++)
                        {

                            JSONObject jsonObject=response.getJSONObject(i);
                            item= new Subscription_my_item();
                            item.setPrice(jsonObject.getString("price"));
                            item.setText(jsonObject.getString("service_name"));
                            item.setYear(jsonObject.getString("period"));
                            item.setSubscription_id(jsonObject.getString("sid"));
                            my_subscription_item.add(item);
                        }

                    }
                                     // Toast.makeText(Bike_subscription_Activity.this, " value hai", Toast.LENGTH_SHORT).show();

                      /*  for(int i=0;i<=response.length();i++)
                        {

                            JSONObject jsonObject=response.getJSONObject(i);
                            item= new Subscription_my_item();
                            item.setPrice(jsonObject.getString("price"));
                            item.setText(jsonObject.getString("service_name"));
                            item.setYear(jsonObject.getString("period"));
                            my_subscription_item.add(item);
                        }*/






                } catch (JSONException e)
                {
                    e.printStackTrace();
                }

                my_subscription_adapter= new My_subscription_adapter(Bike_subscription_Activity.this, my_subscription_item,subscription_my_item, new My_subscription_bike_interfac()
                {
                    @Override
                    public void my_item_subscription(Subscription_my_item my_item)
                    {

                        SharedPreferences preferences= getSharedPreferences("my_vehicle",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("get_vehicle_price",my_item.getPrice());
                        editor.putString("get_subscription",my_item.getSubscription_id());
                        editor.apply();



                        Intent intent= new Intent(Bike_subscription_Activity.this,MySubscriptionbook_Activity.class);
                        startActivity(intent);
                       // Toast.makeText(Bike_subscription_Activity.this, "service", Toast.LENGTH_SHORT).show();


                    }
                });
                recyclerView1.setAdapter(my_subscription_adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

               // Toast.makeText(Bike_subscription_Activity.this,error.toString(), Toast.LENGTH_SHORT).show();


            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap= new HashMap<>();
                return hashMap;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setShouldCache(false);
        Volley.newRequestQueue(Bike_subscription_Activity.this).add(request);


    }
}