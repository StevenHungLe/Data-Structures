/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      TestMaxHeap - Test class for MaxHeap
 **/
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMaxHeap{

     /** 
      * Tests on Integers
      * Number of tests: 5
      * Test 5 different scenarios that can happen to a MaxHeap of Integers
      **/
      
	@Test
	public void testInsertInt() // test insertions of Integers in general cases
	{ 
		MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();
		assertEquals(maxHeap.toString(),"The heap is empty!");
		
		maxHeap.insert(10);
		assertEquals(maxHeap.toString(),"[10]"); // first insertion
		
		maxHeap.insert(15);
		assertEquals(maxHeap.toString(),"[15][10]"); // 15 percolates up to replace 10 
		
		maxHeap.insert(-1);
		assertEquals(maxHeap.toString(),"[15][10][-1]"); // -1 is inserted at the end 
		
		maxHeap.insert(12);
		assertEquals(maxHeap.toString(),"[15][12][-1][10]"); // 12 percolates up to replace 10
		
		maxHeap.insert(12);
		assertEquals(maxHeap.toString(),"[15][12][-1][10][12]"); // 12 does not perculate up since it equals to 12
		
		maxHeap.insert(-5);
		assertEquals(maxHeap.toString(),"[15][12][-1][10][12][-5]"); // -5 is inserted at the end
		
		maxHeap.insert(0);
		assertEquals(maxHeap.toString(),"[15][12][0][10][12][-5][-1]"); // 0 percolates up to replace -1
	}


	@Test(expected=IndexOutOfBoundsException.class)
	public void testInsertIntToFullHeap() // test insertion of an Integer to a full heap
	{ 
		MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();
		for (int i=0;i<10;i++) // insert 10 items to the heap
		     maxHeap.insert(10);

		maxHeap.insert(10); // should throw exception here
	}


	@Test
	public void testRemoveInt() // test removal of Integers in general cases
	{ 
		MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();
		maxHeap.insert(10);
		maxHeap.insert(15);
		maxHeap.insert(-1);
		maxHeap.insert(12);
		maxHeap.insert(12);
		maxHeap.insert(-5);
		maxHeap.insert(0);
		assertEquals(maxHeap.toString(),"[15][12][0][10][12][-5][-1]"); // the heap so far

		Integer maxValue = (Integer)maxHeap.remove(); // remove 15
		assertTrue(maxValue == 15);
		assertEquals(maxHeap.toString(),"[12][12][0][10][-1][-5]");

		maxValue = (Integer)maxHeap.remove(); // remove 12
		assertTrue(maxValue == 12);
		assertEquals(maxHeap.toString(),"[12][10][0][-5][-1]");

		maxValue = (Integer)maxHeap.remove(); // remove 12
		assertTrue(maxValue == 12);
		assertEquals(maxHeap.toString(),"[10][-1][0][-5]");

		maxValue = (Integer)maxHeap.remove(); // remove 10
		assertTrue(maxValue == 10);
		assertEquals(maxHeap.toString(),"[0][-1][-5]");

		maxValue = (Integer)maxHeap.remove(); // remove 0
		assertTrue(maxValue == 0);
		assertEquals(maxHeap.toString(),"[-1][-5]");

		maxValue = (Integer)maxHeap.remove(); // remove -1
		assertTrue(maxValue == -1);
		assertEquals(maxHeap.toString(),"[-5]");

		maxValue = (Integer)maxHeap.remove(); // remove -5
		assertTrue(maxValue == -5);
		assertEquals(maxHeap.toString(),"The heap is empty!");
	}


	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveIntFromEmptyHeap() // test removal of an Integer from an empty heap
	{ 
		MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();

          assertTrue(maxHeap.isEmpty());
          maxHeap.remove(); // should throw exception here
	}


     @Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveIntFromDepletedHeap() // test removal of an Integer from a depleted( a.k.a empty ) heap
	{ 
		MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();
		
		for (int i=0;i<5;i++) // insert 5 items to the heap
		     maxHeap.insert(10);
          assertFalse(maxHeap.isEmpty());
          
          for (int i=0;i<5;i++) // remove 5 items from the heap
		     maxHeap.remove();
		assertTrue(maxHeap.isEmpty());
		
		maxHeap.remove(); // should throw exception here
	}



     /** 
      * Tests on Strings
      * Number of tests: 5
      * These tests are duplicates of the 5 tests above, but they deal with Strings
      * Used to demonstrate that the class can perform correctly with different data types
      **/
      
	@Test
	public void testInsertString() // test insertions of Strings in general cases
	{ 
		MaxHeap<String> maxHeap = new MaxHeap<String>();
		assertEquals(maxHeap.toString(),"The heap is empty!");
		
		maxHeap.insert("Derek");
		assertEquals(maxHeap.toString(),"[Derek]"); // first insertion
		
		maxHeap.insert("Eric");
		assertEquals(maxHeap.toString(),"[Eric][Derek]"); // Eric percolates up to replace Cynthia 
		
		maxHeap.insert("Bob");
		assertEquals(maxHeap.toString(),"[Eric][Derek][Bob]"); // Bob is inserted at the end 
		
		maxHeap.insert("Dory");
		assertEquals(maxHeap.toString(),"[Eric][Dory][Bob][Derek]"); // Dory percolates up to replace Derek
		
		maxHeap.insert("Dory");
		assertEquals(maxHeap.toString(),"[Eric][Dory][Bob][Derek][Dory]"); // Dory does not perculate up since it equals to Dory
		
		maxHeap.insert("Alex");
		assertEquals(maxHeap.toString(),"[Eric][Dory][Bob][Derek][Dory][Alex]"); // Alex is inserted at the end
		
		maxHeap.insert("Cindy");
		assertEquals(maxHeap.toString(),"[Eric][Dory][Cindy][Derek][Dory][Alex][Bob]"); // Cindy percolates up to replace Bob
	}


	@Test(expected=IndexOutOfBoundsException.class)
	public void testInsertStringToFullHeap() // test insertion of a String to a full heap
	{ 
		MaxHeap<String> maxHeap = new MaxHeap<String>();
		for (int i=0;i<10;i++) // insert 10 items to the heap
		     maxHeap.insert("Whatever");

		maxHeap.insert("Whatever"); // should throw exception here
	}


	@Test
	public void testRemoveString() // test removal of Strings in general cases
	{ 
		MaxHeap<String> maxHeap = new MaxHeap<String>();
		maxHeap.insert("Derek");
		maxHeap.insert("Eric");
		maxHeap.insert("Bob");
		maxHeap.insert("Dory");
		maxHeap.insert("Dory");
		maxHeap.insert("Alex");
		maxHeap.insert("Cindy");
		assertEquals(maxHeap.toString(),"[Eric][Dory][Cindy][Derek][Dory][Alex][Bob]"); // the heap so far

		String maxValue = (String)maxHeap.remove(); // remove Eric
		assertEquals(maxValue,"Eric");
		assertEquals(maxHeap.toString(),"[Dory][Dory][Cindy][Derek][Bob][Alex]");

		maxValue = (String)maxHeap.remove(); // remove Dory
		assertEquals(maxValue,"Dory");
		assertEquals(maxHeap.toString(),"[Dory][Derek][Cindy][Alex][Bob]");

		maxValue = (String)maxHeap.remove(); // remove Dory
		assertEquals(maxValue,"Dory");
		assertEquals(maxHeap.toString(),"[Derek][Bob][Cindy][Alex]");

		maxValue = (String)maxHeap.remove(); // remove Derek
		assertEquals(maxValue,"Derek");
		assertEquals(maxHeap.toString(),"[Cindy][Bob][Alex]");

		maxValue = (String)maxHeap.remove(); // remove Cindy
		assertEquals(maxValue,"Cindy");
		assertEquals(maxHeap.toString(),"[Bob][Alex]");

		maxValue = (String)maxHeap.remove(); // remove Bob
		assertEquals(maxValue,"Bob");
		assertEquals(maxHeap.toString(),"[Alex]");

		maxValue = (String)maxHeap.remove(); // remove Alex
		assertEquals(maxValue,"Alex");
		assertEquals(maxHeap.toString(),"The heap is empty!");
	}


	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveStringFromEmptyHeap() // test removal of a String from an empty heap
	{ 
		MaxHeap<String> maxHeap = new MaxHeap<String>();

          assertTrue(maxHeap.isEmpty());
          maxHeap.remove(); // should throw exception here
	}


     @Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveStringFromDepletedHeap() // test removal of a String from a depleted( a.k.a empty ) heap
	{ 
		MaxHeap<String> maxHeap = new MaxHeap<String>();
		
		for (int i=0;i<5;i++) // insert 5 items to the heap
		     maxHeap.insert("Whatever");
          assertFalse(maxHeap.isEmpty());
          
          for (int i=0;i<5;i++) // remove 5 items from the heap
		     maxHeap.remove();
		assertTrue(maxHeap.isEmpty()); // the heap should be empty now
		
		maxHeap.remove(); // should throw exception here

	}
	

} // End of TestMaxHeap class
