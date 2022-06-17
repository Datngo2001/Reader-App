package hcmute.edu.vn.reader.list_adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.data.UserDbHelper;
import hcmute.edu.vn.reader.model.BookTitle;

public class CartItemAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<BookTitle> bookList;

    public CartItemAdapter(Context context, int layout, List<BookTitle> bookList) {
        this.context = context;
        this.layout = layout;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        LinearLayout cartItem;
        ImageView image;
        TextView txtTitle;
        TextView txtAuthor;
        Button deleteBtn;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTitle = (TextView) view.findViewById(R.id.cart_bookTitle);
            holder.image = (ImageView) view.findViewById(R.id.cart_bookImage);
            holder.txtAuthor = (TextView) view.findViewById(R.id.cart_author);
            holder.deleteBtn = (Button) view.findViewById(R.id.cart_delete);
            holder.cartItem = (LinearLayout) view.findViewById(R.id.cart_item);
            view.setTag(holder);
        }else{
            holder = (CartItemAdapter.ViewHolder) view.getTag();
        }

        BookTitle bookTitle = bookList.get(i);
        holder.txtTitle.setText(bookTitle.getTitle());
        holder.txtAuthor.setText(bookTitle.getAuthor());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCartItem(i);
                holder.cartItem.setVisibility(View.INVISIBLE);
            }
        });

        Glide.with(context)
                .load(bookTitle.getImage())
                .centerInside()
                .into(holder.image);

        return view;
    }

    private void deleteCartItem(int index){
        UserDbHelper helper = new UserDbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        String whereClause = "id = '" + bookList.get(index).getId() + "'";
        db.delete("cart", whereClause,null);

        bookList.remove(index);
    }
}
