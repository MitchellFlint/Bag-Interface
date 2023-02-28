import java.util.ArrayList;
import java.util.Arrays;

public class TestBags {
    public static void main(String[] args) {
        ArrayBag<String> myBagA = new ArrayBag<>();

        myBagA.add("HELLO");

        Object[] bagArray = myBagA.toArray();

        System.out.println(bagArray);
    }
}
