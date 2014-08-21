package com.xiaozhen.entities;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Sheet {
	private String name;
	private String sheetId;
	private String rId;
	private Map<Integer, Row> rows;

	// 获取某一个row
	public Row getRow(int rowNum) {
		if (rows != null && rowNum > 0) {
			return rows.get(rowNum);
		} else
			return null;
	}

	public Iterator<Row> getRowsIterator() throws Exception {
		if (rows != null) {
			return rows.values().iterator();
		} else
			throw new Exception("EmptySheetException");

	}

	public int getRowsSize() {
		return rows.size();
	}

	// 返回最后一个row的位置
	public int getLastRowIndex() {
		if (rows.isEmpty()) {
			return -1;
		} else
			return ((TreeMap<Integer, Row>) rows).lastKey();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}

	public String getRId() {
		return rId;
	}

	public void setRId(String rId) {
		this.rId = rId;
	}

	protected Map<Integer, Row> getRows() {
		return rows;
	}

	public void setRows(Map<Integer, Row> rows) {
		this.rows = rows;
	}
}
