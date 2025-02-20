package c.controll;

import java.util.Scanner;

public class Practice1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/*
		 * 나이를 입력받아
		 * 13세이하면 : 어린이
		 * 13세초과 19세이하 : 청소년
		 * 19세 초과 : 성인
		 * 
		 * [출력문]
		 * 나이를 입력 : xx
		 * xx세는 xxx에 속합니다.
		 */
		
		/*
		int age;
		
		System.out.print("나이를 입력 : ");
		age = sc.nextInt();
		
		if(age <= 13) {
			System.out.println(age + "세는 어린이에 속합니다.");
		}
		
		if(age > 13 && age <= 19) {
			System.out.println(age + "세는 청소년에 속합니다.");
		}
		
		if(age > 19) {
			System.out.println(age + "세는 성인에 속합니다.");
		}
		
		if(age <= 13) {
			System.out.println(age + "세는 어린이에 속합니다.");
		} else if(age <= 19) {
			System.out.println(age + "세는 청소년에 속합니다.");
		} else {
			System.out.println(age + "세는 성인에 속합니다.");
		}
		*/
		
		/*
		 * 성별을 (m/f)(대소문자 상관x)로 입력받아 남학생인 여학생인지
		 * 출력하는 프로그램을 작성하세요.
		 * 
		 * [출력문]
		 * 성별(m/f) : x
		 * 여학생입니다 / 남학생입니다 / 잘못입력하셨습니다.
		 */
		/*
		char gender;
		
		System.out.print("성별(m/f) : ");
		gender = sc.next().charAt(0); // m, M, f, F
		
		if (gender == 'f' || gender == 'F') {
			System.out.println("여학생입니다.");
		} else if(gender == 'm' || gender == 'M') {
			System.out.println("남학생입니다.");
		} else {
			System.out.println("잘못입력하셨습니다.");
		}
		*/
		/*
		 * 정수(양수)를 입력받아
		 * 짝수인지 홀수인지 출력하는 프로그램을 작성하라
		 * 
		 * [출력문]
		 * 정수 입력 : xx
		 * 짝수다 / 홀수다 / 양수가아니다.
		 * */
		
		int num;
		
		System.out.print("정수 입력 : ");
		num = sc.nextInt();
		
		if(num > 0) {
			//양수
			if(num % 2 == 0) {
				//짝수
				System.out.println("짝수다.");
			} else {
				//홀수
				System.out.println("홀수다.");
			}
		} else {
			System.out.println("양수가아니다.");
		}
		
		if(num > 0 && num % 2 == 0) {
			System.out.println("짝수다.");
		} else if(num > 0 && num % 2 != 0) {
			System.out.println("홀수다.");
		} else {
			System.out.println("양수가아니다.");
		}
	}
}
