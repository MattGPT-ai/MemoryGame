package memory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // instantiate the model
        Memory_Model model = new Memory_Model();

        // load the view from fxml
        Parent root = FXMLLoader.load(getClass().getResource("memory.fxml"));

        // set scene and show primary stage
        primaryStage.setTitle("Memory");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
} // Main Application
