package j.interface1;

public class Rabbit implements Animal, Baby{

	@Override
	public void move() {
		System.out.println("깡총깡총");
	}

	@Override
	public void eat() {
		System.out.println("뇸뇸 먹습니다");
	}

	@Override
	public void makeSound() {
		System.out.println("꿀꿀 부르릉");
	}
	
}
