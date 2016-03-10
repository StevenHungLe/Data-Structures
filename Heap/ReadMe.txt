Note: Please open this file with a programming editor to see proper formatting.


Name:  Hung Le
Email: hlle@uno.edu

The MaxHeap class is implemented following the guidelines in our textbook. Below are some notable properties:

1. The Startup class can take exactly one command-line argument to specify the size of the heap. If no command-line argument is provided, the size will has the default value of 10. Two or more command-line arguments are not supported.

2. The MaxHeap class also supports a default constructor which requires no arguments and set the size to the default value of 10.

3. The actual array that holds the items of the heap is always greater than the actual maximum size of the heap by one (size+1). This is intentional: Index 0 is meant to be the extra index which holds the sentinel value to break the loop in the insertion algorithm. Thus, the root of the heap is located at index 1 and the values are saved at later indices. This is a design choice that is not visible for the user: The heap will always appear to have the size specified.

4. Duplicates are allowed in this implementation of MaxHeap, in which case the duplicated item stops percolating up if it encounters its duplicate as its parent. This is in accordance with the property of a Max Heap: every node is either greater than or equal to their children.

5. The type variable T is specified to extend Comparable<? super T> so that it can support comparisons required in the implementation. The creation of an array of such generic type is carried out by creating a Comparble array and down-casting it back to T[].   

