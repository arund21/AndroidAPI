package MyAPI;

import model.LoginSignupResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyAPI {

    //For Register
    @FormUrlEncoded
    @POST("users/signup")
    Call<LoginSignupResponse> TestregisterUser(@Field("username") String username, @Field("password") String password);
    //For Register
    @FormUrlEncoded
    @POST("users/signup")
    Call<LoginSignupResponse> registerUser(@Field("userId") int userId,@Field("fName") String fname, @Field("lName") String lname, @Field("username") String username, @Field("password") String password);


}
