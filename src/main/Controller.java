package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.lang.*;
import java.text.SimpleDateFormat;

import txt_io.*;

public class Controller
{
	TextFile f_users = new TextFile("database//Users.txt");
	TextFile f_results = new TextFile("database//Result.txt");
	TextFile f_courseOffers = new TextFile("database//CourseOffer.txt");
	TextFile f_courseDefinitions = new TextFile("database//CourseDefinition.txt");
	TextFile f_records = new TextFile("database//Record.txt");
	TextFile f_programs = new TextFile("database//Program.txt");
	TextFile f_programs_courses = new TextFile("database//ProgramCourse.txt");
	TextFile f_enrolments = new TextFile("database//Enrolments.txt");
	TextFile f_student_course = new TextFile("database//StudentCourse.txt");

	private Program[] programs;
	private Student[] students;
	private Staff[] staff; 
	private CourseOffer[] courses;

	public int numPrograms() { return programs.length; }
	public int numStudents() { return students.length; }
	public int numStaff() { return staff.length; }
	public int numCourseOffers() { return courses.length; }

	public Program getProgram(int i) { return programs[i]; }	
	public Student getStudent(int i) { return students[i]; }	
	public Staff getStaff(int i) { return staff[i]; }
	public CourseOffer getCourseOffer(int i ) { return courses[i]; }

	Scanner kb;

	private Student currentUser;
	private Staff currentStaff;

	
	//////////////////////////////////////////////////
	//////////////////////////////////////////////////
	// COMMUNAL ////////////////////////////
	
	public boolean studentLogin(){

		//instantiate controller


		//scanner for user input
		Scanner sc = new Scanner(System.in);

		// prompt the user for login credentials
		System.out.println("Enter Username: ");
		String username = sc.nextLine();

		System.out.println("Enter Password");
		String password = sc.nextLine();

		// step through the array of student objects
		// validate input for student is valid
		for(int i=0; i< numStudents(); i++){
			if (getStudent(i).getId().compareTo(username)==0 && 
					getStudent(i).getPassword().compareTo(password)==0){
				//find matching student
				currentUser = getStudent(i);	
				// menu for student
				System.out.println("\nWelcome " + getStudent(i).getFirstName());
				return true;
			}
		}
		System.out.println("Invalid Username or Password");
		return false;
	}
	public void staffLogin(){
		//scanner for user input
		Scanner sc = new Scanner(System.in);

		// prompt the user for login credentials
		System.out.println("Enter Username: ");
		String username = sc.nextLine();

		System.out.println("Enter Password");
		String password = sc.nextLine();
		// need to validate input for program coordinator is valid
		for(int i=0; i<numStaff();i++){
			if (getStaff(i).getId().compareTo(username)==0 &&
					getStaff(i).getPassword().compareTo(password)==0){
				//find matching student
				currentStaff = getStaff(i);
				// menu for student
				System.out.println("Welcome " + currentStaff.getFirstName());
				return;
			}

		}


		System.out.println("Invalid Username or Password");
	}
	public void studentMenu() 
	{
		while (true)
		{
			switch (currentUser.menu(this))
			{
			case 1:
				view_enrolment();
				break;

			case 2: 
				view_record();
				break;

			case 3: 
				view_progress();
				break;

			default: return;
			}
		}
	}
	public void staffMenu() 
	{
		currentStaff.menu(this);
	}
	
	public Controller()
	{
		load();
		//save();
		kb = new Scanner(System.in);
	}

	// END ///////////////////////////////////////////
	//////////////////////////////////////////////////
	//////////////////////////////////////////////////
	
	
	
	//////////////////////////////////////////////////
	//////////////////////////////////////////////////
	// LUKE CONTRIBUTION ////////////////////////////
	
	public boolean exit(String keyboard)
	{
		if(keyboard.equals(""))
		{
			System.out.println("Exiting out...\n\n");
			return true;
		}
		return false;
	}
	public int searchStudent()
	{//	  boolean found = false;
		int pos = 0;
		do
		{
			System.out.print("Please enter in Student ID >> ");
			// isn't waiting
			String studentID = kb.nextLine();
			pos = findStudentByID(studentID);
			if(pos == -1)//checking to see if ID has matched and outputs error message
			{
				System.out.println("Student ID "+ studentID +" was not found in the database\nPlease try again");
				continue;
			}
			break;
		}
		while(true);
		return pos;
	}
	public int findStudentByID(String studentID) 
	{
		int pos = -1;
		for(int j = 0; j<numStudents(); ++j)
		{
			if(studentID.equalsIgnoreCase(students[j].getId()))//Checking for valid Student ID against ID's in student array
			{
				pos = j;
				break;
			}
		}
		return pos;
	}
	public boolean programPassed(int index)
	{
		int pos = index;
		System.out.println("in programPassed");
		if(students[pos].getRecord().currentCreditPointsEarned() >= students[pos].getEnrolment(0).getProgram().getCreditPoint())
		{
			return true;
		}
		return false;
	}
	public void generate_report()
	{

		System.out.println("in generate report");

		int amount;

		do
		{
			System.out.print("Please enter in the amount of students you wish to report on >> ");
			amount = kb.nextInt();
			kb.nextLine();
		} while(amount > numStudents());

		String[] generateReport = new String[amount];

		int pos = 0;

		for(int i = 0; i<amount; ++i)
		{
			pos = searchStudent();
			String outcome = (programPassed(pos)) ? "met program requirements \nCAN GRADUATE\n":"not met program requirements - "+students[pos].getRecord().currentCreditPointsEarned()+" out of "+students[pos].getEnrolment(0).getProgram().getCreditPoint()+" credit points earned\nCANNOT GRADUATE\n";
			generateReport[i] = "Student - "+students[pos].getId()+" has "+outcome;
		}
		System.out.println("~~~GRADUATE REPORT~~~");

		for(int k = 0; k<=amount-1; k++)
		{
			System.out.println(generateReport[k]);
		}

	}
	public boolean amountOfStudents(int amount) 
	{
		if(amount > numStudents() || amount <= 0)//checking if amount of students is valid
		{
			System.out.print("Amount of students entered is invalid, Please try again");
			return false;
		}
		return true;
	}
	public void courseResults(int index)
	{
		int pos = index;
		boolean result;
		System.out.println("\n\nStudent Program: "+students[pos].getEnrolment(0).getProgram().getProgName());
		for(int i=0; i<students[index].getEnrolment(0).getCourseOffers().length; i++)
		{
			String grade = students[pos].getRecord().getResult(i).getGrade();
			int score = students[pos].getRecord().getResult(i).getMark();
			int total = 100;
			System.out.println("Course "+(i+1)+": " +students[pos].getEnrolment(0).getCourseOffer(i).getCourseDefinition().getCourseName()+" - Current Mark: "+score+"/"+total+" = "+ grade);
			if(grade.equals("NN"))
			{
				result = false;
			}
			else
			{
				result = true;
			}
			String outcome = (result) ? "	Successfully meets course requirements\n":"	does not meet course requirements\n";
			System.out.println(outcome);
		}
	}	
	public void viewStudentEnrolment()
	{
		int pos = searchStudent();
		courseResults(pos);
	}
	public void viewMyEnrolment(String id)
	{
		int pos = findStudentByID(id);
		courseResults(pos);
		String outcome = (programPassed(pos)) ? "You have successfully met program requirements \nCAN GRADUATE\n":"you have not met program requirements \nCANNOT GRADUATE\n";
		System.out.println(outcome);
	}
	public boolean changeProgramDetails()
	{
		boolean leave = true;
		int pos = 0;
		boolean found = false;
		System.out.println("Please enter in a program's name to edit >> ");
		String progName = kb.nextLine();
		for(int i = 0; i < numPrograms(); i++)
		{
			if(progName.equalsIgnoreCase(programs[i].getProgName()))
			{
				found = true;
				pos = i;
				break;
			}
		}
		if(found == false)
		{
			System.out.println("Program "+progName+" does not exist");
			return false;
		}
		do
		{
			System.out.println("---------------------------------------------");
			System.out.println("1. Change "+programs[pos].progName+" credit points");
			/*System.out.println("2. Change "+programs[pos].progName+" core course");
			System.out.println("3. Change "+programs[pos].progName+" specialisation course");*/
			System.out.println("2. Change "+programs[pos].progName+" courses");
			System.out.println("3. Go Back");
			System.out.println("---------------------------------------------");
			System.out.print("Enter in an option >> ");
			int choice = kb.nextInt();
			kb.nextLine();
			switch(choice)
			{
			case 1: 
				System.out.print("Enter in the new amount of credits for the program >> ");
				int newCreditPoints = kb.nextInt(); 
				kb.nextLine(); 
				programs[pos].setCreditPoint(newCreditPoints);
				System.out.println("SUCCESS: Credit points set to "+newCreditPoints); 
				break;
			case 2:
				System.out.print("Enter in the new core courses for the program >> ");
				//Do the complicated shit for entering 0 - 4 cores
				/*String newCorecourses = kb.nextLine(); 
						program[pos].setCoreCourses(newCorecourses);
						System.out.println("SUCCESS: Credit points set to "+newCreditPoints);  
						if(program[pos].totalCourses != 4)//This is so that a program still has 4 courses when core courses change
						{

						}*/break;
			case 3: leave = false; break;
			}
		}
		while(leave);
		return true;
	}
	public boolean createStudentAccount(int amount)
	{

		for(int i = 0; i<amount; i++)
		{
			boolean out = false;
			String dob;
			String id;
			String firstName;
			String lastName;
			String title;
			String password;
			do
			{
				out = false;
				System.out.print("Student "+(i+1)+" - Enter in ID >> ");
				id = kb.nextLine();
				if(exit(id)==true){return false;}
				if(findStudentByID(id)>=0)
				{
					System.out.println("Student: " + id + " already exists in the system");
					out = true;
				}
			}
			while(out);
			do
			{
				System.out.print("Student "+(i+1)+" - Enter in password >> ");
				password = kb.nextLine();
				System.out.print("Student "+(i+1)+" - Confirm password >> ");
				String confirm = kb.nextLine();
				if(exit(confirm)==true){return false;}
				if(password.equals(confirm)==false)
				{
					System.out.println("Error - Passwords do not match, try again");
					out = true;
				}
			}
			while(out);
			do
			{
				out = false;
				System.out.print("Student "+(i+1)+" - Enter in first name >> ");
				firstName = kb.nextLine();
				if(exit(firstName)==true){return false;}
				if(firstName.matches(".*\\d+.*"))//Checking for numbers in string name
				{
					System.out.println("Student name contains numerical data.   \""+firstName+"\"");
					out = true;
				}
			}
			while(out);
			do
			{
				out = false;
				System.out.print("Student "+(i+1)+" - Enter in last name >> ");
				lastName = kb.nextLine();
				if(exit(lastName)==true){return false;}
				if(lastName.matches(".*\\d+.*"))//Checking for numbers in string name
				{
					System.out.println("Student name contains numerical data.   \""+lastName+"\"");
					out = true;
				}
			}
			while(out);
			do
			{
				out = false;
				System.out.print("Student "+(i+1)+" - Enter in Date Of Birth (day/month/year) >> ");
				dob = kb.nextLine();
				if(exit(dob)==true){return false;}
				out = false;
			}
			while(out);
			do
			{
				out = false;
				System.out.print("Enter in Student Title (i.e Sir, Mr, Ms, Mrs) >> ");
				title = kb.nextLine();
				if(exit(title)==true){return false;}
				if(title.matches(".*\\d+.*"))
				{
					System.out.println("ERROR: A Title cannot contain any digits, try again");
					out = true;
				}
			}
			while(out);
			Enrolment[] enrolments = null;
			Record record = null;
			ArrayList<Student> stud = new ArrayList<Student>();	
			stud.add(new Student(id, password, firstName, lastName, dob, title, record, enrolments));
			students = stud.toArray(new Student[stud.size()]);
			save();
			System.out.println("\nStudent Account created!\n");
		}
		return true;
	}
	public boolean createRole(String role)
	{
		String dob;
		String id;
		String firstName;
		String lastName;
		String title;
		String job;
		String password;
		switch(role)
		{
			case "coordinator": job = "Co-ordinator"; break;
			case "systemadmin": job = "System Administrator";break;
			case "facultyadmin": job = "Faculty Administrator";break;
			default: return false;
		}
		boolean out = false;
		do
		{
			out = false;
			System.out.print(job+" - Enter in an ID >> ");
			id = kb.nextLine();
			if(exit(id)==true){return false;}
			if(findRoleByID(id)>=0)
			{
				System.out.println("ID: "+id + " already exists in the system");
				out = true;
			}
		}
		while(out);
		do
		{
			System.out.print(job+": - Enter in password >> ");
			password = kb.nextLine();
			if(exit(password)==true){return false;}
			System.out.print(job+": - Confirm password >> ");
			String confirm = kb.nextLine();
			if(exit(confirm)==true){return false;}
			if(password.equals(confirm)==false)
			{
				System.out.println("Error - Passwords do not match, try again");
				out = true;
			}
		}
		while(out);
		do
		{
			out = false;
			System.out.print(job+": - Enter in first name >> ");
			firstName = kb.nextLine();
			if(exit(firstName)==true){return false;}
			if(firstName.matches(".*\\d+.*"))//Checking for numbers in string name
			{
				System.out.println("Name contains numerical data. -  \""+firstName+"\"");
				out = true;
			}
		}
		while(out);
		do
		{
			out = false;
			System.out.print(job+": - Enter in last name >> ");
			lastName = kb.nextLine();
			if(exit(lastName)==true){return false;}
			if(lastName.matches(".*\\d+.*"))//Checking for numbers in string name
			{
				System.out.println("Name contains numerical data. -  \""+lastName+"\"");
				out = true;
			}
		}
		while(out);
		do
		{
			out = false;
			System.out.print(job+" - Enter in Date Of Birth (day/month/year) >> ");
			dob = kb.nextLine();
			if(exit(dob)==true){return false;}
			out = false;
		}
		while(out);
		do
		{
			out = false;
			System.out.print("Enter in Staff Title (i.e Sir, Mr, Ms, Mrs) >> ");
			title = kb.nextLine();
			if(exit(title)==true){return false;}
			if(title.matches(".*\\d+.*"))
			{
				System.out.println("ERROR: A Title cannot contain any digits, try again");
				out = true;
			}
		}
		while(out);
		switch(role)
		{
			case "coordinator": 
			ArrayList<Staff> staf = new ArrayList<Staff>();	
			staf.add(new Staff(id, password, firstName, lastName, dob, title));
			students = staf.toArray(new Student[staf.size()]);
		
			save();
			break;
			
			case "systemadmin": job = "System Administrator";break;
			
			
			case "facultyadmin": job = "Faculty Administrator";break;
			
			
			default: return false;
		}
		return true;
	}
	public int findRoleByID(String staffID) 
	{
		int pos = -1;
		for(int j = 0; j< staff.length; ++j)
		{
			if(staffID.equalsIgnoreCase(staff[j].getId()))//Checking for valid Staff ID against ID's in staff array
			{
				pos = j;
				break;
			}
		}
		return pos;
	}
	// END ///////////////////////////////////////////
	//////////////////////////////////////////////////
	//////////////////////////////////////////////////
	

	
	//////////////////////////////////////////////////
	//////////////////////////////////////////////////
	// JAKE 25 SEP ///////////////////////////////////
	private void view_record() 
	{
		Record record = currentUser.getRecord();
		int time;
		ArrayList<Result> timed_results = new ArrayList<Result>();
		Date date = new Date();
		SimpleDateFormat yyyy = new SimpleDateFormat("y");
		String dateString = yyyy.format(date).toString();
		dateString = Integer.toString(Integer.parseInt(dateString) + 1);
		String starting_year = "2000";
		String starting_semester = "1";
		System.out.println("Results\n");
		while (!starting_year.equals(dateString))
		{
			timed_results = results_from(starting_year, starting_semester, record.getResults());
			int semester = Integer.parseInt(starting_semester);
			int year = Integer.parseInt(starting_year);
			if (timed_results.size() > 0)
				System.out.println("Semester " + semester + " " + year);
			for (int i = 0; i < timed_results.size(); i++)
			{	
				System.out.print(timed_results.get(i).getCourseOffer().getCourseDefinition().getCourseCode() + " - ");
				System.out.print(timed_results.get(i).getCourseOffer().getCourseDefinition().getCourseName());
				System.out.println("\t\t" + timed_results.get(i).getMark() + "\t" + timed_results.get(i).getGrade() + "\n");
			}
			if (semester == 2)
			{
				year++;
				semester = 1;
			}
			else semester++;
			starting_semester = Integer.toString(semester);
			starting_year = Integer.toString(year);
		}
		double gpa = record.getGPA();
		int wam = record.getWAM();
		System.out.println("\nGPA: " + gpa +"\tWAM: " + wam);
		System.out.println("\n");
	}
	public ArrayList<Result> results_from(String year, String semester, Result[] results)
	{
		ArrayList<Result> result = new ArrayList<Result>();
		for (int i = 0; i < results.length; i++)
		{
			if (results[i].getCourseOffer().getYear().equals(year) && results[i].getCourseOffer().getSemester().equals(semester))
			{
				result.add(results[i]);
			}		
		}
		return result;
	}
	private void view_progress() 
	{
		Enrolment enrolment = currentUser.getCurrentEnrolment();
		Program program = enrolment.getProgram();
		double credit_needed = program.getCreditPoint();
		double credit_obtained = currentUser.get_current_credit_total();
		credit_needed -= credit_obtained;
		System.out.println("\nCredit points recieved: " + credit_obtained);
		System.out.println("Credit points remaining: " + credit_needed);
		CourseDefinition[] outstanding = currentUser.get_outstanding_courses(program);
		System.out.println("\nOutstanding Courses");
		for (int i = 0; i < outstanding.length; i++)
		{
			System.out.println(outstanding[i].getCourseCode() + " - " + outstanding[i].getCourseName());
		}
	}
	public void view_enrolment()
	{
		// print 
		// 'enter x to add class', etc..
		// done
		Enrolment enrolment = currentUser.getCurrentEnrolment();
		Scanner scanner = new Scanner(System.in);
		ArrayList<CourseOffer> current = new ArrayList<CourseOffer>(); 
		ArrayList<CourseOffer> past = new ArrayList<CourseOffer>(); 
		ArrayList<CourseOffer> future = new ArrayList<CourseOffer>(); 
		Date date = new Date();
		SimpleDateFormat mmyyyy = new SimpleDateFormat("M,y");
		String dateString = mmyyyy.format(date).toString();
		StringTokenizer dateTokens = new StringTokenizer(dateString, ",");
		int month = 0, year = 0;
		int semester = 0;
		try {
			month = Integer.parseInt(dateTokens.nextToken());
			year = Integer.parseInt(dateTokens.nextToken());
		} catch (Exception e) {
			System.out.println("fail");
		}
		if (month > 6)
			semester = 2;
		else semester = 1;
		System.out.println("m: " + month + "y:" + year);
		System.out.println("\nCurrent Enrolment");
		for (int i = 0; i < enrolment.getCourseOffers().length; i++)
		{
			int courseYear = Integer.parseInt(enrolment.getCourseOffer(i).getYear());
			int courseSem = Integer.parseInt(enrolment.getCourseOffer(i).getSemester());
			if (courseYear == year && courseSem == semester)
				current.add(enrolment.getCourseOffer(i));
			else if (courseYear == year && courseSem > semester)
				future.add(enrolment.getCourseOffer(i));
			else if (courseYear > year)
				future.add(enrolment.getCourseOffer(i));
			else 
				past.add(enrolment.getCourseOffer(i));
		}
		System.out.println("Current");
		for (int i = 0; i < current.size(); i++)
		{
			System.out.print("\t" + current.get(i).getCourseDefinition().getCourseCode());
			System.out.println(" - " + current.get(i).getCourseDefinition().getCourseName());			
		}
		System.out.println("Future");
		for (int i = 0; i < future.size(); i++)
		{
			System.out.print("\t" + future.get(i).getCourseDefinition().getCourseCode());
			System.out.println(" - " + future.get(i).getCourseDefinition().getCourseName());			
		}
		System.out.println("\n1. Add Course\n2. Drop Course");
		int option = scanner.nextInt();
		switch (option)
		{
		case 1: add_course(enrolment);save();load();break;
		case 2:drop_course(year, semester);save();load();break;
		}
	} 
	public void drop_course(int year, int sem)
	{
		System.out.print("Drop Course (ID): ");
		Scanner sc = new Scanner(System.in);
		String course_id = sc.nextLine();
		Enrolment enrolment = currentUser.getCurrentEnrolment();
		for (int i = 0; i < enrolment.getCourseOffers().length; i++)
		{
			if (course_id.equals(enrolment.getCourseOffer(i).getCourseDefinition().getCourseCode()))
			{
				if (year <= Integer.parseInt(enrolment.getCourseOffer(i).getYear()))
				{
					enrolment.dropCourse(enrolment.getCourseOffer(i));
				}
				else if (sem >= Integer.parseInt(enrolment.getCourseOffer(i).getSemester()) &&
						year == Integer.parseInt(enrolment.getCourseOffer(i).getYear()))
				{
					enrolment.dropCourse(enrolment.getCourseOffer(i));
				}
				else
				{
					System.out.println("failed");
				}// check sem and year then delete it
			}
		}
	}
	public boolean add_course(Enrolment enrolment)
	{
		Program currentProgram = enrolment.getProgram();

		System.out.println("Cores for " + currentProgram.getProgCode() + ": ");

		for (int i = 0; i < currentProgram.numCores(); i++)
		{
			System.out.print("\t" + currentProgram.getCore(i).getCourseCode() + " - ");
			System.out.print(currentProgram.getCore(i).getCourseName() + "\n");
		}

		System.out.println("Specialisations for " + currentProgram.getProgCode() + ": ");

		for (int i = 0; i < currentProgram.numSpecialisations(); i++)
		{
			System.out.print("\t" + currentProgram.getSpecialisation(i).getCourseCode() + " - ");
			System.out.println(currentProgram.getSpecialisation(i).getCourseName() + "\n");
		}

		System.out.println("\nAdd course for...\nYear: ");
		//
		Scanner sc = new Scanner(System.in);
		String newyear = sc.nextLine();
		System.out.println("Semester: ");
		String newsem = sc.nextLine();
		System.out.println("Course Id: ");
		String course_id = sc.nextLine();

		for (int i = 0; i < numCourseOffers(); i++)
		{			
			if (course_id.equals(getCourseOffer(i).getCourseDefinition().getCourseCode()))
			{
				System.out.println("id equals");
				if (newyear.equals(getCourseOffer(i).getYear()) && newsem.equals(getCourseOffer(i).getSemester()))
				{
					System.out.println("Course successfully added");
					enrolment.addCourse(getCourseOffer(i));
					return true;
				}
			}	
		}

		System.out.println("Course could not be added");

		return false;					
	}
	// END ///////////////////////////////////////////
	//////////////////////////////////////////////////
	//////////////////////////////////////////////////
	

	
	//////////////////////////////////////////////////
	//////////////////////////////////////////////////
	// RICHARD 26 SEP ////////////////////////////////

	public int findProgramByCode(String progCode) 
	{
		int pos = -1;
		for(int j = 0; j<numPrograms(); ++j)
		{
			System.out.println(programs[j].getProgCode());
			if(progCode.equalsIgnoreCase(programs[j].getProgCode()))//Checking for valid Program code against ProgCode in program array
			{
				pos = j;
				break;
			}
		}
		return pos;
	}
	public void createProgram(int counter){

		for(int i = 0; i<counter; i++)
		{
			//import scanner 
			Scanner kb = new Scanner(System.in);
			boolean out = false;

			//declare variables
			String progCode;
			String progName;
			double creditPoint;
			int versionNum;
			String inputStatus;
			char status;
			String inputProgType;
			char progType;

			do
			{
				System.out.print("Program "+i+" - Enter in program code >> ");
				progCode = kb.nextLine();
				if(exit(progCode)==true){out = false;}
				if(findProgramByCode(progCode)>=0)
				{
					System.out.println("Program " + progCode + " already exists in the system");
					out = true;
				}
			}
			while(out);

			System.out.println("Enter in program name:");
			progName = kb.nextLine();

			System.out.println("Enter in credit point value:");
			creditPoint = kb.nextDouble();

			System.out.println("Enter in version number:");
			versionNum = kb.nextInt();

			//consume trailing line
			kb.nextLine();

			//prompt for status at least once and reprompt if input is more than 1 character
			do{

				System.out.print("Enter in status: ");
				inputStatus = kb.nextLine();


				// convert input into single character
				if (inputStatus.length() == 1){
					status = inputStatus.charAt(0);
					out = true;
				}
				else{
					System.out.println("Error. You did not enter a single character for status");
				}
			}while(out);

			//prompt for program type at least once and reprompt if input is more than 1 character
			do{

				System.out.print("Enter in program type: ");
				inputProgType = kb.nextLine();

				// convert input into single character
				if(inputProgType.length() == 1){
					progType = inputProgType.charAt(0);
					out = true;
				}
				else{
					System.out.println("Error. You did not enter a single character for program type");
				}

			}while(out);		
		}			
	}

	// END ///////////////////////////////////////////
	//////////////////////////////////////////////////
	//////////////////////////////////////////////////



	
	
	public ArrayList<String> removeDuplicates(ArrayList<String> list)
	{
		Set<String> set = new LinkedHashSet<>(list);
		ArrayList<String> result = new ArrayList<>(set);
		return result;
	}

	public boolean save() 
	{ 
		////////////////////////////////////////////////////////////////////////

		ArrayList<String> courseDefinitionsUpdate = new ArrayList<String>();

		for (int i = 0; i < programs.length; i++)
			courseDefinitionsUpdate.addAll(programs[i].getCourseDefinitions());

		courseDefinitionsUpdate = removeDuplicates(courseDefinitionsUpdate);

		f_courseDefinitions.overwrite(courseDefinitionsUpdate);

		////////////////////////////////////////////////////////////////////////

		ArrayList<String> programCourseUpdate = new ArrayList<String>();

		for (int i = 0; i < programs.length; i++)
			programCourseUpdate.addAll(programs[i].getProgramCourse());			

		f_programs_courses.overwrite(programCourseUpdate);


		////////////////////////////////////////////////////////////////////////

		ArrayList<String> studentCourseUpdate = new ArrayList<String>();

		for (int i = 0; i < students.length; i++)
			studentCourseUpdate.addAll(students[i].getStudentCourse());

		studentCourseUpdate = removeDuplicates(studentCourseUpdate);

		f_student_course.overwrite(studentCourseUpdate);


		////////////////////////////////////////////////////////////////////////

		ArrayList<String> courseOfferUpdate = new ArrayList<String>();

		for (int i = 0; i < courses.length; i++)
			courseOfferUpdate.add(courses[i].ToString());

		f_courseOffers.overwrite(courseOfferUpdate);


		////////////////////////////////////////////////////////////////////////

		ArrayList<String> enrolmentsUpdate = new ArrayList<String>();

		for (int i = 0; i < students.length; i++)
			enrolmentsUpdate.addAll(students[i].getEnrolments());

		f_enrolments.overwrite(enrolmentsUpdate);

		////////////////////////////////////////////////////////////////////////

		ArrayList<String> programUpdate = new ArrayList<String>();

		for (int i = 0; i < programs.length; i++)
			programUpdate.add(programs[i].ToString());

		f_programs.overwrite(programUpdate);

		////////////////////////////////////////////////////////////////////////

		ArrayList<String> recordUpdate = new ArrayList<String>();

		for (int i = 0; i < students.length; i++)
			recordUpdate.add(students[i].getRecord().ToString());

		f_records.overwrite(recordUpdate);

		////////////////////////////////////////////////////////////////////////

		ArrayList<String> resultUpdate = new ArrayList<String>();

		for (int i = 0; i < students.length; i++)
			resultUpdate.addAll(students[i].getResults());

		f_results.overwrite(resultUpdate);

		////////////////////////////////////////////////////////////////////////

		ArrayList<String> usersUpdate = new ArrayList<String>();

		for (int i = 0; i < staff.length; i++)
			usersUpdate.add(staff[i].ToString());

		for (int i = 0; i < students.length; i++)
			usersUpdate.add(students[i].ToString());


		f_users.overwrite(usersUpdate);

		////////////////////////////////////////////////////////////////////////

		return true; 
	}
	public void load()
	{
		ArrayList<CourseDefinition> courseDefinitions;
		ArrayList<CourseOffer> courseOffers;
		ArrayList<Student> students;
		ArrayList<Staff> staff;
		ArrayList<Program> programs;
		ArrayList<Enrolment> enrolments;
		ArrayList<Result> results;
		ArrayList<Record> records;
		/////////////////////////////////////////////////////////////////////////////////
		courseDefinitions = new ArrayList<CourseDefinition>();
		for (int i = 0; i < f_courseDefinitions.getSize(); i++)
		{
			String code = f_courseDefinitions.get(i, 0);
			String name = f_courseDefinitions.get(i, 1);
			double credit = Double.parseDouble(f_courseDefinitions.get(i, 2));
			// check for exceptions
			courseDefinitions.add(new CourseDefinition(code, name, credit));
		}
		/////////////////////////////////////////////////////////////////////////////////
		courseOffers = new ArrayList<CourseOffer>();
		for (int i = 0; i < f_courseOffers.getSize(); i++)
		{
			String semester = f_courseOffers.get(i, 0);
			String year = f_courseOffers.get(i, 1);
			String code = f_courseOffers.get(i, 2);
			for (int j = 0; j < courseDefinitions.size(); j++)
			{
				if (code.equals(courseDefinitions.get(j).getCourseCode()))
				{	// need checks
					courseOffers.add(new CourseOffer(semester, year, courseDefinitions.get(j)));
				}
			}
		}
		System.out.println();
		/////////////////////////////////////////////////////////////////////////////////
		results = new ArrayList<Result>();
		for (int i = 0; i < f_results.getSize(); i++)
		{
			int mark = Integer.parseInt(f_results.get(i, 0));
			String grade = f_results.get(i, 1);
			String studentId = f_results.get(i, 2);
			String courseCode = f_results.get(i, 3);
			for (int j = 0; j < courseOffers.size(); j++)
			{
				if (courseCode.equals(courseOffers.get(j).getCourseDefinition().getCourseCode()))
				{
					results.add(new Result(mark, grade, studentId, courseOffers.get(j)));
				}
			}
		}
		/////////////////////////////////////////////////////////////////////////////////
		records = new ArrayList<Record>();
		for (int i = 0; i < f_records.getSize(); i++)
		{
			String studentId = f_records.get(i, 0);
			double gpa = Double.parseDouble(f_records.get(i, 1));
			int wam = Integer.parseInt(f_records.get(i, 2));
			ArrayList<Result> record_results = new ArrayList<Result>();
			for (int j = 0; j < results.size(); j++)
			{
				if (studentId.equals(results.get(j).getStudentId()))
				{
					record_results.add(results.get(j));
				}
			}
			Result[] record_results_array = record_results.toArray(new Result[record_results.size()]);
			records.add(new Record(studentId, gpa, wam, record_results_array));
		}
		/////////////////////////////////////////////////////////////////////////////////
		programs = new ArrayList<Program>();
		for (int i = 0; i < f_programs.getSize(); i++)
		{
			String code = f_programs.get(i, 0);
			String name = f_programs.get(i, 1);
			double credit = Double.parseDouble(f_programs.get(i, 2));
			int version = Integer.parseInt(f_programs.get(i, 3));
			char status = f_programs.get(i, 4).charAt(0);
			String type = f_programs.get(i, 5);
			ArrayList<CourseDefinition> program_cores = new ArrayList<CourseDefinition>();
			ArrayList<CourseDefinition> program_specialisations = new ArrayList<CourseDefinition>();
			for (int j = 0; j < f_programs_courses.getSize(); j++)
			{
				String p_code = f_programs_courses.get(j, 0);
				String c_code = f_programs_courses.get(j, 1);
				String role = f_programs_courses.get(j, 2);
				if (p_code.equals(code))
				{
					for (int k = 0; k < courseDefinitions.size(); k++)
					{
						if (courseDefinitions.get(k).getCourseCode().equals(c_code))
						{
							switch (role)
							{
							case "CORE":
								program_cores.add(courseDefinitions.get(k));
								break;
							case "SPECIALISATION":
								program_specialisations.add(courseDefinitions.get(k));
								break;
							default: break;
							}
						}
					}
				}
			}
			CourseDefinition[] cores_array = program_cores.toArray(new CourseDefinition[program_cores.size()]);
			CourseDefinition[] specialisations_array = program_specialisations.toArray(new CourseDefinition[program_specialisations.size()]);
			programs.add(new Program(code, name, credit, version, status, type, cores_array, specialisations_array));
		}
		/////////////////////////////////////////////////////////////////////////////////
		enrolments = new ArrayList<Enrolment>();
		for (int i = 0; i < f_enrolments.getSize(); i++)
		{
			String studentId = f_enrolments.get(i, 0);
			String status = f_enrolments.get(i, 1);
			String program_code = f_enrolments.get(i, 2);
			ArrayList<CourseOffer> courses = new ArrayList<CourseOffer>();
			for (int j = 0; j < f_student_course.getSize(); j++)
			{
				String s_id = f_student_course.get(j, 0);
				String c_code = f_student_course.get(j, 1);
				String sem = f_student_course.get(j, 2);
				String year = f_student_course.get(j, 3);
				if (s_id.equals(studentId))
				{
					for (int k = 0; k < courseOffers.size(); k++)
					{
						if (c_code.equals(courseOffers.get(k).getCourseDefinition().getCourseCode()))
						{
							if (courseOffers.get(k).getSemester().equals(sem))
								if (courseOffers.get(k).getYear().equals(year))
									courses.add(courseOffers.get(k));
						}
					}
				}
			}
			CourseOffer[] courses_array = courses.toArray(new CourseOffer[courses.size()]);

			for (int j = 0; j < programs.size(); j++)
			{
				if (program_code.equals(programs.get(j).getProgCode()))
				{
					enrolments.add(new Enrolment(studentId, status, courses_array, programs.get(j)));
				}
			}
		}
		/////////////////////////////////////////////////////////////////////////////////
		students = new ArrayList<Student>();
		staff = new ArrayList<Staff>();
		for (int i = 0; i < f_users.getSize(); i++)
		{
			String id = f_users.get(i, 0);
			String password = f_users.get(i, 1);
			String first = f_users.get(i, 2);
			String last = f_users.get(i, 3);
			String dob = f_users.get(i, 4);
			String title = f_users.get(i, 5);
			ArrayList<Enrolment> student_enrolments = new ArrayList<Enrolment>();
			Record record = null;
			for (int j = 0; j < enrolments.size(); j++)
			{
				if (id.equals(enrolments.get(j).getStudentId()))
				{
					student_enrolments.add(enrolments.get(j));
				}
			}
			for (int j = 0; j < records.size(); j++)
			{
				if (id.equals(records.get(j).getStudentId()))
				{
					record = records.get(j);
				}
			}
			Enrolment[] student_enrolments_array = student_enrolments.toArray(new Enrolment[student_enrolments.size()]);
			if (f_users.get(i, 6).equals("student"))
			{
				students.add(new Student(id, password, first, last, dob, title, record, student_enrolments_array));
			}

			else if (f_users.get(i, 6).equals("coordinator"))
			{
				staff.add(new Coordinator(id, password, first, last, dob, title));
			}
			else if (f_users.get(i, 6).equals("sysadmin"))
			{
				staff.add(new SystemAdmin(id, password, first, last, dob, title));
			}
			else if (f_users.get(i, 6).equals("facadmin"))
			{
				staff.add(new FacultyAdmin(id, password, first, last, dob, title));
			}
		}
		this.programs = programs.toArray(new Program[programs.size()]);
		this.students = students.toArray(new Student[students.size()]);
		this.staff = staff.toArray(new Staff[staff.size()]);
		this.courses = courseOffers.toArray(new CourseOffer[courseOffers.size()]);
	}
}


