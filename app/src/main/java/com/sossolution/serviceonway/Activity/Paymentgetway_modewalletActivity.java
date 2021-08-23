package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.muddzdev.styleabletoast.StyleableToast;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;
import com.razorpay.RazorpayClient;
import com.sossolution.serviceonway.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Paymentgetway_modewalletActivity extends AppCompatActivity implements PaymentResultWithDataListener
{

    String number;
    String paymentId;
    String orderId;
    String User_id;
    String signature;
    String booking_id;
    String shop_contact;
    String user_contact;
    String amount;
    String promocode_amount;
    String mode;
    String promocode="";
    String order;
    String email;
    String my_price1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentgetway_modewallet);

        //user number........................
        SharedPreferences sharedPreferences6 = getSharedPreferences("myvalue", MODE_PRIVATE);
        number = sharedPreferences6.getString("id1", "");
        Log.d("value1",number);


        SharedPreferences sharedPreferences = getSharedPreferences("myid", MODE_PRIVATE);
        User_id = sharedPreferences.getString("unicid", "");
        Log.d("user",User_id);

        SharedPreferences preferences=getSharedPreferences("My booking id",MODE_PRIVATE);
        booking_id =preferences.getString("getbookingid","");
        Log.d("booking_id",booking_id);



        SharedPreferences preferences1= getSharedPreferences("key2",MODE_PRIVATE);
        order  =preferences1.getString("order_key2","");
        Log.d("order_key",order+"v1");


        SharedPreferences prefere= getSharedPreferences("Email",MODE_PRIVATE);
        email=prefere.getString("get_email","");
        Log.d("email1",email);


        SharedPreferences preferences3=getSharedPreferences("shop_contact",MODE_PRIVATE);
        shop_contact= preferences3.getString("shop","");
        Log.d("shop_contact",shop_contact);


        SharedPreferences preferences4= getSharedPreferences("username",MODE_PRIVATE);
        user_contact= preferences4.getString("contact","");
        Log.d("user_contact",user_contact);




          SharedPreferences preferences8= getSharedPreferences("my_price",MODE_PRIVATE);
          my_price1 =preferences8.getString("get_price","");



         Log.d("get_pricem1",my_price1);


        payment_gatway();

    }

    private void payment_gatway()
    {

        final Activity activity = this;
        final  Checkout  chekout = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Service on way");
            options.put("description", "My Booking payment");
            options.put("image", "https://serviceonway.com/UploadedFiles/ServiceProviderImages/logo3-plane1.png");
            options.put("currency", "INR");
            options.put("order_id",order.replace("\"", ""));
            double total = Double.parseDouble(my_price1);
            total = total * 100;
            options.put("amount", total);
            Log.e("amount_value", total + "amount");
            options.put("prefill.email",email);
            options.put("prefill.contact",number);
            Log.d("options", options.toString());
            chekout.open(activity, options);

        } catch (Exception e)
        {
            e.printStackTrace();
            Log.e("error1", e.toString());

        }

    }

    @Override
    public void onPaymentSuccess(String s, PaymentData data)
    {

        try {

            paymentId = data.getPaymentId();
            signature = data.getSignature();
            orderId = data.getOrderId();

            Log.d("result",paymentId+","+signature+","+orderId);

            String   ID=User_id;
            String  Type="user";
            String Payment_id=paymentId;
            String order_id=orderId;
            String sighnature_id=signature;
            promocode_amount=  my_price1;
            amount=my_price1;
            String promocode="";
            mode="wallet";


            Add_Wallet_Moneyapi(ID,Type,Payment_id,order_id,sighnature_id);

            PaymentUserBookingAndroidApi_wallet(booking_id, paymentId ,orderId,  signature, shop_contact,  user_contact,amount,promocode_amount,mode,promocode);





        }catch (Exception e)
        {
            e.printStackTrace();
            Log.d("error",e.toString());
        }

    }

    private void Add_Wallet_Moneyapi(String ID,String Type,String Payment_id,String Order_id,String Sighnature_id)
   {

        String url="https://serviceonway.com/Add_Wallet_Money_Android_Api?id="+ID+"&type="+Type+"&pid="+Payment_id+"&oid="+Order_id+"&sg="+Sighnature_id;

        Log.d("get_addwalletapi",url);

        StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {

                Log.d("responce",response);

                if(response.contains("done"))
                {
                    Toast.makeText(Paymentgetway_modewalletActivity.this, "add money", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                Log.d("responce_error",error.toString());

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
        Volley.newRequestQueue(Paymentgetway_modewalletActivity.this).add(request);

    }

    private void PaymentUserBookingAndroidApi_wallet(String Booking_id,String Transaction_id,String Order_id, String sighnature_id,String Shop_contact,String User_contact,String Amount,String Promo_amount,String Mode,String Promo_code)
    {

        String url="https://serviceonway.com/PaymentUserBookingAndroidApiByBId?bid="+Booking_id+"&tdid="+Transaction_id+"&oid="+Order_id+"&sg="+sighnature_id+"&s_contact="+Shop_contact+"&u_contact="+User_contact+"&amount="+Amount+"&promo_amount="+Promo_amount+"&mode="+Mode+"&promo_code="+Promo_code;
        Log.d("paymenturl",url.toString());
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response)
            {
                //Toast.makeText(Paymentgetway_modewalletActivity.this, "api nhi ", Toast.LENGTH_SHORT).show();
                Log.d("responce222",response.toString());
                try {

                    String message=response.getString("message");

                    if(message.equals("success"))
                    {
                        StyleableToast.makeText(Paymentgetway_modewalletActivity.this,"Payment successful",R.style.exapleToast).show();
                        Intent intent= new Intent(Paymentgetway_modewalletActivity.this,MainActivity.class);
                        startActivity(intent);

                    }else if(message.equals("invoice mail error"))
                    {
                        Log.e("error","not success");
                       // Toast.makeText(Paymentgetway_modewalletActivity.this, "payment success", Toast.LENGTH_SHORT).show();
                        StyleableToast.makeText(Paymentgetway_modewalletActivity.this,"Payment successful",R.style.exapleToast).show();
                        Intent intent= new Intent(Paymentgetway_modewalletActivity.this,MainActivity.class);
                        startActivity(intent);
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
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
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
           // Toast.makeText(this, "error"+s, Toast.LENGTH_SHORT).show();
            Log.d("error",s.toString());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}