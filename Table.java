package dining;

import java.util.ArrayList;

public class Table {

	public Faculty teacherName;
	public ArrayList<People> people = new ArrayList<People>();
	
	//initialize
	public Table(Faculty a){
		teacherName = a;//may not be the only teacher
		people.add(a);
	}
	
	public ArrayList<People> getlist(){
		return people;
	}
	
	public void add(People a){
		people.add(a);
	}
	
	//pick waiter
	
}
