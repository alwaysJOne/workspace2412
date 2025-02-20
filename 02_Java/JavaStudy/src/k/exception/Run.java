package k.exception;

import java.io.IOException;

public class Run {
	/*
	 	에러종류
	 	- 시스템에러 : 컴퓨터의 오작동으로 발생하는에러 -> 소스코드로 해결불가
	 	- 컴파일에러 : 소스코드 문법상의 오류(개발자 실수) -> ide가 체크를 해주거나 컴파일러가 알려주면 수정 -> 발견과 해결이 쉽다.
	 	- 런타임에러 : 코드상으로 문제가 없지만 프로그램 실행도중 발생하는 에러(개발자가 미처 처리하지못한 예외 또는 사용자의 실수)
	 	- 논리에러 : 문법적으로도 문제없고 실행했을 때도 문제가 없지만 프로그램의 의도와 맞지않는 동작이나 결과가 나타나는 에러
	 
	 	컴파일에러, 런타임에러, 논리에러같은 개발자가 예측가능하고 수정할 수 있는 에러들을 가지고 작업
	 	-> 예외 -> Exception
	 	
	 	이런 "예외"가 발생했을 때 "처리"하는 방법을 "예외처리"라고 함.
	 	
	 	*예외를 처리하는 목적
	 	- 예외를 처리하지않고 그대로 예외가 발생할 경우 프로그램이 비정상적으로 종료될 수 있기 때문에 이를 방지해야한다.
	 
	 	*예외를 처리하는 방법
	 	1. try-catch문을 이용
	 	2. throws를 이용(떠넘기기)
	 	
	 	*사용한 리소스를 반납하는 방법 2가지
	 	1. finally에서 반납
	 	2. try-with-resource문법을 사용
	 */

	public static void main(String[] args) {
		RunException re = new RunException();
//		re.method01();
		re.method02();
		
		try {
			re.methodA();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}

}
