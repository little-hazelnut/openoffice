package com.xiaozhen.styles;

public class Font {// 格式中的字体标签
	// 下划线类型
	// 单下划线，双下划线，会计用单下划线，会计用双下划线
	public enum Font_u {
		singleU, doubleU, singleAccounting, doubleAccounting
	}

	private Font_u u = null;

	// 字形
	private boolean bold = false; // 加粗
	private boolean incline = false; // 倾斜

	// 字号
	private byte size; // 默认为11

	// 特殊效果
	private boolean strike = false; // 删除线

	// 上标，下标
	public enum VertAlign {
		Superscript, Subscript
	}

	private VertAlign vAlign = null;

	// 颜色
	private ColorStyle color;

	// 字体
	private String name; // 常规默认为宋体
	private short family; // 常规默认为2
	private short charset;// 常规默认为134
	private String scheme; // 常规默认为"minor";

	public Font_u getU() {
		return u;
	}

	public void setU(Font_u u) {
		this.u = u;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public boolean isIncline() {
		return incline;
	}

	public void setIncline(boolean incline) {
		this.incline = incline;
	}

	public byte getSize() {
		return size;
	}

	public void setSize(byte size) {
		this.size = size;
	}

	public boolean isStrike() {
		return strike;
	}

	public void setStrike(boolean strike) {
		this.strike = strike;
	}

	public VertAlign getvAlign() {
		return vAlign;
	}

	public void setvAlign(VertAlign vAlign) {
		this.vAlign = vAlign;
	}

	public ColorStyle getColor() {
		return color;
	}

	public void setColor(ColorStyle color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getFamily() {
		return family;
	}

	public void setFamily(short family) {
		this.family = family;
	}

	public short getCharset() {
		return charset;
	}

	public void setCharset(short charset) {
		this.charset = charset;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	@Override
	public String toString() {
		return "Font [u=" + u + ", bold=" + bold + ", incline=" + incline
				+ ", size=" + size + ", strike=" + strike + ", vAlign="
				+ vAlign + ", color=" + color + ", name=" + name + ", family="
				+ family + ", charset=" + charset + ", scheme=" + scheme + "]";
	}

}
