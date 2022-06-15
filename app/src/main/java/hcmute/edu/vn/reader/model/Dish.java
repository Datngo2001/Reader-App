package hcmute.edu.vn.reader.model;

public class Dish {
    private String name;
    private int image;
    private String description;
    private long price;

    public Dish(String name, int image, String description, long price) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
