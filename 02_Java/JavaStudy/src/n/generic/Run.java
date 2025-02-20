package n.generic;

public class Run {
	/*
	 * 제네릭 : 클래스나 메서드가 사용할 데이터타입을 컴파일시점에 지정할 수 있도록 지원하는 문법
	 * -> 매개타입(객체 생성시 전달받아 타입이 정해진다.)
	 */
	public static void main(String[] args) {
		//Box객체를 생성하는 시점에 Box객체에서는 Integer타입을 사용하겠다라고 정해준다.
		Box<Integer> aBox = new Box<>();
		
		Integer[] arr = new Integer[5];
		aBox.setObArr(arr);
		
		String[] strArr = new String[5];
		//aBox.setObArr(strArr); aBox에서 사용하는 타입은 Integer라고 정해줬다.

		
		Box2 bBox = new Box2("안녕");
		String str = (String)bBox.getO();
		//Object타입으로 전달받으면 모든 객체를 받아서 사용할 수 있지만
		//꺼내서 사용할 때 Object로 전달되기 때문에 필수적으로 다운캐스팅이 필요하다.
	}

}
