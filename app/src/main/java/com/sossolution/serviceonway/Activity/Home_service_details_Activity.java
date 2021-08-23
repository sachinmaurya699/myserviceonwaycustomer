package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sossolution.serviceonway.Adapter.Home_service_adapter;
import com.sossolution.serviceonway.Adapter.Home_service_details_adapter;
import com.sossolution.serviceonway.Class.Home_service_details;
import com.sossolution.serviceonway.Class.MY_service_home;
import com.sossolution.serviceonway.Interface.My_home_details_interface;
import com.sossolution.serviceonway.Interface.My_home_interface;
import com.sossolution.serviceonway.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home_service_details_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Home_service_details> home_service_list_item;
    private LinearLayoutManager manager;
    private Home_service_details_adapter home_service_details_adapter;
    private TextView text_header;
    private  TextView text_name,textView_email,textView_service_type,textView_description,textView_number;
    private Button btn_submit;
    private String Name,Email,Description,Service_Type,Number;
    private TextView textView_header;
    private ImageView imageView_back;
    private  String name;
    private  String email;
    private  String contect;
    private   String service;
    private    String desc;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_service_details_);
        progressBar=findViewById(R.id.en_progress);
        progressBar.setVisibility(View.VISIBLE);

        SharedPreferences preferences=getSharedPreferences("my_shared",MODE_PRIVATE);
        String  get_contact=preferences.getString("get_contact","");
        Log.d("contect",get_contact);


        SharedPreferences sharedPreferences=getSharedPreferences("myid",MODE_PRIVATE);
        String  ID=sharedPreferences.getString("unicid","");

        my_contact(ID);

        SharedPreferences preferences1= getSharedPreferences("my_home_service",MODE_PRIVATE);
         service=preferences1.getString("service_name","");
         desc=preferences1.getString("service_desc","");

        textView_description=findViewById(R.id.static_desc);
        textView_service_type=findViewById(R.id.static_service);

        textView_service_type.setText(service);
        textView_description.setText(desc);











        imageView_back=findViewById(R.id.back);
        imageView_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
        text_name=findViewById(R.id.static_name);
        text_header=findViewById(R.id.header);
        text_header.setText("Enquiry Form");
        textView_email=findViewById(R.id.static_email);

        textView_number=findViewById(R.id.static_number);
        btn_submit=findViewById(R.id.submit_enquire);
        btn_submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String Name=name;
                String Number=contect;
                String Email=email;
                String Service_Type=service;
                String desc1=desc;



               my_store_data_api(Name,Number,Email,Service_Type,desc1);




            }
        });



    }

    private void my_store_data_api(String name,String number,String emial,String service_type,String description )
    {

        String url="https://www.serviceonway.com/serviceonway/StoreDataOfAppServiceEnquiry?name="+name+"&phone_no="+number+"&email="+emial+"&service_type="+service_type+"&description="+description;

        StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {

                String responce=response;
                if(responce.equals("success"))
                {
                    Log.d("store_value",responce.toString());
                    Toast.makeText(Home_service_details_Activity.this, "submit Enquiry form", Toast.LENGTH_SHORT).show();

                    Intent intent= new Intent(Home_service_details_Activity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

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
        Volley.newRequestQueue(Home_service_details_Activity.this).add(request);






    }

    private void my_contact(String id)
    {

        String url="https://serviceonway.com/GetUserProfileAndroidApi?id="+id;
        Log.d("url_profile",url);

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST,
                url, null,new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                progressBar.setVisibility(View.GONE);
                try {

                    JSONObject jsonObject= response.getJSONObject(0);
                     name=jsonObject.getString("name");
                     text_name.setText(name);
                     email=jsonObject.getString("email");
                    textView_email.setText(email);
                    String address=jsonObject.getString("address");
                     contect=jsonObject.getString("contact");
                    textView_number.setText(contect);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("error",error.toString());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap= new HashMap<String, String>();
                return hashMap;
            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy (
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache (false);
        Volley.newRequestQueue(this).add (jsonArrayRequest);






    }

    private void my_api_show_details()
    {
       String url="http://www.serviceonway.com/fetchDataOfAppServiceEnquiry";


        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                Log.d("get_service_respoce",response.toString());
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        Home_service_details home_service_details= new Home_service_details();
                        home_service_details.setName(jsonObject.getString("name"));
                        home_service_details.setEmial(jsonObject.getString("email"));
                        home_service_details.setService_type(jsonObject.getString("service_type"));
                        home_service_details.setDescription(jsonObject.getString("description"));
                        home_service_list_item.add(home_service_details);

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
                home_service_details_adapter= new Home_service_details_adapter(Home_service_details_Activity.this, home_service_list_item, new My_home_details_interface() {
                    @Override
                    public void my_item(Home_service_details home_service_details)
                    {

                    }
                });

                recyclerView.setAdapter( home_service_details_adapter);



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
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(Home_service_details_Activity.this).add(jsonArrayRequest);

    }
}