package com.kingsoft.control;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GestureListener extends SimpleOnGestureListener implements
		OnTouchListener {

	private int distance = 10;
	private int velocity = 100;

	private GestureDetector gestureDetector;

	public GestureListener(Context context) {
		super();
		gestureDetector = new GestureDetector(context, this);
	}

	public boolean left() {
		return false;
	}

	public boolean right() {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		if (e1.getX() - e2.getX() > distance && Math.abs(velocityX) > velocity) {
			left();
		}

		if (e2.getX() - e1.getX() > distance && Math.abs(velocityX) > velocity) {
			right();
		}
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		gestureDetector.onTouchEvent(event);
		return false;
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return super.onSingleTapUp(e);
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public GestureDetector getGestureDetector() {
		return gestureDetector;
	}

	public void setGestureDetector(GestureDetector gestureDetector) {
		this.gestureDetector = gestureDetector;
	}
}
