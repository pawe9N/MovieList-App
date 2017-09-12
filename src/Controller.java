import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	
	LinkedList<MovieVbox> MovieVboxes;
	
	@FXML
	VBox vbox1, vbox2, vbox3, vbox4, vbox5, vbox6, vbox7, vbox8, vbox9;
	
	@FXML
	Text title;
	
	@FXML
	Label description;
	
	@FXML
	AnchorPane cover, anchor1, anchor2, anchor3, anchor4, anchor5, anchor6, anchor7, anchor8, anchor9;
	
	@FXML
	Button button1, button2, button3, button4, button5, button6, button7, button8, button9;

    @Override 
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("View is now loaded!");
        
        MySQL.initialization();
        
        MovieVboxes = new LinkedList<MovieVbox>();
        showMovieList(MovieVboxes);
             
    }
    
    public void showMovieList(LinkedList<MovieVbox> MovieVboxes){
    	
    	VBox [] vbox = {vbox1, vbox2, vbox3, vbox4, vbox5, vbox6, vbox7, vbox8, vbox9};
        AnchorPane [] imgVariables = {anchor1, anchor2, anchor3, anchor4, anchor5, anchor6, anchor7, anchor8, anchor9};
        Button [] titleButtons = {button1, button2, button3, button4, button5, button6, button7, button8, button9};
        
        for(int i=0; i < 9; i++){
        	MovieVboxes.add(new MovieVbox(vbox[i]));
        	String imgName = MySQL.getSthFromMoviesDatabase("imgName", i+1);
        	String titleS = MySQL.getSthFromMoviesDatabase("title", i+1);
        	String descriptionS = MySQL.getSthFromMoviesDatabase("description", i+1);
        	MovieVbox.anchorSetImage(MovieVboxes.get(i), imgVariables[i], imgName);
        	MovieVbox.buttonSetText(MovieVboxes.get(i), titleButtons[i], titleS);
        	MovieVbox.descriptionSetText(MovieVboxes.get(i), description, descriptionS);
        	
        	 MovieVboxes.get(i).getVbox().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                 @Override
                 public void handle(MouseEvent event) {
                 	
                 	VBox vbox = (VBox) event.getSource();
                 	int i = (int)vbox.getId().charAt(4) - 49;
                 	title.setText(MovieVboxes.get(i).getTitle());
                 	title.setFill(Color.WHITE);
                 	description.setText(descriptionS);
                 	MovieVbox.anchorSetImage(MovieVboxes.get(i),cover, MovieVboxes.get(i).getImgName());
                 }
            });
        	 
        }
        
    	title.setText(MovieVboxes.get(0).getTitle());
      	title.setFill(Color.WHITE);
      	description.setText(MovieVboxes.get(0).getDescription());
      	MovieVbox.anchorSetImage(MovieVboxes.get(0),cover, MovieVboxes.get(0).getImgName());
        
    }


}