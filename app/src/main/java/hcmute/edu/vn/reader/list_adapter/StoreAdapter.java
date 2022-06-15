package hcmute.edu.vn.reader.list_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.model.Store;

public class StoreAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Store> storeList;

    public StoreAdapter(Context context, int layout, List<Store> storeList) {
        this.context = context;
        this.layout = layout;
        this.storeList = storeList;
    }

    @Override
    public int getCount() {
        return storeList.size();
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
        TextView txtTen;
        TextView txtDescription;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTen = (TextView) view.findViewById(R.id.storeName);
            holder.image = (ImageView) view.findViewById(R.id.storeImage);
            holder.txtDescription = (TextView) view.findViewById(R.id.storeDescription);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Store store = storeList.get(i);
        holder.txtTen.setText(store.getName());
        holder.txtDescription.setText(store.getDescription());
        holder.image.setImageResource(store.getImage());
        return view;
    }
}
