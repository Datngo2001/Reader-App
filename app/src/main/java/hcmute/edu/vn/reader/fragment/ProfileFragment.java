package hcmute.edu.vn.reader.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import hcmute.edu.vn.reader.Goto;
import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.data.UserDbHelper;
import hcmute.edu.vn.reader.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private User currentUser;

    Goto _goto;
    FloatingActionButton editButton;
    TextView username, email, phone, address;

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
        username = (TextView) view.findViewById(R.id.profileUsername);
        email = (TextView) view.findViewById(R.id.profileEmail);
        phone = (TextView) view.findViewById(R.id.profilePhone);
        address = (TextView) view.findViewById(R.id.profileAddress);

        ReadCurrentUser();
        if(currentUser == null){
            return view;
        }
        username.setText(currentUser.getUsername());
        email.setText(currentUser.getEmail());

        return view;
    }

    private void openEditForm(){
        _goto.GotoEditProfile(currentUser);
    }

    private void ReadCurrentUser(){
        UserDbHelper helper = new UserDbHelper(this.getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        String projections[] = {"username", "email", "fname", "lname", "token"};
        Cursor c = db.query("user", projections, null, null, null,null, null);
        if (c == null){
            currentUser = null;
            return;
        }else if(c.getCount() > 0){
            c.moveToPosition(0);
            currentUser = new User();
            currentUser.setUsername(c.getString(0));
            currentUser.setEmail(c.getString(1));
        }else{
            currentUser = null;
        }
        c.close();
    }
}