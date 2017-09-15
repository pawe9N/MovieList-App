import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TopListsController implements Initializable {	
	
	@FXML
	private TableView<MoviesAndSeriesTables> table;

	@FXML
	private TableColumn<MoviesAndSeriesTables, ImageView> movieImage;
	
	@FXML
	private TableColumn<MoviesAndSeriesTables, Label> movieTitle;
	
	@FXML
	private TableColumn<MoviesAndSeriesTables, Integer> movieRating;
	
	@FXML
	private Text pageTitle;
	
	private IntegerProperty indexC = new SimpleIntegerProperty(-1);

	public void setIndex(int index){
	    this.indexC.set(index);
	    if(indexC.getValue().intValue()==1){
	    	showAllMovies();
	    }else if(indexC.getValue().intValue()==2){
	    	showAllSeries();
	    }
	}
	

	@Override 
	public void initialize(URL location, ResourceBundle resources) {
	         System.out.println(indexC.getValue().intValue());
	}
	
	
	public void showAllMovies(){
		
		pageTitle.setText("Movies List");
		
		movieImage.setCellValueFactory(
	            new PropertyValueFactory<MoviesAndSeriesTables, ImageView>("movieCover")
	        );
		
		movieTitle.setCellValueFactory(
	            new PropertyValueFactory<MoviesAndSeriesTables, Label>("movieTitle")
	        );
		
		movieRating.setCellValueFactory(
	            new PropertyValueFactory<MoviesAndSeriesTables, Integer>("movieRating")
	        );
		
		int amountMovies = MySQL.getAmountOfRows("movies");
		ObservableList<MoviesAndSeriesTables> row = FXCollections.observableArrayList();
		ImageView movieCover;
		Label movieTitle;
		String imgName;
		Integer movieRating;
		for(int i=1; i <= amountMovies; i++){
			imgName = MySQL.getStringFromTable("movies", "imgName", i);
			movieCover = new ImageView(new Image(imgName+".jpg"));
			movieCover.setFitHeight(250);
			movieCover.setFitWidth(200);
			movieTitle = new Label(MySQL.getStringFromTable("movies", "title", i));
			movieRating = Integer.parseInt( MySQL.getStringFromTable("movies", "rating", i));
			row.add(new MoviesAndSeriesTables(movieCover, movieTitle, movieRating));
			table.setItems(row);
		}
	}
	
public void showAllSeries(){
		
		pageTitle.setText("Series List");
		
		movieImage.setCellValueFactory(
	            new PropertyValueFactory<MoviesAndSeriesTables, ImageView>("movieCover")
	        );
		
		movieTitle.setCellValueFactory(
	            new PropertyValueFactory<MoviesAndSeriesTables, Label>("movieTitle")
	        );
		
		movieRating.setCellValueFactory(
	            new PropertyValueFactory<MoviesAndSeriesTables, Integer>("movieRating")
	        );
		
		int amountSeries = MySQL.getAmountOfRows("series");
		ObservableList<MoviesAndSeriesTables> row = FXCollections.observableArrayList();
		ImageView movieCover;
		Label movieTitle;
		String imgName;
		Integer movieRating;
		for(int i=1; i <= amountSeries; i++){
			imgName = MySQL.getStringFromTable("series", "imgName", i);
			movieCover = new ImageView(new Image(imgName+".jpg"));
			movieCover.setFitHeight(250);
			movieCover.setFitWidth(200);
			movieTitle = new Label(MySQL.getStringFromTable("series", "title", i));
			System.out.println(movieTitle);
			movieRating = Integer.parseInt( MySQL.getStringFromTable("series", "rating", i));
			row.add(new MoviesAndSeriesTables(movieCover, movieTitle, movieRating));
			table.setItems(row);
		}
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
}