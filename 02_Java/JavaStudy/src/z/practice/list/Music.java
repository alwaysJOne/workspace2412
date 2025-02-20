package z.practice.list;

public class Music {
	private String title;
	private String singer;
	public Music() {
		super();
	}
	public Music(String title, String singer) {
		super();
		this.title = title;
		this.singer = singer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	
	@Override
	public String toString() {
		return title + " - " + singer;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	//클래스에서 equals를 오버라이딩하지 않으면 기본Object의 equals를 따른다(참조비교)
	//실제 해당클래스의 객체비교시 기준을 가지고 오버라이딩해줘야 컬렉션과같은 곳에서
	//적절하게 사용된다.
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Music) {
			Music m = (Music)obj;
			if(m.getTitle().equals(this.title) &&
					m.getSinger().equals(this.singer)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void compareTo() {
		
	}
	
	
}
