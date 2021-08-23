package com.sossolution.serviceonway.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sossolution.serviceonway.R;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Tezt_Activity extends AppCompatActivity
{

    private static final String TAG ="my_value" ;
    String User_id;
    String User_name;
    String User_contact;
    String User_model;
    String User_message;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tezt_);

        User_id="10";
        User_contact="8601502970";
        User_name="skkk";
        User_model="carbike";
        User_message="hii hello";

        //yapifetch();
       // new My_class().execute();
    }

    private void myapifetch()
    {

    // String url="http://serviceonway.com/StoreOfferDetails?user_id="+User_id+"&name="+ User_name+" &contact="+User_contact+"&model=maruti&message=gud";

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("user_id",User_id)
                .add ("name",User_contact )
                .add("contact",User_name)
                .add("model",User_model)
                .add("message",User_message)
                .build();
        Request request = new Request.Builder()
                .url("http://serviceonway.com/StoreOfferDetails")
                .post(requestBody)
                .build();


        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = null;
        try {
            result = response.body().string();
            Log.d("succfull message",result);
           // Toast.makeText(this,result+"v1", Toast.LENGTH_SHORT).show();
        } catch (IOException e)
        {
            e.printStackTrace();
            Log.d("value2",e.toString());
        }
        Log.i(TAG,"result" +  result);
        //JSONObject jsonObject = new JSONObject(result);






    }
    private  class My_class extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... strings)
        {
            myapifetch();
            return null;
        }
    }
}