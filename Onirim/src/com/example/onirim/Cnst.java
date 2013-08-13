package com.example.onirim;

import android.graphics.Rect;

public class Cnst {
	public static final Rect CARD_SIZE = new Rect(0, 0, 90, 150);

	public static final int AREA_NONE_CODE = 0;
	public static final Rect AREA_STOCK = new Rect(30, 10, 190, 210);
	public static final int AREA_STOCK_CODE = 1;
	public static final Rect AREA_PICKED = new Rect(200, 10, 360, 210);
	public static final int AREA_PICKED_CODE = 2;
	public static final Rect AREA_DOOR_1 = new Rect(450, 10, 610, 210);
	public static final int AREA_DOOR_1_CODE = 3;
	public static final Rect AREA_DOOR_2 = new Rect(650, 10, 810, 210);
	public static final int AREA_DOOR_2_CODE = 4;
	public static final Rect AREA_DOOR_3 = new Rect(850, 10, 1010, 210);
	public static final int AREA_DOOR_3_CODE = 5;
	public static final Rect AREA_DOOR_4 = new Rect(1050, 10, 1210, 210);
	public static final int AREA_DOOR_4_CODE = 6;
	public static final Rect AREA_BUILDING = new Rect(30, 250, 270, 450);
	public static final int AREA_BUILDING_CODE = 7;
	public static final Rect AREA_PROPHECY_1 = new Rect(330, 250, 490, 450);
	public static final int AREA_PROPHECY_1_CODE = 8;
	public static final Rect AREA_PROPHECY_2 = new Rect(530, 250, 690, 450);
	public static final int AREA_PROPHECY_2_CODE = 9;
	public static final Rect AREA_PROPHECY_3 = new Rect(710, 250, 870, 450);
	public static final int AREA_PROPHECY_3_CODE = 10;
	public static final Rect AREA_PROPHECY_4 = new Rect(890, 250, 1050, 450);
	public static final int AREA_PROPHECY_4_CODE = 11;
	public static final Rect AREA_PROPHECY_5 = new Rect(1070, 250, 1230, 450);
	public static final int AREA_PROPHECY_5_CODE = 12;
	public static final Rect AREA_HAND_1 = new Rect(30, 480, 190, 680);
	public static final int AREA_HAND_1_CODE = 13;
	public static final Rect AREA_HAND_2 = new Rect(230, 480, 390, 680);
	public static final int AREA_HAND_2_CODE = 14;
	public static final Rect AREA_HAND_3 = new Rect(430, 480, 590, 680);
	public static final int AREA_HAND_3_CODE = 15;
	public static final Rect AREA_HAND_4 = new Rect(630, 480, 790, 680);
	public static final int AREA_HAND_4_CODE = 16;
	public static final Rect AREA_HAND_5 = new Rect(830, 480, 990, 680);
	public static final int AREA_HAND_5_CODE = 17;
	public static final Rect AREA_DISCARDING = new Rect(1070, 480, 1230, 680);
	public static final int AREA_DISCARDING_CODE = 18;
	
	public static final Rect AREA_EXIT_PROPHECY = new Rect(0, 0, 0, 0);
	public static final int AREA_EXIT_PROPHECY_CODE = 19;
	
	public static int getArea(int x, int y) {
		if (Cnst.AREA_STOCK.contains(x, y)) {
			return Cnst.AREA_STOCK_CODE;
		} else if (Cnst.AREA_PICKED.contains(x, y)) {
			return Cnst.AREA_PICKED_CODE;
		} else if (Cnst.AREA_DOOR_1.contains(x, y)) {
			return Cnst.AREA_DOOR_1_CODE;
		} else if (Cnst.AREA_DOOR_2.contains(x, y)) {
			return Cnst.AREA_DOOR_2_CODE;
		} else if (Cnst.AREA_DOOR_3.contains(x, y)) {
			return Cnst.AREA_DOOR_3_CODE;
		} else if (Cnst.AREA_DOOR_4.contains(x, y)) {
			return Cnst.AREA_DOOR_4_CODE;
		} else if (Cnst.AREA_BUILDING.contains(x, y)) {
			return Cnst.AREA_BUILDING_CODE;
		} else if (Cnst.AREA_PROPHECY_1.contains(x, y)) {
			return Cnst.AREA_PROPHECY_1_CODE;
		} else if (Cnst.AREA_PROPHECY_2.contains(x, y)) {
			return Cnst.AREA_PROPHECY_2_CODE;
		} else if (Cnst.AREA_PROPHECY_3.contains(x, y)) {
			return Cnst.AREA_PROPHECY_3_CODE;
		} else if (Cnst.AREA_PROPHECY_4.contains(x, y)) {
			return Cnst.AREA_PROPHECY_4_CODE;
		} else if (Cnst.AREA_PROPHECY_5.contains(x, y)) {
			return Cnst.AREA_PROPHECY_5_CODE;
		} else if (Cnst.AREA_HAND_1.contains(x, y)) {
			return Cnst.AREA_HAND_1_CODE;
		} else if (Cnst.AREA_HAND_2.contains(x, y)) {
			return Cnst.AREA_HAND_2_CODE;
		} else if (Cnst.AREA_HAND_3.contains(x, y)) {
			return Cnst.AREA_HAND_3_CODE;
		} else if (Cnst.AREA_HAND_4.contains(x, y)) {
			return Cnst.AREA_HAND_4_CODE;
		} else if (Cnst.AREA_HAND_5.contains(x, y)) {
			return Cnst.AREA_HAND_5_CODE;
		} else if (Cnst.AREA_DISCARDING.contains(x, y)) {
			return Cnst.AREA_DISCARDING_CODE;
		//} else if( Cnst.AREA_EXIT_PROPHECY.contains(x,y)){
		//	return Cnst.AREA_EXIT_PROPHECY_CODE;
		} else {
			return Cnst.AREA_NONE_CODE;
		}
	}
}
