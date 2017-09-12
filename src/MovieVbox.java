import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MovieVbox {
	
	VBox vbox;
	AnchorPane anchorpane;
	Button button;
	String imgName;
	String title;
	String description;
	Label descriptionLabel;
	
	public MovieVbox(VBox vbox){
		this.vbox = vbox;
	}
	
	public static void anchorSetImage(MovieVbox movievbox, AnchorPane anchor, String imgName){
    	movievbox.setAnchorpane(anchor);
    	movievbox.setImgName(imgName);
		anchor.setStyle("-fx-background-image: url('images/" + imgName + ".jpg')");
    }
    
    public static void buttonSetText(MovieVbox movievbox, Button button, String title){
    	movievbox.setButton(button);
    	movievbox.setTitle(title);
    	button.setText(title);;
    }
    
    public static void descriptionSetText(MovieVbox movievbox, Label label, String description){
    	movievbox.setDescriptionLabel(label);
    	movievbox.setDescription(description);
    }
    
	public VBox getVbox() {
		return vbox;
	}

	public void setVbox(VBox vbox) {
		this.vbox = vbox;
	}

	public AnchorPane getAnchorpane() {
		return anchorpane;
	}

	public void setAnchorpane(AnchorPane anchorpane) {
		this.anchorpane = anchorpane;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
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
	
}
