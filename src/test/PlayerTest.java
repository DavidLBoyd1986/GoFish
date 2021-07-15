package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.DeckOfCards;
import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.Card.Suit;
import GoFish.*;

public class PlayerTest {

	
	
	/*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     * 
     * Player Testing:
     * 
     * Test 1 - testGetID() returns True if Player ID is accurately returned
     * 
     * Test 2 - testGetName() returns True if Player name is accurately returned
     * 
     * Test 3 - testGetPosition() returns True if Player position is accurately returned
     * 
     * Test 4 - testGetBooksEmpty() returns True if getBooks returns empty Set
     * 
     * Test 5 - testGetBooksOneBook() returns True if getBooks returns 1 book
     * 
     * Test 6 - testGetBooksTwoBooks() returns True if getBooks returns 2 books
     * 
     * Test 7 - testGetBooksCardArray() returns True if HashMap has 4 cards
     * 
     * Test 8 - testCreateBookRemoveCards() returns True if createBook removes cards from hand.
     * 
     * Test 9 - testSetRepeatTurn() returns True if setRepeatTurn updates the value
     * 
     * Test 10 - testGetHand() returns True if getHand returns ArrayList of correct size
     * 
     * Test 11 - testGetBookCheck() returns True if getBookCheck returns an empty HashMap
     * 
     * Test 12 - testDoInitialBookCheck() returns True if doInitialBookCheck accurately updates bookCheck
     * 
     * Test 13 - testDoInitialBookCheckCreateBook() returns True if doInitialBookCheck creates a book if dealt all 4 cards.
     * 
     * Test 14 - testUpdateBookCheck() returns True if updateBookCheck accurately updates bookCheck
     * 
     * Test 15 - testGetNumOfBooks() returns True if getNumOfBooks() accurately returns the correct # of books a person has
     * 
     * Test 16 - testHasRank() returns True if hasRank returns the correct boolean for having that rank
     * 
     * Test 17 - testRequestCards() returns True if requestCards() returns the correct boolean for player having that rank
     * 
     * Test 18 - testHasCards() returns True if hasCards returns the correct booleans
     * 
     * Test 19 - testDrawCard() returns True if drawCard adds a card to the player's hand, and the deck of cards decreases by 1
     * 
     * Test 20 - testGetCardsOneCard() returns True if getCards() returns one card, and opposing person's hand
     * 
     * Test 21 - testGetCardsTwoCards() returns True if getCards() returns two cards, and opposing person's hand
     * 
     * Test 22 - testGetCardsThreeCards() returns True if getCards() returns three cards, and opposing person's hand
     * 
     * Test 23 - testUpdateBookCheckPlusOneCard() returns True if updateBookCheck() accurately updates for +1
     * 
     * Test 24 - testUpdateBookCheckMinusOneCard() returns True if updateBookCheck() accurately updates for -1
     * 
     * Test 25 - testUpdateBookCheckPlusTwoCards() returns True if updateBookCheck() accurately updates for +2
     * 
     * Test 26 - testUpdateBookCheckMinusTwoCards() returns True if updateBookCheck() accurately updates for -2
     * 
     * Test 27 - testUpdateBookCheckPlusThreeCards() returns True if updateBookCheck() accurately updates for +3
     * 
     * Test 28 - testUpdateBookCheckMinusThreeCards() returns True if updateBookCheck() accurately updates for -3
     * 
     * Test 29 - testUpdateBookCheckPlusFourCards() returns True if updateBookCheck() accurately updates for +4 and creates a book
     * 
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
     * Test 46 - testGetPlayerSelectionInvalidMultipleRanks() returns True if getPlayerSelection() responds correctly to invalid inputs
     * 
     * Test 47 - testGetPlayerSelectionInvalidManyMistakes() returns True if getPlayerSelection() responds correctly to invalid inputs
	*/
	
	
	
	DeckOfCards deck;
	Player testPlayer;
	ArrayList<Card> hand;
	HashMap<Rank, Integer> bookCheck;
	HashMap<Rank, Card[]> books;
	
	@BeforeEach
	public void setup() throws Exception {
		deck = new DeckOfCards();
		testPlayer = new Player("Test", 1);
		hand = new ArrayList<Card>();
		bookCheck = new HashMap<Rank, Integer>();
		testPlayer.hand = hand;
		testPlayer.bookCheck = bookCheck;
	}
	
	@Test //1
	void testGetID() {
		assert(testPlayer.getID().equals("Test1"));
	}
	
	@Test //2
	void testGetName() {
		assert(testPlayer.getName().equals("Test"));
	}
	
	@Test //3
	void testGetPosition() {
		assert(testPlayer.getPosition() == 1);
	}
	
	@Test //4
	void testGetBooksEmpty() {
		assert(testPlayer.getBooks().equals(Collections.emptySet()));
	}
	
	@Test //5
	void testGetBooksOneBook() {
		testPlayer.hand.add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.HEART, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.DIAMOND, Rank.ACE).get());
		testPlayer.createBook(Rank.ACE);
		HashSet<Rank> testBooks = new HashSet<Rank>();
		testBooks.add(Rank.ACE);
		assert(testPlayer.getBooks().equals(testBooks));
	}
	
	@Test //6
	void testGetBooksTwoBooks() {
		testPlayer.hand.add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.HEART, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.DIAMOND, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.CLUB, Rank.TWO).get());
		testPlayer.hand.add(deck.getExactCard(Suit.HEART, Rank.TWO).get());
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.TWO).get());
		testPlayer.hand.add(deck.getExactCard(Suit.DIAMOND, Rank.TWO).get());
		testPlayer.createBook(Rank.ACE);
		testPlayer.createBook(Rank.TWO);
		HashSet<Rank> testBooks = new HashSet<Rank>();
		testBooks.add(Rank.ACE);
		testBooks.add(Rank.TWO);
		assert(testPlayer.getBooks().equals(testBooks));
	}
	
	@Test //7
	void testGetBooksCardArray() {
		testPlayer.hand.add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.HEART, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.DIAMOND, Rank.ACE).get());
		testPlayer.createBook(Rank.ACE);
		assert(testPlayer.books.get(Rank.ACE).length == 4);
	}
	
	@Test //8
	void testCreateBookRemoveCards() {
		testPlayer.hand.add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.HEART, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.DIAMOND, Rank.ACE).get());
		assert(testPlayer.getHand().size() == 4);
		testPlayer.createBook(Rank.ACE);
		assert(testPlayer.getHand().size() == 0);
	}
	
	@Test //9
	void testSetRepeatTurn() {
		testPlayer.setRepeatTurn(false);
		assertFalse(testPlayer.repeatTurn);
		testPlayer.setRepeatTurn(true);
		assertTrue(testPlayer.repeatTurn);
	}
	
	@Test //10
	void testGetHand() {
		assert(testPlayer.getHand().size() == 0);
		testPlayer.hand.addAll(Arrays.asList(deck.getCardsRandom(10)));
		assert(testPlayer.getHand().size() == 10);
	}
	
	@Test //11
	void testGetBookCheck() {
		assert(testPlayer.getBookCheck().equals(Collections.EMPTY_MAP));
	}
	
	@Test //12
	void testDoInitialBookCheck() {
		testPlayer.hand.add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.HEART, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.CLUB, Rank.TWO).get());
		testPlayer.hand.add(deck.getExactCard(Suit.HEART, Rank.TWO).get());
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.TWO).get());
		testPlayer.doInitialBookCheck();
		HashMap<Rank, Integer> testBookCheck = new HashMap<Rank, Integer>();
		Integer three = new Integer(3);
		testBookCheck.put(Rank.ACE, three);
		testBookCheck.put(Rank.TWO, three);
		assert(testPlayer.getBookCheck().equals(testBookCheck));
	}
	
	@Test //13
	void testDoInitialBookCheckCreateBook() {
		testPlayer.hand.add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.HEART, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.DIAMOND, Rank.ACE).get());
		testPlayer.doInitialBookCheck();
		HashSet<Rank> testBooks = new HashSet<Rank>();
		testBooks.add(Rank.ACE);
		assert(testPlayer.getBooks().equals(testBooks));
		assert(testPlayer.getHand().size() == 0);
		assert(testPlayer.getNumOfBooks() == 1);
	}
	
	@Test //14
	void testUpdateBookCheck() {
		testPlayer.hand.add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.HEART, Rank.TWO).get());
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.FIVE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.DIAMOND, Rank.FIVE).get());
		testPlayer.updateBookCheck(Rank.ACE, 1);
		testPlayer.updateBookCheck(Rank.TWO, 1);
		testPlayer.updateBookCheck(Rank.FIVE, 2);
		HashMap<Rank, Integer> testBookCheck = new HashMap<Rank, Integer>();
		Integer testIntOne = new Integer(1);
		testBookCheck.put(Rank.ACE, testIntOne);
		testBookCheck.put(Rank.TWO, 1);
		testBookCheck.put(Rank.FIVE, 2);
		assert(testPlayer.getBookCheck().equals(testBookCheck));
		assert(testPlayer.getHand().size() == 4);
	}
	
	@Test //15
	void testGetNumOfBooks() {
		testPlayer.hand.add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.HEART, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.DIAMOND, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.CLUB, Rank.TWO).get());
		testPlayer.hand.add(deck.getExactCard(Suit.HEART, Rank.TWO).get());
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.TWO).get());
		testPlayer.hand.add(deck.getExactCard(Suit.DIAMOND, Rank.TWO).get());
		testPlayer.createBook(Rank.ACE);
		testPlayer.createBook(Rank.TWO);
		assert(testPlayer.getNumOfBooks() == 2);
	}
	
	@Test //16
	void testHasRank() {
		testPlayer.hand.add(deck.getExactCard(Suit.HEART, Rank.KING).get());
		testPlayer.hand.add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.HEART, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.CLUB, Rank.QUEEN).get());
		testPlayer.hand.add(deck.getExactCard(Suit.HEART, Rank.QUEEN).get());
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.QUEEN).get());
		assertTrue(testPlayer.hasRank(Rank.KING));
		assertTrue(testPlayer.hasRank(Rank.ACE));
		assertTrue(testPlayer.hasRank(Rank.QUEEN));
		assertFalse(testPlayer.hasRank(Rank.TWO));
	}
	
	@Test //17
	void testRequestCards() {
		Player testPlayer2 = new Player("test", 2);
		testPlayer2.hand.add(deck.getExactCard(Suit.HEART, Rank.KING).get());
		testPlayer2.hand.add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer2.hand.add(deck.getExactCard(Suit.HEART, Rank.ACE).get());
		testPlayer2.hand.add(deck.getExactCard(Suit.CLUB, Rank.QUEEN).get());
		testPlayer2.hand.add(deck.getExactCard(Suit.HEART, Rank.QUEEN).get());
		testPlayer2.hand.add(deck.getExactCard(Suit.SPADE, Rank.QUEEN).get());
		assertTrue(testPlayer.requestCards(Rank.KING, testPlayer2));
		assertTrue(testPlayer.requestCards(Rank.ACE, testPlayer2));
		assertTrue(testPlayer.requestCards(Rank.QUEEN, testPlayer2));
		assertFalse(testPlayer.requestCards(Rank.TWO, testPlayer2));
	}
	
	@Test //18
	void testHasCards() {
		assertFalse(testPlayer.hasCards());
		testPlayer.drawCard(deck);
		assertTrue(testPlayer.hasCards());
	}
	
	@Test //19
	void testDrawCard() {
		int deckSizeStart = deck.getNumCardsInDeck();
		testPlayer.drawCard(deck);
		assert(deck.getNumCardsInDeck() == deckSizeStart - 1);
		assert(testPlayer.hand.size() == 1);
	}
	
	@Test //20
	void testGetCardsOneCard() {
		Player testPlayer2 = new Player("test2", 2);
		testPlayer2.hand.add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer2.bookCheck.put(Rank.ACE, 1);
		Card[] retrievedCards = testPlayer.getCards(Rank.ACE, testPlayer2);
		int numOfRetrievedCards = 0;
		for (Card card : retrievedCards) {
			if (Objects.nonNull(card)) {
				numOfRetrievedCards +=1;
			}
		}
		assert(numOfRetrievedCards == 1);
		assert(testPlayer2.hand.size() == 0);
		assert(testPlayer2.getBookCheck().equals(Collections.EMPTY_MAP));
	}
	
	@Test //21
	void testGetCardsTwoCards() {
		Player testPlayer2 = new Player("test2", 2);
		testPlayer2.hand.add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer2.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer2.bookCheck.put(Rank.ACE, 2);
		Card[] retrievedCards = testPlayer.getCards(Rank.ACE, testPlayer2);
		int numOfRetrievedCards = 0;
		for (Card card : retrievedCards) {
			if (Objects.nonNull(card)) {
				numOfRetrievedCards +=1;
			}
		}
		assert(numOfRetrievedCards == 2);
		assert(testPlayer2.hand.size() == 0);
		assert(testPlayer2.getBookCheck().equals(Collections.EMPTY_MAP));
	}
	
	@Test //22
	void testGetCardsThreeCards() {
		Player testPlayer2 = new Player("test2", 2);
		testPlayer2.hand.add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer2.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer2.hand.add(deck.getExactCard(Suit.DIAMOND, Rank.ACE).get());
		testPlayer2.bookCheck.put(Rank.ACE, 3);
		Card[] retrievedCards = testPlayer.getCards(Rank.ACE, testPlayer2);
		int numOfRetrievedCards = 0;
		for (Card card : retrievedCards) {
			if (Objects.nonNull(card)) {
				numOfRetrievedCards +=1;
			}
		}
		assert(numOfRetrievedCards == 3);
		assert(testPlayer2.hand.size() == 0);
		assert(testPlayer2.getBookCheck().equals(Collections.EMPTY_MAP));
	}
	
	@Test //23
	void testUpdateBookCheckPlusOneCard() {
		testPlayer.updateBookCheck(Rank.ACE, 1);
		HashMap<Rank, Integer> testBookCheck = new HashMap<Rank, Integer>();
		testBookCheck.put(Rank.ACE, 1);
		assert(testPlayer.getBookCheck().equals(testBookCheck));
	}
	
	@Test //24
	void testUpdateBookCheckMinusOneCard() {
		testPlayer.updateBookCheck(Rank.ACE, 1);
		HashMap<Rank, Integer> testBookCheck = new HashMap<Rank, Integer>();
		testBookCheck.put(Rank.ACE, 1);
		assert(testPlayer.getBookCheck().equals(testBookCheck));
		testPlayer.updateBookCheck(Rank.ACE, -1);
		assert(testPlayer.getBookCheck().equals(Collections.EMPTY_MAP));
	}
	
	@Test //25
	void testUpdateBookCheckPlusTwoCards() {
		testPlayer.updateBookCheck(Rank.ACE, 1);
		testPlayer.updateBookCheck(Rank.ACE, 1);
		HashMap<Rank, Integer> testBookCheck = new HashMap<Rank, Integer>();
		testBookCheck.put(Rank.ACE, 2);
		assert(testPlayer.getBookCheck().equals(testBookCheck));
	}
	
	@Test //26
	void testUpdateBookCheckMinusTwoCards() {
		testPlayer.updateBookCheck(Rank.ACE, 1);
		testPlayer.updateBookCheck(Rank.ACE, 1);
		HashMap<Rank, Integer> testBookCheck = new HashMap<Rank, Integer>();
		testBookCheck.put(Rank.ACE, 2);
		assert(testPlayer.getBookCheck().equals(testBookCheck));
		testPlayer.updateBookCheck(Rank.ACE, -2);
		assert(testPlayer.getBookCheck().equals(Collections.EMPTY_MAP));
	}
	
	@Test //27
	void testUpdateBookCheckPlusThreeCards() {
		testPlayer.updateBookCheck(Rank.ACE, 1);
		testPlayer.updateBookCheck(Rank.ACE, 1);
		testPlayer.updateBookCheck(Rank.ACE, 1);
		HashMap<Rank, Integer> testBookCheck = new HashMap<Rank, Integer>();
		testBookCheck.put(Rank.ACE, 3);
		assert(testPlayer.getBookCheck().equals(testBookCheck));
	}
	
	@Test //28
	void testUpdateBookCheckMinusThreeCards() {
		testPlayer.updateBookCheck(Rank.ACE, 1);
		testPlayer.updateBookCheck(Rank.ACE, 1);
		testPlayer.updateBookCheck(Rank.ACE, 1);
		HashMap<Rank, Integer> testBookCheck = new HashMap<Rank, Integer>();
		testBookCheck.put(Rank.ACE, 3);
		assert(testPlayer.getBookCheck().equals(testBookCheck));
		testPlayer.updateBookCheck(Rank.ACE, -3);
		assert(testPlayer.getBookCheck().equals(Collections.EMPTY_MAP));
	}
	
	@Test //29
	void testUpdateBookCheckPlusFourCards() {
		testPlayer.hand.add(deck.getExactCard(Suit.CLUB, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.SPADE, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.HEART, Rank.ACE).get());
		testPlayer.hand.add(deck.getExactCard(Suit.DIAMOND, Rank.ACE).get());
		testPlayer.updateBookCheck(Rank.ACE, 1);
		testPlayer.updateBookCheck(Rank.ACE, 1);
		testPlayer.updateBookCheck(Rank.ACE, 1);
		testPlayer.updateBookCheck(Rank.ACE, 1);
		assert(testPlayer.getBookCheck().equals(Collections.EMPTY_MAP));
		assert(testPlayer.getNumOfBooks() == 1);
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
		Player testPlayer2 = new Player("Test2", 2);
		Player testPlayer3 = new Player("Test3", 3);
		Player testPlayer4 = new Player("Test4", 4);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		
		String testInput = "TEST22\r\n";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Player testInputCapital =
				testPlayer.getPlayerSelection(inputScanner, players);
		assert(testInputCapital instanceof Player);
		inputScanner.close();
	}
	
	@Test //40
	void testGetPlayerSelectionValidLowerCase() {
		Player testPlayer2 = new Player("Test2", 2);
		Player testPlayer3 = new Player("Test3", 3);
		Player testPlayer4 = new Player("Test4", 4);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		
		String testInput = "test44\r\n";
		Scanner inputScanner = new Scanner(testInput);
		inputScanner.useDelimiter(System.lineSeparator());
		Player testInputCapital =
				testPlayer.getPlayerSelection(inputScanner, players);
		assert(testInputCapital instanceof Player);
		inputScanner.close();
	}
	
	@Test //41
	void testGetPlayerSelectionValidMixedCase() {
		Player testPlayer2 = new Player("Test2", 2);
		Player testPlayer3 = new Player("Test3", 3);
		Player testPlayer4 = new Player("Test4", 4);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		
		String testInput = "tESt33\r\n";
		Scanner inputScanner = new Scanner(testInput);
		inputScanner.useDelimiter(System.lineSeparator());
		Player testInputCapital =
				testPlayer.getPlayerSelection(inputScanner, players);
		assert(testInputCapital instanceof Player);
		inputScanner.close();
	}
	
	@Test //42
	void testGetPlayerSelectionInvalidEmpty() {
		Player testPlayer2 = new Player("Test2", 2);
		Player testPlayer3 = new Player("Test3", 3);
		Player testPlayer4 = new Player("Test4", 4);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		
		String testInput = " \r\n \r\nTEST22";
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
		Player testPlayer2 = new Player("Test2", 2);
		Player testPlayer3 = new Player("Test3", 3);
		Player testPlayer4 = new Player("Test4", 4);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);

		String testInput = "afdsf\r\nadfdf\r\nTest44";
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
		Player testPlayer2 = new Player("Test2", 2);
		Player testPlayer3 = new Player("Test3", 3);
		Player testPlayer4 = new Player("Test4", 4);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		
		String testInput = "afdsf\r\n ad fdf\r\n TeST22";
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
		Player testPlayer2 = new Player("Test2", 2);
		Player testPlayer3 = new Player("Test3", 3);
		Player testPlayer4 = new Player("Test4", 4);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);

		String testInput = "a,!f$$f\r\ntest22";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Player testInputCapital =
				testPlayer.getPlayerSelection(inputScanner, players);
		assert(testInputCapital instanceof Player);
		inputScanner.close();
	}
	
	@Test //46
	void testGetPlayerSelectionInvalidMultipleRanks() {
		Player testPlayer2 = new Player("Test2", 2);
		Player testPlayer3 = new Player("Test3", 3);
		Player testPlayer4 = new Player("Test4", 4);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		
		String testInput = "Test22Test33\r\nTest44";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Player testInputCapital =
				testPlayer.getPlayerSelection(inputScanner, players);
		assert(testInputCapital instanceof Player);
		assert(testInputCapital.getID().equals("Test44"));
		inputScanner.close();
	}
	
	@Test //47
	void testGetPlayerSelectionInvalidManyMistakes() {
		Player testPlayer2 = new Player("Test2", 2);
		Player testPlayer3 = new Player("Test3", 3);
		Player testPlayer4 = new Player("Test4", 4);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(testPlayer2);
		players.add(testPlayer3);
		players.add(testPlayer4);
		
		String testInput = "Test22Test33\r\nadff\r\n T WO\r\n !@#$ \r\nTest44";
		System.setIn(new ByteArrayInputStream(testInput.getBytes()));
		Scanner inputScanner = new Scanner(System.in);
		inputScanner.useDelimiter(System.lineSeparator());
		Player testInputCapital =
				testPlayer.getPlayerSelection(inputScanner, players);
		assert(testInputCapital instanceof Player);
		assert(testInputCapital.getID().equals("Test44"));
		inputScanner.close();
	}
}
