package hcmute.edu.vn.reader.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import hcmute.edu.vn.reader.Goto;
import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.list_adapter.DishAdapter;
import hcmute.edu.vn.reader.model.BookTitle;
import hcmute.edu.vn.reader.model.Dish;
import hcmute.edu.vn.reader.model.Store;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookFragment extends Fragment {
    private BookTitle bookTitle;

    Button addToCart;
    TextView bookTitleTxt, bookAuthorTxt, bookDesTxt;
    ImageView bookImage;

    Goto _goto;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookFragment() {
        // Required empty public constructor
    }

    public void setBookTitle(BookTitle bookTitle) {
        this.bookTitle = bookTitle;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookFragment newInstance(String param1, String param2) {
        BookFragment fragment = new BookFragment();
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
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        bookTitleTxt = (TextView) view.findViewById(R.id.bookTitle);
        bookTitleTxt.setText(bookTitle.getTitle());

        bookDesTxt = (TextView) view.findViewById(R.id.bookDescription);
        bookDesTxt.setText(bookTitle.getDescription());

        bookAuthorTxt = (TextView) view.findViewById(R.id.bookAuthor);
        bookAuthorTxt.setText(bookTitle.getAuthor());

        bookImage = (ImageView) view.findViewById(R.id.bookImage);
        Glide.with(getActivity()).load(bookTitle.getImage()).centerInside().into(bookImage);

        return view;
    }
}