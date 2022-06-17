package hcmute.edu.vn.reader.list_adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Date;
import java.util.List;

import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.model.Book;
import hcmute.edu.vn.reader.model.BookTitle;
import hcmute.edu.vn.reader.model.BorrowRegister;
import hcmute.edu.vn.reader.util.ConvertDate;

public class RegisterAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<BorrowRegister> registers;

    public RegisterAdapter(Context context, int layout, List<BorrowRegister> registers) {
        this.context = context;
        this.layout = layout;
        this.registers = registers;
    }

    @Override
    public int getCount() {
        return registers.size();
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
        TextView regisDate;
        TextView returnDate;
        TextView status;
        TextView booksList;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.regisDate = (TextView) view.findViewById(R.id.register_date);
            holder.image = (ImageView) view.findViewById(R.id.register_img);
            holder.returnDate = (TextView) view.findViewById(R.id.register_planReturn);
            holder.status = (TextView) view.findViewById(R.id.register_Status);
            holder.booksList = (TextView) view.findViewById(R.id.register_Book);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        BorrowRegister register = registers.get(i);
        String regisDate = ConvertDate.fromISO8601UTC(register.getCreateDate()).toString();
        holder.regisDate.setText("Register Date: " + regisDate);
        String returnDate = ConvertDate.fromISO8601UTC(register.getPlanReturnDate()).toString();
        holder.returnDate.setText("Return Date: " + returnDate);

        String img = register.getBooks().get(0).getBookTitle().getImage();
        Glide.with(context)
                .load(img)
                .centerInside()
                .into(holder.image);

        String titles = "";
        for (int j = 0; j < register.getBooks().size(); j++) {
            titles += (register.getBooks().get(j).getBookTitle().getTitle() + "\n");
        }
        holder.booksList.setText(titles);

        if(register.isRejected()){
            holder.status.setText("Rejected");
            holder.status.setTextColor(R.color.colorDelete);
        }else{
            holder.status.setText("Pending");
            holder.status.setTextColor(R.color.colorPrimary);
        }

        return view;
    }
}
