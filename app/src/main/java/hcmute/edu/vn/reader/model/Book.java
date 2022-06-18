package hcmute.edu.vn.reader.model;

public class Book {
    private int id; // id of book
    private boolean isGood; // condition of book
    private String createdAt; // the time book has been add
    private int bookTitleId; // book title id
    private BookTitle BookTitle; // name of book title

    public int getId() {
        return id;
    } // get the id of book

    public void setId(int id) {
        this.id = id;
    }   // set the id of book

    public boolean isGood() {
        return isGood;
    } // return condition of book is good or not

    public void setGood(boolean good) {
        isGood = good;
    } // set the condition of book

    public String getCreatedAt() {
        return createdAt;
    } // return time create book

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    } // set time create book

    public int getBookTitleId() {
        return bookTitleId;
    } // get book title id

    public void setBookTitleId(int bookTitleId) {
        this.bookTitleId = bookTitleId;
    } // set book title id

    public BookTitle getBookTitle() {
        return this.BookTitle;
    } // get book title name

    public void setBookTitle(BookTitle bookTitle) {
        this.BookTitle = bookTitle;
    } // set book title name
}
