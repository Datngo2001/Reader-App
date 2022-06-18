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
import hcmute.edu.vn.reader.list_adapter.BillAdapter;
import hcmute.edu.vn.reader.list_adapter.RegisterAdapter;
import hcmute.edu.vn.reader.model.BaseResponse;
import hcmute.edu.vn.reader.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BillFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillFragment extends Fragment {

    // UI projection
    ListView billList;


    public BillFragment() {
        // Required empty public constructor
    }

    public static BillFragment newInstance() {
        BillFragment fragment = new BillFragment();
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
        View view = inflater.inflate(R.layout.fragment_bill, container, false);

        billList = view.findViewById(R.id.bill_list);
        loadBill();

        return view;
    }

    // load all register bill
    private  void loadBill(){
        ApiService.apiService.status(MySingleton.getInstance().getCurrentToken()).enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                if(response.body() == null){
                    return;
                }
                BillAdapter adapter = new BillAdapter(getContext(), R.layout.bill_item, response.body().getData().getBorrowBills());
                billList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {

            }
        });
    }
}