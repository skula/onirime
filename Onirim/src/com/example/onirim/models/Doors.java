package com.example.onirim.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.example.onirim.enums.CardColor;


public class Doors extends CardArray{	
	public static final int COUNT = 8;
	
	private Map<CardColor,Integer[]> map;
	private boolean extension;
	
	public Doors(boolean ext) {
		super(8);
		this.extension = ext;
		this.map = new HashMap<CardColor,Integer[]>();
		if(ext){
			List<Integer> list = new ArrayList<Integer>();
			for(int i=0; i<8; i++){
				list.add(i);
			}
			Random rand = new Random();
			int p1 = list.remove(rand.nextInt(list.size()));
			int p2 = list.remove(rand.nextInt(list.size()));
			this.map.put(CardColor.RED,new Integer[]{p1,p2});
			p1 = list.remove(rand.nextInt(list.size()));
			p2 = list.remove(rand.nextInt(list.size()));
			this.map.put(CardColor.BLUE,new Integer[]{p1,p2});
			p1 = list.remove(rand.nextInt(list.size()));
			p2 = list.remove(rand.nextInt(list.size()));
			this.map.put(CardColor.GREEN,new Integer[]{p1,p2});
			p1 = list.remove(rand.nextInt(list.size()));
			p2 = list.remove(rand.nextInt(list.size()));
			this.map.put(CardColor.BROWN,new Integer[]{p1,p2});
		} else{
			this.map.put(CardColor.RED,new Integer[]{0,1});
			this.map.put(CardColor.BLUE,new Integer[]{2,3});
			this.map.put(CardColor.GREEN,new Integer[]{4,5});
			this.map.put(CardColor.BROWN,new Integer[]{6,7});
		}
	}
	
	public boolean add(Card c){
		Integer[] tmp = map.get(c.getColor());
		if(getCard(tmp[0])==null){
			setCard(tmp[0], c);
		}else if(getCard(tmp[1])==null){
			setCard(tmp[1], c);
		}else{
			return false;
		}
		return true;
	}
}