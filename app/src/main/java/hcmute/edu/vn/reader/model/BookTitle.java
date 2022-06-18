package hcmute.edu.vn.reader.model;

import java.util.List;

public class BookTitle {
    private int id; // book title id
    private String title; // book title name
    private String author; // book title author
    private  String image; // book title image
    private String description; // book title description
    private String createdAt; // the time create book title
    private List<Category> categorys; // list of category

    public int getId() {
        return id;
    } // return book title id value

    public void setId(int id) {
        this.id = id;
    } // set book title id value

    public String getTitle() {
        return title;
    } // get book title name

    public void setTitle(String title) {
        this.title = title;
    } // set book title name

    public String getAuthor() {
        return author;
    } // get author name

    public void setAuthor(String author) {
        this.author = author;
    } // set author name

    public String getImage() {
        return image;
    } // get book title image

    public void setImage(String image) {
        this.image = image;
    } // set book title image

    public String getDescription() {
        return description;
    } //return description about the book

    public void setDescription(String description) {
        this.description = description;
    } // set description about the book

    public String getCreatedAt() {
        return createdAt;
    } // return time create book

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    } // set time create book

    public List<Category> getCategorys() {
        return categorys;
    } // return list of category

    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    } // set category in the list
}
