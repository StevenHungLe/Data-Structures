/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      Startup - a class that contains the main method to use MyArrayList
 **/
 
import java.util.Random;

public class Startup
{
	public static void main(String[] args)
	{
	     Random generator = new Random();
	     int size = generator.nextInt(100)+1; // generate the size of the array list, from 1-100 
		MyArrayList myArl = new MyArrayList(size);

		for (int i=0; i < size; i++) // in a loop, add elements to the array list
		     myArl.add(i);

		// Print the string representation of the list    
		System.out.println("Data:"+ myArl.toString()+"\nSize: "+myArl.size());
	}	
}// end class Startup
