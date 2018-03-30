package memory;

import javafx.scene.control.Button;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import deck.Deck;


public class MemoryController {

    // supporting class to map card spaces
    public class CardBlock {
        private Deck.Card card;
        private Button button;

        public CardBlock(Deck.Card c, Button b) {
            card = c;
            button = b;
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

    private void fillCardBlockGrid() {

        int index = 0;
        for(int row=0; row<memoryView.getNumRows(); row++) {
            for(int col=0; col<memoryView.getNumCols(); col++) {
                Button b = (Button)memoryView.getCardGrid().getChildren().get(0);
                CardBlock cardBlock = new CardBlock(getCardFromDeck(index), b);
                index++;
            }
        }

    }

}
