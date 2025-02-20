package z.practice.inherit.ex2;

import java.util.Scanner;

public class PersonMenu {
	private Scanner sc = new Scanner(System.in);
	private PersonController pc = new PersonController();

	public void mainMenu() {
		
		while(true) {
			int[] countArr = pc.personCount();
			
			System.out.println("학생은 최대 3명까지 저장할 수 있습니다.");
			System.out.println("현재 저장된 학생은 "+countArr[0]+"명입니다.");
			System.out.println("사원은 최대 10명까지 저장할 수 있습니다.");
			System.out.println("현재 저장된 사원은 "+countArr[1]+"명입니다.");
			
			System.out.println("1. 학생 메뉴");
			System.out.println("2. 사원 메뉴");
			System.out.println("9. 끝내기");
			System.out.print("메뉴 번호 :");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1:
				this.studentMenu();
				break;
			case 2:
				this.employeeMenu();
				break;
			case 9:
				System.out.println("종료합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	public void studentMenu() {
		while(true) {
			int[] personCount = pc.personCount();
			
			System.out.println("1. 학생 추가");	
			System.out.println("2. 학생 보기");
			System.out.println("9. 메인으로");
			if(personCount[0] == 3) {
				System.out.println("학생을 담을 수 있는 공간이 꽉 찼기 때문에 학생 추가 메뉴는 더 이상 활성화 되지 않습니다.");
			} 
			System.out.print("메뉴 번호 :");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1:
				if(personCount[0] == 3) {
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
					break;
				}
				this.insertStudent();
				break;
			case 2:
				this.printStudent();
				break;
			case 9:
				System.out.println("메인으로 돌아갑니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	public void employeeMenu() {
		while(true) {
			int[] personCount = pc.personCount();
			
			System.out.println("1. 사원 추가");	
			System.out.println("2. 사원 보기");
			System.out.println("9. 메인으로");
			if(personCount[1] == 10) {
				System.out.println("사원을 담을 수 있는 공간이 꽉 찼기 때문에 사원 추가 메뉴는 더 이상 활성화 되지 않습니다.");
			} 
			System.out.print("메뉴 번호 :");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1:
				if(personCount[1] == 10) {
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
					break;
				}
				this.insertEmployee();
				break;
			case 2:
				this.printEmployee();
				break;
			case 9:
				System.out.println("메인으로 돌아갑니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	public void insertStudent() {
		while(true){
			System.out.print("학생 이름 : ");
			String name = sc.next();
			
			System.out.print("학생 나이 : ");
			int age = sc.nextInt();
			
			System.out.print("학생 키 : ");
			double height = sc.nextDouble();
			
			System.out.print("학생 몸무게 : ");
			double weight = sc.nextDouble();
			
			System.out.print("학생 학년 : ");
			int grade = sc.nextInt();
			
			System.out.print("학생 전공 : ");
			String major = sc.next();
			sc.nextLine();
			
			pc.insertStudent(name, age, height, weight, grade, major);
			
			int[] personCount = pc.personCount();
			if(personCount[0] == 3) {
				System.out.println("학생을 담을 수 있는 공간이 꽉 찼기 때문에 학생 추가를 종료하고 학생 메뉴로 돌아갑니다.");
				return;
			}
		
			System.out.print("그만하시려면 N(또는 n), 이어하시려면 아무 키나 누르세요 : ");
			char ch = sc.next().toLowerCase().charAt(0);
			if(ch == 'n') {
				return;
			}
		}
	}

	public void printStudent() {
		// pc의 printStudent() 메소드의 반환 값을 이용하여 학생 객체 배열에 저장된
		// 모든 데이터 출력
		Student[] stuArr = pc.printStudent();
		for(Student stu : stuArr) {
			if(stu == null)
				break;
			System.out.println(stu.toString());
		}
	}

	public void insertEmployee() {
		while(true){
			System.out.print("사원 이름 : ");
			String name = sc.next();
			
			System.out.print("사원 나이 : ");
			int age = sc.nextInt();
			
			System.out.print("사원 키 : ");
			double height = sc.nextDouble();
			
			System.out.print("사원 몸무게 : ");
			double weight = sc.nextDouble();
			
			System.out.print("사원 급여 : ");
			int salary = sc.nextInt();
			
			System.out.print("사원 부서 : ");
			String dept = sc.next();
			sc.nextLine();
			
			pc.insertEmployee(name, age, height, weight, salary, dept);
			
			int[] personCount = pc.personCount();
			if(personCount[1] == 10) {
				System.out.println("사원을 담을 수 있는 공간이 꽉 찼기 때문에 사원 추가를 종료하고 사원 메뉴로 돌아갑니다.");
				return;
			}
		
			System.out.print("그만하시려면 N(또는 n), 이어하시려면 아무 키나 누르세요 : ");
			char ch = sc.next().toLowerCase().charAt(0);
			if(ch == 'n') {
				return;
			}
		}
	}

	public void printEmployee() {
		Employee[] empArr = pc.printEmployee();
		for(Employee emp : empArr) {
			if(emp == null)
				break;
			System.out.println(emp);
		}
	}
}
