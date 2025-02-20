package o.collection.list;

import java.util.ArrayList;
import java.util.List;

public class ListRun {
	/*
	 * 컬렉션이란
	 * 자료구조 개념이 내장되어있는 클래스로 자바에서 제공하는 "자료구조"를 담당하는 "프레임워크"다.
	 * 
	 * - 자료구조 : 방대한데이터를 보다 효율적으로 관리(추가, 삭제, 조회, 정렬, 수정)할 수 있도록 해주는 구조
	 * - 프레임워크 : 이미 만들어져있는 틀(코드더미)
	 * 
	 */
	public static void main(String[] args) {
		//ArrayList
		List list = new ArrayList(); //크기를 지정해도되고 안해도 됨
		
		System.out.println(list); //빈 배열상태
		
		//1.add(E e) : 리스트공간 끝에 전달된 데이터를 추가시켜주는 메서드
		
		list.add(new Music("에피소드", "이무진"));
		list.add(new Music("천상연", "이창섭"));
		list.add(new Music("비의 랩소디", "임재현"));
		list.add("끝");
		
		//지정된 크기보다 더 많이 넣어도 에러가 발생하지 않는다. -> 장점1. 크기제약x
		//다양한 타입의 데이터를 담을 수 있음 -> 장점2. 여러타입 보관가능
		
		System.out.println(list); // list의 특징 : 순서를 유지하면서 담긴다.(0번인덱스부터 차근차근)
		
		//2. add(int index, E e) : 직접 인덱스를 지정해서 해당위치에 데이터를 추가할 수 있음
		list.add(1, new Music("고민중독","qwer"));
		System.out.println(list);
		
		//3. remove(int index) : 해당 인덱스 위치의 데이터를 삭제시켜주는 메서드
		list.remove(1);
		System.out.println(list);
		
		//4. set(int index, E e) : 해당인덱승의 값을 전달받은 e객체로 덮어씌움
		list.set(2, new Music("고민중독","qwer"));
		System.out.println(list);
		
		//5. size() : 리스트의 사용중인 사이즈를 반환시켜주는 메서드
		System.out.println(list.size());
		
		//6. get(int index) : 해당 인덱스의 객체를 반환
		Music m = (Music)list.get(0);
		System.out.println(m);
		
		//7. subList(int index1, int index2) : List -> 요소들을 출력해서 새로운 List로 반환
		List sub = list.subList(1, 3); //1번인덱스부터 3번인덱스 전까지
		System.out.println(sub);
		
		//8. addAll(Collection c) : 컬렉션을 통째로 뒤에 추가할 수 있음
		list.addAll(sub);
		System.out.println(list);

		//9. isEmpty() : boolean -> 컬렉션이 비어있는지 확인하는 메서드
		System.out.println(list.isEmpty());
		
		//10. clear() : 전부 비워주는 메서드
		//list.clear();
		//System.out.println(list.isEmpty());
		
		//----------리스트 전체 접근방법
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("==========================");
		for(Object o : list) {
			System.out.println(o);
		}
	}

}
