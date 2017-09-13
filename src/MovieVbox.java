import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MovieVbox {
	
	private VBox vboxMovie;
	private AnchorPane anchorImage;
	private Label descriptionLabel;
	private Button buttonTitle;
	private Text premiereText;
	private Text countryText;
	private Text ratingText;
	private Text runtimeText;
	private String imgName;
	private String title;
	private String description;
	private String premiere;
	private String country;
	private int rating;
	private int runtime;
	
	
	public MovieVbox(VBox vbox){
		this.vboxMovie = vbox;
	}
	
	public static void anchorSetImage(MovieVbox movievbox, AnchorPane anchor, String imgName){
    	movievbox.setAnchorImage(anchor);
    	movievbox.setImgName(imgName);
		anchor.setStyle("-fx-background-image: url('"+ imgName + ".jpg')");
    }
    
    public static void buttonSetText(MovieVbox movievbox, Button button, String title){
    	movievbox.setButtonTitle(button);
    	movievbox.setTitle(title);
    	button.setText(title);;
    }
    
    public static void descriptionSetText(MovieVbox movievbox, Label label, String description){
    	movievbox.setDescriptionLabel(label);
    	movievbox.setDescription(description);
    }
    
    public static void overiewMoviesSetText(MovieVbox movievbox, Text premiereText, String premiere, Text countryText, String country, Text ratingText, int rating, Text runtimeText, int runtime){
    	movievbox.setPremiereText(premiereText);
    	movievbox.setPremiere(premiere);
    	premiereText.setText(premiere);
    	movievbox.setCountryText(countryText);
    	movievbox.setCountry(country);
    	countryText.setText(country);
    	movievbox.setRatingText(ratingText);
    	movievbox.setRating(rating);
    	ratingText.setText(Integer.toString(rating)+"/10");
    	movievbox.setRuntimeText(runtimeText);
    	movievbox.setRuntime(runtime);
    	runtimeText.setText(Integer.toString(runtime)+" min");
    	
    }
    
	public VBox getVboxMovie() {
		return vboxMovie;
	}

	public void setVboxMovie(VBox vbox) {
		this.vboxMovie = vbox;
	}

	public AnchorPane getAnchorImage() {
		return anchorImage;
	}

	public void setAnchorImage(AnchorPane anchorpane) {
		this.anchorImage = anchorpane;
	}

	public Button getButtonTitle() {
		return buttonTitle;
	}

	public void setButtonTitle(Button button) {
		this.buttonTitle = button;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Label getDescriptionLabel() {
		return descriptionLabel;
	}

	public void setDescriptionLabel(Label descriptionLabel) {
		this.descriptionLabel = descriptionLabel;
	}
	
	public int  getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getPremiere() {
		return premiere;
	}

	public void setPremiere(String premiere) {
		this.premiere = premiere;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	
	public Text getPremiereText() {
		return premiereText;
	}

	public void setPremiereText(Text premiereText) {
		this.premiereText = premiereText;
	}

	public Text getCountryText() {
		return countryText;
	}

	public void setCountryText(Text countryText) {
		this.countryText = countryText;
	}

	public Text getRatingText() {
		return ratingText;
	}

	public void setRatingText(Text ratingText) {
		this.ratingText = ratingText;
	}

	public Text getRuntimeText() {
		return runtimeText;
	}

	public void setRuntimeText(Text runtimeText) {
		this.runtimeText = runtimeText;
	}
	
}
