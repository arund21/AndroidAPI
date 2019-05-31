package com.example.assignmentfour.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignmentfour.Dashboard;
import com.example.assignmentfour.Model.UserCUDModel;
import com.example.assignmentfour.R;
import com.example.assignmentfour.Repo.UserRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
Button btnlogin;
String BASE_URL="http://10.0.2.2:8080";
    UserRepo userRepo;
    EditText email,password;
    Retrofit retrofit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view= inflater.inflate(R.layout.fragment_login, container, false);

        email=view.findViewById(R.id.loginemail);
        password=view.findViewById(R.id.loginpassword);
        btnlogin=view.findViewById(R.id.btnlogin);
        createInstance();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkLogin();

            }
        });


        return view;
    }

    private void createInstance() {
         retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userRepo= retrofit.create(UserRepo.class);
    }

    private  void  checkLogin(){
        Call<List<UserCUDModel>> userlogin= userRepo.getUser(new UserCUDModel(
                "",
                email.getText().toString(),
                password.getText().toString()
        ));

        userlogin.enqueue(new Callback<List<UserCUDModel>>() {
            @Override
            public void onResponse(Call<List<UserCUDModel>> call, Response<List<UserCUDModel>> response) {
                List<UserCUDModel> userCUDModels= response.body();


                if(userCUDModels.size()>0)
                {
                    Intent intent= new Intent(getActivity(), Dashboard.class);

                    startActivity(intent);
                }

                else {
                    Toast.makeText(getActivity(), "Invalid Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<UserCUDModel>> call, Throwable t) {

            }
        });
    }

}
