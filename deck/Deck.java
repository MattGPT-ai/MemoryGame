package deck;

import java.util.Collections;
import java.util.ArrayList;


public class Deck {

    enum Suit {
		hearts('H'), clubs('C'), diamonds('D'), spades('S');

		private final char suitChar;

		private Suit(char c) {
			suitChar = c;
		}

		public char toChar() {
			return suitChar;
		}
    }

    enum Rank {
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
	}


    // subclass
    public class Card {

		private Suit suit;
		private Rank rank;

		public Card(Suit s, Rank r) {
			suit = s;
			rank = r;
		}

		public Suit getSuit() {
			return suit;
		}

		public Rank getRank() {
			return rank;
		}

		public String imageFilename() {
			String rc;

			switch(rank.ordinal()) {
				case 0: rc = "A"; break; // Ace
				case 10: rc = "J"; break; // Jack
				case 11: rc = "Q"; break; // Queen
				case 12: rc = "K"; break; // King
				default: rc = Integer.toString(rank.ordinal()+1);
			}

			return "/memory/images/" + rc + suit.toChar() + ".jpg";
		}

    } // class Card
    

    private ArrayList<Card> cards;
    //private Card[] cards;
    
    public Deck() {
	
		cards = new ArrayList<Card>(52);

		// add one of each card to deck
		for(Suit s: Suit.values()) {
	    	for(Rank r : Rank.values()) {

				cards.add(new Card(s, r));

	    	}
		}
	
    } // Deck constructor

    
    public Card getCard(int i) {
	return this.cards.get(i);
    }
    
    public Deck shuffleDeck(){
	
	Collections.shuffle(cards);
	
	//Deck shuffled = new Deck();
	// delete old Deck 
	return this;
    }
    
} // class Deck
