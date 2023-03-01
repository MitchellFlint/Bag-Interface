import java.util.Arrays;

public class ArrayBag<T> implements BagInterface<T>{
    private T[] bag;
    private int numberOfEntities;
    private int bagSize = 1;

    public ArrayBag() {
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[])new Object[bagSize];
        bag = tempBag;
    }

    public ArrayBag(T[] bag) {
        bagSize = bag.length;
        numberOfEntities = bag.length;
        this.bag = Arrays.copyOf(bag, bag.length);
    }

    public boolean equals(ArrayBag<T> testArrayBag) {
        if(this.numberOfEntities != testArrayBag.getCurrentSize())
            return false;

        for(int i = 0; i < this.numberOfEntities; i++) {
            if(this.getFrequencyOf(bag[i]) != testArrayBag.getFrequencyOf(bag[i]))
                return false;
        }

        return true;
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
//        if(isFullArray()) return false;
        if (bagSize >= numberOfEntities) {
            expandBag();
        }

        bag[numberOfEntities] = newEntry;
        numberOfEntities++;
        return true;
    }

    private void expandBag() {
        bagSize *= 2;
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[])new Object[bagSize];

        int i = 0;
        for(T nextEntry : bag) {
            tempBag[i] = nextEntry;
            i++;
        }
        bag = tempBag;
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

    public boolean duplicateAll() {
        if(bag.length < 2 * numberOfEntities) return false;

        for(int i = 0; i < numberOfEntities; i++) {
            bag[i + numberOfEntities] = bag[i];
        }

        numberOfEntities *= 2;
        return true;
    }
}
