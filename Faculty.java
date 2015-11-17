package dining;

public class Faculty extends People{

	private String firstName;
	private String lastName;
	private String spousefirstName;
	private String spouselastName;
	private int adviseeNumber;
	
	public Faculty(String first,String last,int advisee){
		firstName = first;
		lastName = last;
		adviseeNumber = advisee;
		spousefirstName = "A";
		spouselastName = "B";
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getSpouseFirstName(){
		return spousefirstName;
	}
	
	public String getSpouseLastName(){
		return spouselastName;
	}
	
	public void setSpouseName(String first,String last){
		spousefirstName = first;
		spouselastName = last;
	}
	
	public int getAdviseeNumber(){
		return adviseeNumber;
	}
	
	public void setAdviseeNumber(int a){
		adviseeNumber = a;
	}
}
