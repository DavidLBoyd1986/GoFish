package com.boyd.deckofcards;

import com.boyd.deckofcards.DeckOfCards.Rank;
import com.boyd.deckofcards.DeckOfCards.Suit;

public class Card {

	Suit suit;
	Rank rank;
	
	public Card(Suit initSuit, Rank initRank) {
		
		this.suit = initSuit;
		this.rank = initRank;
		
	}
	
	public Suit getSuit() {
		return this.suit;
	}
	
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
		boolean cardsEqual = ( this.getSuit() == other.getSuit() && this.getRank() == other.getRank() );
		return cardsEqual;
	}
	public String toString() {
		return this.rank.toString() + "_" + this.suit.toString();
	}

}
