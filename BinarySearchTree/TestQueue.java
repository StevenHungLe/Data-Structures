/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      TestQueue - Test class for class Queue
 **/
import org.junit.Test;
import static org.junit.Assert.*;

public class TestQueue{
	
	@Test
	public void TestGeneralCases(){ // test general cases
		int[] seq = {1,2,3,4};
		Queue queue = new Queue();
		assertTrue(queue.size() == 0);
		for ( int i : seq )
		     queue.enqueue(i);
          assertTrue(queue.size() == 4);
		for ( int i : seq )
		{
		     assertTrue(((Integer)queue.dequeue()).intValue() == i); // first in first out: 1,2,3,4
		}
		assertTrue(queue.size() == 0);
	}

	@Test
	public void TestDequeueFromEmptyQueue(){ // test dequeue from empty queue
		Queue queue = new Queue();
          assertTrue(queue.size() == 0);
		assertNull(queue.dequeue()); // should return null
          
		int[] seq = {1,2,3,4};
		for ( int i : seq )
		     queue.enqueue(i);
		assertTrue(queue.size() == 4);

		for ( int i = 1; i< 10; i++ )
		{
		     if ( i<=4)
		          assertTrue(((Integer)queue.dequeue()).intValue() == i); // first in first out: 1,2,3,4
		     else
                    assertNull(queue.dequeue()); // should return null
		}    
		assertTrue(queue.size() == 0);    

		
	}

	@Test
	public void TestQueueOfString(){ // test with String value
		Queue queue = new Queue();
          
		assertNull(queue.dequeue()); // should return null

          assertTrue(queue.size() == 0);
          
		String[] seq = {"One","Two","Three"};
		for ( String i : seq )
		     queue.enqueue(i);
		assertTrue(queue.size() == 3);

          assertEquals(queue.dequeue(),"One");
          assertEquals(queue.dequeue(),"Two");
          assertEquals(queue.dequeue(),"Three");

          assertTrue(queue.size() == 0);

          assertNull(queue.dequeue()); // should return null
	}

} // End of TestQueue
