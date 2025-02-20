package z.practice.oop.ex2;

import java.util.Scanner;

public class SnackMenu {
	private Scanner sc = new Scanner(System.in);
	private SnackController scr = new SnackController();
	
	public void menu() {
		String kind, name, flavor;
		int numOf, price;
		
		System.out.println("스낵류를 입력하세요.");
		System.out.print("종류 : ");
		kind = sc.next();
		
		System.out.print("이름 : ");
		name = sc.next();
		
		System.out.print("맛 : ");
		flavor = sc.next();
		
		System.out.print("개수 : ");
		numOf = sc.nextInt();
		
		System.out.print("가격 : ");
		price = sc.nextInt();
		
		String result = scr.saveData(kind, name, flavor, numOf, price);
		System.out.println(result);
		
		System.out.print("저장한 정보를 확인하시겠습니까?(y/n) : ");
		char ch = sc.next().charAt(0);
		if(ch == 'y') {
			String snackData = scr.confirmData();
			System.out.println(snackData);
		}
	}
}
