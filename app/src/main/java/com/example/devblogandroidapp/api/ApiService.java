package com.example.devblogandroidapp.api;

import com.example.devblogandroidapp.api.request.RegisterRequest;
import com.example.devblogandroidapp.api.response.ApiResponse;
import com.example.devblogandroidapp.api.response.RegisterResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    ApiService apiService = retrofit.create(ApiService.class);

    Converter<ResponseBody, ApiResponse<RegisterResponse>> errorConverter =
            retrofit.responseBodyConverter(ApiResponse.class, new Annotation[0]);


    @POST("register")
    Call<ApiResponse<RegisterResponse>> register(@Body RegisterRequest registerRequest);
}
