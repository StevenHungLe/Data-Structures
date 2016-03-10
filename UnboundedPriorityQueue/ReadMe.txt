Note: Please open this file with a programming editor to see proper formatting.


Name:  Hung Le
Email: hlle@uno.edu

The UnboundedPriorityQueue relies entirely on the implementation of MinHeap for its funtionalities.The MinHeap class is implemented following the guidelines in our textbook. Below are some notable design choices:

1. The Priority Queue supports a default constructor which requires no arguments and set the initial capacity to a default value of 10. It also support a constructor where a parameter as the desired initial capacity can be passed in. The MinHeap class supports two corresponding constructors.

2. The actual array that holds the items of the heap is always greater than the actual maximum size of the heap by one (size+1). This is intentional: Index 0 is meant to be the extra index which holds the sentinel value to break the loop in the insertion algorithm. Thus, the root of the heap is located at index 1 and the values are saved at later indices. This is a design choice that is not visible for the user: The heap will always appear to have the size specified.

3. Duplicates are allowed in this implementation of MinHeap, in which case the duplicated item stops percolating up if it encounters its duplicate as its parent. This is in accordance with the property of a Min Heap: every node is either less than or equal to their children.

4. The type variable T is specified to extend Comparable<? super T> so that it can support comparisons required in the implementation. The creation of an array of such generic type is carried out by creating a Comparble array and down-casting it back to T[].   

5. The MinHeap can automatically expands itself in case the user attempts to insert a new value to a heap that is already full. In this case, its new capacity will be twice as large as its previous capacity.

6. Removal of items from an empty priority queue/heap is not supported. In such case, an IndexOutOfBoundsException will be thrown.

