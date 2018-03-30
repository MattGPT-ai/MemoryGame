package memory;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ArrayChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
//import javafx.beans.InvalidationListener;
import deck.Deck;

public class MemoryModel {

    //
    private Deck deck;
    private SimpleIntegerProperty numPlayers;
    private Integer[] playerScores;
    //private ObservableArray<Integer> playerScores;

    public MemoryModel()
    {
        numPlayers = new SimpleIntegerProperty(2);
        deck = new Deck();
    }

    public final int getNumPlayers()
    {
        return numPlayers.get();
    }


    // begin game
    public void initGame()
    {

        deck.shuffleDeck();

        playerScores = new Integer[numPlayers.get()];
        ObservableList<Integer> playerList = FXCollections.observableArrayList();
        //ObservableList<Integer> playerList = new ObservableList<Integer>();

        System.out.println("Beginning new game with " + numPlayers.get() + " players!");


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
    }

} // Model
