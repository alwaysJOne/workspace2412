package z.practice.exception.ex2;

import java.util.Scanner;

public class NumberMenu {
	public void menu() {
		try(Scanner sc = new Scanner(System.in);){
			System.out.print("정수1 : ");
			int num1 = sc.nextInt();
			
			System.out.print("정수2 : ");
			int num2 = sc.nextInt();
			
			NumberController nc = new NumberController();
			boolean isDouble = nc.checkDouble(num1, num2);
			System.out.printf("%d은(는) %d의 배수인가 ? %b \n", num1, num2, isDouble);
		} catch (NumRangeException e) {
			e.printStackTrace();
		}
		
	}
}
