package zoo.socialmedia.manager;

import zoo.socialmedia.listener.FriendAddedListener;
import zoo.socialmedia.listener.FriendRemovedListener;
import zoo.socialmedia.members.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
Relatonship Manager describes relationship between each animal in the zoo.
it maintains a register of all make and breakup of friendships within animals.

It also keeps responsibility of informing listeners of each make or break of firndship to log to common base.

@method iterateZooAnimals is used to iterate all animals in the zoo and perform their day activity.
@method removeFriend and addFriends are for removing and adding respectively
@method *listeners are for acknowledging the events.
 */

public class RelationshipHandler {

    public static final String EMPTY_STRING = "";

    public boolean iterateZooAnimals(ZooKeeper zoo) {
        List<Animal> animalList = zoo.getAnimals();
        Random randomGenerator = new Random();
        if(!animalList.isEmpty()) {
            animalList
                    .forEach(theAnimal -> {
                        removeFriends(theAnimal, randomFriendToBreak(randomGenerator, theAnimal.getFriends()), animalList);
                        addFriends(theAnimal, randomPickFriendToBeAdded(randomGenerator, animalList, theAnimal));
                    });
            return true;
        }
        return false;
    }

    public String removeFriends( Animal animal, String friendNameToBeRemoved, List<Animal> zoo) {
        List<String> requesterFriends = animal.getFriends();
        if (!friendNameToBeRemoved.isEmpty() && requesterFriends.contains(friendNameToBeRemoved)) {
            requesterFriends.removeIf(name -> name == friendNameToBeRemoved);
            zoo
                    .stream()
                    .filter(c -> c.getName().equals(friendNameToBeRemoved))
                    .findFirst()
                    .ifPresent(found -> found.getFriends().remove(animal));
            this.notifyFriendRemovedListeners(animal.getName(), friendNameToBeRemoved);
            return friendNameToBeRemoved;
        } else {
            System.out.printf("\n%s has no friends so could not broke relationship with anyone ",animal.getName());
            return EMPTY_STRING;
        }
    }

    public String addFriends(Animal a, Animal b){
        if(!a.getName().equalsIgnoreCase(b.getName())) {
            a.addFriends(b.getName());
            b.addFriends(a.getName());
            this.notifyFriendAddedListeners(a.getName(), b.getName());
            return b.getName();
        }
        return EMPTY_STRING;
    }

    public Animal randomPickFriendToBeAdded(Random randomGen, List<Animal> zoo, Animal friendRequester){
        List<Animal> zooTemp = new ArrayList<>(zoo);
        zooTemp.removeIf(animal -> animal.getName() == friendRequester.getName());
        List<String> friends = friendRequester.getFriends();
        friends.forEach(friendName -> {
            zooTemp.removeIf(animal -> animal.getName() == friendName);
        });
        return !zooTemp.isEmpty() ? zooTemp.get(randomGen.nextInt(zooTemp.size())) : null;
    }

    public String randomFriendToBreak(Random randomGen, List<String> theAnimalFriends){
        if(!theAnimalFriends.isEmpty()){
            return theAnimalFriends.get((randomGen.nextInt(theAnimalFriends.size())));
        }
        return EMPTY_STRING;
    }

    private List<FriendRemovedListener> friendRemovedlisteners = new ArrayList<>();

    protected void notifyFriendRemovedListeners (String fromAnimal, String animalName) {
        // Notify each of the listeners in the list of registered listeners
        this.friendRemovedlisteners.forEach(listener -> listener.onFriendRemoved(fromAnimal, animalName));
    }

    public FriendRemovedListener registerFriendRemovedListener (FriendRemovedListener listener) {
        // Add the listener to the list of registered listeners
        this.friendRemovedlisteners.add(listener);
        return listener;
    }

    private List<FriendAddedListener> friendAddedlisteners = new ArrayList<>();

    protected void notifyFriendAddedListeners (String fromAnimal, String friendAdded) {
        // Notify each of the listeners in the list of registered listeners
        this.friendAddedlisteners.forEach(listener -> listener.onFriendAdded(fromAnimal, friendAdded));
    }
    public void registerFriendAddedListener (FriendAddedListener listener) {
        // Add the listener to the list of registered listeners
        this.friendAddedlisteners.add(listener);
    }
}
