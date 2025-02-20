package z.practice.set;

import java.util.Comparator;

public class SortedLottery implements Comparator<Lottery>{

	@Override
	public int compare(Lottery o1, Lottery o2) {
		int num = o1.getName().compareTo(o2.getName());
		if(num == 0) {
			num = o1.getPhone().compareTo(o2.getPhone());
		}
		
		//0보다 작은값 : o1이 o2보다 작다
		//0 : 동일
		//0보다 큰수 : o1이 o2보다 크다.
		//오름차순
		return num;
	}
}
