/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      junit test class for class MyArrayList 
 **/

import junit.framework.TestCase;

public class MyArrayListTest extends TestCase
{
	// Objects to test
	private MyArrayList intArl;    // an ArrayList of type int
	private MyArrayList stringArl; // an ArrayList of type string


	/**
	 * Setup
	 */
	protected void setUp ()
	{
	     // initialize objects with default size
		intArl = new MyArrayList();  
		stringArl = new MyArrayList();
	}


	/**
	 * Method to be tested: add()
	 * Expected result: new element to be added at the end of the list 
	 * Method of testing: use method toString() to check the array list
	 */
	public void testAdd()
	{
	     // Test the array list of type int \\
	     
	     for ( int i=0; i<3; i++) // add 3 elements
	     {
	          intArl.add(i);
	     }
	     assertEquals(intArl.toString(),"[ 0 1 2 ]");

          // Test the array list of type string \\
          
	     for ( int i=0; i<3; i++) // add 3 elements
	     {
	          stringArl.add("<"+i+">");
	     }
	     assertEquals( stringArl.toString(),"[ <0> <1> <2> ]");
	}

	/**
	 * Method to be tested: remove()
	 * Expected result: the indexed element to be removed from the list 
	 * Method of testing: use method toString() to check the array list
	 */
	public void testRemove()
	{
	     // Test the array list of type int \\
	     
	     for ( int i=0; i<3; i++) // add 3 elements
	     {
	          intArl.add(i);
	     }
	     // before remove
	     assertEquals(intArl.toString(),"[ 0 1 2 ]");

	     intArl.remove(1); // remove element at index 1

	     // after remove
	     assertEquals(intArl.toString(),"[ 0 2 ]");


          // Test the array list of type string \\
          
	     for ( int i=0; i<3; i++) // add 3 elements
	     {
	          stringArl.add("<"+i+">");
	     }
	     // before remove
	     assertEquals( stringArl.toString(),"[ <0> <1> <2> ]");

	     stringArl.remove(0); // remove element at index 0

	     // after remove
	     assertEquals( stringArl.toString(),"[ <1> <2> ]");
	}


	/**
	 * Method to be tested: toString()
	 * Method of testing: use assertEquals to compare the returned result
	   and the expected string
	 */
	public void testToString()
	{
	     // Test the array list of type int \\
	     
	     for ( int i=0; i<3; i++) // add 3 elements
	     {
	          intArl.add(i+100);
	     }
	     assertEquals(intArl.toString(),"[ 100 101 102 ]");

          // Test the array list of type string \\
          
	     for ( int i=0; i<3; i++) // add 3 elements
	     {
	          stringArl.add("<100"+i+">");
	     }
	     assertEquals( stringArl.toString(),"[ <1000> <1001> <1002> ]");
	}


	/**
	 * Method to be tested: size()
	 * expected return value: the number of elements of the array list
	 * Method of testing: use assertTrue to compare the returned result
	   and the expected size of the array list
	 */
	public void testSize()
	{
	     // Test the array list of type int \\
	     
	     for ( int i=0; i<10; i++) // add 10 elements of type int
	     {
	          intArl.add(i);
	     }
	     assertTrue(intArl.size() == 10);

          // Test the array list of type string \\
          
	     for ( int i=0; i<9; i++) // add 9 elements of type string
	     {
	          stringArl.add("<1"+i+">");

	     }
	     assertTrue( stringArl.size() == 9);
	}

}// end class MyArrayListTest
