package dining;

public class Student extends People{
	
	private String firstName;
	private String lastName;
	private String advisorName;
	private String form;
	private boolean waiter = false;
	
	public Student(String first, String last, String advisor, String form){
		firstName = first;
		lastName = last;
		advisorName = advisor;
		this.form = form;
	}
	
	public boolean getWaiter(){
		return waiter;
	}
	
	public void setWaiter(boolean a){
		waiter = a;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}

	public String getAdvisorName(){
		return advisorName;
	}
	
	public String getForm(){
		return form;
	}
}
