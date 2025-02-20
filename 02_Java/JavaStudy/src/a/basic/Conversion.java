package a.basic;

public class Conversion {
	/*
	 * 형변환
	 * 자료형을 다른 자료형으로 변경해주는 것
	 * 
	 * 자동형변환
	 * 두개 이상의 자료형을 연산할 때 하나의 자료형으로 일치시켜야 연산이 가능
	 * -> 데이터손실이 적은 방향으로 컴파일러가 직접 형변환을 해주는 것
	 * 
	 * 명시적형변환(강제형변환)
	 * 값의 번위가 큰 자료형을 값의 범위가 작은 자료형으로 변환할 때
	 * 자동으로 컴파일러가 이를 수행하지 않는다. -> 프로그래머가 직접 진행한다.(데이터손실 감수)
	 * */
	
	public static void main(String[] args) {
		double pi = 3.1415;
		int number = (int)pi; // 3
		System.out.println(number);
		
		char a = 'a';
		System.out.println("a : " + (int)a);
		System.out.println("a+1 : " + (char)(a + 1));
		System.out.println("a+1 : " + (char)(98));
		
		long num1 = 30000000007L;
		int num2 = (int)num1;
		System.out.println(num2);
	}
}
