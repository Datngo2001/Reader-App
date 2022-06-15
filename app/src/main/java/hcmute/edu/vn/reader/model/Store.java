package hcmute.edu.vn.reader.model;

public class Store {
    private String name;
    private int image;
    private int coverImage;
    private String description;

    public Store(String name, int image, int coverImage, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.coverImage = coverImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(int coverImage) {
        this.coverImage = coverImage;
    }

}
