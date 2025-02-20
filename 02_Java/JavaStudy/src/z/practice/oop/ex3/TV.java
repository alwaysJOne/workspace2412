package z.practice.oop.ex3;

public class TV {
	private String brand;
	private int year;
	private int inch;
	private int price;
	
	public TV() {
		super();
	}

	public TV(String brand, int year, int inch, int price) {
		super();
		this.brand = brand;
		this.year = year;
		this.inch = inch;
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getInch() {
		return inch;
	}

	public void setInch(int inch) {
		this.inch = inch;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public void show() {
		System.out.printf("%s에서 만든 %d년형 %d인치 TV 가격 : %d\n", brand, year, inch, price);
	}
}
