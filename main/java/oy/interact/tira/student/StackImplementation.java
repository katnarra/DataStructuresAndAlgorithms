package oy.interact.tira.student;

import oy.interact.tira.util.StackInterface;

public class StackImplementation<E> implements StackInterface<E> {

    private Object [] itemArray;
    private int latest_index = -1;
    private static final int DEFAULT_STACK_SIZE = 10;

    public StackImplementation() {
        itemArray = new Object[DEFAULT_STACK_SIZE];
    }

    public StackImplementation(int given_capacity) {
        itemArray = new Object[given_capacity];

    }
    
    private void reAllocate() {  
        int new_capacity = 2*itemArray.length;
        Object [] newArray = new Object[new_capacity];
        for (int i = 0; i <= itemArray.length; i++) {
            newArray[i] = itemArray[i];
        }
        itemArray = newArray;
    }

    @Override
    public int capacity() {
        return itemArray.length;
    }

    @Override 
    public void push(E element) throws OutOfMemoryError, NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        else if (size() == itemArray.length) {
            try {
                reAllocate();
            } catch (Exception OutOfMemoryError) {
                throw new OutOfMemoryError();
            }  
        }
        itemArray[++latest_index] = element;
    }

    @Override
    public E pop() throws IllegalStateException {
        if (latest_index == -1) {
            throw new IllegalStateException();
        }
        else {
            E popped_e = (E)itemArray[latest_index];
            itemArray[latest_index] = null;
            latest_index--;
            return popped_e;
        }
    }

    @Override
    public E peek() throws IllegalStateException {
        if (latest_index == -1) {
            throw new IllegalStateException();
        }
        return (E)itemArray[latest_index];
    }

    @Override
    public int size() {
        return latest_index +1;
    }

    @Override
    public boolean isEmpty() {
        if (latest_index == -1) {
            return true;
        } 
        else {
            return false;
        }
    }

    @Override
    public void clear() {
        Object [] new_itemArray = new Object[DEFAULT_STACK_SIZE];
        itemArray = new_itemArray;
        latest_index = -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i <= latest_index; i++) {
            builder.append(itemArray[i]);
            if (i < latest_index) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}

    
