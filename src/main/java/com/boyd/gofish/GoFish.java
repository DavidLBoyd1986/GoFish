/**
 * 
 */
package com.boyd.gofish;

//import java.util.HashMap;

import com.boyd.gofish.Player.Result;
import com.boyd.deckofcards.DeckOfCards;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author David
 *
 */
public class GoFish implements GoFishInterface {

	public ArrayList<Player> players;
	public ArrayList<Player> activePlayers;
	public int numOfPlayers;
	public DeckOfCards deck;
	public boolean gameOver;
	public ArrayList<String> easyPlayerNames;
	public ArrayList<String> averagePlayerNames;
	public ArrayList<String> hardPlayerNames;
	int gameSpeed;
	
	
	
	public GoFish() {
		players = new ArrayList<>();
		activePlayers = new ArrayList<>();
		easyPlayerNames = new ArrayList<>();
		averagePlayerNames = new ArrayList<>();
		hardPlayerNames = new ArrayList<>();
		gameOver = false;
		deck = new DeckOfCards();
		deck.shuffleDeck();
		easyPlayerNames.addAll(Arrays.asList("Jimbo", "Susie", "Rose", 
				"Butch", "Cletus", "Patty", "Randy"));
		averagePlayerNames.addAll(Arrays.asList("Dylan", "Eric", "Ryan", 
				"Amanda", "April", "Ashley", "Derek"));
		hardPlayerNames.addAll(Arrays.asList("Urkel", "Albert", "Isaac", 
				"Ada", "Emily", "Elizabeth", "Alan"));
		int gameSpeed = 1;
	}
	
	@Override
	public void gameDelay(int i) {
		//Add a delay so the game has flow
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch(InterruptedException ex)
		{ Thread.currentThread().interrupt(); }
	}
	
	public String getPlayerName(String difficulty) {
		Random random = new Random();
		String playerName = "";
		difficulty = difficulty.toLowerCase();
		if (difficulty.equals("easy")) {
			playerName = easyPlayerNames.get(
					random.nextInt(easyPlayerNames.size()));
			easyPlayerNames.remove(playerName);
		} else if (difficulty.equals("average")) {
			playerName = averagePlayerNames.get(
					random.nextInt(averagePlayerNames.size()));
			averagePlayerNames.remove(playerName);
		} else if (difficulty.equals("hard")) {
			playerName = hardPlayerNames.get(
					random.nextInt(hardPlayerNames.size()));
			hardPlayerNames.remove(playerName);
		}
		return playerName;
	}
	
	@Override
	public void addPlayer(Player player) {
		this.players.add(player);
		this.activePlayers.add(player);
	}
	
	@Override
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	@Override
	public ArrayList<Player> getActivePlayers() {
		return activePlayers;
	}
	
	@Override
	public void checkActivePlayers() {
		//Removes player from activePlayers if they are out of cards
		for (Iterator<Player> iterator = this.getActivePlayers().iterator(); 
				iterator.hasNext();) {
			Player player = iterator.next();
			if(player.getHand().size() == 0) {
				iterator.remove();
				System.out.println("\n" + player.getID() + 
						" is no longer active in the game.\n");
			}
		}
	}

	public void getNumOfPlayersInput(Scanner inputStream) {
		int numPlayerInput = 0;
		System.out.println("Enter number of players, must be between 2 - 7: ");
		boolean inputValid = false;

		while (!inputValid) {
			numPlayerInput = inputStream.nextInt();
			if ((numPlayerInput >= 2) && (numPlayerInput <= 7)) {
				inputValid = true;
			} else {
				System.out.println(
						"Invalid input! Please enter a number between 2-7");
			}
		}
		if (numPlayerInput == 0) {
			throw new RuntimeException("Failed during user input numOfPlayers");
		}
		setNumOfPlayers(numPlayerInput);
	}

	@Override
	public void setNumOfPlayers(int numPlayerInput) {
		numOfPlayers = numPlayerInput;
	}

	public int getGameDelay(Scanner inputStream) {
		String gameDelayInput = "";
		System.out.println("Enter desired game speed - slow, normal, fast (default is normal): ");
		boolean inputValid = false;

		while (!inputValid) {
			gameDelayInput = inputStream.nextLine();
			gameDelayInput = gameDelayInput.toUpperCase();
			gameDelayInput = gameDelayInput.trim();
			if ((gameDelayInput.equals("SLOW")) ||
					(gameDelayInput.equals("NORMAL")) ||
					(gameDelayInput.equals("FAST"))) {
				inputValid = true;
			} else if (gameDelayInput == "") {
				gameSpeed = 1;
				return 2;
			} else {
				System.out.println(
						"Invalid input! Please enter: slow, normal, or fast");
			}
		}
		if (gameDelayInput.equals("SLOW")) {
			gameSpeed = 2;
			return 3;
		} else if (gameDelayInput.equals("NORMAL")) {
			gameSpeed = 1;
			return 2;
		} else if (gameDelayInput.equals("FAST")) {
			gameSpeed = 0;
			return 1;
		}
		return 2;
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
				winner = player.getID();
			} else if (player.getNumOfBooks() > numOfBooks) {
				numOfBooks = player.getNumOfBooks();
				winner = player.getID();
			}
		}
		return winner;
	}

	@Override
	public void dealCards() {
		//Decide how many cards to deal based on numOfPlayers
		int cardsToDeal = 0;
		if (numOfPlayers < 4) {
			cardsToDeal = 7;
		} else if ((numOfPlayers >= 4) && (numOfPlayers <= 7)) {
			cardsToDeal = 5;
		} else {
			System.out.println("numOfPlayers is not valid. Maybe add an exception  here");
		}
		
		for (int i = 1; i <= cardsToDeal; i++) {
			for (Player player : players) {
				player.drawCard(deck);
			}
		}
	}
	
	@Override
	public boolean isGameOver() {
		// Look at all the players books to see if all the books have been created
		int bookCount = 0;
		for (Player player : players) {
			bookCount += player.getNumOfBooks();
		}
		if (bookCount == 13) {
			return true;
		} else {
			return false;
		}
	}

	public void outputGameResults(ArrayList<Player> players) {
		String winner = getWinner();	
		System.out.println("Final Results:");
		for (Player player : this.getPlayers()) {
			System.out.println(player.getID() + "'s Books: " +
					player.getBooks());
		}
		System.out.println("\nThe winner is: " + winner);
		System.out.println("**The player in the later position "
				+ "always wins ties**");
	}
	
	public void outputTurnInformation(Player player) {
		gameDelay(gameSpeed);
		//Regular Output
		System.out.println(player.ID + " has " 
				+ player.getHand().size() + " cards.");
		System.out.println(player.ID + "'s Books: " +
						   player.getBooks());
		System.out.println(this.getActivePlayers());
	}
	
	public void createGame() {
		
		Optional<Result> result;
		gameOver = false;	
		//Decide order around table
			// TODO Randomize the Player positions
		//Get User input
		Scanner inputStream = new Scanner(System.in);
		this.getNumOfPlayersInput(inputStream);
		String inputDump = inputStream.nextLine(); // gets rid of initial 'Invalid Rank Selection'
		int playerSpeed = getGameDelay(inputStream);
		// Create User's Player
		InteractivePlayer user = new InteractivePlayer("You", 1, playerSpeed, inputStream);
		this.addPlayer(user);
		//Create Computer Players
		for (int i = 2; i <= numOfPlayers; i++) {
			String playerName = this.getPlayerName("average");
			this.addPlayer(new AveragePlayer(playerName, i, playerSpeed));
		}		
		//Decide dealer
			// TODO dealer will always be Randomized Player 1, need to implement that before this can be done
		//Deal Cards
		this.dealCards();
		//Do initialBookCheck
		for (Player player : players) {
			player.doInitialBookCheck();
		}
		//Decide who goes first (will be person left of dealer)
			// TODO need to decide dealer before I can do this
		playGameLoop(); // Game loops until over
		outputGameResults(players);
	}
	
	public void createGameTest() {
		gameOver = false;
		//Decide order around table
			// TODO Randomize the Player positions
		//Set number of players
		this.setNumOfPlayers(5);
		gameSpeed = 0;
		//Create Computer Players
		for (int i = 1; i <= numOfPlayers; i++) {
			String playerName = this.getPlayerName("average");
			this.addPlayer(new AveragePlayer(playerName, i, 0));
		}
		//Decide dealer
			// TODO dealer will always be Randomized Player 1, need to implement that before this can be done	
		//Deal Cards
		this.dealCards();	
		//Do initialBookCheck
		for (Player player : players) {
			player.doInitialBookCheck();
		}
		//Decide who goes first (will be person left of dealer)
			// TODO need to decide dealer before I can do this
		//Start game loop (while isGameOver is False, continue gameloop)
		playGameLoop();
		outputGameResults(players);
	}


	private void playGameLoop() {
		while (!gameOver) {
			Optional<Result> result;
			//The game loops through the players until gameOver condition is met
			for (Player player : players) {
				//If player is out of Cards and Deck is empty they aren't active
				if (!this.getActivePlayers().contains(player)) {
					continue;
				}
				// player will repeatTurn when there request is correct
				player.setRepeatTurn(true);
				while (player.repeatTurn) {
					outputTurnInformation(player);
					//Take the turn, and update resultList if necessary
					result = player.takeTurn(this.getActivePlayers(), deck);
					for (Player activePlayer: this.getActivePlayers()) {
						if (activePlayer instanceof HardPlayer) {
							activePlayer.updateResultList(result.get());
						}
					}
					gameOver = isGameOver();
					//If player and deck are both out of cards: remove player
					if(deck.getNumCardsInDeck() == 0) {
						this.checkActivePlayers();
					}
					//Player could have created a book to lose last cards
					if (!this.getActivePlayers().contains(player)) {
						player.repeatTurn = false;
						//continue;
					}
					System.out.println(" ");
				}
			}
		}
	}

	public static void main(String[] args) {
		GoFish game1 = new GoFish();
		game1.createGame();
	}
}
