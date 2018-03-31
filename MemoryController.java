package memory;

import deck.Deck;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.concurrent.TimeUnit;


public class MemoryController{

    // supporting class to map card spaces
    public class CardBlock {

        private Deck.Card card;
        private Button button;
        private Boolean cardUp;

        public CardBlock(Deck.Card c, Button b) {
            card = c;
            button = b;
            cardUp = false;
        }

        public Deck.Card getCard() {
            return card;
        }

        public Button getButton() {
            return button;
        }

        public void setCardUp(Boolean up) {
            cardUp = up;
        }

        public void flipCard() {
            if(!cardUp) {
                cardUp = true;
                String fn = card.imageFilename();
                //System.out.println("clicked card! " + fn);
                Image frontImage = new Image(fn, 50, 100, true, false);
                button.setGraphic(new ImageView(frontImage));
                //button.
                int flipBack = memoryModel.nextFlip(this);


                if(flipBack == 1) {
                    memoryModel.getFirstCard().flipDown();
                    flipDown();

                }
                else if(flipBack == 2) {
                    memoryModel.getFirstCard().getButton().setGraphic(null);
                    button.setGraphic(null);
                }

            } // card up
            //else say card is already up or do nothing
        }
        public void flipDown() {
            cardUp = false;
            button.setGraphic(new ImageView(memoryView.getBackImage()));
        }

    }

    // Memory Controller

    private MemoryModel memoryModel;
    private MemoryView memoryView;
    private CardBlock[][] cardBlockGrid;

    public MemoryController(MemoryModel mm, MemoryView mv) {
        memoryModel = mm;
        memoryView = mv;
        cardBlockGrid = new CardBlock[mv.getNumRows()][mv.getNumCols()];
        fillCardBlockGrid();
    }

    public Deck.Card getCardFromDeck(int index) {
        return memoryModel.getDeck().getCard(index);
    }

    public Integer getNumPlayers() {
        return memoryModel.getNumPlayers();
    }

    public IntegerProperty getPlayerScore(int i) {
        return memoryModel.getPlayerScores().get(i);
    }

    public SimpleIntegerProperty getCurrentPlayer() {
        return memoryModel.getDisplayPlayerProperty();
    }



    private void fillCardBlockGrid() {

        int index = 0;
        for(int row=0; row<memoryView.getNumRows(); row++) {
            for(int col=0; col<memoryView.getNumCols(); col++) {
                Deck.Card c = getCardFromDeck(index);
                Button b = (Button)memoryView.getCardGrid().getChildren().get(index);
                CardBlock cardBlock = new CardBlock(c, b);
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        cardBlock.flipCard();

                        Platform.runLater(new Runnable() {
                            public void run() {
                                // pause after showing second card
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException ie) {
                                    System.err.println(ie);
                                }
                            }
                        });

                    }
                });
                index++;
            }
        }

    }

    /*
    public void flipDown(CardBlock cb) {
        cb.setCardUp(false);
        cb.getButton().setGraphic(new ImageView(memoryView.getBackImage()));
    }*/

    public void displayWinners(ArrayList<Integer> winningPlayers) {
        VBox dialogVbox = new VBox(20);
        String msg = "Winning players!\n";
        for(Integer i: winningPlayers) {
            msg += i + " ";
        }
        Text text = new Text(msg);
        dialogVbox.getChildren().add(text);
        Scene dialogScene = new Scene(dialogVbox, 300, 50);
        final Stage dialog = new Stage();
        dialog.setScene(dialogScene);
        dialog.show();
        memoryView.closeGameView();
    }


}
