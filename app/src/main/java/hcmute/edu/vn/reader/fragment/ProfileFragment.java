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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    User currentUser = MySingleton.getInstance(this.getContext()).getCurrentUser();

    Goto _goto;
    FloatingActionButton editButton;
    TextView username, email, fname, lname;
    ConstraintLayout btnGroup, infoGroup;
    Button toLoginBtn, toSigninBtn, logoutBtn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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