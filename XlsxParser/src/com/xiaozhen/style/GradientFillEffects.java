package com.xiaozhen.style;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GradientFillEffects {// 渐变填充效果
	public enum GradientType {
		path, linear
	};

	private short degree;
	private boolean isSetDegree = false;

	private GradientType gradientType;
	private boolean isSetGradientType = false;
	private float top;
	private float bottom;
	private float left;
	private float right;

	private List<Stop> stops = new ArrayList<Stop>();

	public boolean isSetDegree() {
		return isSetDegree;
	}

	public boolean isSetGradientType() {
		return isSetGradientType;
	}

	public void setDegree(short degree) {
		this.degree = degree;
		isSetDegree = true;
		isSetGradientType = false;
	}

	public short getDegree() {
		return degree;
	}

	public void setGradientType(GradientType gradientType, float top,
			float bottom, float left, float right) {
		this.gradientType = gradientType;
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
		isSetDegree = false;
		isSetGradientType = true;
	}

	public GradientType getGradientType() {
		return gradientType;
	}

	public float getTop() {
		return top;
	}

	public float getBottom() {
		return bottom;
	}

	public float getLeft() {
		return left;
	}

	public float getRight() {
		return right;
	}

	public void addStops(Stop stop) {
		stops.add(stop);
	}
	
	public Iterator<Stop> getStopsIterator(){
		return stops.iterator();
	}
}
