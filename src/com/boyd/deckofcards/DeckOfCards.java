package com.boyd.deckofcards;

import java.util.ArrayList;
import java.util.Random;

class DeckOfCards implements DeckOfCardsInterface {

	private final int numOfCards = 52;
	public enum Suit {DIAMOND, HEART, CLUB, SPADE};
	public enum Rank {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE};
	public ArrayList<Card> deck = new ArrayList<Card>();
	
	public DeckOfCards() {

		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values() ) {
				Card card = new Card(suit, rank);
				deck.add(card);
			}
		}
	}

	@Override
	public void dealCards(int numOfPlayers, int numOfCards) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNumCards() {
		return deck.size();
	}
	
	@Override
	public Card getTopCard() {
		return deck.get(0);
	}

	@Override
	public Card getBottomCard() {
		return deck.get(deck.size()-1);
	}
	
	@Override
	public Card getSpecificCard(int index) {
		return deck.get(index);
	}
	
	@Override
	public Card getRandomCard() {
		Random random = new Random();
		int randomIndex = random.nextInt(deck.size());
		return deck.get(randomIndex);
	}
	
	@Override
	public Card shuffleDeck() {
		// TODO Auto-generated method stub
	}
	
	public String toString() {
		return deck.toString();
	}
	
	public static void main(String[] args) {
		DeckOfCards deck = new DeckOfCards();
		System.out.println(deck);
		Card topCard = deck.getTopCard();
		System.out.println("TopCard = " + topCard);
		Card bottomCard = deck.getBottomCard();
		System.out.println("BottomCard = " + bottomCard);
		Card specificCard = deck.getSpecificCard(4);
		System.out.println("SpecificCard = " + specificCard);
		Card randomCard = deck.getRandomCard();
		System.out.println("RandCard = " + randomCard);

	}

	
}
