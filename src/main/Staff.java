package main;

public class Staff extends User {

	public Staff(String id, String password, String first, String last, String dob, String title) {
		super(id, password, first, last, dob, title);
		
	}
	
	public String ToString()
	{
		return super.ToString();
	}
	public void menu(Controller control){};

}
