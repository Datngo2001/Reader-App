package hcmute.edu.vn.reader.model;

public class Book {
    private int id;
    private boolean isGood;
    private String createdAt;
    private int bookTitleId;
    private BookTitle bookTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getBookTitleId() {
        return bookTitleId;
    }

    public void setBookTitleId(int bookTitleId) {
        this.bookTitleId = bookTitleId;
    }

    public BookTitle getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(BookTitle bookTitle) {
        this.bookTitle = bookTitle;
    }
}
