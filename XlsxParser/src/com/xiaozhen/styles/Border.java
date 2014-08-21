package com.xiaozhen.styles;

public class Border {

	// 线条类型，doubleL为xml中的double类型,默认为null
	public enum BorderStyle {
		hair, slantDashDot, dotted, mediumDashDot, dashDotDot, doubleL, medium, mediumDashed, dashDot, thin, dashed, thick, mediumDashDotDot
	}

	// 边框样式、颜色
	private BorderStyle bLeftStyle;
	private ColorStyle bLeftColor;

	private BorderStyle bRightStyle;
	private ColorStyle bRightColor;

	private BorderStyle bTopStyle;
	private ColorStyle bTopColor;

	private BorderStyle bBottomStyle;
	private ColorStyle bBottomColor;

	private BorderStyle bDiagonalStyle;
	private ColorStyle bDiagonalColor;

	private boolean setLeft = false;
	private boolean setRight = false;
	private boolean setTop = false;
	private boolean setBottom = false;
	private boolean setDiagonalUp = false;
	private boolean setDiagonalDown = false;

	public BorderStyle getbLeftStyle() {
		return bLeftStyle;
	}

	public void setbLeftStyle(BorderStyle bLeftStyle) {
		this.bLeftStyle = bLeftStyle;
		setLeft = true;
	}

	public ColorStyle getbLeftColor() {
		return bLeftColor;
	}

	public void setbLeftColor(ColorStyle bLeftColor) {
		this.bLeftColor = bLeftColor;
	}

	public BorderStyle getbRightStyle() {
		return bRightStyle;
	}

	public void setbRightStyle(BorderStyle bRightStyle) {
		this.bRightStyle = bRightStyle;
		setRight = true;
	}

	public ColorStyle getbRightColor() {
		return bRightColor;
	}

	public void setbRightColor(ColorStyle bRightColor) {
		this.bRightColor = bRightColor;
	}

	public BorderStyle getbTopStyle() {
		return bTopStyle;
	}

	public void setbTopStyle(BorderStyle bTopStyle) {
		this.bTopStyle = bTopStyle;
		setTop = true;
	}

	public ColorStyle getbTopColor() {
		return bTopColor;
	}

	public void setbTopColor(ColorStyle bTopColor) {
		this.bTopColor = bTopColor;
	}

	public BorderStyle getbBottomStyle() {
		return bBottomStyle;
	}

	public void setbBottomStyle(BorderStyle bBottomStyle) {
		this.bBottomStyle = bBottomStyle;
		setBottom = true;
	}

	public ColorStyle getbBottomColor() {
		return bBottomColor;
	}

	public void setbBottomColor(ColorStyle bBottomColor) {
		this.bBottomColor = bBottomColor;
	}

	public BorderStyle getbDiagonalStyle() {
		return bDiagonalStyle;
	}

	public void setbDiagonalStyle(BorderStyle bDiagonalStyle) {
		this.bDiagonalStyle = bDiagonalStyle;
	}

	public ColorStyle getbDiagonalColor() {
		return bDiagonalColor;
	}

	public void setbDiagonalColor(ColorStyle bDiagonalColor) {
		this.bDiagonalColor = bDiagonalColor;
	}

	public boolean isSetLeft() {
		return setLeft;
	}

	public void setLeft(boolean setLeft) {
		this.setLeft = setLeft;
	}

	public boolean isSetRight() {
		return setRight;
	}

	public void setRight(boolean setRight) {
		this.setRight = setRight;
	}

	public boolean isSetTop() {
		return setTop;
	}

	public void setTop(boolean setTop) {
		this.setTop = setTop;
	}

	public boolean isSetBottom() {
		return setBottom;
	}

	public void setBottom(boolean setBottom) {
		this.setBottom = setBottom;
	}

	public boolean isSetDiagonalUp() {
		return setDiagonalUp;
	}

	public void setDiagonalUp(boolean setDiagonalUp) {
		this.setDiagonalUp = setDiagonalUp;
	}

	public boolean isSetDiagonalDown() {
		return setDiagonalDown;
	}

	public void setDiagonalDown(boolean setDiagonalDown) {
		this.setDiagonalDown = setDiagonalDown;
	}

	@Override
	public String toString() {
		return "Border [bLeftStyle=" + bLeftStyle + ", bLeftColor="
				+ bLeftColor + ", bRightStyle=" + bRightStyle
				+ ", bRightColor=" + bRightColor + ", bTopStyle=" + bTopStyle
				+ ", bTopColor=" + bTopColor + ", bBottomStyle=" + bBottomStyle
				+ ", bBottomColor=" + bBottomColor + ", bDiagonalStyle="
				+ bDiagonalStyle + ", bDiagonalColor=" + bDiagonalColor
				+ ", setLeft=" + setLeft + ", setRight=" + setRight
				+ ", setTop=" + setTop + ", setBottom=" + setBottom
				+ ", setDiagonalUp=" + setDiagonalUp + ", setDiagonalDown="
				+ setDiagonalDown + "]";
	}
	
	
}
