package hcmute.edu.vn.reader.fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import hcmute.edu.vn.reader.Goto;
import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.data.UserDbHelper;
import hcmute.edu.vn.reader.fragment.ProfileFragment;
import hcmute.edu.vn.reader.model.User;

public class EditProfileFragment extends Fragment {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    Goto _goto;
    Button saveBtn, cancelBtn;
    EditText username, email, phone, address;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditProfileFragment() {
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
                returnToProfile();
            }
        });
        username = (EditText) view.findViewById(R.id.editUsername);
        email = (EditText) view.findViewById(R.id.editUserEmail);
        phone = (EditText) view.findViewById(R.id.editUserPhone);
        address = (EditText) view.findViewById(R.id.editUserAddress);
        if(user !=null){
            username.setText(user.getUsername());
            email.setText(user.getEmail());
            phone.setText(user.getPhone());
            address.setText(user.getAddress());
        }
        return view;
    }

    private void saveData(){
        UserDbHelper helper = new UserDbHelper(this.getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username.getText().toString());
        values.put("email", email.getText().toString());
        values.put("phone", phone.getText().toString());
        values.put("address", address.getText().toString());

        if(user == null){
            db.insert("user", null, values);
        }else{
            String whereClause = "username = '" + user.getUsername() + "'";
            db.update("user", values, whereClause,null);
        }
        returnToProfile();
    }

    private  void  returnToProfile(){
        _goto.GotoProfile();
    }
}
