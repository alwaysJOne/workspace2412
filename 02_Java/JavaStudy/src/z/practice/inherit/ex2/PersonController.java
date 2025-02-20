package z.practice.inherit.ex2;

public class PersonController {
	Student[] s = new Student[3];
	Employee[] e = new Employee[10];
	
	public int[] personCount() {
		// [Student수, Employee수]
		int[] countArr = new int[2]; //[0, 0]
		
		for(int i=0; i<s.length; i++) {
			Student st = s[i];
			if(st == null) {
				break;
			}
			countArr[0]++;
		}
		
		for(Employee em : e) {
			if(em == null) {
				break;
			}
			countArr[1]++;
		}
		return countArr;
	}
	
	public void insertStudent(String name, int age, double height, double weight, int grade,String major) {
		for(int i=0; i<s.length; i++) {
			if(s[i] == null) {
				s[i] = new Student(name, age, height, weight, grade, major);
				return;
			}
		}
	}
	
	public Student[] printStudent() {
		return s;
	}
	
	public void insertEmployee(String name, int age, double height, double weight, int salary, String dept) {
		for(int i=0; i<e.length; i++) {
			if(e[i] == null) {
				e[i] = new Employee(name, age, height, weight, salary, dept);
				return;
			}
		}
	}
	
	public Employee[] printEmployee() {
		return e;
	}
}
