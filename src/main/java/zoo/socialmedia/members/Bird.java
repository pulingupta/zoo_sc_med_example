package zoo.socialmedia.members;

public class Bird extends Animal {

    public Bird(String name, String wingSpan, String favoriteFood) {
        super(name, favoriteFood);
        this.wingSpan = wingSpan;
    }
    private String wingSpan;
    public String getWingSpan() {
        return wingSpan;
    }

}
