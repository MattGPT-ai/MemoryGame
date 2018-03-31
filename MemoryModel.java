package memory;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.concurrent.TimeUnit;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.collections.ArrayChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
//import javafx.beans.InvalidationListener;
import deck.Deck;


/**
 * @author A
 *
 */

public class MemoryModel {

    // member variables
    private MemoryController memoryController;
    private Deck deck;
    private SimpleIntegerProperty numPlayers;
    private SimpleIntegerProperty currentPlayer;
    private SimpleIntegerProperty playerShift;
    private SimpleIntegerProperty displayPlayer;
    private ArrayList<IntegerProperty> playerScores;
    private MemoryController.CardBlock firstCard;
    private Boolean secondPick;
    private int totalScore;


    public MemoryModel()
    {
        numPlayers = new SimpleIntegerProperty(2);
        deck = new Deck();
        playerShift = new SimpleIntegerProperty(1); // to display 1-indexed value
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


        totalScore = 0;

        playerScores = new ArrayList<IntegerProperty>(numPlayers.get());
        for(int i=0; i<numPlayers.get(); i++) {
            playerScores.add(new SimpleIntegerProperty(0));
        }

        System.out.println("Beginning new game with " + numPlayers.get() + " players!");


    } // initGame


    public Boolean nextFlip(MemoryController.CardBlock cardBlock) {

        Deck.Card card = cardBlock.getCard();

        // simply pick first card
        if(!secondPick) {
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
            }

            // if they don't match, tell controller to flip cards back down
            else {
                return false;
            }
            secondPick = false;
        }

        // cycle through players for next turn
        cyclePlayer();

        return true;
    }

    private void cyclePlayer() {
        currentPlayer.set(currentPlayer.getValue()+1);
        if(currentPlayer.get() >= numPlayers.get()) {
            currentPlayer.set(0);
        }
    }

    private void checkWinCondition() {

        if(totalScore >= 26) {
            memoryController.displayWinner(currentPlayer.get()+1);
        }
    }


} // Model
