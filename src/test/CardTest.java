package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.Card.Suit;

class CardTest {

	@Test
	public void testCardGetSuit() {
		Suit testSuit = Suit.SPADE;
		Rank testRank = Rank.ACE;
		Card card = new Card(testSuit, testRank);
		assertEquals(testSuit, card.getSuit());
	}
	
	@Test
	public void testCardGetRank() {
		Suit testSuit = Suit.SPADE;
		Rank testRank = Rank.ACE;
		Card card = new Card(testSuit, testRank);
		assertEquals(testRank, card.getRank());
	}
}
