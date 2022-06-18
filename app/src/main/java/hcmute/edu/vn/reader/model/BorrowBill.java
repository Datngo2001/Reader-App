package hcmute.edu.vn.reader.model;

import java.util.List;

public class BorrowBill {
    private int id; // id bill
    private String note; // note for librarian
    private boolean isReturned; // status of borrowed book
    private String planReturnDate; // planning date return
    private String returnDate; // date return
    private String borrowDate; // date borrow book
    private List<Book> books; // list of book has been borrowed

    public String getReturnDate() {
        return returnDate;
    } // return the date that you has returned the book

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    } // set the the date that you has returned book

    public int getId() {
        return id;
    } // get id of bill

    public void setId(int id) {
        this.id = id;
    } // set id of bill

    public String getNote() {
        return note;
    } // return a string of note

    public void setNote(String note) {
        this.note = note;
    } // set a string of note

    public boolean isReturned() {
        return isReturned;
    } // return status of book has returned or not

    public void setReturned(boolean returned) {
        isReturned = returned;
    } // set the status of book has returned or not

    public String getPlanReturnDate() {
        return planReturnDate;
    } // return planning date to return the book

    public void setPlanReturnDate(String planReturnDate) {
        this.planReturnDate = planReturnDate;
    } // set planning date to return the book

    public String getBorrowDate() {
        return borrowDate;
    } // return the borrow date

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    } // set the borrow date

    public List<Book> getBooks() {
        return books;
    } // return book in the list

    public void setBooks(List<Book> books) {
        this.books = books;
    } // set book in the list
}
