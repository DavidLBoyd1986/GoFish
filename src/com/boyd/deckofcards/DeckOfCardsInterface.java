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
	
	public Card[] getCardsTop(int numOfCards);
	
	public Card[] getCardsBottom(int numOfCards);
	
	public Card[] getCardsSpecific(int[] cardsSelected);
	
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
