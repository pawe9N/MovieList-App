import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WatchedWatchingToWatchController implements Initializable {
	
	@FXML HBox hbox;
	@FXML AnchorPane anchorScroll, anchorMovie, anchorCover;
	@FXML Button watchedButton, watchingButton, toWatchButton, watchedB;
	@FXML Label descriptionLabel;
	@FXML Text runtimeOrEpisodesText, premiereOrSeasonsText, ratingText, countryText, genreText, titleText, message;
	ObservableList<AnchorPane> newMoviePanes = FXCollections.observableArrayList();
	private IntegerProperty indexC = new SimpleIntegerProperty(-1);

	public void setIndex(int index){
	    this.indexC.set(index);
	    if(index==1){
	    	showWatched();
	    }else if(index==2){
	    	showWatching();
	    }else if(index==3){
	    	showToWatch();
	    }
	}

	@Override 
	public void initialize(URL location, ResourceBundle resources) {
	    
	}
	
	public void showWatched(){
		
		newMoviePanes.clear();
		
		try {
			Statement sqlState = MySQL.conn.createStatement();
			String selectStuff = "Select imgName, title, genre, country, rating, premiere, runtime, description from movies where watched=true";
			ResultSet rows = sqlState.executeQuery(selectStuff);
			
			String imgName, title, genre, country, rating, premiere, runtime, description;
			if(rows.next()!=false){
			imgName =  rows.getString("imgName");
			title = rows.getString("title");
			genre = rows.getString("genre");
			country = rows.getString("country");
			rating = rows.getString("rating");
			premiere = rows.getString("premiere");
			runtime = rows.getString("runtime");
			description = rows.getString("description");
			watchingButton.setDisable(true);
			watchedButton.getStyleClass().add("deleteButton");
			toWatchButton.getStyleClass().add("wButton");
			watchingButton.getStyleClass().add("wButton");
			anchorCover.setStyle("-fx-background-image: url('"+imgName+".jpg')");
			titleText.setText("Title: "+title);
			genreText.setText("Genre: "+genre);
			countryText.setText("Country: "+country);
			ratingText.setText("Rating: "+rating+"/10");
			premiereOrSeasonsText.setText("Premiere: "+premiere);
			runtimeOrEpisodesText.setText("Runtime: "+runtime+"min");
			descriptionLabel.setText("     "+description);
			watchedButton.setOnAction(e2 -> {
				try {
    				String query = "update movies set watched = ? where title='"+title+"';";
					PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, false);
					statement.executeUpdate();
					query = "update movies set toWatch = ? where title='"+title+"';";
					statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, false);
					statement.executeUpdate();
					showWatched();
					message.setText("Deleted from watched!");
				    message.setFill(Color.GREEN);
				} catch (SQLException er2) {
					er2.printStackTrace();
					message.setText("Can't send values to database!");
					message.setFill(Color.RED);
				}
			});
			toWatchButton.setOnAction(e2 -> {
				try {
    				String query = "update movies set watched = ? where title='"+title+"';";
					PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, false);
					statement.executeUpdate();
					query = "update movies set toWatch = ? where title='"+title+"';";
					statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, true);
					statement.executeUpdate();
					showWatched();
					message.setText("Added to to watch!");
				    message.setFill(Color.GREEN);
				} catch (SQLException er2) {
					er2.printStackTrace();
					message.setText("Can't send values to database!");
					message.setFill(Color.RED);
				}
			});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		watchedButton.setText("Delete From List");
		watchingButton.setText("Watching");
		toWatchButton.setText("To Watch");
		anchorScroll.getChildren().clear();
		
		double layoutX = anchorMovie.getLayoutX()-175;
		double layoutY = anchorMovie.getLayoutY();
		int amountMovies = MySQL.getAmountOfWatched("movies");
		int amountSeries = MySQL.getAmountOfWatched("series");
		
		
		try {
			Statement sqlState = MySQL.conn.createStatement();
			String selectStuff = "Select imgName, title, genre, country, rating, premiere, runtime, description from movies where watched=true";
			ResultSet rows = sqlState.executeQuery(selectStuff);
		
		for(int i=1; i<=amountMovies; i++){
			String imgName, title, genre, country, rating, premiere, runtime, description;
			rows.next();
			AnchorPane newAnchor = new AnchorPane();
			newAnchor.getStyleClass().add("anchorMovie");
			AnchorPane anchorImage = new AnchorPane();
			anchorImage.getStyleClass().add("anchorImage");
			newAnchor.getChildren().addAll(anchorImage);
			layoutX +=150;
			newAnchor.setLayoutX(layoutX);
			newAnchor.setLayoutY(layoutY-10);
			newAnchor.setId("id"+i);
			imgName = rows.getString("imgName");
			newAnchor.getChildren().get(0).setStyle("-fx-background-image: url('"+imgName+".jpg')");
			title = rows.getString("title");
			genre = rows.getString("genre");
			country = rows.getString("country");
			rating = rows.getString("rating");
			premiere = rows.getString("premiere");
			runtime = rows.getString("runtime");
			description = rows.getString("description");
			newAnchor.setOnMouseClicked(e ->{
				watchingButton.setDisable(true);
				anchorCover.setStyle("-fx-background-image: url('"+imgName+".jpg')");
				titleText.setText("Title: "+title);
				genreText.setText("Genre: "+genre);
				countryText.setText("Country: "+country);
				ratingText.setText("Rating: "+rating+"/10");
				premiereOrSeasonsText.setText("Premiere: "+premiere);
				runtimeOrEpisodesText.setText("Runtime: "+runtime+"min");
				descriptionLabel.setText("     "+description);
				watchedButton.setOnAction(e2 -> {
					try {
	    				String query = "update movies set watched = ? where title='"+title+"';";
						PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
						statement.setBoolean(1, false);
						statement.executeUpdate();
						query = "update movies set toWatch = ? where title='"+title+"';";
						statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
						statement.setBoolean(1, false);
						statement.executeUpdate();
						showWatched();
						message.setText("Deleted from watched!");
					    message.setFill(Color.GREEN);
					} catch (SQLException er2) {
						er2.printStackTrace();
						message.setText("Can't send values to database!");
						message.setFill(Color.RED);
					}
				});
				toWatchButton.setOnAction(e2 -> {
					try {
	    				String query = "update movies set watched = ? where title='"+title+"';";
						PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
						statement.setBoolean(1, false);
						statement.executeUpdate();
						query = "update movies set toWatch = ? where title='"+title+"';";
						statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
						statement.setBoolean(1, true);
						statement.executeUpdate();
						showWatched();
						message.setText("Added to watch!");
					    message.setFill(Color.GREEN);
					    
					} catch (SQLException er2) {
						er2.printStackTrace();
						message.setText("Can't send values to database!");
						message.setFill(Color.RED);
					}
				});
				
			});
			newMoviePanes.add(newAnchor);
		}
		    selectStuff = "Select imgName, title, genre, country, rating, seasons, episodes, description from series where watched=true";
			rows = sqlState.executeQuery(selectStuff);
		
			for(int i=1; i<=amountSeries; i++){
				String imgName, title, genre, country, rating, seasons, episodes, description;
				rows.next();
				AnchorPane newAnchor = new AnchorPane();
				newAnchor.getStyleClass().add("anchorMovie");
				AnchorPane anchorImage = new AnchorPane();
				anchorImage.getStyleClass().add("anchorImage");
				newAnchor.getChildren().addAll(anchorImage);
				layoutX +=150;
				newAnchor.setLayoutX(layoutX);
				newAnchor.setLayoutY(layoutY-10);
				newAnchor.setId("id"+i);
				imgName = rows.getString("imgName");
				newAnchor.getChildren().get(0).setStyle("-fx-background-image: url('"+imgName+".jpg')");
				title = rows.getString("title");
				genre = rows.getString("genre");
				country = rows.getString("country");
				rating = rows.getString("rating");
				seasons = rows.getString("seasons");
				episodes = rows.getString("episodes");
				description = rows.getString("description");
				newAnchor.setOnMouseClicked(e ->{
					watchingButton.setDisable(false);
					anchorCover.setStyle("-fx-background-image: url('"+imgName+".jpg')");
					titleText.setText("Title: "+title);
					genreText.setText("Genre: "+genre);
					countryText.setText("Country: "+country);
					ratingText.setText("Rating: "+rating+"/10");
					premiereOrSeasonsText.setText("Seasons: "+seasons);
					runtimeOrEpisodesText.setText("Episodes: "+episodes);
					descriptionLabel.setText("     "+description);
					watchedButton.setOnAction(e2 -> {
						try {
		    				String query = "update series set watched = ? where title='"+title+"';";
							PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							query = "update series set watching = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							query = "update series set toWatch = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							showWatched();
							message.setText("Deleted from watched!");
						    message.setFill(Color.GREEN);
						} catch (SQLException er2) {
							er2.printStackTrace();
							message.setText("Can't send values to database!");
							message.setFill(Color.RED);
						}
					});
					watchingButton.setOnAction(e2 -> {
						try {
		    				String query = "update series set watched = ? where title='"+title+"';";
							PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							query = "update series set watching = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, true);
							statement.executeUpdate();
							query = "update series set toWatch = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							showWatched();
							message.setText("Added to watching!");
						    message.setFill(Color.GREEN);
						} catch (SQLException er2) {
							er2.printStackTrace();
							message.setText("Can't send values to database!");
							message.setFill(Color.RED);
						}
					});
					toWatchButton.setOnAction(e2 -> {
						try {
		    				String query = "update series set watched = ? where title='"+title+"';";
							PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							query = "update series set watching = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							query = "update series set toWatch = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, true);
							statement.executeUpdate();
							showWatched();
							message.setText("Added to watch!");
						    message.setFill(Color.GREEN);
						} catch (SQLException er2) {
							er2.printStackTrace();
							message.setText("Can't send values to database!");
							message.setFill(Color.RED);
						}
					});
				});
				newMoviePanes.add(newAnchor);
			}
		
		anchorScroll.getChildren().addAll(newMoviePanes);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showWatching(){
		newMoviePanes.clear();
		
		try {
			Statement sqlState = MySQL.conn.createStatement();
			String selectStuff = "Select imgName, title, genre, country, rating, seasons, episodes, description from series where watching=true";
		    ResultSet rows = sqlState.executeQuery(selectStuff);
		    String imgName, title, genre, country, rating, seasons, episodes, description;
		    if(rows.next()!=false){
			imgName = rows.getString("imgName");
			title = rows.getString("title");
			genre = rows.getString("genre");
			country = rows.getString("country");
			rating = rows.getString("rating");
			seasons = rows.getString("seasons");
			episodes = rows.getString("episodes");
			description = rows.getString("description");
			watchedButton.getStyleClass().add("wButton");
			toWatchButton.getStyleClass().add("wButton");
			watchingButton.getStyleClass().add("deleteButton");
			anchorCover.setStyle("-fx-background-image: url('"+imgName+".jpg')");
			titleText.setText("Title: "+title);
			genreText.setText("Genre: "+genre);
			countryText.setText("Country: "+country);
			ratingText.setText("Rating: "+rating+"/10");
			premiereOrSeasonsText.setText("Seasons: "+seasons);
			runtimeOrEpisodesText.setText("Episodes: "+episodes);
			descriptionLabel.setText("     "+description);
			watchedButton.setOnAction(e2 -> {
				try {
    				String query = "update series set watched = ? where title='"+title+"';";
					PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, true);
					statement.executeUpdate();
					query = "update series set watching = ? where title='"+title+"';";
					statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, false);
					statement.executeUpdate();
					query = "update series set toWatch = ? where title='"+title+"';";
					statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, false);
					statement.executeUpdate();
					showWatching();
					message.setText("Added to watched!");
				    message.setFill(Color.GREEN);
				} catch (SQLException er2) {
					er2.printStackTrace();
					message.setText("Can't send values to database!");
					message.setFill(Color.RED);
				}
			});
			watchingButton.setOnAction(e2 -> {
				try {
    				String query = "update series set watched = ? where title='"+title+"';";
					PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, false);
					statement.executeUpdate();
					query = "update series set watching = ? where title='"+title+"';";
					statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, false);
					statement.executeUpdate();
					query = "update series set toWatch = ? where title='"+title+"';";
					statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, false);
					statement.executeUpdate();
					showWatching();
					message.setText("Deleted from watching!");
				    message.setFill(Color.GREEN);
				} catch (SQLException er2) {
					er2.printStackTrace();
					message.setText("Can't send values to database!");
					message.setFill(Color.RED);
				}
			});
			toWatchButton.setOnAction(e2 -> {
				try {
    				String query = "update series set watched = ? where title='"+title+"';";
					PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, false);
					statement.executeUpdate();
					query = "update series set watching = ? where title='"+title+"';";
					statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, false);
					statement.executeUpdate();
					query = "update series set toWatch = ? where title='"+title+"';";
					statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, true);
					statement.executeUpdate();
					showWatching();
					message.setText("Added to watch!");
				    message.setFill(Color.GREEN);
				} catch (SQLException er2) {
					er2.printStackTrace();
					message.setText("Can't send values to database!");
					message.setFill(Color.RED);
				}
			});
		    }
			}catch (SQLException e) {
				e.printStackTrace();
			}
	
		watchedButton.setText("Watched");
		watchingButton.setText("Delete From List");
		toWatchButton.setText("To Watch");
		anchorScroll.getChildren().clear();
		
		double layoutX = anchorMovie.getLayoutX()-175;
		double layoutY = anchorMovie.getLayoutY();
		int amountSeries = MySQL.getAmountOfWatching();
		
		try {
			Statement sqlState = MySQL.conn.createStatement();
			String selectStuff = "Select imgName, title, genre, country, rating, seasons, episodes, description from series where watching=true";
		    ResultSet rows = sqlState.executeQuery(selectStuff);
		
			for(int i=1; i<=amountSeries; i++){
				String imgName, title, genre, country, rating, seasons, episodes, description;
				rows.next();
				AnchorPane newAnchor = new AnchorPane();
				newAnchor.getStyleClass().add("anchorMovie");
				AnchorPane anchorImage = new AnchorPane();
				anchorImage.getStyleClass().add("anchorImage");
				newAnchor.getChildren().addAll(anchorImage);
				layoutX +=150;
				newAnchor.setLayoutX(layoutX);
				newAnchor.setLayoutY(layoutY-10);
				newAnchor.setId("id"+i);
				imgName = rows.getString("imgName");
				newAnchor.getChildren().get(0).setStyle("-fx-background-image: url('"+imgName+".jpg')");
				title = rows.getString("title");
				genre = rows.getString("genre");
				country = rows.getString("country");
				rating = rows.getString("rating");
				seasons = rows.getString("seasons");
				episodes = rows.getString("episodes");
				description = rows.getString("description");
				newAnchor.setOnMouseClicked(e ->{
					anchorCover.setStyle("-fx-background-image: url('"+imgName+".jpg')");
					titleText.setText("Title: "+title);
					genreText.setText("Genre: "+genre);
					countryText.setText("Country: "+country);
					ratingText.setText("Country: "+rating+"/10");
					premiereOrSeasonsText.setText("Seasons: "+seasons);
					runtimeOrEpisodesText.setText("Episodes: "+episodes);
					descriptionLabel.setText("     "+description);
					watchedButton.setOnAction(e2 -> {
						try {
		    				String query = "update series set watched = ? where title='"+title+"';";
							PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, true);
							statement.executeUpdate();
							query = "update series set watching = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							query = "update series set toWatch = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							showWatching();
							message.setText("Added to watched!");
						    message.setFill(Color.GREEN);
						} catch (SQLException er2) {
							er2.printStackTrace();
							message.setText("Can't send values to database!");
							message.setFill(Color.RED);
						}
					});
					watchingButton.setOnAction(e2 -> {
						try {
		    				String query = "update series set watched = ? where title='"+title+"';";
							PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							query = "update series set watching = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							query = "update series set toWatch = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							showWatching();
							message.setText("Deleted from watching!");
						    message.setFill(Color.GREEN);
						} catch (SQLException er2) {
							er2.printStackTrace();
							message.setText("Can't send values to database!");
							message.setFill(Color.RED);
						}
					});
					toWatchButton.setOnAction(e2 -> {
						try {
		    				String query = "update series set watched = ? where title='"+title+"';";
							PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							query = "update series set watching = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							query = "update series set toWatch = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, true);
							statement.executeUpdate();
							showWatching();
							message.setText("Added to watch!");
						    message.setFill(Color.GREEN);
						} catch (SQLException er2) {
							er2.printStackTrace();
							message.setText("Can't send values to database!");
							message.setFill(Color.RED);
						}
					});
				});
				newMoviePanes.add(newAnchor);
			}
		
		anchorScroll.getChildren().addAll(newMoviePanes);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showToWatch(){
		
		newMoviePanes.clear();
		
		try {
			Statement sqlState = MySQL.conn.createStatement();
			String selectStuff = "Select imgName, title, genre, country, rating, premiere, runtime, description from movies where toWatch=true";
			ResultSet rows = sqlState.executeQuery(selectStuff);
			
			String imgName, title, genre, country, rating, premiere, runtime, description;
			if(rows.next()!=false){
			imgName =  rows.getString("imgName");
			title = rows.getString("title");
			genre = rows.getString("genre");
			country = rows.getString("country");
			rating = rows.getString("rating");
			premiere = rows.getString("premiere");
			runtime = rows.getString("runtime");
			description = rows.getString("description");
			watchedButton.getStyleClass().add("wButton");
			toWatchButton.getStyleClass().add("deleteButton");
			watchingButton.getStyleClass().add("wButton");
			watchingButton.setDisable(true);
			anchorCover.setStyle("-fx-background-image: url('"+imgName+".jpg')");
			titleText.setText("Title: "+title);
			genreText.setText("Genre: "+genre);
			countryText.setText("Country: "+country);
			ratingText.setText("Rating: "+rating+"/10");
			premiereOrSeasonsText.setText("Premiere: "+premiere);
			runtimeOrEpisodesText.setText("Runtime: "+runtime+"min");
			descriptionLabel.setText("     "+description);
			watchedButton.setOnAction(e2 -> {
				try {
    				String query = "update movies set watched = ? where title='"+title+"';";
					PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, true);
					statement.executeUpdate();
					query = "update movies set toWatch = ? where title='"+title+"';";
					statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, false);
					statement.executeUpdate();
					showToWatch();
					message.setText("Added to watched!");
				    message.setFill(Color.GREEN);
				} catch (SQLException er2) {
					er2.printStackTrace();
					message.setText("Can't send values to database!");
					message.setFill(Color.RED);
				}
			});
			toWatchButton.setOnAction(e2 -> {
				try {
    				String query = "update movies set watched = ? where title='"+title+"';";
					PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, false);
					statement.executeUpdate();
					query = "update movies set toWatch = ? where title='"+title+"';";
					statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
					statement.setBoolean(1, false);
					statement.executeUpdate();
					showToWatch();
					message.setText("Deleted from to watch!");
				    message.setFill(Color.GREEN);
				} catch (SQLException er2) {
					er2.printStackTrace();
					message.setText("Can't send values to database!");
					message.setFill(Color.RED);
				}
			});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		watchedButton.setText("Watched");
		watchingButton.setText("Watching");
		toWatchButton.setText("Delete From List");
		anchorScroll.getChildren().clear();
		
		double layoutX = anchorMovie.getLayoutX()-175;
		double layoutY = anchorMovie.getLayoutY();
		int amountMovies = MySQL.getAmountOfToWatch("movies");
		int amountSeries = MySQL.getAmountOfToWatch("series");
		
		try {
			Statement sqlState = MySQL.conn.createStatement();
			String selectStuff = "Select imgName, title, genre, country, rating, premiere, runtime, description from movies where toWatch=true";
			ResultSet rows = sqlState.executeQuery(selectStuff);
		
		for(int i=1; i<=amountMovies; i++){
			String imgName, title, genre, country, rating, premiere, runtime, description;
			rows.next();
			AnchorPane newAnchor = new AnchorPane();
			newAnchor.getStyleClass().add("anchorMovie");
			AnchorPane anchorImage = new AnchorPane();
			anchorImage.getStyleClass().add("anchorImage");
			newAnchor.getChildren().addAll(anchorImage);
			layoutX +=150;
			newAnchor.setLayoutX(layoutX);
			newAnchor.setLayoutY(layoutY-10);
			newAnchor.setId("id"+i);
			imgName = rows.getString("imgName");
			newAnchor.getChildren().get(0).setStyle("-fx-background-image: url('"+imgName+".jpg')");
			title = rows.getString("title");
			genre = rows.getString("genre");
			country = rows.getString("country");
			rating = rows.getString("rating");
			premiere = rows.getString("premiere");
			runtime = rows.getString("runtime");
			description = rows.getString("description");
			newAnchor.setOnMouseClicked(e ->{
				watchingButton.setDisable(true);
				anchorCover.setStyle("-fx-background-image: url('"+imgName+".jpg')");
				titleText.setText("Title: "+title);
				genreText.setText("Genre: "+genre);
				countryText.setText("Country: "+country);
				ratingText.setText("Rating: "+rating+"/10");
				premiereOrSeasonsText.setText("Premiere: "+premiere);
				runtimeOrEpisodesText.setText("Runtime: "+runtime+"min");
				descriptionLabel.setText("     "+description);
				watchedButton.setOnAction(e2 -> {
					try {
	    				String query = "update movies set watched = ? where title='"+title+"';";
						PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
						statement.setBoolean(1, true);
						statement.executeUpdate();
						query = "update movies set toWatch = ? where title='"+title+"';";
						statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
						statement.setBoolean(1, false);
						statement.executeUpdate();
						showToWatch();
						message.setText("Added to watched!");
					    message.setFill(Color.GREEN);
					} catch (SQLException er2) {
						er2.printStackTrace();
						message.setText("Can't send values to database!");
						message.setFill(Color.RED);
					}
				});
				toWatchButton.setOnAction(e2 -> {
					try {
	    				String query = "update movies set watched = ? where title='"+title+"';";
						PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
						statement.setBoolean(1, false);
						statement.executeUpdate();
						query = "update movies set toWatch = ? where title='"+title+"';";
						statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
						statement.setBoolean(1, false);
						statement.executeUpdate();
						showToWatch();
						message.setText("Deleted from to watch!");
					    message.setFill(Color.GREEN);
					} catch (SQLException er2) {
						er2.printStackTrace();
						message.setText("Can't send values to database!");
						message.setFill(Color.RED);
					}
				});
			});
			newMoviePanes.add(newAnchor);
		}
		    selectStuff = "Select imgName, title, genre, country, rating, seasons, episodes, description from series where toWatch=true";
			rows = sqlState.executeQuery(selectStuff);
		
			for(int i=1; i<=amountSeries; i++){
				String imgName, title, genre, country, rating, seasons, episodes, description;
				rows.next();
				AnchorPane newAnchor = new AnchorPane();
				newAnchor.getStyleClass().add("anchorMovie");
				AnchorPane anchorImage = new AnchorPane();
				anchorImage.getStyleClass().add("anchorImage");
				newAnchor.getChildren().addAll(anchorImage);
				layoutX +=150;
				newAnchor.setLayoutX(layoutX);
				newAnchor.setLayoutY(layoutY-10);
				newAnchor.setId("id"+i);
				imgName = rows.getString("imgName");
				newAnchor.getChildren().get(0).setStyle("-fx-background-image: url('"+imgName+".jpg')");
				title = rows.getString("title");
				genre = rows.getString("genre");
				country = rows.getString("country");
				rating = rows.getString("rating");
				seasons = rows.getString("seasons");
				episodes = rows.getString("episodes");
				description = rows.getString("description");
				newAnchor.setOnMouseClicked(e ->{
					watchingButton.setDisable(false);
					anchorCover.setStyle("-fx-background-image: url('"+imgName+".jpg')");
					titleText.setText("Title: "+title);
					genreText.setText("Genre: "+genre);
					countryText.setText("Country: "+country);
					ratingText.setText("Rating: "+rating+"/10");
					premiereOrSeasonsText.setText("Seasons: "+seasons);
					runtimeOrEpisodesText.setText("Episodes: "+episodes);
					descriptionLabel.setText("     "+description);
					watchedButton.setOnAction(e2 -> {
						try {
		    				String query = "update series set watched = ? where title='"+title+"';";
							PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, true);
							statement.executeUpdate();
							query = "update series set watching = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							query = "update series set toWatch = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							showToWatch();
							message.setText("Added to watched!");
						    message.setFill(Color.GREEN);
						} catch (SQLException er2) {
							er2.printStackTrace();
							message.setText("Can't send values to database!");
							message.setFill(Color.RED);
						}
					});
					watchingButton.setOnAction(e2 -> {
						try {
		    				String query = "update series set watched = ? where title='"+title+"';";
							PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							query = "update series set watching = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, true);
							statement.executeUpdate();
							query = "update series set toWatch = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							showToWatch();
							message.setText("Added to watching!");
						    message.setFill(Color.GREEN);
						} catch (SQLException er2) {
							er2.printStackTrace();
							message.setText("Can't send values to database!");
							message.setFill(Color.RED);
						}
					});
					toWatchButton.setOnAction(e2 -> {
						try {
		    				String query = "update series set watched = ? where title='"+title+"';";
							PreparedStatement statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							query = "update series set watching = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							query = "update series set toWatch = ? where title='"+title+"';";
							statement =  (PreparedStatement) MySQL.conn.prepareStatement(query);
							statement.setBoolean(1, false);
							statement.executeUpdate();
							showToWatch();
							message.setText("Deleted from to watch!");
						    message.setFill(Color.GREEN);
						} catch (SQLException er2) {
							er2.printStackTrace();
							message.setText("Can't send values to database!");
							message.setFill(Color.RED);
						}
					});
				});
				newMoviePanes.add(newAnchor);
			}
		
		anchorScroll.getChildren().addAll(newMoviePanes);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
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