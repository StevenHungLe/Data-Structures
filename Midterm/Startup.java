/** 
 * Name: 	     Hung Le
 * Professor: 	Dr. Minhaz F. Zibran
 * Course: 	CSCI2125
 * Class:      Startup - the starting point to make use of the infix to postfix algorithm
 **/

public class Startup
{
     public static void main (String[] args )
     {
          InfixToPostfix ifToPf;
          if ( args.length < 1 || args.length > 2 ) // if command line argument is not entered properly
          {
               System.out.println("Invalid command line arguments");
               return;
          }

          if ( args.length == 1 )  // if only the input file name is entered
               ifToPf = new InfixToPostfix(args[0]);
          else                     // if both input and output file name are entered
               ifToPf = new InfixToPostfix(args[0],args[1]);
          
          ifToPf.conversion();     // call method conversion to make use of the algorithm
     }
}// end of class Startup
