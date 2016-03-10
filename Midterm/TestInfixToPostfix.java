/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      TestInfixToPostfix - JUnit test for the infix to postfix algorithm
 **/

import static org.junit.Assert.*;
import org.junit.Test; 
import org.junit.Before;
import org.junit.After;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestInfixToPostfix{
    // Instance variables
    private InfixToPostfix ifToPf; // the object to run test on
    private char[] output;         // the output buffer

    /**
     * SetUp method that executes right before each test methods
     */
    @Before
    public void setUp()
    {
        ifToPf = new InfixToPostfix("input.txt");   
        this.setupTestInput(""); // to create the input file in case it's not created
        output = new char[1000];    
    }

    /**
     * setupTestInput - Helper method write the given String to the input file
     * @param : String testInput - the content to be written to the input file
     */
    private void setupTestInput(String testInput)
    {

          FileWriter writer = null;

          try // initilize the writer
          {
               writer = new FileWriter( new File( "input.txt" ) );
          }
          catch ( IOException e)
          {
               System.out.println( e.getMessage() );
               return;
          }

          try // write the test input to the file
          {
               writer.write(testInput);
               writer.flush();
          }
          catch ( IOException e)
          {
               System.out.println( e.getMessage() );
               return;
          }
          finally
          {
               try // close the writer
               {
                    writer.close();
               }
               catch ( IOException e)
               {
                    System.out.println( e.getMessage() );
                    return;
               }
          }
    }

    /**
     * newFileReader - Helper method to create a new instance of FileReader to examine the output
     * @Return: the FileReader instance created
     */
    private FileReader newFileReader()
    {
          try
          {
               return new FileReader( new File( "output.txt" ) );
          }
          catch ( FileNotFoundException e)
          {
               System.out.println( e.getMessage() );
               return null;
          }
    }

    /**
     * closeFileReader - Helper method to close a FileReader
     * @param : the file reader to be closed
     */
    private void closeFileReader(FileReader reader){

          try
          {
               reader.close();
          }
          catch ( IOException e)
          {
               System.out.println( e.getMessage() );
               return;
          }
    }

    /**
     * readFile - Helper method to read content from the output file
     * @param : FileReader reader- the reader used to read
     * the content is stored in the buffer output
     */
    private void readFile(FileReader reader){

          try
          {
               reader.read(output);
          }
          catch ( IOException e)
          {
               System.out.println( e.getMessage() );
               return;
          }
    }
    
    
    /**
     * Test the Infix to Postfix algorithm
     */
    @Test
    public void testConversion(){

    
          FileReader reader = null;

          /**
           * Each test case starts with setting up the input file
           * then conversion() method is called
           * a new file rader is created
           * the reader then start reading the output file
           * content is stored in the buffer output
           * the reader is then closed
           * assertEquals is called to verify if the program has written out the expected output
           **/

          // Test Case: single number
          this.setupTestInput("9");
          ifToPf.conversion();

          reader = newFileReader();
          readFile(reader);
          closeFileReader(reader);

          assertEquals((new String(output)).trim(),"9");


          // Test Case: single number with parentheses
          this.setupTestInput("(8)");
          ifToPf.conversion();

          reader = newFileReader();
          readFile(reader);
          closeFileReader(reader);

          assertEquals((new String(output)).trim(),"8");
          

          // Test Case: complicated expression
          this.setupTestInput("(8 + 5 + 9) -(8*5/6)");
          ifToPf.conversion();

          reader = newFileReader();
          readFile(reader);
          closeFileReader(reader);

          assertEquals((new String(output)).trim(),"8 5 + 9 + 8 5 * 6 / -");


          // Test Case: complicated expression with a bunch of parentheses
          this.setupTestInput("(((((((8 + 5 + 9)))))) -(8*5/6))");
          ifToPf.conversion();

          reader = newFileReader();
          readFile(reader);
          closeFileReader(reader);

          assertEquals((new String(output)).trim(),"8 5 + 9 + 8 5 * 6 / -");


          // Test Case: from the assignment examples
          this.setupTestInput("a + b * c + ( d * e + f ) * g");
          ifToPf.conversion();

          reader = newFileReader();
          readFile(reader);
          closeFileReader(reader);

          assertEquals((new String(output)).trim(),"a b c * + d e * f + g * +");


          // Test Case: super complicated expression
          this.setupTestInput("8-5+9*6-(9*5*6)/(((a*b/c)))");
          ifToPf.conversion();

          reader = newFileReader();
          readFile(reader);
          closeFileReader(reader);

          assertEquals((new String(output)).trim(),"8 5 - 9 6 * + 9 5 * 6 * a b * c / / -");

          
    }// end of method testConversion
   
    
}// end of class TestInfixToPostfix 
