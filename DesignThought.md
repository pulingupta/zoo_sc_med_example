## Zoo Social Media Management Application

ZSMA is designed to be using Observer Pattern along with basic OOPS concepts of Inheritence and Polymorphism 
to handle different species in a zoo.

ZSMA is boradly divided into ZooManager (Main class), Zoo Keeper (Register for Zoo Animals) and 
Relationship Manager (One who maintains relationship with different species)

The code is kept in mind to use observer pattern 

The power to register or unregister (no coded though) a listener is kept with client code 
i.e ZooManager (client for registering animals) or RelationshipHandler (client for registering friendship)

    public FriendRemovedListener registerFriendRemovedListener (FriendRemovedListener listener) {
        // Add the listener to the list of registered listeners
        this.friendRemovedlisteners.add(listener);
        return listener;
    }
     
   OR
    
    public AnimalAddedListener registerAnimalAddedListener (AnimalAddedListener listener) {
            // Add the listener to the list of registered listeners
            this.listeners.add(listener);
    
            return listener;
    }

Code is done so that the class implementing the listener can be used to register the action as well.

Animal have been arranged so that inheritance is followed and polymorphism is used to 
show the properties of each animals without calling show properties of each specific specie.

Rest has been kept as simple as it could be. The main idea is keeping statuses running seperately 
while being controlled byy clients doing actual action.

### Things Good to use but could not be used
1. Any third party logging is not used as was written in problem, but could have been used.
2. Powermock to test private functions are not used because of third library but would be really good to have
3. functions are mostly kept public because of TDD and no use of powermock. Ideally they should be 
refactored to different access layers

### Things assumed and unclear
1. Option 1 in the requirement, iterate all animals with properties and friends, but since 
no animal has friend in the start, teh question should be modified so as to find friends on day 2 or day 3 
when actualy friends are there based on one to many relationship.
 