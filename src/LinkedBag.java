import java.util.Arrays;

public class LinkedBag<T> implements BagInterface<T> {
    private int numberOfEntries = 0;
    Node<T> firstNode = null;


    public LinkedBag() {
    }

    public LinkedBag(T[] toBag) {
        for (T nextEntry : toBag) {
            add(nextEntry);
        }
    }

    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean add(T newEntry) {
        Node<T> newNode = new Node<>(newEntry);
        newNode.next = firstNode;
        firstNode = newNode;
        numberOfEntries++;
        return true;
    }

    @Override
    public T remove() {
        if(numberOfEntries == 0)
            return null;

        T toRemove = firstNode.entry;
        Node<T> oldNode = firstNode;
        firstNode = firstNode.next;
        oldNode.toNull();
        numberOfEntries--;

        return toRemove;
    }

    @Override
    public boolean remove(T anEntry) {
        boolean found = false;
        Node<T> currentNode = firstNode;

        for(int i = 0; i < numberOfEntries; i++) {
            if(currentNode.entry.equals(anEntry)) {
                found = true;
                break;
            }
            currentNode = currentNode.next;
        }

        if(found) {
            Node<T> oldNode = currentNode.next;
            currentNode.entry = currentNode.next.entry;
            currentNode.next = currentNode.next.next;
            oldNode.toNull();
            numberOfEntries--;
        }

        return found;
    }

    @Override
    public void clear() {
        while(numberOfEntries != 0) {
            remove();
        }
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int result = 0;
        Node<T> currentNode = firstNode;

        for(int i = 0; i < numberOfEntries; i++) {
            if (currentNode.entry.equals(anEntry)) {
                result++;
            }
            currentNode = currentNode.next;
        }

        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        Node<T> currentNode = firstNode;

        for(int i = 0; i < numberOfEntries; i++) {
            if (currentNode.entry.equals(anEntry)) {
                return true;
            }
            currentNode = currentNode.next;
        }

        return false;
    }

    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        Node<T> currentNode = firstNode;

        for (int i = 0; i < numberOfEntries; i++) {
            result[i] = currentNode.entry;
            currentNode = currentNode.next;
        }

        return result;
    }

    @Override
    public boolean duplicateAll() {
        Node<T> currentNode = firstNode;

        for(int i = 0; i < numberOfEntries; i++) {
            add(currentNode.entry);
            currentNode = currentNode.next;
            numberOfEntries--;
        }

        numberOfEntries *= 2;
        return true;
    }

    @Override
    public boolean equals(ArrayBag<T> testArrayBag) {
        return false;
    }

    private static class Node<E> {
        E entry;
        Node<E> next;

        Node (E entry) {
            this.entry = entry;
        }

        void toNull () {
            entry = null;
            next = null;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
