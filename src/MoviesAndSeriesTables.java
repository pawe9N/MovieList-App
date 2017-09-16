import javafx.scene.image.ImageView;

public class MoviesAndSeriesTables {
	
	private String id;
	private String movieTitle;
	private String serieTitle;
	private ImageView movieCover;
	private ImageView serieCover;
	private int movieRating;
	private int serieRating;
	
	private ImageView seriesCover;
	
	public MoviesAndSeriesTables(String id, String movieTitle, String serieTitle){
		this.id = id;
		this.movieTitle = movieTitle;
		this.serieTitle = serieTitle;
	}
	
	public MoviesAndSeriesTables(String id, ImageView movieCover, String movieTitle, int movieRating){
		this.id = id;
		this.movieCover = movieCover;
		this.movieTitle = movieTitle;
		this.movieRating = movieRating;
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
	
	public ImageView getMovieCover() {
		return movieCover;
	}

	public void setMovieCover(ImageView movieCover) {
		this.movieCover = movieCover;
	}

	public ImageView getSeriesCover() {
		return seriesCover;
	}

	public void setSeriesCover(ImageView seriesCover) {
		this.seriesCover = seriesCover;
	}
	
	public int getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(int movieRating) {
		this.movieRating = movieRating;
	}

	public int getSerieRating() {
		return serieRating;
	}

	public void setSerieRating(int serieRating) {
		this.serieRating = serieRating;
	}

	public ImageView getSerieCover() {
		return serieCover;
	}

	public void setSerieCover(ImageView serieCover) {
		this.serieCover = serieCover;
	}


}
