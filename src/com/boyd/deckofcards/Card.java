package com.boyd.deckofcards;

// Rep Invariant
// initSuit must be a constant in Suit
// initRank must be a contant in Rank

// Abstraction Function
// Card object that will be used in DeckOfCards class
// Each Card is identified by combination of it's Suit and Rank
// Any change to one of these reps means it is a different card.

// No Rep Exposure Argument
// All methods are observer methods and only return copies

/**
 * 
 * The total # of cards in DeckOfCards == (Suit.values().length * Rank.values().length)
 * @param initSuit is a constant of the Suit enum
 * @param initRank is a constant of the Rank enum
 */
public class Card {

	public enum Suit {DIAMOND, HEART, CLUB, SPADE};
	public enum Rank {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE};
	Suit suit;
	Rank rank;
	
	public Card(Suit initSuit, Rank initRank) {
		
		this.suit = initSuit;
		this.rank = initRank;
		
	}
	
	/**
	 * @return the suit of the card
	 */
	public Suit getSuit() {
		Suit tempSuit = this.suit;
		return tempSuit;
	}
	
	/**
	 * @return the rank of the card
	 */
	public Rank getRank() {
		Rank tempRank = this.rank;
		return tempRank;
	}
	
	@Override
	public int hashCode() {
		return this.suit.hashCode() + this.rank.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Card) ) return false;
		Card other = (Card)o;
		boolean cardsEqual = ( this.getSuit() == other.getSuit() && this.getRank() == other.getRank() );
		return cardsEqual;
	}

	public String toString() {
		return this.rank.toString() + "_" + this.suit.toString();
	}

}
