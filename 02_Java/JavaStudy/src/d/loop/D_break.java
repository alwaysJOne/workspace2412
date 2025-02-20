package d.loop;

import java.util.Scanner;

public class D_break {
	/*
	 * break : 반복문, switch문 안에서 사용되는 분기문
	 * 		   break;가 실행되는 순간 가장 가까운 반복문 또는 switch문을 강제로 탈출한다.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//랜덤값(1~100) 발생시키고 그 랜덩값을 출력(이과정을 계속반복)
		//단, 랜덤값이 3의 배수(3으로 나누면 0)일 경우 반복문 종료
		/*
		while(true) {
			int num = (int)(Math.random() * 100) + 1;
			System.out.println(num);
			if(num % 3 == 0) {
				break;
			}
		}
		*/
		//로또번호 추첨 1~45까지 중 랜덤해서 6개 추출
//		for(int i=0;; i++) {
//			int num = (int)(Math.random() * 45) + 1;
//			System.out.print(num + " ");
//			
//			if(i<6) {
//				break;
//			}
//		}
		
		//사용자에게 문자열을 입력받아 해당 문자열의 길이를 출력하는 프로그램을 계속 반복해라
		//단, 사용자가 입력한 문자열이 "exit"일 경우 반복문을 탈출
		//"문자열1".equals("문자열2");
	
		String str;
		
		while(true) {
			System.out.print("문자열 입력 : ");
			str = sc.next();
			
			if(str.equals("exit")) {
				break;
			}
			
			System.out.println("길이 : " + str.length());
		}	
	}
}
