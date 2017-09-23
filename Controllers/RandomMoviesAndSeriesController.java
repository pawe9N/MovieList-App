
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class RandomMoviesAndSeriesController implements Initializable {	
	
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
	LinkedList<SeriesVbox> SeriesVboxes;
	int index;
	
	private IntegerProperty indexC = new SimpleIntegerProperty(-1);

	public void setIndex(int index){
	    this.indexC.set(index);
	    if(index==1){
	    	showMoviesList();
	    }else if(index==2){
	    	showSeriesList();
	    }
	}

    @Override 
    public void initialize(URL location, ResourceBundle resources) {
       
        MovieVboxes = new LinkedList<MovieVbox>();
        SeriesVboxes = new LinkedList<SeriesVbox>();
        
        showMoviesList();   
    }
    
    public void showMoviesList(){
        VBox [] vbox = {vbox1, vbox2, vbox3, vbox4, vbox5, vbox6, vbox7, vbox8, vbox9};
        AnchorPane [] imgVariables = {anchor1, anchor2, anchor3, anchor4, anchor5, anchor6, anchor7, anchor8, anchor9};
        Button [] titleButtons = {button1, button2, button3, button4, button5, button6, button7, button8, button9};
    	
    	String movies = "movies";
    	indexes = getRandomIndex(movies);
    	
        for(int i=0; i < 9; i++){
        	
        	index = indexes.get(i);
        	
        	MovieVboxes.add(new MovieVbox(vbox[i]));
        	String imgName = MySQL.getStringFromTable(movies,"imgName", index);
        	String titleS = MySQL.getStringFromTable(movies,"title", index);
        	String descriptionS = MySQL.getStringFromTable(movies,"description", index);
        	String premiere = MySQL.getStringFromTable(movies,"premiere", index);
        	String country = MySQL.getStringFromTable(movies,"country", index);
        	int rating = MySQL.getIntFromTable(movies, "rating", index);
        	int runtime = MySQL.getIntFromTable(movies,"runtime", index);
        	
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
    
    public void showMoviesListClick(MouseEvent event){
    	showMoviesList();
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
        List<Integer> myRandomIndexes = new ArrayList<Integer>();
        List<Integer> myIndexes = new ArrayList<Integer>();
        myIndexes.addAll(getAllIndexes(table));
        int element;
        while(myRandomIndexes.size() != 9){
        	element = generator.nextInt(amount);
        	if(amount > 9){
	        	if(!myRandomIndexes.contains(myIndexes.get(element)))
	        		myRandomIndexes.add(myIndexes.get(element));
        	}else{
        			myRandomIndexes.add(myIndexes.get(element));
        	}
         }
        return myRandomIndexes;
    }
    
    public static List<Integer> getAllIndexes(String table){
        List<Integer> myIndexes = new ArrayList<Integer>();
        try {
			Statement sqlState = MySQL.conn.createStatement();
			String query = "select id from "+table;
			ResultSet rows = sqlState.executeQuery(query);
			while(rows.next()){
				myIndexes.add(rows.getInt("id"));
			 }
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
        return myIndexes;
    }
    
    public void showSeriesList(){
    	VBox [] vbox = {vbox1, vbox2, vbox3, vbox4, vbox5, vbox6, vbox7, vbox8, vbox9};
        AnchorPane [] imgVariables = {anchor1, anchor2, anchor3, anchor4, anchor5, anchor6, anchor7, anchor8, anchor9};
        Button [] titleButtons = {button1, button2, button3, button4, button5, button6, button7, button8, button9};
    	
    	String series = "series";
    	indexes = getRandomIndex(series);
    	
        for(int i=0; i < 9; i++){
        	
        	index = indexes.get(i);
        	
        	SeriesVboxes.add(new SeriesVbox(vbox[i]));
        	String imgName = MySQL.getStringFromTable(series,"imgName", index);
        	String titleS = MySQL.getStringFromTable(series,"title", index);
        	String descriptionS = MySQL.getStringFromTable(series,"description", index);
        	String country = MySQL.getStringFromTable(series,"country", index);
        	int rating = MySQL.getIntFromTable(series, "rating", index);
        	int episodes = MySQL.getIntFromTable(series,"episodes", index);
        	int seasons = MySQL.getIntFromTable(series,"seasons", index);
        	
        	SeriesVbox.anchorSetImage(SeriesVboxes.get(i), imgVariables[i], imgName);
        	SeriesVbox.buttonSetText(SeriesVboxes.get(i), titleButtons[i], titleS);
        	SeriesVbox.descriptionSetText(SeriesVboxes.get(i), description, descriptionS);
        	SeriesVbox.overiewSeriesSetText(SeriesVboxes.get(i), countryText, country, ratingText, rating, premiereOrEpisodesText, episodes, runtimeOrSeasonsText, seasons);
        	
        	SeriesVboxes.get(i).getVboxMovie().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                 @Override
                 public void handle(MouseEvent event) {
                 	
                 	VBox vbox = (VBox) event.getSource();
                 	int i = (int)vbox.getId().charAt(4) - 49;
                 	title.setText(titleS);
                 	title.setFill(Color.WHITE);
                 	SeriesVbox.overiewSeriesSetText(SeriesVboxes.get(i), countryText, country, ratingText, rating, premiereOrEpisodesText, episodes, runtimeOrSeasonsText, seasons);
                 	description.setText(descriptionS);
                 	SeriesVbox.anchorSetImage(SeriesVboxes.get(i), cover, imgName);
                 }
            });
        	 
        }
        descriptionInSeriesBeginning(SeriesVboxes);
    }
    
    public void showSeriesListClick(MouseEvent event){
    	showSeriesList();
    }
    
    public void descriptionInSeriesBeginning(LinkedList<SeriesVbox> SeriesVboxes){
    	title.setText(SeriesVboxes.get(0).getTitle());
      	title.setFill(Color.WHITE);
      	description.setText(SeriesVboxes.get(0).getDescription());
      	SeriesVbox.overiewSeriesSetText(SeriesVboxes.get(0), countryText, SeriesVboxes.get(0).getCountry(), ratingText, SeriesVboxes.get(0).getRating(), premiereOrEpisodesText, SeriesVboxes.get(0).getEpisodes(), runtimeOrSeasonsText, SeriesVboxes.get(0).getSeasons());
      	SeriesVbox.anchorSetImage(SeriesVboxes.get(0), cover, SeriesVboxes.get(0).getImgName());
    }
    
    public void showYourProfile(MouseEvent event) throws IOException{
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("YourProfile.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	home_page_scene.getStylesheets().add(getClass().getResource("YourProfile.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showAllMoviesList(ActionEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TopLists.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
        TopListsController controller=fxmlLoader.<TopListsController>getController();
        controller.setIndex(1);
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)vbox1).getScene().getWindow();
    	home_page_scene.getStylesheets().add(getClass().getResource("TopLists.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showAllSeriesList(ActionEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TopLists.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
        TopListsController controller=fxmlLoader.<TopListsController>getController();
        controller.setIndex(2);
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)vbox1).getScene().getWindow();
    	home_page_scene.getStylesheets().add(getClass().getResource("TopLists.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showWatched(MouseEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WatchedWatchingToWatch.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
        WatchedWatchingToWatchController controller=fxmlLoader.<WatchedWatchingToWatchController>getController();
        controller.setIndex(1);
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)event.getSource()).getScene().getWindow();
    	home_page_scene.getStylesheets().add(getClass().getResource("WatchedWatchingToWatch.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showWatching(MouseEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WatchedWatchingToWatch.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
        WatchedWatchingToWatchController controller=fxmlLoader.<WatchedWatchingToWatchController>getController();
        controller.setIndex(2);
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)event.getSource()).getScene().getWindow();
    	home_page_scene.getStylesheets().add(getClass().getResource("WatchedWatchingToWatch.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showToWatch(MouseEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WatchedWatchingToWatch.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
        WatchedWatchingToWatchController controller=fxmlLoader.<WatchedWatchingToWatchController>getController();
        controller.setIndex(3);
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)event.getSource()).getScene().getWindow();
    	home_page_scene.getStylesheets().add(getClass().getResource("WatchedWatchingToWatch.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showSearch(MouseEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Search.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)event.getSource()).getScene().getWindow();
    	home_page_scene.getStylesheets().add(getClass().getResource("Search.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showAddMovieNew(ActionEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddMovie.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
        AddController controller=fxmlLoader.<AddController>getController();
        controller.setIndex(1);
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)vbox1).getScene().getWindow();
    	home_page_scene.getStylesheets().add(getClass().getResource("Add.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showAddSeriesNew(ActionEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddSeries.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
        AddController controller=fxmlLoader.<AddController>getController();
        controller.setIndex(2);
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)vbox1).getScene().getWindow();
    	home_page_scene.getStylesheets().add(getClass().getResource("Add.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showUpdate(ActionEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Update.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)vbox1).getScene().getWindow();
    	home_page_scene.getStylesheets().add(getClass().getResource("Update.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showDelete(ActionEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Delete.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)vbox1).getScene().getWindow();
    	home_page_scene.getStylesheets().add(getClass().getResource("Delete.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }

}