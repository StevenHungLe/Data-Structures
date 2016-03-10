/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      MyArrayList - a generic array list
 **/

public class MyArrayList<T>
{
     // Instance variables
	private Object[] data;  // the array of data
	private int numElements;// the number of elements
	private final int DEFAULT_SIZE = 10; // the default number of elements

     /**
      * Constructor - construct an array with the size passed in
      * Parameters: int size:the size of the array to be constructed 
      **/
	public MyArrayList ( int size )
	{
		data = new Object[size];
		numElements = 0;
	}

     /**
      * Constructor - construct an array with the default size of 10
      * Parameters: none
      **/
	public MyArrayList ()
	{
		data = new Object[DEFAULT_SIZE];
		numElements = 0;
	}

     /**
      * Method: add - add an element to the end of the list
      * Parameters: T value: the value to be added to the list
      * Ensure: the number of elements is updated accordingly
      **/
	public void add(T value)
	{
		data[numElements]= (T) value;
		numElements++; // increase the number of elements
	}

     /**
      * Method: remove - remove the element at the specified index
      * Parameters: int idx: the index of the element to be removed
      * Ensure: the number of elements is updated accordingly
      **/
	public void remove(int idx)
	{
	     if ( idx < 0 || idx >= numElements ) // check if the index is in bounds
	     {
	         System.out.println("Cannot remove: Invalid index!");
	         return;
	     }
	     /* shift left the elements after the element to be removed 
	      * thus overwriting the element to be removed*/
		for ( int i=idx; i<numElements-1; i++) 
		{
		     data[i]=data[i+1];
		} 
		numElements--; // decrease the number of elements
	}

     /**
      * Method: size
      * Return: the number of elemments in the list
      **/
	public int size()
	{
	     return numElements;
	}

     /**
      * Method: toString - Generate and return a string representation of the list
      * Return: the string representation of the list
      **/
	public String toString()
	{
	     String dataString = "[ "; // beginning of the string
	     
	     for ( int i=0; i < numElements; i++)
	     {
	          dataString = dataString.concat(data[i]+" "); // concatenate the elements to the string
	     }
	     
	     dataString = dataString.concat("]"); // end of the string
	     
		return dataString;
	}
}// end class MyArrayList
