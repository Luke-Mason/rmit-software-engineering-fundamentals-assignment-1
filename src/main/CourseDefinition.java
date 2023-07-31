package main;

/* Richard s3563242 - Assignment Contribution  */
public class CourseDefinition{
  //define variables
  String courseName;
  String courseCode;
  double creditValue;
  
  //define constructor for course definition
  public CourseDefinition(String courseCode, String courseName, double creditValue){
    this.courseName = courseName;
    this.courseCode = courseCode;
    this.creditValue = creditValue;
  }
  
  //define accessors for each instance variable
  public String getCourseName(){
    return courseName;
  }
  
  public String getCourseCode(){
    return courseCode;
  }
  
  public double getCreditValue(){
    return creditValue;
  }
  
    public String ToString()
  {
	  return courseCode + "," + courseName + "," + creditValue;
  }
  
}
