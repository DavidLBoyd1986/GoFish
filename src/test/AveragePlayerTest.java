package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.DeckOfCards;
import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.Card.Suit;

import GoFish.AveragePlayer;
import GoFish.GoFish;
import GoFish.Player;

public class AveragePlayerTest {

	/*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     * 
     * Player Testing:
     * 
     * Test 1 - testRequestTrackerInitialBookCheck - returns true if requestTracker is updated accurately during initialBookcheck
     * 
     * Test 2 - testUpdateRequestTrackerNewCard - returns true if adding a new card rank to the hand accurately updates requestTracker
     *
     * Test 3 - testUpdateRequestTrackerSameCard - returns true if adding the same rank to the hand won't update requestTracker
     *
     * Test 4 - testUpdateRequestTrackerCreateBook - returns true if the added rank creates a book, the rank is removed from requestTracker
     * 
     * Test 5 - testUpdateRequestTrackerRankTaken - returns true if when someone takes all of your Rank, that rank is removed from RequestList
     * 
     * Test 6 - testUpdateRequestTrackerPlayerListOne - returns true if requestTracker accurately functions:
     * 		creates the Player list, returns the first player, and moves the selected player to the end of the list.
     * 
     * Test 7 - testUpdateRequestTrackerPlayerListTwo - returns true if requestTracker accurately functions:
     * 		returns first player, and moves them to the end of the list for that rank; does this everytime
     * 
     * Test 8 - testRankRequest() - returns the rank you have the most of to be requested (ties don't matter)
     * 
     * Test 9 - testRankRequestTies() - returns the rank you have the most of to be requested (ties don't matter)
     * 
     * Test 10 - testAveragePlayerGame() - returns true if createGameTest() with all average players completes without error.
     */
	
	
	DeckOfCards deck;
	AveragePlayer testPlayer;
	AveragePlayer testPlayer2;
	AveragePlayer testPlayer3;
	AveragePlayer testPlayer4;
	ArrayList<Player> players;
	
	
	@BeforeEach
	public void setup() throws Exception {
		deck = new DeckOfCards();
		GoFish game1 = new GoFish();
		testPlayer = new AveragePlayer("average", 1);
		players = new ArrayList<Player>();
		testPlayer2 = new AveragePlayer("average", 2);
		testPlayer3 = new AveragePlayer("average", 3);
		testPlayer4 = new AveragePlayer("average", 4);
		players.add(testPlayer);
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
	}
	
	@Test //1
	void testRequestTrackerInitialBookCheck() {
		testPlayer.getHand().addAll(Arrays.asList(deck.getCardsRandom(5)));
		testPlayer.doInitialBookCheck();
		assert(testPlayer.bookCheck.keySet().equals(testPlayer.getRequestTracker().keySet()));
	}
	
	@Test //2
	void testUpdateRequestTrackerNewCard() {
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.KING).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.QUEEN).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.JACK).get());
		testPlayer.doInitialBookCheck();
		testPlayer.updateBookCheck(Rank.EIGHT, 1);
		assert(testPlayer.getRequestTracker().containsKey(Rank.EIGHT));
		assert(testPlayer.bookCheck.keySet().equals(testPlayer.getRequestTracker().keySet()));
	}
	
	@Test //3
	void testUpdateRequestTrackerSameCard() {
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.KING).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.QUEEN).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.JACK).get());
		testPlayer.doInitialBookCheck();
		testPlayer.updateBookCheck(Rank.ACE, 1);
		assert(testPlayer.getRequestTracker().containsKey(Rank.ACE));
		assert(testPlayer.bookCheck.keySet().equals(testPlayer.getRequestTracker().keySet()));
	}
	
	@Test //4
	void testUpdateRequestTrackerCreateBook() {
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.HEART, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.KING).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.QUEEN).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.JACK).get());
		testPlayer.doInitialBookCheck();
		testPlayer.getHand().add(deck.getExactCard(Suit.DIAMOND, Rank.ACE).get());
		testPlayer.updateBookCheck(Rank.ACE, 1);
		assert(!testPlayer.getRequestTracker().containsKey(Rank.ACE));
		assert(testPlayer.bookCheck.keySet().equals(testPlayer.getRequestTracker().keySet()));
	}
	
	@Test //5
	void testUpdateRequestTrackerRankTaken() {
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.JACK).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.HEART, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.KING).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.QUEEN).get());
		testPlayer.doInitialBookCheck();
		testPlayer.getHand().remove(0);
		testPlayer.updateBookCheck(Rank.JACK, -1);
		assert(!testPlayer.getRequestTracker().containsKey(Rank.JACK));
		assert(testPlayer.bookCheck.keySet().equals(testPlayer.getRequestTracker().keySet()));
	}
	
	@Test //6
	void testUpdateRequestTrackerPlayerListOne() {
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.JACK).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.HEART, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.KING).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.QUEEN).get());
		testPlayer.doInitialBookCheck();
		Rank rankRequest = Rank.ACE;
		Player playerSelected = testPlayer.playerRequest(players, rankRequest);
		int rankRequestSize = 
				testPlayer.getRequestTracker().get(rankRequest).size();
		// Test it created the player list
		assert(rankRequestSize == 3);
		// Test it moves the select player to the end of the list
		assert(testPlayer.getRequestTracker().get(rankRequest).get(
				rankRequestSize - 1).equals(playerSelected));
		// Test the requestTracker matches bookCheck
		assert(testPlayer.bookCheck.keySet().equals(
				testPlayer.getRequestTracker().keySet()));
	}
	
	@Test //7
	void testUpdateRequestTrackerPlayerListTwo() {
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.JACK).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.HEART, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.KING).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.QUEEN).get());
		testPlayer.doInitialBookCheck();
		Rank rankRequest = Rank.ACE;
		Player playerSelected = testPlayer.playerRequest(players, rankRequest);
		int rankRequestSize = 
				testPlayer.getRequestTracker().get(rankRequest).size();
		// Test it created the player list
		assert(rankRequestSize == 3);
		// Test it moves the select player to the end of the list
		assert(testPlayer.getRequestTracker().get(rankRequest).get(
				rankRequestSize - 1).equals(playerSelected));
		// Test the requestTracker matches bookCheck
		assert(testPlayer.bookCheck.keySet().equals(
				testPlayer.getRequestTracker().keySet()));
		
		// A call to an existing requestTracker list functions accurately
		playerSelected = testPlayer.playerRequest(players, rankRequest);
		// Test it created the player list
		assert(rankRequestSize == 3);
		// Test it moves the select player to the end of the list
		assert(testPlayer.getRequestTracker().get(rankRequest).get(
				rankRequestSize - 1).equals(playerSelected));
		// Test the requestTracker matches bookCheck
		assert(testPlayer.bookCheck.keySet().equals(
				testPlayer.getRequestTracker().keySet()));
		
		// A 2nd call to an existing requestTracker list functions accurately
		playerSelected = testPlayer.playerRequest(players, rankRequest);
		// Test it created the player list
		assert(rankRequestSize == 3);
		// Test it moves the select player to the end of the list
		assert(testPlayer.getRequestTracker().get(rankRequest).get(
				rankRequestSize - 1).equals(playerSelected));
		// Test the requestTracker matches bookCheck
		assert(testPlayer.bookCheck.keySet().equals(
				testPlayer.getRequestTracker().keySet()));
	}
	
	
	@Test //8
	void testRankRequest() {
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.KING).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.QUEEN).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.JACK).get());
		testPlayer.doInitialBookCheck();
		Rank rankRequest = testPlayer.rankRequest();
		assert(rankRequest.equals(Rank.ACE));
	}
	
	@Test //9
	void testRankRequestTies() {
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.KING).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.SPADE, Rank.KING).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.QUEEN).get());
		testPlayer.getHand().add(deck.getExactCard(Suit.CLUB, Rank.JACK).get());
		testPlayer.doInitialBookCheck();
		Rank rankRequest = testPlayer.rankRequest();
		assert(rankRequest.equals(Rank.ACE) || rankRequest.equals(Rank.KING));
	}
	
	@Test //10
	void testAveragePlayerGame() {
		GoFish gameTest = new GoFish();
		gameTest.createGameTest();
	}
}
