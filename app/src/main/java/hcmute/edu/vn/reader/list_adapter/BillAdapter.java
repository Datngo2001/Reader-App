package hcmute.edu.vn.reader.list_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import hcmute.edu.vn.reader.R;
import hcmute.edu.vn.reader.model.BookTitle;
import hcmute.edu.vn.reader.model.BorrowBill;
import hcmute.edu.vn.reader.model.BorrowRegister;
import hcmute.edu.vn.reader.util.ConvertDate;

public class BillAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<BorrowBill> borrowBills;

    public BillAdapter(Context context, int layout, List<BorrowBill> borrowBills) {
        this.context = context;
        this.layout = layout;
        this.borrowBills = borrowBills;
    }

    @Override
    public int getCount() {
        return borrowBills.size();
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
        TextView borrowDate;
        TextView returnDate;
        TextView status;
        TextView booksList;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.borrowDate = (TextView) view.findViewById(R.id.bill_date);
            holder.image = (ImageView) view.findViewById(R.id.bill_img);
            holder.returnDate = (TextView) view.findViewById(R.id.bill_planReturn);
            holder.status = (TextView) view.findViewById(R.id.bill_Status);
            holder.booksList = (TextView) view.findViewById(R.id.bill_Book);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        BorrowBill bill = borrowBills.get(i);
        String borrowdate = ConvertDate.fromISO8601UTC(bill.getBorrowDate()).toString();
        holder.borrowDate.setText("Borrow Date: " + borrowdate);

        String img = bill.getBooks().get(0).getBookTitle().getImage();
        Glide.with(context)
                .load(img)
                .centerInside()
                .into(holder.image);

        String titles = "";
        for (int j = 0; j < bill.getBooks().size(); j++) {
            titles += (bill.getBooks().get(j).getBookTitle().getTitle() + "\n");
        }
        holder.booksList.setText(titles);

        if(bill.isReturned()){
            holder.status.setText("Returned");
            String returnDate = ConvertDate.fromISO8601UTC(bill.getReturnDate()).toString();
            holder.returnDate.setText("Return Date: " + returnDate);
        }else{
            holder.status.setText("");
            String returnDate = ConvertDate.fromISO8601UTC(bill.getPlanReturnDate()).toString();
            holder.returnDate.setText("Plan return Date: " + returnDate);
        }

        return view;
    }
}
