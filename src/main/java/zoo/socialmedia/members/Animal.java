package zoo.socialmedia.members;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    private String name;
    private String favoriteFood;
    List<String> friendsName = new ArrayList();
    public Animal(String name, String favouriteFood) {
        this.name = name;
        this.favoriteFood = favouriteFood;
    }
    public String getName () {
        return this.name;
    }
    public String getFavoriteFood () {
        return this.favoriteFood;
    }
    private ArrayList<Animal> friends = new ArrayList<Animal>();
    public void addFriends(String friendsName){
        this.getFriends().add(friendsName);
    }
    public List<String> getFriends() {
        return friendsName;
    }

    public void show(){
        System.out.println("To print I am "+name);
        System.out.println("My favourite food is "+favoriteFood);
        System.out.println("my friends are "+friends.toString());
    }
}