package com.sossolution.serviceonway.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sossolution.serviceonway.Activity.HistoryAllDetails_Activity;
import com.sossolution.serviceonway.Activity.History_Activity;
import com.sossolution.serviceonway.Adapter.HistoryAdapter;
import com.sossolution.serviceonway.Adapter.Wallet_historyadapter;
import com.sossolution.serviceonway.Class.Historyitem;
import com.sossolution.serviceonway.Class.Wallet_history;
import com.sossolution.serviceonway.Interface.Clickbutton;
import com.sossolution.serviceonway.Interface.Myinterfacehistary;
import com.sossolution.serviceonway.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class History_Fragment extends Fragment
{
    RecyclerView recyclerView;
    HistoryAdapter historyAdapter;
    Historyitem historyitem;
    List<Historyitem> datahistory;
    LinearLayoutManager layoutManager;
    String Id;
    ProgressBar progressBar;
    String bookingid;
    String historybuttonname="";
    String User="";
    String Provider="";
    String Payment="";
    ImageView back;
    TextView header;
    ImageView imageView;
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blank, container, false);

        back=view.findViewById(R.id.back);
        back.setVisibility(View.GONE);
        header=view.findViewById(R.id.header);
        header.setText("History Details");
        dialog=new ProgressDialog(getActivity());
        dialog.setTitle("Please wait");
        dialog.setMessage("Loading......");
        dialog.show();

        //progressbar........


        recyclerView=view.findViewById(R.id.recyclerviewhist);
        recyclerView.setHasFixedSize(true);
        //item reverse and item top show
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,true);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        datahistory= new ArrayList<>();
        //show new data in recyclerview
        Collections.reverse(datahistory);

        //otp activity return user id ...............
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("myid",MODE_PRIVATE);
        String s1 =sharedPreferences.getString("unicid","");
        Log.d("getbooking id","v1"+s1);

        Id=s1;
        UserBookingDetail(Id);
        return  view;
    }


    private void UserBookingDetail(String id)
    {

        String url="https://serviceonway.com/GetUserBookingDetailsAndroid?id="+Id;

        Log.d("url",url);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONArray>()
        {

            @Override
            public void onResponse(JSONArray response)
            {
                dialog.dismiss();
                Log.d("Myresponce",response.toString()) ;

                if (response != null)
                {
                    for (int i = 0; i < response.length(); i++)
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Historyitem historyitem = new Historyitem();
                            historyitem.setDate(jsonObject.getString("date"));
                            historyitem.setInclude(jsonObject.getString("service_include"));
                            historyitem.setAmount(jsonObject.getString("price"));
                            historyitem.setId(jsonObject.getString("booking_id"));
                            historyitem.setButtonname(jsonObject.getString("booking_id"));
                            //get booking id show .....
                            bookingid = jsonObject.getString("booking_id");
                            datahistory.add(historyitem);
                            Provider = jsonObject.getString("shop_status");
                            Payment = jsonObject.getString("payment");
                            User = jsonObject.getString("status");

                            if (User.equals("0") || Provider.equals("0")) {

                                historyitem.setButtonname("Cancle");

                            } else if (Provider.equals("1") && Payment.equals("0")) {
                                historyitem.setButtonname("Processing");


                            } else if (Provider.equals("2") && Payment.equals("0")) {

                                historyitem.setButtonname("Execute on the way");

                            } else if (Provider.equals("3") && Payment.equals("0")) {

                                historyitem.setButtonname("Payment");

                            } else if (Provider.equals("3") && Payment.equals("2")) {
                                historyitem.setButtonname("Cash Process");
                                //process.setText("Cash Process");
                            } else if (Provider.equals("3") && Payment.equals("1")) {
                                historyitem.setButtonname("Booking complete");
                                //process.setText("Booking complete");
                            } else {
                                historyitem.setButtonname("Success");
                            }

                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                            Log.e("Myerror", e.toString());
                        }

                    }

                }
                else
                {
                    imageView.setVisibility(View.VISIBLE);
                }
                historyAdapter= new HistoryAdapter(getActivity(), (ArrayList<Historyitem>) datahistory, new Clickbutton()
                {
                    @Override
                    public void senddata(Historyitem historyitem)
                    {


                        historybuttonname+="hello";
                        SharedPreferences mPrefs = getActivity().getSharedPreferences("My booking id", MODE_PRIVATE);
                        SharedPreferences.Editor editor = mPrefs.edit();
                        editor.putString("getbookingid", historyitem.getId());
                        editor.apply();
                        Intent intent= new Intent(getActivity(),HistoryAllDetails_Activity.class);
                        startActivity(intent);



                    }
                    @Override
                    public void sendid(String historyitem)
                    {
                        //not use
                    }

                });
                recyclerView.setAdapter(historyAdapter);
                historyAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                Log.e("error",error.toString());
                // Toast.makeText(History_Activity.this,error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                HashMap<String,String> hashMap = new HashMap<String, String>();
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