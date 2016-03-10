/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      Startup - a class that contains the main method to make use of SinglyLinkedList
 **/
 
import java.util.Random;

public class Startup
{
	public static void main(String[] args)
	{
	     Random generator = new Random();
	     int size = generator.nextInt(100)+1; // generate the size of the list, from 1-100 
		SinglyLinkedList<Integer> myLinkedList = new SinglyLinkedList<Integer>();

		for (int i=0; i < size; i++) // in a loop, add elements to the linked list
		     myLinkedList.add(i);
          
		// Print the string representation of the list using for-each loop
		System.out.println("The list before removing:");
		for ( Integer i : myLinkedList )
		     System.out.print(i+" ");

          // Remove all the odd numbers in the list
		for (Integer i: myLinkedList)
		{
	          if (i % 2 != 0)
		     myLinkedList.remove(i);
	     } 

          // Print the list after removing add numbers
          System.out.println("\n\nThe list after removing:");
          for ( Integer i : myLinkedList )
		          System.out.print(i+" ");
	
	 }	
	 
}// end class Startup
