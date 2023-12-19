package oy.interact.tira.student;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;


import oy.interact.tira.util.Pair;

public class TreeNode<K extends Comparable<K>, V> {
    
    private K key;
    private V value;
    //nää vois myös olla
    //Pair<K,V> pair;
    //mutta ei molempia
    private TreeNode<K,V> rightChild, leftChild = null;
    private static int depth = 0;

    public TreeNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public static void setDepth(int d) {
        depth = d;
    }

    public static int getDepth() {
        return depth;
    }

    public TreeNode<K,V> getLeftChild() {
        return leftChild;
    }

    public TreeNode<K,V> getRightChild() {
        return rightChild;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public boolean insert(K key, V value, Comparator<K> comparator) {
        boolean result = false;
        if (this.value.equals(value)) {
            this.key = key;
            this.value = value;
            return result;
        }
        if (comparator.compare(key, this.key) <= 0){
            if (leftChild == null) {
                leftChild = new TreeNode<K,V>(key, value);
                depth++;
                result = true;
            }
            else {
                depth++;
                result = leftChild.insert(key, value, comparator);
            }
        }
        else {
            if (rightChild == null) {
                rightChild = new TreeNode<K,V>(key, value);
                depth++;
                result = true;
            }
            else {
                depth++;
                result = rightChild.insert(key, value, comparator);
            }
        }
        return result;
    }

    public V find(K key, Comparator <K> comparator) {
        V result = null;
        if (this.key.equals(key)) {
            result = value;
        }
        else if (comparator.compare(key, this.key) <= 0) {
            if (leftChild != null) {
                result = leftChild.find(key, comparator);
            }
        }
        else {
            if (rightChild != null) {
                result = rightChild.find(key, comparator);
            }
        }
        return result;
    }

    public void toArrayNode(Pair<K,V>[] array, AtomicInteger currentIndex) {
        if (leftChild != null) {
            leftChild.toArrayNode(array, currentIndex);
        }
        array[currentIndex.getAndIncrement()] = new Pair<K,V>(key, value);
        if (rightChild != null) {
            rightChild.toArrayNode(array, currentIndex);
        }
    }
}
