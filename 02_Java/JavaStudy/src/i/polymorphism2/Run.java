package i.polymorphism2;

public class Run {

	public static void main(String[] args) {
		//1. 부모타입 래퍼런스(참조변수)로 부모객체를 다루는 경우
		Car c1 = new Car("빨간색", "가솔린", 2020);
		c1.drive();
		//(Sonata)c1 -> 실제로 만들어진 공간이 Sonata가 아니다.(업캐스팅되지 않았다.) 다운캐스팅 불가

		//2. 자식타입 래퍼런스로 자식객체를 다루는 경우
		Avante c2 = new Avante("하얀색", "LPG", 2022);
		c2.drive(); //Avante클래스에서 오버라이딩 된 drive()호출
		c2.moveAvante();
		((Car)c2).drive(); // 부모참조변수로 업캐스팅 가능 -> 오버라딩된 메서드는 생성된 메모리를 기준으로 호출됨
		//((Car)c2).moveAvante(); // 업캐스팅시 자식요소의 메모리 접근 불가
		
		//3.부모타입 래퍼런스로 자식객체를 다루는 경우(업캐스팅)
		Car c3 = new Sonata("검정", "디젤", 2015);
		//c3.moveSonata(); 부모타입의 참조변수이기 때문에 자식요소의 메서드 접근 불가
		((Sonata)c3).moveSonata();
		
		/*
		 * "상속구조"의 클래스들 간의 형변환 가능
		 * 1. UpCasting
		 *    자식타입 -> 부모타입으로 형변환
		 *    자동형변환
		 *    ex) Car c1 = new Sonata();
		 *    
		 * 2. DownCasting
		 * 	  부모타입 -> 자식타입으로 형변환
		 * 	  강제형변환 해야함, 업캐스팅관계에 있을 때만 가능하다.
		 * 	  ex) ((Sonata)c1).moveSonata()));
		 * 			((자식)부모).자식메서드();
		 */
	}

}
