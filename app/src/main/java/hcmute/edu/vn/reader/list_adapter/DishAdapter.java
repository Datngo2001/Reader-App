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
import hcmute.edu.vn.reader.model.Dish;

public class DishAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Dish> dishList;

    public DishAdapter(Context context, int layout, List<Dish> storeList) {
        this.context = context;
        this.layout = layout;
        this.dishList = storeList;
    }

    @Override
    public int getCount() {
        return dishList.size();
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
        TextView txtPrice;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTen = (TextView) view.findViewById(R.id.dishName);
            holder.image = (ImageView) view.findViewById(R.id.dishImage);
            holder.txtDescription = (TextView) view.findViewById(R.id.dishDescription);
            holder.txtPrice = (TextView) view.findViewById(R.id.dishPrice);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Dish dish = dishList.get(i);
        holder.txtTen.setText(dish.getName());
        holder.txtDescription.setText(dish.getDescription());
        holder.image.setImageResource(dish.getImage());
        holder.txtPrice.setText(Long.toString(dish.getPrice()));
        return view;
    }
}
