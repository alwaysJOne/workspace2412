package f.object.ex1;
/*
 * 클래스의 구조
 * 
 * 접근제한자 class 클래스명{
 * 		//필드영역(사용할 데이터를 선언하는 영역)
 * 
 * 		//생성자영역(데이터를 초기화해기위한 특수목적의 메서드를 정의하는 영역)
 * 
 * 		//메서드영역(클래스의 기능을 정의하는 영역)
 * 
 * }
 */

public class Student{
	//필드
	String name; //이름
	int age; //나이
	double height; //키
	
	//메서드
	public void myInfo() { // 내 정보를 출력하는 기능
		System.out.println("안녕하세요. 저는 " + this.age + "살의 " + this.name + "입니다.");
	}
}
