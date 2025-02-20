package z.practice.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MusicController {
	private List list = new ArrayList();
	
	public MusicController() {
		super();
		list.add(new Music("소우주", "방탄소년단"));
		list.add(new Music("작은 것들을 위한 시", "방탄소년단"));
		list.add(new Music("에피소드", "이무진"));
	}

	public int addList(Music music) {
		list.add(music);
		return 1;
	}
	
	public int addAtZero(Music music) {
		list.add(0, music);
		return 1;
	}
	
	public List printAll() {
		return list;
	}
	
	public Music searchMusic(String title) {
		for(Object obj : list) {
			Music m = (Music)obj;
			if(m.getTitle().equals(title)) {
				return m;
			}
		}
		return null;
	}
	
	public Music removeMusic(String title) {
		Music m = this.searchMusic(title);
		if(m != null) {
			list.remove(m);
		}
		return m;
	}
	
	public Music setMusic(String title, Music music) {
		Music m = this.searchMusic(title);
		if(m != null) {
			int index = list.indexOf(m);
			list.set(index, music);
		}
		return m;
	}
	
	public int ascTitle() {
		// 리스트 곡 명 오름차순 정렬, 제목이 같으면 가수 명으로 오름차순 정렬, 1 리턴 
		//Collections.sort : 컬렉션에서 정렬기능을 제공하는 메서드
		//정렬하고싶은 컬렉션객체와 정렬기준을 정한 객체(Comparator구현한 객체)를
		//전달하면 정렬기준에 맞춰 정렬을 수행해준다.
		Collections.sort(list, new AscTitle());
		return 1;
	}
	
	public int descSinger() {
		// 리스트 가수 명 내림차순 정렬, 1 리턴
//		Collections.sort(list, new Comparator<Music>() {
//			public int compare(Music o1, Music o2) {
//				return o2.getSinger().compareTo(o1.getSinger());
//			}
//		});
		
		/*
		 * 함수형 인터페이스
		 * - 단 하나의 추상메서드를 가진 인터페이스
		 * - 람다식을 통해서 단일추상메서드를 바로 구현해서 사용할 수 있다.
		 */
		
		//람다식의 기본구조
		// (매개변수) -> {실행할 코드}
		
		//반환값이 1개일 때는 {} 생략가능
		// (매개변수) -> 반환값
		
		Comparator com = (o1, o2) -> ((Music)o2).getSinger().compareTo(((Music)o1).getSinger());
		
		Collections.sort(list, com);
		return 1;
	}
}
