package z.practice.object.ex3;

//이름 나이 수학, 영어, 국어점수를 가지고있는 학생클래스
public class Student{
	//필드
	String name; //이름
	int age; //나이
	double mathScore; //수학점수
	double enScore; //영어점수
	double koScore; //국어점수
	
	//생성자 
	//객체를 초기화해주기 위한 특수목적 메서드
	//개발자가 생성해주지 않으면 기능이없는 디폴트생성자가 컴파일과정에서 생성됨.
	//생성자는 class와 이름이 동일하다.
	//반환형이 존재하지 않는다.
	public Student() {
		super();
	}

	//생성자도 메서드 오버로딩이 가능하다.
	public Student(String name, int age, double mathScore, double enScore, double koScore) {
		super();
		this.name = name;
		this.age = age;
		this.mathScore = mathScore;
		this.enScore = enScore;
		this.koScore = koScore;
	}
	

	//메서드
	public void myInfo() { // 내 정보를 출력하는 기능
		System.out.println("안녕하세요. 저는 " + this.age + "살의 " + this.name + "입니다.");
	}
	
	public double getAvg() {
		return (this.enScore + this.koScore + this.mathScore) / 3.0;
	}
}
