/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      SinglyLinkedList - a generic one-way linked list
 **/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<Element> implements Iterable<Element>
{
	//Instance variables
	int size;                // the number of elements in the list
	Node<Element> head;      // the head of the list
	Node<Element> tail;      // the tail of the list
	Node<Element> current;   // used to point to the current nodes involved in insert/remove and other operations
	
	// Constructors
    	public SinglyLinkedList() 
    	{
        this.size = 0;
        this.head = null;
        this.tail = null;
        this.current = null;
    	}


    	//*** Queries Methods ***\\
    	

	/**
	 *  Method: size
	 *  Return: the number of elements in the linked list.
	 */
    	public int size() 
    	{
        	return this.size;
    	}

    	/**
    	 *  Method: isEmpty
	 *  Return: whether the list is empty or not.
	 *  If the head is null, then the list is empty; otherwise it's not
	 */
    	public boolean isEmpty()
    	{
    	     return this.head == null;
    	}

    	
     /**
      *  Method: getNode
	 *  Return: Node<Element> - the node at the provided index 
	 *  @param: int index - the index of the node to be retrieved
	 *  @ensure invalid index is thrown as an exception
	 */
	public Node<Element> getNode(int index)
	{
	     if ( index < 0 || index >= this.size )
	          throw new IndexOutOfBoundsException("Invalid index!");
	          
		int count=0;
		resetCurrent(); // reset the current pointer to the head
		while (count < index )
		{
			this.next();
			count++;
		}
		return this.current;
	}

	
     /**
      *  Method: getNthFromFirst
	 *  Return: the nth value from the first,a.k.a. the head
	 *  @Param: int n - the index of the element to be retrieved, counting from the first node
	 *  @ensure invalid index is thrown as an exception
	 */
     public Element getNthFromFirst( int n )
     {
          if ( n < 0 || n >= this.size )
	          throw new IndexOutOfBoundsException("Invalid index!");
	     else
               return this.getNode(n).getData();
     }

     /**
      *  Method: getNthFromLast
	 *  Return: the nth value from the last,a.k.a. the tail
	 *  @Param: int n - the index of the element to be retrieved, counting from the last node
	 *  @ensure invalid index is thrown as an exception
	 */
     public Element getNthFromLast( int n )
     {
          if ( n < 0 || n >= this.size )
	          throw new IndexOutOfBoundsException("Invalid index!");
	     else
               return this.getNode((this.size-1)-n).getData(); // this.size-1 is the index of the last element, 
                                                               // then (this.size-1)-n is the index of the nth element from the last element
     }

     /**
      * Method: toString - Generate and return a string representation of the list
      * Return: the string representation of the list
      **/
	public String toString()
	{
	     String dataString = "[ "; // beginning of the string

	     resetCurrent(); //make sure the current pointer is set to the head
	     
	     for ( Element element : this)
	     {
	          dataString = dataString.concat(element+" "); // concatenate the elements to the string
	     }
	     
	     dataString = dataString.concat("]"); // end of the string
	     
		return dataString;
	}

	/**
	 *  Method: iterator
	 *  Return: SinglyLinkedListIterator - the iterator of the linked list
	 */
     public SinglyLinkedListIterator iterator()
     {
        return new SinglyLinkedListIterator();
     }


	//*** Commands Methods ***\\
	
    	
    	/**
    	 * Method: next
	 * Updates the current pointer to point to the next node in the list
	 * @require this.size() != 0 
	 */ 
	public void next()
	{
		if ( this.current != null)
			this.current = this.current.getNext();
	}

     /**
      *  Method:  add
	 *  Adds a new element at the end of the list
	 *  @param:  Element item - the element to be added
	 *  @ensure: the list's size is properly updated
	 */
	public void add(Element item)
	{
		Node<Element> newNode = new Node<Element>(item);
		if ( this.size == 0 ) 			// edge case: if the list is empty
		{
			this.head = newNode;
			this.tail = newNode;
			this.current = newNode;
		}
		else 	                         // general case
		{
			this.tail.setNext(newNode);   // tail points to the new node
			this.tail = newNode;          // the new node is now the new tail
		}
		this.size++;                       // increase the size
	}
	
	/**
	 *  Method: insertAt
	 *  Inserts a new element at the given index
	 *  @param: Element item  - the element to be inserted
	 *          int     index - the index to insert at
	 *  @ensure: the list's size is properly updated  
	 */
	public void insertAt(Element item, int index)
	{

	     if ( this.size == 0 || index < 0 || index > this.size ) // Exceptions
	          throw new IndexOutOfBoundsException("Invalid index!");
	     
	     Node<Element> newNode = new Node<Element>(item);
	
	     if ( this.size == 0 ) 			// edge case: if the list is empty
	     {
		     this.head = newNode;
		     this.tail = newNode;
	     }
	     else if (index == 0 ) 	          // edge case: insert at the head of the list
	     {
	          newNode.setNext(this.head.getNext());
		     this.head = newNode;
	     }
	     else 					     // general case
	     {      
               this.current = this.getNode(index-1);    // set current to point to the node just before the index to insert
		     newNode.setNext(this.current.getNext()); // connect the new node to the element after it
		     this.current.setNext(newNode);           // insert the new node to the given index
	     }
	     this.size++; // increase the size
	}

     /**
      *  Method: clear
	 *  Clear all elements of the list
	 */
	public void clear()
	{
	     if ( this.head != null) // if the list is not cleared, clear the list
	     {
	          this.head.setNext(null);
	          this.head = null;
	          this.tail = null;
		     this.size = 0;
		}
	}
	
	
	/**
	 * Method: remove
	 * Removes the first occurrence of the given element from the list
	 * @param: Element element - the element to be removed
	 * @ensure: only the first occurrence is removed in one method call
	 *          the list's size is updated properly
	 */
	public void remove(Element element)
	{
		if(this.isEmpty()==true) // throw an error if the list is empty
		{
			throw new NoSuchElementException("The list is empty!");
	     }
		
		this.resetCurrent(); // reset current to point to the head
		
		if (this.head.getData().equals(element))// edge case: if the node to be removed is the head
		{
			this.head = this.head.getNext(); 	// the element next to head will now be the new head
			this.current.setNext(null);
		}
		else                                    // general case
		{   
		     /**
		      * Loop and use the current pointer to run through the list searching for the given element.
		      * if such element is found, the current pointer will point to the node right before it
		      * if not found, current pointer will shift to the next node until the end of the list is reached
		      **/
	          for (int i=0; i<(this.size-1) ; i++ )
	          {
	               if ( this.current.getNext().getData().equals(element) )
	                    break;
	               else
		               this.next();
	          }

	          if ( this.current == this.tail ) // the current pointer has run through the list and no such element was found
	          {
	               throw new NoSuchElementException("There is no such element in the list");
	          }

		     else if (this.current.getNext() == this.tail)// edge case: if the node to be removed is the tail
		     {
			     this.tail = this.current;
			     this.tail.setNext(null);
		     }
		     else 						          // general case
		     {
			     Node<Element> temp = this.current.getNext();
			     this.current.setNext( this.current.getNext().getNext());
			     temp.setNext(null);
		     }
		}
		
		this.size--; // decrease the size
	}


	/**
	 *  Method: resetCurrent
	 *  Resets current pointer to the head of the list.
	 */
    	public void resetCurrent()
    	{
    		this.current = this.head;
    	}


     /**
	 *  Inner class: SinglyLinkedListIterator
	 *  Defines the iterator of the singly linked list
      *  remove operation is not supported
	 */
     public class SinglyLinkedListIterator implements Iterator<Element>{
          Node<Element> currentNode = head;

          /**
           * Method: hasNext
           * Return: boolean - whether or not there is still element to iterate through
           **/
          public boolean hasNext()
          {
               return currentNode != null; // true if there exists an element at the current node, otherwise false
          }

          /**
           * Method: next
           * Return: Element - the element at the current node, if any
           * The current node is then shifted to the next one
           * @ensure: exception is thrown when there is no element to be returned at the current node
           **/
          public Element next()
          {
               if ( currentNode == null )
                    throw new NoSuchElementException("There is no next element!");
               else 
               {
                    Element toReturn = currentNode.getData();
                    currentNode = currentNode.getNext();
                    return toReturn;
               }
          }
          
          /**
           * Method: remove
           * We don't want removal of item through iterator. This can be unsafe.
           * @ensure: exception is thrown when remove is called
           **/
          public void remove() 
          {
               throw new UnsupportedOperationException("Operation not supported.");
          }
    }

    
     /**
      *  Inner class: Node<Element>
	 *  Defines a node in the singly linked list
	 *  Attributes: Element data - the value held in the node
	 *              Node<Element> next - pointer to the next node in the list
	 */
    	private class Node<Element> 
    	{
	     //Instance Variables
	     Element data;
	     Node<Element> next;

          /**
           * Constructor
           * @param: Element data - the value to be held in the node
           **/
	     public Node(Element data)
	     {
		     this.data = data;
		     this.next = null;
	     }

          /**
           * Method: getData
           * Return: the value held in the node
           **/
	     public Element getData()
	     {
		     return data;
	     }
	     
          /**
           * Method: getNext
           * Return: the node right next to this node in the list
           **/
	     public Node<Element> getNext()
	     {
		     return next;
	     }

          /**
           * Method: setNext
           * Updates the next pointer of the current node to point to the given node
           * @param: Node<Element> next - the node to be connected to this node
           **/
	     public void setNext(Node<Element> next)
	     {
		     this.next = next;
	     }
     }
     
}
