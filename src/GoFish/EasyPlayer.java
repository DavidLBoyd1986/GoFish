package GoFish;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.DeckOfCards;

public class EasyPlayer extends Player implements PlayerInterface {

	public EasyPlayer(String initName, int initPosition) {
		super(initName, initPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void takeTurn(ArrayList<Player> players, DeckOfCards deck) {

		Random random = new Random();
		this.repeatTurn = false;
		int numOfCardsRetrieved = 0;
		
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
			System.out.println(this.getID() + " requested " + rankRequested
					+ " from " + playerRequested + " and received "
					+ numOfCardsRetrieved + " cards.");
		} else {
			if (deck.getNumCardsInDeck() == 0 ) {
				System.out.println(playerRequested.getID() +
						" didn't have that card, but there are"
						+ " no cards left in the deck to draw!!!");
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

	}
	
}
