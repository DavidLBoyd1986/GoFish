/**
 * 
 */
package GoFish;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.Card.Rank;

/**
 * @author David
 *
 */
public class GoFish implements GoFishInterface {

	public static ArrayList<Player> players = new ArrayList<Player>();
	public int numOfPlayers = 4;
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
