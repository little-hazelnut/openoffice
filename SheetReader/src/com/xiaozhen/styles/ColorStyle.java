package com.xiaozhen.styles;

import org.xml.sax.Attributes;

import com.kingsoft.util.SheetTools;

public class ColorStyle {
	// colorStyle
	private final String QNAME_theme = "theme";
	private final String QNAME_tint = "tint";
	private final String QNAME_rgb = "rgb";
	private final String QNAME_indexed = "indexed";
	private final String QNAME_auto = "auto";

	private byte theme; // default theme 1
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

	public boolean isSetIndexed() {
		return isSetIndexed;
	}

	@Override
	public String toString() {
		if (isSetRgb) {
			return "ColorStyle [rgb=" + SheetTools.intToRgbString(rgb) + "]";
		} else if (isSetIndexed) {
			return "ColorStyle [indexed=" + indexed + "]";
		} else {// default theme
			return "ColorStyle [theme=" + theme + ", tint=" + tint + "]";
		}
	}

	public void parseColorStyle(Attributes attributes) {
		String attriValue;
		if ((attriValue = attributes.getValue(QNAME_theme)) != null) {// theme
			this.setTheme(Byte.parseByte(attriValue));
			if ((attriValue = attributes.getValue(QNAME_tint)) != null) {// tint
				this.setTint(Double.parseDouble(attriValue));
			}
		} else if ((attriValue = attributes.getValue(QNAME_rgb)) != null) {// rgb
			this.setRgb(SheetTools.rgbStringToInt(attriValue));
		} else if ((attriValue = attributes.getValue(QNAME_indexed)) != null) {// indexed
			this.setIndexed(Byte.parseByte(attriValue));
		} else if (attributes.getValue(QNAME_auto) != null) {// auto
			this.setTheme((byte) 1);
		}
	}

}
