package com.example.assignmentfour.Fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.assignmentfour.Model.UserCUDModel;
import com.example.assignmentfour.R;
import com.example.assignmentfour.Repo.UserRepo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterFragment extends Fragment {
    UserRepo userRepo;

    Button btnRegister;
    TextInputEditText txtname, txtemail, txtpassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_register, container, false);

        btnRegister = view.findViewById(R.id.btnregister);
        txtname = view.findViewById(R.id.registername);
        txtemail = view.findViewById(R.id.registeremail);
        txtpassword = view.findViewById(R.id.registerpassword);

        //for instance
        createInstance();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserCUDModel userCUDModel = new UserCUDModel(
                        txtname.getText().toString(),
                        txtemail.getText().toString(),
                        txtpassword.getText().toString());

                Call<Void> call = userRepo.addUser(userCUDModel);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(view.getContext(), "done", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(view.getContext(), "not done", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        return view;
    }
    private void createInstance(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userRepo = retrofit.create(UserRepo.class);
    }


}
