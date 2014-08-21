package com.xiaozhen.styles;

public class Fill {
	public enum PatternType {
		gray125, darkGray, mediumGray, lightGray, gray0625, 
		darkHorizontal, darkVertical, darkDown, darkUp, darkGrid, 
		darkTrellis, lightHorizontal, lightVertical, lightDown, 
		lightUp, lightGrid, lightTrellis, solid, none
	}

	private boolean patternFill = false; // 样式填充
	private boolean gradientFill = false; // 渐变填充

	private PatternType patternType; // 填充模式，默认为null
	private GradientFillEffects GFEffects; // 填充效果，默认为null

	private ColorStyle fgColor;
	private ColorStyle bgColor;

	public PatternType getPatternType() {
		return patternType;
	}

	public void setPatternType(PatternType patternType) {
		this.patternType = patternType;
		patternFill = true;
		gradientFill = false;
	}

	public GradientFillEffects getGFEffects() {
		return GFEffects;
	}

	public void setGFEffects(GradientFillEffects gFEffects) {
		GFEffects = gFEffects;
		patternFill = false;
		gradientFill = true;
	}

	public ColorStyle getFgColor() {
		return fgColor;
	}

	public void setFgColor(ColorStyle fgColor) {
		this.fgColor = fgColor;
	}

	public ColorStyle getBgColor() {
		return bgColor;
	}

	public void setBgColor(ColorStyle bgColor) {
		this.bgColor = bgColor;
	}

	public boolean isSetPatternFill() {
		return patternFill;
	}

	public boolean isSetGradientFill() {
		return gradientFill;
	}

	public boolean isSetFgColor() {
		return fgColor == null ? false : true;
	}

	public boolean isSetBgColor() {
		return bgColor == null ? false : true;
	}

	@Override
	public String toString() {
		return "Fill [patternFill=" + patternFill + ", gradientFill="
				+ gradientFill + ", patternType=" + patternType
				+ ", GFEffects=" + GFEffects + ", fgColor=" + fgColor
				+ ", bgColor=" + bgColor + "]";
	}

}
