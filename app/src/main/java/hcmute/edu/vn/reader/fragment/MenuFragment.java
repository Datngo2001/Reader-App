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

import java.util.ArrayList;

import hcmute.edu.vn.reader.Goto;
import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.list_adapter.DishAdapter;
import hcmute.edu.vn.reader.model.Dish;
import hcmute.edu.vn.reader.model.Store;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {
    private Store store;

    Button toBookingBtn;
    TextView storeName;
    ImageView storeImage;
    ImageView storeCoverImage;
    ListView dishListView;

    ArrayList<Dish> arrayDish;
    DishAdapter adapter;
    Goto _goto;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    public void setStore(Store store) {
        this.store = store;
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
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        storeName = (TextView) view.findViewById(R.id.storeNameMenu);
        storeName.setText(store.getName());

        storeImage = (ImageView) view.findViewById(R.id.menuStoreImage);
        storeImage.setImageResource(store.getImage());

        storeCoverImage = (ImageView) view.findViewById(R.id.menuCoverImage);
        storeCoverImage.setImageResource(store.getCoverImage());

        dishListView = (ListView) view.findViewById(R.id.dishListView);
        arrayDish = new ArrayList<Dish>();
        AddArrayDish();
        adapter = new DishAdapter(getActivity(), R.layout.dish_item, arrayDish);
        dishListView.setAdapter(adapter);

        dishListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                _goto.GotoBooking(arrayDish.get(i));
            }
        });

        return view;
    }

    private void AddArrayDish(){
        arrayDish.add(new Dish("Com chien 1", R.drawable.comchien, "com ngon", 30000));
        arrayDish.add(new Dish("Com chien 2", R.drawable.comchien, "com ngon", 40000));
        arrayDish.add(new Dish("Com chien 3", R.drawable.comchien, "com ngon", 80000));
        arrayDish.add(new Dish("Com chien 4", R.drawable.comchien, "com ngon", 100000));
    }
}