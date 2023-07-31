package main;

/* Richard Kuoch - s3563242 Assignment Contribution */ 
public class CourseOffer
{
  
  private String semester;
  private String year;
  private Result[] results;
  private CourseDefinition course_definition;
  
  public CourseOffer(String semester, String year, CourseDefinition course_definition)
  {
    this.semester = semester;
    this.year = year;
    this.course_definition = course_definition;
  }
  
  //getters
  public String getSemester(){ 
  	return semester;
  }
  
  public String getYear(){ 
  	return year; 
  }
  
  public CourseDefinition getCourseDefinition() { 
  	return course_definition; 
  	
  }
  public Result[] getResults() { 
  	return results; 
  }
  
  public Result getResult(int i) { 
  	return results[i];
  }
  
  
  //setters
  public void setSemester(String semester){
  	this.semester = semester;
  }
  
  public void setYear(String year){
  	this.year = year;
  }
  
  ///////////////////////////////////////////////
  /* lots of bugs here Richard
  
  //helper methods
  public Result getResult(String studentId) 
  {
    // search results for correct student
    for(int i=0;i<results.length;i++){
    	// if student ID matches results???
    	if (results[i].getStudentId().compareTo(studentId)==0){
    		System.out.println(results[i]);
    	}
    }
  }
  
  public boolean addResult(Result result)
  {
    // add result to results array/vector
    for(int i=0;i<results.length;i++){
    	// results[i].add(result); 
    }
  }
  
  */
  ////////////////////////////////////////////////
 
  
    public String ToString()
  {
	  return semester + "," + year + "," + course_definition.getCourseCode();
  }
  
}
