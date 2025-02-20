package j.interface1;

public class Cat implements Animal{

	@Override
	public void move() {
		System.out.println("사뿐사뿐 움직인다.");
	}

	@Override
	public void eat() {
		System.out.println("츄릅츄릅 먹는다.");
	}

	@Override
	public void makeSound() {
		System.out.println("야용~ 꺄악!!");
	}

}
