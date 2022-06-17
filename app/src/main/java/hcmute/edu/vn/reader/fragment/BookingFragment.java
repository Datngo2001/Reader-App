package hcmute.edu.vn.reader.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hcmute.edu.vn.reader.Goto;
import hcmute.edu.vn.reader.MySingleton;
import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.api.ApiService;
import hcmute.edu.vn.reader.data.UserDbHelper;
import hcmute.edu.vn.reader.dtos.CreateBooksRegisterDto;
import hcmute.edu.vn.reader.model.BaseResponse;
import hcmute.edu.vn.reader.model.BookTitle;
import hcmute.edu.vn.reader.model.BorrowRegister;
import hcmute.edu.vn.reader.model.Dish;
import hcmute.edu.vn.reader.util.ConvertDate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingFragment extends Fragment {

    Goto _goto;
    List<BookTitle> bookTitles;

    Button registerBtn;
    CalendarView calendarView;
    EditText noteTxt;

    public void setBookTitles(List<BookTitle> books) {
        this.bookTitles = books;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookingFragment newInstance(String param1, String param2) {
        BookingFragment fragment = new BookingFragment();
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
        _goto = (Goto)getActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        calendarView = (CalendarView) view.findViewById(R.id.register_returnDate);
        registerBtn = (Button) view.findViewById(R.id.register_btn);
        noteTxt = (EditText) view.findViewById(R.id.register_note);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerBooks();
            }
        });

        return view;
    }

    private void registerBooks(){
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (BookTitle book:bookTitles) {
            ids.add(book.getId());
        }
        Date returnDate = new Date(calendarView.getDate());

        CreateBooksRegisterDto data = new CreateBooksRegisterDto();
        data.bookIds = ids;
        data.note = noteTxt.getText().toString();
        data.planReturnDate = ConvertDate.toISO8601UTC(returnDate);

        ApiService.apiService.registerBooks(MySingleton.getInstance().getCurrentToken(), data).enqueue(new Callback<BaseResponse<BorrowRegister>>() {
            @Override
            public void onResponse(Call<BaseResponse<BorrowRegister>> call, Response<BaseResponse<BorrowRegister>> response) {
                ClearCart();
                _goto.GotoHome();
            }

            @Override
            public void onFailure(Call<BaseResponse<BorrowRegister>> call, Throwable t) {

            }
        });
    }
    private  void ClearCart(){
        UserDbHelper helper = new UserDbHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        String whereClause = "True";
        db.delete("cart", whereClause,null);
    }
}