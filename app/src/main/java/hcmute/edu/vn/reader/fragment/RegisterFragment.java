package hcmute.edu.vn.reader.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hcmute.edu.vn.reader.Goto;
import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.api.ApiService;
import hcmute.edu.vn.reader.dtos.LoginDto;
import hcmute.edu.vn.reader.model.BaseResponse;
import hcmute.edu.vn.reader.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment {

    // fragment navigator
    Goto _goto;

    // UI projection
    Button signinBtn;
    EditText usernameTxt, passwordTxt, confirmTxt;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_register, container, false);

        signinBtn = view.findViewById(R.id.siginBtn);
        usernameTxt = view.findViewById(R.id.signin_username);
        passwordTxt = view.findViewById(R.id.signin_password);
        confirmTxt = view.findViewById(R.id.signin_repeat);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
            }
        });

        return view;
    }

    // creat new account
    private void signin() {
        String password = passwordTxt.getText().toString();
        String confirm = confirmTxt.getText().toString();
        if(!password.equals(confirm)){
            Toast.makeText(getContext(), "Password not match", Toast.LENGTH_SHORT).show();
            return;
        }
        LoginDto data = new LoginDto(usernameTxt.getText().toString(), password);
        ApiService.apiService.signup(data).enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                if(response.body() == null){
                    Toast.makeText(getContext(), "Register error", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                _goto.GotoProfile();
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}