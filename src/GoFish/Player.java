/**
 * 
 */
package GoFish;
import GoFish.GoFish;
import com.boyd.deckofcards.*;
import com.boyd.deckofcards.Card.Rank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author David
 *
 */
public class Player implements PlayerInterface {

	public String name;
	public int position;
	public boolean repeatTurn;
	
	public ArrayList<Card> hand;
	public HashMap<Rank, Card[]> books;
	public HashMap<Rank, Integer> bookCheck;
	
	
	/**
	 * Build the Player object
	 * 
	 * @param initName - a String representing the Player's name
	 * @param initPosition - an int representing the Player's position at the table
	 */
	public Player(String initName, int initPosition) {
		name = initName;
		position = initPosition;
		hand = new ArrayList<Card>();
		books = new HashMap<Rank, Card[]>();
		bookCheck = new HashMap<Rank, Integer>();
		
	}

	@Override
	public void setRepeatTurn(boolean repeat) {
		repeatTurn = repeat;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getPosition() {
		return position;
	}
	
	@Override
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	@Override
	public int getNumOfBooks() {
		int numOfBooks = books.size();
		return numOfBooks;
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
		}
		return true;
	}
	
	@Override
	public Card drawCard(DeckOfCards deck) {
		return deck.getTopCard();
	}
	
	@Override
	public boolean requestCards(Rank rank, Player player) {
		return player.hasRank(rank);
	}
	
	@Override
	public Card[] getCards(Rank rank, Player player) {
		Card[] requestedCards = new Card[4];
		int count = 0;
		for (Card card : player.getHand()) {
			if (card.getRank().equals(rank)) {
				requestedCards[count] = card;
				player.getHand().remove(card);
				count++;
			}
		}
		player.updateBookCheck(rank, requestedCards.length * -1);
		return requestedCards;
	}
	
	@Override
	public void doInitialBookCheck() {
		for (Card card : hand) {
			if (bookCheck.containsKey(card.getRank())) {
				// Create a new Integer that is +1 of the previous value
				Integer numOfRank = new Integer(bookCheck.get(card.getRank()).intValue() + 1);
				bookCheck.replace(card.getRank(), numOfRank);
			} else {
				Integer numOfRank = new Integer(1);
				bookCheck.put(card.getRank(), numOfRank);
			}
		}
	}
	
	@Override
	public void updateBookCheck(Rank rank, int cardCount) {
		if (bookCheck.containsKey(rank)) {
			// Add the cardCounts together
			int oldCardCount = bookCheck.get(rank).intValue();
			Integer newCardCount = new Integer(cardCount + oldCardCount);
			// If person has all 4 of the Rank make the book and set bookCheck to 0
			if (newCardCount.intValue() == 4) {
				createBook(rank);
				newCardCount = new Integer(0);
				bookCheck.replace(rank, oldCardCount, newCardCount);
			} else {
				bookCheck.replace(rank, oldCardCount, newCardCount);
			}	
		} else {
			bookCheck.put(rank, cardCount);
		}
	}
	
	@Override
	public void createBook(Rank rank) {
		Card[] book = new Card[4];
		int count = 0;
		for (Card card : hand) {
			if (card.getRank().equals(rank)) {
				book[count] = card;
				count++;
			}
		}
		assert(book.length == 4);
		books.put(rank, book);
	}
	
	@Override
	public void takeTurn() {
		//These are declared here because the actual initialization is in a try clause, and would create and error.
		Rank rankRequested = null;
		Player playerRequested = null;
		int numOfCardsRetrieved = 0;
		
		//Get the rank you will request
		try {
			rankRequested = getRankSelection();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error taking turn while running getRankSelection()");			
		}
		//Get the player you will make the request to
		try {
			playerRequested = getPlayerSelection();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error taking turn while running getPlayerSelection()");
		}
		//Request Card and take cards if player has it, go fish and draw card otherwise
		boolean cardRequest = requestCards(rankRequested, playerRequested);
		if (cardRequest) {
			Card[] retrievedCards = getCards(rankRequested, playerRequested);
			numOfCardsRetrieved = retrievedCards.length;
			for (Card card : retrievedCards) {
				hand.add(card);
			repeatTurn = true;
			}
		} else {
			Card drawnCard = drawCard(GoFish.deck);
			numOfCardsRetrieved = 1;
			hand.add(drawnCard);
			repeatTurn = false;
		}
		//Update BookCheck
		updateBookCheck(rankRequested, numOfCardsRetrieved);
	}
	
	// This function will only be for testing on command line. It will be replaced once I build a GUI
	@Override
	public Rank getRankSelection() throws Exception{
		System.out.println("Please enter what Rank you want to ask a Player for based on the following list:");
		ArrayList<Rank> rankList = new ArrayList<Rank>();
		for (Card card : hand) {
			if (!rankList.contains(card.getRank())) {
				rankList.add(card.getRank());
			}
		}
		for (Rank rank : rankList) {
			System.out.println(rank);
		}
		// TODO update exception for User input
		try {
		Scanner in = new Scanner(System.in);
		Rank rank = Rank.valueOf(in.next(name));
		in.close();
		return rank;
		} catch(Exception e) {
			e.printStackTrace();
		}
		// TODO update the thrown Exception
		throw new Exception("ERROR - No rank was returned with getRankSelection()");
	}
	
	/**
	 * method used to get the Player the request is made to
	 * @return - the Player the request is made to
	 */
	public Player getPlayerSelection() throws Exception{
		ArrayList<Player> players = GoFish.players;
		String playerRequest = "";
		System.out.print("Please select which player the request will be made to:");
		// Player toString will be outputted
		for (Player player : players) {
			System.out.println(player);
		}
		// The user input will be a String that matches Player toString output (Case insensitive)
		// TODO update Exception for User input
		try {
		Scanner in = new Scanner(System.in);
		playerRequest = in.next();
		in.close();
		} catch(Exception e ) {
			e.printStackTrace();
		}
		playerRequest.toLowerCase();
		for (Player player : players) {
			String playerString = player.toString();
			playerString.toLowerCase();
			if (playerString.equals(playerRequest)) {
				return player;
			}
		}
		// TODO update the thrown Exception
		throw new Exception("ERROR - No Player was returned in getPlayerSelection()");
	}
	
	
}
