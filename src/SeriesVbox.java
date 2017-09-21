import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SeriesVbox extends MovieVbox {
	
	private Text episodesText;
	private Text seasonsText;
	private int episodes;
	private int seasons;
	
	public SeriesVbox(VBox vboxSerie){
		super(vboxSerie);
	}
	
	public static void overiewSeriesSetText(SeriesVbox seriesvbox,
								Text countryText, String country,
								Text ratingText, int rating, 
								Text episodesText, int episodes,
								Text seasonsText, int seasons){
		
		seriesvbox.setCountryText(countryText);
		seriesvbox.setCountry(country);
    	countryText.setText(country);
    	seriesvbox.setRatingText(ratingText);
    	seriesvbox.setRating(rating);
    	ratingText.setText(Integer.toString(rating)+"/10");
    	seriesvbox.setEpisodesText(episodesText);
    	seriesvbox.setEpisodes(episodes);
    	episodesText.setText(Integer.toString(episodes)+" episodes");
    	seriesvbox.setSeasonsText(seasonsText);
    	seriesvbox.setSeasons(seasons);
    	if(seasons > 1)
    		seasonsText.setText(Integer.toString(seasons)+" seasons");
    	else
    		seasonsText.setText(Integer.toString(seasons)+" season");
	}
	
	public Text getEpisodesText() {
		return episodesText;
	}

	public void setEpisodesText(Text episodesText) {
		this.episodesText = episodesText;
	}

	public Text getSeasonsText() {
		return seasonsText;
	}

	public void setSeasonsText(Text seasonsText) {
		this.seasonsText = seasonsText;
	}

	public int getEpisodes() {
		return episodes;
	}

	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}

	public int getSeasons() {
		return seasons;
	}

	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}
	
	

}
