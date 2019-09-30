package com.furkanzayimoglu.powerapp;

import com.google.gson.JsonObject;

import java.lang.reflect.Array;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface PowerInterface {

    String  jsonUrl = " https://api.powergroup.com.tr/";
    @Multipart
    @POST("v2/Channels/list/radios")
    Call<String> getResponse(@PartMap Map<String, RequestBody> params);
}
