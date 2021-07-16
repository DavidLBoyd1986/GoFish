/**
 * 
 */
package GoFish;

//import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

//import com.boyd.deckofcards.Card;
//import com.boyd.deckofcards.Card.Rank;
import com.boyd.deckofcards.*;

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
	/**
	 * 
	 */
	public GoFish() {
		players = new ArrayList<Player>();
		activePlayers = new ArrayList<Player>();
		//numOfPlayers = 4;
		gameOver = false;
		deck = new DeckOfCards();
		deck.shuffleDeck();		
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

	@Override
	public void setNumOfPlayers(Scanner inputNumOfPlayers) {
		System.out.println("Enter number of players, must be between 2 - 7: ");
		boolean inputValid = false;
		
		while (!inputValid) {
			numOfPlayers = inputNumOfPlayers.nextInt();
			if ((numOfPlayers >= 2) && (numOfPlayers <= 7)) {
				inputValid = true;
			} else {
				System.out.println(
						"Invalid input! Please enter a number between 2-7");
			}
		}
		inputNumOfPlayers.close();
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
		//Decide order around table
			// TODO Randomize the Player positions
		
		//Set Number of Players --- THIS IS BROKE. FIX!!!
		//Scanner inputNumOfPlayers = new Scanner(System.in);
		//this.setNumOfPlayers(inputNumOfPlayers);
		this.numOfPlayers = 4;
		
		// Create User's Player
		Player user = new Player("David", 1);
		this.addPlayer(user);
		
		//Create Computer Players
		for (int i = 2; i <= numOfPlayers; i++) {
			this.addPlayer(new EasyPlayer("Easy", i));
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
		while (!gameOver) {

			for (Player player : players) {
				//If player isn't still in the game continue
				if (!this.getActivePlayers().contains(player)) {
					continue;
				}
				//Player loop (while repeatTurn is true, continue playerloop)
				player.setRepeatTurn(true);
				while (player.repeatTurn) {
					//debugging bookcheck methods
					System.out.println(player.ID + " has " 
							+ player.getHand().size() + " cards in his Hand.");
//					System.out.println(player.ID + "'s Hand: "
//							+ player.getHand());
//					System.out.println(player.ID + "'s BookCheck: "
//							+ player.getBookCheck());
					System.out.println(player.ID + "'s Books: " + player.getBooks());
					System.out.println(this.getActivePlayers());
					player.takeTurn(this.getActivePlayers(), deck);
					gameOver = isGameOver();
					//If player and deck are both out of cards: remove player
					if(deck.getNumCardsInDeck() == 0) {
						this.checkActivePlayers();
					}
					//If player isn't still in the game continue
					if (!this.getActivePlayers().contains(player)) {
						player.repeatTurn = false;
						continue;
					}
					System.out.println(" ");
				}
			}
		}
		
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
	
	public void createGameTest() {
		
		gameOver = false;
		
		//Decide order around table
			// TODO Randomize the Player positions
		
		//Set Number of Players --- FIX THIS AFTER TESTING!!!!!!!!!!!!!!!!!!!!
		//Scanner inputNumOfPlayers = new Scanner(System.in);
		//this.setNumOfPlayers(inputNumOfPlayers);
		//this.numOfPlayers = 4;
		
		this.numOfPlayers = 4;
		
		//Create Computer Players
		for (int i = 1; i <= numOfPlayers; i++) {
			this.addPlayer(new EasyPlayer("Easy", i));
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
		while (!gameOver) {
			
			for (Player player : players) {
				//If player isn't still in the game continue
				if (!this.getActivePlayers().contains(player)) {
					continue;
				}
				player.setRepeatTurn(true);
				//Player loop (while repeatTurn is true, continue playerloop)
				while (player.repeatTurn) {
					//debugging bookcheck methods
					System.out.println(player.ID + " has " 
							+ player.getHand().size() + " cards in his Hand.");
//					System.out.println(player.ID + "'s Hand: "
//							+ player.getHand());
//					System.out.println(player.ID + "'s BookCheck: "
//							+ player.getBookCheck());
					System.out.println(player.ID + "'s Books: " + 
							player.getBooks());
					player.takeTurn(this.getActivePlayers(), deck);
					gameOver = isGameOver();
					//If player and deck are both out of cards: remove player
					if(deck.getNumCardsInDeck() == 0) {
						this.checkActivePlayers();
					}
					//Player could have created a book to lose last cards
					if (!this.getActivePlayers().contains(player)) {
						player.repeatTurn = false;
						continue;
					}
					System.out.println(" ");
				}
			}
		}
		
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GoFish game1 = new GoFish();
		game1.createGame();
	}

}
