package com.example.onirim.services;

import com.example.onirim.Cnst;
import com.example.onirim.R;
import com.example.onirim.enums.CardColor;
import com.example.onirim.enums.CardFamily;
import com.example.onirim.models.Card;
import com.example.onirim.models.CardArray;
import com.example.onirim.models.CardList;
import com.example.onirim.models.Doors;

public class Engine {
	private CardList stock;
	private CardList limbo;
	private CardList discarding;
	private CardList building;

	private CardArray hand;
	private CardArray prophecy;
	private Doors doors;

	private Card pickedCard;
	private int buildFlag;

	public Engine() {
		newGame();
	}
	
	public void newGame(){
		this.stock = new CardList(Card.getAllCards());
		this.stock.blend();
		this.limbo = new CardList();
		this.discarding = new CardList();
		this.building = new CardList();
		this.hand = new CardArray(5);
		this.prophecy = new CardArray(5);
		this.doors = new Doors(false);
		this.pickedCard = null;
		this.buildFlag = -1;
		initHand();
	}

	public void initHand() {
		int cpt = 0;
		Card c = null;
		while (cpt < 5) {
			c = stock.pickHead();
			if (c.isRoom()) {
				hand.setCard(cpt, c);
				cpt++;
			} else {
				limbo.putTail(c);
			}
		}
		
		stock.putTail(limbo.clear());
		stock.blend();
	}

	public boolean isValideAction(int action, int areaFrom, int areaTo) {
		Card from = getCard(areaFrom);
		Card to = getCard(areaTo);
		
		switch (action) {
		case Coordinator.ACTION_NONE:
			return false;
			
		case Coordinator.ACTION_DISCARD_STOCK_OR_DOOR_LIMBO: 
			return !isProphecy() && from!=null && !from.isRoom();
			
		case Coordinator.ACTION_LOOSE_DOOR:
			return !isProphecy() && from!=null && from.isNightmare() && to!=null;
			
		case Coordinator.ACTION_BUILD:
			return !isProphecy() && hand.isFull() && from != null && tryBuild(from);
			
		case Coordinator.ACTION_SWAP_PROPHECY: 
			return isProphecy();
			
		case Coordinator.ACTION_ADD_HAND: 
			if(from==null || isProphecy()){
				return false;
			}
			if(from.isDoor()){
				return to!=null && to.isKey() && from.sameColor(to);
			}
			if(from.isNightmare()){
				return to!=null && to.isKey();
			}
			return to==null;
			
		case Coordinator.ACTION_SWAP_HAND:
			return true;
			
		case Coordinator.ACTION_DISCARD_HAND:
			return !isProphecy() && from!=null && from.isNightmare();
			
		case Coordinator.ACTION_PROPHECY_OR_DISCARD: 
			return !isProphecy() && from != null;
		
		case Coordinator.ACTION_EXIT_PROPHECY: 
			return isProphecy() && prophecy.getCard(0)!=null;
			
		default:
			return false;
		}
	}

	public int doAction(int action, int areaFrom, int areaTo) {		
		Card c = null;
		switch (action) {
		case Coordinator.ACTION_DISCARD_STOCK_OR_DOOR_LIMBO:
			if(pickedCard.isNightmare()){
				for (int i = 0; i < 5; i++) {
					c = stock.pickHead();
					if(c!=null){
						discarding.putTail(c);
					}
				}
				discarding.putTail(removePickedCard());
			} else{
				limbo.putTail(removePickedCard());
			}
			return Coordinator.ACTION_PICK_NEW_CARD;
			
		case Coordinator.ACTION_LOOSE_DOOR:
			discarding.putTail(removePickedCard());
			limbo.putTail(removeCard(areaTo));
			return Coordinator.ACTION_PICK_NEW_CARD;
			
		case Coordinator.ACTION_BUILD:
			c = hand.removeCard(getHandId(areaFrom));
			building.putTail(c);
			if(isBuildDoor()){
				buildDoor();
			}
			return Coordinator.ACTION_PICK_NEW_CARD;
			
		case Coordinator.ACTION_SWAP_PROPHECY:
			prophecy.swap(getProphecyId(areaFrom), getProphecyId(areaTo));
			return Coordinator.ACTION_NONE;
			
		case Coordinator.ACTION_ADD_HAND:
			int id = getHandId(areaTo);
			c = removePickedCard();
			if(c.isDoor()){
				discarding.putTail(hand.getCard(id));
				doors.add(c);
				return Coordinator.ACTION_PICK_NEW_CARD;
			} else if(c.isNightmare()){
				discarding.putTail(hand.removeCard(id));
				discarding.putTail(c);
				return Coordinator.ACTION_PICK_NEW_CARD;
			} else{
				hand.setCard(id, c);
				if(!isHandFull()){
					return Coordinator.ACTION_PICK_NEW_CARD;
				}else{
					return Coordinator.ACTION_NONE;
				}
			}
			
		case Coordinator.ACTION_SWAP_HAND:
			hand.swap(getHandId(areaFrom),getHandId(areaTo));
			return Coordinator.ACTION_NONE;
			
		case Coordinator.ACTION_DISCARD_HAND:
			for(int i = 0; i<5; i++){
				c = hand.removeCard(i);
				if(c!=null){
					discarding.putTail(c);
				}
			}
			discarding.putTail(removePickedCard());
			return Coordinator.ACTION_FILL_NEW_HAND;
			
		case Coordinator.ACTION_PROPHECY_OR_DISCARD:
			c = hand.removeCard(getHandId(areaFrom));
			discarding.putTail(c);
			if(c.isKey()){
				for(int i = 0; i<5; i++){
					prophecy.setCard(i, stock.pickHead());
				}
				return Coordinator.ACTION_NONE;
			}
			return Coordinator.ACTION_PICK_NEW_CARD;
			
		case Coordinator.ACTION_EXIT_PROPHECY: 
			discarding.putTail(prophecy.removeCard(0));
			for(int i=4; i>=0; i--){
				if((c=prophecy.removeCard(i))!=null){
					stock.putHead(c);
				}
			}
			return Coordinator.ACTION_PICK_NEW_CARD;
			
		default:
			return Coordinator.ACTION_NONE;
		}
	}
	
	private int getProphecyId(int i){
		switch(i){
			case Cnst.AREA_PROPHECY_1_CODE:
				return 0;
			case Cnst.AREA_PROPHECY_2_CODE:
				return 1;
			case Cnst.AREA_PROPHECY_3_CODE:
				return 2;
			case Cnst.AREA_PROPHECY_4_CODE:
				return 3;
			case Cnst.AREA_PROPHECY_5_CODE:
				return 4;
			default:
				return -1;
		}
	}
	
	private int getHandId(int i){
		switch(i){
			case Cnst.AREA_HAND_1_CODE:
				return 0;
			case Cnst.AREA_HAND_2_CODE:
				return 1;
			case Cnst.AREA_HAND_3_CODE:
				return 2;
			case Cnst.AREA_HAND_4_CODE:
				return 3;
			case Cnst.AREA_HAND_5_CODE:
				return 4;
			default:
				return -1;
		}
	}
	
	public Card getCard(int area) {
		Card res = null;
		switch (area) {
		case Cnst.AREA_NONE_CODE:
			return null;
		case Cnst.AREA_STOCK_CODE:
			return stock.getCard(0);
		case Cnst.AREA_PICKED_CODE:
			return pickedCard;
		case Cnst.AREA_DOOR_1_CODE:
			res = doors.getCard(1);
			if(res==null){
				res = doors.getCard(0);
			}
			return res;
		case Cnst.AREA_DOOR_2_CODE:
			res = doors.getCard(3);
			if(res==null){
				res = doors.getCard(2);
			}
			return res;
		case Cnst.AREA_DOOR_3_CODE:
			res = doors.getCard(5);
			if(res==null){
				res = doors.getCard(4);
			}
			return res;
		case Cnst.AREA_DOOR_4_CODE:
			res = doors.getCard(7);
			if(res==null){
				res = doors.getCard(6);
			}
			return res;
		case Cnst.AREA_BUILDING_CODE:
			return null;
		case Cnst.AREA_PROPHECY_1_CODE:
			return prophecy.getCard(0);
		case Cnst.AREA_PROPHECY_2_CODE:
			return prophecy.getCard(1);
		case Cnst.AREA_PROPHECY_3_CODE:
			return prophecy.getCard(2);
		case Cnst.AREA_PROPHECY_4_CODE:
			return prophecy.getCard(3);
		case Cnst.AREA_PROPHECY_5_CODE:
			return prophecy.getCard(4);
		case Cnst.AREA_HAND_1_CODE:
			return hand.getCard(0);
		case Cnst.AREA_HAND_2_CODE:
			return hand.getCard(1);
		case Cnst.AREA_HAND_3_CODE:
			return hand.getCard(2);
		case Cnst.AREA_HAND_4_CODE:
			return hand.getCard(3);
		case Cnst.AREA_HAND_5_CODE:
			return hand.getCard(4);
		case Cnst.AREA_DISCARDING_CODE:
			return null;
		default:
			return null;
		}
	}

	public Card removeCard(int area) {
		Card res = null;
		switch (area) {
		case Cnst.AREA_NONE_CODE:
			return null;
		case Cnst.AREA_STOCK_CODE:
			return stock.pickHead();
		case Cnst.AREA_PICKED_CODE:
			return removePickedCard();
		case Cnst.AREA_DOOR_1_CODE:
			res = doors.removeCard(1);
			if(res==null){
				res = doors.removeCard(0);
			}
			return res;
		case Cnst.AREA_DOOR_2_CODE:
			res = doors.removeCard(3);
			if(res==null){
				res = doors.removeCard(2);
			}
			return res;
		case Cnst.AREA_DOOR_3_CODE:
			res = doors.removeCard(5);
			if(res==null){
				res = doors.removeCard(4);
			}
			return res;
		case Cnst.AREA_DOOR_4_CODE:
			res = doors.removeCard(7);
			if(res==null){
				res = doors.removeCard(6);
			}
			return res;
		case Cnst.AREA_BUILDING_CODE:
			return null;
		case Cnst.AREA_PROPHECY_1_CODE:
			return prophecy.removeCard(0);
		case Cnst.AREA_PROPHECY_2_CODE:
			return prophecy.removeCard(1);
		case Cnst.AREA_PROPHECY_3_CODE:
			return prophecy.removeCard(2);
		case Cnst.AREA_PROPHECY_4_CODE:
			return prophecy.removeCard(3);
		case Cnst.AREA_PROPHECY_5_CODE:
			return prophecy.removeCard(4);
		case Cnst.AREA_HAND_1_CODE:
			return hand.removeCard(0);
		case Cnst.AREA_HAND_2_CODE:
			return hand.removeCard(1);
		case Cnst.AREA_HAND_3_CODE:
			return hand.removeCard(2);
		case Cnst.AREA_HAND_4_CODE:
			return hand.removeCard(3);
		case Cnst.AREA_HAND_5_CODE:
			return hand.removeCard(4);
		case Cnst.AREA_DISCARDING_CODE:
			return null;
		default:
			return null;
		}
	}
	//     *              
	// 0 1 2 3 4 5 6 7 8 9
	private boolean isBuildDoor(){
		if(building.size()<3){
			return false;
		}
		
		if(buildFlag>=building.size()-3 && buildFlag<=building.size()-1){
			return false;
		}

		CardColor c1 = building.getCard(building.size()-1).getColor();
		CardColor c2 = building.getCard(building.size()-2).getColor();
		CardColor c3 = building.getCard(building.size()-3).getColor();
		return c1==c2 && c2==c3;
	}
	
	private void buildDoor(){
		buildFlag = building.size()-1;
		Card c1 = building.getCard(building.size()-1);
		int cpt = 0;
		for(Card c : stock.getCards()){
			if(c.isDoor() && c.sameColor(c1)){
				doors.add(stock.getCards().remove(cpt));
				return;
			}
			cpt++;
		}
	}

	private boolean tryBuild(Card c) {
		if(building.isEmpty()){
			return true;
		}
		return c.getFamily()!=building.getTail().getFamily();
	}
	
	public void pickNewCard(){
		pickedCard = stock.pickHead();
	}
	
	public boolean isProphecy(){
		return !prophecy.isEmpty();
	}
	
	public boolean isHandFull(){
		for(int i =0; i<5; i++){
			if(hand.getCard(i)==null){
				return false;
			}
		}
		return true;
	}

	public CardList getStock() {
		return stock;
	}

	public CardList getLimbo() {
		return limbo;
	}

	public CardList getDiscarding() {
		return discarding;
	}

	public CardList getBuilding() {
		return building;
	}

	public CardArray getHand() {
		return hand;
	}

	public CardArray getProphecy() {
		return prophecy;
	}

	public Doors getDoors() {
		return doors;
	}

	public Card getPickedCard() {
		return pickedCard;
	}

	public Card removePickedCard() {
		Card res = pickedCard;
		pickedCard = null;
		return res;
	}

	public void test() {
		/*prophecy.setCard(0, new Card(R.drawable.bluekey, CardColor.BLUE,
				CardFamily.KEY));

		doors.setCard(0, new Card(R.drawable.reddoor, CardColor.RED,
				CardFamily.DOOR));
		doors.setCard(1, new Card(R.drawable.reddoor, CardColor.RED,
				CardFamily.DOOR));
		doors.setCard(2, new Card(R.drawable.bluedoor, CardColor.BLUE,
				CardFamily.DOOR));
		doors.setCard(3, new Card(R.drawable.bluedoor, CardColor.BLUE,
				CardFamily.DOOR));
		doors.setCard(4, new Card(R.drawable.greendoor, CardColor.GREEN,
				CardFamily.DOOR));
		doors.setCard(5, new Card(R.drawable.greendoor, CardColor.GREEN,
				CardFamily.DOOR));
		doors.setCard(6, new Card(R.drawable.browndoor, CardColor.BROWN,
				CardFamily.DOOR));
		doors.setCard(7, new Card(R.drawable.browndoor, CardColor.BROWN,
				CardFamily.DOOR));*/
	}
}