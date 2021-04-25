/**
 * 
 */
package com.boyd.deckofcards;

import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.Card.Suit;

/**
 * @author David
 *
 * Interface for a deck of cards object (DeckOfCards).
 * 
 * Should be able to do anything that you could with a normal deck of cards.
 */

public interface DeckOfCardsInterface {
	
	/**
	 * @return - int of amount of cards in the DeckOfCards object;
	 */
	public int getNumCards();
	
	/**
	 * See if Card is in the DeckOfCards
	 * @param suit - the suit of the card requested
	 * @param rank - the rank of the card requested
	 * @return - Boolean that answers if the card is in the deck
	 */
	public boolean hasCard(Suit suit, Rank rank);
	
	/**
	 * Get the top Card off the DeckOfCards
	 * @return - Card on top of the DeckOfCards
	 */
	public Card getTopCard();

	/**
	 * Get the bottom Card off the DeckOfCards
	 * @return - Card on bottom of the DeckOfCards
	 */
	public Card getBottomCard();
	
	/**
	 * Get the Card from the requested index counting from the top of the DeckOfCards
	 * @return - Card from the requested index counting from the top of the DeckOfCards
	 */
	public Card getCardByIndex(int index);
	
	/**
	 * Get a random Card from the DeckOfCards
	 * @return - random Card from the DeckOfCards
	 */
	public Card getRandomCard();
	
	/**
	 * Get a requested Card from the DeckOfCards;
	 * @param suit - the Suit of the requested Card
	 * @param rank - the Rank of the requested Card
	 * @return - the requested Card
	 * @throws Exception - if Card is not in the DeckOfCards
	 */
	public Card getExactCard(Suit suit, Rank rank) throws Exception ;
	
	/**
	 * Get the specified amount of cards off the top of the DeckOfCards
	 * @param - numOfCards requested; requires numOfCards > 0;
	 * @return - Card[] containing the specified amount of cards
	 */
	public Card[] getCardsTop(int numOfCards);
	
	/**
	 * Get the specified amount of cards off the bottom of the DeckOfCards
	 * @param - numOfCards requested; requires numOfCards > 0;
	 * @return - Card[] containing the specified amount of cards
	 */
	public Card[] getCardsBottom(int numOfCards);
	
	/**
	 * Get the specified amount of cards from the specified spots in the DeckOfCards counting from the top
	 * @param - numOfCards requested; requires numOfCards > 0;
	 * @return - Card[] containing the specified amount of cards
	 */
	public Card[] getCardsByIndex(int[] cardsSelected);
	
	/**
	 * Get the specified amount of cards from random spots in the DeckOfCards
	 * @param - numOfCards requested; requires numOfCards > 0;
	 * @return - Card[] containing the specified amount of cards
	 */
	public Card[] getCardsRandom(int numOfCards);
	
	/**
	 * Add the Card to the top of the DeckOfCards
	 * @param card - the Card to be added
	 */
	public void addCardTop(Card card);
	
	/**
	 * Add the Card to the bottom of the DeckOfCards
	 * @param card - the Card to be added
	 */
	public void addCardBottom(Card card);
	
	/**
	 * Add the Card to a random spot in the DeckOfCards
	 * @param card - the Card to be added
	 */
	public void addCardRandom(Card card);
	
	/**
	 * Add Cards to the top of the DeckOfCards
	 * @param card - the Cards to be added
	 */
	public void addCardsTop(Card[] cards);
	
	/**
	 * Add Cards to the bottom of the DeckOfCards
	 * @param card - the Cards to be added
	 */
	public void addCardsBottom(Card[] cards);
	
	/**
	 * Add Cards to random spots in the DeckOfCards
	 * @param card - the Cards to be added
	 */
	public void addCardsRandom(Card[] cards);
	
	/**
	 * Shuffles the Cards to random spots in the DeckOfCards
	 */
	public void shuffleDeck();
	

}
