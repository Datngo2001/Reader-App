package hcmute.edu.vn.reader.model;

import java.util.List;

public class BorrowRegister {
    private int id; // book register id
    private String note; // note
    private boolean isConfirmed; // status of register has been approved
    private boolean isRejected; // status of register has been rejected
    private String planReturnDate; // the date that planning to return book
    private String createDate; //time create the register
    private int userId; // user's id
    private List<Book> books; // list of book

    public int getId() {
        return id;
    } // return book register id

    public void setId(int id) {
        this.id = id;
    } // set book register id

    public String getNote() {
        return note;
    } // return string of note

    public void setNote(String note) {
        this.note = note;
    } //set a string of note

    public boolean isConfirmed() {
        return isConfirmed;
    } // return the status of register has been approved

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    } //set the status of register to be approve

    public boolean isRejected() {
        return isRejected;
    } // return the status of register has been rejected

    public void setRejected(boolean rejected) {
        isRejected = rejected;
    } // set the status of register to be rejected

    public String getPlanReturnDate() {
        return planReturnDate;
    } // return date that planning to return book

    public void setPlanReturnDate(String planReturnDate) {
        this.planReturnDate = planReturnDate;
    } // set date that planning to return book

    public String getCreateDate() {
        return createDate;
    } // return date create the register

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    } // set the date create the register

    public int getUserId() {
        return userId;
    } // return user's id

    public void setUserId(int userId) {
        this.userId = userId;
    } // set user's id

    public List<Book> getBooks() {
        return books;
    } // return book in the list

    public void setBooks(List<Book> books) {
        this.books = books;
    }  // set book in the list

}
