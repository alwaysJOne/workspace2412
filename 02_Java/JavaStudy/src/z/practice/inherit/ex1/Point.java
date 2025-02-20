package z.practice.inherit.ex1;

public class Point{
	private int x;
	private int y;
	
	public Point() {
		super();
	}

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	//toString -> 클래스의 모든 필드정보를 문자열로 반환하는 메서드
	//Object클래스에 정의가 되어있는 메서드로 참조변수를 출력시 자동으로
	//호출되는 메서드
	@Override //어노테이션
	public String toString() {
		return x + ", " + y;
	}
}
