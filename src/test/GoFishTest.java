package test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.DeckOfCards;
import com.boyd.deckofcards.Card.Rank;

import GoFish.GoFish;
import GoFish.Player;

public class GoFishTest {



	/*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     * 
     * Player Testing:
     * 
     * Test 1 - testAddPlayers() returns True if addPlayers adds the right numOfPlayers
     * 
     * Test 2 - testGetPlayers() returns True if getPlayers returns an accurate playerList
     * 
     * Test 3 - testSetNumOfPlayers() returns True if setNumOfPlayers responds correctly to input
     * 
     * Test 4 - testDealCardsTwoAndThree() - returns True if dealCards deals 7 cards to each Player when 2 or 3 people are playing
     * 
     * Test 5 - testDealCardsFourPlus() - returns True if dealCards deals 5 cards to each Player when 4 or more people are playing
     * 
     */
	

	Player testPlayer1;
	Player testPlayer2;
	Player testPlayer3;
	Player testPlayer4;
	ArrayList<Player> testPlayers;

	HashMap<Rank, Integer> bookCheck;
	HashMap<Rank, Card[]> books;
	
	@BeforeEach
	public void setup() throws Exception {
		testPlayer1 = new Player("Test", 1);
		testPlayer2 = new Player("Test", 2);
		testPlayer3 = new Player("Test", 3);
		testPlayer4 = new Player("Test", 4);
		testPlayers = new ArrayList<Player>();
		testPlayers.add(testPlayer1);
		testPlayers.add(testPlayer2);
		testPlayers.add(testPlayer3);
		testPlayers.add(testPlayer4);
	}
	
	@Test //Test 1
	void testAddPlayers() {
		GoFish game1 = new GoFish();
		for (Player player : testPlayers) {
			game1.addPlayer(player);
		}
		assert(game1.players.size() == 4);
	}
	
	@Test //Test 2
	void testGetPlayers() {
		GoFish game1 = new GoFish();
		for (Player player : testPlayers) {
			game1.addPlayer(player);
		}
		assert(game1.getPlayers().size() == 4);
	}
	
	// Below works, I just can't figure out how to automatically set input to an int
//	@Test //Test 3
//	void testSetNumOfPlayers() {
//		GoFish game1 = new GoFish();
//		//Integer testInput = 4;
//		Scanner inputNumOfPlayers = new Scanner(System.in);
//		game1.setNumOfPlayers(inputNumOfPlayers);
//		assert((game1.getNumOfPlayers() >= 2) && (game1.getNumOfPlayers() <= 7));
//	}
	
	@Test //Test 4
	void testDealCardsTwoAndThree() {
		GoFish game1 = new GoFish();
		for (Player player : testPlayers) {
			game1.addPlayer(player);
		}
		game1.players.remove(1);
		game1.numOfPlayers = 3;
		assert(game1.getPlayers().size() == 3);
		testPlayers = game1.dealCards(testPlayers);
		assert(testPlayers.get(1).getHand().size() == 7);
	}
	
	@Test //Test 4
	void testDealCardsFourPlus() {
		GoFish game1 = new GoFish();
		for (Player player : testPlayers) {
			game1.addPlayer(player);
		}
		game1.numOfPlayers = 4;
		assert(game1.getPlayers().size() == 4);
		testPlayers = game1.dealCards(testPlayers);
		assert(testPlayers.get(1).getHand().size() == 5);
	}
}
