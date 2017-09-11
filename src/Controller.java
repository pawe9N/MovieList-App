
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	
	@FXML
	AnchorPane anchor1, anchor2, anchor3, anchor4, anchor5, anchor6, anchor7, anchor8, anchor9;
	
	@FXML
	Button button1, button2, button3, button4, button5, button6, button7, button8, button9;

    @Override 
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("View is now loaded!");
        
        showMovieList();
        
    }
    
    public void anchorSetImage(AnchorPane anchor, String imgName){
    	anchor.setStyle("-fx-background-image: url('images/" + imgName + ".jpg')");
    }
    
    public void buttonSetText(Button button, String title){
    	button.setText(title);;
    }
    
    public void showMovieList(){
        String [] imgNames = {"gladiator", "thegodfather", "americanbeauty", "goodfellas", "inception", "memento", "oneflewoverthecuckoosnest", "pulpfiction", "theprofesional"};
        String [] titles = {"Gladiator", "Godfather", "American Beauty", "Goodfellas", "Iception", "Memento", "One flaw over the cuckoo's nest", "Pulp Fiction", "The Professional"};
        AnchorPane [] imgVariables = {anchor1, anchor2, anchor3, anchor4, anchor5, anchor6, anchor7, anchor8, anchor9};
        Button [] titleButtons = {button1, button2, button3, button4, button5, button6, button7, button8, button9};
        
        for(int i=0; i < imgVariables.length; i++){
        	anchorSetImage(imgVariables[i], imgNames[i]);
        	buttonSetText(titleButtons[i], titles[i]);
        }
    }

}