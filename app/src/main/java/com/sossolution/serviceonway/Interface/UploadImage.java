package com.sossolution.serviceonway.Interface;

import com.sossolution.serviceonway.Class.ImagePoso;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UploadImage
{
    @Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("/insert_vehicle_listing_api")
    Call<ImagePoso> uploadimage(

            @Field("file") String encodeImage, @Query("vehicle_type") String Vehicle, @Query("maker") String maker, @Query("model") String model,
            @Query("fuel") String fuel, @Query("transmission") String transmission, @Query("number_of_owners") String number_of_owners,
            @Query("km") String km, @Query("year") String year, @Query("title") String title, @Query("description") String desc, @Query("price")
            String price
    );




}
