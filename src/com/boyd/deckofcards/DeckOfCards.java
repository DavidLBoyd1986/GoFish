package com.boyd.deckofcards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.Card.Suit;

public class DeckOfCards implements DeckOfCardsInterface {

    private final int totalNumOfCards = Suit.values().length * Rank.values().length;
 
	public ArrayList<Card> deck = new ArrayList<Card>();
	
	/**
	 * Build the DeckOfCards by creating and adding a Card for every combination of Suit and Rank
	 */
	public DeckOfCards() {
		for ( Suit suit : Suit.values() ) {
			for ( Rank rank : Rank.values() ) {
				Card card = new Card(suit, rank);
				deck.add(card);
			}
		}
	}

	@Override
	public boolean hasCard(Suit suit, Rank rank) {
		boolean foundCard = false;
		for ( Card card : deck) {
			if ( card.getSuit() == suit && card.getRank() == rank) {
				foundCard = true;
			}}
		return foundCard;
	}

	@Override
	public int getNumCards() {
		return deck.size();
	}


	@Override
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}
	
	@Override
	public Card getTopCard() {
		Card card = deck.get(0);
		deck.remove(0);
		return card;
	}

	@Override
	public Card getBottomCard() {
		Card card = deck.get(deck.size() - 1);
		deck.remove(deck.size() - 1);
		return card;
	}
	
	@Override
	public Card getCardByIndex(int index) {
		Card card = deck.get(index);
		deck.remove(index);
		return card;
	}
	
	@Override
	public Card getRandomCard() {
		Random random = new Random();
		int randomIndex = random.nextInt(deck.size());
		Card card = deck.get(randomIndex);
		deck.remove(randomIndex);
		return card;
	}
	
	// This method is funky. Not sure how to get it better written
	@Override
	public Card getExactCard(Suit suit, Rank rank) throws Exception {
		
		boolean foundCard = false;
		Card returnCard = new Card(suit, rank);
		for ( Card card : deck) {
			if ( card.getSuit() == suit && card.getRank() == rank) {
				foundCard = true;
				deck.remove(card);
			}
		}
		// I had to write the method this way because it shows errors if it doesn't return Card at the end.
		// throwing the exception had to be before the return Card
		if (!foundCard) {
			throw new Exception();
		} else {
			return returnCard;
		}
	}


	@Override
	public Card[] getCardsTop(int numOfCards) {
		Card[] cards = new Card[numOfCards];
		int count = 0;
		while ( count < numOfCards ) {
			cards[count] = deck.get(count);
			deck.remove(count);
			count++;
		}
		return cards;
	}
	
	@Override
	public Card[] getCardsBottom(int numOfCards) {
		Card[] cards = new Card[numOfCards];
		int count = 0;
		while ( count < numOfCards ) {
			cards[count] = deck.get(deck.size() - 1);
			deck.remove(deck.size() - 1);
			count ++;
		}
		return cards;
	}
	
	@Override
	public Card[] getCardsByIndex(int[] cardsSelected) {
		Card[] cards = new Card[cardsSelected.length];
		// Array must be sorted so it always gets the earliest card in the array first
		Arrays.sort(cardsSelected);
		int count = 0;
		for ( int i : cardsSelected) {
			// Since the array gets smaller I have to subtract the # of items removed to get the selection specified
			Card card = deck.get(i - count);
			deck.remove(i - count);
			cards[count] = card;
			count ++;
		}
		return cards;
	}
	
	@Override
	public Card[] getCardsRandom(int numOfCards) {
		Random random = new Random();
		Card[] cards = new Card[numOfCards];
		int count = 0;
		while ( count < numOfCards ) {
			int randomInt = random.nextInt(deck.size());
			cards[count] = deck.get(randomInt);
			deck.remove(randomInt);
			count ++;
		}
		return cards;
	}

	@Override
	public void addCardTop(Card card) {
		for ( Card deckCard : deck ) {
			if (deckCard.equals(card)) {
				System.out.println("Can't add duplicate card: " + card);
				// Add exception here
			}
		}
		deck.add(0, card);
	}
	
	public void addCardBottom(Card card) {
		for ( Card deckCard : deck ) {
			if ( deckCard.equals(card) ) {
				System.out.println("Can't add duplicate card: " + card);
			} 
		}
		deck.add(deck.size(), card);
	}
	
	public void addCardRandom(Card card) {
		Random random = new Random();
		for ( Card deckCard : deck ) {
			if ( deckCard.equals(card) ) {
				System.out.println("Can't add duplicate card:" + card);
			}
		}
		deck.add(random.nextInt(deck.size()), card);
	}
	
	@Override
	public void addCardsTop(Card[] cards) {
		for ( Card suppliedCard : cards ) {
			for ( Card deckCard : deck ) {
				if (deckCard.equals(suppliedCard)) {
					System.out.println("Can't add duplicate card: " + suppliedCard);
					// Add exception here
				}
			}
		}
		for ( Card suppliedCard : cards ) {
			deck.add(0, suppliedCard);
		}
	}
	
	@Override
	public void addCardsBottom(Card[] cards) {
		for ( Card suppliedCard : cards ) {
			for ( Card deckCard : deck ) {
				if (deckCard.equals(suppliedCard)) {
					System.out.println("Can't add duplicate card: " + suppliedCard);
					// Add exception here
				}
			}
		}
		for ( Card suppliedCard : cards ) {
			deck.add(deck.size(), suppliedCard);
		}
	}
	
	@Override
	public void addCardsRandom(Card[] cards) {
		Random random = new Random();
		for ( Card suppliedCard : cards ) {
			for ( Card deckCard : deck ) {
				if (deckCard.equals(suppliedCard)) {
					System.out.println("Can't add duplicate card: " + suppliedCard);
					// Add exception here
				}
			}
		}
		for ( Card suppliedCard : cards ) {
			deck.add(random.nextInt(deck.size()), suppliedCard);
		}
	}
	
	public String toString() {
		return deck.toString();
	}
	
	public static void main(String[] args) {
		
		//These are all simple test of the application. Will write junit tests later.
		DeckOfCards deck = new DeckOfCards();
		System.out.println(deck);
		Card topCard = deck.getTopCard();
		System.out.println("TopCard = " + topCard);
		Card bottomCard = deck.getBottomCard();
		System.out.println("BottomCard = " + bottomCard);
		Card specificCard = deck.getCardByIndex(4);
		System.out.println("SpecificCard = " + specificCard);
		Card randomCard = deck.getRandomCard();
		System.out.println("RandCard = " + randomCard);
		deck.shuffleDeck();
		System.out.println(deck);
		Card[] cardsTop = deck.getCardsTop(4);
		System.out.println(cardsTop);
		for ( Card card : cardsTop) {
			System.out.println(card);
		}
		System.out.println(deck);
		Card[] cardsBottom = deck.getCardsBottom(5);
		System.out.println(cardsBottom);
		for ( Card card :cardsBottom) {
			System.out.println(card);
		}
		System.out.println(deck.getNumCards());
		Card[] cardsRandom = deck.getCardsRandom(3);
		System.out.println(cardsRandom);
		for ( Card card : cardsRandom ) {
			System.out.println(card);
		}
		System.out.println(deck.getNumCards());
		System.out.println(deck);
		int[] cardsToGet = {1, 3, 4};
		Card[] cardsSpecific = deck.getCardsByIndex(cardsToGet);
		System.out.println(cardsSpecific);
		for ( Card card : cardsSpecific) {
			System.out.println(card);
		}
		System.out.println(deck);
		
		System.out.println("STARTING GET EXACT CARD");
		Card twoHeart = new Card(Suit.HEART, Rank.TWO);
		Card twoHearttwo = new Card(Suit.HEART, Rank.TWO);
		Card threeHeart = new Card(Suit.HEART, Rank.THREE);
		DeckOfCards testDeck = new DeckOfCards();
		System.out.println(twoHeart == threeHeart);
		System.out.println(twoHeart.getSuit() == twoHeart.getSuit());
		System.out.println(twoHeart.equals(twoHearttwo));
		//Have to include try and catch with this method since it throws an exception
		try {System.out.println(testDeck.getExactCard(Suit.SPADE, Rank.NINE)); }
			catch (Exception e) { System.out.println("Card not found");}
		//Have to include try and catch with this method since it throws an exception
		try {System.out.println(testDeck.getExactCard(Suit.DIAMOND, Rank.TWO)); }
			catch (Exception e) { System.out.println("Card not found");}
		System.out.println(testDeck.hasCard(Suit.DIAMOND, Rank.TWO));
		System.out.println(testDeck.hasCard(Suit.SPADE, Rank.NINE));
		System.out.println("ENDING GET EXACT CARD");
		
		// Test addCardTop()
		System.out.println(deck);
		Card testTopCard = deck.getTopCard();
		System.out.println(testTopCard);
		System.out.println(deck);
		deck.addCardTop(testTopCard);
		System.out.println(deck);
		
		// Test addCardBottom()
		Card testBottomCard = deck.getBottomCard();
		System.out.println(testBottomCard);
		System.out.println(deck);
		deck.addCardBottom(testBottomCard);
		System.out.println(deck);
		
		// Test addCardRandom()
		Card testRandomCard = deck.getRandomCard();
		System.out.println(testRandomCard);
		System.out.println(deck);
		deck.addCardRandom(testRandomCard);
		System.out.println(deck);
		
		// Test addCardsTop()
		System.out.println(deck);
		Card testTopCard1 = deck.getTopCard();
		Card testBottomCard1 = deck.getBottomCard();
		Card testRandomCard1 = deck.getRandomCard();
		System.out.println(testTopCard1);
		System.out.println(testBottomCard1);
		System.out.println(testRandomCard1);
		System.out.println(deck);
		Card[] testCardsTop = {testTopCard1, testBottomCard1, testRandomCard1};
		deck.addCardsTop(testCardsTop);
		System.out.println(deck);
		
		// Test addCardsBottom()
		System.out.println(deck);
		Card testTopCard2 = deck.getTopCard();
		Card testBottomCard2 = deck.getBottomCard();
		Card testRandomCard2 = deck.getRandomCard();
		System.out.println(testTopCard2);
		System.out.println(testBottomCard2);
		System.out.println(testRandomCard2);
		System.out.println(deck);
		Card[] testCardsBottom = {testTopCard2, testBottomCard2, testRandomCard2};
		deck.addCardsBottom(testCardsBottom);
		System.out.println(deck);
		
		// Test addCardsRandom()
		System.out.println(deck);
		Card testTopCard3 = deck.getTopCard();
		Card testBottomCard3 = deck.getBottomCard();
		Card testRandomCard3 = deck.getRandomCard();
		System.out.println(testTopCard3);
		System.out.println(testBottomCard3);
		System.out.println(testRandomCard3);
		System.out.println(deck);
		Card[] testCardsRandom = {testTopCard3, testBottomCard3, testRandomCard3};
		deck.addCardsRandom(testCardsRandom);
		System.out.println(deck);
	}
}

