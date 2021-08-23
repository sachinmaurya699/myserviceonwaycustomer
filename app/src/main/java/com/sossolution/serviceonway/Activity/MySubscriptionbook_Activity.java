package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.muddzdev.styleabletoast.StyleableToast;
import com.sossolution.serviceonway.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySubscriptionbook_Activity extends AppCompatActivity
{

    EditText editText_contact,editText_rc_no,editText_mode_name,editText_NAME_PLATE_NO;
    Button btn_pay;
    TextView textView_wallet;
    String wallet_amount;
    String get_price;
    Float my_price;
    Float my_wallet;
    String  id;
    String  newprice;
    String contact;
    TextView textView_contact;
    String value_maker;
    String   get_subscription_id;
    ImageView cross_image;
    ImageView back;
    TextView my_subscription_amount;
    String MODEL;
    String rc_no;
    String plate_no;
    int new_value;

    private CheckBox checkBox;
    private  int wallet_money;
    private  int get_price_value;
    private String get_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_subscriptionbook_);


        cross_image=findViewById(R.id.share_pic);
        back=findViewById(R.id.back);
        checkBox=findViewById(R.id.checkbox_wallet);
        editText_rc_no=findViewById(R.id.rc_no_ID);
        my_subscription_amount=findViewById(R.id.subscription_statement_id);
        editText_NAME_PLATE_NO=findViewById(R.id.name_plate_number);
        editText_mode_name=findViewById(R.id.MODEL_NAME);
        textView_contact=findViewById(R.id.edit_text_contact_dia);


         btn_pay=findViewById(R.id.pay_amount);
         textView_wallet=findViewById(R.id.wallet_text_dia);

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        cross_image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              //  Toast.makeText(MySubscriptionbook_Activity.this, "hii", Toast.LENGTH_SHORT).show();
                onBackPressed();
                finish();
            }
        });


        SharedPreferences preferences1=getSharedPreferences("my_shared",MODE_PRIVATE);
        get_contact=preferences1.getString("get_contact","");
        Log.d("contect",get_contact);
        textView_contact.setText(get_contact);



        SharedPreferences preferences= getSharedPreferences("my_vehicle",MODE_PRIVATE);
        get_price=preferences.getString("get_vehicle_price","");
         get_price_value=Integer.parseInt(get_price);
        my_subscription_amount.setText("Subscription Amount:"+get_price);
        Log.d("get_price",get_price);

        get_subscription_id=preferences.getString("get_subscription","");
        Log.d("get_subscription",get_subscription_id);


        //maker...........
        SharedPreferences mPrefs = getSharedPreferences("makeitem", MODE_PRIVATE);
        value_maker=mPrefs.getString("bike","");
        Log.d("get_maker", value_maker);

        SharedPreferences sharedPreferences = getSharedPreferences("myid", MODE_PRIVATE);
        id = sharedPreferences.getString("unicid", "");
        Log.d("user",id);


        String type="user";
        String user_id=id;



         //first time....call get wallet amount check.............
        wallet_amount(user_id,type);
        btn_pay.setText("pay now"+"\u20B9"+get_price);


        btn_pay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                 String rc=editText_rc_no.getText().toString();
                 String mode=editText_mode_name.getText().toString();
                 String plat_no=editText_NAME_PLATE_NO.getText().toString();

                 SharedPreferences preferences1=getSharedPreferences("get_payment",MODE_PRIVATE);
                 SharedPreferences.Editor editor=preferences1.edit();
                 editor.putString("rc_no",rc);
                 editor.putString("model",mode);
                 editor.putString("plat_no",plat_no);
                 editor.apply();

                if(rc.isEmpty())
                {
                    editText_rc_no.setError("Enter the rc no");
                    editText_rc_no.setFocusable(true);

                }else if(mode.isEmpty())
                {
                    editText_mode_name.setError("Enter the model name");
                    editText_mode_name.setFocusable(true);

                }else if(plat_no.isEmpty())
                {
                    editText_NAME_PLATE_NO.setError("Enter the plate no");
                    editText_NAME_PLATE_NO.setFocusable(true);

                }else
                {



                    //payment getway

                    String Type="user";
                    String User_id=id;
                    String Amount=get_price;


                    int value = Integer.parseInt(Amount);
                    int total1 = value * 100;
                    String  newprice1 = String.valueOf(total1);
                    Log.d("price", newprice1);



                    getorder_id(Type,User_id,newprice1);





/*

                    Log.d("my_wallet_value",wallet_amount);

                    my_price=Float.parseFloat(get_price);
                    my_wallet=Float.parseFloat(wallet_amount);

                    String  my_price1="2";
                    //dummy data
                    int my_pp= Integer.parseInt(my_price1);

                    //int total = (int) (my_price * 100);
                    int total = (int) (my_pp * 100);
                    newprice = String.valueOf(total);
                    Log.d("price", newprice);
*/


                /*    if(my_wallet>=my_pp)
                    {

                        Toast.makeText(MySubscriptionbook_Activity.this, "wallet in outof money", Toast.LENGTH_SHORT).show();

                        //detct vali api

                        String USER_ID=id;
                        String AMOUNT= String.valueOf(my_pp);
                        String SUB_ID="12";
                        String TYPE="user";


                        my_detect_api(USER_ID,AMOUNT,SUB_ID,TYPE);



                    }else
                    {
                        Toast.makeText(MySubscriptionbook_Activity.this, "wallet in not  avilable money", Toast.LENGTH_SHORT).show();




                        Log.d("value","2");
                    }

*/













                }







               /* ...............detct vali api call...............*/


             /*   String USER_id=user_id;
                String TYPE="user";
                String AMOUNT="2";
                String SUB_ID=get_subscription_id;
                String OTP="";
                String transaction_id="";

                my_detect_api(USER_id,TYPE,AMOUNT,SUB_ID,OTP,transaction_id);*/


               // Toast.makeText(MySubscriptionbook_Activity.this, "subscription submit", Toast.LENGTH_SHORT).show();
                //StyleableToast.makeText(MySubscriptionbook_Activity.this,"subscription submit",R.style.exapleToast).show();

               // Myapipayment_save(USER_ID,MAKER,MODEL,Subscription_id,payment_type,amount,rc_no,plate_no);
             /*   Intent intent= new Intent(MySubscriptionbook_Activity.this,MainActivity.class);
                startActivity(intent);*/

            }
        });


    }

    private void My_payment_submit(String user_id,String maker,String model,String subscription,String payment_type,String amount,String rc_no,String plate_no )
    {

        String url1="https://www.serviceonway.com/StorePaymentsaveApi?user_id="+user_id+"&maker_name="+maker+"&model_name="+model+"&subscription_id="+subscription+"&payment_type="+payment_type+"&amount="+amount+"&rc_no="+rc_no+"&plate_no="+plate_no;
         Log.d("url",url1);

        StringRequest request= new StringRequest(Request.Method.POST, url1, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("get_responce",response);
              //  Toast.makeText(MySubscriptionbook_Activity.this, "payment submit", Toast.LENGTH_SHORT).show();

             /*   Intent intent= new Intent(Paymentgetway_Subscription_Activity.this,MainActivity.class);
                startActivity(intent);*/


            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("get_error_payment",error.toString());
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
        Volley.newRequestQueue(MySubscriptionbook_Activity.this).add(request);




    }


    private void my_detect_api(String user_id,String amount,String subid,String type)
    {

        String url="https://www.serviceonway.com/DeductSubscriptionAmountFromUserWalletWithoutOtp?uid="+user_id+"&amount="+amount+"&sub_id="+subid+"&type="+type;

        StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("responce",response);

                Toast.makeText(MySubscriptionbook_Activity.this, "detact submit", Toast.LENGTH_SHORT).show();

                String USER_id=id;
                String MAKER=value_maker;
                String MODEL="";
                String SUBSCRIPTION_ID="10";
                String PAYMENT_TYPE="wallet";
                String PAYMENT_AMOUNT="2";
                String RC_NO="124";
                String PLATE_NO="up53501";



                payment_storeapi(USER_id,MAKER,MODEL,SUBSCRIPTION_ID,PAYMENT_TYPE,PAYMENT_AMOUNT,RC_NO,PLATE_NO);


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
        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setShouldCache(false);
        Volley.newRequestQueue(MySubscriptionbook_Activity.this).add(request);


    }

    private void payment_storeapi(String user_id,String maker,String model,String subscription_id,String payment_type,String amount,String rc_no,String plate_no)
    {


        String url="https://www.serviceonway.com/StorePaymentsaveApi?user_id="+user_id+"&maker_name="+maker+"&model_name="+model+"&subscription_id="+subscription_id+"&payment_type="+payment_type+"&amount="+amount+"&rc_no="+rc_no+"&plate_no="+plate_no;
        Log.d("url_value_first",url);

        StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(MySubscriptionbook_Activity.this, "payment save", Toast.LENGTH_SHORT).show();

                if(response.equals("success"))
                {
                    Toast.makeText(MySubscriptionbook_Activity.this, "payment save", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(MySubscriptionbook_Activity.this,MainActivity.class);
                    startActivity(intent);

                }else
                {
                    Toast.makeText(MySubscriptionbook_Activity.this, "not save", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap= new HashMap<>();
                return super.getParams();
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                2,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setShouldCache(false);
        Volley.newRequestQueue(MySubscriptionbook_Activity.this).add(request);

    }


    private void wallet_amount(String user_id,String type)
    {

        String url="https://serviceonway.com/GetUserWalletTransactionAndroidApi?id="+user_id+"&type="+type;
        Log.d("url_mywallet",url);


        JsonObjectRequest jsonArrayRequest= new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
               // dialog.dismiss();
                Log.d("onresponce",response.toString());

                try {

                    JSONObject jsonObject= response.getJSONObject("map");
                    JSONArray jsonArray=jsonObject.getJSONArray("user");
                    wallet_amount= String.valueOf(jsonObject.getInt("wallet"));
                    wallet_money=Integer.parseInt(wallet_amount);

                     JSONObject object1=jsonArray.getJSONObject(0);
                     String contect1=object1.getString("contact");

                     SharedPreferences preferences=getSharedPreferences("my_shared",MODE_PRIVATE);
                     SharedPreferences.Editor editor=preferences.edit();
                     editor.putString("get_contact",contect1);
                     editor.apply();


                    // if()
                    String sign_upamount= String.valueOf(jsonObject.getInt("signup_amount"));
                    Log.d("value",wallet_amount+sign_upamount);
                    //textView_wallet.setText("\u20B9"+ sign_upamount);
                    textView_wallet.setText("\u20B9"+wallet_amount);
                    Log.d("wallet_new",wallet_amount);
                    Log.d("sign_up_new",sign_upamount);
                    Log.d("value",jsonObject.toString());


                } catch (JSONException e)
                {

                    e.printStackTrace();
                    Log.e("get_error",e.toString());
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("error_new",error.toString());

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
        Volley.newRequestQueue(MySubscriptionbook_Activity.this).add(jsonArrayRequest);


    }

    private void getorder_id(String Type, String User_id, String Amount)
    {

        String url = "https://serviceonway.com/RazorpayApiKey?type="+Type+"&id="+User_id+"&amount="+Amount;
        Log.d("url_getorder_id", url.toString());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                // Toast.makeText(getActivity(), "hii", Toast.LENGTH_SHORT).show();

                Log.d("##responce", response.toString());
                String replace = response.toString().replace("[", "");
                String replace1 = replace.replace("]", "");
                List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(",")));
                Log.d("list", myList.toString());
                Log.d("list", "" + myList.size());
                String key = myList.get(0);
                Log.d("k", key);
              String  order_key = myList.get(1);
                Log.d("oreder_key",order_key);
                // Toast.makeText(getActivity(),order_key, Toast.LENGTH_SHORT).show();

                if(order_key!=null)
                {

                    Intent intent = new Intent(MySubscriptionbook_Activity.this.getBaseContext(), Paymentgetway_Subscription_Activity.class);

                    SharedPreferences preferences= MySubscriptionbook_Activity.this.getSharedPreferences("key",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("order_key",order_key);
                    editor.apply();
                    startActivity(intent);


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

                    SharedPreferences preferences=getSharedPreferences("get_contact",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("contect",contact);
                    editor.apply();

                    String email = jsonObject.getString("email");
                    Log.d("###name", name);

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
                //yhi problem hai


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
            protected Map<String, String> getParams() throws AuthFailureError {

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
        Volley.newRequestQueue(MySubscriptionbook_Activity.this).add(jsonArrayRequest);





    }



}