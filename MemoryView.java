package memory;

import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.geometry.Pos;

import deck.Deck;

public class MemoryView {

    private MemoryController controller;
    private GridPane cardGrid;
    private int numRows;
    private int numCols;


    public MemoryView()
    {


        // set up grid
        cardGrid = new GridPane();
        cardGrid.setPadding(new Insets(10, 10, 10, 10));
        cardGrid.setVgap(5);
        cardGrid.setHgap(5);

        Image playImage = new Image("/memory/images/blue_back.jpg", 50, 50, true, false);

        numRows = 4;
        numCols = 13;
        int index = 0;

        // fill grid
        for(int row=0; row<numRows; row++) {
            for(int col=0; col<numCols; col++) {

                Button cardButton = new Button("", new ImageView(playImage));
                cardGrid.setRowIndex(cardButton, row);
                cardGrid.setColumnIndex(cardButton, col);
                cardGrid.getChildren().addAll(cardButton);

                //Deck.Card

                index++;
            }
        }

    } // MemoryView constructor

    public void setController(MemoryController c) {
        controller = c;
    }

    public void openView()
    {

        // open new window
        Scene memoryScene = new Scene(cardGrid, 800, 600);
        Stage memoryStage = new Stage();
        memoryStage.setTitle("Memory game");
        memoryStage.setScene(memoryScene);
        memoryStage.show();

        for(int i=0; i<controller.getNumPlayers(); i++) {
            Label scoreLabel = new Label();
            scoreLabel.setAlignment(Pos.CENTER_LEFT);
            scoreLabel.textProperty().bind(controller.getPlayerScore(i).asString());
            //text.setText(Integer.toString(P1_Score));
            scoreLabel.setPadding(new Insets(25, 30, 50, 25));
        }

    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public GridPane getCardGrid() {
        return cardGrid;
    }


} // MemoryView
