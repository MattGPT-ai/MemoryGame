package memory;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

    //public class Player

    //
    private Deck deck;
    private SimpleIntegerProperty numPlayers;
    private IntegerProperty currentPlayer;
    private ArrayList<IntegerProperty> playerScores;
    //private ObservableArray<Integer> playerScores;


    public MemoryModel()
    {
        numPlayers = new SimpleIntegerProperty(2);
        deck = new Deck();
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

    public IntegerProperty getCurrentPlayerProperty() {
        return currentPlayer;
    }

    public final int getCurrentPlayer()
    {
        return currentPlayer.get();
    }

    public ArrayList<IntegerProperty> getPlayerScores() {
        return playerScores;
    }

    // begin game
    public void initGame()
    {

        deck.shuffleDeck();

        playerScores = new ArrayList<IntegerProperty>(numPlayers.get());
        for(int i=0; i<numPlayers.get(); i++) {
            playerScores.add(new SimpleIntegerProperty(0));
        }

        System.out.println("Beginning new game with " + numPlayers.get() + " players!");


        //ObservableList<Integer> playerList = FXCollections.observableArrayList();
        //ObservableList<Integer> playerList = new ObservableList<Integer>();

        /*playerScores = new ObservableArray<Integer>() {
            @Override
            public void addListener(ArrayChangeListener<Integer> listener) {

            }

            @Override
            public void removeListener(ArrayChangeListener<Integer> listener) {

            }

            @Override
            public void resize(int size) {

            }

            @Override
            public void ensureCapacity(int capacity) {

            }

            @Override
            public void trimToSize() {

            }

            @Override
            public void clear() {

            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public void addListener(InvalidationListener listener) {

            }

            @Override
            public void removeListener(InvalidationListener listener) {

            }
        } */
    } // initGame

    public void executeNextRound() {

    }



} // Model
