package zoo.socialmedia.members;

import java.util.List;

public class Chicken extends Bird {

    public Chicken(String name, String wingSpan, String favoriteFood, boolean broiler) {
        super(name, wingSpan, favoriteFood);
        this.broiler = broiler;
    }

    private boolean broiler;

    public boolean isBroiler() {
        return broiler;
    }
    List<String> friends = super.getFriends();
    public void show(){
        System.out.printf("%s favorite food is %s. it has wingspan of %s and %s and %s %n",
                super.getName(), super.getFavoriteFood(), super.getWingSpan(), isBroiler()? "It is broiler": "It is not broiler",
                friends.size() > 0? "It has friends "+ friends.toString(): "It" +
                        " has no friend");
    }
}
