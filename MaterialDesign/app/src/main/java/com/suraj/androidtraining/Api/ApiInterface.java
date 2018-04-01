package com.suraj.androidtraining.Api;

import com.suraj.androidtraining.Model.Result;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hp on 3/18/2018.
 */

public interface ApiInterface {

    @GET("api/users/2")
    Call<Result> getDataFromServer();

}
