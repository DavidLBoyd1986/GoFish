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

public class Card {

	// These are public, so they can be accessed for testing purposes
	public enum Suit {DIAMOND, HEART, CLUB, SPADE}
	public enum Rank {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT,
					  NINE, TEN, JACK, QUEEN, KING, ACE}

	private final Suit suit;
	private final Rank rank;

	/**
	 *
	 * The total # of cards in DeckOfCards == (Suit.values().length * Rank.values().length)
	 * @param initSuit is a constant of the Suit enum
	 * @param initRank is a constant of the Rank enum
	 */
	public Card(Suit initSuit, Rank initRank) {
		this.suit = initSuit;
		this.rank = initRank;
	}
	
	/**
	 * @return the suit of the card
	 */
	public Suit getSuit() {
		return this.suit;
	}
	
	/**
	 * @return the rank of the card
	 */
	public Rank getRank() {
		return this.rank;
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
		return (this.getSuit() == other.getSuit() && this.getRank() == other.getRank());
	}

	public String toString() {
		return this.rank.toString() + "_" + this.suit.toString();
	}

}
