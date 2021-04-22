/**
 * 
 */
package com.boyd.deckofcards;

/**
 * @author David
 *
 */
public interface DeckOfCardsInterface {
	
	public int getNumCards();
	
	public Card getTopCard();

	public Card getBottomCard();
	
	public Card getSpecificCard(int index);
	
	public Card getRandomCard();
	
	public Card shuffleDeck();
	
	public void dealCards(int numOfPlayers, int numOfCards);
	
}
