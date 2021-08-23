package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.muddzdev.styleabletoast.StyleableToast;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;
import com.sossolution.serviceonway.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Paymentgetway_Subscription_Activity extends AppCompatActivity implements PaymentResultWithDataListener
{

    String get_price;
    String my_order_key;
    String user_id;

  /*  ......................*/
    String ID;
    String Type;
    String Payment_id;
    String order_id;
    String sighnature_id;

    String USER_id;
    String TYPE;
    String AMOUNT;
    String SUB_ID;
    String OTP;
    String type;
    String contact;
    String  get_subscription_id;

    ///details store
    String rc;
    String mode;
    String plat_no;
    String value_maker;
    String getUser_id;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentgetway__subscription_);

        Toast.makeText(this, "paymentget way", Toast.LENGTH_SHORT).show();
       /* ..............details.............*/

        SharedPreferences preferences1=getSharedPreferences("get_payment",MODE_PRIVATE);
         rc=preferences1.getString("rc_no","");
         mode=preferences1.getString("model","");
         plat_no=preferences1.getString("plat_no","");


        //price.....
        SharedPreferences preferences= getSharedPreferences("my_vehicle",MODE_PRIVATE);
        get_price=preferences.getString("get_vehicle_price","");

        Log.d("get_price",get_price);



        //subscription id
        get_subscription_id=preferences.getString("get_subscription","");
        Log.d("get_subscription",get_subscription_id);


        //maker...........
        SharedPreferences mPrefs = getSharedPreferences("makeitem", MODE_PRIVATE);
        value_maker=mPrefs.getString("bike","");
        Log.d("get_maker", value_maker);

        //user id
        SharedPreferences sharedPreferences = getSharedPreferences("myid", MODE_PRIVATE);
        getUser_id = sharedPreferences.getString("unicid", "");
        Log.d("user",getUser_id);

        SharedPreferences preferences2= getSharedPreferences("key",MODE_PRIVATE);
        my_order_key=preferences2.getString("order_key","");




        payment_gatway();
    }

    private void payment_gatway()
    {
        // Toast.makeText(this, "payment getway", Toast.LENGTH_SHORT).show();
        final Activity activity = this;
        final Checkout chekout = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Service on way");
            options.put("description", "My Booking payment");
            options.put("image", "https://serviceonway.com/UploadedFiles/ServiceProviderImages/logo3-plane1.png");
            options.put("currency", "INR");
            options.put("order_id",my_order_key.replace("\"", ""));
            double total = Double.parseDouble( get_price);
            total = total * 100;
            options.put("amount", total);
            Log.e("amount_value", total + "amount");
            options.put("prefill.email","sachinmaurya@gmail.com");
            options.put("prefill.contact","8601502970");
            Log.d("options", options.toString());
            chekout.open(activity, options);

        } catch (Exception e)
        {
            e.printStackTrace();
            Log.e("error1", e.toString());
            // Toast.makeText(this, "error"+e.toString(), Toast.LENGTH_SHORT).show();

        }




    }
    @Override
    public void onPaymentSuccess(String s, PaymentData data)
    {
        try {

            String paymentId1 = data.getPaymentId();
            String signature1 = data.getSignature();
            String orderId1 = data.getOrderId();



            String USER_ID=getUser_id;
            String MAKER=value_maker;
            String MODEL=mode;
            String SUBSCRIPTION=get_subscription_id;
            String PAYMENT_TYPE="online";
            String AMOUNT=get_price;
            String RC_NO=rc;
            String PLATE_NO=plat_no;

            My_payment_submit(USER_ID,MAKER,MODEL,SUBSCRIPTION,PAYMENT_TYPE,AMOUNT,RC_NO,PLATE_NO);






        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void My_payment_submit(String user_id,String maker,String model,String subscription,String payment_type,String amount,String rc_no,String plate_no )
    {

        String url1="https://www.serviceonway.com/serviceonway/StorePaymentsaveApi?user_id="+user_id+"&maker_name="+maker+"&model_name="+model+"&subscription_id="+subscription+"&payment_type="+payment_type+"&amount="+amount+"&rc_no="+rc_no+"&plate_no="+plate_no;
       Log.d("get_payment_save",url1);

        StringRequest request= new StringRequest(Request.Method.POST, url1, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("get_responce",response);

                 StyleableToast.makeText(Paymentgetway_Subscription_Activity.this,"subscription payment submit",R.style.exapleToast).show();

                Intent intent= new Intent(Paymentgetway_Subscription_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();


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
        Volley.newRequestQueue(Paymentgetway_Subscription_Activity.this).add(request);


    }


    private void my_detect_api(String user_id,String amount,String subid,String type)
    {

        String url="https://www.serviceonway.com/DeductSubscriptionAmountFromUserWalletWithoutOtp?uid="+user_id+"&amount="+amount+"&sub_id="+subid+"&type="+type;

        Log.d("get_url",url);

        StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("get_responce",response);
                Toast.makeText(Paymentgetway_Subscription_Activity.this, "payment detect", Toast.LENGTH_SHORT).show();








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
        Volley.newRequestQueue(Paymentgetway_Subscription_Activity.this).add(request);






    }

    private void  Add_Wallet_Moneyapi(String id, String type, String payment_id, String order_id, String sighnature_id)
    {

        String url="https://serviceonway.com/Add_Wallet_Money_Android_Api?id="+id+"&type="+type+"&pid="+payment_id+"&oid="+order_id+"&sg="+sighnature_id;

        Log.d("url",url);

        StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("responce",response);

                if(response.contains("done"))
                {



                    StyleableToast.makeText(Paymentgetway_Subscription_Activity.this,"Add amount successful",R.style.exapleToast).show();

                    USER_id=user_id;
                    AMOUNT="2";
                    SUB_ID="10";
                   String  type1="user";


                 //   my_detect_api(USER_id,AMOUNT,SUB_ID,type1);

                   /* Intent intent= new Intent(Paymentgetway_Subscription_Activity.this,MainActivity.class);
                    startActivity(intent);*/
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
        Volley.newRequestQueue(Paymentgetway_Subscription_Activity.this).add(request);




    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData)
    {

    }


}