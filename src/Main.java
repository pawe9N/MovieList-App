import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	
	Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
    	MySQL.initialization();
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("RandomMoviesAndSeries.fxml"));
        primaryStage.setTitle("MovieList App");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("icon.png"));
        Parent root = loader.load();
        scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("RandomMoviesAndSeries.css").toExternalForm());
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Bowlby+One+SC");
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}