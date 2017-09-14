
public class ProfileTable {
	
	private String id;
	private String movieTitle;
	private String serieTitle;
	
	public ProfileTable(String id, String movieTitle, String serieTitle){
		this.id = id;
		this.movieTitle = movieTitle;
		this.serieTitle = serieTitle;
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
}
