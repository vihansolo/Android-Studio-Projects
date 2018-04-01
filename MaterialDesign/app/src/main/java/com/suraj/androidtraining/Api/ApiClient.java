package com.suraj.androidtraining.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hp on 3/18/2018.
 */

public class ApiClient {
    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://reqres.in/";

    private ApiClient() {
        // Empty private constructor
    }

    public static Retrofit getClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
