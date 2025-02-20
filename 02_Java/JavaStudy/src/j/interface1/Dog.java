package j.interface1;

public class Dog implements Animal{

	@Override
	public void move() {
		System.out.println("껑충껑충 뛰어다닌다.");
	}

	@Override
	public void eat() {
		System.out.println("왁왁왁먹는다.");
	}

	@Override
	public void makeSound() {
		System.out.println("멍멍~ 왈왈");
	}
	
}
