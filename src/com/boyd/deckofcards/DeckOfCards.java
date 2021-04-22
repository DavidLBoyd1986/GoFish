package com.boyd.deckofcards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

class DeckOfCards implements DeckOfCardsInterface {

	private final int totalNumOfCards = 52;
	public enum Suit {DIAMOND, HEART, CLUB, SPADE};
	public enum Rank {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE};
	public ArrayList<Card> deck = new ArrayList<Card>();
	
	public DeckOfCards() {

		for ( Suit suit : Suit.values() ) {
			for ( Rank rank : Rank.values() ) {
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
	public Card getSpecificCard(int index) {
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
	public Card[] getCardsSpecific(int[] cardsSelected) {
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
		Card[] cardsSpecific = deck.getCardsSpecific(cardsToGet);
		System.out.println(cardsSpecific);
		for ( Card card : cardsSpecific) {
			System.out.println(card);
		}
		System.out.println(deck);
		
	}

	
}
