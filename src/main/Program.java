package main;

import java.util.ArrayList;

/* Richard s3563242 - Assignment Contribution  */
public class Program
{
  // declare variables
  String progCode;
  String progName;
  double creditPoint;
  int versionNum;
  char status;
  String progType;
  
  //array list for cores and specialisations. 
  private CourseDefinition[] cores;
  private CourseDefinition[] specialisations;
  
  //define a constructor that sets up program
  public Program(String progCode, String progName, double creditPoint, int versionNum, char status, String progType, 
  CourseDefinition[] cores, CourseDefinition[] specialisations)
  {
    //initialise variables
    this.progCode = progCode;
    this.progName = progName;
    this.creditPoint = creditPoint;
    this.versionNum = versionNum;
    this.status = status;
    this.progType = progType;
    this.cores = cores;
    this.specialisations = specialisations;
  }
  
  //define accessors for each instance variable
  public String getProgCode(){
    return progCode;
  }
  
  public String getProgName(){
    return progName;
  }
  
  public double getCreditPoint(){
    return creditPoint;
  }
  
  public int getVersionNum(){
    return versionNum;
  }
  
  public char getStatus(){
    return status;
  }
  
  public String getProgType(){
    return progType;
  }
  
  public CourseDefinition getCore(int i){ 
  	return cores[i]; 
  	
  }
  public CourseDefinition getSpecialisation(int i) { 
  	return specialisations[i];
  }
  
  public int numCores() { return cores.length; }
  public int numSpecialisations() { return specialisations.length; }
  
  // helper methods
  public void setCreditPoint(double newCreditPoint){
    this.creditPoint = newCreditPoint;
  }
  
    public ArrayList<String> getProgramCourse()
  {
	  ArrayList<String> program_courses = new ArrayList<String>();
	  
	  for (int i = 0; i < cores.length; i++)
	  {
		  program_courses.add(progCode + "," + cores[i].getCourseCode() + ",CORE");
	  }
	  
	  for (int i = 0; i < specialisations.length; i++)
	  {
		  program_courses.add(progCode + "," + specialisations[i].getCourseCode() + ",SPECIALISATION");
	  }
	  
	  return program_courses;
  }
  
  public ArrayList<String> getCourseDefinitions()
  {
	  ArrayList<String> courseDefinitions = new ArrayList<String>();
	  
	  for (int i = 0; i < cores.length; i++)
		  courseDefinitions.add(cores[i].ToString());
	  	  
	  for (int i = 0; i < specialisations.length; i++)
		  courseDefinitions.add(specialisations[i].ToString());

	  return courseDefinitions;
  }
  
  public String ToString()
  {
	  return progCode + "," + progName + "," + creditPoint + "," + versionNum + "," + status + "," + progType;
  }
 

  
  
  
  
  
  
  ////////////////////
  /* what the fuck is going on here???
  
 //  assign cores a coreName???
  public void setCores(int index){
  	cores = index;
  	
  }
  
  //assign Specialisation Set to be equal to int amount
  public void setSpecialisationSet(int amount){
  	specialisations = amount;
  }
*/
///////////////////////////////////

}
