package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.boyd.deckofcards.DeckOfCards;
import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.Card.Suit;

import GoFish.AveragePlayer;
import GoFish.GoFish;
import GoFish.HardPlayer;
import GoFish.Player;
import GoFish.Player.Result;

public class HardPlayerTest {

	/*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     * 
     * Player Testing:
     * 
     * Test 1 - testRequestFromResults - returns true if requestFromResults return the correct choice
     * 
     */
	
	
	DeckOfCards deck;
	HardPlayer testPlayer;
	HardPlayer testPlayer2;
	HardPlayer testPlayer3;
	HardPlayer testPlayer4;
	ArrayList<Player> players;
	
	
	@BeforeEach
	public void setup() throws Exception {
		deck = new DeckOfCards();
		GoFish game1 = new GoFish();
		testPlayer = new HardPlayer("hard", 1);
		players = new ArrayList<Player>();
		testPlayer2 = new HardPlayer("hard", 2);
		testPlayer3 = new HardPlayer("hard", 3);
		testPlayer4 = new HardPlayer("hard", 4);
		players.add(testPlayer);
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
	}
	
	@Test //1
	void testrequestFromResults() {
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.KING).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.QUEEN).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.JACK).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.TEN).get());
		
		testPlayer2.getHand().add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer2.getHand().add(deck.getExactCard(Suit.SPADE, Rank.KING).get());
		testPlayer2.getHand().add(deck.getExactCard(Suit.SPADE, Rank.QUEEN).get());
		testPlayer2.getHand().add(deck.getExactCard(Suit.SPADE, Rank.JACK).get());
		testPlayer2.getHand().add(deck.getExactCard(Suit.SPADE, Rank.TEN).get());
		
		testPlayer3.getHand().add(deck.getExactCard(Suit.HEART, Rank.ACE).get());
		testPlayer3.getHand().add(deck.getExactCard(Suit.HEART, Rank.KING).get());
		testPlayer3.getHand().add(deck.getExactCard(Suit.HEART, Rank.QUEEN).get());
		testPlayer3.getHand().add(deck.getExactCard(Suit.HEART, Rank.JACK).get());
		testPlayer3.getHand().add(deck.getExactCard(Suit.HEART, Rank.TEN).get());
		
		testPlayer.doInitialBookCheck();
		testPlayer2.doInitialBookCheck();
		testPlayer3.doInitialBookCheck();
		
		Optional<Result> requestAceResult = testPlayer.requestFromResults();
		assert(testPlayer.bookCheck.keySet().equals(testPlayer.getRequestTracker().keySet()));
	}
}
