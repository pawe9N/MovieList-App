import javafx.scene.image.ImageView;

public class MoviesAndSeriesTables {
	
	private String id;
	private String movieTitle;
	private String serieTitle;
	private String title;
	private String country;
	private ImageView cover;
	private String rating;
	
	public MoviesAndSeriesTables(String id, String movieTitle, String serieTitle){
		this.id = id;
		this.movieTitle = movieTitle;
		this.serieTitle = serieTitle;
	}
	
	public MoviesAndSeriesTables(String id, ImageView movieCover, String title, String country, String rating){
		this.id = id;
		this.cover = movieCover;
		this.title = title;
		this.country = country;
		this.rating = rating;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getSerieTitle() {
		return serieTitle;
	}

	public void setSerieTitle(String serieTitle) {
		this.serieTitle = serieTitle;
	}
	
	public ImageView getCover() {
		return cover;
	}

	public void setCover(ImageView movieCover) {
		this.cover = movieCover;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String movieRating) {
		this.rating = movieRating;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
