/**
 * 
 */
package com.boyd.deckofcards;

import com.boyd.deckofcards.DeckOfCards.Rank;
import com.boyd.deckofcards.DeckOfCards.Suit;

/**
 * @author David
 *
 * Interface for a deck of cards object (DeckOfCards).
 * 
 * Should be able to do anything that you could with a normal deck of cards.
 */

public interface DeckOfCardsInterface {
	
	public int getNumCards();
	
	public boolean hasCard(Suit suit, Rank rank);
	
	public Card getTopCard();

	public Card getBottomCard();
	
	public Card getCardByIndex(int index);
	
	public Card getRandomCard();
	
	public Card getExactCard(Suit suit, Rank rank) throws Exception ;
	
	public Card[] getCardsTop(int numOfCards);
	
	public Card[] getCardsBottom(int numOfCards);
	
	public Card[] getCardsByIndex(int[] cardsSelected);
	
	public Card[] getCardsRandom(int numOfCards);
		
	public void addCardTop(Card card);
	
	public void addCardBottom(Card card);
	
	public void addCardRandom(Card card);
	
	public void addCardsTop(Card[] cards);
	
	public void addCardsBottom(Card[] cards);
	
	public void addCardsRandom(Card[] cards);
	
	public void shuffleDeck();
	
	public void dealCards(int numOfPlayers, int numOfCards);
	
}
