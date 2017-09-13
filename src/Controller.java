import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {	
	
	@FXML
	VBox vbox1, vbox2, vbox3, vbox4, vbox5, vbox6, vbox7, vbox8, vbox9;
	@FXML
	Text title, ratingText, countryText, premiereOrEpisodesText,  runtimeOrSeasonsText;
	@FXML
	Label description;
	@FXML
	AnchorPane cover, anchor1, anchor2, anchor3, anchor4, anchor5, anchor6, anchor7, anchor8, anchor9;
	@FXML
	Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
	
	List<Integer> indexes;
	LinkedList<MovieVbox> MovieVboxes;
	int index;

    @Override 
    public void initialize(URL location, ResourceBundle resources) {
    	
        System.out.println("View is now loaded!");
        
        MySQL.initialization();
        
        MovieVboxes = new LinkedList<MovieVbox>();
        VBox [] vbox = {vbox1, vbox2, vbox3, vbox4, vbox5, vbox6, vbox7, vbox8, vbox9};
        AnchorPane [] imgVariables = {anchor1, anchor2, anchor3, anchor4, anchor5, anchor6, anchor7, anchor8, anchor9};
        Button [] titleButtons = {button1, button2, button3, button4, button5, button6, button7, button8, button9};
        
        
        
        showMovieList(MovieVboxes,vbox,imgVariables,titleButtons);   
    }
    
    public void showMovieList(LinkedList<MovieVbox> MovieVboxes, VBox [] vbox, AnchorPane [] imgVariables, Button [] titleButtons){
    	String movies = "movies";
    	indexes = getRandomIndex(movies);
    	
        for(int i=0; i < 9; i++){
        	
        	index = indexes.get(i);
        	
        	MovieVboxes.add(new MovieVbox(vbox[i]));
        	String imgName = MySQL.getStringFromTable(movies,"imgName", index+1);
        	String titleS = MySQL.getStringFromTable(movies,"title", index+1);
        	String descriptionS = MySQL.getStringFromTable(movies,"description", index+1);
        	String premiere = MySQL.getStringFromTable(movies,"premiere", index+1);
        	String country = MySQL.getStringFromTable(movies,"country", index+1);
        	int rating = MySQL.getIntFromTable(movies, "rating", index+1);
        	int runtime = MySQL.getIntFromTable(movies,"runtime", index+1);
        	
        	MovieVbox.anchorSetImage(MovieVboxes.get(i), imgVariables[i], imgName);
        	MovieVbox.buttonSetText(MovieVboxes.get(i), titleButtons[i], titleS);
        	MovieVbox.descriptionSetText(MovieVboxes.get(i), description, descriptionS);
        	MovieVbox.overiewMoviesSetText(MovieVboxes.get(i), premiereOrEpisodesText, premiere, countryText, country, ratingText, rating, runtimeOrSeasonsText, runtime);
        	
        	 MovieVboxes.get(i).getVboxMovie().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                 @Override
                 public void handle(MouseEvent event) {
                 	
                 	VBox vbox = (VBox) event.getSource();
                 	int i = (int)vbox.getId().charAt(4) - 49;
                 	title.setText(titleS);
                 	title.setFill(Color.WHITE);
                 	MovieVbox.overiewMoviesSetText(MovieVboxes.get(i), premiereOrEpisodesText, premiere, countryText, country, ratingText, rating, runtimeOrSeasonsText, runtime);
                 	description.setText(descriptionS);
                 	MovieVbox.anchorSetImage(MovieVboxes.get(i), cover, imgName);
                 }
            });
        	 
        }
        descriptionInBeginning(MovieVboxes);
    }
    
    public void descriptionInBeginning(LinkedList<MovieVbox> MovieVboxes){
    	title.setText(MovieVboxes.get(0).getTitle());
      	title.setFill(Color.WHITE);
      	description.setText(MovieVboxes.get(0).getDescription());
      	MovieVbox.overiewMoviesSetText(MovieVboxes.get(0), premiereOrEpisodesText, MovieVboxes.get(0).getPremiere(), countryText, MovieVboxes.get(0).getCountry(), ratingText, MovieVboxes.get(0).getRating(), runtimeOrSeasonsText, MovieVboxes.get(0).getRuntime());
      	MovieVbox.anchorSetImage(MovieVboxes.get(0),cover, MovieVboxes.get(0).getImgName());
    }
    
    public static List<Integer> getRandomIndex(String table){
    	int amount = MySQL.getAmountOfRows(table);
        Random generator = new Random();
        List<Integer> myList = new ArrayList<Integer>();
        int element;
        while(myList.size() != 9){
        	element = generator.nextInt(amount);
        	if(!myList.contains(element))
        		myList.add(element);
         }
        return myList;
    }
    
    public void showYourProfile(MouseEvent event) throws IOException{
    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("YourProfile.fxml"));
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    
    


}