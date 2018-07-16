package zoo.socialmedia.members;

import java.util.List;

public class Parrot extends Bird {

    public Parrot(String name, String wingSpan, String favoriteFood, boolean canSpeak) {
        super(name, wingSpan, favoriteFood);
        this.canSpeak = canSpeak;
    }
    private boolean canSpeak;

    public boolean isCanSpeak() {
        return canSpeak;
    }
    public void show(){
        List<String> friends = super.getFriends();
        System.out.printf("%s favorite food is %s. It has wingspan of %s and %s and %s %n",
                super.getName(), super.getFavoriteFood(), super.getWingSpan(), isCanSpeak()? "It can speak": "It can not speak",
                friends.size() > 0? "It has friends "+ friends.toString(): "It has no friend");
    }
}
