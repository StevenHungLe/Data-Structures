/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      InfixToPostfix - the class that contains the algorithm for Infix to Postfix conversion
 **/
 
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InfixToPostfix
{
     // Instance variables
     private String inputFile;  // the path of the input file
     private String outputFile; // the path of the output file
     private Stack<Character> operatorStack; // the stack to store operators

     /** 
      * Constructor - with 2 parameters
      * @Param: String inputFile - the path of the input file
      *         String outputFile- the path of the output file 
      **/
     public InfixToPostfix(String inputFile, String outputFile)
     {
          this.inputFile = inputFile;
          this.outputFile = outputFile;
          operatorStack = new Stack<Character>();
     }

     /** 
      * Constructor - with 1 parameter
      * @Param: String inputFile - the path of the input file
      * The output file is assigned the default name "output.txt"
      **/
     public InfixToPostfix(String inputFile)
     {
          this.inputFile = inputFile;
          this.outputFile = "output.txt";
          operatorStack = new Stack<Character>();
     }

     /** 
      * conversion - the method that carry out the infix to postfix conversion
      * read input from the input file, then write result to the output file
      **/
     public void conversion()
     {
          FileReader reader = null; // to read input
          FileWriter writer = null; // to write output
          
          try // try-catch block to initialize the reader
          {
               reader = new FileReader( new File( inputFile ) );
          }
          catch ( FileNotFoundException e)
          {
               System.out.println( e.getMessage() );
               return;
          }

          try // try-catch block to initialize the writer
          {
               writer = new FileWriter( new File( outputFile ) );
          }
          catch ( IOException e)
          {
               System.out.println( e.getMessage() );
               return;
          }

          
          int returnedValue;         // contains the int value read from a single call of reader.read()
          boolean firstWrite = true; // will be set to false after the first write, for formatting purpose
          Character symbol;          // the character symbol converted from the int value read
          Character poppedOperator;  // the operator popped from the stack
          
          try // try-catch block for read() operation
          {
               while( (returnedValue = reader.read()) != -1 )         // read until end-of-file is reached
               {
                    symbol = (Character.toChars(returnedValue))[0];   // convert the int value to character

                    if ( symbol.equals(' '))      // ignore blank spaces
                         continue;
                         
                    else if ( symbol.equals(')')) // if a right parenthese is read
                    {
                         do                       // in a loop...
                         {
                              poppedOperator = operatorStack.pop(); // pop an operator    
                              if ( !poppedOperator.equals('('))     // output the operator, unless it's the left parenthese
                              {
                                   System.out.print(" "+poppedOperator);
                                   try
                                   {
                                        writer.write(" "+poppedOperator);
                                        writer.flush();
                                   }
                                   catch ( IOException e)
                                   {
                                        System.out.println( e.getMessage() );
                                        return;
                                   }
                              }
                         }
                         while( !poppedOperator.equals('(')); // repeat this loop until the left parenthese is popped
                    }
                    // if any other operator is read
                    else if ( symbol.equals('*') || symbol.equals('/') || symbol.equals('+') || symbol.equals('-') || symbol.equals('('))
                    {
                         /**
                          * in a loop, pop and output all operators of higher precedence, unless it's a left parenthese
                          * the precedence of an operator is determined by the helper method precedence(operator)
                          **/
                         while( operatorStack.peek()!= null && precedence(symbol) <= precedence(operatorStack.peek()) &&  !operatorStack.peek().equals('(') )
                         {
                              System.out.print(" "+operatorStack.peek());
                              try
                              {
                                   writer.write(" "+operatorStack.pop());
                                   writer.flush();
                              }
                              catch ( IOException e)
                              {
                                   System.out.println( e.getMessage() );
                                   return;
                              }
                         }
                         operatorStack.push(symbol); // once done popping, push the symbol to the stack
                    }
                    
                    else // if an operand is read
                    {
                         if ( firstWrite == true )       // if this is the first write to the output file
                         {
                              System.out.print(symbol);
                              try
                              {
                                   writer.write(symbol); // write the operand without a blank space
                                   writer.flush();
                              }
                              catch ( IOException e)
                              {
                                   System.out.println( e.getMessage() );
                                   return;
                              }
                              firstWrite = false;        // from now on all symbols will be written with a leading blank space
                         }
                         else                            // when firstWrite is false
                         {
                              System.out.print(" "+symbol);
                              try
                              {
                                   writer.write(" "+symbol);
                                   writer.flush();
                              }
                              catch ( IOException e)
                              {
                                   System.out.println( e.getMessage() );
                                   return;
                              }
                          }
                    }
               } // end of the read-and-write while loop, meaning the end-of-file is reached
               
               while( operatorStack.peek() != null ) // pop all the remaining operators in the stack
               {
                    System.out.print(" "+operatorStack.peek());
                    try
                    {
                         writer.write(" "+operatorStack.pop());
                         writer.flush();
                    }
                    catch ( IOException e)
                    {
                         System.out.println( e.getMessage() );
                         return;
                    }
               }
                    System.out.println();

          }
          catch ( IOException e)
          {
               System.out.println( e.getMessage() );
               return;
          } // end try-catch block for read() operation

          
          try // try-catch block to close the reader
          {
               reader.close();
          }
          catch ( IOException e)
          {
               System.out.println( e.getMessage() );
               return;
          }

          try // try-catch block to close the writer
          {
               writer.close();
          }
          catch ( IOException e)
          {
               System.out.println( e.getMessage() );
               return;
          }
          
     } // end of conversion method

     /** 
      * precedence - helper method to determine the precedence of an operator
      * @Param : Character operator - the operator whose precendence to be determined
      * @Return: the precedence level of the operator
      **/
     private int precedence( Character operator )
     {
          /**
           * Operator    Precedence
           * +,-         0    (lowest)
           * *,/         1
           * (           2    (highest)
           **/
          if ( operator.equals('+') || operator.equals('-'))
               return 0;
          else if ( operator.equals('*') || operator.equals('/'))
               return 1;
          else
               return 2;
     }

     /** 
      * Stack<T> - inner class that defines a generic Stack Datatype
      * Supports peek, pop, and push operation
      **/
     private class Stack<T>{

	     private ArrayList <T> list; // the ArrayList to store the stack's data

          // Constructor
	     public Stack()
	     {
		     this.list = new ArrayList<T>();
	     }
          /** 
           * push - add an item to the top of the stack
           * @Param : T item - the item to be added
           **/
	     public void push ( T item )
	     {
		     this.list.add(item);	
	     }

	     /**
	      * pop - retrieve and remove the item on the top of the stack
	      * @ensure returns T or null;
	      */
	     public T pop()
	     {
		     if (this.list.size() == 0)
			     return null;
		     T temp = this.list.get(this.list.size()-1);
		     this.list.remove(this.list.size()-1);
		     return temp;
	     }

          /** 
           * peek - retrieve, but not remove, the item on the top of the stack
           * @ensure returns T or null;
           **/
	     public T peek()
	     {
		     if (this.list.size() == 0)
			     return null;
		     return this.list.get(this.list.size()-1);
	     }
     } // end of inner class Stack<T>
     
} // end of class InfixToPostfix
