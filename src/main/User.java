package main;

public class User{

// declare variables
 String first;
 String last;
 String dob;
 String title;
 String password;
 String id;

// define constructor class for user 
 public User(String id, String password, String first, String last, String dob, String title)
  {
  	this.id = id;
  	this.password = password;
	this.first = first;
	this.last = last;
	this.dob = dob;
	this.title = title;
  } 
  
  // define accessors for variables
  public String getFirstName(){
  	return first;
  }
  
  public String getLastName(){
	  	return last;
	  }
  
  public String getDob(){
  	return dob;
  }
  
  public String getTitle(){
  	return title;
  }
  public String getId(){
  	return id;
  }
  public String getPassword(){
  	return password;
  }
  
    public String ToString()
  {
	  String string = new String();
	  
	  string += id + ",";
	  string += password + ",";
	  string += first + ",";
	  string += last + ",";
	  string += dob + ",";
	  string += title;
	  
	  return string;
  }
  
  
}

