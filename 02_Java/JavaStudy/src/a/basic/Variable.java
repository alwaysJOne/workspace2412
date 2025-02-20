package a.basic;

public class Variable {
	/*
	 	변수 : 값을 기록하고 사용하기위한 메모리 공간, 
	 	값을 보관하거나, 보관된 값을 가져와 사용할 수 있다.
	 	
	 	[표현법]
	 	자료형 변수이름;
	 	
	 	자료형 : 변수의 크기및 모양을 지정해주는 부분
	 	변수이름 : 변수를 식별하기위해 붙여주는 이름
	 */
	public static void main(String[] args) {
		//정수형 변수 num을 선언
		int num;
		
		//정수형 변수 num에 10이라는 값을 대입해라.
		num = 10;
		System.out.println(num);
		
		//참과거짓을 표현하는 자료형으로 isTrue라는 이름의 변수를 만들어라.
		boolean isTrue = false;
		isTrue = true;
		System.out.println(isTrue);
		
		//실수형(소수점 6자리까지) 자료형을 지정할 수 있는 num2라는 이름의 변수를 만들어라.
		float num2;
		num2 = 3.14234235243242345f; 

		System.out.printf("%.6f", num2);
		

		//===============================================
		// 원시타입 : 가장 기본적인 데이터타입, 값 자체를 저장하며 추가적인 속성이 없다.
		
		/*
		 * 정수 자료형
		 * byte(1) short(2) int(4) long(8)
		 * 모두 정수를 저장하는 자료형, 각 자료형마다 표현할 수 있는 수의 범위가 다르다.
		 * 
		 * 정수 리터럴은 기본적으로 int타입으로 간주된다.
		 * 그러나 변수의 타입에 따라서 적절하게 변환되거나 명시적으로 변환해주어야한다.
		 * byte, short는 범위내에 있을 경우 자동으로 변환된다.
		 * */
		
		byte by = 10;
		short sh = 10;
		int in = 10;
		long lo = 10; // 10L
		
		System.out.println("정수 자료형");
		System.out.println(by + ", " + sh + ", " + in + ", " + lo);
		
		/*
		 * 실수 자료형
		 * float(4) double(8)
		 * 실수를 저장하는 자료형으로 float보다 double이 더 정밀한 수를 표현할 수 있다.
		 * */
		float fl = 4.24f;
		double dou = 4.24;
		System.out.println("실수 자료형");
		System.out.println(fl + ", " + dou);
		
		/*
		 * 문자자료형
		 * char(2)
		 * 문자 하나를 담을 수 있는 자료형 ''
		 * 자바는 2바이트 유니코드로 표현한다.
		 * */
		
		char ch1 = '최';
		char ch2 = '지';
		char ch3 = '원';
		
		System.out.println("" + ch1 + ch2 + ch3);
		System.out.println(ch1);
		
		/*
		 * 논리자료형
		 * boolean(1)
		 * true(참)과 false(거짓)을 저장하는 자료형
		 * */
		
		boolean b1 = 10 > 5; //true
		boolean b2 = 20 == 10; // false
		
		System.out.println("논리 자료형");
		System.out.println(b1);
		System.out.println(b2);
		
		/*
		 * 문자열
		 * String(======================객체======================)
		 * 문자열 같은 경우 할당해야할 메모리크기가 너무 가변적이므로 원시타입이 아닌 객체로 변수를 만들어 준다.
		 * */
		
		String str1 = "안녕하세요.안녕하세요. 안녕안녕";
		String str2 = new String("문자열!!!!~~~~");
		String str3 = null; // String은 참조변수이기 때문에 기본값이 null
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		
		/*
		 * 이스케이프 시퀀스
		 * 문자열내에서 탭, 백슬러시, 작은따옴표등을 코드로 사용하기위한 방식
		 * 
		 * 탭문자 \t
		 * 백슬래시 \\
		 * 작은따옴표 \'
		 * 큰따옴표 \"
		 * 개행문자 \n
		 * 등등 다양하게 필요한 것을 그때그때 찾아서 사용
		 * */
		
		System.out.println("저는 이렇게 말했습니다. \"저는 배가 고파요\"");
		System.out.println("저는 \n지금\n배가 고파요.");
		
		/*
		 * 상수
		 * 수학 -> 변하지않는 값
		 * 프로그래밍 -> 한번만 쓸 수 있는 메모리
		 * 
		 * [표현법]
		 * final 자료형 변수이름;
		 * 
		 * 상수의 이름을 모두 대문자로 짓는 것이 관례
		 * 이름이 둘 이상의 단어로 되어있는 경우 언더바로 연결해준다.
		 * */
		
		final int MY_AGE;
		MY_AGE = 90;
		//MY_AGE = 111; 값을 초기화하면 더이상 변경할 수 없다.
	}
}
