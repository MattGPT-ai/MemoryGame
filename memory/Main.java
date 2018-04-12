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
        MemoryModel model = new MemoryModel();

        // load the view from fxml
        Parent root = FXMLLoader.load(getClass().getResource("memory.fxml"));

        //Button example = (Button) root.lookup("#button"); // #button exists in FXMLDocument.fxml
        //example.setOnAction(e -> { myController.doSomething(); });

        // set scene and show primary stage
        primaryStage.setTitle("Memory");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
} // Main Application
