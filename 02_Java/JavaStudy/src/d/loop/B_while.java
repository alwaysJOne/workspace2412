package d.loop;

import java.util.Scanner;

public class B_while {
	/*
	 * while문
	 * 
	 * [표현법]
	 * 
	 * [초기식;]
	 * while(조건식){
	 * 		실행할 문자
	 * 		[탈출할 방법(증감식 or 분기문)]
	 * }
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		/*
		for(int i=0; i<10; i++) {
			System.out.println("안녕하세요.");
		}
		
		int i=0;
		while(i<10) {
			System.out.println("안녕하세요.");
			i++;
		}
		*/
		
		//사용자가 0을 입력할 때까지 반복해서 숫자를 입력받아 그대로 출력해라. -> for
		
//		for(int n = sc.nextInt(); n != 0; n=sc.nextInt()) {
//			System.out.println(n);
//		}
		
//		int n=sc.nextInt();
//		while(n != 0) {
//			System.out.println(n);
//			n = sc.nextInt();
//		}
		
		//랜덤값을 하나 생성해서
		//1부터 랜덤값(1~100사이)까지의 합계를 구해라
		
		//1에서부터 xx까지의 총 합 : xxx
		/*
		int random = (int)(Math.random() * 100) + 1;
		int sum = 0;
		
		int i = 1;
		while(i <= random) {
			sum += i;
			i++;
		}
		
		System.out.printf("1에서부터 %d까지의 총 합 : %d", random, sum);
		*/
		int num;
		System.out.println("서비스 번호를 입력하세요.");
		System.out.println("1. 추가");
		System.out.println("2. 삭제");
		System.out.println("9. 종료");
		
		System.out.print("입력 : ");
		num = sc.nextInt();
		while(num != 9) {
			switch(num) {
			case 1:
				System.out.println("추가되었습니다.");
				break;
			case 2:
				System.out.println("삭제되었습니다.");
				break;
			}
			System.out.print("입력 : ");
			num = sc.nextInt();
		}
	}

}
