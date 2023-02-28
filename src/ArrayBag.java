import java.util.Arrays;

public class ArrayBag<T> implements BagInterface<T>{
    private final T[] bag;
    private int numberOfEntities;
    private final int BAG_SIZE;

    public ArrayBag() {
        BAG_SIZE = 25;
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[])new Object[BAG_SIZE];
        bag = tempBag;
    }

    public ArrayBag(int bagSize) {
        BAG_SIZE = bagSize;
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[])new Object[BAG_SIZE];
        bag = tempBag;
    }

    public ArrayBag(T[] bag) {
        BAG_SIZE = bag.length;
        this.bag = Arrays.copyOf(bag, bag.length);
    }

    public ArrayBag(T[] bag, int bagSize) {
        BAG_SIZE = bagSize;
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[])new Object[BAG_SIZE];
        this.bag = tempBag;
        for(int i = 0; i < bag.length && i < BAG_SIZE; i++) {
            this.bag[i] = bag[i];
        }
    }

    @Override
    public int getCurrentSize() {
        return numberOfEntities;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntities == 0;
    }

    @Override
    public boolean add(T newEntry) {
        if(isFullArray()) return false;

        bag[numberOfEntities] = newEntry;
        numberOfEntities++;
        return true;
    }

    private boolean isFullArray() {
        return numberOfEntities >= BAG_SIZE;
    }

    @Override
    public T remove() {
        T removedEntity = bag[numberOfEntities - 1];
        remove(removedEntity);
        return removedEntity;
    }

    // TODO make sure to actually remove the entity, this is lazy
    @Override
    public boolean remove(T anEntry) {
        if(!contains(anEntry)) return false;

        bag[findLastIndex(anEntry)] = bag[numberOfEntities - 1];
        numberOfEntities--;

        return true;
    }

    private int findLastIndex(Object anEntry) {
        boolean objectFound = false;
        int objectIndex = 0;

        for(int i = numberOfEntities - 1; i >= 0 && !objectFound; i--) {
            if(bag[i] == anEntry) {
                objectIndex = i;
                objectFound = true;
            }
        }

        return objectIndex;
    }

    // TODO make sure to actually remove the entities, this is lazy
    @Override
    public void clear() {
        numberOfEntities = 0;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;

        for(int i = 0; i < numberOfEntities; i++) {
            if(bag[i] == anEntry) frequency++;
        }
        return frequency;
    }

    @Override
    public boolean contains(T anEntry) {
        return getFrequencyOf(anEntry) > 0;
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(bag, numberOfEntities);
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
