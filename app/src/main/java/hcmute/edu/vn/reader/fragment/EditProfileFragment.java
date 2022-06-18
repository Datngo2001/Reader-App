package hcmute.edu.vn.reader.fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import hcmute.edu.vn.reader.Goto;
import hcmute.edu.vn.reader.MySingleton;
import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.api.ApiService;
import hcmute.edu.vn.reader.data.UserDbHelper;
import hcmute.edu.vn.reader.dtos.UpdateProfileDto;
import hcmute.edu.vn.reader.fragment.ProfileFragment;
import hcmute.edu.vn.reader.model.BaseResponse;
import hcmute.edu.vn.reader.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileFragment extends Fragment {
    // current user
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    // fragment navigator
    Goto _goto;

    // UI projection
    Button saveBtn, cancelBtn;
    EditText email, fname, lname, password, repeat, newPass;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        _goto = (Goto) getActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        saveBtn = view.findViewById(R.id.saveProfile);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
        cancelBtn = view.findViewById(R.id.cancelProfile);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _goto.GotoProfile();
            }
        });
        email = (EditText) view.findViewById(R.id.editUserEmail);
        fname = (EditText) view.findViewById(R.id.editUserFname);
        lname = (EditText) view.findViewById(R.id.editUserLname);
        password = (EditText) view.findViewById(R.id.editPassword);
        repeat = (EditText) view.findViewById(R.id.editRepeat);
        newPass = (EditText) view.findViewById(R.id.editNewPassWord);
        if(user !=null){
            email.setText(user.getEmail());
            fname.setText(user.getFname());
            lname.setText(user.getLname());
        }
        return view;
    }

    // save user profile
    private void saveData(){
        String newpass = newPass.getText().toString();
        String rep = repeat.getText().toString();
        if(!newpass.equals("") && newpass.equals(rep)){
            Toast.makeText(getContext(), "Password not match", Toast.LENGTH_SHORT).show();
            return;
        }

        UpdateProfileDto data = new UpdateProfileDto();
        data.email = email.getText().toString();
        data.password = password.getText().toString();
        data.newPassword = newpass;
        data.fname = fname.getText().toString();
        data.lname = lname.getText().toString();

        String token = MySingleton.getInstance().getCurrentToken();
        ApiService.apiService.updateProfile(token, data).enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                if(response.body() == null){
                    Toast.makeText(getActivity().getApplicationContext(), "Error update profile", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = response.body().getData();
                MySingleton.getInstance(getContext()).setCurrentUserAndToken(user, token);
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                _goto.GotoProfile();
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
