package com.xiaozhen.style;

import java.util.List;

public class Styles {
	private static Styles styles = new Styles();
	private List<NumFmt> NumFmts;
	private List<Font> fonts;
	private List<Fill> fills;
	private List<Border> borders;
	private List<CellXf> cellxfs;
	
	private Styles() {
	}

	public static Styles getInstance() {
		return styles;
	}

	public List<Border> getBorders() {
		return borders;
	}

	public void setBorders(List<Border> borders) {
		this.borders = borders;
	}

	public List<Fill> getFills() {
		return fills;
	}

	public void setFills(List<Fill> fills) {
		this.fills = fills;
	}

	public List<NumFmt> getNumFmts() {
		return NumFmts;
	}

	public void setNumFmts(List<NumFmt> numFmts) {
		NumFmts = numFmts;
	}

	public List<Font> getFonts() {
		return fonts;
	}

	public void setFonts(List<Font> fonts) {
		this.fonts = fonts;
	}

	public List<CellXf> getCellxfs() {
		return cellxfs;
	}

	public void setCellxfs(List<CellXf> cellxfs) {
		this.cellxfs = cellxfs;
	}
	
	
}
