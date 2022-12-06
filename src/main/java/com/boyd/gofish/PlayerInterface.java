package com.boyd.gofish;

import com.boyd.gofish.Player.Result;
import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.DeckOfCards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public interface PlayerInterface {

	/**
	 * Sets the repeatTurn boolean
	 * @param repeat - boolean
	 */
	void setRepeatTurn(boolean repeat);
	
	/**
	 * 	/**
	 * Puts some delay in the Computers actions to give the game flow
	 * And let the Player see who requests what from whom.
	 *
	 * @param i - number of seconds to wait
	 */
	void gameDelay(int i);
	
	/**
	 * Gets the Player's name
	 * 
	 * @return - String of the Player's name
	 */
	String getName();
	
	/**
	 * Gets the Player's position
	 * 
	 * @return - int representing the Player's position at the table
	 */
	int getPosition();
	
	/**
	 * Gets all the Player's books
	 * @return - String representing the HashMap of all the Player's books
	 */
	Set<Rank> getBooks();
	
	/**
	 * Gets the hand for the Player
	 * 
	 * @return - An ArrayList representing that Player's hands.
	 */
	ArrayList<Card> getHand();
	/**
	 * Gets the bookCheck HashMap
	 * 
	 * @return the bookCheck Hashmap
	 */
	HashMap<Rank, Integer> getBookCheck();
	
	/**
	 * Gets all the books the player has
	 * @return HashMap - that holds all the books
	 */
	int getNumOfBooks();
	
	/**
	 * Determines if a Player has a Card of that Rank in his hand
	 * 
	 * @param rank - A rank in the Rank enum.
	 * @return hasRank - Boolean indicating if the Player has that Rank of card
	 */
	boolean hasRank(Rank rank);
	
	/**
	 * Determines if a Player has Cards
	 * @return - boolean indicating if a Player has cards in his hand
	 */
	boolean hasCards();
	
	/**
	 * Requests Cards from another player of that Rank. The Player must have one Card of that Rank in his hand to request it
	 * 
	 * @param rank - Rank to be requested
	 * @param player - the Player the request is made to
	 * @return hasCards - boolean that indicates if the Player has the requested Card or not
	 */
	boolean requestCards(Rank rank, Player player);
	
	/**
	 * Gets the requested Cards from another Player or a Card from the top of the DeckOfCards.
	 * If the Player has Cards of the requested Rank the returned Card[] will be all the Cards of that Rank from his hand
	 * 
	 * @param rank - the Rank of the requested Cards
	 * @param player - the Player the request is made to
	 * @return requestedCards - Card[] of the requestedCards
	 */
	Card[] getCards(Rank rank, Player player);
	
	/**
	 * Draws one Card from the top of the DeckOfCards, and puts it in the Player's hand
	 * 
	 * @param deck - DeckOfCards the Player will draw the card from
	 * @return drawnRank - Rank of card drawn
	 */
	Rank drawCard(DeckOfCards deck);
	
	/**
	 * Updates a Player's bookCheck with the number of Card's by Rank they have
	 * 
	 * @param rank - The Rank of the Cards that are being added or removed
	 * @param cardCount - int between -3 and 3 that indicates the amount of cards of that Rank the Player is getting or losing
	 */
	void updateBookCheck(Rank rank, int cardCount);
	
	/**
	 * Updates all Player's bookCheck after the initial deal
	 * 
	 */
	void doInitialBookCheck();

	/**
	 * Creates a book of a Rank of Cards once a Player has all 4, should only be ran if checksForBook() returns True;
	 * Examine's a Player's hand, and creates a book, and adds that to the Player's books
	 */
	void createBook(Rank rank);
	
	/**
	 * method used for the Player to take their turn
	 * 
	 * @param players - list of players
	 * @param deck - the DeckOfCards
	 */
	Optional<Result> takeTurn(ArrayList<Player> players, DeckOfCards deck);
	
	/**
	 * Attempts to draw a card from the deck if the request failed
	 * 
	 * @param deck - deck of cards used for the game
	 * @param playerRequested - Player the request was made to
	 * @param rankRequested - Rank of Card requested from the Player
	 * @param numOfCardsRetrieved - int for the # of cards retrieved. Will be 1.
	 */
	void GoFish(DeckOfCards deck, Player playerRequested,
						 Rank rankRequested, int numOfCardsRetrieved);
	
	/**
	 * The Player has the requested Rank, this takes the cards from the player
	 * 
	 * @param playerRequested - Player the request was made to
	 * @param rankRequested - Rank of Card requested from the Player
	 * @param numOfCardsRetrieved - int for the # of cards retrieved.
	 */
	void takeCards(Player playerRequested, Rank rankRequested,
						  int numOfCardsRetrieved);
	
	/**
	 * The player's hand is out of cards, so they must GoFish
	 * @param deck- deck of cards used for the game
	 * @param numOfCardsRetrieved - int for the # of cards retrieved.
	 */
	void outOfCards(DeckOfCards deck, int numOfCardsRetrieved);
	
	/**
	 * Updates the resultList for HardPlayer class
	 * ONLY USED BY - HardPlayer Subclass!!!!
	 * @param result - the result of the previous Player's turn
	 */
	void updateResultList(Result result);
}
