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
	
	private Suit getSuit() {
		return this.suit;
	}
	
	private Rank getRank() {
		return this.rank;
	}
	
	public String toString() {
		return this.rank.toString() + "_" + this.suit.toString();
	}

}
