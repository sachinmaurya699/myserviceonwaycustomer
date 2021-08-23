package com.sossolution.serviceonway.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import com.razorpay.Checkout;
import com.razorpay.Order;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.sossolution.serviceonway.Fragment.Wallet_Fragment;
import com.sossolution.serviceonway.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class Paymentgetway_Activity extends AppCompatActivity implements PaymentResultWithDataListener
{

/*...........inislize all component.............*/
    String number;
    String paymentId;
    String orderId;
    String User_id;
    String signature;
    String booking_id;
    String shop_contact;
    String user_contact;
    String promocode_amount;
    String mode;
    String promocode="";
    String order_key1;
    String order;
    String Mode;
    String email;
    String getprice;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentgetway_);

        SharedPreferences sharedPreferences6 = getSharedPreferences("myvalue", MODE_PRIVATE);
        number = sharedPreferences6.getString("id1", "");
        Log.d("value1",number);

        SharedPreferences sharedPreferences = getSharedPreferences("myid", MODE_PRIVATE);
        User_id = sharedPreferences.getString("unicid", "");
        Log.d("user",User_id);


        SharedPreferences preferences=getSharedPreferences("My booking id",MODE_PRIVATE);
        booking_id =preferences.getString("getbookingid","");
        Log.d("booking_id",booking_id);


        SharedPreferences preferences1= getSharedPreferences("key",MODE_PRIVATE);
        order  =preferences1.getString("online_key","");
         Log.d("online_key",order+"v1");


        SharedPreferences prefere= getSharedPreferences("Email",MODE_PRIVATE);
        email=prefere.getString("get_email","");
        Log.d("email1",email);


        SharedPreferences preferences3=getSharedPreferences("shop_contact",MODE_PRIVATE);
        shop_contact= preferences3.getString("shop","");
        Log.d("shop_contact",shop_contact);


         SharedPreferences preferences4= getSharedPreferences("username",MODE_PRIVATE);
         user_contact= preferences4.getString("contact","");
         Log.d("user_contact",user_contact);

         mode="online";

        SharedPreferences preferences2= getSharedPreferences("get_price",MODE_PRIVATE);
        getprice=preferences2.getString("price_value","");
        Log.d("valuprice",getprice);






        //call payment getway.......
        payment_gatway();


    }

    private void payment_gatway()
    {

        final   Checkout  chekout = new Checkout();

        final Activity activity = this;

        try {
             JSONObject options = new JSONObject();
             options.put("name", "Service on way");
             options.put("description", "My Booking payment");
             options.put("image", "https://serviceonway.com/UploadedFiles/ServiceProviderImages/logo3-plane1.png");
             options.put("currency", "INR");
             options.put("order_id",order.replace("\"", ""));
             double total = Double.parseDouble(getprice);
             total = total * 100;
             options.put("amount",total);
             options.put("prefill.email",email);
             options.put("prefill.contact",number);
             Log.e("amount_value", "" + "amount");
             Log.d("options1", options.toString());
             chekout.open(activity, options);

        } catch (Exception e)
        {
            e.printStackTrace();
            Log.e("exception_error", e.toString());

        }
    }

    @Override
    public void onPaymentSuccess(String payment_id, PaymentData data)
    {

        try {

             paymentId = data.getPaymentId();
             signature = data.getSignature();
             orderId = data.getOrderId();

             Log.d("result",paymentId+","+signature+","+orderId);

            double price=Double.parseDouble( getprice);
            int value= (int)Math.round(price);
            String amount1= String.valueOf(value);

         String   ID=User_id;
         String  Type="user";
         String Payment_id=paymentId;
         String order_id=orderId;
         String sighnature_id=signature;
         promocode_amount=amount1;


             //database value store......
            PaymentUserBookingAndroidApi_wallet(booking_id,Payment_id,order_id,sighnature_id, shop_contact,  user_contact,amount1,promocode_amount,mode,promocode);


           }catch (Exception e)
        {
            e.printStackTrace();

            Log.e("error",e.toString());
        }
    }

    private void PaymentUserBookingAndroidApi_wallet(String Booking_id,String Transaction_id,String Order_id, String sighnature_id,String Shop_contact,String User_contact,String Amount,String Promo_amount,String Mode,String Promo_code)
    {

        String url="https://serviceonway.com/PaymentUserBookingAndroidApiByBId?bid="+Booking_id+"&tdid="+Transaction_id+"&oid="+Order_id+"&sg="+sighnature_id+"&s_contact="+Shop_contact+"&u_contact="+User_contact+"&amount="+Amount+"&promo_amount="+Promo_amount+"&mode="+Mode+"&promo_code="+Promo_code;
        Log.d("payment_user",url.toString());
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response)
            {
                 Log.d("get_responce",response.toString());
                try {

                    String message=response.getString("message");

                    if(message.equals("success"))
                    {

                        StyleableToast.makeText(Paymentgetway_Activity.this,"Payment successful",R.style.exapleToast).show();

                        SharedPreferences sharedPreferences6 = getSharedPreferences("myvalue", MODE_PRIVATE);
                        number = sharedPreferences6.getString("id1", "");
                        Log.d("vv",number);


                        Intent intent= new Intent(Paymentgetway_Activity.this,MainActivity.class);
                        startActivity(intent);


                    }else if(message.equals("invoice mail error"))
                    {

                       StyleableToast.makeText(Paymentgetway_Activity.this,"Payment mail successful",R.style.exapleToast).show();
                        Log.e("error","not success");
                        Intent intent= new Intent(Paymentgetway_Activity.this,MainActivity.class);
                        startActivity(intent);
                    }
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
                Log.e("error",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
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

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData)
    {

        try {
            //Toast.makeText(this, "error"+s, Toast.LENGTH_SHORT).show();
           Log.d("error",s.toString());
           }catch (Exception e)
        {
            e.printStackTrace();
            Log.e("error",e.toString());
        }


    }


}