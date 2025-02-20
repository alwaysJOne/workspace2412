package f.object;

public class C_Method {
	
	//메소드 오버로딩
	//메소드의 이름이 동일해도 매개변수의 갯수 또는 타입이 다르면 메소드를 구분할 수 있다.
	//단, 호출시점에 구분해서 호출할 수 있어야하므로 반환형이 다른 것은 아무 의미가 없다.
	//자동형변환은 매개변수로 인자를 전달할 때도 동작하기 때문에 모호한 오버로딩을 하지 말자.

	public static void main(String[] args) {
		System.out.println(adder(5, 10));
		System.out.println(adder(5));
		System.out.println(adder(5, "안녕"));
	}
	
	public static int adder(int num1, int num2) {
		int addResult = num1 + num2;
		return addResult;
	}
	
	public static double adder(double num1, double num2) {
		double addResult = num1 + num2;
		return addResult;
	}
	
	public static int adder(int num1) {
		int addResult = num1 + 10;
		return addResult;
	}
	
	public static int adder(int num1, String ch) {
		String addResult = num1 + ch;
		return num1;
	}
	
//	public static String adder(int num1, String ch) {
//		String addResult = num1 + ch;
//		return addResult;
//	}

}
