package z.practice.oop.ex4;

import java.util.Scanner;

public class EmployeeMenu {
	private Scanner sc = new Scanner(System.in);
	private EmployeeController ec = new EmployeeController();

	public EmployeeMenu() {
		super();
		while(true) {
			System.out.println("1. 사원추가");
			System.out.println("2. 사원 수정");
			System.out.println("3. 사원 삭제");
			System.out.println("4. 사원 출력");
			System.out.println("9. 프로그램 종료");
			
			System.out.print("메뉴 번호를 누르세요 : ");
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				insertEmp();
				break;
			case 2:
				updateEmp();
				break;
			case 3:
				deleteEmp();
				break;
			case 4:
				printEmp();
				break;
			case 9:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
			}
		}
	}

	public void insertEmp() {
		System.out.print("사원 번호 : ");
		int empNo = sc.nextInt();
		
		System.out.print("사원 이름 : ");
		String empName = sc.next();
		
		System.out.print("사원 성별 : ");
		char gender = sc.next().charAt(0);
		
		System.out.print("전화 번호 : ");
		String phone = sc.next();
		
		System.out.print("추가 정보를 더 입력하시겠습니까? : ");
		if(sc.next().toUpperCase().charAt(0) == 'Y') {
			System.out.print("사원 부서 : ");
			String dept = sc.next();
			
			System.out.print("사원 연봉 : ");
			int salary = sc.nextInt();
			
			System.out.print("보너스 율 : ");
			double bonus = sc.nextDouble();
			
			ec.add(empNo, empName, gender, phone, dept, salary, bonus);
		} else {
			ec.add(empNo, empName, gender, phone);
		}
	}

	public void updateEmp() {
		System.out.println("가장 최신의 사원 정보를 수정하게 됩니다.");
		System.out.println("사원의 어떤 정보를 수정하시겠습니까?");
		System.out.println("1. 전화번호");
		System.out.println("2. 사원연봉");
		System.out.println("3. 보너스율");
		System.out.println("9. 돌아가기");
		
		int ckhoice = sc.nextInt();
		
		switch(ckhoice) {
		case 1:
			System.out.println("수정할 전화번호 :");
			ec.modify(sc.next());
			break;
		case 2:
			System.out.println("수정할 사원연봉 :");
			ec.modify(sc.nextInt());
			break;
		case 3:
			System.out.println("수정할 보너스율 :");
			ec.modify(sc.nextDouble());
			break;
		case 9:
			System.out.println("메인메뉴로 돌아갑니다");
			break;
		default:
				System.out.println("잘못 입력하셨습니다");
		}
	}

	public void deleteEmp() {
		System.out.print("정말 삭제하시겠습니까? (y/n) : ");
		if(sc.next().toLowerCase().charAt(0) == 'y') {
			if(ec.remove() == null) {
				System.out.println("데이터 삭제에 성공하였습니다.");
			} else {
				System.out.println("데이터 삭제에 실패하였습니다.");
			}
		}
	}

	public void printEmp() {
		String result = ec.inform();
		System.out.println(result == null ? "사원데이터가 없습니다." : result);
	}
}
