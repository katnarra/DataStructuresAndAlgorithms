package oy.interact.tira.student;

import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;

public class HashTableContainer<K extends Comparable<K>,V> implements TIRAKeyedContainer<K,V> {

    private int size;
    Pair<K,V>[] array;
    private static final int DEFAULT_CAPACITY = 20;
    private static double LOAD_FACTOR = 0.65;

    //tilastointiin

    private int collisionCount;
    private int maxProbingCount;
    private int pairsUpdated;

    public HashTableContainer() {
        array = new Pair[DEFAULT_CAPACITY];
    }

    private void reAllocate(int newCapacity) {
        Pair<K,V>[] oldArray = array;
        array = new Pair[newCapacity];
        collisionCount = 0;
        maxProbingCount = 0;
        size = 0;
        for (int index = 0; index < oldArray.length; index++) {
            if (oldArray[index] != null) {
                add(oldArray[index].getKey(), oldArray[index].getValue());
            }
        }
    }

    private int indexFor(int hash, int hashModifier) {
        int c1 = 1;
        int c2 = 3;
        hash = ((hash + c1 * hashModifier + c2 * hashModifier * hashModifier) & 0x7FFFFFFF) % array.length;
        return hash;
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        if (size >= array.length * LOAD_FACTOR) {
            reAllocate((int) (array.length / LOAD_FACTOR));
        }
        int index = 0;
        int hashModifier = 0;
        int currentProbingCount = 0;
        boolean added = false;
        int hash = key.hashCode();
        do {
            index = indexFor(hash, hashModifier);
            if (array[index] == null) {
                array[index] = new Pair<K,V>(key, value);
                added = true;
                size++;
            }
            else if (array[index].getKey().equals(key)) {
                array[index] = new Pair<K,V>(key, value);
                pairsUpdated++;
                added = true;
            }
            else {
                hashModifier++;
                collisionCount++;
                currentProbingCount++;
            }
        }
        while (!added);
        maxProbingCount = Math.max(maxProbingCount, currentProbingCount);
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        V result = null;
        int index = 0;
        int hash = key.hashCode();
        int hashModifier = 0;
        boolean found = false;
        do {
            index = indexFor(hash, hashModifier);
            if (array[index] != null) {
                if (array[index].getKey().equals(key)) {
                result = array[index].getValue();
                found = true;
                }
                else {
                    hashModifier++;
                }
            } 
            else {
                found = true;
            }
        }
        while (!found);
        return result;
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        //valinnainen
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public V find(Predicate<V> searcher) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                if (searcher.test(array[i].getValue())) {
                    return array[i].getValue();
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
        if (size == 0) {
            array = null;
            array = new Pair[capacity];
        }
        else {
            reAllocate(capacity);
        }
    }

    @Override
    public void clear() {
        size = 0;
        array = new Pair[DEFAULT_CAPACITY];
        collisionCount = 0;
        pairsUpdated = 0;
        maxProbingCount = 0;
    }

    @Override
    public Pair<K, V>[] toArray() throws Exception {
        Pair<K, V>[] newArray = (Pair<K, V>[]) new Pair[size];
        int tempIndex = 0;
        for (int index = 0; index < array.length; index++) {
            if (array[index] != null) {
                newArray[tempIndex] = new Pair<K,V>(array[index].getKey(), array[index].getValue());
                tempIndex++;
            }
        }
        return newArray;
    }
    
}
