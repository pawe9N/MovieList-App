import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddController implements Initializable {
	
	@FXML private HBox hbox;
	@FXML TextField title, premiere, country, rating, genre, runtime;
	@FXML TextArea description;
	@FXML Button explorerImages, submit;
	@FXML AnchorPane anchorImage;
	@FXML RadioButton watched, toWatch;
	@FXML ToggleGroup Watch;
	
	String titleString, premiereString, countryString, ratingString, genreString, runtimeString, descriptionString, imgName, radioSelection;
	boolean submitBool;
	FileChooser fileChooser;

	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		
		submit.setOnAction(e -> {
				
				submitBool = inputvalidation();
				
				if(submitBool){
					System.out.println("AAAA");
				}else{
					System.out.println("Noo");
				}
				
			});
		
		explorerImages.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			
			@Override
             public void handle(MouseEvent event) {
				 FileChooser fileChooser = new FileChooser();
				 FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		         fileChooser.getExtensionFilters().addAll(extFilterJPG);
				 File file = fileChooser.showOpenDialog(null);
				 if(file != null){
					 imgName = file.getName().replaceAll(".jpg", "");
					 copyImageToBin(file);
				 }
			}
			}); 
		
	    
}
	public void copyImageToBin(File file){
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
		 anchorImage.setStyle("-fx-background-image:url('"+file.getName()+"')");
	}
	
	public boolean inputvalidation(){
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
	
    
	public void showYourProfile(MouseEvent event) throws IOException{
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("YourProfile.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showSearch(MouseEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Search.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)event.getSource()).getScene().getWindow();
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showAddNew(ActionEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Add.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)hbox).getScene().getWindow();
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showUpdate(ActionEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Update.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)hbox).getScene().getWindow();
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
    
    public void showDelete(ActionEvent event) throws IOException{
	  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Delete.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage =  (Stage) ((Node)hbox).getScene().getWindow();
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
	
}
