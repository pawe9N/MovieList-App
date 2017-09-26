import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.mysql.jdbc.PreparedStatement;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddController implements Initializable {
	
	@FXML private HBox hbox;
	@FXML Text message, messageSeries;
	@FXML TextField title, premiere, country, rating, genre, runtime, titleSeries, genreSeries, episodes, seasons, countrySeries, ratingSeries;
	@FXML TextArea description, descriptionSeries;
	@FXML Button explorerImages, submit, explorerImagesSeries, submitSeries;
	@FXML AnchorPane anchorImage, anchorImageSeries;
	@FXML RadioButton watched, watching, watchedSeries, toWatchSeries;
	
	String titleString, premiereString, countryString, ratingString, genreString, runtimeString, descriptionString, imgName, radioSelection, episodesString, seasonsString;
	boolean submitBool;
	FileChooser fileChooser;
	File file;
	
	private IntegerProperty indexC = new SimpleIntegerProperty(-1);

	public void setIndex(int index){
	    this.indexC.set(index);
	    if(index==1){
	    	showAddMovie();
	    }else if(index==2){
	    	showAddSeries();
	    }
	}

	@Override 
	public void initialize(URL location, ResourceBundle resources) {
	    
	}
	
	public void showAddMovie(){
		submit.setOnAction(e -> {
			
			submitBool = movieInputvalidation();
			
			if(submitBool){
				copyImageToBin(file, anchorImage);
				
				String query = " insert into movies (title, premiere, country, rating, genre, runtime, description, imgName, watched, toWatch)"
				        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				String [] movieData = {titleString, premiereString, countryString, ratingString, genreString, runtimeString, descriptionString, imgName, radioSelection};

				    try{
				    	 PreparedStatement preparedStmt = (PreparedStatement) MySQL.conn.prepareStatement(query);
				    	 for(int i=1; i<=movieData.length; i++){
				    		 if(i==9){
				    			 if(radioSelection == "watched"){
									 preparedStmt.setBoolean(i, true);
									 preparedStmt.setBoolean (i+1, false);
							     }else if(radioSelection == "toWatch"){
							    	 preparedStmt.setBoolean(i, false);
									 preparedStmt.setBoolean (i+1, true);
							     }
				    		 }else{
				    			 preparedStmt.setString (i, movieData[i-1]);
				    		 }
				    	 }
					     
					     preparedStmt.execute();
					     
					     for(int i=0; i<movieData.length; i++){
					    	 movieData[i]="";
					     }
					     
					     clearingInputsMovies();
					     message.setText("Added movie to database!");
					     message.setFill(Color.GREEN);
					     
					}catch (SQLException e1) {
						
						e1.printStackTrace();
						message.setText("Can't send values to database!");
						message.setFill(Color.RED);
					}
				      
				
			}else{
				message.setText("Input correct values!");
				message.setFill(Color.RED);
			}
			
		});
	
	explorerImages.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		
		@Override
         public void handle(MouseEvent event) {
			 FileChooser fileChooser = new FileChooser();
			 FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
	         fileChooser.getExtensionFilters().addAll(extFilterJPG);
	         file = fileChooser.showOpenDialog(null);
			 if(file != null){
				 imgName = file.getName().replaceAll(".jpg", "");
				 String path = file.toPath().toString();
				 anchorImage.setStyle("-fx-background-image:url('file:///"+path.replace("\\", "//")+"')");
			 }
			}
		}); 
	}
	
	public void showAddSeries(){
		submitSeries.setOnAction(e -> {
			
			submitBool = seriesInputValidation();
			
			if(submitBool){
				copyImageToBin(file, anchorImageSeries);
				
				String query = " insert into series (title, imgName, description, episodes, seasons, country, rating, genre, watched, watching, toWatch)"
				        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				String [] seriesData = {titleString, imgName, descriptionString, episodesString, seasonsString, countryString, ratingString, genreString, radioSelection};

				    try{
				    	 PreparedStatement preparedStmt = (PreparedStatement) MySQL.conn.prepareStatement(query);
				    	 for(int i=1; i<=seriesData.length; i++){
				    		 if(i==9){
				    			 if(radioSelection == "watched"){
									 preparedStmt.setBoolean(i, true);
									 preparedStmt.setBoolean (i+1, false);
									 preparedStmt.setBoolean (i+2, false);
							     }else if(radioSelection=="watching"){
							    	 preparedStmt.setBoolean(i, false);
									 preparedStmt.setBoolean (i+1, true);
									 preparedStmt.setBoolean (i+2, false);
							     }
				    			 else if(radioSelection == "toWatch"){
				    				 preparedStmt.setBoolean(i, false);
									 preparedStmt.setBoolean (i+1, true);
									 preparedStmt.setBoolean (i+2, true);
							     }
				    		 }else{
				    			 preparedStmt.setString (i, seriesData[i-1]);
				    		 }
				    	 }
					     
					     preparedStmt.execute();
					     
					     for(int i=0; i<seriesData.length; i++){
					    	 seriesData[i]="";
					     }
					     
					     clearingInputsSeries();
					     messageSeries.setText("Added series to database!");
					     messageSeries.setFill(Color.GREEN);
					     
					}catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						messageSeries.setText("Can't send values to database!");
						messageSeries.setFill(Color.RED);
					}
				      
				
			}else{
				messageSeries.setText("Input correct values!");
				messageSeries.setFill(Color.RED);
			}
			
		});
	
	explorerImagesSeries.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		
		@Override
         public void handle(MouseEvent event) {
			 FileChooser fileChooser = new FileChooser();
			 FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
	         fileChooser.getExtensionFilters().addAll(extFilterJPG);
	         file = fileChooser.showOpenDialog(null);
			 if(file != null){
				 imgName = file.getName().replaceAll(".jpg", "");
				 String path = file.toPath().toString();
				 anchorImageSeries.setStyle("-fx-background-image:url('file:///"+path.replace("\\", "//")+"')");
			 }
			}
		}); 
	}
	
	public void copyImageToBin(File file, AnchorPane anchorImages){
		Path from = file.toPath();
	 	String toPath = getClass().getClassLoader().getResource(".").getPath();
	 	toPath = toPath.replaceFirst("^/(.:/)", "$1");
	 	Path to = Paths.get(toPath+file.getName());
        try {
			Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		anchorImages.setStyle("-fx-background-image:url('"+file.getName()+"')");
	}
	
	public boolean movieInputvalidation(){
		boolean submitBool = true;
		
		titleString = title.getText();
		countryString = country.getText();
		genreString = genre.getText();
		descriptionString = description.getText();
		if(watched.isSelected()){
			radioSelection = "watched";
		}else{
			radioSelection = "toWatch";
		}
		premiereString= premiere.getText();
		if (!premiereString.matches("\\d*")) {
			premiereString = premiereString.replaceAll("[^\\d]", "");
        }
		ratingString= rating.getText();
		if (!ratingString.matches("\\d*")) {
			ratingString = ratingString.replaceAll("[^\\d]", "");
        }
		runtimeString = runtime.getText();
		if (!runtimeString.matches("\\d*")) {
			runtimeString = runtimeString.replaceAll("[^\\d]", "");
        }
		String [] movieData = {titleString, premiereString, countryString, ratingString, genreString, runtimeString, descriptionString, imgName, radioSelection};
		for(String data : movieData){
			if(data == "" || data==null){
				submitBool = false;
				return submitBool;
			}else{
				submitBool = true;
			}
		}
		return true;
	}
	
	public boolean seriesInputValidation(){
		boolean submitBool = true;
		
		titleString = titleSeries.getText();
		countryString = countrySeries.getText();
		genreString = genreSeries.getText();
		descriptionString = descriptionSeries.getText();
		if(watchedSeries.isSelected()){
			radioSelection = "watched";
		}else if(watching.isSelected()){
			radioSelection = "watching";
		}else{
			radioSelection = "toWatch";
		}
		seasonsString= seasons.getText();
		if (!seasonsString.matches("\\d*")) {
			seasonsString = premiereString.replaceAll("[^\\d]", "");
        }
		ratingString= ratingSeries.getText();
		if (!ratingString.matches("\\d*")) {
			ratingString = ratingString.replaceAll("[^\\d]", "");
        }
		episodesString = episodes.getText();
		if (!episodesString.matches("\\d*")) {
			episodesString = runtimeString.replaceAll("[^\\d]", "");
        }
		String [] seriesData = {titleString, imgName, descriptionString, episodesString, seasonsString, countryString, ratingString, genreString, radioSelection};
		for(String data : seriesData){
			if(data == "" || data==null){
				submitBool = false;
				return submitBool;
			}else{
				submitBool = true;
			}
		}
		return true;
	}
	
	public void clearingInputsMovies(){
		title.setText("");
		premiere.setText("");
		country.setText("");
		rating.setText("");
		genre.setText("");
		runtime.setText("");
		description.setText("");
		anchorImage.setStyle("-fx-background-image:none");
	}
	
	public void clearingInputsSeries(){
		titleSeries.setText("");
		episodes.setText("");
		countrySeries.setText("");
		ratingSeries.setText("");
		genreSeries.setText("");
		seasons.setText("");
		descriptionSeries.setText("");
		anchorImageSeries.setStyle("-fx-background-image:none");
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
	
	public void showMoviesListClick(MouseEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RandomMoviesAndSeries.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
        RandomMoviesAndSeriesController controller=fxmlLoader.<RandomMoviesAndSeriesController>getController();
        controller.setIndex(1);
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	home_page_scene.getStylesheets().add(getClass().getResource("RandomMoviesAndSeries.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
	
	public void showSeriesListClick(MouseEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RandomMoviesAndSeries.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
        RandomMoviesAndSeriesController controller=fxmlLoader.<RandomMoviesAndSeriesController>getController();
        controller.setIndex(2);
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	home_page_scene.getStylesheets().add(getClass().getResource("RandomMoviesAndSeries.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showAllMoviesList(ActionEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TopLists.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
        TopListsController controller=fxmlLoader.<TopListsController>getController();
        controller.setIndex(1);
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)hbox).getScene().getWindow();
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
    	Stage app_stage =  (Stage) ((Node)hbox).getScene().getWindow();
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
    	Stage app_stage =  (Stage) ((Node)hbox).getScene().getWindow();
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
    	Stage app_stage =  (Stage) ((Node)hbox).getScene().getWindow();
    	home_page_scene.getStylesheets().add(getClass().getResource("Add.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showUpdate(ActionEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Update.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)hbox).getScene().getWindow();
        home_page_scene.getStylesheets().add(getClass().getResource("Update.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showDelete(ActionEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Delete.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)hbox).getScene().getWindow();
    	home_page_scene.getStylesheets().add(getClass().getResource("Delete.css").toExternalForm());
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
	
}
