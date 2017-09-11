import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
        primaryStage.setTitle("Movie App");
        
        Scene scene = new Scene(root, 1080, 720);
        scene.getStylesheets().add(getClass().getResource("Scene.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


}