package m.api;

import java.util.StringTokenizer;

public class B_String {
	public void method01() {
		//1. 생성자를 통한 문자열 생성
		String str1 = new String("hello");
		String str2 = new String("hello");
		
		System.out.println(str1);
		System.out.println(str2);
		//String클래스에 toString메서드는 이미 오버라이딩 되어있음
		
		System.out.println(str1 == str2);
		//String객체의 주소값을 비교하기 때문에 false가 나옴
		
		System.out.println(str1.equals(str2));
		//String클래스에서 equals메서드는 이미 오버라이딩 되어있음(주소값비교가 아닌 값비교)
		
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		//String클래스에서 hashCode메서드도 이미 오버라이딩 되어있음(주소값이 아닌 문자열을 가지고 만듬)
		
		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
		
		//----------------------------------------------------------
		
		//2. 문자열을 리터럴값으로 생성
		String str3 = "hello";
		String str4 = "hello";
		//리터럴 제시시 상수풀영역에 들어감
		//String pool특징 : 동일한 문자열을 중복해서 가지지 않는다.
		
		System.out.println(str3.hashCode());
		System.out.println(str4.hashCode());
		
		System.out.println(str3 == str4);
		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
		System.out.println(System.identityHashCode(str3));
		System.out.println(System.identityHashCode(str4));
		
		str3 = "bye";
		System.out.println(System.identityHashCode(str3));
		//변경하는 순간 기존의 문자열 자리에서 변경되는게 아니라
		//새로운 값을 참조하도록 되어있다.(상수풀에 새로운 값이 등록되고 해당 주소를 가르킴)
	}
	
	public void method02() {
		String str1 = "Hello World";
		
		//문자열.charAt(int index) : char
		//문자열에서 전달받은 index위치에 문자를 추출해서 반환
		char ch = str1.charAt(6);
		System.out.println("ch : " + ch);
		
		//문자열.concat(String str) : String
		//문자열과 전달된 또다른 문자열을 하나로 합쳐서 새로운 문자열 리턴
		String str2 = str1.concat("!!!");
		System.out.println("str2 : " + str2);
		
		String str3 = str1 + "!!!";
		System.out.println("str3 : " + str3);
		
		//문자열.contains(찾고자하는 문자열) : boolean
		//문자열에 전달된 문자열이 포함되어있는지 확인하여 반환
		System.out.println("str1에 Hello라는 문자열 포함? : " + str1.contains("Hello"));
		System.out.println("str1에 bye라는 문자열 포함? : " + str1.contains("bye"));
	
		//문자열.substring(시작위치, [끝위치])
		//문자열을 시작위치부터 끝위치-1까지 추출해서 반환
		System.out.println(str1.substring(6));
		System.out.println(str1.substring(6, 9));
		
		//문자열.replace(대상문자, 변경할문자) : String
		//문자열에서 대상문자를 찾아서 변경한문자로 변환된 문자열 반환
		String str4 = str1.replace('l', 'c');
		System.out.println("str1 : " + str1);
		System.out.println("str4 : " + str4);
		
		//문자열.toUpperCase() : String -> 문자열을 전부 대문자 변환
		//문자열.toLowerCase() : String -> 문자열을 전부 소문자 변환
		System.out.println(str1.toUpperCase());
		System.out.println(str1.toLowerCase());
		
		//문자열.trim() : String
		//문자열의 앞뒤 공백을 제거시킨 새 문자열 리턴
		String str5 = "     JA    VA      ";
		System.out.println(str5.trim());
		
		//문자열.toCahrArray() : char[]
		char[] arr = str1.toCharArray();
		for(char c : arr) {
			System.out.print(c + " ");
		}
		
		//String -> valueOf()
		//다른 타입을 문자열로 변경하는 메서드
		System.out.println();
		System.out.println(String.valueOf(arr));
	}
	
	public void method03() {
		String str = "Java,Oracle,sql,html,css,spring";
		
		//구분자를 기준으로 문자열을 분리시키는 방법
		
		//방법1. 분리된 문자열들을 String[]에 차곡차곡 담아야 할 때
		//      String에서 제공하는 split메서드 사용
		// 문자열.split(구분자) : String[]
		String[] arr = str.split(",");
		for(String st : arr) {
			System.out.println(st);
		}
		
		//분리된 문장열 배열을 다시 String으로 변경하는 방법
		//String.join(구분자, 배열);
		String str2 = String.join(",", arr);
		System.out.println(str2);
		
		//방법2. 분리된 문자열들을 각각 토큰으로써 관리가능
		StringTokenizer stn = new StringTokenizer(str, ",");
		
		System.out.println(stn.countTokens());
		
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		System.out.println(stn.nextToken());
		
		//System.out.println(stn.nextToken()); 더이상 토큰이 남아있지 않을 때 다음 토큰을 가져오려하면 예외발생
	
		stn = new StringTokenizer(str, ",");
		int size = stn.countTokens();
		for(int i=0; i<size; i++) {
			System.out.println(stn.nextToken());
		}
		
		stn = new StringTokenizer(str, ",");
		while(stn.hasMoreElements()) {
			System.out.println(stn.nextToken());
		}
	}

}
