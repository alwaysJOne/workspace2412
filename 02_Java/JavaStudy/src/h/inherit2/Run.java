package h.inherit2;

public class Run {

	public static void main(String[] args) {
//		Desktop d1 = new Desktop();
		Desktop d1 = new Desktop("LG", "d-100", "사무용PC", 1000000, true);
		System.out.println(d1.information());
		
		TV tv1 = new TV("샘송", "t-102", "LED TV", 3000000, 75);
		System.out.println(tv1.information());
	}

}
