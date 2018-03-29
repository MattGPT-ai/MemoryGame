package memory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Controller {

    private int numPlayers;

    @FXML
    private TextField nPlayersField;


    // this method is automatically called by fxml to initialize the controller
    @FXML
    private void initialize() {


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


    public void quitGame(ActionEvent actionEvent) {
        System.exit(0);
    }


}
