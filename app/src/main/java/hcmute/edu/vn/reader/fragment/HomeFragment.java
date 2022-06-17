package hcmute.edu.vn.reader.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.reader.Goto;
import hcmute.edu.vn.reader.MySingleton;
import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.api.ApiService;
import hcmute.edu.vn.reader.list_adapter.BooksAdapter;
import hcmute.edu.vn.reader.model.BaseResponse;
import hcmute.edu.vn.reader.model.BookTitle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    ListView bookList;
    SearchView searchBooks;

    List<BookTitle> arrayBooks;
    BooksAdapter adapter;

    Goto _goto;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _goto = (Goto) getActivity();

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        bookList = (ListView) view.findViewById(R.id.bookListView);
        searchBooks = (SearchView) view.findViewById(R.id.searchBooks);
        searchBooks.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                prepareBookList(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                prepareBookList(s);
                return true;
            }
        });

        prepareBookList("");

        return view;
    }

    private void prepareBookList(String searchQuery){
        ApiService.apiService.searchBookTitle(MySingleton.getInstance().getCurrentToken(), searchQuery,1,10).enqueue(new Callback<BaseResponse<List<BookTitle>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<BookTitle>>> call, Response<BaseResponse<List<BookTitle>>> response) {
                if(response.body() == null){
                    return;
                }
                arrayBooks = response.body().getData();
                adapter = new BooksAdapter(getActivity(), R.layout.book_item, arrayBooks);
                bookList.setAdapter(adapter);

                bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        _goto.GotoDetail(arrayBooks.get(i));
                    }
                });
            }

            @Override
            public void onFailure(Call<BaseResponse<List<BookTitle>>> call, Throwable t) {
                Toast.makeText(getContext(), "Please login or check your connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}