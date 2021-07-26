package GoFish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.DeckOfCards;
import com.boyd.deckofcards.Card.Rank;


public class AveragePlayer extends Player implements PlayerInterface {

	private HashMap<Rank, ArrayList<Player>> requestTracker;
	
	public AveragePlayer(String initDifficulty, int initPosition) {
		super(initDifficulty, initPosition);
		String difficulty = initDifficulty;
		name = GoFish.getPlayerName(difficulty);
		position = initPosition;
		hand = new ArrayList<Card>();
		books = new HashMap<Rank, Card[]>();
		bookCheck = new HashMap<Rank, Integer>();
		ID = name;
		requestTracker = new HashMap<Rank, ArrayList<Player>>();
	}
	
	public HashMap<Rank, ArrayList<Player>> getRequestTracker() {
		return requestTracker;
	}
	
	@Override
	public void doInitialBookCheck() {
		//Two variables are initialized on the off chance dealt 4 of one rank
		boolean createBook = false;
		Rank rankToBook = null;

		for (Card card : hand) {
			if (bookCheck.containsKey(card.getRank())) {
				Integer numOfRank = new Integer(
						bookCheck.get(card.getRank()).intValue() + 1);
				bookCheck.replace(card.getRank(), numOfRank);
				// If dealt 4 remember rank to createBook out of loop;
				if (numOfRank.intValue() == 4) {
					rankToBook = card.getRank();
					createBook = true;
				}
			} else {
				Integer numOfRank = new Integer(1);
				bookCheck.put(card.getRank(), numOfRank);
				updateRequestTracker(card.getRank());
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
			int oldCardCount = bookCheck.get(rank).intValue();
			Integer newCardCount = new Integer(cardCount + oldCardCount);
			// If person has all 4 of the Rank make the book and remove rank
			if (newCardCount.intValue() == 4) {
				createBook(rank);
				newCardCount = new Integer(0);
				bookCheck.remove(rank);
				updateRequestTracker(rank);
			} else if (newCardCount.intValue() == 0) {
				// If they lost all the cards remove the rank from bookcheck
				bookCheck.remove(rank);
				updateRequestTracker(rank);
			} else {
				// Normal update of the cards
				bookCheck.replace(rank, oldCardCount, newCardCount);
			}	
		} else {
			// They don't have a card of the rank, so add it
			bookCheck.put(rank, cardCount);
			updateRequestTracker(rank);
		}
	}
	
	public void updateRequestTracker(Rank rank) {
		// If rank is in HashMap remove it, if it isn't in HashMap add it
		if (!requestTracker.containsKey(rank)) {
			requestTracker.put(rank, new ArrayList<Player>());
		} else {requestTracker.remove(rank);}
	}
	
	public Rank rankRequest() {
		Rank rankRequest = null;
		int numOfRank = 0;
		for (Rank rank : bookCheck.keySet()) {
			if (bookCheck.get(rank) > numOfRank) {
				rankRequest = rank;
				numOfRank = bookCheck.get(rank);
			}
		}
		return rankRequest;
	}
	
	public Player playerRequest(ArrayList<Player> players, Rank rankRequest) {
		Player playerRequest;
		// If first time requesting Rank add Players to the requestTracker
		if (requestTracker.get(rankRequest).size() == 0) {
			for (Player player : players) {
				if (!player.equals(this)) {
					requestTracker.get(rankRequest).add(player);
				}
			}
			//Get the first Player, then move him to the end of the list
			playerRequest = requestTracker.get(rankRequest).get(0);
			requestTracker.get(rankRequest).remove(0);
			requestTracker.get(rankRequest).add(playerRequest);
		} else {
			//Get the first Player, then move him to the end of the list
			playerRequest = requestTracker.get(rankRequest).get(0);
			requestTracker.get(rankRequest).remove(0);
			requestTracker.get(rankRequest).add(playerRequest);
		}
		return playerRequest;
	}
	
	@Override
	public Optional<Result> takeTurn(ArrayList<Player> players, DeckOfCards deck, Scanner inputStream) {

		this.repeatTurn = false;
		int numOfCardsRetrieved = 0;
		Optional<Result> result = Optional.empty();
		//If hand is empty can't request card, try to GoFish!!
		if (this.getHand().size() == 0) {
			if (deck.getNumCardsInDeck() == 0 ) {
				System.out.println(this.getID() +
						" is out of cards and the deck is empty. "
						+ "Turn passed.");
				System.out.println("THIS SECTION SHOULD BE UNREACHABLE!!!!");
				repeatTurn = false;
				return result;
			} else {
				Rank rankDrawn = drawCard(deck);
				numOfCardsRetrieved = 1;
				repeatTurn = false;
				updateBookCheck(rankDrawn, numOfCardsRetrieved);
				System.out.println(this.getID() + " is out of cards. "
						+ "They had to Go Fish!!!");
				System.out.println("You drew a: " + rankDrawn);
				return result;
			}
		}
		//Get the rank and player you will request
		Rank rankRequested = rankRequest();
		Player playerRequested = playerRequest(players, rankRequested);

		//Request Card and take cards if player has it, go fish and draw card otherwise
		boolean cardRequest = requestCards(rankRequested, playerRequested);
		// Create a Result to return
		if (cardRequest) {
			result = Optional.of(
					new Result(rankRequested, this, cardRequest));}
		if (!cardRequest) {
			result = Optional.of(
					new Result(rankRequested, playerRequested, cardRequest));
		}
		if (cardRequest) {
			Card[] retrievedCards = getCards(rankRequested, playerRequested);
			for (Card card : retrievedCards) {
				if (Objects.nonNull(card)) {
				hand.add(card);
				numOfCardsRetrieved += 1;
				}
			}
			updateBookCheck(rankRequested, numOfCardsRetrieved);
			this.repeatTurn = true;
			gameDelay(2);
			System.out.println(this.getID() + " requested a " + rankRequested
					+ " from " + playerRequested);
			gameDelay(2);
			System.out.println(this.getID() + " received "
					+ numOfCardsRetrieved + " cards from " + playerRequested);
			return result;
		//Go Fish
		} else {
			// No cards left in deck
			if (deck.getNumCardsInDeck() == 0 ) {
				gameDelay(2);
				System.out.println(this.getID() + " requested a " + 
						rankRequested + " from " + playerRequested);
				gameDelay(2);
				System.out.println(playerRequested.getID() +
						" didn't have that card, and there are"
						+ " no cards left in the deck to draw!!!");
				repeatTurn = false;
				return result;
			// Draw card
			} else {
				Rank rankDrawn = drawCard(deck);
				numOfCardsRetrieved = 1;
				repeatTurn = false;
				updateBookCheck(rankDrawn, numOfCardsRetrieved);
				gameDelay(2);
				System.out.println(this.getID() + " a requested " + 
						rankRequested + " from " + playerRequested);
				gameDelay(2);
				System.out.println(playerRequested.getID() +
						" didn't have that card. Go Fish!!!");
				return result;
			}
		}
	}
	
public class RequestPair {
		
		public final Rank rank;
		public final Player player;
		
		String rankString;
		String playerString;

		
		public RequestPair(Rank initRank, Player initPlayer) {
			rank = initRank;
			player = initPlayer;
			playerString = player.toString();
			rankString = rank.toString();
		}

		public Rank getRank() {
			return rank;
		}
		
		public Player getPlayer() {
			return player;
		}
		
		public String toString() {
			return "(" + rankString + ", " + playerString + ")";
		}
		
		@Override
		public int hashCode() {
			return ( rank.hashCode() + player.hashCode() );
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this) return true;
			if (!(o instanceof RequestPair) ) return false;
			RequestPair other = (RequestPair)o;
			boolean requestPair = ( this.rank == other.rank && this.player == other.player );
			return requestPair;
		}
	}
	
	
}
