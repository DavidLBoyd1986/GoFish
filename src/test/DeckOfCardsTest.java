package test;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.Card.Suit;
import com.boyd.deckofcards.DeckOfCards;
import com.boyd.deckofcards.DeckOfCards.SuitPair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

//import static org.junit.jupiter.api.Assert.*;
//import static org.junit.jupiter.api.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

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
     * Test 6 - DeckOfCards() verifying multiple decks have the appropriate isolation.
     * 
     * Test 7 - DeckOfCards() Two DeckOfCards objects are always in the same order and equal each other if in same order with same amount
     * 
     * Test 8 - getTopCard() returns the top Card
     * 
     * Test 9 - getBottomCard() returns the bottom Card
     * 
     * Test 10 - getCardByIndex() returns the Card from that index
     * 
     * Test 11 - getRandomCard() returns a Card, not testing the randomness as I trust the java Random library
     * 
     * Test 12 - getCardsTop() returns the amount of Cards requested off the top of the Deck
     * 
     * Test 13 - getCardsBottom() returns the amount of Cards requested off the bottom of the Deck
     * 
     * Test 14 - getCardsByIndex() returns the exact Cards from the indexes requested
     * 
     * Test 15 - getCardsRandom() returns the amount of cards requested, not testing the randomness as I trust the java Random library
     * 
     * Test 16 - shuffleDeck() shuffles the deck, tests DeckOfCards.equals() as well
     * 
     * Test 17 - getNumCardsInDeck() returns an accurate # of Cards in the deck
     * 
     * Test 18 - addCardTop() puts the card on top of the deck
     * 
     * Test 19 - addCardBottom() puts the card on the bottom of the deck
     * 
     * Test 20 - addCardRandom() puts the card back in the deck at a random spot //Chance of False Negative
     * 
     * Test 21 = addCardsTop() puts the cards on top of the deck
     * 
     * Test 22 = addCardsBottom() puts the cards on bottom of the deck
     * 
     * Test 23 - addCardsRandom() puts the cards back in the deck
     * 
     * Test 24 - getExactCards() tests requested Cards returned, test SuitPair as well
     * 
     * Test 25 - discardCard() - tests the Card is added to the discardPile
     * 
     * Test 26 - discardCards() - tests the Cards are added to the discardPile
     * 
     * Test 27 - discardCards() - tests no Error is thrown when an empty Card[] is added
     * 
     * Test 28 - addDiscardPileToDeck() - test it drops the discardPile on top of DeckOfCards with out shuffling
     * 
     * Test 29 - seeCardByIndex() - test it returns Card at the index

     */

	@Test //1
	void testHasCardTrue() {
		DeckOfCards testDeck = new DeckOfCards();
		assert(testDeck.hasCard(Suit.SPADE, Rank.ACE));
	}
	
	@Test //2
	void testHasCardTrueTwo() {
		DeckOfCards testDeck = new DeckOfCards();
		Card testCard = new Card(Suit.SPADE, Rank.ACE);
		assert(testDeck.hasCard(testCard.getSuit(), testCard.getRank()));
	}
	
	@Test //3
	void testGetExactCard() {
		DeckOfCards testDeck = new DeckOfCards();
		Card testCard1 = new Card(Suit.SPADE, Rank.ACE);
		Optional<Card> testGetCard = testDeck.getExactCard(Suit.SPADE, Rank.ACE);
		Card testCard2 = testGetCard.get();
		assert(testCard2.equals(testCard1));				
	}
	
	@Test //4
	void testGetExactCardRemovesCardFromDeck() {
		DeckOfCards testDeck = new DeckOfCards();
		// No need to assign card to variable
		testDeck.getExactCard(Suit.SPADE, Rank.ACE);
		assertFalse(testDeck.hasCard(Suit.SPADE, Rank.ACE));		
	}
	
	@Test //5
	void testGetExactCardReturnNull() {
		DeckOfCards testDeck = new DeckOfCards();
		// No need to assign card to variable
		testDeck.getExactCard(Suit.SPADE, Rank.ACE);
		Optional<Card> noCard = testDeck.getExactCard(Suit.SPADE, Rank.ACE);
		assertFalse(noCard.isPresent());		
	}
	
	@Test //6
	void testDeckOfCardsInstanceIsolation() {
		DeckOfCards testDeck1 = new DeckOfCards();
		DeckOfCards testDeck2 = new DeckOfCards();
		testDeck1.getExactCard(Suit.SPADE, Rank.ACE);
		assert(testDeck2.hasCard(Suit.SPADE, Rank.ACE));
	}
	
	@Test //7
	void testDeckOfCardsAlwaysStartInSameOrder() {
		DeckOfCards testDeck = new DeckOfCards();
		DeckOfCards copyDeck = new DeckOfCards();
		assert(testDeck.equals(copyDeck));
	}
	
	@Test //8
	void testGetTopCard() {
		DeckOfCards testDeck = new DeckOfCards();
		DeckOfCards copyDeck = new DeckOfCards();
		Card topCard = testDeck.getTopCard();
		assert(topCard.equals(copyDeck.getCardByIndex(1)));		
	}
	
	@Test //9
	void testGetBottomCard() {
		DeckOfCards testDeck = new DeckOfCards();
		DeckOfCards copyDeck = new DeckOfCards();
		Card bottomCard = testDeck.getBottomCard();
		assert(bottomCard.equals(copyDeck.getCardByIndex(52)));		
	}
	
	@Test //10
	void testGetCardByIndex() {
		DeckOfCards testDeck = new DeckOfCards();
		DeckOfCards copyDeck = new DeckOfCards();
		Card testCard = testDeck.getCardByIndex(15);
		assert(testCard.equals(copyDeck.getCardByIndex(15)));		
	}
	
	@Test //11
	void testGetRandomCard() {
		DeckOfCards testDeck = new DeckOfCards();
		Card testCard = testDeck.getRandomCard();
		assert(testCard instanceof Card);	
	}
	
	@Test //12
	void testGetCardsTop() {
		DeckOfCards testDeck = new DeckOfCards();
		DeckOfCards copyDeck = new DeckOfCards();
		Card[] retrievedCards = testDeck.getCardsTop(4);
		boolean testResult = true;
		for ( Card card : retrievedCards ) {
			if (! card.equals(copyDeck.getTopCard())) {
				testResult = false;
				break;
			}
		}
		assert(testResult);
	}

	@Test //13
	void testGetCardsBottom() {
		DeckOfCards testDeck = new DeckOfCards();
		DeckOfCards copyDeck = new DeckOfCards();
		Card[] retrievedCards = testDeck.getCardsBottom(4);
		boolean testResult = true;
		for ( Card card : retrievedCards ) {
			if (! card.equals(copyDeck.getBottomCard())) {
				testResult = false;
				break;
			}
		}
		assert(testResult);
	}
	
	@Test //14
	void testGetCardsByIndex() {
		DeckOfCards testDeck = new DeckOfCards();
		DeckOfCards copyDeck = new DeckOfCards();	
		int[] cardsIndex = { 4, 7, 11, 26, 34, 42, 51 };
		Card[] retrievedCards = testDeck.getCardsByIndex(cardsIndex);
		boolean testResult = true;
		int cardIndex = cardsIndex.length - 1;
		for ( int i = 0 ; i < cardsIndex.length ; i++ ) {
			if (!retrievedCards[i].equals(copyDeck.getCardByIndex(cardsIndex[cardIndex]))) {
				testResult = false;
				break;
			}
			cardIndex--;
		}
		assert(testResult);

	}
	
	@Test //15
	void testGetCardsRandom() {
		DeckOfCards testDeck = new DeckOfCards();
		Card[] retrievedCards = testDeck.getCardsRandom(4);
		assert(retrievedCards.length == 4);
	}
	
	@Test //16
	void testShuffleDeck() {
		DeckOfCards testDeck = new DeckOfCards();
		DeckOfCards copyDeck = new DeckOfCards();
		testDeck.shuffleDeck();
		boolean deckShuffled = testDeck.equals(copyDeck);
		assert(!deckShuffled);
	}
	
	@Test //17
	void testGetNumCardsInDeck() {
		DeckOfCards testDeck = new DeckOfCards();
		testDeck.getCardsRandom(32);
		assert(testDeck.getNumCardsInDeck() == 20);
	}
	
	@Test //18
	void testAddCardTop() {
		DeckOfCards testDeck = new DeckOfCards();
		Card testCard = testDeck.getRandomCard();
		Card copyCard = testCard;
		testDeck.addCardTop(testCard);
		assert(testDeck.getTopCard().equals(copyCard));
	}
	
	@Test //19
	void testAddCardBottom() {
		DeckOfCards testDeck = new DeckOfCards();
		Card testCard = testDeck.getRandomCard();
		Card copyCard = testCard;
		testDeck.addCardBottom(testCard);
		assert(testDeck.getBottomCard().equals(copyCard));
	}
	
	@Test //20 - Has a chance of a false negative because it will randomly add the card back into the same spot
	void testAddCardRandom() {
		DeckOfCards testDeck = new DeckOfCards();
		DeckOfCards copyDeck = new DeckOfCards();
		Card testCard = testDeck.getRandomCard();
		testDeck.addCardRandom(testCard);
		assert(!testDeck.equals(copyDeck));
	}
	
	@Test //21 - This test is a mess, but it works, so I'm keeping it.
	void testAddCardsTop() {
		DeckOfCards testDeck = new DeckOfCards();
		Card[] retrievedCards = testDeck.getCardsRandom(11);
		Card[] copyCards = retrievedCards;
		testDeck.addCardsTop(retrievedCards);
		Card[] testCards = testDeck.getCardsTop(11);
		// Loop through testCards and copyCards to verify they match. One is backwards so that's why it's a mess.
		boolean cardsMatch = true;
		int backwards = 10;
		for (int i = 0; i < testCards.length; i++) {
			if (!testCards[i].equals(copyCards[backwards])) {
				cardsMatch = false;
				break;
			}
			backwards--;
		}
		assert(cardsMatch);
	}
	
	@Test //22 - This test is a mess, but it works, so I'm keeping it.
	void testAddCardsBottom() {
		DeckOfCards testDeck = new DeckOfCards();
		Card[] retrievedCards = testDeck.getCardsRandom(11);
		Card[] copyCards = retrievedCards;
		testDeck.addCardsBottom(retrievedCards);
		Card[] testCards = testDeck.getCardsBottom(11);
		// Loop through testCards and copyCards to verify they match. One is backwards so that's why it's a mess.
		boolean cardsMatch = true;
		int backwards = 10;
		for (int i = 0; i < testCards.length; i++) {
			if (!testCards[i].equals(copyCards[backwards])) {
				cardsMatch = false;
				break;
			}
			backwards--;
		}
		assert(cardsMatch);
	}
	
	@Test //23
	void testAddCardsRandom() {
		DeckOfCards testDeck = new DeckOfCards();
		Card[] retrievedCards = testDeck.getCardsRandom(11);
		testDeck.addCardsRandom(retrievedCards);
		assert(testDeck.getNumCardsInDeck() == 52);
	}
	
	@Test //24
	void testGetExactCards() {
		DeckOfCards deck = new DeckOfCards();
		// Test SuitPair
		DeckOfCards.SuitPair cardRequest1 = deck.new SuitPair(Suit.CLUB, Rank.FIVE);
		DeckOfCards.SuitPair cardRequest2 = deck.new SuitPair(Suit.DIAMOND, Rank.QUEEN);		
		// Test getExactCards()
		SuitPair[] cardRequestArray = {cardRequest1, cardRequest2};
		Card[] retrievedCards = deck.getExactCards(cardRequestArray);
		Card fiveClub = new Card(Suit.CLUB, Rank.FIVE);
		Card queenDiamond = new Card(Suit.DIAMOND, Rank.QUEEN);
		Card[] testCards = { fiveClub, queenDiamond };
		boolean testResult = true;
		for (Card card : testCards) {
			if (!Arrays.asList(retrievedCards).contains(card)) {
				testResult = false;
				break;
			}
		}
		assert(testResult);
	}
	
	@Test //25
	void testDiscardCard() {
		DeckOfCards testDeck = new DeckOfCards();
		Card testCard = testDeck.getBottomCard();
		testDeck.discardCard(testCard);
		assert( testDeck.getNumCardsInDiscardPile() == 1 && testDeck.getNumCardsInDeck() == 51 && testDeck.getNumCardsNotInDeck() == 1 );
	}
	
	@Test //26
	void testDiscardCards() {
		DeckOfCards testDeck = new DeckOfCards();
		Card[] testCards = testDeck.getCardsBottom(21);
		testDeck.discardCards(testCards);
		assert( testDeck.getNumCardsInDiscardPile() == 21 && testDeck.getNumCardsInDeck() == 31 && testDeck.getNumCardsNotInDeck() == 21);
	}
	
	@Test //27
	void testDiscardCardsEmpty() {
		DeckOfCards testDeck = new DeckOfCards();
		Card[] testCards = {};
		testDeck.discardCards(testCards);
		assert(testDeck.getNumCardsInDiscardPile() == 0);
	}
	
	@Test //28
	void testAddDiscardPileToDeck() {
		DeckOfCards testDeck = new DeckOfCards();
		Card[] testCards = testDeck.getCardsBottom(13);
		testDeck.discardCards(testCards);
		boolean allSpades = true;
		for ( Card card : testDeck.discardPile ) {
			if (!card.getSuit().equals(Suit.SPADE)) {
				allSpades = false;
				break;
			}
		}
		assert(allSpades && testDeck.getNumCardsInDiscardPile() == 13 && testDeck.getNumCardsNotInDeck() == 13);
	}
	
	@Test //29
	void testSeeCardByIndex() {
		DeckOfCards testDeck = new DeckOfCards();
		DeckOfCards copyDeck = new DeckOfCards();
		String testCard = testDeck.seeCardByIndex(15);
		Card copyCard = copyDeck.getCardByIndex(15);
		assert(testCard.equals(copyCard.toString()));		
	}
}
