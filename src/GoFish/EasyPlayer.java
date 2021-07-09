package GoFish;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.Card.Rank;

public class EasyPlayer extends Player implements PlayerInterface {

	public EasyPlayer(String initName, int initPosition) {
		super(initName, initPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void takeTurn(ArrayList<Player> players) {

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
			numOfCardsRetrieved = retrievedCards.length;
			for (Card card : retrievedCards) {
				if (Objects.nonNull(card)) {
				hand.add(card);
				}
			}
			this.repeatTurn = true;
		} else {
			drawCard(GoFish.deck);
			numOfCardsRetrieved = 1;
			repeatTurn = false;
		}
		//Update BookCheck
		updateBookCheck(rankRequested, numOfCardsRetrieved);
	}
	
}
