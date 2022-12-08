package com.boyd.gofish;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.DeckOfCards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

public class EasyPlayer extends Player implements PlayerInterface {

	public EasyPlayer(String initName, int initPosition, int initGameDelay) {
		super(initName, initPosition, initGameDelay);
		name = initName;
		position = initPosition;
		hand = new ArrayList<>();
		books = new HashMap<>();
		bookCheck = new HashMap<>();
		ID = name;
	}

	public Result getRequests(ArrayList<Player> players) {
		Random random = new Random();
		//Get the rank you will request
		Card cardRequested = hand.get(random.nextInt(hand.size()));
		Rank rankRequested = cardRequested.getRank();
		//Get the player you will make the request to.
		//Need to create new list without this Player
		ArrayList<Player> playerList = new ArrayList<>();
		for (Player player : players) {
			if (!player.getID().equals(this.getID())) {
				playerList.add(player);
			}
		}
		Player playerRequested =
			playerList.get(random.nextInt(playerList.size()));
		return new Result(rankRequested, playerRequested, false);
	}
	
	@Override
	public Optional<Result> takeTurn(ArrayList<Player> players, DeckOfCards deck) {

		this.repeatTurn = false;
		int numOfCardsRetrieved = 0;
		Optional<Result> result = Optional.empty();
		//If hand is empty, then can't request card, have to GoFish!!
		if (this.getHand().size() == 0) {
			outOfCards(deck);
			return result;
		}
		//Decide Rank and Player for Requests, Result is used to return these.
		Result requestResult = getRequests(players);
		Rank rankRequested = requestResult.getRank();
		Player playerRequested = requestResult.getPlayer();
		//Request Card and take cards if player has it, go fish and draw card otherwise
		boolean cardRequest = requestCards(rankRequested, playerRequested);
		//If Player had that Rank take the card(s), else GoFish
		if (cardRequest) {
			takeCards(playerRequested, rankRequested, numOfCardsRetrieved);
			result = Optional.of(new Result(rankRequested, this, cardRequest));
			return result;
		//Go Fish
		} else {
			GoFish(deck, playerRequested, rankRequested);
			result = Optional.of(
					new Result(rankRequested, playerRequested, cardRequest));
			return result;
		}
	}
}
