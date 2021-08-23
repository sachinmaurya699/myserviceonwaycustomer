package com.sossolution.serviceonway.Interface;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface My_Retrofit_Api
{

    String Baseurl="https://serviceonway.com/";
    @Multipart
    @POST("insert_vehicle_listing")
    Call<String> get_update_form(  @Part("vehicle") RequestBody vehicle,
                                   @Part("maker") RequestBody maker,
                                   @Part("model") RequestBody model,
                                   @Part("fuel") RequestBody fuel,
                                   @Part("transmission") RequestBody transmission,
                                   @Part("number_of_owners") RequestBody number_of_owners,
                                   @Part("km") RequestBody km,
                                   @Part("year") RequestBody year,
                                   @Part("title") RequestBody title,
                                   @Part("des") RequestBody des,
                                   @Part("price") RequestBody price,
                                   @Part MultipartBody.Part body1);





}
