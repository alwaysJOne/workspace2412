package k.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunException {
	public void method01() {
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.print("a/b...a : ");
			int a = sc.nextInt();
			
			System.out.print("a/b...b : ");
			int b = sc.nextInt();
			
			System.out.printf("%d / %d = %d \n", a, b, a/b);
			System.out.println("계산종료");
		} catch(ArithmeticException e) {
			System.out.println("수학적 에러발생");
		} catch(InputMismatchException e) {
			System.out.println("입력 오류발생");
			e.printStackTrace();
		} finally { //무조간 한번은 실행시켜주는 구문
			sc.close();
		}
		System.out.println("프로그램 종료");
	}
	
	//2. throws : 여기서 예외를 처리하지않고 현재 이 메서드를 호출한 곳으로 예외처리를 떠넘기겠다.
	public void method02() {
		/*
		 * CheckedException : 반드시 예외처리를 해야하는 예외들
		 * -> 조건문을 미리 제시할 수 없음(왜? 예측이 불가한 곳에서 문제가 발생할 수 있기 때문에)
		 * -> 외부 매개체와 입출력이 일어날 때 발생할 수 있음
		 */
		
		//Scanner와 같이 키보드로 입력받은 값을 코드로 가져와주는 객체(단, 문자열로만 읽어들인다)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = null;
		
		try {
			//1. try-catch문 : 여기서 곧바로 예외를 처리하겠다.
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("입력 : " + str);
	}
	
	public void methodA() throws IOException, CustomException {
		methodB();
	}
	
	public void methodB() throws IOException, CustomException {
		methodC();
		methodD();
	}
	
	public void methodC() throws IOException {
		throw new IOException(); // 예외 직접 발생
	}
	
	public void methodD() throws CustomException {
		throw new CustomException("나만의 이유로 에러 발생"); // 예외 직접 발생
	}
}
