package com.sossolution.serviceonway.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.JsonArray;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;
import com.sossolution.serviceonway.Activity.HistoryAllDetails_Activity;
import com.sossolution.serviceonway.Activity.Maker_Activity;
import com.sossolution.serviceonway.Activity.Payment_Activity;
import com.sossolution.serviceonway.Activity.Paymentgetway_Activity;
import com.sossolution.serviceonway.Activity.Paymentgetway_walletActivity;
import com.sossolution.serviceonway.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.content.Context.JOB_SCHEDULER_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class Wallet_Fragment extends Fragment
{

    EditText Enter_amount;
    TextView show_amount;
    ImageView show_wallet;
    Button add_amount;
    TextView tool_text;
    ImageView back;
    TextView header;
    String email;
    String number;
    String Price;
    String price;
    String user_id;
    String Type,ID,Amount;
    String User_id;
    String order_key;
    String newprice;
    TextView wallet_money;
    TextView textView;
    ProgressDialog dialog;
    String signupamount;
    String sign_amount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
       {


          SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myid", MODE_PRIVATE);
          User_id = sharedPreferences.getString("unicid", "");
          Log.d("user",User_id);

         Type="user";
         dialog= new ProgressDialog(getActivity());
         dialog.setMessage("Please wait");
         dialog.setTitle("Loading......");
         dialog.show();
         Mywallet(User_id,Type);


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wallet_, container, false);
        textView=view.findViewById(R.id.sencond_wallet);
        Enter_amount = view.findViewById(R.id.wallet_id);
        show_amount = view.findViewById(R.id.textview_price);
        show_wallet = view.findViewById(R.id.wallet);
        add_amount = view.findViewById(R.id.add_money);
        add_amount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

               // Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
                Price = Enter_amount.getText().toString();
               // int price=Integer.parseInt(Price);
                if(Price.isEmpty())
                {
                    Enter_amount.setError("Enter the amount");
                    Enter_amount.setHasTransientState(true);
                }
                else if(Integer.parseInt(Price)<0)
                {
                    Enter_amount.setError("Minimum amount 500");
                    Enter_amount.setHasTransientState(true);

                }
                else
                {

                    int value = Integer.parseInt(Price);
                    int total = value * 100;
                    newprice = String.valueOf(total);
                    Log.d("price", newprice);


                   SharedPreferences preferences= getActivity().getSharedPreferences("get_price",MODE_PRIVATE);
                   SharedPreferences.Editor editor=preferences.edit();
                   editor.putString("price",String.valueOf(value));
                   editor.apply();

                    getorder_id(Type, User_id,newprice);

                }

            }

        });
        back = view.findViewById(R.id.back);
        back.setVisibility(View.GONE);
        header = view.findViewById(R.id.header);
        header.setText("Wallet");
        return view;
    }

    private void Mywallet(String ID,String Type )
    {

        String url="https://serviceonway.com/GetUserWalletTransactionAndroidApi?id="+ID+"&type="+Type;
        Log.d("url_mywallet",url);


        JsonObjectRequest jsonArrayRequest= new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                dialog.dismiss();
                Log.d("onresponce",response.toString());

             /*   {
                    "map":
                    {
                        "user":
[{"signup_amount":"300","transaction_type":"company","contact":"7665880900","status":"1","process":"1","transaction_id":"Signup Bonus","id":"1356","date":"2020-10-19 14:29:43","name":"Nk","message":"Sign Up Amount","user_id":"995"}],

                        "wallet":0,
                            "signup_amount":300
                    }
                } */

                try {

                    JSONObject jsonObject= response.getJSONObject("map");
                    JSONArray jsonArray=jsonObject.getJSONArray("user");
                    String wallet= String.valueOf(jsonObject.getInt("wallet"));
                    String sign_upamount= String.valueOf(jsonObject.getInt("signup_amount"));
                    Log.d("value",wallet+sign_upamount);
                    textView.setText("\u20B9"+ sign_upamount);
                    show_amount.setText("\u20B9"+wallet);
                    Log.d("wallet",wallet);
                    Log.d("sign_up",sign_upamount);
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
        Volley.newRequestQueue(getActivity()).add(jsonArrayRequest);

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
                order_key = myList.get(1);
                Log.d("oreder_key",order_key);
               // Toast.makeText(getActivity(),order_key, Toast.LENGTH_SHORT).show();

                if(order_key!=null)
                {

                    Intent intent = new Intent(getActivity().getBaseContext(), Paymentgetway_walletActivity.class);

                    SharedPreferences preferences= getActivity().getSharedPreferences("key",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("order_key",order_key);
                    editor.apply();
                    getActivity().startActivity(intent);
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
        Volley.newRequestQueue(getActivity()).add(jsonArrayRequest);

    }


}