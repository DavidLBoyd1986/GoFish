package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
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
//		books = new HashMap<Rank, Card[]>();
//		hand.addAll(Arrays.asList(deck.getCardsTop(5)));
//		//Update bookCheck
//		for (Card card : hand) {
//			if (bookCheck.containsKey(card.getRank())) {
//				int value = bookCheck.get(card.getRank()) + 1;
//				bookCheck.replace(card.getRank(), value);
//			} else {
//				bookCheck.put(card.getRank(), 1);
//			}
//		}
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
	}
}
