package com.sossolution.serviceonway.Class;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sossolution.serviceonway.Interface.UploadImage;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retroclient
{
    private static final  String BASE_URL="https://www.serviceonway.com";
    private static Retroclient myclient;
    private Retrofit retrofit;

    public Retroclient()
    {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }
    public static  synchronized Retroclient getInstance()
    {
        if(myclient == null)
        {
            myclient= new Retroclient();
        }

        return myclient;
    }
    public UploadImage getapi()
    {
        return retrofit.create(UploadImage.class);
    }






}
