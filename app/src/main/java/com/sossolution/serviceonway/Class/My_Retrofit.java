package com.sossolution.serviceonway.Class;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sossolution.serviceonway.Interface.My_Retrofit_Api;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class My_Retrofit
{
    private Context context;
    private static  My_Retrofit instance=null;
    private My_Retrofit_Api my_retrofit_api_interface;


    public My_Retrofit()
    {
        //logcat data show......
        HttpLoggingInterceptor loggingInterceptor= new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //okhttp
        OkHttpClient client= new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
       //Gson....
        Gson gson= new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(My_Retrofit_Api.Baseurl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        my_retrofit_api_interface= retrofit.create(My_Retrofit_Api.class);

    }
    public static synchronized My_Retrofit getInstance()
    {
        if (instance == null) {
            instance = new My_Retrofit();
        }
        return instance;
    }

    public My_Retrofit_Api getMyApi()
    {
        return my_retrofit_api_interface;
    }



}
