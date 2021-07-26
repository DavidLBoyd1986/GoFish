package GoFish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.Card.Rank;

import com.boyd.deckofcards.DeckOfCards;

public class EasyPlayer extends Player implements PlayerInterface {

	public EasyPlayer(String initDifficulty, int initPosition) {
		super(initDifficulty, initPosition);
		String difficulty = initDifficulty;
		name = GoFish.getPlayerName(difficulty);
		position = initPosition;
		hand = new ArrayList<Card>();
		books = new HashMap<Rank, Card[]>();
		bookCheck = new HashMap<Rank, Integer>();
		ID = name;
	}

	@Override
	public Optional<Result> takeTurn(ArrayList<Player> players, DeckOfCards deck, Scanner inputStream) {

		Random random = new Random();
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
		//Get the rank you will request
		Card cardRequested = hand.get(random.nextInt(hand.size()));
		Rank rankRequested = cardRequested.getRank();
		//Get the player you will make the request to.
		//Need to create new list without this Player
		ArrayList<Player> playerList = new ArrayList<>();
		for (Player player : players) {
			if (player.getID() != this.getID()) {
				playerList.add(player);
			}
		}
		Player playerRequested = playerList.get(random.nextInt(playerList.size()));

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
}
