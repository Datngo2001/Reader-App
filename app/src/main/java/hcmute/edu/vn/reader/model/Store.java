package hcmute.edu.vn.reader.model;

public class Store {
    private String name; // book name
    private int image; // book image
    private int coverImage; // book cover image
    private String description; // book description

    //method to get the name, image, cover image, description of book
    public Store(String name, int image, int coverImage, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.coverImage = coverImage;
    }

    public String getName() {
        return name;
    } // return book name

    public void setName(String name) {
        this.name = name;
    } // set book name

    public int getImage() {
        return image;
    } // return book image

    public void setImage(int image) {
        this.image = image;
    } //set book image

    public String getDescription() {
        return description;
    } // get book description

    public void setDescription(String description) {
        this.description = description;
    } // set book description

    public int getCoverImage() {
        return coverImage;
    } // return book cover image

    public void setCoverImage(int coverImage) {
        this.coverImage = coverImage;
    } // set book cover image

}
