package com.xiaozhen.entities;

import com.xiaozhen.cellValue.CellType.ValueType;
import com.xiaozhen.cellValue.Value;

public class Cell {

	int id; // 标记cell位置
	short style; // 内容样式
	ValueType type; // 标记cell类型，若为null则是公式或者数值型
	Value value;// 存储内容,无内容的为null

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getStyle() {
		return style;
	}

	public void setStyle(short style) {
		this.style = style;
	}

	public ValueType getType() {
		return type;
	}

	public void setType(ValueType type) {
		this.type = type;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
}
