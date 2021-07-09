/**
 * 
 */
package GoFish;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.*;

/**
 * @author David
 *
 */
public class GoFish implements GoFishInterface {

	public ArrayList<Player> players;
	public int numOfPlayers;
	public static DeckOfCards deck = new DeckOfCards();
	public boolean gameOver;
	/**
	 * 
	 */
	public GoFish() {
		players = new ArrayList<Player>();
		numOfPlayers = 4;
		gameOver = false;
	}
	
	@Override
	public void setNumOfPlayers() {
		System.out.println("Enter a number of players (2 <= num <= 5):");
		Scanner scPlayers = new Scanner(System.in);
		numOfPlayers = scPlayers.nextInt();
		scPlayers.close();
	}
	
	@Override
	public int getNumOfPlayers() {
		return numOfPlayers;
	}
	
	@Override
	public String getWinner() {
		int numOfBooks = 0;
		String winner = "";
		for (Player player : players) {
			if (player.getNumOfBooks() == numOfBooks) {
				// TODO Need to decide how to break a tie
				winner = player.getName();
			} else if (player.getNumOfBooks() > numOfBooks) {
				numOfBooks = player.getNumOfBooks();
				winner = player.getName();
			}
		}
		return winner;
	}

	@Override
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * method used to get the Player the request is made to
	 * @return - the Player the request is made to
	 */
	static public Player getPlayerSelection(ArrayList<Player> players) throws Exception{
		String playerRequest = "";
		System.out.print("Please select which player the request will be made to:");
		// Player toString will be outputted
		for (Player player : players) {
			System.out.println(player);
		}
		// The user input will be a String that matches Player toString output (Case insensitive)
		// TODO update Exception for User input
		try {
		Scanner in = new Scanner(System.in);
		playerRequest = in.next();
		in.close();
		} catch(Exception e ) {
			e.printStackTrace();
		}
		playerRequest.toLowerCase();
		for (Player player : players) {
			String playerString = player.toString();
			playerString.toLowerCase();
			if (playerString.equals(playerRequest)) {
				return player;
			}
		}
		// TODO update the thrown Exception
		throw new Exception("ERROR - No Player was returned in getPlayerSelection()");
	}
	
	public ArrayList<Player> dealCards(ArrayList<Player> players) {
		//Decide how many cards to deal based on numOfPlayers
		int cardsToDeal = 0;
		if (numOfPlayers < 4) {
			cardsToDeal = 7;
		} else if ((numOfPlayers > 3) && (numOfPlayers < 6)) {
			cardsToDeal = 5;
		} else {
			System.out.println("numOfPlayers is not valid. Maybe add an exception  here");
		}
		
		for (int i = 1; i <= cardsToDeal; i++) {
			for (Player player : players) {
				player.drawCard(deck);
			}
		}
		return players;
	}
	
	@Override
	public boolean isGameOver() {
		// Look at all the players books to see if all the books have been created
		int bookCount = 0;
		for (Player player : players) {
			bookCount += player.getNumOfBooks();
		}
		if (bookCount == 13) {
			gameOver = true;
		} else {
			gameOver = false;
		}
		return gameOver;
	}

	public void createGame() {
		
		gameOver = false;
		
		//Get num of players
		
		//Decide order around table
			// TODO Randomize the Player positions
		
		// Create User's Player
		Player user = new Player("David", 1);
		players.add(user);
		
		//Create Computer Players
		for (int i = 2; i <= numOfPlayers; i++) {
			players.add(new EasyPlayer("Easy", i));
		}
		
		//Decide dealer
			// TODO dealer will always be Randomized Player 1, need to implement that before this can be done
		
		//Deal Cards
		players = dealCards(players);
		
		//Do initialBookCheck
		for (Player player : players) {
			player.doInitialBookCheck();
		}
		
		//Decide who goes first (will be person left of dealer)
			// TODO need to decide dealer before I can do this
		
		//Start game loop (while isGameOver is False, continue gameloop)
		while (!gameOver) {
			
			for (Player player : players) {
				player.setRepeatTurn(true);
			//Player loop (while repeatTurn is true, continue playerloop)
				while (player.repeatTurn) {
					//debugging bookcheck methods
					System.out.println(player.bookCheck);
					player.takeTurn(players);
					gameOver = isGameOver();
				}
			}
		}
		
		String winner = getWinner();
		
		System.out.println("The winner is: " + winner);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GoFish game1 = new GoFish();
		game1.createGame();

	}

}
