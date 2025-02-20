package d.loop;

public class E_continue {
	/*
	 * continue : 반복문 안에 기술되는 구문
	 * 			  continue;코드를 실행시 그 뒤에 코드를 실행하지않고 곧바로 다시 반복문의 상단으로 이동
	 */
	public static void main(String[] args) {
		//1분터 10까지 홀수출력
		
		for(int i=1; i<=10; i++) {
			if(i % 2 == 1) {
				System.out.print(i + " ");
			}
		}
	}
}
