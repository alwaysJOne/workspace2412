package z.practice.object.ex1;

public class Run {

	public static void main(String[] args) {
		Member m1 = new Member("user01", "pass01", "홍길동");
		m1.setInfo("010-1234-5678", "user01@naver.com", 20, '남');
		
		Member m2 = new Member("user02", "pass03", "홍길금");
		m2.setInfo("010-1224-5678", "user02@naver.com", 22, '여');

		Member m3 = new Member("user03", "pass03", "홍길은");
		m3.setInfo("010-1234-7777", "user03@naver.com", 45, '남');
	
		m1.toInfo();
		m2.toInfo();
		m3.toInfo();
	}

}
