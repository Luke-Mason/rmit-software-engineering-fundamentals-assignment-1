package main;

import java.util.ArrayList;

public class Enrolment
{
  private String status;
  private String studentId;
  private CourseOffer[] course_offers;
  private Program program;
  
  public Enrolment(String studentId, String status, CourseOffer[] course_offers, Program program)
  {
    this.status = status;
    this.studentId = studentId;
    this.course_offers = course_offers;
    this.program = program;
  };
  
  public Enrolment(String status, Program program, String studentId)
  {
    this.status = status;
    this.program = program;
    this.studentId = studentId;
  };
  
  public String getStatus() { return status; }
  public Program getProgram() { return program; }
  public String getStudentId() { return studentId; }
  
    public ArrayList<String> getStudentCourse()
  {
	  ArrayList<String> student_courses = new ArrayList<String>();
	  
	  for (int i = 0; i < course_offers.length; i++)
	  {
		  student_courses.add(studentId + "," + course_offers[i].getCourseDefinition().getCourseCode()
				   + "," + course_offers[i].getSemester() + "," + course_offers[i].getYear());
	  }
	  
	  return student_courses;
  }
  
  public String ToString()
  {
	  return studentId + "," + status + "," + program.getProgCode();
  }
  
  
  /*
  public CourseOffer getCourseOffer(String code) // Courde definition code to search for offering
  {
    for (int i = 0; i < course_offers.length; i++)
    {
      if (course_offers[i].getCourseCode() == code)
      {
        return course_offers[i]; 
      }
    }
    return null;
  };
  */
  
  public CourseOffer getCourseOffer(int i) { return course_offers[i]; }
  public CourseOffer[] getCourseOffers() { return course_offers; }
  
  
  public void addCourse(CourseOffer course_offer) 
  {
	  ArrayList<CourseOffer> courses = new ArrayList<CourseOffer>();
	  
	  for (int i = 0; i < course_offers.length; i++)
		  courses.add(course_offers[i]);

	  courses.add(course_offer);
	  
	  course_offers = courses.toArray(new CourseOffer[courses.size()]);  
  }
  
  public void dropCourse(CourseOffer course_offer)
  {
	  ArrayList<CourseOffer> courses = new ArrayList<CourseOffer>();
	  
	  for (int i = 0; i < course_offers.length; i++)
		  courses.add(course_offers[i]);

	  courses.remove(course_offer);
	  
	  course_offers = courses.toArray(new CourseOffer[courses.size()]);  
  }
    // add course_offer to array.
    // maybe should use vectors for this.. ?
  /*
  
  public void activate() { //status = ??; }
  public void deactivate() { //status = ??; }
  public void defer() {}
  */
}
