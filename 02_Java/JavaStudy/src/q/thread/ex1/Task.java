package q.thread.ex1;

//쓰레드를 만드는 방법 1
//Thread클래스를 상속받는 클래스를 작성한다.
public class Task extends Thread{

	//메인문처럼 해당쓰레드 실행시 독립적으로 실행되는 메서드
	@Override
	public void run() {
		int n1 = 20;
		int n2 = 30;
		
		//Thread.currentThread() 지금 실행하고 있는 쓰레드의 정보객체를 반환
		String name = Thread.currentThread().getName();
		System.out.println(name + " : " + (n1 + n2));
		super.run();
	}
	
}
