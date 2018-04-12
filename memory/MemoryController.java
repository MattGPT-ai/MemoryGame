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

                /*
                if(flipBack == 1) {
                    memoryModel.getFirstCard().flipDown();
                    flipDown();

                }*/

                if(flipBack == 2) {
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

                        new Thread(){
                            public void run() {

                                try {
                                    Thread.sleep(100);
                                    //System.out.println("printin");
                                    //b.setGraphic(null);
                                } catch(InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                }

                            }
                        }.start();

                    }
                });
                index++;
            }
        }

    }

    public void flipBack(CardBlock[] cba) {
        for(int i=0; i<2; i++) {
            cba[i].setCardUp(false);
            cba[i].getButton().setGraphic(new ImageView(memoryView.getBackImage()));
        }
    }

    public void displayWinners(ArrayList<Integer> winningPlayers) {
        VBox dialogVbox = new VBox(20);
        String msg = "Winning players!\n";
        for(Integer i: winningPlayers) {
            msg += i+1 + " ";
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
