package oy.interact.tira.student;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;

public class BinarySearchTreeContainer<K extends Comparable<K>, V> implements TIRAKeyedOrderedContainer<K, V> {

	TreeNode<K, V> root;  // Root node of the tree, your private little helper class.
	private int size;		// Number of elements currently in the tree.
    private int maxDepth;       //statistiikkaa raporttia varten

	private Comparator<K> comparator;  // The comparator used to determine if new node will go to left or right subtree.

	public BinarySearchTreeContainer(Comparator<K> comparator) {
		this.comparator = comparator;
	}

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (root == null) {
            root = new TreeNode<K,V>(key, value);
            size++;
            maxDepth = 1;
        }
        else {
            //TreeNode.setDepth(1);
            if (root.insert(key, value, comparator)) {
                //maxDepth = java.lang.Math.max(maxDepth, TreeNode.getDepth());
                size++;
            }
        }
    }

    @Override
	public int indexOf(K itemKey) {
		if (root == null) {
            return -1;
        }
        int index = 0;
        StackImplementation<TreeNode<K,V>> nodeStack = new StackImplementation<>();
        TreeNode<K,V> current = root;
        TreeNode<K,V> parent = null;
        while (!nodeStack.isEmpty() || current != null) {
            if (current != null) {
                nodeStack.push(current);
                parent = current;
                current = current.getLeftChild();
            }
            else {
                parent = nodeStack.pop();
                current = parent.getRightChild();
                if(parent.getKey().equals(itemKey)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
	}

    @Override
    public V get(K key) throws IllegalArgumentException {
        if (root == null) {
            return null;
        }
        return root.find(key, comparator);
    }

    @Override
	public int size() {
		return size;
	}

    @Override
        public int capacity() {
            return Integer.MAX_VALUE;
        }

	@Override
	public V find(Predicate<V> searcher) {
        if (root == null) {
            return null;
        }
        StackImplementation<TreeNode<K,V>> nodeStack = new StackImplementation<>();
        TreeNode<K,V> current = root;
        TreeNode<K,V> parent = null;
        while (!nodeStack.isEmpty() || current != null) {
            if (current != null) {
                nodeStack.push(current);
                parent = current;
                current = current.getLeftChild();
            }
            else {
                parent = nodeStack.pop();
                current = parent.getRightChild();
                if(searcher.test(parent.getValue())) {
                    return parent.getValue();
                }
            }
        }
        return null;
	}

	@Override
	public void clear() {
        //PAKOLLINEN
        root = null;
        size = 0;
	}

	@Override
	public Pair<K, V>[] toArray() throws Exception {
		//muodostetaan taulukko
        //PAKOLLINEN
        if (root.getLeftChild() == null) {
            return null;
        }
        Pair<K, V>[] array = (Pair<K, V>[]) new Pair[size];
        AtomicInteger arrayIndex = new AtomicInteger(0);
        root.toArrayNode(array, arrayIndex);
		return array;
	}

	@Override
	public Pair<K, V> getIndex(int index) throws IndexOutOfBoundsException {
		// PAKOLLINEN
        //Retrieves the key-value pair at the index.
        if (root == null) {
            return null;
        }
        StackImplementation<TreeNode<K,V>> nodeStack = new StackImplementation<>();
        TreeNode<K,V> current = root;
        TreeNode<K,V> parent = null;
        int currentIndex = 0;
        while (!nodeStack.isEmpty() || current != null) {
            if (current != null) {
                nodeStack.push(current);
                parent = current;
                current = current.getLeftChild();
            }
            else {
                parent = nodeStack.pop();
                current = parent.getRightChild();
                Pair<K,V> pair = new Pair<>(parent.getKey(), parent.getValue());
                if(currentIndex == index) {
                    return pair;
                }
                currentIndex++;
            }
        }
        return null;
        //throw new IndexOutOfBoundsException("Index out of bounds: " + index);
	}

	@Override
	public int findIndex(Predicate<V> searcher) {
		// PAKOLLINEN
        //Finds an index for the value conforming to the predicate.
        if (root == null) {
            return -1;
        }
        int index = 0;
        StackImplementation<TreeNode<K,V>> nodeStack = new StackImplementation<>();
        TreeNode<K,V> current = root;
        TreeNode<K,V> parent = null;
        while (!nodeStack.isEmpty() || current != null) {
            if (current != null) {
                nodeStack.push(current);
                parent = current;
                current = current.getLeftChild();
            }
            else {
                parent = nodeStack.pop();
                current = parent.getRightChild();
                if(searcher.test(parent.getValue())) {
                    return index;
                }
                index++;
            }
        }
        return -1;
	}

    @Override
    public V remove(K key) throws IllegalArgumentException {
        //valinnainen
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void accept(Visitor<K, V> visitor) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }

    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'ensureCapacity'");
    }

    
}