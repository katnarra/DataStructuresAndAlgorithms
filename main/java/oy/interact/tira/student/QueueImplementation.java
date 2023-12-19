package oy.interact.tira.student;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import oy.interact.tira.util.QueueInterface;

public class QueueImplementation<E> implements QueueInterface<E> {
    
    private Object [] array;
    private int tail;
    private int head;
    private int size = 0;
    private static final int DEFAULT_ARRAY_SIZE = 10;

    public QueueImplementation() {
        array = new Object[DEFAULT_ARRAY_SIZE];
    }

    public QueueImplementation(int capacity) {
        array = new Object[capacity];
    }

    private void reAllocate() {  
        int new_capacity = 2*array.length;
        Object [] newArray = new Object[new_capacity];
        for (int i = 0; i <= array.length-1; i++) {
            if (head == array.length) {
                head = 0;
            }
            newArray[i] = array[head];
            head++;
        }
        tail = array.length;
        head = 0;
        array = newArray;
    }

    @Override
    public int capacity() {
        return array.length;
    }

    @Override
    public void enqueue(E element) throws OutOfMemoryError, NullPointerException {
        if (element == null) {
        throw new NullPointerException();
        }
        if (head == tail && tail != 0) {
            try {
                reAllocate();
            } catch (Exception OutOfMemoryError) {
                throw new OutOfMemoryError();
            }  
        }
        else if (tail == array.length) {
            if (head == 0) {
               try {
                reAllocate();
            } catch (Exception OutOfMemoryError) {
                throw new OutOfMemoryError();
                } 
            }
            else {
                tail = 0;
            } 
        }
        array[tail] = element;
        tail++; 
        size++;
    }

    @Override
    public E dequeue() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        else {
            E deque_e = (E)array[head];
            array[head] = null;
            head++;
            size--;
            return deque_e;
        }
    }


    @Override
    public E element() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        if (head == array.length) {
            head = 0;
        }
        E element = (E)array[head];
        return element;
    }

    @Override
    public int size() {
        return size; 
    }


    @Override
    public boolean isEmpty() {
        if (size() > 0) {
            return false;
        }
        else {
            return true;
        }
    }


    @Override
    public void clear() {
        Object [] new_itemArray = new Object[DEFAULT_ARRAY_SIZE];
        array = new_itemArray;
        head = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (head <= tail) {
            for (int i = head; i < tail; i++) {
                builder.append(array[i]);
                if (i < tail -1) {
                    builder.append(", ");
                }
            }
        }
        else if (tail < head) {
            for (int j = head; j < capacity(); j++) {
                builder.append(array[j]);
                if (j < capacity() +1) {
                    builder.append(", ");
                }
            }
            for (int k = 0; k < tail; k++) {
                builder.append(array[k]);
                if (k < tail -1) {
                    builder.append(", ");
                }
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
