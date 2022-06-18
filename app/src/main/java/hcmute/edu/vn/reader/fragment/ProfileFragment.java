package hcmute.edu.vn.reader.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import hcmute.edu.vn.reader.Goto;
import hcmute.edu.vn.reader.MySingleton;
import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.api.ApiService;
import hcmute.edu.vn.reader.data.UserDbHelper;
import hcmute.edu.vn.reader.model.BaseResponse;
import hcmute.edu.vn.reader.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    // current user
    User currentUser = MySingleton.getInstance(this.getContext()).getCurrentUser();

    // fragment navigator
    Goto _goto;

    // UI projection
    FloatingActionButton editButton;
    TextView username, email, fname, lname;
    ConstraintLayout btnGroup, infoGroup;
    Button toLoginBtn, toSigninBtn, logoutBtn;

    public ProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        editButton = view.findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditForm();
            }
        });
        toLoginBtn = view.findViewById(R.id.gotoLoginBtn);
        toLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _goto.GotoLogin();
            }
        });
        toSigninBtn = view.findViewById(R.id.gotoSigninBtn);
        toSigninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _goto.GotoSignin();
            }
        });
        logoutBtn = (Button) view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        username = (TextView) view.findViewById(R.id.profileUsername);
        email = (TextView) view.findViewById(R.id.profileEmail);
        fname = (TextView) view.findViewById(R.id.profileFname);
        lname = (TextView) view.findViewById(R.id.profileLname);
        btnGroup = (ConstraintLayout) view.findViewById(R.id.profileBtnGroup);
        infoGroup = (ConstraintLayout) view.findViewById(R.id.profileInfoGroup);

        if(currentUser == null){
            btnGroup.setVisibility(View.VISIBLE);
            infoGroup.setVisibility(View.INVISIBLE);
            return view;
        }

        btnGroup.setVisibility(View.INVISIBLE);
        infoGroup.setVisibility(View.VISIBLE);
        username.setText(currentUser.getUsername());
        email.setText(currentUser.getEmail());
        fname.setText(currentUser.getFname());
        lname.setText(currentUser.getLname());

        return view;
    }

    private void openEditForm(){
        _goto.GotoEditProfile(currentUser);
    }

    // logout user
    private void logout(){
        ApiService.apiService.logout().enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                MySingleton.getInstance(getContext()).logoutUser();
                _goto.GotoHome();
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}