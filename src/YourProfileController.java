import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.skin.TableHeaderRow;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class YourProfileController implements Initializable {
	
	@FXML
	BorderPane borderPane;
	
	@FXML
	private TableView<MoviesAndSeriesTables> table;
	
	@FXML 
	private Text moviesAmount, seriesAmount;
	
	@FXML
	TableColumn<MoviesAndSeriesTables, String> movieCol, serieCol;
	
	@Override 
    public void initialize(URL location, ResourceBundle resources) {
        loadingTables();
             
    }
	
	public void loadingTables(){
		movieCol.setCellValueFactory(
	            new PropertyValueFactory<MoviesAndSeriesTables, String>("movieTitle")
	        );
		
		serieCol.setCellValueFactory(
	            new PropertyValueFactory<MoviesAndSeriesTables, String>("serieTitle")
	        );
		
		int amountMovies = MySQL.getAmountOfRows("movies");
		int amountSeries = MySQL.getAmountOfRows("series");
		int amount;
		amount = (amountMovies > amountSeries) ? amountMovies : amountSeries;
		ObservableList<MoviesAndSeriesTables> row = FXCollections.observableArrayList();
		String id, movieTitle, serieTitle;
		for(int i=1; i <= amount; i++){
			id = Integer.toString(i);
			movieTitle = MySQL.getStringFromTable("movies", "title", i);
			serieTitle = MySQL.getStringFromTable("series", "title", i);
			row.add(new MoviesAndSeriesTables(id, movieTitle, serieTitle));
			table.setItems(row);
		}
	    
		moviesAmount.setText("Movies: "+Integer.toString(amountMovies));
		seriesAmount.setText("Series: "+Integer.toString(amountSeries));
		
		table.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) table.lookup("TableHeaderRow");
		        header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
		            @Override
		            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		                header.setReordering(false);
		            }
		        });
		    }
		});
		
		
	}
	
	public void showYourProfile(MouseEvent event) throws IOException{
    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("YourProfile.fxml"));
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
	
	public void showMoviesListClick(MouseEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
        Controller controller=fxmlLoader.<Controller>getController();
        controller.setIndex(1);
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
	
	public void showSeriesListClick(MouseEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene.fxml"));
        Parent home_page_parent = (Parent) fxmlLoader.load();
        Controller controller=fxmlLoader.<Controller>getController();
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
	    	Stage app_stage =  (Stage) ((Node)borderPane).getScene().getWindow();
	    	app_stage.setScene(home_page_scene);
	    	app_stage.show();
	 }
	  
	 public void showAllSeriesList(ActionEvent event) throws IOException{
		  	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TopLists.fxml"));
	        Parent home_page_parent = (Parent) fxmlLoader.load();
	        TopListsController controller=fxmlLoader.<TopListsController>getController();
	        controller.setIndex(2);
	    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
	    	Stage app_stage =  (Stage) ((Node)borderPane).getScene().getWindow();
	    	app_stage.setScene(home_page_scene);
	    	app_stage.show();
	    }
	
	
	
}
