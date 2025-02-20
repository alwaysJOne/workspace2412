package e.array;

import java.util.Scanner;

public class D_Array_Copy {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		
		int[] origin = {1,2,3,4,5};
		
		System.out.println("==원본배열 출력==");
		for(int value : origin) {
			System.out.print(value + " ");
		}
		
		System.out.println(); //개행
		
		//단순하게 origin을 다시 대입시켜 copy배열 생성
		int[] copy = origin;
		System.out.println("==복사본배열 출력==");
		for(int value : copy) {
			System.out.print(value + " ");
		}
		
		copy[2] = 99;
		System.out.println("\n복사본 값 변경");
		
		System.out.println("==원본배열 출력==");
		for(int value : origin) {
			System.out.print(value + " ");
		}
		
		*/
		
		//copy의 값만을 수정해도 원본의 값이 함께 변경된다.
		//왜? origin과 copy가 같은 메모리를 참조하고 있기 때문(참조변수의 주소값이 동일)
		//얕은복사 : 주소값 복사
		
		//배열 복사 방법
		/*
		 * 1. for문을 활용한 방법
		 * 새로운 배열을 만들어서 반복문을 통해 원본 배열의 값들을 새로운 배열에 대입
		 */
		/*
			int[] origin = {1,2,3,4,5};
			int[] copy = new int[origin.length];
			
			for(int i=0; i<origin.length; i++) {
				copy[i] = origin[i];
			}
			
			copy[2] = 99;
			
			System.out.println("==원본배열 출력==");
			for(int value : origin) {
				System.out.print(value + " ");
			}
			
			System.out.println();
			
			System.out.println("==복사본배열 출력==");
			for(int value : copy) {
				System.out.print(value + " ");
			}
		*/
		
		//2. clone함수를 이용한 방법
		int[] origin = {1,2,3,4,5};
		int[] copy = origin.clone();
	}
}
