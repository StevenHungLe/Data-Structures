 /** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      UnboundedPriorityQueue - a generic UnboundedPriorityQueue
 **/
 public class UnboundedPriorityQueue<T extends Comparable<? super T>>
 {
     private MinHeap<T> minHeap; // the minHeap - the core of this priority queue's funtionalities

     // default constructor, create a priority queue of an initial capacity of 10
     public UnboundedPriorityQueue()
     {
          minHeap = new MinHeap<T>();
     }

     // constructor that takes a parameter as the initial capacity of the priority queue
     public UnboundedPriorityQueue(int capacity)
     {
          minHeap = new MinHeap<T>(capacity);
     }

     /**
	 *  method to enqueue an item into the priority queue
	 *  @param T item : the item to be enqueued
	 */
     public void enqueue(T item)
     {
          minHeap.insert(item);
     }

     /**
	 *  method to dequeue an item from the priority queue
	 *  @return T the minimum value in the priority queue
	 */
     public T dequeue()
     {
          return minHeap.remove();
     }

     /**
	 * method to determine if the priority queue is empty
	 * @return true if the priority queue is empty, otherwise false
	 */
     public boolean isEmpty()
     {
          return minHeap.isEmpty();
     }

     /**
	 *  method to return a string representation of the internal array of the priority queue
	 */
	 @Override
	 public String toString()
	 {  
	     return minHeap.toString();
	 }

	 /**
	  * method to determine if the priority queue is full
	  * @return true if the priority queue is full, otherwise false
	  */
	 public boolean isFull()
	 {
	     return minHeap.isFull();
	 }

	 /**
	  * method that returns the size, aka number of elements in the priority queue
	  * @return the number of elements in the priority queue
	  */
	 public int size()
	 {
	     return minHeap.size();
	 }

	 /**
	 * method that returns the current capacity of the priority queue 
	 * @return the capacity of the priority queue
	 */
	 public int capacity()
	 {
	     return minHeap.capacity(); // capacity = heap.length - one of the extra sentinel
	 }
     
 } // End of UnboundedPriorityQueue class
