import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class YourProfileController implements Initializable {
	@Override 
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("View is now loaded!");
             
    }
	
	public void showYourProfile(MouseEvent event) throws IOException{
    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("YourProfile.fxml"));
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
	
	public void showMovieList(MouseEvent event) throws IOException{
    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("Scene.fxml"));
    	Scene home_page_scene = new Scene(home_page_parent, 1280, 720);
    	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	app_stage.setScene(home_page_scene);
    	app_stage.show();
    }
	
	
}
