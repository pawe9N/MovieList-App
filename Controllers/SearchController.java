import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SearchController implements Initializable {
	
	@FXML HBox hbox;
	@FXML AnchorPane anchorImage;
	@FXML TextField titleField;
	@FXML Button watchedButton, watchingButton, toWatchButton, deleteButton, submit;
	@FXML MenuButton tableMenu;
	@FXML Text message, titleText;
	
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		tableMenu.setText(tableMenu.getItems().get(0).getText());
		watchingButton.setDisable(true);
		
		for(int i=0; i < tableMenu.getItems().size(); i++){
	    	tableMenu.getItems().get(i).setOnAction(e -> {
	    		tableMenu.setText(((MenuItem)e.getSource()).getText());
	    		if(tableMenu.getText().contains("Movies")){	
	    			watchingButton.setDisable(true);
	    		}else{
	    			watchingButton.setDisable(false);
	    		}
	    	});
	    }
		
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	titleField.requestFocus();  
		    }   
		});
		
		titleField.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

	        @Override
	        public void handle(KeyEvent event)
	        {
	            if (event.getCode() == KeyCode.ENTER)
	            {
	               submit.fire();
	               event.consume(); // do nothing
	        }
	      }
	    });

	    submit.setOnAction(e -> {
	    	String title = titleField.getText().toString();
	    	String table = tableMenu.getText().toString();
	    	if(title.length() != 0){
		    	String imgName="";
		    	boolean mysqlCorrect = true;
		    	try{
		    		 Statement sqlState = MySQL.conn.createStatement();
					 String selectStuff = "Select imgName from "+table+" where title='"+title+"'";
					 ResultSet rows = sqlState.executeQuery(selectStuff);
					 while(rows.next()){
						 imgName = rows.getString("imgName");
					 }
					 if(imgName.length() == 0){
						 mysqlCorrect = false;
				    		titleField.setText("");
				    		titleField.requestFocus();  
				    		message.setText("Input correct title!");
							message.setFill(Color.RED);
					 }
		    	}catch(SQLException er){
		    		er.printStackTrace();
		    	}
		    	
		    	if(mysqlCorrect){
		    		anchorImage.setStyle("-fx-background-image: url('"+imgName+".jpg')");
		    		titleText.setText(title);
		    		titleField.setText("");
		    		titleField.requestFocus();
		    		watchedButton.setOnAction(e2 -> {
		    			try {
		    				String query = "update "+table+" set watched = ? where title='"+title+"';";
							PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, true);
							statement.executeUpdate();
							if(table.contains("Series")){
								query = "update "+table+" set watching = ? where title='"+title+"';";
								statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
								statement.setBoolean(1, false);
								statement.executeUpdate();
							}
							query = "update "+table+" set toWatch = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							message.setText("Added watched!");
						    message.setFill(Color.GREEN);
						} catch (SQLException er2) {
							er2.printStackTrace();
							message.setText("Can't send values to database!");
							message.setFill(Color.RED);
						}
		    		});
		    		
		    		watchingButton.setOnAction(e2 -> {
		    			try {
		    				String query = "update "+table+" set watched = ? where title='"+title+"';";
							PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							if(table.contains("Series")){
								query = "update "+table+" set watching = ? where title='"+title+"';";
								statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
								statement.setBoolean(1, true);
								statement.executeUpdate();
							}
							query = "update "+table+" set toWatch = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							message.setText("Added watching!");
						    message.setFill(Color.GREEN);
						} catch (SQLException er2) {
							er2.printStackTrace();
							message.setText("Can't send values to database!");
							message.setFill(Color.RED);
						}
		    		});
		    		
		    		toWatchButton.setOnAction(e2 -> {
		    			try {
		    				String query = "update "+table+" set watched = ? where title='"+title+"';";
							PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							if(table.contains("Series")){
								query = "update "+table+" set watching = ? where title='"+title+"';";
								statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
								statement.setBoolean(1, false);
								statement.executeUpdate();
							}
							query = "update "+table+" set toWatch = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, true);
							statement.executeUpdate();
							message.setText("Added to watch!");
						    message.setFill(Color.GREEN);
						} catch (SQLException er2) {
							er2.printStackTrace();
							message.setText("Can't send values to database!");
							message.setFill(Color.RED);
						}
		    		});
		    		
		    		deleteButton.setOnAction(e2 -> {
		    			try {
		    				String query = "update "+table+" set watched = ? where title='"+title+"';";
							PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							if(table.contains("Series")){
								query = "update "+table+" set watching = ? where title='"+title+"';";
								statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
								statement.setBoolean(1, false);
								statement.executeUpdate();
							}
							query = "update "+table+" set toWatch = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							message.setText("Deleted from list!");
						    message.setFill(Color.GREEN);
						} catch (SQLException er2) {
							er2.printStackTrace();
							message.setText("Can't send values to database!");
							message.setFill(Color.RED);
						}
		    		});
		    	}
	    	}
	    	else{
	    		message.setText("Input correct title!");
				message.setFill(Color.RED);
	    	}
	    });
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