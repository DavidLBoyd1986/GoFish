package GoFish;

import java.util.Scanner;

import GoFish.Player.Result;

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
	 * Gets the user's input to decide the player type (easy, average, hard)
	 * @param inputStream - scanner object
	 * @return - String of player's type.
	 */
	public String getPlayerType(Scanner inputStream, int opponentNumber);
	
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
	 * Prints the game results to the console
	 * 
	 * @param players - List of the Players in the game
	 */
	public void outputGameResults(ArrayList<Player> players);
	
	/**
	 * Prints the turn results to the console
	 * 
	 * @param player - Player who just took the turn
	 */
	public void outputTurnInformation(Player player);
	
	/**
	 * Removes Ranks that are Books from Result List since they are out of game
	 * 
	 * @param players - List of Players in the games
	 * @return resultList - Updated resultList with Ranks removed that are books
	 */
	public void removeRankFromResults(ArrayList<Player> players);

	/**
	 * Places Players around a table in clockwise fashion
	 * 
	 * Positions are 1 - getNumOfPlayers() with 1 being the dealer
	 */
	//public void placePlayers();
	

}
