package com.xiaozhen.entities;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Row {
	private int rowId;
	private String x14ac_dyDescent;
	private String spans;
	private Map<Integer, Cell> cells;
	private Cell cell;

	public Cell getCell(int colNum) {
		if (cells != null && colNum > 0) {
			cell = cells.get(colNum);
		}
		return cell;
	}
	
	public Iterator<Cell> getCellsIteartor() {//按顺序返回行中的单元格集合
		return cells.values().iterator();
	}

	public int getCellsSize() {
		return cells.size();
	}

	// 返回最后一个cell的位置
	public int getLastCellIndex() {
		if (cells.size()!=0) {
			return ((TreeMap<Integer, Cell>) cells).lastKey();
		} else {
			return -1;	//映射为空则返回-1
		}

	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public String getX14ac_dyDescent() {
		return x14ac_dyDescent;
	}

	public void setX14ac_dyDescent(String x14ac_dyDescent) {
		this.x14ac_dyDescent = x14ac_dyDescent;
	}

	public String getSpans() {
		return spans;
	}

	public void setSpans(String spans) {
		this.spans = spans;
	}

	protected Map<Integer, Cell> getCells() {
		return cells;
	}

	public void setCells(Map<Integer, Cell> cells) {
		this.cells = cells;
	}

}
