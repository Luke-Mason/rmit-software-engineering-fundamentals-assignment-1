package main;

public class Record
{
	private String studentId;
	private double GPA;
	private int WAM;
	private Result[] results;
	private double credit_earned;
	private final String[] passes = {"HD", "DI", "CR", "PA", "PX", "SP" };

	public Record(String studentId, double GPA, int WAM, Result[] results)
	{
		this.studentId = studentId;
		this.GPA = GPA;
		this.WAM = WAM;
		this.results = results;

		credit_earned = 0;
		
		for(int i=0; i<results.length; i++)
		{
			for (int j = 0; j < passes.length; j++)
			{
				if(results[i].getGrade().equals(passes[j])) 
					credit_earned += results[i].getCourseOffer().getCourseDefinition().getCreditValue();
			}
		}
	};

	public String getStudentId() { return studentId; }
	public double getGPA() { return GPA; }
	public int getWAM() { return WAM; }

	/*
  public Result getResult(CourseOffer course_offer)
  {
    // cycle results to find one that matches the course
  }
	 */

	public Result getResult(int i)
	{
		return results[i];
	}

	public Result[] getResults() { return results; }
	
	public double getCreditEarned() { return credit_earned; }
	
	public double currentCreditPointsEarned()
	{
		double total = 0.0;
		for(int i=0; i<results.length; i++)
		{
			if(results[i].getGrade().equals("NN")) {}
			else
			{
				total = total + results[i].getCourseOffer().getCourseDefinition().getCreditValue();
			}
		}

		System.out.println("in currentCreditPointsEarned");

		return total;
	}

	public String ToString()
	{
		return studentId + "," + GPA + "," + WAM;
	}

}
