package hcmute.edu.vn.reader.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.data.UserDbHelper;
import hcmute.edu.vn.reader.list_adapter.CartItemAdapter;
import hcmute.edu.vn.reader.model.BookTitle;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    ListView booksList;
    CartItemAdapter cartItemAdapter;
    ArrayList<BookTitle> bookTitles;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        View view =  inflater.inflate(R.layout.fragment_cart, container, false);

        booksList = view.findViewById(R.id.cart_list);
        loadCartItem();
        cartItemAdapter = new CartItemAdapter(getActivity(), R.layout.cart_item, bookTitles);
        booksList.setAdapter(cartItemAdapter);

        return view;
    }

    private void loadCartItem(){
        bookTitles = new ArrayList<BookTitle>();

        UserDbHelper helper = new UserDbHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        String projections[] = {"id", "title", "author", "image", "description"};
        Cursor c = db.query("cart", projections, null, null, null,null, null);

        if(c.getCount() > 0) {
            while (!c.isLast()) {
                c.moveToNext();
                BookTitle bookTitle = new BookTitle();
                bookTitle.setId(c.getInt(0));
                bookTitle.setTitle(c.getString(1));
                bookTitle.setAuthor(c.getString(2));
                bookTitle.setImage(c.getString(3));
                bookTitle.setDescription(c.getString(4));
                bookTitles.add(bookTitle);
            }
        }

        c.close();
    }
}