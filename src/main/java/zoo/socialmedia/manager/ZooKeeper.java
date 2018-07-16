package zoo.socialmedia.manager;

import zoo.socialmedia.listener.AnimalAddedListener;
import zoo.socialmedia.members.Animal;

import java.util.ArrayList;
import java.util.List;

/*
    ZooKeeper is a keeper class used to maintain register for addition of animals in the zoo

    @method addAnimal is used to add animals to the zoo and notify AnimalAddedListener which keeps the monitoring upto date.
    @method registerAnimalAddedListener is for registering each new animal added
    @method notifyAnimalAddedListeners for informing with each action done

 */

public class ZooKeeper {

    private List<Animal> animals = new ArrayList();
    private List<AnimalAddedListener> listeners = new ArrayList<>();

    public void addAnimal (Animal animal) {
        // Add the animal to the list of animals
        this.animals.add(animal);
        // Notify the list of registered listeners
        this.notifyAnimalAddedListeners(animal);
    }

    public List<Animal> getAnimals(){
        return this.animals;
    }

    public AnimalAddedListener registerAnimalAddedListener (AnimalAddedListener listener) {
        // Add the listener to the list of registered listeners
        this.listeners.add(listener);

        return listener;
    }

    protected void notifyAnimalAddedListeners (Animal animal) {
        // Notify each of the listeners in the list of registered listeners
        this.listeners.forEach(listener -> listener.onAnimalAdded(animal));
    }

}