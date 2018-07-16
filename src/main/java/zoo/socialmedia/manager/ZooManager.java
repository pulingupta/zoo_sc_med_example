package zoo.socialmedia.manager;

import zoo.socialmedia.members.Chicken;
import zoo.socialmedia.members.Dog;
import zoo.socialmedia.members.Parrot;

import java.util.Scanner;
import java.util.stream.IntStream;

/*
 * ZooManager keeps a record of all animals in a zoo.
 * it keeps a monitor of each animal added or removed

 * The main method will asks for following choices and based on the selection ZooManager does the required task
 * case 1 iterate all animals with friends
 * case 2 live one day, where relations are added and broken
 * case 3 exit
 */
public class ZooManager {

    public static void main (String[] args) {
        System.out.println("########################################################################\n");
        System.out.println("#####################Welcome to Zoo Manager Application#####################\n");
        System.out.println("########################################################################\n");
        System.out.println("Choose what you want");
        System.out.println("-------------------------\n");
        System.out.println("1 - Iterate all animals and get their characteristics along with their friend names");
        System.out.println("2 - Live a day in zoo");
        System.out.println("3 - Exit");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        try{
            ZooManager manager = new ZooManager();
            switch (choice) {
                case 1:
                    manager.showAnimalsAndProperties();
                    break;
                case 2:
                    System.out.println("Enter no of days the everyday activity should be shown : ");
                    int activityDays = scanner.nextInt();
                    manager.performDayActivity(activityDays);
                    break;
                case 3:
                    System.out.println("Thank you for running the application...exiting");
                    break;
                default:
                    System.out.println("Please make the currect choice and try again...exiting");
            }

        } catch(Exception e) {
            System.out.println("Exception occured ZooManager main "+e.getMessage());
        }
    }

    public void showAnimalsAndProperties() {
        ZooKeeper zoo = new ZooKeeper();
        registerAndShowAnimals(zoo);
    }

    public void performDayActivity(int noOfDays) {
        ZooKeeper zoo = new ZooKeeper();
        registerAndShowAnimals(zoo);
        showDayActivity(zoo, noOfDays > -1 ? noOfDays : 0);

    }

    public void showDayActivity(ZooKeeper zooObj, int noOfDays) {
        RelationshipHandler relHandler = new RelationshipHandler();
        registerAddAndRemoveFriendslisteners(relHandler);
        IntStream.range(0, noOfDays)
                .forEach(day -> {
                    System.out.printf("\nOn Day %s following activity is executed:", day);
                    relHandler.iterateZooAnimals(zooObj);
                });
    }

    private void registerAddAndRemoveFriendslisteners(RelationshipHandler relHandler) {
        relHandler.registerFriendRemovedListener (
                (fromAnimal, friendRemoved)  -> {
                    if(!friendRemoved.isEmpty()) System.out.printf("\n%s broke relationship with %s", fromAnimal, friendRemoved);
                    else System.out.printf("\n%s have none friends and so could not broke relationship with anyone", fromAnimal);
                }
        );

        relHandler.registerFriendAddedListener (
                (fromAnimal, friendAdded)  -> {
                    if(!friendAdded.isEmpty()) System.out.printf("\n%s made relationship with %s %n", fromAnimal, friendAdded);
                    else System.out.printf("\n%s have none friends and so could not make relationship with anyone " +
                            "%n", fromAnimal);
                }
        );
    }


    public void registerAndShowAnimals(ZooKeeper zoo) {
        zoo.registerAnimalAddedListener (
                (animal) -> {
                    animal.show();
                }
        );
        zoo.addAnimal(new Dog("Dog One", "Hunting dog", "Meat"));
        zoo.addAnimal(new Parrot( "Parrot One",  "0.25",  "grain",  false));
        zoo.addAnimal(new Chicken("Chicken one",  "0,75",  "Corn",  true));
        zoo.addAnimal(new Dog("Dog Two", "Assistance dog", "Fresh meat"));
        zoo.addAnimal(new Parrot( "Parrot Two",  "0.5",  "Corn",  true));
        zoo.addAnimal(new Dog("Dog Three", "Racing dog", "Pedigree"));
        zoo.addAnimal(new Chicken("Chicken two",  "0,75",  "Corn",  false));
    }
}
