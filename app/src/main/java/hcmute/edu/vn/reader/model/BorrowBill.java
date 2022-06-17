package hcmute.edu.vn.reader.model;

import java.util.List;

public class BorrowBill {
    private int id;
    private String note;
    private boolean isReturned;
    private String planReturnDate;
    private String returnDate;
    private String borrowDate;
    private List<Book> books;

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public String getPlanReturnDate() {
        return planReturnDate;
    }

    public void setPlanReturnDate(String planReturnDate) {
        this.planReturnDate = planReturnDate;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
