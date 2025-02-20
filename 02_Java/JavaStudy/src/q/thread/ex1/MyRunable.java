package q.thread.ex1;

//쓰레드를 만드는 방법2 Runnable 추상메서드 run을 구현하는 객체를 만든다.
public class MyRunable implements Runnable{

	@Override
	public void run() {
		int n1 = 20;
		int n2 = 30;
		
		//Thread.currentThread() 지금 실행하고 있는 쓰레드의 정보객체를 반환
		String name = Thread.currentThread().getName();
		System.out.println(name + " : " + (n1 + n2));
	}

}
