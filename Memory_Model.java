package memory;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ArrayChangeListener;
import javafx.collections.ObservableArray;

public class Memory_Model {

    private SimpleIntegerProperty numPlayers;
    private Integer[] playerScores;
    //private ObservableArray<Integer> playerScores;

    public Memory_Model()
    {
        numPlayers = new SimpleIntegerProperty(2);

    }

    public final int getNumPlayers()
    {
        return numPlayers.get();
    }


    // begin game
    public void initGame()
    {

        playerScores = new Integer[numPlayers.get()];

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
