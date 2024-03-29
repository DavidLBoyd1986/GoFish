/**
 * 
 */
package com.boyd.deckofcards;

import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.Card.Suit;

import java.util.Optional;



/**
 * @author David
 *
 * Interface for a deck of cards object (DeckOfCards).
 * 
 * Should be able to do anything that you could with a normal deck of cards.
 */

public interface DeckOfCardsInterface {
	
	/**
	 * Get the number of Cards in the Deck
	 * @return - the number of cards in the DeckOfCards object;
	 */
	int getNumCardsInDeck();
	
	/**
	 * Get the number of Cards not in the Deck
	 * @return - The number of cards not in the DeckOfCards object;
	 */
	int getNumCardsNotInDeck();
	
	/**
	 * Get the size of the discardPile
	 * @return - the number of cards in the discardPile
	 */
	int getNumCardsInDiscardPile();
	
	/**
	 * Get the number of Cards not in the Deck or Discard Pile
	 * @return - the number of Cards not in the Deck or Discard Pile
	 */
	int getNumCardsNotInDeckOrDiscard();
	
	/**
	 * See if Card is in the DeckOfCards
	 * @param suit - the Suit of the card requested
	 * @param rank - the Rank of the card requested
	 * @return - Boolean that answers if the card is in the deck
	 */
	boolean hasCard(Suit suit, Rank rank);
	
	/**
	 * See the card (String Representation) at a specific index in the DeckOfCards
	 * DeckOfCards must contain an amount of Cards > 0
	 * 
	 * @param index - integer index for the card requested. Index MUST be (0 < index < DeckOfCards.getNumCardsInDeck())
	 * @return - String representation for the Card at that index
	 */
	String seeCardByIndex(int index);
	
	/**
	 * Get the top Card off the DeckOfCards
	 * DeckOfCards must contain an amount of Cards > 0
	 * 
	 * @return - Card on top of the DeckOfCards
	 */
	Card getTopCard();

	/**
	 * Get the bottom Card off the DeckOfCards
	 * DeckOfCards must contain an amount of Cards > 0
	 * 
	 * @return - Card on bottom of the DeckOfCards
	 */
	Card getBottomCard();
	
	/**
	 * Get the Card from the requested index counting from the top of the DeckOfCards
	 * Must contain an amount of Cards > 0
	 * 
	 * @param index - index must be an int > 0 && int <= deck.getNumOfCardsInDeck
	 * @return - Card from the requested index counting from the top of the DeckOfCards
	 */
	Card getCardByIndex(int index);
	
	/**
	 * Get a random Card from the DeckOfCards
	 * DeckOfCards must contain an amount of Cards > 0
	 * 
	 * @return - random Card from the DeckOfCards
	 */
	Card getRandomCard();
	
	/**
	 * Get a requested Card from the DeckOfCards;
	 * 
	 * @param suit - the Suit of the requested Card
	 * @param rank - the Rank of the requested Card
	 * @return - An Optional<Card> that will contain the Card requested or be empty if the card wasn't in the deck
	 */
	Optional<Card> getExactCard(Suit suit, Rank rank);
	
	/**
	 * Get requested Card(s) from the DeckOfCards;
	 *
	 * @param array[] of Pair<Suit,Rank> representing the requested Card(s)
	 * @param suit - the Suit of the requested Card
	 * @param rank - the Rank of the requested Card
	 * @return - An Array with the requested Card(s) or an empty array
	 * Need to implement this
	 */
	//public Card[] getExactCards(SuitPair[] requestedCards);
	
	/**
	 * Get the specified amount of cards off the top of the DeckOfCards
	 * Must contain an amount of Cards > 0
	 * 
	 * @param numOfCards - amount of cards requested; requires numOfCards > 0 && numOfCards <= deck.getNumOfCardsInDeck
	 * @return - Card[] containing the specified amount of cards
	 */
	Card[] getCardsTop(int numOfCards);
	
	/**
	 * Get the specified amount of cards off the bottom of the DeckOfCards
	 * Must contain an amount of Cards > 0
	 * 
	 * @param numOfCards - amount of cards requested; requires numOfCards > 0 && numOfCards <= deck.getNumOfCardsInDeck
	 * @return - Card[] containing the specified amount of cards
	 */
	Card[] getCardsBottom(int numOfCards);
	
	/**
	 * Get the specified amount of cards from the specified spots in the DeckOfCards counting from the top
	 * Must contain an amount of Cards > 0
	 * 
	 * @param cardsSelected - int representing position of cardsSelected: The int must be > 0 && int <= deck.getNumOfCardsInDeck
	 * @return - Card[] containing the specified amount of cards
	 */
	Card[] getCardsByIndex(int[] cardsSelected);
	
	/**
	 * Get random cards from the deck equal to the numOfCards supplied
	 * 
	 * @param numOfCards - amount of cards requested; must be > 0
	 */
	Card[] getCardsRandom(int numOfCards);
	
	/**
	 * Add the Card to the top of the DeckOfCards
	 * @param card - the Card to be added
	 */
	void addCardTop(Card card);
	
	/**
	 * Add the Card to the bottom of the DeckOfCards
	 * @param card - the Card to be added
	 */
	void addCardBottom(Card card);
	
	/**
	 * Add the Card to a random spot in the DeckOfCards
	 * @param card - the Card to be added
	 */
	void addCardRandom(Card card);
	
	/**
	 * Add Cards to the top of the DeckOfCards
	 * @param cards - the Cards to be added
	 */
	void addCardsTop(Card[] cards);
	
	/**
	 * Add Cards to the bottom of the DeckOfCards
	 * @param cards - the Cards to be added
	 */
	void addCardsBottom(Card[] cards);
	
	/**
	 * Add Cards to random spots in the DeckOfCards
	 * @param cards - the Cards to be added
	 */
	void addCardsRandom(Card[] cards);
	
	/**
	 * Shuffles the Cards to random spots in the DeckOfCards
	 */
	void shuffleDeck();
	
	/**
	 * Add card to the discardPile
	 * @param card - the Card to be added to the discard pile
	 */
	void discardCard(Card card);
	
	/**
	 * Add cards to the discardPile
	 * @param cards - The Cards to be added to the discard pile
	 */
	void discardCards(Card[] cards);
	
	/**
	 * Add the discardPile to the top of the DeckOfCards
	 * This doesn't shuffle the discardPile or the DeckOfCards
	 */
	void addDiscardPileToDeck();
}
