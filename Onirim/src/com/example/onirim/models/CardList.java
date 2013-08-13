package com.example.onirim.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardList {
	private List<Card> cards;

	public CardList() {
		this.cards = new ArrayList<Card>();
	}

	public CardList(List<Card> cards) {
		this.cards = cards;
	}

	public Card pickHead() {
		if (!cards.isEmpty()) {
			return cards.remove(0);
		}
		return null;
	}

	public Card pickTail() {
		if (!cards.isEmpty()) {
			return cards.remove(cards.size() - 1);
		}
		return null;
	}
	
	public Card getTail() {
		if (!cards.isEmpty()) {
			return cards.get(cards.size() - 1);
		}
		return null;
	}
	
	public Card getCard(int i) {
		return cards.get(i);
	}

	public void putHead(Card c) {
		cards.add(0, c);
	}

	public void putTail(Card c) {
		cards.add(c);
	}
	
	public void putTail(List<Card> l) {
		cards.addAll(l);
	}
	
	public List<Card> clear(){
		List<Card> res = cards;
		cards.clear();
		return res;
	}
	
	public boolean isEmpty(){
		return cards.isEmpty();
	}
	
	public int size(){
		return cards.size();
	}

	public void blend() {
		List<Card> tmp = new ArrayList<Card>();
		tmp.addAll(cards);
		cards.clear();

		Random rand = new Random();
		int pos = 0;
		while (!tmp.isEmpty()) {
			pos = rand.nextInt(tmp.size());
			cards.add(tmp.get(pos));
			tmp.remove(pos);
		}
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
}
