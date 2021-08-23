package com.sossolution.serviceonway.Activity;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.muddzdev.styleabletoast.StyleableToast;
import com.razorpay.Checkout;
import com.sossolution.serviceonway.Adapter.Include_ItemAdapter;
import com.sossolution.serviceonway.Class.Include_item;
import com.sossolution.serviceonway.Class.NotIncludeI_temAdapter;
import com.sossolution.serviceonway.Class.Notification_Chanal;
import com.sossolution.serviceonway.Class.Notinclude_item;
import com.sossolution.serviceonway.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class HistoryAllDetails_Activity extends AppCompatActivity
{
    String ID="";
    TextView textView_ddate,textView_dmaker,textView_dbookingid,textView_dmodel,textView_dvechical;
    TextView textView_dconnect,textView_dshopname;
    String bookingid;
    int totalPrice = 0;
    TextView textprice;
    String User="";
    String Provider="";
    String Payment="";
    String Price1="";
    String promocode_final_price="";
    Button buttonprossing;
    TextView textinclude,textincludeprice,textnotinclude;
    String connectno="";
     String EMail="";
     String Usercontact="";
     String Amount="";
     String Promocode="";
     SwipeRefreshLayout refreshLayout;
     TextView texttotal,textprice1;
     int value1=0;
    String Email;
    String Number;
    String email1;
    String bookingId;
    String shopcontect1;
    Handler handler;
    Button button_view;
    String provide_new_lat;
    String provider_new_lang;
    String newuser_latitude;
    String newuser_lagitude;
    TextView textView_notinclude;
    Timer timer;
    String Service_not_include="";
    private Timer t ;
    private TimerTask task ;
    RecyclerView recyclerView_include;
    RecyclerView recyclerView_notinclude;
    List<Include_item>include_itemslist;
    List<Notinclude_item>notinclude_itemslist2;
    Include_ItemAdapter include_itemAdapter;
    NotIncludeI_temAdapter notIncludeI_temAdapter;
    String Notincludeservice="";
    ImageView back;
    TextView header;
    String model;
    Notification_Chanal notification_chanal;
    Button online,wallet_new;
    String order_key;
    String number;
    String  User_id;
    String   newprice;
    String wallet;
    String email;
    String address;
    String name;
    String Number1;
    String signupamount;
    double  Signup_lessamount;
    double FinalPay_amount;
    String  Type1;


    String booking_id;
    String transaction_id="";
    String order_id="";
    String sighnature="";
    String shop_contact;
    String user_contact;
    String amount;
    String promocode_amount="";
    String mode="";
    String promocode="";
    String neworder_id;

    String  Type="user";

    double s1;
    double s2;
    Dialog dialog;
    TextView textmessage;
    String newamount1;
    String  amount1;
    ProgressDialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historyfulldetail);
        //recyclerview add
        back=findViewById(R.id.back);
      //  dialog1= new ProgressDialog(HistoryAllDetails_Activity.this);
      //  dialog1.setTitle("Please wait");
       // dialog1.setMessage("Loading......");
       // dialog1.show();
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });
        online=findViewById(R.id.online);
        wallet_new=findViewById(R.id.wallet_new);
        online.setVisibility(View.GONE);
        wallet_new.setVisibility(View.GONE);
        header=findViewById(R.id.header);
        recyclerView_include=findViewById(R.id.recyclerview_first);
        header.setText("Booking Details");
        recyclerView_include.setHasFixedSize(true);
        recyclerView_notinclude=findViewById(R.id.recycler_second);
        recyclerView_notinclude.setHasFixedSize(true);
        recyclerView_include.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        recyclerView_notinclude.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        include_itemslist= new ArrayList<>();
        notinclude_itemslist2=new ArrayList<>();
        button_view=findViewById(R.id.view_direction);
        textView_notinclude=findViewById(R.id.text_notinclude);
        t=new Timer();
        task = new TimerTask()
        {
            @Override    public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                       GetUserBookingDetail(ID);
                        //Toast.makeText(HistoryAllDetails_Activity.this, "call", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        t.scheduleAtFixedRate(task, 0, 5000);


        button_view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                String s1="https://maps.google.com/maps?saddr="+newuser_latitude+","+newuser_lagitude+"&daddr="+provide_new_lat+","+provider_new_lang;

                Log.d("newuser","null"+newuser_latitude+","+newuser_lagitude);

                Log.d("values1",s1);

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(s1));
                startActivity(intent);

            }
        });

        //new handlar use

        SharedPreferences preferences=getSharedPreferences("My booking id",MODE_PRIVATE);
        bookingid =preferences.getString("getbookingid","");
        textView_dbookingid=findViewById(R.id.text_showid);
        textView_dbookingid.setText(bookingid);




        textprice=findViewById(R.id.newprice3);
        Checkout.preload(getApplicationContext());


        SharedPreferences sharedPreferences6=getSharedPreferences("myvalue",MODE_PRIVATE);
        Number = sharedPreferences6.getString("id1","");
        Log.d("number",Number);

        SharedPreferences s1= getSharedPreferences("Email",MODE_PRIVATE);
        EMail=s1.getString("myemail","");

        SharedPreferences sharedPreferences= getSharedPreferences("email",MODE_PRIVATE);
        String email=sharedPreferences.getString("email","");
        Log.d("newemail",email);

        SharedPreferences prefere= getSharedPreferences("Email",MODE_PRIVATE);
        email1=prefere.getString("myemail","");
        Log.d("email1",email1);





        ID=bookingid;
        Log.d("booking",ID);
        GetUserBookingDetail(ID);

        SharedPreferences sharedPreferences1 = getSharedPreferences("myid", MODE_PRIVATE);
        User_id = sharedPreferences1.getString("unicid", "");
        Log.d("user",User_id);
        Type="user";

        textinclude=findViewById(R.id.text_includeshow);
        textincludeprice=findViewById(R.id.text_includeprice);
        textView_ddate=findViewById(R.id.text_showdate);
        textView_dmaker=findViewById(R.id.textshow_make);
        textView_dbookingid=findViewById(R.id.text_showid);
        textView_dmodel=findViewById(R.id.text_showmodel);
        textView_dvechical=findViewById(R.id.text_showvechicle);
        buttonprossing=findViewById(R.id.buttonchange);
         //referesh();
        //shop details ......
        textView_dconnect=findViewById(R.id.text_shopconnectnew);
        textView_dshopname=findViewById(R.id.text_shopnamenew);
        refreshLayout=findViewById(R.id.swiperefresh_items);

    }
    private void GetUserBookingDetail(String ID)
    {
        String url="https://serviceonway.com/GetUserBookingDetailsBySpecBIdAndroidApi?bid="+ID;
        Log.d("url",url.toString());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                //dialog1.hide();
               include_itemslist= new ArrayList<>();
                Log.d("newvalue", response.toString());
                 try
                  {
                    JSONArray jsonArray = response.getJSONArray(0);
                    Log.d("getjson", jsonArray.toString());

                    for (int i = 0; i < jsonArray.length(); i++)
                    {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String date = jsonObject.getString("date");
                        booking_id = jsonObject.getString("booking_id");
                        String vehicle = jsonObject.getString("vehicle");
                        newuser_latitude = jsonObject.getString("user_lat");
                        newuser_lagitude = jsonObject.getString("user_lng");
                        textView_ddate.setText(date);
                        textView_dvechical.setText(vehicle);


                        String sr = jsonObject.getString("service_include");
                        Log.d("sr_value",sr);
                         Include_item include= new Include_item();
                         include.setName(jsonObject.getString("service_include"));
                         include.setPrice(jsonObject.getString("service_include"));
                         include_itemslist.add(include);


                        Notinclude_item notinclude_item = new Notinclude_item();
                        notinclude_item.setService(jsonObject.getString("service_not_include"));
                        notinclude_itemslist2.add(notinclude_item);


                        Price1 = jsonObject.getString("price");
                        Amount = Price1;
                        //new value addd...................
                        SharedPreferences preferences1= getSharedPreferences("get_price",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences1.edit();
                        editor.putString("price",Amount);
                        editor.apply();

                        //user booking api show payment amount.................
                        Log.d("Amount",Price1);
                        textprice.setText("Total"+"   "+"\u20B9"+Amount);
                        Payment = jsonObject.getString("payment");
                        User = jsonObject.getString("status");
                        Provider = jsonObject.getString("shop_status");
                        connectno = jsonObject.getString("user_contact");

                        Log.d("user_contact",connectno);
                        SharedPreferences preferences= getSharedPreferences("user",MODE_PRIVATE);
                        SharedPreferences.Editor editor1=preferences.edit();
                        editor1.putString("user_contact",connectno);
                        editor1.apply();


                        user_contact=connectno;
                        Usercontact = connectno;
                        String promocode_id = jsonObject.getString("promocode_id");
                        String promocode = jsonObject.getString("promocode");
                        Promocode = promocode;


                        String promocode_amount = jsonObject.getString("promocode_amount");
                        promocode_final_price = jsonObject.getString("promocode_final_price");


                        if (User.equals("0") || Provider.equals("0"))
                        {
                            buttonprossing.setText("Cancel");
                            buttonprossing.setBackgroundColor(Color.RED);

                        } else if (Provider.equals("1") && Payment.equals("0")) {
                            buttonprossing.setText("Processing");
                            buttonprossing.setBackgroundColor(Color.GREEN);
                            // Toast.makeText(HistoryAllDetails_Activity.this, "processing", Toast.LENGTH_SHORT).show();


                        } else if (Provider.equals("2") && Payment.equals("0"))
                        {
                            buttonprossing.setText("Executive on the way");
                            //notification();
                            buttonprossing.setBackgroundColor(Color.BLUE);
                            //Toast.makeText(HistoryAllDetails_Activity.this, "execute on the way", Toast.LENGTH_SHORT).show();


                        } else if (Provider.equals("3") && Payment.equals("0"))
                        {
                            //hitapishowhistory(ID);
                            //Toast.makeText(HistoryAllDetails_Activity.this, "payment ", Toast.LENGTH_SHORT).show();
                            buttonprossing.setText("Payment");

                            buttonprossing.setBackgroundColor(Color.GREEN);

                            buttonprossing.setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View view)
                                {
                                    buttonprossing.setVisibility(View.GONE);
                                     online.setVisibility(View.VISIBLE);

                                     online.setOnClickListener(new View.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View view)
                                         {

                                             //online payment......................

                                             double price=Double.parseDouble(Amount);
                                             int value2= (int)Math.round(price);
                                             int total=value2*100;
                                             String amount=String.valueOf(total);
                                             Log.d("s1value",Amount);


                                             SharedPreferences preferences2= getSharedPreferences("get_price",MODE_PRIVATE);
                                             SharedPreferences.Editor editor2=preferences2.edit();
                                             editor2.putString("price_value",String.valueOf(value2));
                                             editor2.apply();


                                              walletcheck_blance(User_id,Type);


                                              getorder_id(Type,User_id,amount, booking_id );


                                         }
                                     });
                                     wallet_new.setVisibility(View.VISIBLE);

                                     wallet_new.setOnClickListener(new View.OnClickListener()
                                     {

                                         @Override
                                         public void onClick(View view)
                                         {
                                             buttonprossing.setVisibility(View.GONE);

                                              SharedPreferences sharedPreferences7=getSharedPreferences("myvalue",MODE_PRIVATE);
                                              Number1=sharedPreferences7.getString("contect","");

                                              SharedPreferences sharedPreferences1=getSharedPreferences("username",MODE_PRIVATE);
                                              name=sharedPreferences1.getString("id1","");

                                              SharedPreferences preferences= getSharedPreferences("Email",MODE_PRIVATE);
                                              email=preferences.getString("myemail","");

                                              SharedPreferences sharedPreferences3= getSharedPreferences("profile_Activity",MODE_PRIVATE);
                                              address=sharedPreferences3.getString("getaddress","");

                                              SharedPreferences sharedPreferences2 = getSharedPreferences("myid", MODE_PRIVATE);
                                              User_id = sharedPreferences2.getString("unicid", "");


                                              SharedPreferences preferences1=getSharedPreferences("My booking id",MODE_PRIVATE);
                                              bookingid =preferences1.getString("getbookingid","");



                                              //int value convert..............

                                             double price=Double.parseDouble(Amount);
                                             int value= (int)Math.round(price);
                                             int total=value*100;
                                             String s1=String.valueOf(total);
                                             Log.d("s1value",Amount);

                                             String type="user";


                                             //api hit check money.......
                                             chekwallet_Blance(User_id,Type);

                                         }

                                     });

                                         }
                                     });



                        } else if (Provider.equals("3") && Payment.equals("2"))
                        {

                            buttonprossing.setText("Cash Process");
                            buttonprossing.setBackgroundColor(Color.GREEN);

                        } else if (Provider.equals("3") && Payment.equals("1"))
                        {
                            // hitapishowhistory(ID);
                            buttonprossing.setText("Booking complete");
                            buttonprossing.setBackgroundColor(Color.GREEN);
                        } else
                            {
                            buttonprossing.setText("Success");

                            }

                        //data add show krna ....................


                        String[] s1 = sr.split(",");


                        String bookingdata = "";
                        String[] bookingdata1;
                        String service_name = "";
                        String service_price = "";
                        String s2 = "";
                        String myprice = "";

                        //first for loop..............
                        for (int j = 0; j < s1.length; j++)
                        {
                            bookingdata1 = s1[j].split("=");
                            Log.d("bookingdata1", bookingdata.toString());

                            service_name += bookingdata1[0] + ",";
                            service_price += bookingdata1[1] + ",";
                            Log.d("service", service_price.toString());

                        }

                        //second sinerio...........................
                        String[] s3 = service_name.split(",");
                        String[] s4 = service_price.split(",");

                        //second for loop.............................
                        for (int m = 0; m < s3.length; m++)
                        {
                            s2 += s3[m] + "\n";

                        }
                        Log.d("values2", s2.toString());

                        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                        //  textinclude.setText(s2+"\n");

                        //third for loop...............

                        for (int n = 0; n < s4.length; n++)
                        {
                            myprice += "\u20B9" + s4[n] + "\n";
                        }
                        Log.d("price", myprice.toString());
                        String maker = jsonObject.getString("maker");
                        textView_dmaker.setText(maker);
                        Log.d("makar", maker.toString());
                        model = jsonObject.getString("model");
                        Log.d("model",model);


                        if(model.startsWith("Splendor"))
                        {
                            textView_dmodel.setText(model);

                        }
                        else
                        {
                            textView_dmodel.setText(model);

                        }
                        textView_dmaker.setText(maker);

                    }


                    //not include adapter.........................
                     notIncludeI_temAdapter= new NotIncludeI_temAdapter(getApplicationContext(), (ArrayList<Notinclude_item>) notinclude_itemslist2);
                     recyclerView_notinclude.setAdapter(notIncludeI_temAdapter);


                     //include adapter......................
                     include_itemAdapter= new Include_ItemAdapter(getApplicationContext(), (ArrayList<Include_item>) include_itemslist);
                     recyclerView_include.setAdapter(include_itemAdapter);

                      notinclude_itemslist2= new ArrayList<>();
                      JSONArray jsonArray1 = response.getJSONArray(1);
                       Log.d("jsonarray", jsonArray1.toString());


                    for (int j = 0; j < jsonArray1.length(); j++)
                    {

                        // Toast.makeText(HistoryAllDetails_Activity.this,jsonArray1.toString(), Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                        Log.d("getdata", jsonObject1.toString());
                        String shop = jsonObject1.getString("shop_name");
                        provide_new_lat = jsonObject1.getString("latitude");
                        Log.d("lat", "p" + provide_new_lat);
                        provider_new_lang = jsonObject1.getString("longitude");
                        Log.d("lat", "p" + provider_new_lang);
                        textView_dshopname.setText(shop);

                        shopcontect1 = jsonObject1.getString("contact");
                        SharedPreferences preferences=getSharedPreferences("shop_contact",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("shop",shopcontect1);
                        editor.apply();

                        shop_contact=shopcontect1;
                        textView_dconnect.setText(shopcontect1);
                        Log.e("error", shopcontect1);

                    }


                } catch (JSONException e)
                {
                    e.printStackTrace();
                    Log.d("error", e.toString());
                }



            }

        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                Log.e("error",error.toString());
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
        Volley.newRequestQueue(HistoryAllDetails_Activity.this).add(jsonArrayRequest);

    }


    private void getorder_id(String Type,String User_id,String Amount,String booking_id)
    {

        String url = "https://serviceonway.com/RazorpayApiKey?type="+Type+"&id="+User_id+"&amount="+Amount+"&bid="+booking_id;

        Log.d("getorder_id", url.toString());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
               // Toast.makeText(HistoryAllDetails_Activity.this,response.toString(), Toast.LENGTH_SHORT).show();

                Log.d("##responce", response.toString());
                String replace = response.toString().replace("[", "");
                String replace1 = replace.replace("]", "");
                List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(",")));
                Log.d("list", myList.toString());
                Log.d("list", "" + myList.size());
                String key = myList.get(0);
                Log.d("k", key);
                order_key = myList.get(1);

                if(order_key!=null)
                {

                    Intent intent = new Intent(HistoryAllDetails_Activity.this, Paymentgetway_Activity.class);


                    SharedPreferences preferences = getSharedPreferences("key", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("online_key", order_key);
                    editor.apply();


                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(HistoryAllDetails_Activity.this, "not order id", Toast.LENGTH_SHORT).show();
                }

                Log.d("order_key",order_key);
                String key1 = myList.get(2);
                Log.d("key1", key1.toString());

                try {
                    JSONArray jsonarray = response.getJSONArray(2);
                    JSONObject jsonObject = jsonarray.getJSONObject(0);
                    Log.d("object", jsonObject.toString());
                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                    String contact = jsonObject.getString("contact");
                    String email = jsonObject.getString("email");
                    Log.d("###name", name);

                } catch (JSONException e)
                {
                    e.printStackTrace();
                    Log.d("error",e.toString());
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("error", error.toString());

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {

                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("Content-Type", "application/json; charset=utf-8");
                // Toast.makeText(Maker_Activity.this,hashMap.toString(), Toast.LENGTH_SHORT).show();
                Log.d("maker", hashMap.toString());
                return hashMap;

            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(HistoryAllDetails_Activity.this).add(jsonArrayRequest);


    }

    private void PaymentUserBookingAndroidApi_wallet(String Booking_id,String Shop_contact,String User_contact,String Amount,String Promo_amount,String Mode)
    {

        String url = "https://serviceonway.com/PaymentUserBookingAndroidApiByBId?bid="+Booking_id+"&tdid=&oid=&sg=&s_contact="+Shop_contact+"&u_contact="+User_contact+"&amount="+Amount+"&promo_amount="+Promo_amount+"&mode="+Mode+"&promo_code=";

        Log.d("PaymentUserBooking_url",url.toString());

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response)
            {

                 Log.d("responce222",response.toString());
                try {

                    String message=response.getString("message");
                    if(message.equals("success"))
                    {
                        StyleableToast.makeText(HistoryAllDetails_Activity.this,"payment successfull",R.style.exapleToast).show();

                    }
                    else if(message.equals("invoice mail error"))
                    {
                        StyleableToast.makeText(HistoryAllDetails_Activity.this,"payment successfull",R.style.exapleToast).show();
                        Intent intent= new Intent(HistoryAllDetails_Activity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                       // Log.d("")
                    }
                } catch (JSONException e)
                {
                    e.printStackTrace();
                    Log.d("get_error",e.toString());
                }


            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.e("responce_error",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                HashMap<String,String> hashMap= new HashMap<>();
                return hashMap;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy (
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjectRequest.setShouldCache (false);
        Volley.newRequestQueue(this).add (jsonObjectRequest);


    }

    private void walletcheck_blance(String ID,String Type)
    {

      String url="https://serviceonway.com/GetUserWalletTransactionAndroidApi?id="+ID+"&type="+Type;
         Log.d("onlineurl",url);

        JsonObjectRequest jsonArrayRequest= new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {

                Log.d("onresponce",response.toString());

                try {
                    JSONObject jsonObject= response.getJSONObject("map");
                    wallet = String.valueOf(jsonObject.getInt("wallet"));
                    double s1=Double.parseDouble(wallet);
                    double s2=Double.parseDouble(Amount);
                    Log.d("wallet",wallet);
                    Log.d("object",jsonObject.toString());
                    JSONArray jsonArray=jsonObject.getJSONArray("user");

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String id=jsonObject1.getString("id");
                        signupamount= String.valueOf(jsonObject1.getInt("signup_amount"));
                        double  signup_amount =Double.parseDouble(signupamount);

                        if(signup_amount>100)
                        {

                            Signup_lessamount=signup_amount-100;

                            Log.d("amount",String.valueOf(Signup_lessamount));

                            Log.d("mypayment",String.valueOf(FinalPay_amount));


                        }
                        else
                        {
                            Toast.makeText(HistoryAllDetails_Activity.this, "100 less then ", Toast.LENGTH_SHORT).show();
                        }

                        Log.d("error",signupamount);
                        // textView.setText("\u20B9"+signupamount);
                        String transaction_id=jsonObject1.getString("transaction_id");
                        String name=jsonObject1.getString("name");
                        String contact=jsonObject1.getString("contact");
                        Log.d("object",jsonObject1.toString());

                    }


                } catch (JSONException e)
                {
                    e.printStackTrace();
                    Log.e("wallet_error",e.toString());
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("error1",error.toString());

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
        Volley.newRequestQueue(HistoryAllDetails_Activity.this).add(jsonArrayRequest);


    }

    private void chekwallet_Blance(String ID,String Type)
    {

        String url="https://serviceonway.com/GetUserWalletTransactionAndroidApi?id="+ID+"&type="+Type;

        Log.d("getchekwallet_url",url);

        JsonObjectRequest jsonArrayRequest= new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {

                Log.d("onresponce",response.toString());

                 String newprice1=Amount;

                try {

                     JSONObject jsonObject= response.getJSONObject("map");
                     JSONArray jsonArray1=jsonObject.getJSONArray("user");
                      wallet = String.valueOf(jsonObject.getInt("wallet"));
                      s1=Double.parseDouble(wallet);
                      Log.d("wallet_value", String.valueOf(s1));
                      s2=Double.parseDouble(newprice1);
                      Log.d("price_value", String.valueOf(s2));

                     //if condition.............

                     if(s1<s2)
                     {

                         dialog = new Dialog(HistoryAllDetails_Activity.this);
                         dialog.setContentView(R.layout.customdialog);
                         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                         {
                             dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));

                         }
                         dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                         dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                         dialog.setCancelable(false);

                         Button cancel = dialog.findViewById(R.id.cancel_button);
                         Button add = dialog.findViewById(R.id.addbutton);
                         TextView message = dialog.findViewById(R.id.message);

                         double price=Double.parseDouble(Amount);
                         int value= (int)Math.round(price);
                         String price_new3=String.valueOf(value);

                         message.setText(price_new3);


                         ///show button.............
                         add.setOnClickListener(new View.OnClickListener()
                         {
                             @Override
                             public void onClick(View view)
                             {



                                 //money add...................

                                 double price=Double.parseDouble(Amount);
                                 int value= (int)Math.round(price);
                                 int total=value*100;
                                 String price_new3=String.valueOf(total);
                                 Log.d("price_1",price_new3);




                                 SharedPreferences preferences= getSharedPreferences("my_price",MODE_PRIVATE);
                                 SharedPreferences.Editor editor=preferences.edit();
                                 editor.putString("get_price", String.valueOf(value));
                                 editor.apply();

                                 String Type1="user";


                                  //get order_id.....................

                                 wallet_add(Type1,User_id,price_new3,booking_id);
                                 String Type="user";


                             }
                         });
                         cancel.setOnClickListener(new View.OnClickListener()
                         {
                             @Override
                             public void onClick(View view)
                             {

                                 dialog.dismiss();
                             }

                         });
                         dialog.show();

                     }

                     else
                         {


                             double price1=Double.parseDouble( Amount);
                             int value1= (int)Math.round(price1);
                             amount1= String.valueOf(value1);


                             Log.d("get_price",amount1);



                         String Payment_id="";
                         String Transaction_id1 ="";
                         String Order_id="";
                         String Sighnature_id11="";
                         String newpromocode_amount=amount1;
                         String new_mode="wallet";
                         String Promocode1=""

                                 ;


                             PaymentUserBookingAndroidApi_wallet(booking_id, shop_contact,  user_contact,amount1,newpromocode_amount,new_mode);


                             Log.d("wallet", wallet);
                             Log.d("object", jsonObject.toString());


                         JSONArray jsonArray = jsonObject.getJSONArray("user");

                         for (int i = 0; i < jsonArray.length(); i++)
                         {
                             JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                             String id = jsonObject1.getString("id");
                             signupamount = String.valueOf(jsonObject1.getInt("signup_amount"));

                             double signup_amount = Double.parseDouble(signupamount);

                             if (signup_amount > 100)
                             {

                                 Signup_lessamount = signup_amount - 100;

                                 Log.d("amount", String.valueOf(Signup_lessamount));

                                 mode = "Wallet";

                             }

                             Log.d("error", signupamount);
                             // textView.setText("\u20B9"+signupamount);
                             //String transaction_id = jsonObject1.getString("transaction_id");
                            // String name = jsonObject1.getString("name");
                             //String contact = jsonObject1.getString("contact");
                             //Log.d("object", jsonObject1.toString());
                         }
                     }

                } catch (JSONException e)
                {
                    e.printStackTrace();
                    Log.e("error",e.toString());
                }

            }
        }, new Response.ErrorListener()
        {
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
        Volley.newRequestQueue(HistoryAllDetails_Activity.this).add(jsonArrayRequest);

    }

    private void wallet_add(String Type, String User_id, String Amount,String Booking_id)
    {

        String url = "https://serviceonway.com/RazorpayApiKey?type="+Type+"&id="+User_id+"&amount="+Amount+"&bid="+Booking_id;
        Log.d("url1", url.toString());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {

                Log.d("##responce", response.toString());
                String replace = response.toString().replace("[", "");
                String replace1 = replace.replace("]", "");
                List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(",")));
                Log.d("list", myList.toString());
                Log.d("list", "" + myList.size());
                String key = myList.get(0);
                Log.d("k", key);
                order_key = myList.get(1);
                Log.d("order",order_key);


                if(order_key!=null)
                {

                    Intent intent= new Intent(HistoryAllDetails_Activity.this,Paymentgetway_modewalletActivity.class);
                    startActivity(intent);

                    SharedPreferences preferences= getSharedPreferences("key2",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("order_key2",order_key);
                    editor.apply();

                 }

                 SharedPreferences preferences= getSharedPreferences("order",MODE_PRIVATE);
                 neworder_id=preferences.getString("order_key","");
                 Log.d("order_id",neworder_id);
                 Log.d("order_key",order_key);
                 String key1 = myList.get(2);
                 Log.d("key1", key1.toString());

                try {
                    JSONArray jsonarray = response.getJSONArray(2);
                    JSONObject jsonObject = jsonarray.getJSONObject(0);
                    Log.d("object", jsonObject.toString());
                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                    String contact = jsonObject.getString("contact");
                    String email = jsonObject.getString("email");
                    Log.d("###name", name);

                } catch (JSONException e)
                {
                    e.printStackTrace();
                    Log.d("get_exception",e.toString());
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("error1", error.toString());

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {

                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("Content-Type", "application/json; charset=utf-8");
                // Toast.makeText(Maker_Activity.this,hashMap.toString(), Toast.LENGTH_SHORT).show();
                Log.d("maker", hashMap.toString());
                return hashMap;

            }
        };
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(HistoryAllDetails_Activity.this).add(jsonArrayRequest);

    }

}
