import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class WatchedWatchingToWatchController implements Initializable {
	
	@FXML
	private HBox hbox;
	
	private IntegerProperty indexC = new SimpleIntegerProperty(-1);

	public void setIndex(int index){
	    this.indexC.set(index);
	    if(index==1){
	    	
	    }else if(index==2){
	    	
	    }else if(index==3){
	    	
	    }
	}

	@Override 
	public void initialize(URL location, ResourceBundle resources) {
	    
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