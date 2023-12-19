package oy.interact.tira.student;

import java.util.Comparator;

public class Algorithms {

   private Algorithms() {
      // nada
   }

   ///////////////////////////////////////////
   // Insertion Sort for the whole array
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> void insertionSort(T[] array) {
      // Tehty
         insertionSort(array, 0, array.length);
   }

   ///////////////////////////////////////////
   // Insertion Sort for a slice of the array
   ///////////////////////////////////////////

   //generic method to swap indexes

   public static <T> void swap(T[] array, int first, int second) {
      T temp = array[first];
      array[first] = array[second];
      array[second] = temp;
   }

   public static <T extends Comparable<T>> void insertionSort(T[] array, int fromIndex, int toIndex) {
      // Tehty
      for (fromIndex = 1; fromIndex < toIndex; fromIndex++) {
         int key = fromIndex;
         if (array[key] != null) {
            while (key > 0 && array[key - 1].compareTo(array[key]) > 0) {
            swap(array, key, key - 1);
            key--;
            }
         }  
      }
   }

   //////////////////////////////////////////////////////////
   // Insertion Sort for the whole array using a Comparator
   //////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, Comparator<T> comparator) {
      insertionSort(array, 0, array.length, comparator);
   }

   ////////////////////////////////////////////////////////////
   // Insertion Sort for slice of the array using a Comparator
   ////////////////////////////////////////////////////////////

   public static <T> void insertionSort(T[] array, int fromIndex, int toIndex, Comparator<T> comparator) {
      for (fromIndex = 1; fromIndex < toIndex; fromIndex++) {
         int key = fromIndex;
         if (array[key] != null) {
            while (key > 0 && comparator.compare(array[key-1], array[key]) > 0) {
            swap(array, key, key-1);
            key--;
            }
         }
          
      }
   }

   ///////////////////////////////////////////
   // Reversing an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array) {
      reverse(array, 0, array.length);
   }

   ///////////////////////////////////////////
   // Reversing a slice of an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array, int fromIndex, int toIndex) {
      int i;
      for (i = fromIndex; i < toIndex / 2; i++) {
         if (array[i] != null) {
            swap(array, i, toIndex-i-1);
         }
      }

   }




   ///////////////////////////////////////////
   // Binary search bw indices
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
      toIndex--;
      while (fromIndex <= toIndex) {
         int middle = fromIndex + (toIndex - fromIndex) / 2;
         if (aValue.compareTo(fromArray[middle]) < 0) {
            toIndex = middle - 1;
         }
         else if (aValue.compareTo(fromArray[middle]) > 0) {
            fromIndex = middle + 1;
         }
         else if (aValue.compareTo(fromArray[middle]) == 0) {
            return middle;
         }
      }
      return -1;
   }

   ///////////////////////////////////////////
   // Binary search using a Comparator
   ///////////////////////////////////////////

   public static <T> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator) {
      toIndex--;
      while (fromIndex <= toIndex) {
         int middle = fromIndex + (toIndex - fromIndex) / 2;
            if (comparator.compare(aValue, fromArray[middle]) < 0) {
               toIndex = middle - 1;
            }
            else if (comparator.compare(aValue, fromArray[middle]) > 0) {
               fromIndex = middle + 1;
            }
            else if (comparator.compare(aValue, fromArray[middle]) == 0) {
               return middle;
            }
         }
      return -1;
   }

   public static <E extends Comparable<E>> void fastSort(E [] array) {
      quicksortComparable(array, 0, array.length-1);
   }

   public static <E extends Comparable<E>> void fastSort(E [] array, int fromIndex, int toIndex) {
      quicksortComparable(array, fromIndex, toIndex-1);
   }

   public static <E> void fastSort(E [] array, Comparator<E> comparator) {
      quicksortComparator(array, 0, array.length-1, comparator);
   }

   public static <E> void fastSort(E [] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      quicksortComparator(array, fromIndex, toIndex-1, comparator);
   }

   private static <E extends Comparable<E>> void quicksortComparable(E [] array, int low, int high) {
      if (low < high) {
         int part_index = partitionComparable(array, low, high);
         quicksortComparable(array, low, part_index-1);
         quicksortComparable(array, part_index+1, high);
      }
   }

   private static <E> void quicksortComparator(E [] array, int low, int high, Comparator<E> comparator) {
      if (low < high) {
         int part_index = partitionComparator(array, low, high, comparator);
         quicksortComparator(array, low, part_index-1, comparator);
         quicksortComparator(array, part_index+1, high, comparator);
      }
   }

   private static <E extends Comparable<E>> int partitionComparable(E[] array, int low, int high) {
      E pivot = array[high];
      int i = low-1;
      for (int j = low; j <= high; j++) {
         if (array[j].compareTo(pivot) < 0) {
            i++;
            swap((E[])array, i, j);
         }
      }
   swap(array, i+1, high);
   return (i+1);
   }

   private static <E> int partitionComparator(E[] array, int low, int high, Comparator<E> comparator) {
      E pivot = array[high];
      int i = low -1;
      for (int j = low; j <= high; j++) {
         if (comparator.compare(array[j], pivot) < 0) {
            i++;
            swap((E[])array, i, j); 
         }
      }
   swap(array, i+1, high);
   return (i+1);
   }
   
}
