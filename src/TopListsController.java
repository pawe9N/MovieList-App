import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class TopListsController implements Initializable {	
	
	@FXML
	private TableView<MoviesAndSeriesTables> table;
	
	@FXML
	private TableColumn<MoviesAndSeriesTables, Integer> movieId;

	@FXML
	private TableColumn<MoviesAndSeriesTables, ImageView> movieImage;
	
	@FXML
	private TableColumn<MoviesAndSeriesTables, String> movieTitle;
	
	@FXML
	private TableColumn<MoviesAndSeriesTables, Integer> movieRating;
	
	@FXML
	private Text pageTitle;
	
	private IntegerProperty indexC = new SimpleIntegerProperty(-1);

	public void setIndex(int index){
	    this.indexC.set(index);
	    if(indexC.getValue().intValue()==1){
	    	showAllMoviesList();
	    }else if(indexC.getValue().intValue()==2){
	    	showAllSeriesList();
	    }
	}
	

	@Override 
	public void initialize(URL location, ResourceBundle resources) {
	    
	}
	
	public void showAllList(String tableName){
		
		pageTitle.setText("Movies List");
		
		movieId.setCellValueFactory(
	            new PropertyValueFactory<MoviesAndSeriesTables, Integer>("id")
	        );
		
		movieImage.setCellValueFactory(
	            new PropertyValueFactory<MoviesAndSeriesTables, ImageView>("movieCover")
	        );
		
		movieTitle.setCellValueFactory(
	            new PropertyValueFactory<MoviesAndSeriesTables, String>("movieTitle")
	        );
		
		setWrapCellFactory(movieTitle);
		
		movieRating.setCellValueFactory(
	            new PropertyValueFactory<MoviesAndSeriesTables, Integer>("movieRating")
	        );
		
		ObservableList<MoviesAndSeriesTables> row = FXCollections.observableArrayList();
		ImageView movieCover;
		String movieTitleS, imgName;
		Integer movieRatingI, index=1;
		
		try{	
			 Statement sqlState = MySQL.conn.createStatement();
			 String selectStuff = "Select title, imgName, rating from "+tableName+" order by rating desc";
			 ResultSet rows = sqlState.executeQuery(selectStuff);
			 while(rows.next()){
			    imgName = rows.getString("imgName");
				movieCover = new ImageView(new Image(imgName+".jpg"));
				movieCover.setFitHeight(200);
				movieCover.setFitWidth(150);
				movieTitleS = rows.getString("title");
				movieRatingI = Integer.parseInt(rows.getString("rating"));
				row.add(new MoviesAndSeriesTables("#"+index.toString(), movieCover, movieTitleS, movieRatingI));
				table.setItems(row);
				index++;
			 }
		 }
		 catch (SQLException ex) {
			 System.out.println("SQLException: " + ex.getMessage());
			 System.out.println("VendorError: " + ex.getErrorCode()); 
		 }

		
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

	private void setWrapCellFactory(TableColumn<MoviesAndSeriesTables, String> table) {
	    table.setCellFactory(tablecol -> {
	        TableCell<MoviesAndSeriesTables, String> cell = new TableCell<>();
	        Text text = new Text();
	        cell.setGraphic(text);
	        text.wrappingWidthProperty().bind(cell.widthProperty());
	        text.setStyle("-fx-text-alignment: center;");
	        text.setFill(Color.WHITE);
	        text.textProperty().bind(cell.itemProperty());
	        return cell;
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
	
	public void showAllMoviesList(){
		showAllList("movies");
	}
	
	public void showAllSeriesList(){
		showAllList("series");
	}
}