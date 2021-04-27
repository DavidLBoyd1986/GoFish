package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.Card.Suit;
import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.DeckOfCards;

class DeckOfCardsTest {

	   /*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     * 
     * Test 1 - hasCard() returns True if Card is in the deck
     * 
     * Test 2 - hasCard() returns True if Card is in the deck; using a test Card
     * 
     * Test 3 - getExactCard() returns the requested Card in an Optional<Card>;
     * 
     * Test 4 - getExactCard() successfully removes a card, and hasCard() returns False that Card is not in the deck
     * 
     * Test 5 - getExactCard() returns an empty Optional<Card> if the card isn't in the deck;
     * 

     */

	@Test
	void testHasCardTrue() {
		DeckOfCards testDeck = new DeckOfCards();
		assert(testDeck.hasCard(Suit.SPADE, Rank.ACE));
	}
	
	@Test
	void testHasCardTrueTwo() {
		DeckOfCards testDeck = new DeckOfCards();
		Card testCard = new Card(Suit.SPADE, Rank.ACE);
		assert(testDeck.hasCard(testCard.getSuit(), testCard.getRank()));
	}
	
	@Test
	void testGetExactCard() {
		DeckOfCards testDeck = new DeckOfCards();
		Card testCard1 = new Card(Suit.SPADE, Rank.ACE);
		Optional<Card> testGetCard = testDeck.getExactCard(Suit.SPADE, Rank.ACE);
		Card testCard2 = testGetCard.get();
		assert(testCard2.equals(testCard1));				
	}
	
	@Test
	void testGetExactCardRemovesCardFromDeck() {
		DeckOfCards testDeck = new DeckOfCards();
		// No need to assign card to variable
		testDeck.getExactCard(Suit.SPADE, Rank.ACE);
		assertFalse(testDeck.hasCard(Suit.SPADE, Rank.ACE));		
	}
	
	@Test
	void testGetExactCardReturnNull() {
		DeckOfCards testDeck = new DeckOfCards();
		// No need to assign card to variable
		testDeck.getExactCard(Suit.SPADE, Rank.ACE);
		Optional<Card> noCard = testDeck.getExactCard(Suit.SPADE, Rank.ACE);
		assertFalse(noCard.isPresent());		
	}

}
