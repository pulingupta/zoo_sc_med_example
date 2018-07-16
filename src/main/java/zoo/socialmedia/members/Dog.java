package zoo.socialmedia.members;

import java.util.List;

public class Dog extends Animal{
    public Dog(String name, String dogType, String favoriteFood) {
        super(name, favoriteFood);
        this.dogType = dogType;
    }
    public String getDogType() {
        return dogType;
    }
    private String dogType;

    public void show(){
        List<String> friends = super.getFriends();

        System.out.printf("%s's favorite food is %s. Its type belongs to %s and %s %n",
                super.getName(), super.getFavoriteFood(), getDogType(), friends.size() > 0? "It has friends "+ friends.toString(): "It has no friend");
    }
}