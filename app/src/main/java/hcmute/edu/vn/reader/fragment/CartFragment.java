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
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import hcmute.edu.vn.reader.Goto;
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
    // fragment navigator
    Goto _goto;

    // book title list
    ArrayList<BookTitle> bookTitles;

    // list adapter
    CartItemAdapter cartItemAdapter;

    // UI projection
    FloatingActionButton registerBooks;
    ListView booksList;


    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        loadCartItem();
        _goto = (Goto) getActivity();

        View view =  inflater.inflate(R.layout.fragment_cart, container, false);

        registerBooks = (FloatingActionButton) view.findViewById(R.id.bookRegister);
        registerBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _goto.GotoBooking(bookTitles);
            }
        });
        booksList = (ListView) view.findViewById(R.id.cart_list);
        cartItemAdapter = new CartItemAdapter(getActivity(), R.layout.cart_item, bookTitles);
        booksList.setAdapter(cartItemAdapter);

        return view;
    }

    // load all book stored in cart
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