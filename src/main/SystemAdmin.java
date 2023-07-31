package main;

import java.util.Scanner;
public class SystemAdmin extends Staff
{
	Controller controller;
  public SystemAdmin(String id, String password, String first, String last, String dob, String type, Controller controller)
  {
    super(id, password, first, last, dob, type);
  }
  public SystemAdmin(String id, String password, String first, String last, String dob,String title)
 	{
 		super(id, password, first, last, dob, title);
 	}
  public void menu(Controller control)
	{
		Scanner kb = new Scanner(System.in);
		boolean leave = true;
		do
		{
			System.out.println("Welcome " + title +" "+ first +" " + last +"!");
			System.out.println("---------------------------------------------");
			System.out.println("1. Create a program");
			System.out.println("2. Change a program details");
			System.out.println("3. Create student account(s)");
			System.out.println("4. Enrol student(s) to a program ");
			System.out.println("5. Create a new Staff Member");
			System.out.println("6. Log out");
			System.out.println("---------------------------------------------");
			System.out.print("Enter in an option >> ");
			int choice = kb.nextInt();
			kb.nextLine();
			switch(choice)
			{
				case 1: break;
				case 2: controller.changeProgramDetails(); break;
				case 3: System.out.print("Enter in the amount of student accounts you want to create >> ");
					int amount = kb.nextInt();
					kb.nextLine();
					control.createStudentAccount(amount); break;
				case 4: break;
				case 5: System.out.print("Enter in the role of staff you want to create >> ");
				String role = kb.nextLine();
				control.createRole(role); break;
				case 6: leave = false; break;
			}
		}
		while(leave);
		kb.close();
	}
	
		public String ToString()
	{			
		return super.ToString() + ",sysadmin";			
	}
}
