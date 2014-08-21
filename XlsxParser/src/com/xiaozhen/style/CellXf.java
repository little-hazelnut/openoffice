package com.xiaozhen.style;

public class CellXf {
	private int borderId; // 默认为0
	private int fillId; // 默认为0
	private int fontId; // 默认为0
	private int numFmtId; // 默认为0
	private int xfId; // 默认为0

	private Alignment alignment;

	public int getBorderId() {
		return borderId;
	}

	public void setBorderId(int borderId) {
		this.borderId = borderId;
	}

	public int getFillId() {
		return fillId;
	}

	public void setFillId(int fillId) {
		this.fillId = fillId;
	}

	public int getFontId() {
		return fontId;
	}

	public void setFontId(int fontId) {
		this.fontId = fontId;
	}

	public int getNumFmtId() {
		return numFmtId;
	}

	public void setNumFmtId(int numFmtId) {
		this.numFmtId = numFmtId;
	}

	public int getXfId() {
		return xfId;
	}

	public void setXfId(int xfId) {
		this.xfId = xfId;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	@Override
	public String toString() {
		return "CellXf [borderId=" + borderId + ", fillId=" + fillId
				+ ", fontId=" + fontId + ", numFmtId=" + numFmtId + ", xfId="
				+ xfId + ", alignment=" + alignment + "]";
	}

}
