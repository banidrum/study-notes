public class MapExample {
    public static void main(String[] args) {

        // Creates the map structure

        Map<> people = new HashMap<Integer, String="">();

        // Adds items to the Map

        people.put(1, "Person 1");
        people.put(2, "Person 2");
        people.put(3, "Person 3");

        // Removes items

        people.remove(1);

        // After item removal

        System.out.println("The map now looks like this: " + people);
        
    }
}