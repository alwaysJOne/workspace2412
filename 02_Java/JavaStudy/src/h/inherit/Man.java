package h.inherit;

public class Man {
	private String name;
	
	public Man(String name) {
		super();
		this.name = name;
		System.out.println("Man : " + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void tellYourName() {
		System.out.println("my name is " + this.name);
	}
}
