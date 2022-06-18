package hcmute.edu.vn.reader.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import hcmute.edu.vn.reader.Goto;
import hcmute.edu.vn.reader.MySingleton;
import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.api.ApiService;
import hcmute.edu.vn.reader.dtos.LoginDto;
import hcmute.edu.vn.reader.model.BaseResponse;
import hcmute.edu.vn.reader.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // fragment navigator
    Goto _goto;

    // UI projection
    EditText usernameTxt, passwordTxt;
    Button loginBtn;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        _goto = (Goto) getActivity();

        usernameTxt = (EditText) view.findViewById(R.id.loginUsername);
        passwordTxt = (EditText) view.findViewById(R.id.loginPassword);
        loginBtn = (Button) view.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallLoginApi();
            }
        });

        return view;
    }

    // call login api
    private void CallLoginApi(){
        LoginDto params = new LoginDto(usernameTxt.getText().toString(), passwordTxt.getText().toString());
        ApiService.apiService.login(params)
                .enqueue(new Callback<BaseResponse<User>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                        if(response.body()==null){
                            Toast.makeText(getContext(), "username or password not match", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        User user = response.body().getData();
                        List<String> Cookielist = response.headers().values("Set-Cookie");
                        String authCookie = (Cookielist .get(0).split(";"))[0];
                        String token = authCookie.split("=")[1];
                        MySingleton.getInstance(getContext()).setCurrentUserAndToken(user, token);
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                        _goto.GotoHome();
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}