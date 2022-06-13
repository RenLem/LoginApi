package com.renatopuskaric.loginapi.services;

import com.renatopuskaric.loginapi.models.BodyPojo;
import com.renatopuskaric.loginapi.models.LogPojo;
import com.renatopuskaric.loginapi.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface RestApiService {

 public static final String ENDPOINT_URL = "https://account.zadar.mediaapp.eu/api/";

 @POST("User/AdminLogin")
 Call<LogPojo> getApiLoginData(@Body BodyPojo bodyPojo);

 @GET("User/User")
 Call<User> getApiUserData();

}
