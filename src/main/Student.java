package main;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public  class Student extends User
{ // write junit test for constructors to test load
	private Record record;
	private Enrolment[] enrolments;

	public Student(String id, String password, String first, String last, String dob, String title, Record record, Enrolment[] enrolments)
	{
		super(id, password, first, last, dob, title);
		this.record = record;
		this.enrolments=enrolments;
		// validation here
	};

	public void addEnrolment(Enrolment enrolment)
	{
		int previous_num_enrolments = enrolments.length;
		Enrolment[] previous_enrolments = enrolments;
		enrolments = new Enrolment[previous_num_enrolments + 1];

		enrolments[0] = enrolment;

		for (int i = 0; i < previous_num_enrolments; i++)
		{
			enrolments[i+1] = previous_enrolments[i];
		}
	}

	public boolean getReport()
	{
		Result[] mark = record.getResults();
		for(int i = 0; i<4; i++ )
		{
			if(mark[i].getGrade().equalsIgnoreCase("NN"))
			{
				return false;
			}
		}
		return true;
	}
	public Record getRecord() { return record; }
	public Enrolment getCurrentEnrolment() { return enrolments[0]; }
	public Enrolment getEnrolment(int i) { return enrolments[i]; } // <-- will need exception handler

	public ArrayList<String> getEnrolments()
	{
		ArrayList<String> list = new ArrayList<String>();

		for (int i = 0; i < enrolments.length; i++)
		{
			list.add(enrolments[i].ToString());
		}

		return list;
	}

	public ArrayList<String> getStudentCourse()
	{
		ArrayList<String> list = new ArrayList<String>();

		for (int i = 0; i < enrolments.length; i++)
		{
			list.addAll(enrolments[i].getStudentCourse());
		}

		return list;
	}

	public ArrayList<String> getResults()
	{
		ArrayList<String> list = new ArrayList<String>();

		for (int i = 0; i < record.getResults().length; i++)
		{
			list.add(record.getResult(i).ToString());
		}

		return list;
	}

	public String ToString()
	{			
		return super.ToString() + ",student";			
	}


	public int menu(Controller control)
	{
		System.out.println("\nStudent menu");
		System.out.println("-------------------------------");
		System.out.println("1. view/change enrolment"); // including add/drop courses
		System.out.println("2. view academic record");
		System.out.println("3. View Program Progress");
		System.out.println("4. exit");
		Scanner sc = new Scanner(System.in);
		int option = Integer.parseInt(sc.next());

		return option;
	}

	public double get_current_credit_total() 
	{
		return record.getCreditEarned();
	}

	public CourseDefinition[] get_outstanding_courses(Program program)
	{
		ArrayList<CourseDefinition> not_passed = new ArrayList<CourseDefinition>();

		for (int i = 0; i < program.numCores(); i++)
			not_passed.add(program.getCore(i));

		for (int i = 0; i < program.numSpecialisations(); i++)
			not_passed.add(program.getSpecialisation(i));

		String[] passes = {"HD", "DI", "CR", "PA", "PX", "SP" };

		for (int i = 0; i < record.getResults().length; i++)
		{
			for (int j = 0; j < passes.length; j++)
			{
				if (record.getResult(i).getGrade().equals(passes[j]))
					not_passed.remove(record.getResult(i).getCourseOffer().getCourseDefinition());
			}	  
		}

		CourseDefinition[] result = not_passed.toArray(new CourseDefinition[not_passed.size()]);

		return result;
	}
}









