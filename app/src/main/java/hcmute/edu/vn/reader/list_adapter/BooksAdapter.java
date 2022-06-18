package hcmute.edu.vn.reader.list_adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.model.BookTitle;
import hcmute.edu.vn.reader.model.Store;

public class BooksAdapter extends BaseAdapter {

    // current context
    private Context context;
    // current layout
    private int layout;
    // current list
    private List<BookTitle> bookList;

    public BooksAdapter(Context context, int layout, List<BookTitle> bookList) {
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
        ImageView image;
        TextView txtTitle;
        TextView txtAuthor;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTitle = (TextView) view.findViewById(R.id.home_bookTitle);
            holder.image = (ImageView) view.findViewById(R.id.home_bookImage);
            holder.txtAuthor = (TextView) view.findViewById(R.id.home_author);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        BookTitle bookTitle = bookList.get(i);
        holder.txtTitle.setText(bookTitle.getTitle());
        holder.txtAuthor.setText(bookTitle.getAuthor());

        Glide.with(context)
                .load(bookTitle.getImage())
                .centerInside()
                .into(holder.image);

        return view;
    }
}
