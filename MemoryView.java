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
    private Stage memoryStage;
    private int numRows;
    private int numCols;
    Image backImage;

    public MemoryView()
    {

        int cardHeight = 100;


        // set up grid
        cardGrid = new GridPane();
        cardGrid.setPadding(new Insets(10, 10, 10, 10));
        cardGrid.setVgap(5);
        cardGrid.setHgap(5);

        backImage = new Image("/memory/images/blue_back.jpg", 50, cardHeight, true, false);

        numRows = 4;
        numCols = 13;
        int index = 0;

        // fill grid
        for(int row=0; row<numRows; row++) {
            for(int col=0; col<numCols; col++) {

                Button cardButton = new Button("", new ImageView(backImage));
                cardGrid.setRowIndex(cardButton, row);
                cardGrid.setColumnIndex(cardButton, col);

                //cardButton.setOnAction()

                cardGrid.getChildren().add(cardButton);

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

        // add view of player scores

        Label pTitle = new Label("Player:");
        cardGrid.setRowIndex(pTitle, numRows+1);
        Label sTitle = new Label("Score:");
        cardGrid.setRowIndex(sTitle, numRows+2);
        Label cTitle = new Label("Current player: ");
        cardGrid.setRowIndex(cTitle, numRows+3);
        Label cLabel = new Label();
        cardGrid.setRowIndex(cLabel, numRows+3);
        cardGrid.setColumnIndex(cLabel, 1);
        cLabel.textProperty().bind(controller.getCurrentPlayer().asString());
        cardGrid.getChildren().addAll(pTitle, sTitle, cTitle, cLabel);


        for(int i=0; i<controller.getNumPlayers(); i++) {
            Label pLabel = new Label(Integer.toString(i+1));
            cardGrid.setRowIndex(pLabel, numRows+1);
            cardGrid.setColumnIndex(pLabel, i+1);
            Label scoreLabel = new Label();
            //scoreLabel.setAlignment(Pos.CENTER);
            scoreLabel.textProperty().bind(controller.getPlayerScore(i).asString());
            scoreLabel.setPadding(new Insets(25, 30, 50, 25));
            cardGrid.setRowIndex(scoreLabel, numRows+2);
            cardGrid.setColumnIndex(scoreLabel, i+1);
            cardGrid.getChildren().addAll(pLabel, scoreLabel);

        }


        // open new window
        Scene memoryScene = new Scene(cardGrid, 1000, 600);
        memoryStage = new Stage();
        memoryStage.setTitle("Memory game");
        memoryStage.setScene(memoryScene);
        memoryStage.show();

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

    public Image getBackImage() {
        return backImage;
    }

    public void closeGameView() {
        memoryStage.close();
    }


} // MemoryView
