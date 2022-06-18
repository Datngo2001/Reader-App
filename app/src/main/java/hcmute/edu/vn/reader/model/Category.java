package hcmute.edu.vn.reader.model;

public class Category {
    private int id; // category's id
    private String name; // category's name
    private String createdAt; // time create category

    public int getId() {
        return id;
    } // return category's id

    public void setId(int id) {
        this.id = id;
    } //set category's id

    public String getName() {
        return name;
    } //return category's name

    public void setName(String name) {
        this.name = name;
    } // set category's name

    public String getCreatedAt() {
        return createdAt;
    } // return time create category

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    } // set time create category
}
