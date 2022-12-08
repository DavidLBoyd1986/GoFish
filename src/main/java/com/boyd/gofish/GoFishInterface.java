package com.boyd.gofish;

import java.util.ArrayList;
import java.util.Scanner;

public interface GoFishInterface {

	/**
	 * Creates a game of GoFish
	 * 
	 */
	void createGame();
	
	/**
	 * Creates a game of GoFish for testing full functionality
	 * Only computers play 
	 */
	void createGameTest();
	
	/**
	 * 	/**
	 * Puts some delay in the Computers actions to give the game flow
	 * And let the Player see who requests what from whom.
	 *
	 * @param i - number of seconds to wait
	 */
	void gameDelay(int i);
	
	/**
	 * Adds a player to the game of GoFish. Should only be used at the start
	 * @param player - player to be added
	 */
	void addPlayer(Player player);
	
	/**
	 * Gets the players list
	 * @return players - an ArrayList of all the Players in the game
	 */
	ArrayList<Player> getPlayers();
	
	/**
	 * Gets the active players list
	 * @return players - an ArrayList of all the Players in the game
	 */
	ArrayList<Player> getActivePlayers();
	
	/**
	 * This is only called if the deck is empty!!
	 * It checks if any players are out of cards, and if so removes them from 
	 * active players.
	 * 
	 */
	void checkActivePlayers();
	
	/**
	 * Set the number of Players for go fish
	 * @param inputStream - scanner input stream;
	 */
	void getNumOfPlayersInput(Scanner inputStream);

	/**
	 * Set the number of Players for go fish
	 * @param numPlayerInput - int of the num of players the user inputted;
	 */
	void setNumOfPlayers(int numPlayerInput);

	/**
	 * Gets the num of Players in the game, even one's who have been eliminated because there are no cards left
	 * 
	 * @return - int representing the number of Players in the game.
	 */
	int getNumOfPlayers();
	
	/**
	 * Decides who the dealer is by dealing one card face up to every player, the player with the lowest card is set to be the dealer
	 * Sets the variable 'dealer' to be that player
	 */
	//public void decideDealer();
	
	/**
	 * Deal the Cards to the Players, the amount of Cards the Players get depends on the number of Players
	 * Moves Cards from the DeckOfCards to the Player's hands
	 */
	void dealCards();
	
	/**
	 * See if all 13 books have been won, and the game is over
	 * 
	 * @return - boolean showing if all 13 books have been won, and the game is over
	 */
	boolean isGameOver();
	
	/**
	 * Determine the winner of the game. Should only be run after isGameOver() is true
	 * 
	 * @return - Player that is the winner of the game, who has the most books
	 */
	String getWinner();
	
	void outputGameResults(ArrayList<Player> players);
	
	void outputTurnInformation(Player player);
	
	/**
	 * Places Players around a table in clockwise fashion
	 * Positions are 1 - getNumOfPlayers() with 1 being the dealer
	 */
	//public void placePlayers();
	

}
