package com.boyd.deckofcards;

import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.Card.Suit;

import java.util.*;

public class DeckOfCards implements DeckOfCardsInterface, Iterable<Card> {

    private final int totalNumOfCards = Suit.values().length * Rank.values().length;
 
	public ArrayList<Card> deck = new ArrayList<>();
	public ArrayList<Card> discardPile = new ArrayList<>();
	
	/**
	 * Build the DeckOfCards by creating and adding a Card for every combination of Suit and Rank
	 * Adding some Reqs and Specs here that will be cleaned up later
	 * Requirements:
	 * 1. It's on the user of this class to verify the DeckOfCards is not empty (out of cards) before calling get methods.
	 * 		I didn't want to force the user to load their code with try/catch statements when they should design their game
	 * 		with the intent that the deck never ran out, and if it did the discard pile would be added back into the deck
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
	public int getNumCardsInDeck() {
		return deck.size();
	}
	
	@Override
	public int getNumCardsNotInDeck() {
		return totalNumOfCards - deck.size();
	}
	
	@Override
	public int getNumCardsInDiscardPile() {
		return discardPile.size();
	}
	
	@Override
	public int getNumCardsNotInDeckOrDiscard() {
		return (totalNumOfCards - (discardPile.size() + deck.size()));
	}

	@Override
	public String seeCardByIndex(int index) {
		index--;
		Card card = deck.get(index);
		return card.toString();
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
		index--;
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
	
	@Override
	public Optional<Card> getExactCard(Suit suit, Rank rank) {
		
		Optional<Card> card = Optional.empty();
		Card cardRequested = new Card(suit, rank);
		int index = deck.indexOf(cardRequested);
		if (index == -1) {
			return card;
		} else {
			deck.remove(index);
			card = Optional.of(cardRequested);
			return card;
		}
	}

	public Card[] getExactCards(SuitPair[] requestedCards) {
		
		Card[] returnedCards = new Card[requestedCards.length];
		int count = 0;
		
		for ( SuitPair pair : requestedCards) {
			Card requestedCard = new Card(pair.getSuit(), pair.getRank());
			int cardIndex = deck.indexOf(requestedCard);
			if( cardIndex >= 0 ) {
				returnedCards[count] = requestedCard;
				deck.remove(cardIndex);
				count ++;
			}
		}
		return returnedCards;
	}

	@Override
	public Card[] getCardsTop(int numOfCards) {
		Card[] cards = new Card[numOfCards];
		int count = 0;
		while ( count < numOfCards ) {
			//Always take the top card [0]
			cards[count] = deck.get(0);
			deck.remove(0);
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
		// Subtract the index by 1 so it works on 0-51 and not 1-52	
		// Array must be sorted so it always gets the last Card in the deck first
		Integer[] cardsSelectedReversed = new Integer[cardsSelected.length];
		for ( int i = 0; i < cardsSelected.length; i++) {
			int cardIndex = cardsSelected[i];
			cardIndex--;
			cardsSelectedReversed[i] = cardIndex;
		}
		// Had to turn into Integer to use Collections.reverseOrder
		Arrays.sort(cardsSelectedReversed, Collections.reverseOrder());
		
		int count = 0;
		for ( Integer i : cardsSelectedReversed) {
			Card card = deck.get(i);
			deck.remove(i);
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
	
	@Override
	public void addCardBottom(Card card) {
		for ( Card deckCard : deck ) {
			if ( deckCard.equals(card) ) {
				System.out.println("Can't add duplicate card: " + card);
			} 
		}
		deck.add(deck.size(), card);
	}
	
	@Override
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
	
	@Override
	public void discardCard(Card card) {
		discardPile.add(card);
	}
	
	
	@Override
	public void discardCards(Card[] cards) {
		discardPile.addAll(Arrays.asList(cards));
	}
	
	@Override
	public void addDiscardPileToDeck() {
		deck.addAll(0, discardPile);
	}

	@Override
	public int hashCode() {
		int hashCode = 0;
		for ( Card card : deck ) {
			hashCode += card.hashCode();
		}
		return hashCode;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof DeckOfCards) ) return false;
		DeckOfCards other = (DeckOfCards)o;
		if (this.getNumCardsInDeck() != other.getNumCardsInDeck()) {
			return false;
		}
		for (int i = 1; i <= this.getNumCardsInDeck(); i++) {
			if (!this.seeCardByIndex(i).equals(other.seeCardByIndex(i))) {
				return false;
			}
		}
		return true;
		}		

	@Override
	public String toString() {
		return deck.toString();
		
	}
	
	@Override
	public Iterator<Card> iterator() {
		return new Iterator<> () {
			private final Iterator<Card> iter = deck.iterator();
			
			@Override
			public boolean hasNext() {
				return iter.hasNext();
			}
			
			@Override
			public Card next() {
				return iter.next();
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException("no changes allowed");
			}
		};
	}
	
	/**
	 * This class is only used to request Cards by adding SuitPair to a SuitPair[]
	 * which is used as a parameter in getExactCards(SuitPair[])
	 */
	public class SuitPair {
		
		public final Suit first;
		public final Rank second;
		
		String suitString;
		String rankString;

		
		public SuitPair(Suit suit, Rank rank) {
			
			suitString = suit.toString();
			rankString = rank.toString();
			this.first = suit;
			this.second = rank;
		}

		public Suit getSuit() {
			return this.first;
		}
		
		public Rank getRank() {
			return this.second;
		}
		
		public String toString() {
			return rankString + "_" + suitString;
		}
		
		@Override
		public int hashCode() {
			return ( this.first.hashCode() + this.second.hashCode() );
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this) return true;
			if (!(o instanceof SuitPair) ) return false;
			SuitPair other = (SuitPair)o;
			return (this.suitString.equals(other.suitString) && this.rankString.equals(other.rankString));
		}
	}
}

