package com.example.assignmentfour.Repo;

import com.example.assignmentfour.Model.UserCUDModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserRepo {

    @POST("/addUser")
    Call<Void> addUser(@Body UserCUDModel userCUDModel);

    @POST("/getUser")
    Call<List<UserCUDModel>> getUser(@Body UserCUDModel userCUDModel);
}
