package com.example.onirim;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.onirim.models.Card;
import com.example.onirim.models.CardArray;
import com.example.onirim.models.Doors;
import com.example.onirim.services.Engine;

public class Drawer {
	private Engine engine;
	private Paint paint;
	private Resources res;
	
	public Drawer(Resources res, Engine engine) {
		this.engine = engine;
		this.res = res;
		this.paint = new Paint();
	}
	
	public void draw(Canvas c, int dx, int dy){
		drawStock(c);
		drawDiscarding(c);
		drawPickedCard(c, dx, dy);
		drawBuildingCard(c);
		drawProphecyCard(c);
		drawDoors(c);
		drawHand(c);
		drawCounts(c);
	}

	private void drawStock(Canvas canvas) {
		int id = engine.getStock().isEmpty() ? R.drawable.emptyslot: R.drawable.back;
		drawBitmap(canvas, Cnst.AREA_STOCK, id);
	}
	
	private void drawDiscarding(Canvas canvas) {
		Card c = engine.getDiscarding().getTail();
		int id = c==null?R.drawable.emptyslot:c.getId();
		drawBitmap(canvas, Cnst.AREA_DISCARDING, id);
	}
	
	private void drawPickedCard(Canvas canvas, int dxPos, int dyPos) {
		/*if(engine.getPickedCard() != null){
			drawBitmap(canvas, getRect(Cnst.AREA_PICKED, dxPos, dyPos), 
						engine.getPickedCard().getId());
		}else{
			drawBitmap(canvas, Cnst.AREA_PICKED, R.drawable.emptyslot);
		}*/
		if(engine.getPickedCard() != null){
			drawBitmap(canvas, Cnst.AREA_PICKED, 
						engine.getPickedCard().getId());
		}else{
			drawBitmap(canvas, Cnst.AREA_PICKED, R.drawable.emptyslot);
		}
	}
	
	private void drawBuildingCard(Canvas canvas) {
		int size = engine.getBuilding().size();
		Rect rect = new Rect(30, 250, 190, 450);
		if(size==0){
			drawBitmap(canvas, rect, R.drawable.emptyslot);
			return;
		}
		int cpt = size < 4 ? size : 4;
		for (int i = size - cpt, j = 0; i < size; i++, j++) {
			drawBitmap(canvas, getRect(rect, j * 40, 0),
					engine.getBuilding().getCard(i).getId());
		}
	}
	
	private void drawProphecyCard(Canvas canvas) {
		CardArray array = engine.getProphecy();
		if (array.isEmpty()) {
			return;
		}
		int id = array.getCard(0)==null?R.drawable.emptyslot:array.getCard(0).getId();
		drawBitmap(canvas, Cnst.AREA_PROPHECY_1, id);
		for (int i = 1; i < 5; i++) {
			id = array.getCard(i) == null ? R.drawable.emptyslot : array
					.getCard(i).getId();
			drawBitmap(canvas,
					getRect(Cnst.AREA_PROPHECY_2, (i - 1) * 180, 0), id);
		}
	}
	
	private void drawDoors(Canvas canvas) {
		int cpt = 0;
		boolean b = false;
		Card c = null;
		for (int i = 0; i < Doors.COUNT; i++) {
			c = engine.getDoors().getCard(i);
			if (c != null) {
				int spaceX = i % 2 == 0 ? 0 : 20;
				spaceX += cpt * 200;
				int spaceY = i % 2 == 0 ? 0 : 20;
				Rect rect = getRect(Cnst.AREA_DOOR_1, spaceX, spaceY);
				drawBitmap(canvas, rect, c.getId());
				cpt += b ? 1 : 0;
				b = !b;
			}
		}
	}
	
	private void drawHand(Canvas canvas) {
		for (int i = 0; i < 5; i++) {
			Card c = engine.getHand().getCard(i);
			int id = c != null ? c.getId() : R.drawable.emptyslot;
			drawBitmap(canvas, getRect(Cnst.AREA_HAND_1, 200 * i, 0), id);
		}
	}
	
	private void drawCounts(Canvas canvas){
		paint.setColor(Color.YELLOW);
		paint.setTextSize(30f);
		int stockCount = engine.getStock().size();
		int limboCount = engine.getLimbo().size();
		canvas.drawText(stockCount + "+" + limboCount, 50, 190, paint);
		int discardCount = engine.getDiscarding().size();
		canvas.drawText(discardCount+"", 1090, 660, paint);
	}
	
	private Rect getRect(Rect src, int dx, int dy) {
		return new Rect(src.left + dx, src.top + dy, src.right + dx, src.bottom
				+ dy);
	}

	private void drawBitmap(Canvas canvas, Rect r, int id) {
		canvas.drawBitmap(getPict(id), Cnst.CARD_SIZE, r, null);
	}

	private Bitmap getPict(int drawableId) {
		return BitmapFactory.decodeStream(res.openRawResource(drawableId));
	}
}
