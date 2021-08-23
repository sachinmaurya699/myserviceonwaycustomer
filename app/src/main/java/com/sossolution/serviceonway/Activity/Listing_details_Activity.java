package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextClock;
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
import com.sossolution.serviceonway.Adapter.SliderAdapter;
import com.sossolution.serviceonway.R;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Listing_details_Activity extends AppCompatActivity {


    ImageView back;
    TextView header;
    TextView my_vehicle_price,my_fuel,my_owener_id,my_location,my_year,my_second_title;
    TextView discription_id,textView_km,my_manual_details;
    String textView_vehicle_id;
    String data;
    String my_vehicle_id;
    ImageView image_full;
    private ImageView img;
    //show........
    //scrolling view pager create
    private static ViewPager mPager;
    private CirclePageIndicator indicator1;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    ImageView imagenotification;
    String image_value;
    int [] urls1 ={R.drawable.banner,R.drawable.slider23,R.drawable.slider3};
    TextView textView_brand_name,textview_manual,textView_full_address;
    String brand_name;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    Button button_call;
    String  vehicle_contact;
    String vehicle_value;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_new_deisgn_olx);
        permission();
        back=findViewById(R.id.back);
        progressBar=findViewById(R.id.my_progressbar);
        progressBar.setVisibility(View.VISIBLE);
        textView_brand_name=findViewById(R.id.brand_name);
        textview_manual=findViewById(R.id.manual);
        textView_full_address=findViewById(R.id.my_full_address);

        //create slider
      /*  mPager=findViewById(R.id.imageView_new_pagr);
        indicator1=findViewById(R.id.indicator1);
        init();*/



       /* img=findViewById(R.id.share_pic);
        img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = "Here is the share content body";
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "Share via"));
                my_method1();

               *//* Uri uri = Uri.fromFile(file);
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_SEND);
                intent1.setType("image/*");

                intent1.putExtra(Intent.EXTRA_SUBJECT, "");
                intent1.putExtra(Intent.EXTRA_TEXT, "");
                intent1.putExtra(Intent.EXTRA_STREAM, uri);
                try {
                    startActivity(Intent.createChooser(intent1, "Share Screenshot"));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(Listing_details_Activity.this, "No App Available", Toast.LENGTH_SHORT).show();
                }
                *//*






            }
        });

*/
        header=findViewById(R.id.header);
        my_vehicle_price=findViewById(R.id.price_new);
        image_full=findViewById(R.id.img_33);


        my_fuel=findViewById(R.id.petrol_new);
        my_owener_id=findViewById(R.id.owner_value);
        my_location=findViewById(R.id.my_new_address);
        discription_id=findViewById(R.id.Des_1);
        textView_km=findViewById(R.id.km_new);
        my_year=findViewById(R.id.my_new_date);
       // my_second_title=findViewById(R.id.second_d);



        SharedPreferences preferences= getSharedPreferences("my_id",MODE_PRIVATE);
        my_vehicle_id=preferences.getString("my_vahicle_id","");
        brand_name=preferences.getString("vahicle_brand_name","");

        SharedPreferences preferences1= getSharedPreferences("my_contact",MODE_PRIVATE);
        vehicle_contact=preferences1.getString("vahicle_contact","");

        String my_numer="+91"+vehicle_contact;
       // Toast.makeText(this,vehicle_value+"v1", Toast.LENGTH_SHORT).show();
        Log.d("vahicle_contact",  vehicle_contact);
        button_call=findViewById(R.id.btn_call);
        button_call.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String phone = vehicle_contact;
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });



        Log.d("vahicle_value",my_vehicle_id);
        textView_brand_name.setText(brand_name);
        my_api(my_vehicle_id);


        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });
        header=findViewById(R.id.header);
        header.setText("Vehicle Details");


    }

    private void my_method1()
    {


    }

    private void permission()
    {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED)
            {
                Log.d("user_permission","allow");
                //Toast.makeText(this, "all ready permission", Toast.LENGTH_SHORT).show();
                //Location Permission already granted
                // Toast.makeText(this, "location", Toast.LENGTH_SHORT).show();

            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {

        }


    }

    private void checkLocationPermission()
    {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED)
        {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE))
            {


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }


}

    private void init() {

        //Log.d("show","init");
/*
        mPager.setAdapter(new SliderAdapter1(Listing_details_Activity.this,urls1));
        indicator1.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator1.setRadius(5* density);
      //  NUM_PAGES = urls1.length;
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable()
        {
            public void run() {
                if (currentPage == NUM_PAGES)
                {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };*/

       /* Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run()
            {
                handler.post(Update);
            }
        }, 4000, 4000);*/
        // Pager listener over indicator*/

     /*  indicator1.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("show","onPageScrolled");
                currentPage = position;
            }

            @Override
            public void onPageSelected(int position)
            {
                // Toast.makeText(getActivity(), "hello ji", Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/




    }

    private void my_api(String id)
    {
        String url="https://www.serviceonway.com/fetchVehicleListingById?id="+id;

        JsonArrayRequest request= new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject= response.getJSONObject(0);
                    String price=jsonObject.getString("price");
                    image_value= jsonObject.getString("image");


                 // Log.d("image_item",image1);
                  Log.d("image_value_1", image_value);
                   Picasso.get()
                          .load("https://serviceonway.com/UploadedFiles/Listing_Images/Vehicle/"+image_value)
                        .fit()
                      .into(image_full);
                    //Log.d("image_value",image);
                    String title=jsonObject.getString("title");
                    String phone_number=jsonObject.getString("user_contact");

                    Log.d("brand_name",title);
                 //   textView_brand_name.setText(title);
                    my_vehicle_price.setText("\u20B9"+price);
                   // my_vehicle_price.setText("\u20B9"+price);
                    String fuel=jsonObject.getString("fuel");
                    String decription_value=jsonObject.getString("description");
                    String transmission=jsonObject.getString("transmission");
                   // my_manual_details.setText(transmission);
                   // my_second_title.setText("2."+title);
                    String year=jsonObject.getString("year");
                    my_year.setText(year);
                   // discription_id.setText("1."+decription_value);
                   my_fuel.setText(fuel);
                    String km=jsonObject.getString("kilometer");
                    textView_km.setText(km+"km");
                    String wonner_name=jsonObject.getString("owner");
                    my_owener_id.setText(wonner_name+"st");
                    String address=jsonObject.getString("user_address");
                 /*   my_location.setText(address);*/
                    textView_full_address.setText(address);




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("error",error.toString());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                HashMap<String,String> hashMap= new HashMap<>();
                return hashMap;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setShouldCache(false);
        Volley.newRequestQueue(Listing_details_Activity.this).add(request);



    }
}