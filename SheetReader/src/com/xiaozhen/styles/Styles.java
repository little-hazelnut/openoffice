package com.xiaozhen.styles;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Styles {
	private static Styles styles = new Styles();
	private Map<Integer,String> numFmts;
	private List<Font> fonts;
	private List<Fill> fills;
	private List<Border> borders;
	private List<CellXf> cellxfs;

	private Styles() {
	}

	public static Styles getInstance() {
		return styles;
	}

	public void setBorders(List<Border> borders) {
		this.borders = borders;
	}

	public void setFills(List<Fill> fills) {
		this.fills = fills;
	}

	public void setNumFmts(Map<Integer,String> numFmts) {
		this.numFmts = numFmts;
	}

	public void setFonts(List<Font> fonts) {
		this.fonts = fonts;
	}

	public void setCellxfs(List<CellXf> cellxfs) {
		this.cellxfs = cellxfs;
	}

	public int getCellxfsSize() {
		return cellxfs.size();
	}

	public Iterator<CellXf> getCellxfsIterator() {
		return cellxfs.iterator();
	}

	public Border getBorderStyle(int borderId) {
		if (borders != null && borderId >= 0 && borderId < borders.size())
			return borders.get(borderId);
		else
			return null;
	}

	public Fill getFillStyle(int fillId) {
		if (fills != null && fillId >= 0 && fillId < fills.size())
			return fills.get(fillId);
		else
			return null;
	}

	public Font getFontStyle(int fontId) {
		if (fonts != null && fontId >= 0 && fontId < fonts.size())
			return fonts.get(fontId);
		else
			return null;
	}

	public String getNumFmtStyle(int numFmtId) {
		if (numFmts != null && numFmts.containsKey(numFmtId))
			return numFmts.get(numFmtId);
		else
			return null;
	}

	public CellXf getCellXf(int i){
		return cellxfs.get(i);
	}
	
	@Override
	public String toString() {
		return "Styles elements size: [numFmts=" + numFmts.size() + ", fonts="
				+ fonts.size() + ", fills=" + fills.size() + ", borders="
				+ borders.size() + ", cellxfs=" + cellxfs.size() + "]";
	}

}
