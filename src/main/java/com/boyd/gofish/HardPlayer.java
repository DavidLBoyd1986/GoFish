package com.boyd.gofish;

import com.boyd.deckofcards.Card;
import com.boyd.deckofcards.Card.Rank;

import java.util.ArrayList;
import java.util.HashMap;

public class HardPlayer extends Player implements PlayerInterface {

	ArrayList<Result> resultList;
	
	public HardPlayer(String initName, int initPosition, int initGameDelay) {
		super(initName, initPosition, initGameDelay);
		name = initName;
		position = initPosition;
		hand = new ArrayList<>();
		books = new HashMap<>();
		bookCheck = new HashMap<>();
		ID = name;
		resultList = new ArrayList<>();
	}
	
	@Override
	public void updateResultList(Result result) {
		resultList.add(result);
	}
}
