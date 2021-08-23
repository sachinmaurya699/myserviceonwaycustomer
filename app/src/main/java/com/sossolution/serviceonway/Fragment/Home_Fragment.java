package com.sossolution.serviceonway.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sossolution.serviceonway.Activity.Home_service_details_Activity;
import com.sossolution.serviceonway.Activity.Maker_Activity;
import com.sossolution.serviceonway.Activity.Vechicle_listing_Activity;
import com.sossolution.serviceonway.Activity.Vehicle_Activity;
import com.sossolution.serviceonway.Adapter.Home_service_adapter;
import com.sossolution.serviceonway.Adapter.MY_home_service_adapter;
import com.sossolution.serviceonway.Adapter.My_bike;
import com.sossolution.serviceonway.Adapter.SliderAdapter;
import com.sossolution.serviceonway.Class.Bike_adapter;
import com.sossolution.serviceonway.Class.Car_adapter;
import com.sossolution.serviceonway.Class.Home_bike_data;
import com.sossolution.serviceonway.Class.Home_car_data;
import com.sossolution.serviceonway.Class.MY_service_home;
import com.sossolution.serviceonway.Class.My_Home_s;
import com.sossolution.serviceonway.Class.Vehicle_bike_name;
import com.sossolution.serviceonway.Class.Vehicle_car_name;
import com.sossolution.serviceonway.Interface.Car_item;
import com.sossolution.serviceonway.Interface.My_home_interface;
import com.sossolution.serviceonway.Interface.My_home_service;
import com.sossolution.serviceonway.R;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.MODE_PRIVATE;

public class Home_Fragment extends Fragment implements IOnBackPressed
{

    private  List<Home_car_data> datalist_carhome;
    private List<Home_bike_data>datalistsecond;
    private GridLayoutManager gridLayoutManager;
    private String Car;
    private String Bike;
    private Button button;
    ImageView imageView;
    GridView gridView,gridView2;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    //scrolling view pager create
    private static ViewPager mPager;
    private  CirclePageIndicator indicator1;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    ImageView imagenotification;

    private int [] urls ={R.drawable.banner,R.drawable.sliderimg,R.drawable.slider3};

    private String []careservice={"Express","Dent/Scratch","Detailing","Car Posilsh"};

    private String []bikeservice={"General Service","Flat Tyre","Brake Service", "Bike Ceramic"};


    //first method........
    //recyclerview show;
    RecyclerView recyclerView_car,recyclerView_bike;
    List<Vehicle_bike_name> vehicle_bike_nameList;
    List<Vehicle_car_name> vehicle_car_nameList;
    GridLayoutManager manager,manager2;
    Bike_adapter adapter;
    //car
    Car_adapter carAdapter;
    TextView textView_subscription;
    ImageView img;
    Button button_upload,buy_button,sell_button;

    //home_fragment......


    List<MY_service_home> home_service_list;
    RecyclerView my_home_service_recyclerview;
    LinearLayoutManager manager_service;
    Home_service_adapter home_service_adapter;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        Log.d("show","onCreateView");
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.home_fragmentdesign, container, false);
        mPager = view.findViewById(R.id.imageView111);
        buy_button=view.findViewById(R.id.buy_button);



        //My_home_service..............................
        home_service_list= new ArrayList<>();
        my_home_service_recyclerview=view.findViewById(R.id.recyclerView_my_home_service);
        my_home_service_recyclerview.setHasFixedSize(true);
        manager_service= new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true);

       // manager_service= new LinearLayoutManager(Vechicle_listing_Activity.this,LinearLayoutManager.VERTICAL,true);
        my_home_service_recyclerview.setLayoutManager(manager_service);
        Home_service();












        recyclerView_car=view.findViewById(R.id.recyclerview_car);
        recyclerView_car.setHasFixedSize(true);
        recyclerView_bike=view.findViewById(R.id.recycler_bike);
        recyclerView_bike.setHasFixedSize(true);











        sell_button=view.findViewById(R.id.seal_button);
        sell_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent= new Intent(getActivity(),Vehicle_Activity.class);
                startActivity(intent);


                Animation startAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.blinking_animation);
                sell_button.startAnimation(startAnimation);


                SharedPreferences preferences=getActivity().getSharedPreferences("value1", MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("value","7");
                editor.apply();
            }
        });

        buy_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(getActivity(), Vechicle_listing_Activity.class);
                getActivity().startActivity(intent);


                Animation startAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.blinking_animation);
                buy_button.startAnimation(startAnimation);




            }
        });
        button_upload=view.findViewById(R.id.button_upload);
        button_upload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                Intent intent= new Intent(getActivity(), Vehicle_Activity.class);
                getActivity().startActivity(intent);

                Animation startAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.blinking_animation);
                button_upload.startAnimation(startAnimation);




                SharedPreferences preferences=getActivity().getSharedPreferences("value1",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("value","0");
                editor.apply();





               /* Intent intent= new Intent(getActivity(),Vehicle_Activity.class);
                startActivity(intent);*/

                /*SharedPreferences preferences=getActivity().getSharedPreferences("Home_fragment",MODE_PRIVATE);
               /* SharedPreferences.Editor editor=preferences.edit();
                String s1="upload";
                editor.putString("upload",s1);
                editor.apply();*/

               /* SharedPreferences preferences=getActivity().getSharedPreferences("value1", MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("value","2");
                editor.apply();*/

            }
        });
        //recyclerview show
        recyclerView_car=view.findViewById(R.id.recyclerview_car);
        recyclerView_car.setHasFixedSize(true);
        recyclerView_bike=view.findViewById(R.id.recycler_bike);
        recyclerView_bike.setHasFixedSize(true);



          // img=view.findViewById(R.id.img);
         //subscription
        // textView_subscription=view.findViewById(R.id.text_subscription);
       /* textView_subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getActivity(), Vehicle_Activity.class);
                startActivity(intent);

            }
        });
*/

        manager= new GridLayoutManager(getActivity(),4,GridLayoutManager.VERTICAL,false);

        manager2= new GridLayoutManager(getActivity(),4,GridLayoutManager.VERTICAL,false);

        recyclerView_bike.setLayoutManager(manager);
        recyclerView_car.setLayoutManager(manager2);

        vehicle_bike_nameList= new ArrayList<>();
        vehicle_car_nameList= new ArrayList<>();

        /*{R.drawable.bike_png,R.drawable.bike_png,R.drawable.bike_png,R.drawable.bike_png,R.drawable.bike_png,
                R.drawable.bike_png,R.drawable.bike_png,R.drawable.bike_png
*/

        vehicle_bike_nameList= new ArrayList<>();


        vehicle_bike_nameList.add((new Vehicle_bike_name(R.drawable.bike_png,"General")));
        vehicle_bike_nameList.add((new Vehicle_bike_name(R.drawable.bike_png,"Flat Tyre")));
        vehicle_bike_nameList.add((new Vehicle_bike_name(R.drawable.bike_png,"Brake Service")));
        vehicle_bike_nameList.add((new Vehicle_bike_name(R.drawable.bike_png,"Bike Ceramic")));


         adapter= new Bike_adapter(getActivity(), vehicle_bike_nameList, new My_bike()
         {
             @Override
             public void mybike_item(Vehicle_bike_name vehicle_bike_name1)
             {

                 Intent intent= new Intent(getActivity(),Maker_Activity.class);
                 startActivity(intent);
                 String s1="bike";
                 SharedPreferences   mPrefs = getActivity().getSharedPreferences("vec", MODE_PRIVATE);
                 editor = mPrefs.edit();
                 editor.putString("car",s1);
                 editor.apply();


                 SharedPreferences   mPrefs1 = getActivity().getSharedPreferences("value1", MODE_PRIVATE);
                 editor = mPrefs1.edit();
                 editor.putString("value","1");
                 editor.apply();


                 //Toast.makeText(getActivity(), "ha ha ji", Toast.LENGTH_SHORT).show();

             }
         });
            recyclerView_bike.setAdapter(adapter);

        //car ........................

      /*  Express","Dent/Scratch","Detailing","Car Posilsh",
        "Repainting","Oil Change","Complete car","Ac Service*/





        vehicle_car_nameList.add((new Vehicle_car_name(R.drawable.package_car,"Express")));
        vehicle_car_nameList.add((new Vehicle_car_name(R.drawable.package_car,"Dent/Scratch")));
        vehicle_car_nameList.add((new Vehicle_car_name(R.drawable.package_car,"Detailing")));
        vehicle_car_nameList.add((new Vehicle_car_name(R.drawable.package_car,"Car Posilsh")));

        carAdapter=new Car_adapter(getActivity(), vehicle_car_nameList, new Car_item()
        {
            @Override
            public void my_car_item(Vehicle_car_name vehicle_car_name)
            {


                Toast.makeText(getActivity(), "car", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getActivity(),Maker_Activity.class);
                startActivity(intent);
                String s1="car";
                SharedPreferences   mPrefs = getActivity().getSharedPreferences("vec", MODE_PRIVATE);
                editor = mPrefs.edit();
                editor.putString("car",s1);
                editor.apply();

                SharedPreferences   mPrefs1 = getActivity().getSharedPreferences("value1", MODE_PRIVATE);
                editor = mPrefs1.edit();
                editor.putString("value","1");
                editor.apply();





            }
        });
        recyclerView_car.setAdapter(carAdapter);
        //horizo ntal recyclerview

        indicator1=view.findViewById(R.id.indicator);
        imageView=view.findViewById(R.id.imageservice);

      //((MainActivity)getActivity()).getSupportActionBar().hide();

        init();
        button=view.findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                /*Log.d("show","onClick");
                Intent intent = new Intent(getActivity(),Vehicle_Activity.class);
                startActivity(intent);*/
                //Intent intent = new Intent(getActivity(),hit_);


                Animation startAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.blinking_animation);
                button.startAnimation(startAnimation);
                //startAnimation.setDuration(50);




                Intent intent = new Intent(getActivity(),Vehicle_Activity.class);
                startActivity(intent);


                SharedPreferences preferences=getActivity().getSharedPreferences("value1", MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("value","1");
                editor.apply();

            }
        });
        Car="car_package";
        Bike="bike_package";
        //hitapi_car(Car);
       // hitapi_bike(Bike);
        datalist_carhome= new ArrayList<Home_car_data>();
        datalistsecond= new ArrayList<>();
        return view;
    }

    private void Home_service()
    {

        String url="http://www.serviceonway.com/fetchDataOfAppServices";

        JsonArrayRequest  jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                Log.d("get_service_respoce",response.toString());
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        MY_service_home my_service_home=new MY_service_home();
                        my_service_home.setName(jsonObject.getString("service_name"));
                        my_service_home.setDes_cription(jsonObject.getString("service_description"));
                        my_service_home.setImage(jsonObject.getString("service_image"));
                        home_service_list.add(my_service_home);

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
                home_service_adapter= new Home_service_adapter(getActivity(), home_service_list, new My_home_interface()
                {
                    @Override
                    public void item_service(MY_service_home service_home)
                    {

                        SharedPreferences preferences= getActivity().getSharedPreferences("my_home_service",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("service_name",service_home.getName());
                        editor.putString("service_desc",service_home.getDes_cription());
                        editor.apply();

                       Intent intent= new Intent(getActivity(), Home_service_details_Activity.class);
                        startActivity(intent);

                    }
                });
                my_home_service_recyclerview.setAdapter(home_service_adapter);



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
        Volley.newRequestQueue(getActivity()).add(jsonArrayRequest);



    }


    private void dummy_data()
    {




       /* {"General Service","Flat Tyre","Brake Service",
                "Bike Ceramic","Accelerator Service","Locked Service","Clutch service",
                "Engine Service"};*/

       // R.drawable.bike_png,R.drawable.bike_png,R.drawable.bike_png,R.drawable.bike_png,R.drawable.bike_png,
              //  R.drawable.bike_png,R.drawable.bike_png,R.drawable.bike_png













    }


    private void init()
    {

        Log.d("show","init");

        mPager.setAdapter(new SliderAdapter(getActivity(),urls));
        indicator1.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator1.setRadius(5* density);
        NUM_PAGES = urls.length;
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
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run()
            {
                handler.post(Update);
            }
        }, 4000, 4000);
        // Pager listener over indicator

        indicator1.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
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
        });

    }

    @Override
    public boolean onBackPressed()
    {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            //super.onBackPressed();
        }
        return false;
    }
}
