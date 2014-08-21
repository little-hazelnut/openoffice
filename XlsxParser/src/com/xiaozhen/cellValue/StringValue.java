package com.xiaozhen.cellValue;

public class StringValue implements Value {
	int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public void setFormula(String str) {
	}

}
