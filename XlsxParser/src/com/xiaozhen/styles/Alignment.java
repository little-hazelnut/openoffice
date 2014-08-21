package com.xiaozhen.styles;

public class Alignment {// 对齐标签
	// 垂直对齐选项，居中，两端对齐，分散对齐，靠上，靠下
	public enum Vertical {
		center, justify, distributed, top, bottom
	}

	// 垂直对齐属性
	private Vertical vertical = Vertical.center; // 默认居中，若为靠下，则对应xml中无此标签

	// 水平对齐选项，常规，靠左，居中，靠右，填充，两端对齐，跨列居中，分散对齐
	public enum Horizontal {
		general, left, center, right, fill, justify, centerContinuous, distributed
	}

	// 水平对齐属性,默认常规
	public Horizontal horizontal = Horizontal.general;
	private byte indent = 0; // 缩进值
	private int relativeIndent;

	// 文本控制
	private boolean wrapText = false; // 自动换行
	private boolean shrinkToFit = false; // 缩小字体填充
	private boolean justifyLastLine = false;// A boolean value indicating if the
											// cells justified or distributed
											// alignment should
											// be used on the last line of text.

	// 文字方向
	// 根据内容，从左到右，从右到左
	public enum ReadingOrder {
		onContent, leftToRight, RightToLeft
	}

	private ReadingOrder readingOrder = ReadingOrder.onContent; // 默认根据内容

	// 文本方向
	private byte textRotation = 0;

	public Vertical getVertical() {
		return vertical;
	}

	public void setVertical(Vertical vertical) {
		this.vertical = vertical;
	}

	public Horizontal getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(Horizontal horizontal) {
		this.horizontal = horizontal;
	}

	public byte getIndent() {
		return indent;
	}

	public void setIndent(byte indent) {
		this.indent = indent;
	}

	public int getRelativeIndent() {
		return relativeIndent;
	}

	public void setRelativeIndent(int relativeIndent) {
		this.relativeIndent = relativeIndent;
	}

	public boolean isWrapText() {
		return wrapText;
	}

	public void setWrapText(boolean wrapText) {
		this.wrapText = wrapText;
	}

	public boolean isShrinkToFit() {
		return shrinkToFit;
	}

	public void setShrinkToFit(boolean shrinkToFit) {
		this.shrinkToFit = shrinkToFit;
	}

	public boolean isJustifyLastLine() {
		return justifyLastLine;
	}

	public void setJustifyLastLine(boolean justifyLastLine) {
		this.justifyLastLine = justifyLastLine;
	}

	public ReadingOrder getReadingOrder() {
		return readingOrder;
	}

	public void setReadingOrder(ReadingOrder readingOrder) {
		this.readingOrder = readingOrder;
	}

	public byte getTextRotation() {
		return textRotation;
	}

	public void setTextRotation(byte textRotation) {
		this.textRotation = textRotation;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("vertical: " + vertical + "\n");
		sb.append("horizontal: " + horizontal + "\n");
		sb.append("indent: " + indent + "\n");
		sb.append("relativeIndent: " + relativeIndent + "\n");
		sb.append("wrapText: " + wrapText + "\n");
		sb.append("shrinkToFit: " + shrinkToFit + "\n");
		sb.append("justifyLastLine: " + justifyLastLine + "\n");
		sb.append("ReadingOrder: " + readingOrder + "\n");
		sb.append("textRotation: " + textRotation + "\n");
		return sb.toString();
	}
}
