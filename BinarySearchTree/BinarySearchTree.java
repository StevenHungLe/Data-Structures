/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      BinarySearchTree - a generic binary search tree
 **/

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BinarySearchTree<T> extends BinaryTree<T>
{
     // default constructor
     public BinarySearchTree(){
	}

	// constructor that takes a sequence of value as parameter
	public BinarySearchTree(T[] seq){
	     for ( T i: seq)
		     insert(i);
	}

	/**
	 *  constructor
	 *  @param  seq : a sequence of value to insert into the tree
	 *          nullSymbol: the value that specifies a null which shouldn't be added   
	 */
	public BinarySearchTree(T[] seq, T nullSymbol){
		for ( T i: seq)
		{
		     if( i != nullSymbol )
		          insert(i);
		}
	}

     /**
	 *  method to insert a value into the tree
	 *  @param  value : the value to be inserted 
	 */
     public void insert( T value )
     {
          if ( this.root == null )  // empty tree
               this.root = new BinaryNode<T>(value);
          else if ( contains(value))// skip duplicated value
          {
               System.out.println("Duplicated value! Skipped");
               return;
          }
          else // general cases
          {
               BinaryNode<T> current = this.root; // current : the pointer that will run through the tree
               
               while(true) // loops until reach break statement
               {
                    if( compare((Integer)current.getData(),(Integer)value)< 0) // root.data < value
                    {
                         if( current.getLeftNode() == null ) // insert if left tree is null
                         {
                              current.setLeftNode(new BinaryNode<T>(value));
                              break;
                         }
                         else // set current to left tree
                              current = current.getLeftNode();
                    } 
                    else // root.data > value
                    {
                         if( current.getRightNode() == null ) // insert if right tree is null
                         { 
                              current.setRightNode(new BinaryNode<T>(value));
                              break;
                         }
                         else // set current to left tree
                              current = current.getRightNode();
                    }
                    
               }
          }

     }

     /**
	 *  method to remove a value from the tree
	 *  @param  value : the value to be removed 
	 */
     public void remove( T value )
     {
          if ( !contains(value)) // throws exception if the value to remove is not in the tree
               throw new NoSuchElementException("\nThere is no such element as "+value+" in the tree.");

               
          BinaryNode<T> toRemove = null;          // the node to remove
          BinaryNode<T> parentOfToRemove = null;  // the parent of the node to remove
          String toRemoveLocation = "";           // specifying whether toRemove is a left child or a right child
          
          if ( this.root.getData() == value ) // edge case: the value to remove is the root
          {
               toRemove = this.root;
          }
          else // the value to remove is not the root
          {
               parentOfToRemove = getParent( this.root, value ); // find the parent
               toRemoveLocation = getToRemoveLocation( parentOfToRemove, value ); // left child or right child
               toRemove = toRemoveLocation.equals("left")?parentOfToRemove.getLeftNode():parentOfToRemove.getRightNode(); // find the node to remove
          }
           
               
          switch (numberOfChildren(toRemove)) // switch the number of children that the node to remove has
          {
          case 0: // the node is a leaf
          if ( toRemoveLocation.equals("left")) // the node to remove is a left child
          {
               parentOfToRemove.setLeftNode( null ); 
               toRemove = null;
          }
          else // the node to remove is a right child
          {
               parentOfToRemove.setRightNode( null ); 
               toRemove = null;
          }
          break;
          
          case 1: // the node has only one child

          BinaryNode<T> childOfToRemove = (toRemove.getLeftNode()!=null)?toRemove.getLeftNode():toRemove.getRightNode(); // the child of the node to remove
          if ( toRemove == this.root ) // edge case : the node to remove is the root
          {
               this.root= childOfToRemove; // the child is now the new root
          }
          else // general case
          {  
               if ( toRemoveLocation.equals("left")) // the node to remove is a left child
               {
                    parentOfToRemove.setLeftNode( childOfToRemove ); // connect the parent to its new child
                    toRemove = null;
               }
               else // the node to remove is a right child
               {
                    parentOfToRemove.setRightNode( childOfToRemove ); // connect the parent to its new child
                    toRemove = null;
               }
          }
          break;
          
          case 2: // the node has two children
          BinaryNode<T> toReplace = getSmallestNode(toRemove.getRightNode()); // find the smallest node in the right subtree
          T temp = toReplace.getData(); // save the data of the node to replace
          remove(toReplace.getData());  // recursively remove the node to replace
          toRemove.setData(temp);       // overwrite the node-to-remove's data with the node-to-replace's data 
          
          break;
          }
          
     }

     /**
	 *  helper method to find the smallest node in a subtree
	 *  @param  subtree : the subtree to search in
	 *  @return: the smallest node in the subtree
	 */
     public BinaryNode<T> getSmallestNode( BinaryNode<T> subtree )
     {
          if( subtree.getLeftNode() == null )
               return subtree;
          else
               return getSmallestNode(subtree.getLeftNode());
     }
     
     /**
	 *  helper method to find the number of children that a node has
	 *  @param  node : the node to inpect
	 *  @return: the number of children
	 */
     public int numberOfChildren( BinaryNode<T> node )
     {
          int count = 2;
          if ( node.getRightNode() == null )
               count--;
          if ( node.getLeftNode() == null )
               count--;
          return count;
     }

     /**
	 *  method to check if the tree contains the given value
	 *  @param  value : the value to check
	 *  @return: true if found, otherwise false
	 */
     public boolean contains(  T value )
     {
          return contains( this.root, value);
     }

     /**
	 *  method to check if a subtree contains the given value
	 *  @param  value : the value to check
	 *          root: root of the subtree to search in
	 *  @return: true if found, otherwise false
	 */
	public boolean contains( BinaryNode<T> root, T value )
	{
	     int compareResult = compare((Integer)root.getData(),(Integer) value );

	     
	     if(  compareResult == 0 )
	          return true;
	          
	     else if( compareResult < 0 && root.getLeftNode() != null )
	          return contains(root.getLeftNode(),value);

	     else if( compareResult > 0 && root.getRightNode() != null )
	          return contains(root.getRightNode(),value);
	     else
	          return false;  
	}

     /**
	 *  method to check whether a node is a left child or a right child
	 *  @param  value : the value to check
	 *          root: the root of the subtree to check in 
	 *  @return: the string specifying the location of the node
	 */
	public String getToRemoveLocation( BinaryNode<T> root, T value )
	{
	     return (compare((Integer)root.getLeftNode().getData(),(Integer) value) == 0 )?"left":"right";
	}


     /**
	 *  method to retrieve the parent of a given node
	 *  @param  value : the value to find parent for
	 *          root: the root of the subtree to search in 
	 *  @return: the parent of the given node
	 */
	public BinaryNode<T> getParent( BinaryNode<T> root, T value )
	{

	     if (   compare((Integer)root.getLeftNode().getData(),(Integer) value) == 0 
	         || compare((Integer)root.getRightNode().getData(),(Integer) value) == 0 )
	     {
	          return root;
	     }
	      
	     int compareResult = compare((Integer)root.getData(),(Integer) value );      
	     if( compareResult < 0 && root.getLeftNode() != null )
	          return getParent(root.getLeftNode(),value);

	     else if( compareResult > 0 && root.getRightNode() != null )
	          return getParent(root.getRightNode(),value);
	     else
	          return null;  
	}

	/**
	 *  method to compare two values
	 *  @param  int1 : the first value
	 *          int2 : the second value
	 *  @return: negative number if second<first, 0 if equal, otherwise positive 
	 */
     private int compare( Integer int1, Integer int2 )
     {
          return int2.intValue() - int1.intValue();
     }
	
} // end of BinarySearchTree
