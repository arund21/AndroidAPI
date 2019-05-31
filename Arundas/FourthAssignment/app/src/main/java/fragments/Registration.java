package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.fourthassignment.R;
import MyAPI.MyAPI;
import model.LoginSignupResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import url.Url;


public class Registration extends Fragment implements View.OnClickListener {
    EditText etFname, etLname, etUsername, etPassword;
    Button btnRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        etFname = view.findViewById(R.id.etFname);
        etLname = view.findViewById(R.id.etLname);
        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        signUp();

    }

    private void signUp(){
        MyAPI myAPI = Url.getInstance().create(MyAPI.class);
        int userId= ' ';
        String fName = etFname.getText().toString().trim();
        String lName = etLname.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        Call<LoginSignupResponse> usersCall = myAPI.registerUser(userId,fName,lName,username,password);
        usersCall.enqueue(new Callback<LoginSignupResponse>() {
            @Override
            public void onResponse(Call<LoginSignupResponse> call, Response<LoginSignupResponse> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Registration Failed",Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    if (response.body().isSuccess()) {
                        Toast.makeText(getActivity(),"Registration Successfull. Please login",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginSignupResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"API Error",Toast.LENGTH_LONG).show();
            }
        });

    }
}

