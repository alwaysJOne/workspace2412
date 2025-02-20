package e.array;

import java.util.Scanner;

public class E_Array_Two {
	/*
	 * 2차원 배열
	 * 자료형이 같은 1차원배열의 묶음으로 배열안에 다른배열이 존재한다.
	 * 2차원 배열은 할당된 공간마다 인덱스번호를 두개 부여(앞번호는 몇번째 1차원배열인지(행), 뒷번호는 1차원배열의 몇번째 index인지(열)) 
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * 1, 2차원 배열 선언(1차원 배열의 묶음을 참조하는 2차원 배열 참조변수 만들기)
		 * 자료형[][] 배열명;
		 * int[][] arr;
		 */
		
		/*
		 * 2. 배열할당(실제 m크기의 1차원배열 n개를 만들어서 참조)
		 * 배열명 = new 자료형[n][m];
		 * 
		 * 1차언배열의 참조변수들을 먼저 생성할 수 있다.
		 * 배열명 = new 자료형[n][];
		 * arr[0] = new 자료형[m];
		 * arr[1] = new 자료형[m];
		 * arr[2] = new 자료형[m];
		 */
		
		//배열의 선언과 동시에 할당
		int[][] arr = new int[3][2];
		
		arr[0][0] = 3;
		arr[0][1] = 2;
		arr[1][0] = 7;
		arr[1][1] = 4;
		arr[2][0] = 6;
		arr[2][1] = 1;
		
		//전체 출력
		//for문을 중첩하면 편리하게 전체를 출력할 수 있음
		for(int i = 0; i < arr[0].length; i++) {
			System.out.print(arr[0][i]);
		}
		
		for(int i = 0; i < arr[1].length; i++) {
			System.out.print(arr[1][i]);
		}
		
		for(int i = 0; i < arr[2].length; i++) {
			System.out.print(arr[2][i]);
		}
		
		for(int j=0; j < arr.length; j++) {
			for(int i = 0; i < arr[j].length; i++) {
				System.out.print(arr[j][i]);
			}
		}
	}
}
