/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      TestUnboundedPriorityQueue - Test class for UnboundedPriorityQueue
 **/
import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnboundedPriorityQueue{

     /** 
      * Tests on Integers
      * Number of tests: 5
      * Test 5 different scenarios that can happen to a UnboundedPriorityQueue of Integers
      **/
      
	@Test
	public void testInsertInt() // test insertions of Integers in general cases
	{ 
		UnboundedPriorityQueue<Integer> priorityQ = new UnboundedPriorityQueue<Integer>();
		assertEquals(priorityQ.toString(),"The heap is empty!");
		
		priorityQ.enqueue(10);
		assertEquals(priorityQ.toString(),"[10]"); // first insertion
		
		priorityQ.enqueue(5);
		assertEquals(priorityQ.toString(),"[5][10]"); // 5 percolates up to replace 10 
		
		priorityQ.enqueue(21);
		assertEquals(priorityQ.toString(),"[5][10][21]"); // 21 is inserted at the end 
		
		priorityQ.enqueue(8);
		assertEquals(priorityQ.toString(),"[5][8][21][10]"); // 8 percolates up to replace 10
		
		priorityQ.enqueue(8);
		assertEquals(priorityQ.toString(),"[5][8][21][10][8]"); // 8 does not perculate up since it equals to 8
		
		priorityQ.enqueue(25);
		assertEquals(priorityQ.toString(),"[5][8][21][10][8][25]"); // 25 is inserted at the end
		
		priorityQ.enqueue(20);
		assertEquals(priorityQ.toString(),"[5][8][20][10][8][25][21]"); // 20 percolates up to replace 21
	}

     @Test
	public void testExpandInteger() // test the expand capability of the priority queue of Integers
	{ 
		UnboundedPriorityQueue<Integer> priorityQ = new UnboundedPriorityQueue<Integer>();
		
		for (int i=0;i<10;i++) // enqueue 10 items to the priority queue
		     priorityQ.enqueue(10);
          assertTrue( priorityQ.isFull() && priorityQ.size() == 10 ); // confirm that the heap is currently full
          
		priorityQ.enqueue(11); // enqueue an item into the full priority queue
		assertTrue( priorityQ.size() == 11 ); // confirm that the priority queue's current size is 11
		assertTrue( priorityQ.capacity() == 20 ); // confirm that the priority queue has doubled in capacity
		
		for (int i=0;i<10;i++) // enqueue 10 more items to the priority queue
		     priorityQ.enqueue(12);   
	     assertTrue( priorityQ.size() == 21 ); // confirm that the priority queue's current size is 21
		assertTrue( priorityQ.capacity() == 40 ); // confirm that the priority queue has doubled in capacity 
	}


	@Test
	public void testRemoveInt() // test removal of Integers in general cases
	{ 
		UnboundedPriorityQueue<Integer> priorityQ = new UnboundedPriorityQueue<Integer>();
		priorityQ.enqueue(10);
		priorityQ.enqueue(5);
		priorityQ.enqueue(21);
		priorityQ.enqueue(8);
		priorityQ.enqueue(8);
		priorityQ.enqueue(25);
		priorityQ.enqueue(20);
		assertEquals(priorityQ.toString(),"[5][8][20][10][8][25][21]"); // the priority queue so far

		int minValue = priorityQ.dequeue(); // dequeue 5
		assertTrue(minValue == 5);
		assertEquals(priorityQ.toString(),"[8][8][20][10][21][25]");

		minValue = priorityQ.dequeue(); // dequeue 8
		assertTrue(minValue == 8);
		assertEquals(priorityQ.toString(),"[8][10][20][25][21]");

		minValue = priorityQ.dequeue(); // dequeue 8
		assertTrue(minValue == 8);
		assertEquals(priorityQ.toString(),"[10][21][20][25]");

		minValue = priorityQ.dequeue(); // dequeue 10
		assertTrue(minValue == 10);
		assertEquals(priorityQ.toString(),"[20][21][25]");

		minValue = priorityQ.dequeue(); // dequeue 20
		assertTrue(minValue == 20);
		assertEquals(priorityQ.toString(),"[21][25]");

		minValue = priorityQ.dequeue(); // dequeue 21
		assertTrue(minValue == 21);
		assertEquals(priorityQ.toString(),"[25]");

		minValue = priorityQ.dequeue(); // dequeue 25
		assertTrue(minValue == 25);
		assertEquals(priorityQ.toString(),"The heap is empty!");
	}


	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveIntFromEmptyHeap() // test removal of an Integer from an empty priority queue
	{ 
		UnboundedPriorityQueue<Integer> priorityQ = new UnboundedPriorityQueue<Integer>();

          assertTrue(priorityQ.isEmpty());
          priorityQ.dequeue(); // should throw exception here
	}


     @Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveIntFromDepletedHeap() // test removal of an Integer from a depleted( a.k.a empty ) priority queue
	{ 
		UnboundedPriorityQueue<Integer> priorityQ = new UnboundedPriorityQueue<Integer>();
		
		for (int i=0;i<5;i++) // enqueue 5 items to the heap
		     priorityQ.enqueue(10);
          assertFalse(priorityQ.isEmpty());
          
          for (int i=0;i<5;i++) // dequeue 5 items from the heap
		     priorityQ.dequeue();
		assertTrue(priorityQ.isEmpty());
		
		priorityQ.dequeue(); // should throw exception here
	}



     /** 
      * Tests on Task objects
      * Number of tests: 5
      * These tests are duplicates of the 5 tests above, but they deal with Task objects defined in Task.java
      * Used to demonstrate that the class can perform correctly with different data types
      *
      * NOTE !!! These tests are meant to be exact duplicates of the tests for Integers above: the Integers value are used to construct the Task objects of corresponding deadline;
      * thus, the behavior of enqueue/dequeue should be the same, because comparisons of Task objects are performed only on their deadlines;
      * The array presentation of the deadlines are provided in comments for better understandability
      **/
      
	@Test
	public void testInsertTask() // test insertions of Tasks in general cases
	{ 
		UnboundedPriorityQueue<Task> priorityQ = new UnboundedPriorityQueue<Task>();
		assertEquals(priorityQ.toString(),"The heap is empty!");
		
		priorityQ.enqueue(new Task("DL10",10,100));
		// [10] : first insertion
		
		priorityQ.enqueue(new Task("DL5",5,100));
		// [5][10] : 5 percolates up to replace 10 
		
		priorityQ.enqueue(new Task("DL21",21,100));
		// [5][10][21] : 21 is inserted at the end 
		
		priorityQ.enqueue(new Task("DL8",8,100));
		// [5][8][21][10] :  8 percolates up to replace 10
		
		priorityQ.enqueue(new Task("DL8",8,100));
		// [5][8][21][10][8] : 8 does not perculate up since it equals to 8
		
		priorityQ.enqueue(new Task("DL25",25,100));
		// [5][8][21][10][8][25] : 25 is inserted at the end
		
		priorityQ.enqueue(new Task("DL20",20,100));
		// [5][8][20][10][8][25][21] : 20 percolates up to replace 21

		// confirm the returned String from the toString() method exactly matches the expected String   
		assertEquals(priorityQ.toString(),"[Name:DL5, duration:100, deadline:5][Name:DL8, duration:100, deadline:8][Name:DL20, duration:100, deadline:20][Name:DL10, duration:100, deadline:10][Name:DL8, duration:100, deadline:8][Name:DL25, duration:100, deadline:25][Name:DL21, duration:100, deadline:21]");
	}

     @Test
	public void testExpandTask() // test the expand capability of the priority queue of Tasks
	{ 
		UnboundedPriorityQueue<Task> priorityQ = new UnboundedPriorityQueue<Task>();
		
		for (int i=0;i<10;i++) // enqueue 10 items to the priority queue
		     priorityQ.enqueue(new Task("whatever",10,10));
          assertTrue( priorityQ.isFull() && priorityQ.size() == 10 ); // confirm that the priority queue is currently full
          
		priorityQ.enqueue(new Task("whatever",11,11)); // enqueue an item into the full priority queue
		assertTrue( priorityQ.size() == 11 ); // confirm that the priority queue's current size is 11
		assertTrue( priorityQ.capacity() == 20 ); // confirm that the priority queue has doubled in capacity
		
		for (int i=0;i<10;i++) // enqueue 10 more items to the priority queue, exceeding its capacity again
		     priorityQ.enqueue(new Task("whatever",12,12));   
	     assertTrue( priorityQ.size() == 21 ); // confirm that the priority queue's current size is 21
		assertTrue( priorityQ.capacity() == 40 ); // confirm that the priority queue has doubled in capacity 
	}


	@Test
	public void testRemoveTask() // test removal of Tasks in general cases
	{ 
		UnboundedPriorityQueue<Task> priorityQ = new UnboundedPriorityQueue<Task>();

		// populate the priority queue for testing
		priorityQ.enqueue(new Task("DL10",10,100));
		priorityQ.enqueue(new Task("DL5",5,100));
		priorityQ.enqueue(new Task("DL21",21,100));
		priorityQ.enqueue(new Task("DL8",8,100));
		priorityQ.enqueue(new Task("DL8",8,100));
		priorityQ.enqueue(new Task("DL25",25,100));
		priorityQ.enqueue(new Task("DL20",20,100));

		// the priority queue so far 
		assertEquals(priorityQ.toString(),"[Name:DL5, duration:100, deadline:5][Name:DL8, duration:100, deadline:8][Name:DL20, duration:100, deadline:20][Name:DL10, duration:100, deadline:10][Name:DL8, duration:100, deadline:8][Name:DL25, duration:100, deadline:25][Name:DL21, duration:100, deadline:21]"); 
		

		long minDeadline = priorityQ.dequeue().getDeadline(); // dequeue the task with deadline 5
		assertTrue(minDeadline == 5);
		//[8][8][20][10][21][25] : the priority queue so far( in terms of deadlines )

		minDeadline = priorityQ.dequeue().getDeadline(); // dequeue the task with deadline 8
		assertTrue(minDeadline == 8);
		//[8][10][20][25][21]

		minDeadline = priorityQ.dequeue().getDeadline(); // dequeue the task with deadline 8
		assertTrue(minDeadline == 8);
		//[10][21][20][25]

		minDeadline = priorityQ.dequeue().getDeadline(); // dequeue the task with deadline 10
		assertTrue(minDeadline == 10);
		//[20][21][25]

		minDeadline = priorityQ.dequeue().getDeadline(); // dequeue the task with deadline 20
		assertTrue(minDeadline == 20);
		//[21][25]

		minDeadline = priorityQ.dequeue().getDeadline(); // dequeue the task with deadline 21
		assertTrue(minDeadline == 21);
		//[25]

		minDeadline = priorityQ.dequeue().getDeadline(); // dequeue the task with deadline 25
		assertTrue(minDeadline == 25);
		assertEquals(priorityQ.toString(),"The heap is empty!");
	}


	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveTaskFromEmptyHeap() // test removal of a Task from an empty priority queue
	{ 
		UnboundedPriorityQueue<Task> priorityQ = new UnboundedPriorityQueue<Task>();

          assertTrue(priorityQ.isEmpty());
          priorityQ.dequeue(); // should throw exception here
	}


     @Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveTaskFromDepletedHeap() // test removal of a Task from a depleted( a.k.a empty ) priority queue
	{ 
		UnboundedPriorityQueue<Task> priorityQ = new UnboundedPriorityQueue<Task>();
		
		for (int i=0;i<5;i++) // enqueue 5 items to the priority queue
		     priorityQ.enqueue(new Task("whatever",10,10));
          assertFalse(priorityQ.isEmpty());
          
          for (int i=0;i<5;i++) // dequeue 5 items from the priority queue
		     priorityQ.dequeue();
		assertTrue(priorityQ.isEmpty());
		
		priorityQ.dequeue(); // should throw exception here
	}

} // End of TestUnboundedPriorityQueue class
