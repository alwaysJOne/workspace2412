package o.collection.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SetRun {

	public static void main(String[] args) {
		HashSet<String> hs1 = new HashSet();
		
		hs1.add("반갑습니다");
		hs1.add(new String("반갑습니다"));
		hs1.add(new String("여러분"));
		hs1.add(new String("안녕하세요."));
		hs1.add(new String("여러분"));
		
		System.out.println(hs1); //저장순서를 유지하지 않는다.! 중복된 데이터(동일객체) 보관 불가
		//String에 equlas() 오버라이딩 -> "실제 담긴 문자열"가지고 동등비교 진행해서 일치하면 true/ 아니면 false
		//String에 hashCode() 오버라이딩 -> "실제 담긴 문자열"을 가지고 10진수 형태의 해시코드로 반환
		
		HashSet<Student> hs2 = new HashSet();
		hs2.add(new Student("최지원", 22, 95));
		hs2.add(new Student("홍길동", 27, 45));
		hs2.add(new Student("김지민", 41, 60));
		hs2.add(new Student("최지원", 22, 95));
		System.out.println(hs2);
		
		//동일객체 : 객체마다 hashCode와 equlas의 결과가 모두 일치(true)하는 객체
		
		/*
		 * equlas, hashCode를 오버라이딩하지 않으면 Object의 equlas, hashCode를 사용한다.
		 * equlas : e두 객체의 주소값을 비교해서 같으면 true/ 아니면 false
		 * hashCode : 해당 객체의 주소값을 가지고 10진수 형태의 해시코드로 반환
		 */
		
		// hs2.get() -> 인덱스의 개념이 없기때문에 get을 할 수 없음
		
		//HashSet에 담긴 모든 객체를 순차적으로 접근
		//1. for each문 이용
		for(Student st : hs2) {
			System.out.println(st);
		}
		
		//2. Iterator인터페이스를 이용해서 순차적 접근
		// Iterator : 컬렉션 요소에 순차적으로 접근하기위한 반복자 인터페이스
		//  hasNext() : 다음요소가 있는지를 알려줌(true/false)
		Iterator it = hs2.iterator();
		while(it.hasNext()) { //다음으로 가져올 요소가 있니?
			Object obj = it.next();
			System.out.println(obj);
		}
		
		//3. ArrayList에 담아준 다음 ArrayLst를 반복적으로 돌아가며 접근하는 방법
		ArrayList list = new ArrayList();
		list.addAll(hs2);
		
		for(int i=0; i<list.size(); i++) {
			Object obj = list.get(i);
			System.out.println(obj);
		}
		
		
		
	}

}
