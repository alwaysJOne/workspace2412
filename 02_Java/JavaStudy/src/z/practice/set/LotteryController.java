package z.practice.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class LotteryController {
	private HashSet lottery = new HashSet();
	private HashSet win = new HashSet();
	
	public boolean insertObject(Lottery l) {
		return lottery.add(l);
	}
	
	public boolean deleteObject(Lottery l) {
		boolean isRemove = lottery.remove(l);
		if(isRemove && win != null) {
			win.remove(l);
		}
		
		return isRemove;
	}
	
	public HashSet winObject() {
		if(lottery.size() < 4) {
			return null;
		}
		
		List<Lottery> list = new ArrayList<>();
		list.addAll(lottery);
		
		while(win.size() < 4) { //사이즈가 4명 이하면 계속진행
			int rand = (int)(Math.random() * list.size());
			win.add(list.get(rand));
		}
		
		return win;
	}
	
	public TreeSet sortedWinObject() {
		//람다식으로 변경해보기
		TreeSet sortedWinSet = new TreeSet(new SortedLottery());
		sortedWinSet.addAll(win);
		
		return sortedWinSet;
	}
	
	public boolean searchWinner(Lottery l) {
		return win.contains(l);
	}
}
