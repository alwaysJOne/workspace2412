package d.loop;

import java.util.Scanner;

public class A_for {
	/*
	 * <반복문>
	 * 프로그램 흐름을 제어하는 제어문 중 하나.
	 * 어떤 실행코드를 반복적으로 수행시켜준다.
	 * 
	 * 크게 두 종류로 나뉜다(for / while(do-while))
	 * 
	 * for문
	 * 
	 * for(초기식; 조건식; 증감식){ //반복횟수를 지정하기위해 제시하는 것들
	 * 		반복적으로 실행시키고자하는 코드더미
	 * }
	 * 
	 * -초기식 : 반복문 수행될 때 "처음에 딱 한번만 실행하는 구문"
	 * 			(반복문 안에서 사용 될 변수를 선언및 초기화하는 작업)
	 * 
	 * -조건식 : "반복문이 수행될 조건"을 작성하는 구문
	 * 			조건식이 true일 경우 해당 반복을 실행
	 * 			조건식이 false가 되는 순간 반복문을 탈출
	 * 			(초기식에서 제시된 변수를 가지고 조건식을 정함)
	 * 
	 * -증감식 : 반복문을 제어하는 변수 값을 증감시키는 구문
	 * 			(보통 초기식에 제시된 변수를 가지고 증감을 시킴)
	 * */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

//		System.out.println("안녕하세요.");
//		System.out.println("안녕하세요.");
//		System.out.println("안녕하세요.");
//		System.out.println("안녕하세요.");
//		System.out.println("안녕하세요.");
//		System.out.println("안녕하세요.");
//		System.out.println("안녕하세요.");
//		System.out.println("안녕하세요.");
//		System.out.println("안녕하세요.");
//		System.out.println("안녕하세요.");
		
		//10번 반복하는 반복문
		/*
		for(int i=0; i<10; i++) {
			System.out.println("안녕하세요. " + i);
		}
		*/
		
		/*
		 * n번 반복하는 for문의 형식
			for(int i=0; i<n; i++) {
				실행코드
			}
		*/
		/*
		for(int i=11; i<= 17; i+=2) {
			System.out.println(i);
		}
		*/
		
		//1~5까지 숫자를 순차적으로 출력하세요.(1 2 3 4 5 )
		
//		for(int i=1; i<=5; i++) {
//			System.out.print(i + " ");
//		}
		
		/*
		 * 자바의 지역변수
		 * - 특정 메서드나 블록({}) 내부에서 선언된다
		 * - 선언된 블록({}) 범위 내에서만 접근이 가능하고 해당 블록이 종료되면 함께 사라진다.
		 * */
		
		/*
			초기식, 조건식, 증감식을 사용하지 않아도 상관없지만 그렇다면 for문을 사용할 이유가 없다.
			for(;;) {
				System.out.println("반복됩니까?");
			}
		*/
		
		//5~1까지 순차적으로 출력 : (5 4 3 2 1 )
		/*
		for(int i=5; i>0; i--) {
			System.out.print(i + " ");
		}
		*/
		
		//정수 n을 입력받아 1부터 n까지 1씩 증가시키면서 출력을 해라
		//정수입력 : n
		//1 2 3 4 ... n
		/*
		int num;
		
		System.out.print("정수입력 : ");
		num = sc.nextInt();
		
		for(int i = 1; i<=num; i++) {
			System.out.print(i + " ");
		}
		*/
		/*
		int count;
		
		System.out.print("아메리카 몇잔을 구매하시겠습니까?(가격1500원) : ");
		count = sc.nextInt();
		
		int sum = 0;
		for(int i=0; i < count; i++) {
			sum += 1500;
			System.out.println(i + "번째 반복중... 누적금액 : " + sum);
		}
		
		System.out.print("결제하실 금액 : " + sum + "원");
		*/
		
		/*
		// 1~10 숫자중 홀수만 출력
		// 1 3 5 7 9
		for(int i=1; i<=10; i++) {
			//홀수만출력
			if(i % 2 != 0) {
				System.out.print(i + " ");
			}
		}
		*/
		
		//1부터 100까지의 합을 구해라
		// 1~100까지의 총 합 : xxxx
		/*
		int sum = 0;
		for(int i=1; i<=100; i++) {
			sum += i;
		}
		
		System.out.println("1~100까지의 총 합 : " + sum);
		*/
		
		//정수 n을 입력받아서 1부터 n까지 1씩 증가하며 모든 수를 더해서 출력해라.
		/*
		int num, sum = 0;
		System.out.print("정수 입력: ");
		num = sc.nextInt();
		
		for(int i=1; i<=num; i++) {
			sum += i;
		}
		
		System.out.println("1부터 " + num + "까지의 총 합 : " + sum);
		*/
		
		/*
		 * java.lang.Math 클래스에서 제공하는 random()함수를 호출하면 매번 다른 랜덤값을 받을 수 있다.
		 * 
		 * Math.random() 호출시 -> 0.0~0.9999999999999사이의 랜덤값을 반환
		 * 
		 * int num = Math.random(); // double형의 random수이기 때문에 int로 받을 수 없음
		 * 
		 * int num = (int)Math.random(); (0.0~0.9999999999999) -> 0 ~ 0
		 * int num = (int)(Math.random() * 10) + 1; (0~9.999999999999) -> 1 ~ 10
		 * 최소값~최대값 랜덤 수 : (int)(Math.random() * (최대값 + 1 - 최소값)) + 최소값
		 */
		/*
			int num = (int)(Math.random() * 10);
			System.out.println(num);
		*/
		
		//random(1~10)한 숫자 n을 생성해서 1부터 n까지 모두 더한 값을 출력
		// random 수 : n
		// 1~n까지의 합 : xxx
		/*
		int sum = 0, n = (int)(Math.random() * 10) + 1;
		System.out.println("random 수 : " + n);
		for(int i=1; i<=n; i++) {
			sum += i;
		}
		System.out.printf("1~%d까지의 합 : %d", n, sum);
		*/
		
		// random(5~50)한 숫자 n을 생성 후 1부터 n까지의 수중 짝수만 출력
		/*
		int n = (int)(Math.random() * 46) + 5;
		for(int i=1; i<=n; i++) {
			if(i % 2 == 0) {
				System.out.print(i + " ");
			}
		}
		*/
		
		/**
		 * 각 인덱스별 문자 출력
		 * str.charAt(0); -> H
		 * str.charAt(1); -> e
		 * str.charAt(2); -> l
		 * str.charAt(3); -> l
		 * str.charAt(4); -> o
		 * index반복 -> 0~(길이-1)
		 * 
		 * 문자열 길이 : 문자열.length()
		 */
		/*
		String str ="Hello";
		
		System.out.println("길이 : " + str.length());
		for(int i=0; i<str.length(); i++) {
			System.out.println(str.charAt(i));
		}
		*/
		
		//사용자에게 문자열을 입력받아 해당 문자열의 짝수자리 글자만 출력
		//문자열 입력 : Hello
		//el
		/*
		String str;
		
		System.out.print("문자열 입력 : ");
		str = sc.next();
		
		//각 인덱스별 문자를 가져와서 출력
		for(int i=0; i<str.length(); i++) {
			if(i % 2 == 1) {
				System.out.print(str.charAt(i));
			}
		}
		*/
		
		/*
		 * 2 * 1 = 2
		 * 2 * 2 = 4
		 * 2 * 3 = 6
		 * 2 * 4 = 8
		 * 2 * 5 = 10
		 * 2 * 6 = 12
		 * 2 * 7 = 14
		 * 2 * 8 = 16
		 * 2 * 9 = 18
		 * 
		 * 2 * i = (2*i)
		 * 3 * i = (3*i)
		 * 4 * i = (4*i)
		 * 5 * i = (5*i)
		 * 6 * i = (6*i)
		 * 7 * i = (7*i)
		 * 8 * i = (8*i)
		 * 9 * i = (9*i)
		 * 
		 * j * i = (j*i)
		 */
		for(int j=2; j<=9; j++) {
			for(int i=1; i<=9; i++) {
				System.out.println(j + " * " + i + " = " + (j*i));
			}
		}
		
	} 

}
