package e.array;

public class B_Array {
	public static void main(String[] args) {
		int i = 10;
		int[] iArr = new int[5];
		System.out.println(iArr.hashCode());
		
		double[] dArr = new double[3];
		System.out.println(dArr.hashCode());
		
		/*
		 * 실제 리터럴값을 곧바로 담을 수 있는 변수를 일반변수
		 * 주소값을 담고있는 변수는 참조변수(래퍼런스 변수)라고 표현
		 * 
		 * 기본자료형(원시타입) : int, double, float, char, long...
		 * => 실제 리터럴값을 바로 담을 수 있음
		 * 
		 * 그외 자료형(String, Scanner, int[], double[], char[]...) -> 참조변수
		 * => 메모리크기가 가변적이기 때문에
		 * */
		
		for(int j=0; j<iArr.length; j++) {
			System.out.println(iArr[j]);
		}
		
		for(int j=0; j<dArr.length; j++) {
			System.out.println(dArr[j]);
		}
		
		//내가 각 인덱스의 초기화를 하지 않아도 값들이 담겨있음
		// Heap이라는 메모리공간은 절대 빈공간을 허용하지 않는다.
		// => 공간이 만들어질 때 JVM이 기본값으로 모두 초기화 함.
		
		System.out.println(iArr); // 자료형 + @ + 주소값의 16진수 해시코드
		System.out.println(iArr.hashCode()); // 주소값의 10진수 해시코드형태
		
		int[] arr = null; //아무것도 참조하고있지 않음
		System.out.println(arr);
		
		/*
		 * arr.hashCode();
		 * null을 가지고 있는 참조변수에 접근해서 다른 정보를 구하고하자면(함수를 실행하면)
		 * 항상 오류가 발생한다.
		 * 
		 * NullPointerException => 참조변수가 가지고있는 주소값이 없음
		 */
		
		arr = new int[5];
		/*
		 * System.out.println(arr[5]);
		 * 배열의 부적절한 인덱스 제시하면 발생하는 에러
		 * ArrayIndexOutOfBoundsException
		 */
		
		//배열의 가장 큰 단점
		// - 한번 지정된 배열의 크기는 변경이 불가
		// -> 배열의 크기를 변경하고자 한다면 다시 만들어야한다.
		System.out.println(arr.hashCode());
		arr = new int[10];
		System.out.println(arr.hashCode());
		//주소값이 변경된다 -> 새로운 공간을 할당한다.
		
		/*
		 * 연결이 끊어진 기존 배열은 Heap영역에 둥둥 떠다닌다.(어떤 변수에도 참조되지 않는 상태, 불러올 수 없는 메모리)
		 * => 일정시간이 지나면 "GC(가비지컬렉터)"가 알아서 회수한다.
		 * => 자바에서의 "자동메모리관리" 특징 => 개발자가 코드에만 집중 할 수 있다.
		 */
		
		arr = null;
		//배열을 강제로 삭제시키고자한다면 null을 대입해주면 된다.
		
		int[] arr2 = {1,1,1,1,1};
		int[] arr3 = {1,1,1,1,1};
		System.out.println(arr2 == arr3);
		//false -> 각 참조변수는 ==를 통해서 비교시 주소값을 비교하기 때문
		
		arr3 = arr2;
		System.out.println(arr2 == arr3);
		
		arr3[0] = 100;
		arr2[1] = 200;
		
		System.out.println(arr2[0] + ", " + arr3[1]);
	}
}
