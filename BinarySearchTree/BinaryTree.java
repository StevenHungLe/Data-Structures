/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      BinaryTree - a generic binary tree
 **/

import java.util.List;
import java.util.ArrayList;

public class BinaryTree<T>{
	BinaryNode<T> root = null;	
	
	private T nullSymbol = null;

	/**
	 * Default constructor
	 */
	public BinaryTree(){

	}

	/**
	 *	This constructor is useful for test purposes,
	 *  not meant for use in general.
	 *
	 *  Constructs a binary tree from a given valid breadth-first traversal sequence.
	 *  @param seq is an array containing breadth-first traversal sequence of the nodes of a tree.
	 */
	public BinaryTree(T[] seq){
		initFromBfsSequence(seq);
	}

	/**
	 *	This constructor is useful for test purposes,
	 *  not meant for use in general.
	 *
	 *  Constructs a binary tree from a given valid breadth-first traversal sequence. 
	 *	A given special symbol (nullSymbol) in the sequence is interpreted as absence of node. 
	 *	During construction of the tree, when such a special symbol is encountered, 
	 *	that is considered to be an absent node, and thus no corresponding node is added to the tree.
	 * 	
	 * 	@param seq is an array containing breadth-first traversal sequence of the nodes of a tree.
	 * 	@param nullSymbol is a special symbol in the given sequence that denotes absence of a node.
	 */
	public BinaryTree(T[] seq, T nullSymbol){
		this.nullSymbol = nullSymbol;
		initFromBfsSequence(seq);
	}

	private void initFromBfsSequence(T[] seq){
		if(seq.length == 0)
			throw new IllegalArgumentException("Cannot build tree from empty sequence");
		
		if(seq[0].equals(nullSymbol))
			throw new IllegalArgumentException("Symbol for root cannot be nullSymbol");
				
		List<BinaryNode<T>> nodes 
						= new ArrayList<BinaryNode<T>>(seq.length);
		this.root = new BinaryNode<T>(seq[0]);
		nodes.add(root);

		for(int i = 0; i < seq.length; i++){			
			if(nodes.get(i) == null){ 				
				handelNullParentNode(nodes, i, seq.length);				
			}else{				
				handleNonNullParentNode(nodes, i, seq);				
			}
		}		
	}

	private void handelNullParentNode(List<BinaryNode<T>> nodes, 
								int nullNodeIndex, int seqLength){
		int leftIndex = (nullNodeIndex * 2) + 1;
				
		if(leftIndex < seqLength){
			nodes.add(null);

			int rightIndex = (nullNodeIndex * 2) + 2;
			if(rightIndex < seqLength){
				nodes.add(null);
			}
		}
	}

	private void handleNonNullParentNode(List<BinaryNode<T>> nodes, 
								int parentIndex, T[] seq){
		int leftIndex = (parentIndex * 2) + 1;			
		if(leftIndex < seq.length){
			BinaryNode<T> leftNode = null;
			if(!seq[leftIndex].equals(nullSymbol)){
				leftNode = new BinaryNode<T>(seq[leftIndex]);
			}
			nodes.get(parentIndex).leftNode = leftNode;
			nodes.add(leftNode);

			int rightIndex = (parentIndex * 2) + 2;				
			if(rightIndex < seq.length){
				BinaryNode<T> rightNode = null;
				if(!seq[rightIndex].equals(nullSymbol)){
					rightNode = new BinaryNode<T>(seq[rightIndex]);
				}
				nodes.get(parentIndex).rightNode = rightNode;
				nodes.add(rightNode);			
			}
		}
	}

	public int height(){
		if (root == null) return 0;	
		return root.height();
	}

     //TODO
     /**
	 *  method to calculate the width of the tree
	 */
	public int width(){

		Queue<BinaryNode<T>> q1 = new Queue<BinaryNode<T>>(); // queue 1 to hold nodes at current level
		Queue<BinaryNode<T>> q2 = new Queue<BinaryNode<T>>(); // queue 2 to hold nodes at next level
          int width = 0;
          int maxWidth = 0;
          boolean isRowEmpty = false;
          BinaryNode<T> node = null;

          q1.enqueue(this.root);

          while(isRowEmpty == false) // loops as long as the next row is now empty
          {
               isRowEmpty = true;
               while ( q1.size() != 0 )
               {
                    node = q1.dequeue();     // dequeue the node in queue 1
                    width++;                 // increment width count
                    if(node.getLeftNode() != null) // enqueue the left child if not null
                    {
                         q2.enqueue(node.getLeftNode());
                         isRowEmpty = false;
                    }
                    if(node.getRightNode() != null)// enqueue the right child if not null
                    {
                         q2.enqueue(node.getRightNode());
                         isRowEmpty = false;
                    }
               }
               if ( width > maxWidth )       // update maxWidth
                    maxWidth = width;

               while ( q2.size() != 0 )      // move queue 2 to queue 1 for the next loop
               {
                    q1.enqueue(q2.dequeue());
               }

               width = 0; // reset width count
          }
		
		return maxWidth;
	}

     // TODO
     // call method breadthFirstTraverse from class BinaryNode
	public String breadthFirstTraverse(){
		return root.breadthFirstTraverse().trim();
	}

	public String preOrderTraverse(){
		return root.preOrderTraverse().trim();				
	}

	public String postOrderTraverse(){
		return root.postOrderTraverse().trim();
	}

	public String inOrderTraverse(){
		return root.inOrderTraverse().trim();
	}


	
	class BinaryNode<T>{
		private T data = null;
		private BinaryNode<T> leftNode = null;
		private BinaryNode<T> rightNode = null;

		public BinaryNode(T data){
			this.data = data;			
		}

		public String toString(){
			return "" + data;
		}

		public BinaryNode<T> getLeftNode(){
			return this.leftNode;
		}

		public BinaryNode<T> getRightNode(){
			return this.rightNode;
		}

		public void setLeftNode(BinaryNode<T> node){
			this.leftNode = node;
		}

		public void setRightNode(BinaryNode<T> node){
			this.rightNode = node;
		}

		public T getData(){
			return this.data;
		}

		public void setData(T data){
			this.data = data;
		}

		public int height(){
			if(isLeaf()) return 0;
			
			int leftHeight = 0;
			int rightHeight = 0;

			if(leftNode != null){ 
				leftHeight = leftNode.height();
			}

			if(rightNode != null){
				rightHeight = rightNode.height();
			}
			
			int maxHeight = leftHeight > rightHeight? leftHeight: rightHeight;

			return maxHeight + 1 ;
		}

		public boolean isLeaf(){
			return (leftNode == null && rightNode == null);
		}


          /**TODO
	      *  recursively construct the breadth-first traversal sequence for the tree
	      *  The Algorithm is identical to the calculation of width, but append the data to the string every time a node is dequeued from queue 1
	      *  @return the string representation of the breadth-first traversal
	      */
          public String breadthFirstTraverse(){
			StringBuilder stringBuffer = new StringBuilder();			
			
			Queue<BinaryNode<T>> q1 = new Queue<BinaryNode<T>>();
		     Queue<BinaryNode<T>> q2 = new Queue<BinaryNode<T>>();
               boolean isRowEmpty = false;
               BinaryNode<T> node = null;

               q1.enqueue(this);

               while(isRowEmpty == false)
               {
                    isRowEmpty = true;
                    while ( q1.size() != 0 )
                    {
                         node = q1.dequeue();
                         
                         stringBuffer.append(" " + node.data); // append data to the string
                         
                         if(node.getLeftNode() != null)
                         {
                              q2.enqueue(node.getLeftNode());
                              isRowEmpty = false;
                         }
                         if(node.getRightNode() != null)
                         {
                              q2.enqueue(node.getRightNode());
                              isRowEmpty = false;
                         }
                    }

                    while ( q2.size() != 0 )
                    {
                         q1.enqueue(q2.dequeue());
                    }
               }

			return stringBuffer.toString();				
		}
		
		public String preOrderTraverse(){
			StringBuilder stringBuffer = new StringBuilder();			
			
			stringBuffer.append(" " + data);
			
			if(leftNode != null){
				stringBuffer.append(leftNode.preOrderTraverse());				
			}
			
			if(rightNode != null){
				stringBuffer.append(rightNode.preOrderTraverse());
			}

			return stringBuffer.toString();				
		}

		public String postOrderTraverse(){			
			StringBuilder stringBuffer = new StringBuilder();
			
			if(leftNode != null){
				stringBuffer.append(leftNode.postOrderTraverse());
			}

			if(rightNode != null){
				stringBuffer.append(rightNode.postOrderTraverse());
			}

			stringBuffer.append(" " + data);			

			return stringBuffer.toString();	
		}

		public String inOrderTraverse(){	
			// The following null pointer checking was missed out
			// while coding in class, and thus we got null-pointer exceptions 
			// See, a common source of error!

			StringBuilder stringBuffer = new StringBuilder();			

			if(leftNode != null){ 
				stringBuffer.append(leftNode.inOrderTraverse());
			}

			stringBuffer.append(" " + data);			

			if(rightNode != null){
				stringBuffer.append(rightNode.inOrderTraverse());
			}

			return stringBuffer.toString();		
		}
	}
}
