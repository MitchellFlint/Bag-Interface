import java.util.Arrays;

/**
 * This is a resizable implementation of the {@code BagInterface} ADT. This
 * means that it has the ability to hold elements of a specific type, without
 * order, and the user is able to access these elements.
 *
 * @param <T> The type of the entries in the bag.
 * @author Mitchell Flint
 */
public class ArrayBag<T> implements BagInterface<T> {
    private T[] bag;
    private int numberOfEntries;
    private int bagSize = 1;
    private final boolean integrity;
    private final int MAX_CAPACITY = 10000;


    /**
     * Constructs an empty bag.
     */
    public ArrayBag() {
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[]) new Object[bagSize];
        bag = tempBag;
        integrity = true;
    }


    /**
     * Constructs a bag containing the values of an array.
     *
     * @param toBag The array whose elements are copies.
     */
    public ArrayBag(T[] toBag) {
        if(toBag.length > MAX_CAPACITY)
            throw new IllegalStateException("Tried to create a bag with a capacity too large. please keep capacity below 10,000");

        bagSize = toBag.length;
        numberOfEntries = toBag.length;
        this.bag = Arrays.copyOf(toBag, toBag.length);
        integrity = true;
    }

    /**
     * Compares that two bags have the same frequency of each entry.
     *
     * @param testArrayBag The bag that will be tested against this one.
     * @return Whether the bags are equal.
     */
    @Override
    public boolean equals(BagInterface<T> testArrayBag) {
        if (this.getCurrentSize() != testArrayBag.getCurrentSize())
            return false;

        for (int i = 0; i < this.numberOfEntries; i++) {
            if (this.getFrequencyOf(bag[i]) != testArrayBag.getFrequencyOf(bag[i]))
                return false;
        }

        return true;
    }


    /**
     * Returns the number of entries in the bag.
     *
     * @return Number of entries.
     */
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }


    /**
     * Detects if the bag is empty.
     *
     * @return Whether the bag is empty.
     */
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }


    /**
     * Adds a new entry into the bag.
     *
     * @param newEntry The object to be added as a new entry.
     * @return Whether a new entry was added.
     */
    @Override
    public boolean add(T newEntry) {
        checkIntegrity();

        if(!canExpand())
            return false;

        if (bagSize >= numberOfEntries) {
            expandBag();
        }

        bag[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }


    /**
     * Removes an entry from the bag.
     *
     * @return The entry removed from the bag.
     */
    @Override
    public T remove() {
        checkIntegrity();

        if (numberOfEntries <= 0) return null;
        T removedEntity = bag[numberOfEntries - 1];
        remove(removedEntity);
        return removedEntity;
    }


    /**
     * Removes a specified entry from the bag, checking if the entry was
     * even in the bag in the first place.
     *
     * @param anEntry The entry the user desires to rid of.
     * @return Whether the entry could be removed or not.
     */
    @Override
    public boolean remove(T anEntry) {
        checkIntegrity();

        if (!contains(anEntry)) return false;

        bag[findLastIndex(anEntry)] = bag[numberOfEntries - 1];
        bag[numberOfEntries] = null;
        numberOfEntries--;

        return true;
    }


    /**
     * Clears all entries from the bag.
     */
    @Override
    public void clear() {
        while(numberOfEntries > 0)
            remove();
    }


    /**
     * Gets the frequency of a specified entry in the bag.
     *
     * @param anEntry The entry to test the frequency of.
     * @return The frequency of the entry.
     */
    @Override
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;

        for (T nextEntry : bag)
            if (nextEntry == anEntry) frequency++;

        return frequency;
    }


    /**
     * Checks if the bag contains a specified entry.
     *
     * @param anEntry The entry to find.
     * @return Whether the bag contains the entry.
     */
    @Override
    public boolean contains(T anEntry) {
        for (T nextEntry : bag)
            if (nextEntry == anEntry) return true;

        return false;
    }


    /**
     * Returns the bag as an unordered array.
     *
     * @return An array of type {@code T[]}.
     */
    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] asArray = (T[]) new Object[numberOfEntries];

        System.arraycopy(bag, 0, asArray, 0, numberOfEntries);

        return asArray;
    }


    /**
     * Returns the bag as a String of the array.
     *
     * @return A string displaying all entries in the bag.
     */
    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }


    /**
     * Duplicates all entries in the bag.
     *
     * @return Whether the bag was successfully duplicated.
     */
    @Override
    public boolean duplicateAll() {
        checkIntegrity();

        if (bag.length < 2 * numberOfEntries) return false;

        for (int i = 0; i < numberOfEntries; i++) {
            bag[i + numberOfEntries] = bag[i];
        }

        numberOfEntries *= 2;
        return true;
    }

    /**
     * Doubles the size of the array that the bag is stored in.
     */
    private void expandBag() {
        bagSize *= 2;
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[]) new Object[bagSize];

        int i = 0;
        for (T nextEntry : bag) {
            tempBag[i] = nextEntry;
            i++;
        }
        bag = tempBag;
    }

    private boolean canExpand() {
        return bagSize * 2 <= MAX_CAPACITY;
    }

    /**
     * Finds the last index of a specified entry.
     *
     * @param anEntry The entry you wish to find the index of.
     * @return the last index of the specified entry.
     */
    private int findLastIndex(Object anEntry) {
        boolean objectFound = false;
        int objectIndex = 0;

        for (int i = numberOfEntries - 1; i >= 0 && !objectFound; i--) {
            if (bag[i] == anEntry) {
                objectIndex = i;
                objectFound = true;
            }
        }

        return objectIndex;
    }

    private void checkIntegrity() {
        if (!integrity)
            throw new SecurityException("ArrayBag object is corrupt");
    }
}
