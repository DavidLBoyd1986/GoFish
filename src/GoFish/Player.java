/**
 * 
 */
package GoFish;
//import GoFish.GoFish;
import com.boyd.deckofcards.*;
import com.boyd.deckofcards.Card.Rank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

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
		ID = name + position;
		
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
			} else if (newCardCount.intValue() == 0) {
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
	
	@Override
	public void takeTurn(ArrayList<Player> players, DeckOfCards deck) {
		//These are declared here because the actual initialization is in a try clause, and would create an error.
		Rank rankRequested = null;
		Player playerRequested = null;
		int numOfCardsRetrieved = 0;
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());

		//Get the rank you will request
		try {
			rankRequested = getRankSelection(inputScanner);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error taking turn while running getRankSelection()");			
		}
		//Get the player you will make the request to
		try {
			playerRequested = getPlayerSelection(inputScanner, players);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error taking turn while running getPlayerSelection()");
		}
		//Request Card and take cards if player has it, go fish and draw card otherwise
		boolean cardRequest = requestCards(rankRequested, playerRequested);
		if (cardRequest) {
			Card[] retrievedCards = getCards(rankRequested, playerRequested);
			for (Card card : retrievedCards) {
				if (Objects.nonNull(card)) {
				hand.add(card);
				numOfCardsRetrieved += 1;
				}
			}
			repeatTurn = true;
			updateBookCheck(rankRequested, numOfCardsRetrieved);
			System.out.println(playerRequested.getID() +
					" had that card. Received " + numOfCardsRetrieved
					+ " " + rankRequested + "'s");
		} else {
			Rank rankDrawn = drawCard(deck);
			numOfCardsRetrieved = 1;
			repeatTurn = false;
			updateBookCheck(rankDrawn, numOfCardsRetrieved);
			System.out.println(playerRequested.getID() +
					" didn't have that card. Go Fish!!!");
			System.out.println("You drew a: " + rankDrawn);
		}
	}
	
	// This function will only be for testing on command line. It will be replaced once I build a GUI
	@Override
	public Rank getRankSelection(Scanner inputScanner) {
		System.out.println("Please enter what Rank of card you have in your"
				+ " hand want to request from another Player: ");
		
		//Print bookcheck so User can select a Rank already held in hand
		System.out.println(this.getBookCheck());
		System.out.println("Enter Rank: ");
		//variable that tests if input is valid
		boolean inputValid = false;
		//Set up Rank variable
		Rank rank = null;
		//Create list of Rank Strings from Cards held to verify if input's valid
		ArrayList<String> rankCheck = new ArrayList<String>();
		for (Card card : this.hand) {
			rankCheck.add(card.getRank().toString());
		}
		
		//Loop until input is valid
		while (!inputValid) {
			//System.out.println(inputScanner.next());
			String rankRequest = inputScanner.next();
			rankRequest = rankRequest.toUpperCase();
			rankRequest = rankRequest.trim();
			// check it's a valid Rank before changing input String to Rank
			if (rankCheck.contains(rankRequest)) {
					rank = Rank.valueOf(rankRequest);
					inputValid = true;
			} else {
				System.out.println("Invalid Rank Selection. Try Again....");
			}
		}
		return rank;
	}
	
	public Player getPlayerSelection(Scanner inputScanner, ArrayList<Player> players) {

		System.out.println("Please select which player the request will be made to: ");
		// Create list of Player ID Strings to check if input is valid
		ArrayList<String> playerIDs = new ArrayList<String>();
		for (Player player : players) {
			if (player.getID() != this.ID) {
				playerIDs.add(player.getID().toLowerCase());
			}
		}
		System.out.println("Enter Player to request Rank from: ");
		System.out.println(playerIDs);
		// Set up variables
		boolean inputValid = false;
		Player returnedPlayer = null;
		//Input validation loop
		while (!inputValid) {
			String playerRequest = inputScanner.next();
			playerRequest = playerRequest.toLowerCase();
			playerRequest = playerRequest.trim();
			if (playerIDs.contains(playerRequest)) {
				//Still have to loop through Players to get the one requested.
				for (Player player : players) {
					String playerString = player.getID();
					playerString = playerString.toLowerCase();
					if (playerString.equals(playerRequest)) {
						returnedPlayer = player;
					}
				}
				inputValid = true;
			} else {
				System.out.println("Invalid Player Selection. Try Again....");
			}		
		}
		// Return Player selected
		return returnedPlayer;
	}
	
	public String toString() {
		return this.ID;
	}
	
}
