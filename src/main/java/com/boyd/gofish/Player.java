/**
 * 
 */
package com.boyd.gofish;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.DeckOfCards;

import java.util.*;
import java.util.concurrent.TimeUnit;
/**
 * @author David
 *
 */
public class Player implements PlayerInterface {

	public String name;
	public String ID;
	public int position;
	public boolean repeatTurn;
	
	public ArrayList<Card> hand;
	public HashMap<Rank, Card[]> books;
	public HashMap<Rank, Integer> bookCheck;
	private int playerSpeed;
	
	
	/**
	 * Build the Player object
	 * 
	 * @param initName - a String representing the Player's name
	 * @param initPosition - an int representing the Player's position at the table
	 */
	public Player(String initName, int initPosition, int initGameDelay) {
		name = initName;
		position = initPosition;
		hand = new ArrayList<>();
		books = new HashMap<>();
		bookCheck = new HashMap<>();
		ID = name;
		playerSpeed = initGameDelay;
		
	}

	@Override
	public void setRepeatTurn(boolean repeat) {
		repeatTurn = repeat;
	}
	
	@Override
	public void gameDelay(int i) {
		//Add a delay so the game has flow
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch(InterruptedException ex)
		{ Thread.currentThread().interrupt(); }
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getPosition() {
		return position;
	}
	
	public String getID() {
		return ID;
	}
	
	@Override
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	@Override
	public Set<Rank> getBooks(){
		return books.keySet();
	}
	
	@Override
	public HashMap<Rank, Integer> getBookCheck() {
		return bookCheck;
	}
	
	@Override
	public int getNumOfBooks() {
		return books.size();
	}
	
	@Override
	public boolean hasRank(Rank rank) {
		for (Card card : hand) {
			if (card.getRank().equals(rank)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean hasCards() {
		if (hand.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public Rank drawCard(DeckOfCards deck) {
		Card cardDrawn = deck.getTopCard();
		Rank rankDrawn = cardDrawn.getRank();
		hand.add(cardDrawn);
		return rankDrawn;
	}
	
	@Override
	public boolean requestCards(Rank rank, Player player) {
		return player.hasRank(rank);
	}
	
	@Override
	public Card[] getCards(Rank rank, Player player) {
		Card[] requestedCards = new Card[4];
		int count = 0;
		for (Iterator<Card> iterator = player.getHand().iterator(); iterator.hasNext();) {
			Card card = iterator.next();
			if (card.getRank().equals(rank)) {
				requestedCards[count] = card;
				iterator.remove();
				count++;
			}
		}
		// The below updates the bookcheck of the Player losing the card
		player.updateBookCheck(rank, count * -1);
		return requestedCards;
	}
	
	@Override
	public void doInitialBookCheck() {
		//Two variables are initialized on the off chance dealt 4 of one rank
		boolean createBook = false;
		Rank rankToBook = null;

		for (Card card : hand) {
			if (bookCheck.containsKey(card.getRank())) {
				Integer numOfRank = bookCheck.get(card.getRank()) + 1;
				bookCheck.replace(card.getRank(), numOfRank);
				// If dealt 4 remember rank to createBook out of loop;
				if (numOfRank == 4) {
					rankToBook = card.getRank();
					createBook = true;
				}
			} else {
				Integer numOfRank = 1;
				bookCheck.put(card.getRank(), numOfRank);
			}
		}
		// Someone was dealt 4 of one rank
		if (createBook) {
			createBook(rankToBook);
			bookCheck.remove(rankToBook);
		}
	}
	
	@Override
	public void updateBookCheck(Rank rank, int cardCount) {
		//If already has one of those Cards
		if (bookCheck.containsKey(rank)) {
			// Add the cardCounts together
			int oldCardCount = bookCheck.get(rank);
			Integer newCardCount = cardCount + oldCardCount;
			// If person has all 4 of the Rank make the book and remove rank
			if (newCardCount == 4) {
				createBook(rank);
				newCardCount = 0;
				bookCheck.remove(rank);
			} else if (newCardCount == 0) {
				// If they lost all the cards remove the rank from bookcheck
				bookCheck.remove(rank);
			} else {
				// Normal update of the cards
				bookCheck.replace(rank, oldCardCount, newCardCount);
			}	
		} else {
			// They don't have a card of the rank, so add it
			bookCheck.put(rank, cardCount);
		}
	}
	
	@Override
	public void createBook(Rank rank) {
		Card[] book = new Card[4];
		int count = 0;

		for (Iterator<Card> iterator = hand.iterator(); iterator.hasNext();) {
			Card card = iterator.next();
			if (card.getRank().equals(rank)) {
				book[count] = card;
				count++;
				iterator.remove();
			}
		}
		assert(count == 4);
		books.put(rank, book);
	}
	
	
	
	public String toString() {
		return this.ID;
	}
	
	@Override
	public Optional<Result> takeTurn(ArrayList<Player> players, DeckOfCards deck) {
		Optional<Result> result = null;
		return result;
	}
	
	public void outOfCards(DeckOfCards deck) {
		Rank rankDrawn = drawCard(deck);
		repeatTurn = false;
		updateBookCheck(rankDrawn, 1);
		System.out.println(this.getID() + " is out of cards. "
				+ "They had to Go Fish!!!");
		System.out.println("You drew a: " + rankDrawn);
	}
	
	public void GoFish(DeckOfCards deck, Player playerRequested,
			 		   Rank rankRequested) {
		// No cards left in deck
		if (deck.getNumCardsInDeck() == 0 ) {
			gameDelay(playerSpeed);
			System.out.println(this.getID() + " requested a " + 
					rankRequested + " from " + playerRequested);
			gameDelay(playerSpeed);
			System.out.println(playerRequested.getID() +
					" didn't have that card, and there are"
					+ " no cards left in the deck to draw!!!");
			repeatTurn = false;
		// Draw card
		} else {
			Rank rankDrawn = drawCard(deck);
			repeatTurn = false;
			updateBookCheck(rankDrawn, 1);
			gameDelay(playerSpeed);
			System.out.println(this.getID() + " a requested " + 
					rankRequested + " from " + playerRequested);
			gameDelay(playerSpeed);
			System.out.println(playerRequested.getID() +
					" didn't have that card. Go Fish!!!");
		}
	}
	
	public void takeCards(Player playerRequested, Rank rankRequested,
			  					  int numOfCardsRetrieved) {
		Card[] retrievedCards = getCards(rankRequested, playerRequested);
		for (Card card : retrievedCards) {
			if (Objects.nonNull(card)) {
			hand.add(card);
			numOfCardsRetrieved += 1;
			}
		}
		updateBookCheck(rankRequested, numOfCardsRetrieved);
		this.repeatTurn = true;
		gameDelay(playerSpeed);
		System.out.println(this.getID() + " requested a " + rankRequested
				+ " from " + playerRequested);
		gameDelay(playerSpeed);
		System.out.println(this.getID() + " received "
				+ numOfCardsRetrieved + " cards from " + playerRequested);
	}
	
	public void updateResultList(Result result) {}

public class Result {
		
		private final Rank rank;
		private final Player player;
		private final Boolean hasCard;
		
		String rankString;
		String playerString;
		
		public Result(Rank initRank, Player initPlayer, Boolean initHasCard) {
			rank = initRank;
			player = initPlayer;
			hasCard = initHasCard;
			playerString = player.toString();
			rankString = rank.toString();
		}

		public Rank getRank() {
			return rank;
		}
		
		public Player getPlayer() {
			return player;
		}
		
		public Boolean getHasCard() {
			return hasCard;
		}
		
		public String toString() {
			return "(" + rankString + ", " + playerString + 
					", " + hasCard + ")";
		}
		
		@Override
		public int hashCode() {
			return ( rank.hashCode() + player.hashCode() );
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this) return true;
			if (!(o instanceof Result) ) return false;
			Result other = (Result)o;
			boolean result = ( this.rank == other.rank && 
							   this.player == other.player && 
							   this.hasCard == other.hasCard);
			return result;
		}
	}
}
