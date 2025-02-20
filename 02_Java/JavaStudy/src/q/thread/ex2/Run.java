package q.thread.ex2;

public class Run {
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> { //20이하 짝수 출력
			try {
				for(int i=1; i<=100; i++) {
					if(i % 2 == 0) {
						System.out.print(i + " ");
					} Thread.sleep(400);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	
		Thread t2 = new Thread(() -> {  //20이하 홀수 출력
			try {
				for(int i=1; i<=100; i++) {
					if(i % 2 == 1) {
						System.out.print(i + " ");
					} Thread.sleep(400);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
	}
}
