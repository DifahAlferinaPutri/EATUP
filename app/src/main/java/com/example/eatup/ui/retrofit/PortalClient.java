package com.example.eatup.ui.retrofit;

import com.example.eatup.ui.model.AuthClass;
import com.example.eatup.ui.model.RestaurantResponse;
import com.example.eatup.ui.model.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PortalClient {

    @POST("login")
    Call<AuthClass> checkLogin(@Body LoginInformation loginInformation);

    @POST("register")
    Call<SignUpResponse> register(@Body RegisterInformation registerInformation);

    @GET("restaurants")
    Call<RestaurantResponse> getRestaurantResponse();

    @POST("restaurants/get-data")
    Call<RestaurantResponse> getTypeRestaurantResponse(@Body TypeInformation typeInformation);

}
