package com.example.assignmentfour.Repo;

import com.example.assignmentfour.Model.ImageModel;
import com.example.assignmentfour.Model.ItemsCUDModel;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ItemsRepo {
    @POST("/addItem")
    Call<Void> addItems(@Body ItemsCUDModel itemsCUDModel);

    @GET("/getItem")
    Call<List<ItemsCUDModel>> getItems();

    @Multipart
    @POST("/upload")
    Call<ImageModel> uploadImage(@Part MultipartBody.Part image);
}
