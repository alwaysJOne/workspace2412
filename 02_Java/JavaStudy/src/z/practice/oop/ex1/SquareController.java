package z.practice.oop.ex1;

public class SquareController {
	private Shape s = new Shape();
	
	public double calcPerimeter(double height, double width) {
		this.s = new Shape(2, height, width);
		return (s.getWidth() * 2) + (s.getHeight() * 2);
	}
	
	public double calcArea(double height, double width) {
		this.s = new Shape(2, height, width);
		return s.getWidth() * s.getHeight();
	}
	
	public void paintColor(String color) {
		this.s.setColor(color);
	}
	
	public String print() {
		return "사각형 " + this.s.information();
	}
}
