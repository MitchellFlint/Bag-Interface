import java.util.Arrays;

public class TestBags {
    public static void main(String[] args) {
        ArrayBag<String> myBag = new ArrayBag<>();

        myBag.add("HELLO");
        myBag.add("GOODBYE");

        System.out.printf("My bag as an array: %s%n", myBag);

        String[] arrayToBag = {"GOODBYE", "HELLO"};

        System.out.printf("Making following array into a new bag (%s). . .%n", Arrays.toString(arrayToBag));

        ArrayBag<String> myEqualsBag = new ArrayBag<>(arrayToBag);

        System.out.printf("The test bag as an array: %s%n", myEqualsBag);

        System.out.printf("My bag equals the test bag: %b%n", myBag.equals(myEqualsBag));

        myEqualsBag.add("GREETINGS");
        myEqualsBag.add("ADIEU");

        System.out.printf("New items added to the test bag.%n" +
                "The test bag now: %s%n", myEqualsBag);

        System.out.printf("My bag equals the test bag: %b%n", myBag.equals(myEqualsBag));

        System.out.printf("How many objects are in my bag? %d%n", myBag.getCurrentSize());

        myBag.add("THIS IS A NEW ENTRY");
        myBag.add("THIS IS ANOTHER NEW ENTRY");
        myBag.add("THIS SHOULD BE THE LAST ENTRY");

        System.out.printf("Added three entries.\nMy bag now: %s%n", myBag);

        System.out.printf("Can I add more entries? %b%n", myBag.add("THIS ENTRY SHOULD NOT EXIST"));

        System.out.printf("How many objects are in my bag? %d%n", myBag.getCurrentSize());

        System.out.printf("Removed entry: %s%n", myBag.remove());

        System.out.printf("Can I add more entries now? %b%n", myBag.add("I CAN ADD NOW"));

        System.out.printf("My bag now: %s%n", myBag);

        System.out.printf("\"GOODBYE\" removed? %b%n", myBag.remove("GOODBYE"));

        System.out.printf("\"GREETINGS\" removed? %b%n", myBag.remove("GREETINGS"));

        System.out.printf("My bag now: %s%n", myBag);

        System.out.printf("Frequency of \"HELLO\"? %d%n", myBag.getFrequencyOf("HELLO"));

        System.out.printf("Added \"HELLO\": %b%n", myBag.add("HELLO"));

        System.out.printf("My bag now: %s%n", myBag);

        System.out.printf("Frequency of \"HELLO\"? %d%n", myBag.getFrequencyOf("HELLO"));

        System.out.printf("Is the bag empty? %b%n", myBag.isEmpty());

        System.out.println("Clearing bag. . .");
        myBag.clear();

        System.out.printf("Is the bag empty now? %b%n", myBag.isEmpty());

        System.out.printf("My bag now: %s%n", myBag);

        System.out.printf("Added two entries: %b %b%n", myBag.add("OKAY"), myBag.add("NOT OKAY"));

        System.out.printf("Duplicated all entries in the bag? %b%n", myBag.duplicateAll());

        System.out.printf("My bag now: %s%n", myBag);

        System.out.println("Test Complete!");
    }
}
