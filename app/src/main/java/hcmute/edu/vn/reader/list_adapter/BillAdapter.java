package hcmute.edu.vn.reader.list_adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import hcmute.edu.vn.reader.model.BookTitle;
import hcmute.edu.vn.reader.model.BorrowBill;

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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        return null;
    }
}
