package com.kingsoft.entities;

import java.util.List;
import java.util.Map;

public class WorkBook {

	private List<Sheet> sheets;
	private Map<String, String> relationShips; // workbook.xml.rels文件中的映射关系,key:关系id,value:文件target
	private List<String> sharedStringList;

	private Sheet sheet;
	
	// workbookView 属性
	private byte activeTab;
	private int windowHeight;
	private int windowWidth;
	private int yWindow;
	private int xWindow;
	
	public byte getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(byte activeTab) {
		this.activeTab = activeTab;
	}

	public int getWindowHeight() {
		return windowHeight;
	}

	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}

	public int getWindowWidth() {
		return windowWidth;
	}

	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}

	public int getyWindow() {
		return yWindow;
	}

	public void setyWindow(int yWindow) {
		this.yWindow = yWindow;
	}

	public int getxWindow() {
		return xWindow;
	}

	public void setxWindow(int xWindow) {
		this.xWindow = xWindow;
	}

	public Cell getCell(int sheetNum, int rowNum, int colNum) {

		Sheet destSheet;
		Row destRow;
		Cell cell = null;

		destSheet = getSheet(sheetNum);
		if (destSheet != null) {
			destRow = destSheet.getRow(rowNum);
			if (destRow != null) {
				cell = destRow.getCell(colNum);
			}
		}
		return cell;
	}

	public Sheet getSheet(int sheetNum) {
		if (sheets != null && sheetNum >= 0 && sheetNum < sheets.size()) {
			sheet = sheets.get(sheetNum);
		} else {
			System.err
					.println("WorkBook.getSheet() error : sheets size is no more than "
							+ getSheetsSize());
		}
		return sheet;
	}

	public int getSheetsSize() {
		int size = 0;
		if (sheets != null) {
			size = sheets.size();
		}
		return size;
	}

	public List<String> getSharedStringList() {
		return sharedStringList;
	}

	public void setSharedStringList(List<String> sharedStringList) {
		this.sharedStringList = sharedStringList;
	}

	protected List<Sheet> getSheets() {
		return sheets;
	}

	public Map<String, String> getRelationShips() {
		return relationShips;
	}

	public void setRelationShips(Map<String, String> relationShips) {
		this.relationShips = relationShips;
	}

	public void setSheets(List<Sheet> sheets) {
		this.sheets = sheets;
	}
}
