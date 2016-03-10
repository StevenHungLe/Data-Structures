/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      TestMinHeap - Test class for MinHeap
 **/
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMinHeap{

     /** 
      * Tests on Integers
      * Number of tests: 5
      * Test 5 different scenarios that can happen to a MinHeap of Integers
      **/
      
	@Test
	public void testInsertInt() // test insertions of Integers in general cases
	{ 
		MinHeap<Integer> minHeap = new MinHeap<Integer>();
		assertEquals(minHeap.toString(),"The heap is empty!");
		
		minHeap.insert(10);
		assertEquals(minHeap.toString(),"[10]"); // first insertion
		
		minHeap.insert(5);
		assertEquals(minHeap.toString(),"[5][10]"); // 5 percolates up to replace 10 
		
		minHeap.insert(21);
		assertEquals(minHeap.toString(),"[5][10][21]"); // 21 is inserted at the end 
		
		minHeap.insert(8);
		assertEquals(minHeap.toString(),"[5][8][21][10]"); // 8 percolates up to replace 10
		
		minHeap.insert(8);
		assertEquals(minHeap.toString(),"[5][8][21][10][8]"); // 8 does not perculate up since it equals to 8
		
		minHeap.insert(25);
		assertEquals(minHeap.toString(),"[5][8][21][10][8][25]"); // 25 is inserted at the end
		
		minHeap.insert(20);
		assertEquals(minHeap.toString(),"[5][8][20][10][8][25][21]"); // 20 percolates up to replace 21
	}

     @Test
	public void testExpandInteger() // test the expand capability of the heap of Integers
	{ 
		MinHeap<Integer> minHeap = new MinHeap<Integer>();
		
		for (int i=0;i<10;i++) // insert 10 items to the heap
		     minHeap.insert(10);
          assertTrue( minHeap.isFull() && minHeap.size() == 10 ); // confirm that the heap is currently full
          
		minHeap.insert(11); // insert an item into the full heap
		assertTrue( minHeap.size() == 11 ); // confirm that the heap's current size is 11
		assertTrue( minHeap.capacity() == 20 ); // confirm that the heap has doubled in capacity
		
		for (int i=0;i<10;i++) // insert 10 more items to the heap
		     minHeap.insert(12);   
	     assertTrue( minHeap.size() == 21 ); // confirm that the heap's current size is 21
		assertTrue( minHeap.capacity() == 40 ); // confirm that the heap has doubled in capacity 
	}


	@Test
	public void testRemoveInt() // test removal of Integers in general cases
	{ 
		MinHeap<Integer> minHeap = new MinHeap<Integer>();
		minHeap.insert(10);
		minHeap.insert(5);
		minHeap.insert(21);
		minHeap.insert(8);
		minHeap.insert(8);
		minHeap.insert(25);
		minHeap.insert(20);
		assertEquals(minHeap.toString(),"[5][8][20][10][8][25][21]"); // the heap so far

		int minValue = minHeap.remove(); // remove 5
		assertTrue(minValue == 5);
		assertEquals(minHeap.toString(),"[8][8][20][10][21][25]");

		minValue = minHeap.remove(); // remove 8
		assertTrue(minValue == 8);
		assertEquals(minHeap.toString(),"[8][10][20][25][21]");

		minValue = minHeap.remove(); // remove 8
		assertTrue(minValue == 8);
		assertEquals(minHeap.toString(),"[10][21][20][25]");

		minValue = minHeap.remove(); // remove 10
		assertTrue(minValue == 10);
		assertEquals(minHeap.toString(),"[20][21][25]");

		minValue = minHeap.remove(); // remove 20
		assertTrue(minValue == 20);
		assertEquals(minHeap.toString(),"[21][25]");

		minValue = minHeap.remove(); // remove 21
		assertTrue(minValue == 21);
		assertEquals(minHeap.toString(),"[25]");

		minValue = minHeap.remove(); // remove 25
		assertTrue(minValue == 25);
		assertEquals(minHeap.toString(),"The heap is empty!");
	}


	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveIntFromEmptyHeap() // test removal of an Integer from an empty heap
	{ 
		MinHeap<Integer> minHeap = new MinHeap<Integer>();

          assertTrue(minHeap.isEmpty());
          minHeap.remove(); // should throw exception here
	}


     @Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveIntFromDepletedHeap() // test removal of an Integer from a depleted( a.k.a empty ) heap
	{ 
		MinHeap<Integer> minHeap = new MinHeap<Integer>();
		
		for (int i=0;i<5;i++) // insert 5 items to the heap
		     minHeap.insert(10);
          assertFalse(minHeap.isEmpty());
          
          for (int i=0;i<5;i++) // remove 5 items from the heap
		     minHeap.remove();
		assertTrue(minHeap.isEmpty());
		
		minHeap.remove(); // should throw exception here
	}



     /** 
      * Tests on Task objects
      * Number of tests: 5
      * These tests are duplicates of the 5 tests above, but they deal with Task objects defined in Task.java
      * Used to demonstrate that the class can perform correctly with different data types
      *
      * NOTE !!! These tests are meant to be exact duplicates of the tests for Integers above: the Integers value are used to construct the Task objects of corresponding deadline;
      * thus, the behavior of insert/remove should be the same, because comparisons of Task objects are performed only on their deadlines;
      * The array presentation of the deadlines are provided in comments for better understandability
      **/
      
	@Test
	public void testInsertTask() // test insertions of Tasks in general cases
	{ 
		MinHeap<Task> minHeap = new MinHeap<Task>();
		assertEquals(minHeap.toString(),"The heap is empty!");
		
		minHeap.insert(new Task("DL10",10,100));
		// [10] : first insertion
		
		minHeap.insert(new Task("DL5",5,100));
		// [5][10] : 5 percolates up to replace 10 
		
		minHeap.insert(new Task("DL21",21,100));
		// [5][10][21] : 21 is inserted at the end 
		
		minHeap.insert(new Task("DL8",8,100));
		// [5][8][21][10] :  8 percolates up to replace 10
		
		minHeap.insert(new Task("DL8",8,100));
		// [5][8][21][10][8] : 8 does not perculate up since it equals to 8
		
		minHeap.insert(new Task("DL25",25,100));
		// [5][8][21][10][8][25] : 25 is inserted at the end
		
		minHeap.insert(new Task("DL20",20,100));
		// [5][8][20][10][8][25][21] : 20 percolates up to replace 21

		// confirm the returned String from the toString() method exactly matches the expected String   
		assertEquals(minHeap.toString(),"[Name:DL5, duration:100, deadline:5][Name:DL8, duration:100, deadline:8][Name:DL20, duration:100, deadline:20][Name:DL10, duration:100, deadline:10][Name:DL8, duration:100, deadline:8][Name:DL25, duration:100, deadline:25][Name:DL21, duration:100, deadline:21]");
	}

     @Test
	public void testExpandTask() // test the expand capability of the heap of Tasks
	{ 
		MinHeap<Task> minHeap = new MinHeap<Task>();
		
		for (int i=0;i<10;i++) // insert 10 items to the heap
		     minHeap.insert(new Task("whatever",10,10));
          assertTrue( minHeap.isFull() && minHeap.size() == 10 ); // confirm that the heap is currently full
          
		minHeap.insert(new Task("whatever",11,11)); // insert an item into the full heap
		assertTrue( minHeap.size() == 11 ); // confirm that the heap's current size is 11
		assertTrue( minHeap.capacity() == 20 ); // confirm that the heap has doubled in capacity
		
		for (int i=0;i<10;i++) // insert 10 more items to the heap, exceeding its capacity again
		     minHeap.insert(new Task("whatever",12,12));   
	     assertTrue( minHeap.size() == 21 ); // confirm that the heap's current size is 21
		assertTrue( minHeap.capacity() == 40 ); // confirm that the heap has doubled in capacity 
	}


	@Test
	public void testRemoveTask() // test removal of Tasks in general cases
	{ 
		MinHeap<Task> minHeap = new MinHeap<Task>();

		// populate the heap for testing
		minHeap.insert(new Task("DL10",10,100));
		minHeap.insert(new Task("DL5",5,100));
		minHeap.insert(new Task("DL21",21,100));
		minHeap.insert(new Task("DL8",8,100));
		minHeap.insert(new Task("DL8",8,100));
		minHeap.insert(new Task("DL25",25,100));
		minHeap.insert(new Task("DL20",20,100));

		// the heap so far 
		assertEquals(minHeap.toString(),"[Name:DL5, duration:100, deadline:5][Name:DL8, duration:100, deadline:8][Name:DL20, duration:100, deadline:20][Name:DL10, duration:100, deadline:10][Name:DL8, duration:100, deadline:8][Name:DL25, duration:100, deadline:25][Name:DL21, duration:100, deadline:21]"); 
		

		long minDeadline = minHeap.remove().getDeadline(); // remove the task with deadline 5
		assertTrue(minDeadline == 5);
		//[8][8][20][10][21][25] : the heap so far( in terms of deadlines )

		minDeadline = minHeap.remove().getDeadline(); // remove the task with deadline 8
		assertTrue(minDeadline == 8);
		//[8][10][20][25][21]

		minDeadline = minHeap.remove().getDeadline(); // remove the task with deadline 8
		assertTrue(minDeadline == 8);
		//[10][21][20][25]

		minDeadline = minHeap.remove().getDeadline(); // remove the task with deadline 10
		assertTrue(minDeadline == 10);
		//[20][21][25]

		minDeadline = minHeap.remove().getDeadline(); // remove the task with deadline 20
		assertTrue(minDeadline == 20);
		//[21][25]

		minDeadline = minHeap.remove().getDeadline(); // remove the task with deadline 21
		assertTrue(minDeadline == 21);
		//[25]

		minDeadline = minHeap.remove().getDeadline(); // remove the task with deadline 25
		assertTrue(minDeadline == 25);
		assertEquals(minHeap.toString(),"The heap is empty!");
	}


	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveTaskFromEmptyHeap() // test removal of a Task from an empty heap
	{ 
		MinHeap<Task> minHeap = new MinHeap<Task>();

          assertTrue(minHeap.isEmpty());
          minHeap.remove(); // should throw exception here
	}


     @Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveTaskFromDepletedHeap() // test removal of a Task from a depleted( a.k.a empty ) heap
	{ 
		MinHeap<Task> minHeap = new MinHeap<Task>();
		
		for (int i=0;i<5;i++) // insert 5 items to the heap
		     minHeap.insert(new Task("whatever",10,10));
          assertFalse(minHeap.isEmpty());
          
          for (int i=0;i<5;i++) // remove 5 items from the heap
		     minHeap.remove();
		assertTrue(minHeap.isEmpty());
		
		minHeap.remove(); // should throw exception here
	}

} // End of TestMinHeap class
