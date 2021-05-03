package com.boyd.deckofcards;

import java.util.AbstractMap;

import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.Card.Suit;

public class SuitPair {
	
	String suitString;
	String rankString;
	AbstractMap.SimpleImmutableEntry<Suit, Rank> pair;
	
	public SuitPair(Suit suit, Rank rank) {
		
		suitString = suit.toString();
		rankString = rank.toString();
		
		final AbstractMap.SimpleImmutableEntry<Suit, Rank> pair
		    = new AbstractMap.SimpleImmutableEntry<>(suit, rank);
	}
	
	public Suit getSuit() {
		return pair.getKey();
	}
	
	public Rank getRank() {
		return pair.getValue();
	}
	
	public String toString() {
		return rankString + "_" + suitString;
	}
	
	@Override
	public int hashCode() {
		return pair.hashCode();
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