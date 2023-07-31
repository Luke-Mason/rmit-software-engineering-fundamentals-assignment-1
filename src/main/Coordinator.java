package main;

import java.util.Scanner;
public class Coordinator extends Staff
{

		
		public Coordinator(String id, String password, String first, String last, String dob,String title)
		{
			super(id, password, first, last, dob, title);
		}
		
		public String ToString()
		{			
			return super.ToString() + ",coordinator";			
		}
		
		public void menu(Controller control)
		{
			Scanner kb = new Scanner(System.in);
			boolean leave = true;
			do
			{
				System.out.println("Welcome Co-ordinator "+ first +" "+ last +"!");
				System.out.println("---------------------------------------------");
				System.out.println("1. Generate Report");//
				System.out.println("2. View student enrolment and course results");
				System.out.println("3. Change a program details");
				System.out.println("4. Create student account(s)");
				System.out.println("5. Enrol student(s) to a program ");
				System.out.println("6. Log out");
				System.out.println("---------------------------------------------");
				System.out.print("Enter in an option >> ");
				int choice = kb.nextInt();
				kb.nextLine();
				
				System.out.println("made it past menu");
				
				switch(choice)
				{
					case 1: control.generate_report(); break;
					case 2: control.viewStudentEnrolment(); break;
					case 3: control.changeProgramDetails(); break;
					case 4: 
						System.out.print("Enter in the amount of student accounts you want to create >> ");
						int amount = kb.nextInt();
						kb.nextLine();
						control.createStudentAccount(amount); break;
					case 5: break;
					case 6: leave = false; break;
				}
			}
			while(leave);
			kb.close();
		}
}

	
	
	/*public static void changeCreditPoint(Program program){
	    Scanner scanner = null;
	    
	try{
	    scanner = new Scanner(System.in);
	    System.out.println("Enter 1 for 25 points or enter 2 for 12.5 points or enter 3 for 0 points : ");
	    int inputSelection = scanner.nextInt();
	    
	    switch (inputSelection){
	    	case 1:
	    	  System.out.println("25 Credit Points");
	    	  program.setCreditPoint(25.0);
	    	  break;
	    	case 2:
	    	  System.out.println("12.5 Credit Points");
	    	  program.setCreditPoint(12.5);
	    	  break;
	    	case 3:
	    	  System.out.println("25 Credit Points");
	    	  program.setCreditPoint(0.0);
	    	  break;
	        default:
	    	  System.out.println("Invalid");
	    	  break; 
	    }
	}finally{
		if(scanner!=null)
		scanner.close();
		}
	}*/
