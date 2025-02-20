package n.generic;

public class Box2 {
	private Object o;

	public Box2(Object o) {
		super();
		this.o = o;
	}

	public Object getO() {
		return o;
	}

	public void setO(Object o) {
		this.o = o;
	}

	@Override
	public String toString() {
		return "Box2 [o=" + o + "]";
	}
}
