package b.operator;

public class Operator3 {

	/*
	 * 산술연산자
	 * + - * / %
	 * 
	 * * / % > + -
	 * 
	 * 복합대입연산자
	 * 산술연산자와 대입연산자를 결합해서 사용할 수 있다.
	 * 
	 * += /= -= *= %=
	 * 
	 * a = a + 3; -> a += 3;
	 * */
	public static void main(String[] args) {
		int a = 5;
		int b = 10;
		int c = (++a) + b; // a = 6, b = 10, c = 16
		int d = 16 / 6; // d = 2
		int e = c % a; // e = 4
		int f = e++; // f = 4, e = 5
		int g = (--b) + (d--); // b = 9,  g = 11, d = 1
		int h = 2;
		
		int i = (a++) + b / (--c / f) * (g-- - d) % (++e + h);
		//	     6    + 9 /  (15 / 4) * (11 - 1) % (6 + 2)
		//			6 + 9 /  3	 * 10 % 8
		//			6 + 3 * 10 % 8
		//          6 + 30 % 8
		//			6 + 6
		//			i = 12
		//a=7  b=9  c=15   f=4  g=10  d=1  e=6  h=2
		//최종적으로 각 변수에 값이 얼마나 들어있는지 예측해보기 

		System.out.println(i);
	}

}




