package main;

import java.util.Scanner;

public class FacultyAdmin extends Staff
{
	Controller controller;
	public FacultyAdmin(String id, String password, String first, String last, String dob, String title, Controller controller)
	{
		super(id, password, first, last, dob, title);
	}
	
	public FacultyAdmin(String id, String password, String first, String last, String dob,String title)
	{
		super(id, password, first, last, dob, title);
	}
	
	public void menu(Controller control)
	{
		Scanner kb = new Scanner(System.in);
		boolean leave = true;
		do
		{
			System.out.println("Welcome Faculty Admin "+ first + last +"!");
			System.out.println("---------------------------------------------");
			System.out.println("1. View student enrolment and course results");
			System.out.println("2. Log out");
			System.out.println("---------------------------------------------");
			System.out.print("Enter in an option >> ");
			int choice = kb.nextInt();
			kb.nextLine();
			switch(choice)
			{
				case 1: controller.viewStudentEnrolment(); break;	
				case 2: leave = false; break;
			}
		}
		while(leave);
		kb.close();
	}
	
	public String ToString()
	{			
		return super.ToString()+ ",facadmin";			
	}
}
