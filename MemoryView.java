package memory;

import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class MemoryView {
    public MemoryView()
    {

        GridPane cardGrid = new GridPane();
        cardGrid.setPadding(new Insets(10, 10, 10, 10));
        cardGrid.setVgap(5);
        cardGrid.setHgap(5);

        Image playImage = new Image("/memory/images/blue_back.jpg", 50, 50, true, false);

        int numRows = 4;
        int numCols = 13;
        int index = 0;

        for(int row=0; row<numRows; row++) {
            for(int col=0; col<numCols; col++) {
                Button cardButton = new Button("", new ImageView(playImage));
                cardGrid.setRowIndex(cardButton, row);
                cardGrid.setColumnIndex(cardButton, col);
                cardGrid.getChildren().addAll(cardButton);
                index++;
            }
        }

        Scene memoryScene = new Scene(cardGrid, 800, 600);
        Stage memoryStage = new Stage();
        memoryStage.setTitle("Memory game");
        memoryStage.setScene(memoryScene);
        memoryStage.show();

    }
} // MemoryView
