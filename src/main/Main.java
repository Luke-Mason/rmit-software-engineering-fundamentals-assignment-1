package main;
/* Richard s3563242 - Assignment Contribution  */
import java.util.Scanner;

//main menu
public class Main
{
    static Controller control = new Controller();


    public static void main(String[] args){
        //scanner for user input
        
   	Scanner sc = new Scanner(System.in);


         // variables required to process user's menu selection
         String userInput;
         char selection = '\0';



         // keep repeating the menu until the user chooses to exit
         do
         {
            // display menu options
            System.out.println("Study Progess System Menu");
            System.out.println("-------------------------");
            System.out.println("1. Student Login");
            System.out.println("2. Staff Login");
            System.out.println("3. Exit Program");

            // prompt the user for input
            System.out.print("Enter your selection: ");
            
            userInput = sc.nextLine();

            System.out.println();

            // validate user has entered correct input
   	if (userInput.length() != 1)
            {
               System.out.println("Error - selection must be a single character!");

            }
            else
            {

               // convert to case-insensitive input
               selection = Character.toUpperCase(userInput.charAt(0));

               // process the user's selection
               switch (selection)
               {
                  case '1':
                     if (control.studentLogin())
                    	 control.studentMenu();
                     
          
                     
                     break;


                  case '2':

                     // call progCordLogin()
                     control.staffLogin();
                     control.staffMenu();
                     System.out.println("-");
                     break;

                  case '3':
                     System.out.println("Program Terminated!");
                     return;

                  default:

                     System.out.println("Error - invalid selection!");
               }
            }
            System.out.println();

         } while (selection != 'X');
   }





//student login 






}
