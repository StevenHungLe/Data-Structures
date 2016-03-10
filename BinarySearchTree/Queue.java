/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      Queue - a generic queue ADT
 **/
import java.util.ArrayList;

public class Queue<T>{

	     private ArrayList <T> list; // the ArrayList to store the queue's data

          // Constructor
	     public Queue()
	     {
		     this.list = new ArrayList<T>();
	     }
          /** 
           * enqueue - add an item to the end of the stack
           * @Param : T item - the item to be added
           **/
	     public void enqueue ( T item )
	     {
		     this.list.add(item);	
	     }

	     /**
	      * dequeue - retrieve and remove the item from the front of the queue
	      * @ensure returns T or null;
	      */
	     public T dequeue()
	     {
		     if (this.list.size() == 0)
			     return null;
		     T temp = this.list.get(0);
		     this.list.remove(0);
		     return temp;
	     }

          /** 
           * peek - retrieve, but not remove, the item at the front of the queue
           * @ensure returns T or null;
           **/
	     public T peek()
	     {
		     if (this.list.size() == 0)
			     return null;
		     return this.list.get(0);
	     }

	     /** 
           * size - return the size of the queue
           **/
	     public int size()
	     {
		     return this.list.size();	
	     }
     } // end of inner class Queue<T>
