package GoFish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.DeckOfCards;
import com.boyd.deckofcards.Card.Rank;

public class InteractivePlayer extends Player implements PlayerInterface {

	Scanner inputStream;
	
	public InteractivePlayer(String initName, int initPosition, Scanner initInputStream) {
		super(initName, initPosition);
		name = initName;
		position = initPosition;
		hand = new ArrayList<Card>();
		books = new HashMap<Rank, Card[]>();
		bookCheck = new HashMap<Rank, Integer>();
		ID = name;
		inputStream = initInputStream;
	}
	
	@Override
	public Optional<Result> takeTurn(ArrayList<Player> players, DeckOfCards deck) {
		//These are declared here because the actual initialization is in a try clause, and would create an error.
		Rank rankRequested = null;
		Player playerRequested = null;
		int numOfCardsRetrieved = 0;
		Optional<Result> result = Optional.empty();
		//If hand is empty can't request card, try to GoFish!!
		if (this.getHand().size() == 0) {
			if (deck.getNumCardsInDeck() == 0 ) {
				gameDelay(1);
				System.out.println("You are out of cards and the deck is empty. "
						+ "Turn passed!!!!");
				System.out.println("THIS SECTION SHOULD BE UNREACHABLE!!!!");
				repeatTurn = false;
				return result;
			} else {
				Rank rankDrawn = drawCard(deck);
				numOfCardsRetrieved = 1;
				repeatTurn = false;
				updateBookCheck(rankDrawn, numOfCardsRetrieved);
				gameDelay(1);
				System.out.println("You are out of cards and have to Go Fish!");
				gameDelay(1);
				System.out.println("You drew a: " + rankDrawn);
				return result;
			}
		}
		//Get the rank you will request
		try {
			rankRequested = getRankSelection(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error taking turn while running getRankSelection()");			
		}
		//Get the player you will make the request to
		try {
			playerRequested = getPlayerSelection(inputStream, players);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error taking turn while running getPlayerSelection()");
		}
		//Request Card from another Player
		boolean cardRequest = requestCards(rankRequested, playerRequested);
		// Create a Result to return
		if (cardRequest) {
			result = Optional.of(
					new Result(rankRequested, this, cardRequest));}
		if (!cardRequest) {
			result = Optional.of(
					new Result(rankRequested, playerRequested, cardRequest));
		}
		//If Player had that Rank take the card(s), else GoFish
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
			gameDelay(1);
			System.out.println(playerRequested.getID() +
					" had that card. Received " + numOfCardsRetrieved
					+ " " + rankRequested + "'s");
			return result;
		//Go Fish
		} else {
			GoFish(deck, playerRequested, rankRequested, numOfCardsRetrieved);
			return result;
		}
	}
	
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
}
