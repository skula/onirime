package com.example.onirim.models;

import java.util.List;

public class CardArray {
	protected Card[] cards;

	public CardArray(Card[] cards) {
		this.cards = cards;
	}

	public CardArray(List<Card> cards) {
		this.cards = cards.toArray(new Card[cards.size()]);
	}

	public CardArray(int n) {
		this.cards = new Card[n];
	}

	public void swap(int i1, int i2) {
		try {
			Card tmp = cards[i1];
			cards[i1] = cards[i2];
			cards[i2] = tmp;
		} catch (Exception e) {
		}
	}

	public boolean isEmpty() {
		for (int i = 0; i < cards.length; i++) {
			if (cards[i] != null) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isFull() {
		for (int i = 0; i < cards.length; i++) {
			if (cards[i] == null) {
				return false;
			}
		}
		return true;
	}

	public void clear() {
		for (int i = 0; i < cards.length; i++) {
			cards[i] = null;
		}
	}

	public Card removeCard(int i) {
		try {
			Card tmp = cards[i];
			cards[i] = null;
			return tmp;
		} catch (Exception e) {
			return null;
		}
	}

	public Card getCard(int i) {
		return cards[i];
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCard(int i, Card c) {
		try {
			cards[i] = c;
		} catch (Exception e) {
		}
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}
}
