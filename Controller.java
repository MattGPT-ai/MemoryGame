package memory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.control.Button;


// main controller for the application
public class Controller {

    private int numPlayers;
    private MemoryModel memoryModel;

    @FXML
    private TextField nPlayersField;

    // create buttons to play or quit
    @FXML
    Button playButton;


    // constructor called before initialize
    public Controller()
    {
        numPlayers = 2;
    }

    // this method is automatically called by fxml to initialize the controller
    @FXML
    private void initialize() {

        // PLAY button
        //Image playImage = new Image("images/AS.jpg", 50, 50, true, false);
        // = new Button("Play!", new ImageView(playImage));


        // set number of players and force to only have digits entered

        nPlayersField.setPrefColumnCount(2);
        nPlayersField.setText(Integer.toString(numPlayers));
        nPlayersField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*")) {
                    nPlayersField.setText(newValue.replaceAll("[^\\d]", ""));
                }

                numPlayers = Integer.parseInt(newValue);
            }
        });
    }


    public void newGame()
    {
        memoryModel = new MemoryModel();
        memoryModel.setNumPlayers(numPlayers);
        memoryModel.initGame();
        MemoryView memoryView = new MemoryView();
        MemoryController memoryController = new MemoryController(memoryModel, memoryView);
        memoryModel.setMemoryController(memoryController);
        memoryView.setController(memoryController);
        memoryView.openView();
    }

    // Quit button
    public void quitGame(ActionEvent actionEvent) {
        System.exit(0);
    }


} // Controller
