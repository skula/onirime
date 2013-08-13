package com.example.onirim;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.example.onirim.models.Card;
import com.example.onirim.models.CardArray;
import com.example.onirim.models.Doors;
import com.example.onirim.services.Coordinator;
import com.example.onirim.services.Engine;

class BoardView extends View {
	private Drawer drawer;
	private Engine engine;
	
	private int lastArea;
	private int xPos;
	private int yPos;
	private int dxPos;
	private int dyPos;

	public BoardView(Context context) {
		super(context);
		this.lastArea = Cnst.AREA_NONE_CODE;
		this.engine = new Engine();
		this.drawer = new Drawer(context.getResources(), engine);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastArea = Cnst.getArea(x, y);
			xPos = x;
			yPos = y;
			break;
		case MotionEvent.ACTION_MOVE:
			dxPos+=x-xPos;
			dyPos+=y-yPos;
			xPos = x;
			yPos = y;
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			dxPos = 0;
			dyPos = 0;
			int currentArea = Cnst.getArea(x, y);
			int action = Coordinator.get(lastArea, currentArea);
			if (engine.isValideAction(action, lastArea, currentArea)) {
				switch(engine.doAction(action, lastArea, currentArea)){
				case Coordinator.ACTION_PICK_NEW_CARD:
					engine.pickNewCard();
					break;
				case Coordinator.ACTION_FILL_NEW_HAND:
					engine.initHand();
					break;
				}
				invalidate();
			}
			lastArea = 0;
			break;
		}
		return true;
	}
	
	@Override
	public void draw(Canvas canvas) {
		drawer.draw(canvas, dxPos, dyPos);
	}
}
