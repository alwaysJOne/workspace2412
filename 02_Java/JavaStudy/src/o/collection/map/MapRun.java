package o.collection.map;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class MapRun {

	public static void main(String[] args) {
		HashMap<String, Snack> hm = new HashMap();
		HashMap<Snack, String> hm2 = new HashMap();

		//계층구조를 보면
		//List계열, Set계열의 클래스들은 Collection을 구현한 클래스이다.
		//-> 객체를 추가하고자할 때 공통적으로 add메서드를 이용
		
		//Map계열은 Collection을 구현한 클래스가 아니다.
		//-> 객체를 추가할 때 put메서드를 사용(key, value 쌍으로 담는)
	
		//1. put(k,v) : map에 k,v쌍으로 값을 추가하는 메서드
		hm.put("다이제", new Snack("초코맛", 2000));
		hm.put("도리토스", new Snack("나초치즈맛", 500));
		hm.put("먹태깡", new Snack("와사비맛", 600));
		hm.put("틴틴", new Snack("초코맛", 2000));
		
		hm2.put(new Snack("초코맛", 2000),"다이제");
		hm2.put(new Snack("나초치즈맛", 500), "도리토스" );
		hm2.put(new Snack("와사비맛", 600), "먹태깡" );
		hm2.put(new Snack("초코맛", 2000), "틴틴");
		//-> 칼로리값 변경해도 맛만으로 동일객체로 판단하게 만들기
		
		System.out.println(hm);
		System.out.println(hm2);
		//저장되는 순서 유지가 안됨! value값이 중복되어도 키값이 중복되지 않으면 저장됨
		
		//2. get(key) : 해당 키값을 가지는 value가 반환된다.
		System.out.println(hm.get("먹태깡"));
		
		//3. .size() : 담겨있는 객체 수
		System.out.println(hm.size());
		
		//4.remove(key) => 해다키값을 찾아서 그 벨류세트를 삭제
		hm.remove("도리토스");
		System.out.println(hm);
		
		//전체 객체에 접근하는 방법
		
		//Iterator반복자 이용x
		//for each 이용x
		
		//Map의 key는 Set과 매우 유사하다.
		//1. key를 모아서 Set자료구조의 형태로 반환받는다
		Set keySet = hm.keySet();
		System.out.println(keySet);
		for(Object key : keySet) {
			System.out.println("키:" + key + " 값:" + hm.get(key));
		}
		
		//2. entrySet()을 이용하는 방법
		//entrySet() : Map인터페이스에서 제공하는 메서드, 맵에 저장된 모든 키-값 쌍을 set형태로 변환한 것
		Set entrySet = hm.entrySet();
		
		for(Object entry : entrySet) {
			System.out.println(((Entry)entry).getKey() + " : " + ((Entry)entry).getValue());
		}
	}
	

}
