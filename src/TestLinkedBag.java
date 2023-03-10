import java.util.Arrays;

public class TestLinkedBag {
    public static void main(String[] args) {
        LinkedBag<String> myBag = new LinkedBag<>();

        myBag.add("HELLO");
        myBag.add("GOODBYE");

        System.out.println(Arrays.toString(myBag.toArray()));

        System.out.println(myBag.remove());

        System.out.println(Arrays.toString(myBag.toArray()));

        myBag.add("GREETINGS");
        myBag.add("ADIEU");
        myBag.add("GOODBYE");

        System.out.println(Arrays.toString(myBag.toArray()));

        System.out.println(myBag.remove("ADIEU"));

        System.out.println(Arrays.toString(myBag.toArray()));
    }
}
