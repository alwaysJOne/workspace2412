package z.practice.interface1;

public class Run {

	public static void main(String[] args) {
		PhoneController pc = new PhoneController();
		
		String[] results = pc.method();
		
		for(int i=0; i<results.length; i++) {
			System.out.println(results[i + 1]);
			System.out.println();
		}
	}

}
