package com.xiaozhen.style;

public class ColorStyle {
	private byte theme; // 默认为主题色1
	private double tint;
	private int rgb;
	private byte indexed;

	private boolean isSetTheme = false;
	private boolean isSetTint = false;
	private boolean isSetRgb = false;
	private boolean isSetIndexed = false;

	public byte getTheme() {
		return theme;
	}

	public void setTheme(byte theme) {
		isSetTheme = true;
		isSetTint = false;
		isSetRgb = false;
		isSetIndexed = false;
		this.theme = theme;
	}

	public void setTheme(byte theme, double tint) {
		this.theme = theme;
		this.tint = tint;
		isSetTheme = true;
		isSetTint = true;
		isSetRgb = false;
		isSetIndexed = false;
	}

	public double getTint() {
		return tint;
	}

	public void setTint(double tint) {
		isSetTint = true;
		this.tint = tint;
	}

	public int getRgb() {
		return rgb;
	}

	public void setRgb(int rgb) {
		isSetRgb = true;
		isSetTheme = false;
		isSetTint = false;
		isSetIndexed = false;
		this.rgb = rgb;
	}

	public byte getIndexed() {
		return indexed;
	}

	public void setIndexed(byte index) {
		this.indexed = index;
		isSetIndexed = true;
		isSetRgb = false;
		isSetTheme = false;
		isSetTint = false;
		
	}

	public boolean isSetTheme() {
		return isSetTheme;
	}

	public boolean isSetRgb() {
		return isSetRgb;
	}

	public boolean isSetTint() {
		return isSetTint;
	}

	public boolean isSetIndexed(){
		return isSetIndexed;
	}
}
