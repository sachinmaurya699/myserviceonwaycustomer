package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
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
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.sossolution.serviceonway.Adapter.SecondAdapter;
import com.sossolution.serviceonway.Class.Storedata;
import com.sossolution.serviceonway.Interface.CustomItemClick;
import com.sossolution.serviceonway.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maker_Activity extends AppCompatActivity
{

    TextView textView;
    //Fourth_Fragment fragment;
    CustomItemClick click;
    Fragment frag1;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    SecondAdapter towdapter;
    List<Storedata> datalisttwo;
    GridLayout gridLayout;
    String Car;
    String Honda;
    String make;
    TabLayout tabLayout;
    TabItem tabItem;
    String qty;
    String Select;
    String string1;
    String ss;
    ProgressBar progressBar;
    String item;
    ImageView back;
    TextView header;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make3);

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });
        header=findViewById(R.id.header);
        header.setText("Select Maker");
        recyclerView=findViewById(R.id.recyclerviewservice);
        recyclerView.setHasFixedSize(true);
        tabLayout=findViewById(R.id.tabtwo);
        progressBar=findViewById(R.id.progressBar_service);
        progressBar.setVisibility(View.VISIBLE);

        SharedPreferences pref = getSharedPreferences("vec",Context.MODE_PRIVATE);
        String  item1=pref.getString("car","");

        SharedPreferences   mPrefs1 =getSharedPreferences("my_value", MODE_PRIVATE);
        String item2=mPrefs1.getString("value","");
        if(item2.equals("0"))
        {
            header.setText("Select Bike");

        }else if(item2.equals("1"))
        {
            header.setText("Select Car");
        }else
        {
            /////
        }
        Log.d("car","item1"+item1);
        Select=item1;
        Log.d("car",item1);

        //Home fragment.....
        /*SharedPreferences  mPrefs = getSharedPreferences("car", MODE_PRIVATE);
        String item2=mPrefs.getString("car","");
        Log.d("Home","item2"+item2);
        Select=item2;*/
      //  Log.d("select",Select);
        //getSupportActionBar().setTitle("Select"+" "+item1);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // Log.d("select",select);

             //Select=id;

        MakerDetails( Select);

             datalisttwo=new ArrayList<>();
        //one activity to data fech

        recyclerView.setLayoutManager(new GridLayoutManager(Maker_Activity.this, 2,GridLayoutManager.VERTICAL,false));

    }

    public void MakerDetails(String Select)
    {
        String url="https://serviceonway.com/Get_All_Maker_Details?veh="+Select;
              Log.d("url",url.toString());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, url,
                null, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                progressBar.setVisibility(View.GONE);

                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        Storedata storedata= new Storedata();
                        storedata.setText(jsonObject.getString("maker"));
                        storedata.setImage(jsonObject.getString("logo"));
                        datalisttwo.add(storedata);

                    } catch (JSONException e)
                       {
                        e.printStackTrace();
                        Log.e("error",e.toString());

                        }
                }
                towdapter=new SecondAdapter(getApplicationContext(),
                        (ArrayList<Storedata>) datalisttwo, new CustomItemClick()
                {
                    @Override
                    public void onItemClick1(Storedata storedata1)
                    {
                        //model value show doing

                        SharedPreferences mPrefs1 = getSharedPreferences("value1", MODE_PRIVATE);
                         String s1=mPrefs1.getString("value","");
                         Log.d("value_zero",s1);

                        if(s1.equals("0"))
                        {
                           /* Intent intent1= new Intent(Maker_Activity.this,SubsCription_Activity.class);
                            startActivity(intent1);
*/
                            Intent intent1= new Intent(Maker_Activity.this,Bike_subscription_Activity.class);
                            startActivity(intent1);

                            SharedPreferences mPrefs = getSharedPreferences("makeitem", MODE_PRIVATE);
                            SharedPreferences.Editor editor = mPrefs.edit();
                            editor.putString("bike", storedata1.getText());
                            editor.apply();

                           // Toast.makeText(Maker_Activity.this, "zero value", Toast.LENGTH_SHORT).show();

                        }
                        else if(s1.equals("2"))
                        {
                            SharedPreferences mPrefs = getSharedPreferences("makeitem", MODE_PRIVATE);
                            SharedPreferences.Editor editor = mPrefs.edit();
                            editor.putString("bike", storedata1.getText());
                            editor.apply();
                            // Toast.makeText(Maker_Activity.this, "one value", Toast.LENGTH_SHORT).show();

                            Intent intent= new Intent(Maker_Activity.this,Model_Activity.class);
                            startActivity(intent);


                        }
                        else if(s1.equals("1"))
                        {

                                SharedPreferences mPrefs = getSharedPreferences("makeitem", MODE_PRIVATE);
                                SharedPreferences.Editor editor = mPrefs.edit();
                                editor.putString("bike", storedata1.getText());
                                editor.apply();
                           // Toast.makeText(Maker_Activity.this, "one value", Toast.LENGTH_SHORT).show();

                                Intent intent= new Intent(Maker_Activity.this,Model_Activity.class);
                                startActivity(intent);


                       /* SharedPreferences preferences=getSharedPreferences("",MODE_PRIVATE);
                        SharedPreferences.Editor editor1=preferences.edit();
                        editor1.putString("get_value",s2);
                        editor1.apply();*/


                        }
                        //add new code  esle.......
                        else if(s1.equals("5"))
                        {
                           // Toast.makeText(Maker_Activity.this, "5", Toast.LENGTH_SHORT).show();
                            Intent intent1= new Intent(Maker_Activity.this,Model_Activity.class);
                            startActivity(intent1);

                            SharedPreferences mPrefs = getSharedPreferences("makeitem", MODE_PRIVATE);
                            SharedPreferences.Editor editor = mPrefs.edit();
                            editor.putString("bike", storedata1.getText());
                            editor.apply();




                        }
                        else if(s1.equals("6"))
                        {

                           // Toast.makeText(Maker_Activity.this, "6", Toast.LENGTH_SHORT).show();
                            Intent intent1= new Intent(Maker_Activity.this,Bike_subscription_Activity.class);
                            startActivity(intent1);

                            SharedPreferences mPrefs = getSharedPreferences("makeitem", MODE_PRIVATE);
                            SharedPreferences.Editor editor = mPrefs.edit();
                            editor.putString("bike", storedata1.getText());
                            editor.apply();
                        }


                        else if(s1.equals("7"))
                        {

                          //  Toast.makeText(Maker_Activity.this, "7", Toast.LENGTH_SHORT).show();
                            Intent intent1= new Intent(Maker_Activity.this,Model_Activity.class);
                            startActivity(intent1);

                            SharedPreferences mPrefs = getSharedPreferences("makeitem", MODE_PRIVATE);
                            SharedPreferences.Editor editor = mPrefs.edit();
                            editor.putString("bike", storedata1.getText());
                            editor.apply();
                        }
                    }
                });
                recyclerView.setAdapter(towdapter);


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

                hashMap.put ("Content-Type", "application/json; charset=utf-8");
               // Toast.makeText(Maker_Activity.this,hashMap.toString(), Toast.LENGTH_SHORT).show();
                Log.d("maker",hashMap.toString());
                return hashMap;

            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(Maker_Activity.this).add(jsonArrayRequest);


    }

}
