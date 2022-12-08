package test;

import com.boyd.gofish.*;
import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.Card.Suit;
import com.boyd.deckofcards.DeckOfCards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class InteractivePlayerTest {

	/**
    * Test 30 - testGetRankSelectionValidUpperCase() returns True if getRankSelection() responds correctly to valid inputs
    * 
    * Test 31 - testGetRankSelectionValidLowerCase() returns True if getRankSelection() responds correctly to valid inputs
    * 
    * Test 32 - testGetRankSelectionValidMixedCase() returns True if getRankSelection() responds correctly to valid inputs
    * 
    * Test 33 - testGetRankSelectionInvalidEmpty() returns True if getRankSelection() responds correctly to invalid inputs
    * 
    * Test 34 - testGetRankSelectionInvalidString() returns True if getRankSelection() responds correctly to invalid inputs
    * 
    * Test 35 - testGetRankSelectionInvalidWhiteSpace() returns True if getRankSelection() responds correctly to invalid inputs
    * 
    * Test 36 - testGetRankSelectionInvalidCharacters() returns True if getRankSelection() responds correctly to invalid inputs
    * 
    * Test 37 - testGetRankSelectionInvalidMultipleRanks() returns True if getRankSelection() responds correctly to invalid inputs
    * 
    * Test 38 - testGetRankSelectionInvalidManyMistakes() returns True if getRankSelection() responds correctly to invalid inputs
    * 
    * Test 39 - testGetPlayerSelectionValidUpperCase() returns True if getPlayerSelection() responds correctly to valid inputs
    * 
    * Test 40 - testGetPlayerSelectionValidLowerCase() returns True if getPlayerSelection() responds correctly to valid inputs
    * 
    * Test 41 - testGetPlayerSelectionValidMixedCase() returns True if getPlayerSelection() responds correctly to valid inputs
    * 
    * Test 42 - testGetPlayerSelectionInvalidEmpty() returns True if getPlayerSelection() responds correctly to invalid inputs
    * 
    * Test 43 - testGetPlayerSelectionInvalidString() returns True if getPlayerSelection() responds correctly to invalid inputs
    * 
    * Test 44 - testGetPlayerSelectionInvalidWhiteSpace() returns True if getPlayerSelection() responds correctly to invalid inputs
    * 
    * Test 45 - testGetPlayerSelectionInvalidCharacters() returns True if getPlayerSelection() responds correctly to invalid inputs
    * 
    * Test 46 - testGetPlayerSelectionInvalidMultiplePeople() returns True if getPlayerSelection() responds correctly to invalid inputs
    * 
    * Test 47 - testGetPlayerSelectionInvalidManyMistakes() returns True if getPlayerSelection() responds correctly to invalid inputs
	*/
	
	DeckOfCards deck;
	InteractivePlayer testPlayer;
	ArrayList<Card> hand;
	HashMap<Rank, Integer> bookCheck;
	HashMap<Rank, Card[]> books;
	
	@BeforeEach
	public void setup() throws Exception {
		deck = new DeckOfCards();
		Scanner inputStream = new Scanner(System.in);
		testPlayer = new InteractivePlayer("TestPlayer", 1, 0, inputStream);
		hand = new ArrayList<Card>();
		bookCheck = new HashMap<Rank, Integer>();
		testPlayer.hand = hand;
		testPlayer.bookCheck = bookCheck;
	}
	
	@Test //30
	void testGetRankSelectionValidUpperCase() {
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		String testInput = "ACE\r\n";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Rank testInputCapital = testPlayer.getRankSelection(inputScanner);
		assert(testInputCapital instanceof Rank);
		inputScanner.close();
	}
	
	@Test //31
	void testGetRankSelectionValidLowerCase() {
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		String testInput = "ace\r\n";
		Scanner inputScanner = new Scanner(testInput);
		inputScanner.useDelimiter(System.lineSeparator());
		Rank testInputCapital = testPlayer.getRankSelection(inputScanner);
		assert(testInputCapital instanceof Rank);
		inputScanner.close();
	}
	
	@Test //32
	void testGetRankSelectionValidMixedCase() {
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		String testInput = "aCE\r\n";
		Scanner inputScanner = new Scanner(testInput);
		inputScanner.useDelimiter(System.lineSeparator());
		Rank testInputCapital = testPlayer.getRankSelection(inputScanner);
		assert(testInputCapital instanceof Rank);
		inputScanner.close();
	}
	
	@Test //33
	void testGetRankSelectionInvalidEmpty() {
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		String testInput = " \r\n \r\nACE";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Rank testInputCapital = testPlayer.getRankSelection(inputScanner);
		assert(testInputCapital instanceof Rank);
		inputScanner.close();
	}
	
	@Test //34
	void testGetRankSelectionInvalidString() {
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		String testInput = "afdsf\r\nadfdf\r\nACE";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Rank testInputCapital = testPlayer.getRankSelection(inputScanner);
		assert(testInputCapital instanceof Rank);
		inputScanner.close();
	}
	
	@Test //35
	void testGetRankSelectionInvalidWhiteSpace() {
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		String testInput = "afdsf\r\n ad fdf\r\n ACE";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Rank testInputCapital = testPlayer.getRankSelection(inputScanner);
		assert(testInputCapital instanceof Rank);
		inputScanner.close();
	}
	
	@Test //36
	void testGetRankSelectionInvalidCharacters() {
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		String testInput = "a,!f$$f\r\nACE";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Rank testInputCapital = testPlayer.getRankSelection(inputScanner);
		assert(testInputCapital instanceof Rank);
		inputScanner.close();
	}
	
	@Test //37
	void testGetRankSelectionInvalidMultipleRanks() {
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.TWO).get());
		String testInput = "TWOACE\r\nACE";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Rank testInputCapital = testPlayer.getRankSelection(inputScanner);
		assert(testInputCapital instanceof Rank);
		inputScanner.close();
	}
	
	@Test //38
	void testGetRankSelectionInvalidManyMistakes() {
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.TWO).get());
		String testInput = "TWOACE\r\nadff\r\n T WO\r\n !@#$ \r\nACE";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Rank testInputCapital = testPlayer.getRankSelection(inputScanner);
		assert(testInputCapital instanceof Rank);
		assert(testInputCapital == Rank.ACE);
		inputScanner.close();
	}
	
	@Test //39
	void testGetPlayerSelectionValidUpperCase() {
		Player testPlayer2 = new Player("easy", 2, 0);
		Player testPlayer3 = new Player("easy", 3, 0);
		Player testPlayer4 = new Player("easy", 4, 0);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		for (Player player : players) {
			player.ID = "TEST";
		}
		String testInput = "TEST";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Player testPlayerSelection =
				testPlayer.getPlayerSelection(inputScanner, players);
		assert(testPlayerSelection instanceof Player);
		inputScanner.close();
	}
	
	@Test //40
	void testGetPlayerSelectionValidLowerCase() {
		Player testPlayer2 = new Player("easy", 2, 0);
		Player testPlayer3 = new Player("easy", 3, 0);
		Player testPlayer4 = new Player("easy", 4, 0);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		for (Player player : players) {
			player.ID = "TEST";
		}
		String testInput = "test\r\n";
		Scanner inputScanner = new Scanner(testInput);
		inputScanner.useDelimiter(System.lineSeparator());
		Player testInputCapital =
				testPlayer.getPlayerSelection(inputScanner, players);
		assert(testInputCapital instanceof Player);
		inputScanner.close();
	}
	
	@Test //41
	void testGetPlayerSelectionValidMixedCase() {
		Player testPlayer2 = new Player("Test2", 2, 0);
		Player testPlayer3 = new Player("Test3", 3, 0);
		Player testPlayer4 = new Player("Test4", 4, 0);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		for (Player player : players) {
			player.ID = "TEST";
		}
		String testInput = "tESt\r\n";
		Scanner inputScanner = new Scanner(testInput);
		inputScanner.useDelimiter(System.lineSeparator());
		Player testInputCapital =
				testPlayer.getPlayerSelection(inputScanner, players);
		assert(testInputCapital instanceof Player);
		inputScanner.close();
	}
	
	@Test //42
	void testGetPlayerSelectionInvalidEmpty() {
		Player testPlayer2 = new Player("Test2", 2, 0);
		Player testPlayer3 = new Player("Test3", 3, 0);
		Player testPlayer4 = new Player("Test4", 4, 0);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		for (Player player : players) {
			player.ID = "TEST";
		}
		String testInput = " \r\n \r\nTEST";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Player testInputCapital =
				testPlayer.getPlayerSelection(inputScanner, players);
		assert(testInputCapital instanceof Player);
		inputScanner.close();
	}
	
	@Test //43
	void testGetPlayerSelectionInvalidString() {
		Player testPlayer2 = new Player("Test2", 2, 0);
		Player testPlayer3 = new Player("Test3", 3, 0);
		Player testPlayer4 = new Player("Test4", 4, 0);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		for (Player player : players) {
			player.ID = "TEST";
		}
		String testInput = "afdsf\r\nadfdf\r\nTest";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Player testInputCapital =
				testPlayer.getPlayerSelection(inputScanner, players);
		assert(testInputCapital instanceof Player);
		inputScanner.close();
	}
	
	@Test //44
	void testGetPlayerSelectionInvalidWhiteSpace() {
		Player testPlayer2 = new Player("Test2", 2, 0);
		Player testPlayer3 = new Player("Test3", 3, 0);
		Player testPlayer4 = new Player("Test4", 4, 0);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		for (Player player : players) {
			player.ID = "TEST";
		}
		String testInput = "afdsf\r\n ad fdf\r\n TeST";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Player testInputCapital =
				testPlayer.getPlayerSelection(inputScanner, players);
		assert(testInputCapital instanceof Player);
		inputScanner.close();
	}
	
	@Test //45
	void testGetPlayerSelectionInvalidCharacters() {
		Player testPlayer2 = new Player("Test2", 2, 0);
		Player testPlayer3 = new Player("Test3", 3, 0);
		Player testPlayer4 = new Player("Test4", 4, 0);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		for (Player player : players) {
			player.ID = "TEST";
		}
		String testInput = "a,!f$$f\r\ntest";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Player testInputCapital =
				testPlayer.getPlayerSelection(inputScanner, players);
		assert(testInputCapital instanceof Player);
		inputScanner.close();
	}
	
	@Test //46
	void testGetPlayerSelectionInvalidMultiplePeople() {
		Player testPlayer2 = new Player("Test2", 2, 0);
		Player testPlayer3 = new Player("Test3", 3, 0);
		Player testPlayer4 = new Player("Test4", 4, 0);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		for (Player player : players) {
			player.ID = "TEST";
		}
		String testInput = "TestTEST\r\nTest";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Player testInputCapital =
				testPlayer.getPlayerSelection(inputScanner, players);
		assert(testInputCapital instanceof Player);
		assert(testInputCapital.getID().equals("TEST"));
		inputScanner.close();
	}
	
	@Test //47
	void testGetPlayerSelectionInvalidManyMistakes() {
		Player testPlayer2 = new Player("Test2", 2, 0);
		Player testPlayer3 = new Player("Test3", 3, 0);
		Player testPlayer4 = new Player("Test4", 4, 0);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		for (Player player : players) {
			player.ID = "TEST";
		}
		String testInput = "Test22Test33\r\nadff\r\n T WO\r\n !@#$ \r\nTest";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Player testInputCapital =
				testPlayer.getPlayerSelection(inputScanner, players);
		assert(testInputCapital instanceof Player);
		assert(testInputCapital.getID().equals("TEST"));
		inputScanner.close();
	}
}
