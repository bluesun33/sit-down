package dining;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	private static ArrayList<Student> student = new ArrayList<Student>();
	private static ArrayList<Faculty> faculty = new ArrayList<Faculty>();
	private static ArrayList<String> s = new ArrayList<String>();//temporary string used for input
	private static ArrayList<String> f = new ArrayList<String>();//temporary string used for input
	private static ArrayList<String> fn = new ArrayList<String>();//temporary string used for input
	private static ArrayList<String> hm = new ArrayList<String>();//temporary string used for input
	private static ArrayList<String> w = new ArrayList<String>();//temporary string used for input
	private static ArrayList<Faculty> eatHome = new ArrayList<Faculty>();
	private static ArrayList<Table> table = new ArrayList<Table>();
	private static ArrayList<Student> headmaster = new ArrayList<Student>();
	private static ArrayList<Student> waited = new ArrayList<Student>();
	private static int guest = 0;
	
	//takes input "students.txt"
	//creates student objects
	public static void inputStudents(){
		String fileName = "students.txt";
		String line = null;
		try{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line=bufferedReader.readLine())!=null){
				s.add(line);
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("Nope");
		}
		catch(IOException ex){
			System.out.println("Noope");
		}
		
		for(String a:s){
			String fac = a.substring(0,a.indexOf("	"));
			String stul = a.substring(a.indexOf("	")+1,a.lastIndexOf(","));
			String stuf = a.substring(a.lastIndexOf(",")+2,a.lastIndexOf("	"));
			String form = a.substring(a.lastIndexOf("	")+1);
			student.add(new Student(stuf,stul,fac,form));
		}
	}
	
		//takes input "faculty.txt"
		//creates faculty objects
		public static void inputFaculty(){
			String fileName = "faculty.txt";
			String line = null;
			try{
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				while((line=bufferedReader.readLine())!=null){
					f.add(line);
				}
				bufferedReader.close();
			}
			catch(FileNotFoundException ex){
				System.out.println("Nope");
			}
			catch(IOException ex){
				System.out.println("Noope");
			}
			
			for(String a:f){
				String last = a.substring(0,a.indexOf(","));
				String first = a.substring(a.indexOf(",")+2);
				faculty.add(new Faculty(first,last,0));
			}
		}
		
	//takes input "facultynum.txt"
	//add number of advisees
	public static void inputFacultyNum(){
		String fileName = "facultynum.txt";
		String line = null;
		try{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line=bufferedReader.readLine())!=null){
				fn.add(line);
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("Nope");
		}
		catch(IOException ex){
			System.out.println("Noope");
		}
		
		for(String a:fn){
			String last = a.substring(0,a.indexOf(","));
			String first = a.substring(a.indexOf(",")+2,a.indexOf("\t"));
			int adviseeNumber = Integer.parseInt(a.substring(a.indexOf("\t")+1));
			if(search(last,first)!=0){
				faculty.get(search(last,first)).setAdviseeNumber(adviseeNumber);
			}
		}
	}
	
	public static void inputHeadmaster(){
		String fileName = "headmaster.txt";
		String line = null;
		try{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line=bufferedReader.readLine())!=null){
				hm.add(line);
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex){
			headmaster = null;
		}
		catch(IOException ex){
			System.out.println("Noope");
		}
		for(String a:hm){
			String first = a.substring(0,a.indexOf("\t"));
			String last = a.substring(a.indexOf("\t")+1);
			headmaster.add(new Student(first,last,null,null));
		}
		
	}
	
	public static void inputWaiter(){
		String fileName = "waited.txt";
		String line = null;
		try{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line=bufferedReader.readLine())!=null){
				w.add(line);
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex){
			waited = null;
		}
		catch(IOException ex){
			System.out.println("Noope");
		}
		for(String a:w){
			String first = a.substring(0,a.indexOf("\t"));
			String last = a.substring(a.indexOf("\t")+1);
			waited.add(new Student(first,last,null,null));
		}
	}
	
	public static int search(String last,String first){
		for(int i=0;i<faculty.size();i++){
			Faculty a = faculty.get(i);
			if (a.getFirstName().equals(first) && a.getLastName().equals(last)){
				return i;
			}
		}
		return 0;
	}
	
	public static boolean searchHead(String last,String first){
		if(headmaster!=null){
			for(int i=0;i<headmaster.size();i++){
				Student a = headmaster.get(i);
				if (a.getFirstName().equals(first) && a.getLastName().equals(last)){
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean searchWaiter(String last,String first){
		if(waited!=null){
			for(int i=0;i<waited.size();i++){
				Student a = waited.get(i);
				if (a.getFirstName().equals(first) && a.getLastName().equals(last)){
					return false;
				}
			}
		}
		return true;
	}
	
	public static int search(String last){
		for(int i=0;i<faculty.size();i++){
			Faculty a = faculty.get(i);
			if (a.getLastName().equals(last)){
				return i;
			}
		}
		return 0;
	}
	
	//pick advisee dinner faculty
	//removes those faculty and students from ArrayList faculty and ArrayList student
	public static void pickAdviseeDinner(){
		int total = 0;
		while(total<80){
			if(!faculty.get(0).getLastName().equals("Maqubela")&&
					!faculty.get(0).getLastName().equals("Leggat")&&
					!faculty.get(0).getLastName().equals("Choate")&&
					faculty.get(0).getAdviseeNumber() != 0){
				total += faculty.get(0).getAdviseeNumber();
				Faculty k = faculty.remove(0);
				eatHome.add(k);
				String spouse = checkSpouse(k.getLastName());
				if(spouse.length()>2){
					total += faculty.get(search(spouse)).getAdviseeNumber();
					eatHome.add(faculty.remove(search(spouse)));
				}
				if (k.getLastName().equals("Spring")){
					total += faculty.get(search("Spring")).getAdviseeNumber();
					eatHome.add(faculty.remove(search("Spring")));
				}
			}
			Collections.shuffle(faculty);
			
		}
		
		for(int i=0;i<student.size();i++){
			Student a=student.get(i);
			for(Faculty b:eatHome){
				if (a.getAdvisorName().contains(b.getLastName())){
					student.remove(i);
				}
			}
		}
	}
	
	public static String checkSpouse(String lastName){
		String spouse = "";
		switch(lastName){
		case "Tumminio": spouse = "Hansen";
		break;
		case "Hansen": spouse = "Tumminio";
		break;
		case "Viacava": spouse = "Vera de Viacava";
		break;
		case "Vera de Viacava": spouse = "Viacava";
		break;
		case "Nelson": spouse = "Martin-Nelson";
		break;
		case "Martin-Nelson": spouse = "Nelson";
		break;
		case "LeRoy": spouse = "LeRoy";
		break;
		case "Belsky": spouse = "Dennison";
		break;
		case "Dennison": spouse = "Belsky";
		break;
		case "Das": spouse = "Sen-Das";
		break;
		case "Sen-Das": spouse = "Das";
		break;
		case "Gracey": spouse = "Gracey";
		break;
		}
		return spouse;
	}
	
	//assign to tables
	public static void assignTables(){
		table.add(new Table(faculty.remove(search("Maqubela"))));
		table.get(0).add(faculty.remove(search("Maqubela")));
		table.add(new Table(faculty.remove(search("Leggat"))));
		table.get(1).add(faculty.remove(search("Choate")));
		table.add(new Table(faculty.remove(search("Bradley","Katherine"))));
		table.add(new Table(faculty.remove(search("Reyes","Andy"))));
		table.add(new Table(faculty.remove(search("Conner","John"))));

		
		//table.add(new Table())
		for(int i=5;i<47;i++){
			Faculty k = faculty.remove(0);
			table.add(new Table(k));
			String spouse = checkSpouse(k.getLastName());
			if(spouse.length()>2){
				table.get(i).add(faculty.remove(search(spouse)));
			}
			if (k.getLastName().equals("Spring")){
				faculty.remove(search("Spring"));
			}
			
			Collections.shuffle(faculty);
		}
		int j=6;
		while(faculty.size()>0){
			if(table.get(j).getlist().size()<2){
			Faculty k = faculty.remove(0);
			table.get(j).add(k);
			String spouse = checkSpouse(k.getLastName());
			if(spouse.length()>2){
				faculty.add((Faculty) table.get(j).getlist().remove(0));
				table.get(j).add(faculty.remove(search(spouse)));
			}
			if (k.getLastName().equals("Spring")){
				faculty.remove(search("Spring"));
			}
			Collections.shuffle(faculty);
			}
			j++;
		}
		for(int i=0;i<4-guest;i++){
			if(searchHead(student.get(0).getLastName(),student.get(0).getFirstName())){
				table.get(0).add(student.remove(0));
			}
			Collections.shuffle(student);
		}
		for(int i=2;i<5;i++){
			table.get(i).add(student.remove(0));
			Collections.shuffle(student);
		}
		int a=student.size()%46;
		for(int i=student.size();i>0;i--){
			if (i>a){
				table.get((i-a)%46).add(student.remove(0));
				Collections.shuffle(student);
			}
			else{
				table.get(46-(a-i)).add(student.remove(0));
				Collections.shuffle(student);
			}
		}
		for(int i=1;i<46;i++){
			if(table.get(i).getlist().size()>8){
				student.add((Student) table.get(i).getlist().remove(8));
			}
		}
		int i=1;
		while(student.size()>0 && i<47){
			table.get(i).add(student.remove(0));
			i++;
		}
	}
	
	public static void pickWaiter(){
		int totalWaiter;
		for(Table a:table){
			totalWaiter = 0;
			
			while(totalWaiter==0){
				int k=(int)(Math.random()*(a.getlist().size()));
				if(a.getlist().get(k).getClass().equals(Student.class) && 
						!((Student)a.getlist().get(k)).getForm().equals("VI") &&
						searchWaiter(a.getlist().get(k).getLastName(), a.getlist().get(k).getFirstName())){
						((Student)a.getlist().get(k)).setWaiter(true);
				}
				
				for(People b:a.getlist()){
					if(b.getClass().equals(Student.class) && ((Student)b).getWaiter()){
						totalWaiter += 1;
					}
				}
			}
		}
		totalWaiter=1;
		while(totalWaiter==1){
			int k=(int)(Math.random()*(table.get(0).getlist().size()));
			if(table.get(0).getlist().get(k).getClass().equals(Student.class) && 
					!((Student)table.get(0).getlist().get(k)).getForm().equals("VI") &&
					searchWaiter(table.get(0).getlist().get(k).getLastName(), table.get(0).getlist().get(k).getFirstName())){
					((Student)table.get(0).getlist().get(k)).setWaiter(true);
			}
			totalWaiter=0;
			for(People b:table.get(0).getlist()){
				if(b.getClass().equals(Student.class) && ((Student)b).getWaiter()){
					totalWaiter += 1;
				}
			}
		}
	}
	
	//output
	public static void main(String[] args){
		inputStudents();
		inputFaculty();
		inputFacultyNum();
		inputHeadmaster();
		inputWaiter();
		for(Student a:student){
			System.out.println(a.getForm());
		}
		Collections.shuffle(student);
		Collections.shuffle(faculty);
		pickAdviseeDinner();
		assignTables();
		pickWaiter();
		//final output list
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("finaltables.rtf", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for(int i=0;i<table.size()-1;i++){
			Table a = table.get(i);
			writer.println(i+1 + " ");
			for(People b:a.getlist()){
				if(b.getClass().equals(Student.class) && ((Student)b).getWaiter()){
					writer.print("Waiter ");
				}
				writer.print(b.getFirstName()+" ");
				writer.println(b.getLastName());
			}
			writer.println();
			
		}
		writer.println("Advisee Dinners");
		for(Faculty a:eatHome){
			writer.print(a.getFirstName() + " ");
			writer.println(a.getLastName());
		}
		writer.close();
		//track who's waited
		try {
			writer = new PrintWriter("waited.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(waited!=null){
			for(People j:waited){
				writer.println(j.getFirstName()+"	"+j.getLastName());
			}
		}
		for(Table a:table){
			for(People b:a.getlist()){
				if(b.getClass().equals(Student.class) && ((Student)b).getWaiter()){
					writer.println(b.getFirstName()+"	"+b.getLastName());
				}
			}
		}
		writer.close();
		//track who sat at headmaster's
		try {
			writer = new PrintWriter("headmaster.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (headmaster!=null){
			for(People b:headmaster){
				writer.println(b.getFirstName()+"	"+b.getLastName());
			}
		}
		for(People a:table.get(0).getlist()){
			if (!a.getLastName().equals("Maqubela")){
				writer.println(a.getFirstName()+"	"+a.getLastName());
			}
		}
		writer.close();
	}
}
