package com.boyd.deckofcards;

import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.Card.Suit;


public class SuitPair {
	
	public final Suit first;
	public final Rank second;
	
	String suitString;
	String rankString;
	
	public SuitPair(Suit suit, Rank rank) {
		
		suitString = suit.toString();
		rankString = rank.toString();
		this.first = suit;
		this.second = rank;
	}

	public Suit getSuit() {
		return this.first;
	}
	
	public Rank getRank() {
		return this.second;
	}
	
	public String toString() {
		return rankString + "_" + suitString;
	}
	
	@Override
	public int hashCode() {
		return ( this.first.hashCode() + this.second.hashCode() );
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof SuitPair) ) return false;
		SuitPair other = (SuitPair)o;
		boolean suitPair = ( this.suitString == other.suitString && this.rankString == other.rankString );
		return suitPair;
	}
}