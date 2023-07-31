package main;

public class Result
{
  private int mark;
  private String grade;
  private String studentId;
  private CourseOffer course_offer;
  
  public Result(int mark, String grade, String studentId, CourseOffer course_offer)
  {
    this.mark = mark;
    this.grade = grade;
    this.studentId = studentId;
    this.course_offer = course_offer;
    // validation
  };
  
  public int getMark() { return mark; }
  public String getGrade() { return grade; }
  public String getStudentId() { return studentId; }
  public CourseOffer getCourseOffer() { return course_offer; }
  
   public String ToString()
  {
	  return mark + "," + grade + "," + studentId + "," + course_offer.getCourseDefinition().getCourseCode();
  }
  
  
}
