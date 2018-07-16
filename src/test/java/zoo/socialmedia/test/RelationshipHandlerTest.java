package zoo.socialmedia.test;

import org.junit.Test;
import zoo.socialmedia.manager.RelationshipHandler;
import zoo.socialmedia.manager.ZooKeeper;
import zoo.socialmedia.manager.ZooManager;
import zoo.socialmedia.members.Animal;

import java.util.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class RelationshipHandlerTest {

    @Test
    public void test_iteration_animallist_with_no_entries() {
        RelationshipHandler rel = new RelationshipHandler();
        ZooKeeper zooKeeper = new ZooKeeper();
        boolean iterationDone = rel.iterateZooAnimals(zooKeeper);
        assertEquals(false, iterationDone);
    }

    @Test
    public void test_iteration_animallist_with_entries() {
        ZooManager zooManager = new ZooManager();
        ZooKeeper zooKeeper = new ZooKeeper();
        zooManager.registerAndShowAnimals(zooKeeper);
        RelationshipHandler rel = new RelationshipHandler();
        boolean iterationDone = rel.iterateZooAnimals(zooKeeper);
        assertEquals(true, iterationDone);
    }
    @Test
    public void test_when_same_name_animal_put_to_add_list_randomPickFriendToBeAdded() {
        Random randomGen = new Random();
        RelationshipHandler rel = new RelationshipHandler();
        List<Animal> list = new ArrayList<>();
        list.add(new Animal("test","fake3"));
        Animal animalReturned = rel.randomPickFriendToBeAdded(randomGen, list, new Animal("test","fake"));
        assertEquals(null, animalReturned);
    }

    @Test
    public void test_when_different_name_animal_put_to_add_list_randomPickFriendToBeAdded() {
        Random randomGen = new Random();
        RelationshipHandler rel = new RelationshipHandler();
        List<Animal> list = new ArrayList<>();
        list.add(new Animal("test2","fake4"));
        Animal animalReturned = rel.randomPickFriendToBeAdded(randomGen, list, new Animal("test","fake"));
        assertEquals("test2", animalReturned.getName());
    }
    @Test
    public void test_when_empty_list_animal_put_to_add_list_randomPickFriendToBeAdded() {
        Random randomGen = new Random();
        RelationshipHandler rel = new RelationshipHandler();
        List<Animal> list = new ArrayList<>();
        Animal animalReturned = rel.randomPickFriendToBeAdded(randomGen, list, new Animal("test","fake"));
        assertEquals(null, animalReturned);
    }

    @Test
    public void test_when_valid_list_animal_put_to_randomFriendToBreak() {
        Random randomGen = new Random();
        List<String> animalFriends  = Arrays.asList("Dog");
        RelationshipHandler rel = new RelationshipHandler();
        List<Animal> list = new ArrayList<>();
        String animalReturned = rel.randomFriendToBreak(randomGen, animalFriends);
        assertEquals("Dog", animalReturned);
    }

    @Test
    public void test_when_empty_list_animal_put_to_remove_list_randomFriendToBreak() {
        Random randomGen = new Random();
        List<String> animalFriends  = new ArrayList<>();
        RelationshipHandler rel = new RelationshipHandler();
        String animalReturned = rel.randomFriendToBreak(randomGen, animalFriends);
        assertEquals("", animalReturned);
    }

    @Test
    public void test_add_friends() {
        Animal animal1 = new Animal("Dog One","pedigree");
        Animal animal2 = new Animal("Parrot","corns");
        RelationshipHandler rel = new RelationshipHandler();
        rel.addFriends(animal1, animal2);

        assertThat(animal1.getFriends().toString(), containsString("Parrot"));
    }

    @Test
    public void test_invalid_input_add_friends() {
        Animal animal1 = new Animal("Dog One","pedigree");
        Animal animal2 = new Animal("Dog One","corns");
        RelationshipHandler rel = new RelationshipHandler();
        rel.addFriends(animal1, animal2);

        assertEquals(Collections.EMPTY_LIST, animal1.getFriends());
    }

    @Test
    public void test_valid_input_remove_friends() {
        Animal animal = new Animal("Dog Two","pedigree");
        animal.addFriends("Dog One");
        List<Animal> list = new ArrayList<>();
        list.add(new Animal("Dog One","fake4"));
        list.add(new Animal("Parrot","fake4"));
        String animalToBeRemoved = "Dog One";
        RelationshipHandler rel = new RelationshipHandler();
        assertEquals("Dog One", rel.removeFriends(animal, animalToBeRemoved, list));
    }

    @Test
    public void test_invalid_input_remove_friends() {
        Animal animal = new Animal("Dog Two","pedigree");
        animal.addFriends("Parrot");
        List<Animal> zoo = new ArrayList<>();
        zoo.add(new Animal("Dog One","fake4"));
        zoo.add(new Animal("Parrot","fake4"));
        String animalToBeRemoved = "Parrot2";
        RelationshipHandler rel = new RelationshipHandler();
        assertEquals("", rel.removeFriends(animal, animalToBeRemoved, zoo));
    }


}
