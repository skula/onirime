package com.example.onirim.services;


public class Coordinator {
	public static final int ACTION_NONE = 0;
	public static final int ACTION_DISCARD_STOCK_OR_DOOR_LIMBO = 1;
	public static final int ACTION_LOOSE_DOOR = 2;
	public static final int ACTION_BUILD = 4;
	public static final int ACTION_SWAP_PROPHECY = 5;
	public static final int ACTION_ADD_HAND = 6;
	public static final int ACTION_SWAP_HAND = 7;
	public static final int ACTION_DISCARD_HAND = 8;
	public static final int ACTION_PROPHECY_OR_DISCARD = 9;
	
	public static final int ACTION_PICK_NEW_CARD = 10;
	public static final int ACTION_FILL_NEW_HAND = 11;
	
	public static final int ACTION_EXIT_PROPHECY = 12;
	
	private static Integer[][] matrix = {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 12, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 0, 0 },
			{ 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 7, 7, 7, 0, 0 },
			{ 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 0, 7, 7, 0, 0 },
			{ 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 0, 7, 0, 0 },
			{ 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 0, 0, 0 },
			{ 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 9, 9, 9, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12 }};

	public static int get(int i, int j) {
		return matrix[j][i];
	}
	
	public static String get2(int i, int j) {
		switch (matrix[j][i]) {
		case Coordinator.ACTION_NONE:
			return "ACTION_NONE";
		case Coordinator.ACTION_DISCARD_STOCK_OR_DOOR_LIMBO: 
			return "ACTION_DISCARD_STOCK_OR_DOOR_LIMBO";
		case Coordinator.ACTION_LOOSE_DOOR: 
			return "ACTION_LOOSE_DOOR";
		case Coordinator.ACTION_BUILD: 
			return "ACTION_BUILD";
		case Coordinator.ACTION_SWAP_PROPHECY:
			return "ACTION_SWAP_PROPHECY";
		case Coordinator.ACTION_ADD_HAND: 
			return "ACTION_ADD_HAND";
		case Coordinator.ACTION_SWAP_HAND:
			return "ACTION_SWAP_HAND";
		case Coordinator.ACTION_DISCARD_HAND: 
			return "ACTION_DISCARD_HAND";
		case Coordinator.ACTION_PROPHECY_OR_DISCARD:
			return "ACTION_PROPHECY_OR_DISCARD";
		default:
			return "";
		}
	}
}