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

	public static ArrayList<Player> players = new ArrayList<Player>();
	public int numOfPlayers = 4;
	public static DeckOfCards deck = new DeckOfCards();
	/**
	 * 
	 */
	public GoFish() {
		// TODO Auto-generated constructor stub
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
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * method used to get the Player the request is made to
	 * @return - the Player the request is made to
	 */
	static public Player getPlayerSelection() throws Exception{
		ArrayList<Player> players = GoFish.players;
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

	public void createGame(){
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
