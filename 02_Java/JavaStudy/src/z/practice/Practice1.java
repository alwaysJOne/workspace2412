package z.practice;

import java.util.Scanner;

public class Practice1 {
	/*
	 	키보드로 1개의 정수형(int) 데이터를 입력 받아, 1~100 사이의 값일 때만 2의 배수인지 아닌지
		를 출력 처리한다.
	*/
	/*
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			
			int num;
			System.out.print("입력 : ");
			num = sc.nextInt();
			
			if(num >= 1 && num <= 100) {
				if(num % 2 == 0) {
					System.out.println("2의 배수입니다.");
				} else {
					System.out.println("2의 배수가 아닙니다.");
				}
				
				System.out.println(num % 2 == 0 ? "2의 배수입니다." : "2의 배수가 아닙니다.");
			}
		}
	*/
	/*
	 	2단에서 5단까지의 구구단의 결과 중 홀수인 것만 출력한다. 단, for문을 이용한다. 
	*/
	/*
		public static void main(String[] args) {
			for(int j=2; j<6; j++) {
				for(int i=1; i < 10; i++) {
					if((j*i) % 2 == 1) {
						System.out.println(j + " * " + i + " = " + (j*i));
					}
				}
			}
		}
	*/
	/*
	  1부터 100까지의 모든 정수들의 합과 평균을 구하는 프로그램을 작성한다. 
	  단, while문이나 do while문을 이용하여 작성한다.
	*/
	/*
		public static void main(String[] args) {
			int sum = 0;
			
			int i=1;
			while(i <= 100) {
				sum+=i;
				i++;
			}
			
			System.out.println("합계 : " + sum);
			System.out.println("평균 : " + (sum/100.0));
		}
	*/
	
	/*
	 키보드로 2개의 정수형 데이터를 입력 받아, 두 수가 모두 1부터 9까지의 수일 때만 두 수의 곱
	 이 한자리 수인지 두자리 수인지를 출력한다.
	*/
	/*
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			
			System.out.print("입력 : ");
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			
			if(num1 >= 1 && num1 <= 9 && num2 >= 1 && num2 <= 9) {
				int result = num1 * num2;
				
				if(result > 10) {
					System.out.println("두자리 수 입니다.");
				} else {
					System.out.println("한자리 수 입니다.");
				}
				
				System.out.println(result > 10 ? "두자리 수 입니다." : "한자리 수 입니다.");
				
			}
		}
	*/
	/*
	 키보드로 1개의 정수형(int) 데이터를 입력 받아 1부터 5까지 일때만 해당 숫자에 해당하는 결과
	를 출력 한다. 단, switch문을 이용한다. 
		1 : ** 입력이 정상적으로 되었습니다! ** 
		2 : ** 조회가 시작되었습니다! ** 
		3 : ** 수정이 정상적으로 되었습니다! ** 
		4 : ** 삭제가 정상적으로 되었습니다! ** 
		5 : ** 정상적으로 종료 되었습니다! ** 
		1~5가 아닌 값이 입력 된 경우 : ** 다시 입력해 주세요! **
	*/
	/*
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int num;
			
			System.out.print("입력 : ");
			num = sc.nextInt();
			
			switch(num) {
			case 1:
				System.out.println("** 입력이 정상적으로 되었습니다! **");
				break;
			case 2:
				System.out.println("** 조회가 시작되었습니다! **");
				break;
			case 3:
				System.out.println("** 수정이 정상적으로 되었습니다! **");
				break;
			case 4:
				System.out.println("** 삭제가 정상적으로 되었습니다! **");
				break;
			case 5:
				System.out.println("** 정상적으로 종료 되었습니다! **");
				break;
			default:
				System.out.println("** 다시 입력해 주세요! **");
			}
		}
	*/
	/*
	 	국어, 영어, 수학 세 과목의 점수를 키보드로 입력 받고 합계와 평균을 계산하고
		합계와 평균을 이용하여 합격 / 불합격 처리하는 기능을 구현하세요.
		(합격 조건 : 세 과목의 점수가 각각 40점 이상이면서 평균이 60점 이상일 경우)
		합격 했을 경우 과목 별 점수와 합계, 평균, “축하합니다, 합격입니다!”를 출력하고
		불합격인 경우에는 “불합격입니다.”를 출력하세요.
		
		ex 1.			ex 2.
		국어점수 : 88		국어점수 : 88
		수학점수 : 50		수학점수 : 50
		영어점수 : 40		영어점수 : 45
		불합격입니다.		국어 : 88
						수학 : 50
						영어 : 45
						합계 : 183
						평균 : 61.0
						축하합니다, 합격입니다!

	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int koScore, mathScore, enScore;
		
		System.out.print("국어점수 : ");
		koScore = sc.nextInt();
		
		System.out.print("수학점수 : ");
		mathScore = sc.nextInt();
		
		System.out.print("영어점수 : ");
		enScore = sc.nextInt();
		
		int sum = koScore + mathScore + enScore;
		float evg = sum / 3.0f;
		
		if(koScore >= 40 && mathScore >= 40 && enScore >= 40 && evg >= 60) {
			System.out.println("국어 : " + koScore);
			System.out.println("수학 : " + mathScore);
			System.out.println("영어 : " + enScore);
			System.out.println("합계 : " + sum);
			System.out.println("평균 : " + evg);
			System.out.println("축하합니다, 합격입니다!");
		} else {
			System.out.println("불합격입니다.");
		}
	}
}
