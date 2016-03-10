/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      MaxHeap - a generic MaxHeap
 **/

import java.lang.Comparable;

public class MaxHeap<T extends Comparable<? super T>>
{
     // Attributes
     T[] heap;           // the fixed size array to store the elements of the heap
     int currentSize;    // the variable to keep track of the size of the heap

     
     // default constructor, in case no parameter is passed
     @SuppressWarnings("unchecked")
     public MaxHeap()
     {
          heap = (T[]) new Comparable[11]; // The actual maximum size of the heap is 10, see ReadMe.txt for explanation
          currentSize = 0;
	}

	// constructor that takes a parameter as the size of the heap
	@SuppressWarnings("unchecked")
     public MaxHeap(int size)
     {
          heap = (T[]) new Comparable[size+1]; // The actual maximum size of the heap is <size>, see ReadMe.txt for explanation
          currentSize = 0;
	}


     /**
	 *  method to insert a value into the heap
	 *  @param  value : the value to be inserted 
	 */
     public void insert( T value )
     {
          if (currentSize == heap.length-1) // throws exception if the heap is already full
               throw new IndexOutOfBoundsException("\nThe heap is full!");

          // create a "hole" at the next available index in the array and increment the heap size, 
          // this hole will hold the value to be inserted at the end of the insertion    
          int hole = ++currentSize; 

          /**
           * Insert Algorithm:
           * Place the value to be inserted at index 0 as a sentinel to break the loop in case the "hole" reach the root ( index 1 )
           * The loop will go on as long as the value to be inserted is greater compared to the "hole"'s parent
           * update statement: update the "hole" to the index of its parent
           * inside the loop : push the item at the parent's index down to the "hole"
           * after the loop  : store the value to be inserted at the index of the "hole"  
           * Algorithm summary: the "hole" starts off at the end of the heap and percolates up until it finds its place to belong
           **/
          for ( heap[0] = value; value.compareTo(heap[hole/2])>0; hole /= 2)
               heap[hole] = heap[hole/2];
          heap[hole] = value;
     }

     /**
	 * method to remove a value from the tree
      * @return the maximum value in the heap which is located at the root
	 */
	 
     public T remove()
     {
          if (isEmpty()) // throws exception if the value to remove is not in the tree
               throw new IndexOutOfBoundsException("\nThe heap is empty!");

          T maxItem = heap[1]; // save the maximum value at the root to a variable

          /**
           * Remove Algorithm:
           * assign the item at the end of the heap to the root
           * percolate this item down until it finds its place to belong
           *
           * Percolate Down Algorithm:
           * create a "hole" starting off from thr root
           * the loop goes on as long as at least one child of the "hole" exists
           * inside the loop : push the value of the greater child of the "hole" up if its value is greater than that of the "hole"
           * update statement: update the "hole" to the index of the child, i.e. percolates it down
           **/
          heap[1] = heap[currentSize--]; // push the value at the end of the heap up to the root so it can percolate down later

          int hole = 1;    // the index of the "hole"
          int child;       // the index of the child to swap value with the "hole"
          T tmp = heap[1]; // the value to be saved at the "hole" after it percolated down

          for (; hole*2 <= currentSize; hole = child )
          {
               // check if the value at the right child is greater than that of the left child; if so, update the "child" index to the right child
               child = hole*2;
               if ( child != currentSize && heap[child+1].compareTo(heap[child])>0 ) 
                    child++;

               // check if the value at the child is greater than that of the "hole"; if so, push it up to the index of the "hole" 
               if ( heap[child].compareTo(tmp)>0 )
                    heap[hole] = heap[child];
                    
               else // otherwise, if the child's value is not greater than the "hole"'s value, then the "hole" has found its place to belong
                    break;
          }
          heap[hole] = tmp; // assign the saved value to the hole
          
          return maxItem;   // return the maximum value that is saved earlier
     }

     
     /**
	 *  method to return a string representation of the internal array of the heap
	 */
	 @Override
	 public String toString()
	 {
	     String toReturn = "";

          if ( !isEmpty() )   // if the heap is not empty
          {
	          for ( int i = 1; i <= currentSize ; i++ )
	               toReturn += "["+heap[i].toString()+"]";
	     }
	     else                // if the heap is empty
	          toReturn = "The heap is empty!";
	          
	     return toReturn;
	 }


     /**
	 * method to determine if the heap is empty
	 * @return true if the heap is empty, otherwise false
	 */
	 public boolean isEmpty()
	 {
	     return currentSize == 0;
	 }
	
} // end of MaxHeap class
