package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.DeckOfCards;
import com.boyd.deckofcards.Card.Rank;

import GoFish.GoFish;
import GoFish.Player;

public class GoFishTest {



	/*
     * Testing strategy
     * ==================
     * 
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     * 
     * Player Testing:
     * 
     * Test 1 - testAddPlayers() returns True if addPlayers adds the right numOfPlayers
     * 
     * Test 2 - testGetPlayers() returns True if getPlayers returns an accurate playerList
     * 
     * Test 3 - testSetNumOfPlayers() returns True if setNumOfPlayers responds correctly to input
     * 
     * Test 4 - testDealCardsTwoAndThree() - returns True if dealCards deals 7 cards to each Player when 2 or 3 people are playing
     * 
     * Test 5 - testDealCardsFourPlus() - returns True if dealCards deals 5 cards to each Player when 4 or more people are playing
     * 
     * Test 6 - testIsGameOver() - returns True if isGameOver() can accurately detect when a game is over
     * 
     * Test 7 - testGetWinnerClearCut() - returns True if getWinner() accurately determines the winner
     * 
     * Test 8 - testGetWinnerTie() - returns True if getWinner() accurately determines the winner in case of a tie
     * 
     * Test 9 - testCreateGameTest() - returns True if createGameTest runs smoothly and ends with a clear winner
     */
	

	Player testPlayer1;
	Player testPlayer2;
	Player testPlayer3;
	Player testPlayer4;
	ArrayList<Player> testPlayers;

	HashMap<Rank, Integer> bookCheck;
	HashMap<Rank, Card[]> books;
	
	@BeforeEach
	public void setup() throws Exception {
		testPlayer1 = new Player("Test", 1);
		testPlayer2 = new Player("Test", 2);
		testPlayer3 = new Player("Test", 3);
		testPlayer4 = new Player("Test", 4);
		testPlayers = new ArrayList<Player>();
		testPlayers.add(testPlayer1);
		testPlayers.add(testPlayer2);
		testPlayers.add(testPlayer3);
		testPlayers.add(testPlayer4);
	}
	
	@Test //Test 1
	void testAddPlayers() {
		GoFish game1 = new GoFish();
		for (Player player : testPlayers) {
			game1.addPlayer(player);
		}
		assert(game1.players.size() == 4);
	}
	
	@Test //Test 2
	void testGetPlayers() {
		GoFish game1 = new GoFish();
		for (Player player : testPlayers) {
			game1.addPlayer(player);
		}
		assert(game1.getPlayers().size() == 4);
	}
	
	// Below works, I just can't figure out how to automatically set input to an int
//	@Test //Test 3
//	void testSetNumOfPlayers() {
//		GoFish game1 = new GoFish();
//		//Integer testInput = 4;
//		Scanner inputNumOfPlayers = new Scanner(System.in);
//		game1.setNumOfPlayers(inputNumOfPlayers);
//		assert((game1.getNumOfPlayers() >= 2) && (game1.getNumOfPlayers() <= 7));
//	}
	
	@Test //Test 4
	void testDealCardsTwoAndThree() {
		GoFish game1 = new GoFish();
		for (Player player : testPlayers) {
			game1.addPlayer(player);
		}
		game1.players.remove(1);
		game1.numOfPlayers = 3;
		assert(game1.getPlayers().size() == 3);
		game1.dealCards();
		assert(game1.getPlayers().size() == 3);
		for (Player player : game1.getPlayers()) {
			assert(player.getHand().size() == 7);
		}
		
	}
	
	@Test //Test 5
	void testDealCardsFourPlus() {
		GoFish game1 = new GoFish();
		for (Player player : testPlayers) {
			game1.addPlayer(player);
		}
		game1.numOfPlayers = 4;
		assert(game1.getPlayers().size() == 4);
		game1.dealCards();
		assert(game1.getNumOfPlayers() == 4);
		for (Player player : game1.getPlayers()) {
			assert(player.getHand().size() == 5);
		}
	}
	
	@Test //Test 6
	void testIsGameOver() {
		GoFish game1 = new GoFish();
		for (Player player : testPlayers) {
			game1.addPlayer(player);
		}
		game1.numOfPlayers = 4;
		
		//Give every player all the cards of a certain rank
		for (Card card : game1.deck) {
			if ( (card.getRank().equals(Rank.ACE)) ||
				 (card.getRank().equals(Rank.KING)) ||
				 (card.getRank().equals(Rank.QUEEN)) ||
				 (card.getRank().equals(Rank.JACK)) ||
				 (card.getRank().equals(Rank.TEN))
				) {game1.players.get(0).hand.add(card);
			} else if ( (card.getRank().equals(Rank.NINE)) ||
				      (card.getRank().equals(Rank.EIGHT)) ||
				      (card.getRank().equals(Rank.SEVEN))
				) {game1.players.get(1).hand.add(card);
			} else if ( (card.getRank().equals(Rank.SIX)) ||
				      (card.getRank().equals(Rank.FIVE)) ||
				      (card.getRank().equals(Rank.FOUR))
				) {game1.players.get(2).hand.add(card);
			} else if ( (card.getRank().equals(Rank.THREE)) ||
				      (card.getRank().equals(Rank.TWO))
				) {game1.players.get(3).hand.add(card);
			}
		}
		//Create all the books from the cards given
		for (Player player : game1.getPlayers()) {
			int playerNumber = player.getPosition();
			if (playerNumber == 1) {
				player.createBook(Rank.ACE);
				player.createBook(Rank.KING);
				player.createBook(Rank.QUEEN);
				player.createBook(Rank.JACK);
				player.createBook(Rank.TEN);
				assertFalse(game1.isGameOver());
			} else if (playerNumber == 2) {
				player.createBook(Rank.NINE);
				player.createBook(Rank.EIGHT);
				player.createBook(Rank.SEVEN);
				assertFalse(game1.isGameOver());
			} else if (playerNumber == 3) {
				player.createBook(Rank.SIX);
				player.createBook(Rank.FIVE);
				player.createBook(Rank.FOUR);
				assertFalse(game1.isGameOver());
			} else if (playerNumber == 4) {
				player.createBook(Rank.THREE);
				player.createBook(Rank.TWO);
			}
		}		
		//Test it
		assertTrue(game1.isGameOver());
	}
	
	@Test //Test 7
	void testGetWinnerClearCut() {
		GoFish game1 = new GoFish();
		for (Player player : testPlayers) {
			game1.addPlayer(player);
		}
		game1.numOfPlayers = 4;
		
		//Give every player all the cards of a certain rank
		for (Card card : game1.deck) {
			if ( (card.getRank().equals(Rank.ACE)) ||
				 (card.getRank().equals(Rank.KING)) ||
				 (card.getRank().equals(Rank.QUEEN)) ||
				 (card.getRank().equals(Rank.JACK)) ||
				 (card.getRank().equals(Rank.TEN))
				) {game1.players.get(0).hand.add(card);
			} else if ( (card.getRank().equals(Rank.NINE)) ||
				      (card.getRank().equals(Rank.EIGHT)) ||
				      (card.getRank().equals(Rank.SEVEN))
				) {game1.players.get(1).hand.add(card);
			} else if ( (card.getRank().equals(Rank.SIX)) ||
				      (card.getRank().equals(Rank.FIVE)) ||
				      (card.getRank().equals(Rank.FOUR))
				) {game1.players.get(2).hand.add(card);
			} else if ( (card.getRank().equals(Rank.THREE)) ||
				      (card.getRank().equals(Rank.TWO))
				) {game1.players.get(3).hand.add(card);
			}
		}
		//Create all the books from the cards given
		for (Player player : game1.getPlayers()) {
			int playerNumber = player.getPosition();
			if (playerNumber == 1) {
				player.createBook(Rank.ACE);
				player.createBook(Rank.KING);
				player.createBook(Rank.QUEEN);
				player.createBook(Rank.JACK);
				player.createBook(Rank.TEN);
				assertFalse(game1.isGameOver());
			} else if (playerNumber == 2) {
				player.createBook(Rank.NINE);
				player.createBook(Rank.EIGHT);
				player.createBook(Rank.SEVEN);
				assertFalse(game1.isGameOver());
			} else if (playerNumber == 3) {
				player.createBook(Rank.SIX);
				player.createBook(Rank.FIVE);
				player.createBook(Rank.FOUR);
				assertFalse(game1.isGameOver());
			} else if (playerNumber == 4) {
				player.createBook(Rank.THREE);
				player.createBook(Rank.TWO);
			}
		}		
		//Test it
		assert(game1.getWinner().equals("Test1"));
	}
	
	@Test //Test 7
	void testGetWinnerTie() {
		GoFish game1 = new GoFish();
		for (Player player : testPlayers) {
			game1.addPlayer(player);
		}
		game1.numOfPlayers = 4;
		
		//Give every player all the cards of a certain rank
		for (Card card : game1.deck) {
			if ( (card.getRank().equals(Rank.ACE)) ||
				 (card.getRank().equals(Rank.KING)) ||
				 (card.getRank().equals(Rank.QUEEN)) ||
				 (card.getRank().equals(Rank.JACK))
				) {game1.players.get(0).hand.add(card);
			} else if ( (card.getRank().equals(Rank.NINE)) ||
				      (card.getRank().equals(Rank.EIGHT)) ||
				      (card.getRank().equals(Rank.SEVEN))||
					  (card.getRank().equals(Rank.TEN))
				) {game1.players.get(1).hand.add(card);
			} else if ( (card.getRank().equals(Rank.SIX)) ||
				      (card.getRank().equals(Rank.FIVE)) ||
				      (card.getRank().equals(Rank.FOUR))
				) {game1.players.get(2).hand.add(card);
			} else if ( (card.getRank().equals(Rank.THREE)) ||
				      (card.getRank().equals(Rank.TWO))
				) {game1.players.get(3).hand.add(card);
			}
		}
		//Create all the books from the cards given
		for (Player player : game1.getPlayers()) {
			int playerNumber = player.getPosition();
			if (playerNumber == 1) {
				player.createBook(Rank.ACE);
				player.createBook(Rank.KING);
				player.createBook(Rank.QUEEN);
				player.createBook(Rank.JACK);
				assertFalse(game1.isGameOver());
			} else if (playerNumber == 2) {
				player.createBook(Rank.NINE);
				player.createBook(Rank.EIGHT);
				player.createBook(Rank.SEVEN);
				player.createBook(Rank.TEN);
				assertFalse(game1.isGameOver());
			} else if (playerNumber == 3) {
				player.createBook(Rank.SIX);
				player.createBook(Rank.FIVE);
				player.createBook(Rank.FOUR);
				assertFalse(game1.isGameOver());
			} else if (playerNumber == 4) {
				player.createBook(Rank.THREE);
				player.createBook(Rank.TWO);
			}
		}		
		//Test it
		System.out.println(game1.getWinner());
		assert(game1.getWinner().equals("Test2"));
	}
	
	@Test //Test9
	public void testCreateGameTest() {
		GoFish game1 = new GoFish();
		game1.createGameTest();
	}
}
