package GoFish;

import java.util.ArrayList;
import java.util.Random;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.Card.Rank;

public class EasyPlayer extends Player implements PlayerInterface {

	public EasyPlayer(String initName, int initPosition) {
		super(initName, initPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void takeTurn() {

		Random random = new Random();
		boolean repeatTurn = false;
		int numOfCardsRetrieved = 0;
		
		//Get the rank you will request
		Card cardRequested = hand.get(random.nextInt(hand.size()));
		Rank rankRequested = cardRequested.getRank();
		//Get the player you will make the request to
		ArrayList<Player> players = GoFish.players;
		Player playerRequested = players.get(random.nextInt(players.size()));

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
	
}