package z.practice.oop.ex1;

import java.util.Scanner;

public class ShapeMenu {
	private Scanner sc = new Scanner(System.in);
	private SquareController scr = new SquareController();
	private TriangleController tc = new TriangleController();

	public void inputMenu() {
		while(true) {
			System.out.println("==== 도형 프로그램 ====");
			System.out.println("3. 삼각형");
			System.out.println("4. 사각형");
			System.out.println("9. 프로그램 종료");
			System.out.print("메뉴 번호 : ");
			
			int choice = sc.nextInt();
			sc.nextLine(); //개행문자 비움
			
			switch(choice) {
			case 3:
				this.triangleMenu();
				break;
			case 4:
				this.squareMenu();
				break;
			case 9:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못된 번호입니다. 다시 입력하세요.");
			}
		}
	}

	//삼각형 메뉴 출력 메소드
	public void triangleMenu() {
		while(true) {
			System.out.println("==== 삼각형 ====");
			System.out.println("1. 삼각형 면적");
			System.out.println("2. 삼각형 색칠");
			System.out.println("3. 삼각형 정보");
			System.out.println("9. 메인으로");
			System.out.print("메뉴 번호 : ");
			
			int choice = sc.nextInt();
			sc.nextLine(); //개행비움
			
			if(choice == 9) { // triangleMenu종료 후 메인으로 이동
				return;
			}
			
			this.inputSize(1, choice);
		}
		
	}

	public void squareMenu() {
		while(true) {
			System.out.println("==== 사각형 ====");
			System.out.println("1. 사각형 둘레");
			System.out.println("2. 사각형 면접");
			System.out.println("3. 사각형 색칠");
			System.out.println("4. 사각형 정보");
			System.out.println("9. 메인으로");
			System.out.print("메뉴 번호 : ");
			
			int choice = sc.nextInt();
			sc.nextLine(); //개행비움
			
			if(choice == 9) { // triangleMenu종료 후 메인으로 이동
				return;
			}
			
			this.inputSize(2, choice);
		}
	}

	public void inputSize(int type, int menuNum) {
		//type -> 1 삼각형, 2 사각형
		//menuNum -> 1.색칠 2.넓이높이입력
		
		if(type == 1) {
			switch(menuNum) {
			case 1:
				System.out.print("높이 : ");
				double height = sc.nextDouble();
				System.out.print("너비 : ");
				double width = sc.nextDouble();
				
				double areaSize = tc.calcArea(height, width);
				System.out.println("삼각형의 면적 : " + areaSize);
				break;
			case 2:
				System.out.print("색깔을 입력하세요 : ");
				String color = sc.next();
				sc.nextLine();
				tc.paintColor(color);
				System.out.println("색이 수정되었습니다.");
				break;
			case 3:
				this.printInformation(type);
				break;
			}
		} else {
			double height, width;
			switch(menuNum) {
			case 1:
				System.out.print("높이 : ");
				height = sc.nextDouble();
				System.out.print("너비 : ");
				width = sc.nextDouble();
				
				double perimeter = scr.calcPerimeter(height, width);
				System.out.println("사각형의 둘레: " + perimeter);
				break;
			case 2:
				System.out.print("높이 : ");
				height = sc.nextDouble();
				System.out.print("너비 : ");
				width = sc.nextDouble();
				
				double area = scr.calcArea(height, width);
				System.out.println("사각형의 면적: " + area);
				break;
			case 3:
				System.out.print("색깔을 입력하세요 : ");
				String color = sc.next();
				sc.nextLine();
				scr.paintColor(color);
				System.out.println("색이 수정되었습니다.");
				break;
			case 4:
				this.printInformation(type);
				break;
			}	
		}
			
			
	}

	public void printInformation(int type) {
		if(type == 1) {
			System.out.println(tc.print());
		} else {
			System.out.println(scr.print());
		}
	}
}
