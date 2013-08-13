package com.example.onirim.models;

import java.util.ArrayList;
import java.util.List;

import com.example.onirim.R;
import com.example.onirim.enums.CardColor;
import com.example.onirim.enums.CardFamily;

public class Card {
	private int id;
	private CardColor color;
	private CardFamily family;

	public Card(int id, CardColor color, CardFamily family) {
		this.id = id;
		this.color = color;
		this.family = family;
	}

	public Card(CardColor color, CardFamily family) {
		this.color = color;
		this.family = family;
	}

	public boolean isRoom() {
		return !isNightmare() && !isDoor();
	}
	
	public boolean isNightmare() {
		return family == CardFamily.NIGHTMARE;
	}
	
	public boolean isDoor() {
		return family == CardFamily.DOOR;
	}
	
	public boolean isKey() {
		return family == CardFamily.KEY;
	}
	
	public boolean isMoon() {
		return family == CardFamily.MOON;
	}
	
	public boolean isSun() {
		return family == CardFamily.SUN;
	}
	
	public boolean sameColor(Card c) {
		return color == c.getColor();
	}

	public CardColor getColor() {
		return color;
	}

	public void setColor(CardColor color) {
		this.color = color;
	}

	public CardFamily getFamily() {
		return family;
	}

	public void setFamily(CardFamily family) {
		this.family = family;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static List<Card> getAllCards() {
		List<Card> res = new ArrayList<Card>();

		// cartes rouges
		for (int i = 0; i < 3; i++) {
			res.add(new Card(R.drawable.redkey, CardColor.RED, CardFamily.KEY));
		}
		for (int i = 0; i < 4; i++) {
			res.add(new Card(R.drawable.redmoon, CardColor.RED, CardFamily.MOON));
		}

		for (int i = 0; i < 9; i++) {
			res.add(new Card(R.drawable.redsun, CardColor.RED, CardFamily.SUN));
		}
		// cartes bleues
		for (int i = 0; i < 3; i++) {
			res.add(new Card(R.drawable.bluekey, CardColor.BLUE, CardFamily.KEY));
		}
		for (int i = 0; i < 4; i++) {
			res.add(new Card(R.drawable.bluemoon, CardColor.BLUE, CardFamily.MOON));
		}
		for (int i = 0; i < 8; i++) {
			res.add(new Card(R.drawable.bluesun, CardColor.BLUE, CardFamily.SUN));
		}
		// cartes vertes
		for (int i = 0; i < 3; i++) {
			res.add(new Card(R.drawable.greenkey, CardColor.GREEN, CardFamily.KEY));
		}
		for (int i = 0; i < 4; i++) {
			res.add(new Card(R.drawable.greenmoon, CardColor.GREEN, CardFamily.MOON));
		}
		for (int i = 0; i < 7; i++) {
			res.add(new Card(R.drawable.greensun, CardColor.GREEN, CardFamily.SUN));
		}
		// cartes brunes
		for (int i = 0; i < 3; i++) {
			res.add(new Card(R.drawable.brownkey, CardColor.BROWN, CardFamily.KEY));
		}
		for (int i = 0; i < 4; i++) {
			res.add(new Card(R.drawable.brownmoon, CardColor.BROWN, CardFamily.MOON));
		}
		for (int i = 0; i < 6; i++) {
			res.add(new Card(R.drawable.brownsun, CardColor.BROWN, CardFamily.SUN));
		}
		// cartes songes
		for (int i = 0; i < 10; i++) {
			res.add(new Card(R.drawable.songe, CardColor.NONE, CardFamily.NIGHTMARE));
		}

		// cartes portes
		for (int i = 0; i < 2; i++) {
			res.add(new Card(R.drawable.reddoor, CardColor.RED, CardFamily.DOOR));
			res.add(new Card(R.drawable.greendoor, CardColor.GREEN, CardFamily.DOOR));
			res.add(new Card(R.drawable.bluedoor, CardColor.BLUE, CardFamily.DOOR));
			res.add(new Card(R.drawable.browndoor, CardColor.BROWN, CardFamily.DOOR));
		}

		return res;
	}

	@Override
	public String toString() {
		if(family==CardFamily.NIGHTMARE ||family==CardFamily.NIGHTMARE){
			return family + "";
		}
		else{
			return color + " " + family;
		}
	}
}