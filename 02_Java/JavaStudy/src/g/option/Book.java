package g.option;
/*
 * 접근제한자 : 해당구조에 접근할 수 있는 범위를 제한한다.
 * 		public > protected > default > private
 * 
 * 클래스에 사용가능한 접근제한자 2가지
 * default, public
 * 
 * 필드와 메소드에 사용할 수 있는 접근제한자 4가지
 * public : 어디서든(동일패키지, 외부패키지 모두) 접근가능
 * protected : 같은패키지 + 다른패키지인 겨우 상속관계에 있을 때만 접근가능
 * default : 같은 패키지에서만 접근가능
 * private : 오직 해당 클래스에서만 접근가능
 */

public class Book {
	//필드(private) -> 외부에 접근을 막아 데이터를 의도에 맞게 사용할 수 있게 만든다 -> 정보은닉
	private String title; //제목
	private String genre; //장르
	private String author; //저자
	private int maxPage; //페이지 수
	
	public Book() {
		super();
	}

	public Book(String title, String genre, String author, int maxPage) {
		super();
		this.title = title;
		this.genre = genre;
		this.author = author;
		this.maxPage = maxPage;
	}

	//외부에서 접근가능하도록 getter/setter를 작성해준다.
	//모든 데이터에 접근은 메서드를 통해서 한다.
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		if(maxPage < 1) {
			this.maxPage = 1;
			return;
		}
		this.maxPage = maxPage;
	}
}
