package memory;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import deck.Deck;
import javafx.util.Pair;


/**
 * @author Andrey Gaydukov
 *
 */

public class MemoryModel {

    // member variables
    private MemoryController memoryController;
    private Deck deck;
    private SimpleIntegerProperty numPlayers;
    private SimpleIntegerProperty currentPlayer;
    //private SimpleIntegerProperty playerShift;
    private SimpleIntegerProperty displayPlayer;
    private ArrayList<IntegerProperty> playerScores;
    private MemoryController.CardBlock firstCard;
    private MemoryController.CardBlock[] lastPair;
    //private Boolean matchedLast;
    private Boolean secondPick;
    private int totalScore;


    public MemoryModel()
    {
        numPlayers = new SimpleIntegerProperty(2);
        deck = new Deck();
        //matchedLast = false;
        //playerShift = new SimpleIntegerProperty(1); // to display 1-indexed value
    }

    public void setMemoryController(MemoryController mc) {
        memoryController = mc;
    }

    public Deck getDeck() {
        return deck;
    }

    public final int getNumPlayers()
    {
        return numPlayers.get();
    }

    public final void setNumPlayers(int newVal)
    {
        numPlayers.set(newVal);
    }

    public SimpleIntegerProperty getCurrentPlayerProperty() {
        return currentPlayer;
    }

    public SimpleIntegerProperty getDisplayPlayerProperty() {
        return displayPlayer;
    }

    public final int getCurrentPlayer()
    {
        return currentPlayer.get();
    }

    public ArrayList<IntegerProperty> getPlayerScores() {
        return playerScores;
    }

    public MemoryController.CardBlock getFirstCard() {
        return firstCard;
    }

    // begin game
    public void initGame()
    {

        deck.shuffleDeck();
        secondPick = false;
        currentPlayer = new SimpleIntegerProperty(); // initializes to 0
        displayPlayer = new SimpleIntegerProperty(1);

        totalScore = 0;

        playerScores = new ArrayList<IntegerProperty>(numPlayers.get());
        for(int i=0; i<numPlayers.get(); i++) {
            playerScores.add(new SimpleIntegerProperty(0));
        }

        System.out.println("Beginning new game with " + numPlayers.get() + " players!");


    } // initGame


    public int nextFlip(MemoryController.CardBlock cardBlock) {

        int flipBack = 0;
        Deck.Card card = cardBlock.getCard();

        // simply pick first card
        if(!secondPick) {

            // hide previous pair
            if(lastPair != null) { // !matchedLast
                memoryController.flipBack(lastPair);
            }

            secondPick = true;
            firstCard = cardBlock;
        }

        // if picking second card, check for match
        else {

            // if cards match, increment player score and total score
            if(card.getRank() == firstCard.getCard().getRank()) {
                IntegerProperty pScore = playerScores.get(currentPlayer.get());
                pScore.set(pScore.get()+1);
                totalScore++;
                checkWinCondition();
                flipBack = 2;
                lastPair = null;
                //matchedLast = true;
            }

            // if they don't match, tell controller to flip cards back down
            else {
                lastPair = new MemoryController.CardBlock[] {firstCard, cardBlock};
                //matchedLast = false;
                flipBack = 1;
            }

            secondPick = false;

            // cycle through players for next turn
            cyclePlayer();

        }

        // deprecated
        return flipBack;
    }

    private void cyclePlayer() {
        currentPlayer.set(currentPlayer.getValue()+1);
        if(currentPlayer.get() >= numPlayers.get()) {
            currentPlayer.set(0);
        }
        displayPlayer.set(currentPlayer.get()+1);
    }

    private void checkWinCondition() {

        if(totalScore >= 26) {
             int maxScore = 0;
             ArrayList<Integer> winners = new ArrayList<Integer>();

             for(int player=0; player<numPlayers.get(); player++) {
                 int score = playerScores.get(player).get();
                 if (score > maxScore) {
                    maxScore = score;
                 }
             }

            for(int player=0; player<numPlayers.get(); player++) {
                if (playerScores.get(player).get() == maxScore) {
                    winners.add(player);
                }
            }
                 memoryController.displayWinners(winners);
        }
    }


} // Model
