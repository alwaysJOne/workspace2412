package z.practice.object.ex2;

public class Movie {
	String movieId;
	String title;
	String director;
	double rating;
	int price;
	boolean isPlaying;
	
	public Movie() {
		super();
	}

	public Movie(String title, String director, double rating, int price, boolean isPlaying) {
		super();
		this.title = title;
		this.director = director;
		this.rating = rating;
		this.price = price;
		this.isPlaying = isPlaying;
	}
	
	public void setOff() {
		this.isPlaying = false;
	}
}
