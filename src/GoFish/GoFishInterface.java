package GoFish;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.Card.Rank;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public interface GoFishInterface {

	/**
	 * Creates a game of GoFish
	 * 
	 */
	public void createGame();
	
	/**
	 * Creates a game of GoFish for testing full functionality
	 * Only computers play 
	 */
	public void createGameTest();
	
	/**
	 * 	/**
	 * Puts some delay in the Computers actions to give the game flow
	 * And let the Player see who requests what from whom.
	 *
	 * @param i - number of seconds to wait
	 */
	public void gameDelay(int i);
	
	/**
	 * Adds a player to the game of GoFish. Should only be used at the start
	 * @param player - player to be added
	 */
	public void addPlayer(Player player);
	
	/**
	 * Gets the players list
	 * @return players - an ArrayList of all the Players in the game
	 */
	public ArrayList<Player> getPlayers();
	
	/**
	 * Gets the active players list
	 * @return players - an ArrayList of all the Players in the game
	 */
	public ArrayList<Player> getActivePlayers();
	
	/**
	 * This is only called if the deck is empty!!
	 * It checks if any players are out of cards, and if so removes them from 
	 * active players.
	 * 
	 */
	public void checkActivePlayers();
	
	/**
	 * Set the number of Players for go fish
	 * @param initNumOfPlayers - int between 2-5;
	 */
	public void setNumOfPlayers(Scanner inputNumOfPlayer);
	
	/**
	 * Gets the num of Players in the game, even one's who have been eliminated because there are no cards left
	 * 
	 * @return - int representing the number of Players in the game.
	 */
	public int getNumOfPlayers();
	
	/**
	 * Decides who the dealer is by dealing one card face up to every player, the player with the lowest card is set to be the dealer
	 * 
	 * Sets the variable 'dealer' to be that player
	 */
	//public void decideDealer();
	
	/**
	 * Deal the Cards to the Players, the amount of Cards the Players get depends on the number of Players
	 * 
	 * Moves Cards from the DeckOfCards to the Player's hands
	 */
	public void dealCards();
	
	/**
	 * See if all 13 books have been won, and the game is over
	 * 
	 * @return - boolean showing if all 13 books have been won, and the game is over
	 */
	public boolean isGameOver();
	
	/**
	 * Determine the winner of the game. Should only be run after isGameOver() is true
	 * 
	 * @return - Player that is the winner of the game, who has the most books
	 */
	public String getWinner();
	
	/**
	 * Creates a Player character who will play the game
	 * 
	 * @param skillLevel - enum representing the skill of the Player
	 * EASY - The Player never remembers anything about what Cards were asked for from whom
	 * MEDIUM - The Player remembers what Cards he asked for from other Players
	 * HARD - The Player remembers what Cards anyone asked for from other Players
	 * @return - Player object that can play the game
	 */
	//public Player createPlayer(int skillLevel);
	
	/**
	 * Places Players around a table in clockwise fashion
	 * 
	 * Positions are 1 - getNumOfPlayers() with 1 being the dealer
	 */
	//public void placePlayers();
	

}
