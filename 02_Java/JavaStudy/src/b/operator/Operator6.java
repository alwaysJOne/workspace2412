package b.operator;

import java.util.Scanner;

public class Operator6 {
	/*
	 * 삼항연산자
	 * [표현식]
	 * 조건식 ? 조건이 참일경우 결과값 : 조건이 거짓일경우 결과값
	 * 
	 * 이때, 조건식은 반드시 true 또는 false가 나오는 논리값이여야한다.
	 * 주로 비교, 논리연산자를 통해 작성
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = (3 < 10) ? 10 : 20; 
		
		//엽력받은 정수값이 양수인지 아닌지 판별 후 출력해라
		//정수값입력 : 
		//양수 / 음수
		System.out.print("정수값 입력 : ");
		int num2 = sc.nextInt();
		
		String result = (num2 == 0) ? "0" : ((num2 > 0) ? "양수" : "음수");
		System.out.println(result);
	}

}
