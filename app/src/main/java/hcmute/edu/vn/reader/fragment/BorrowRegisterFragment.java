package hcmute.edu.vn.reader.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import hcmute.edu.vn.reader.MySingleton;
import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.api.ApiService;
import hcmute.edu.vn.reader.list_adapter.RegisterAdapter;
import hcmute.edu.vn.reader.model.BaseResponse;
import hcmute.edu.vn.reader.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BorrowRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BorrowRegisterFragment extends Fragment {

    // current register
    ListView registerList;

    public BorrowRegisterFragment() {
        // Required empty public constructor
    }

    public static BorrowRegisterFragment newInstance(String param1, String param2) {
        BorrowRegisterFragment fragment = new BorrowRegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_borrow_register, container, false);

        registerList = view.findViewById(R.id.register_list);
        loadRegister();

        return view;
    }

    // load user borrow register
    private  void loadRegister(){
        ApiService.apiService.status(MySingleton.getInstance().getCurrentToken()).enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                if(response.body() == null){
                    return;
                }
                RegisterAdapter adapter = new RegisterAdapter(getContext(), R.layout.register_item, response.body().getData().getBorrowRegister());
                registerList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {

            }
        });
    }
}