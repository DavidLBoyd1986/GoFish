package GoFish;

import java.util.ArrayList;
import java.util.HashMap;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.Card.Rank;

import GoFish.Player.Result;

public class HardPlayer extends Player implements PlayerInterface {

	ArrayList<Result> resultList;
	
	public HardPlayer(String initName, int initPosition) {
		super(initName, initPosition);
		name = initName;
		position = initPosition;
		hand = new ArrayList<Card>();
		books = new HashMap<Rank, Card[]>();
		bookCheck = new HashMap<Rank, Integer>();
		ID = name;
		resultList = new ArrayList<Result>();
	}
	
	@Override
	public void updateResultList(Result result) {
		resultList.add(result);
	}
}
