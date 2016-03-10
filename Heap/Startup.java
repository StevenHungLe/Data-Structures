/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      Startup - the startup class to utilize and demonstrate the uses of MaxHeap
 **/

import java.util.Random;

public class Startup
{
     public static void main ( String[] args )
     {
          Random generator = new Random(); // The random number generator
          int size; // The size of the heap
          
          if     ( args.length == 1 ) // heap size is specified as a command-line argument
               size = Integer.parseInt(args[0]);
          else //( args.length == 0 ) // heap size is not specified, default it to 10
               size = 10;

          MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(size); // The MaxHeap object to work on
               
          for(int i=0; i<size; i++) // Insert random numbers to the heap
               maxHeap.insert(generator.nextInt(100));

          int[] sortedArray = new int[size]; // The array to store the heap's items

          // until the heap runs out of item, remove its maximum value and store it to the array
          for(int i=0; !maxHeap.isEmpty(); i++)
          {
               sortedArray[i] = ((Integer)maxHeap.remove()).intValue();
          }

          // print out the array's content
          for( int i : sortedArray )
               System.out.print("["+i+"]");
     }
} // End of Startup class
